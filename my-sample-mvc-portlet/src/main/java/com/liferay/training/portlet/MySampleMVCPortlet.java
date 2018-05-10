package com.liferay.training.portlet;

import com.liferay.portal.configuration.metatype.bnd.util.ConfigurableUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;
import com.liferay.training.configuration.MySampleMVCPortletConfiguration;
import com.liferay.training.constants.MySampleMVCPortletKeys;

import java.io.IOException;
import java.util.Map;

import javax.portlet.Portlet;
import javax.portlet.PortletException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Modified;

/**
 * @author krisztian.rostas
 */
@Component(configurationPid = "com.liferay.training.configuration.MySampleMVCPortletConfiguration", immediate = true, property = {
		"com.liferay.portlet.display-category=category.sample", "com.liferay.portlet.instanceable=true",
		"javax.portlet.display-name=my-sample-mvc-portlet Portlet", "javax.portlet.init-param.template-path=/",
		"javax.portlet.init-param.view-template=/view.jsp", "javax.portlet.name=" + MySampleMVCPortletKeys.MySampleMVC,
		"javax.portlet.resource-bundle=content.Language",
		"javax.portlet.security-role-ref=power-user,user" }, service = Portlet.class)

public class MySampleMVCPortlet extends MVCPortlet {

	@Override
	public void doView(RenderRequest renderRequest, RenderResponse renderResponse)
			throws IOException, PortletException {

		renderRequest.setAttribute(MySampleMVCPortletConfiguration.class.getName(), _configuration);

		super.doView(renderRequest, renderResponse);
	}

	@Activate
	@Modified
	protected void activate(Map<String, Object> properties) {
		_configuration = ConfigurableUtil.createConfigurable(MySampleMVCPortletConfiguration.class, properties);
	}

	private volatile MySampleMVCPortletConfiguration _configuration;

}