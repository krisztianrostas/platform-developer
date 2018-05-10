package com.liferay.training.dynamicinclude;

import com.liferay.portal.kernel.servlet.taglib.DynamicInclude;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.osgi.service.component.annotations.Component;

/**
 * @author krisztian.rostas
 */
@Component(
	immediate = true,
	service = DynamicInclude.class
)
public class BlogsDynamicInclude implements DynamicInclude {

	  @Override
	    public void include(
	         HttpServletRequest request, HttpServletResponse response,
	         String key)
	       throws IOException {

	       PrintWriter printWriter = response.getWriter();

	       printWriter.println(
	         "<h2>Added by Blogs Dynamic Include!</h2><br />");
	    }

	    @Override
	    public void register(DynamicIncludeRegistry dynamicIncludeRegistry) {
	       dynamicIncludeRegistry.register(
	         "com.liferay.blogs.web#/blogs/view_entry.jsp#pre");
	    }

}