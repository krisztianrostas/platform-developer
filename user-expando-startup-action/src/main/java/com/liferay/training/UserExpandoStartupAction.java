package com.liferay.training;

import com.liferay.expando.kernel.exception.DuplicateColumnNameException;
import com.liferay.expando.kernel.exception.DuplicateTableNameException;
import com.liferay.expando.kernel.model.ExpandoColumn;
import com.liferay.expando.kernel.model.ExpandoColumnConstants;
import com.liferay.expando.kernel.model.ExpandoTable;
import com.liferay.expando.kernel.service.ExpandoColumnLocalService;
import com.liferay.expando.kernel.service.ExpandoTableLocalService;
import com.liferay.expando.kernel.service.ExpandoValueLocalService;
import com.liferay.portal.kernel.events.ActionException;
import com.liferay.portal.kernel.events.LifecycleAction;
import com.liferay.portal.kernel.events.LifecycleEvent;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.util.Portal;
import com.liferay.portal.kernel.util.PropsKeys;
import com.liferay.portal.kernel.util.UnicodeProperties;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author krisztian.rostas
 */
@Component(immediate = true, property = { "key=" + PropsKeys.LOGIN_EVENTS_POST }, service = LifecycleAction.class)
public class UserExpandoStartupAction implements LifecycleAction {
	
  @Override
	public void processLifecycleEvent(LifecycleEvent lifecycleEvent) throws ActionException {
		
		System.out.println("*** Expando in action ***");
		
		ExpandoTable table = null;
		long companyId = _portal.getCompanyId(lifecycleEvent.getRequest());

		try {

			try {
				table = expandoTableLocalService.addDefaultTable(companyId, User.class.getName());
			} catch (DuplicateTableNameException dtne) {
				table = expandoTableLocalService.getDefaultTable(companyId, User.class.getName());
			}

			long tableId = table.getTableId();

			try {

				// Add the expando column StudentMajor
				ExpandoColumn columnStudentMajor = expandoColumnLocalService.addColumn(tableId, "student-major",
						ExpandoColumnConstants.STRING);

				// Add the expando column EnrollmentDate
				expandoColumnLocalService.addColumn(tableId, "student-enrollment-date", ExpandoColumnConstants.DATE);

				// Add the expando column StudentId
				expandoColumnLocalService.addColumn(tableId, "studentid", ExpandoColumnConstants.INTEGER);

				// Set the indexable property on the column
				UnicodeProperties properties = new UnicodeProperties();
				properties.setProperty(ExpandoColumnConstants.INDEX_TYPE, Boolean.TRUE.toString());
				columnStudentMajor.setTypeSettingsProperties(properties);

				expandoColumnLocalService.updateExpandoColumn(columnStudentMajor);

			} catch (DuplicateColumnNameException dcne) {
				// do nothing
			}

		} catch (PortalException pe) {
			pe.printStackTrace();
		}

	}

	@Reference
	ExpandoColumnLocalService expandoColumnLocalService;
	@Reference
	ExpandoTableLocalService expandoTableLocalService;
	@Reference
	ExpandoValueLocalService expandoValueLocalService;
	
	@Reference(unbind = "-")
	protected void setPortal(Portal portal) {
		_portal = portal;
	}
	
	private Portal _portal;

}