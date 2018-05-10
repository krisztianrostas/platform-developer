<%@ include file="/init.jsp" %>

<p>
	<b><liferay-ui:message key="my-sample-mvc-portlet.caption"/></b>
</p>


<%
MySampleMVCPortletConfiguration configuration = (MySampleMVCPortletConfiguration) GetterUtil.getObject(
    renderRequest.getAttribute(MySampleMVCPortletConfiguration.class.getName()));

String favoriteColor = configuration.favoriteColor();
%>

<p>Favorite color: <span style="color: <%= favoriteColor %>;"><%= favoriteColor %></span></p