package com.liferay.training.autologin;

import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.security.auto.login.AutoLogin;
import com.liferay.portal.kernel.security.auto.login.AutoLoginException;
import com.liferay.portal.kernel.service.UserLocalService;
import com.liferay.portal.kernel.util.Portal;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author krisztian.rostas
 */
@Component(
	immediate = true,
	service = AutoLogin.class
)
public class AutoLoginExample implements AutoLogin {

	@Override
	public String[] handleException(HttpServletRequest request, HttpServletResponse response, Exception e)
			throws AutoLoginException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String[] login(HttpServletRequest request, HttpServletResponse response) throws AutoLoginException {
		// TODO Auto-generated method stub
		
        String screenName = request.getParameter("insecurely_login_user");
        if (screenName == null || screenName.isEmpty())
            return null;

        try {
            long companyId = _portal.getCompanyId(request);
            User user = _userLocalService.getUserByScreenName(companyId,
                    screenName);
            return new String[] { String.valueOf(user.getUserId()),
                    user.getPassword(),
                    String.valueOf(user.isPasswordEncrypted()) };
        } catch (Exception e) {
            return null;
        }
	}
	
	@Reference
	private volatile UserLocalService _userLocalService;
	
	@Reference(unbind = "-")
	protected void setPortal(Portal portal) {
		_portal = portal;
	}
	
	private Portal _portal;

}