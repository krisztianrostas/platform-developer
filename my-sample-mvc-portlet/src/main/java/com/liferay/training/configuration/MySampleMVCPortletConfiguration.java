package com.liferay.training.configuration;

import aQute.bnd.annotation.metatype.Meta;

@Meta.OCD(id = "com.liferay.training.configuration.MySampleMVCPortletConfiguration")
public interface MySampleMVCPortletConfiguration {

	@Meta.AD(deflt = "blue", required = false)
	public String favoriteColor();

	@Meta.AD(deflt = "red|green|blue", required = false)
	public String[] validColors();

	@Meta.AD(required = false)
	public int itemsPerPage();
}
