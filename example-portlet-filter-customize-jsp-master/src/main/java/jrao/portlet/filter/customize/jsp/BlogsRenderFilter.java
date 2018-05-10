package jrao.portlet.filter.customize.jsp;

import java.io.IOException;

import javax.portlet.PortletException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import javax.portlet.filter.FilterChain;
import javax.portlet.filter.FilterConfig;
import javax.portlet.filter.PortletFilter;
import javax.portlet.filter.RenderFilter;
import javax.portlet.filter.RenderResponseWrapper;

import org.osgi.service.component.annotations.Component;

import com.liferay.portal.kernel.util.PortletKeys;

/**
 * @author jrao
 */
@Component(
	immediate = true,
	property = {
			"javax.portlet.name=" + PortletKeys.BLOGS
	},
	service = PortletFilter.class
)
public class BlogsRenderFilter implements RenderFilter {

	@Override
	public void init(FilterConfig config) throws PortletException {

	}

	@Override
	public void destroy() {

	}

	@Override
	public void doFilter(RenderRequest request, RenderResponse response, FilterChain chain)
			throws IOException, PortletException {
		
		RenderResponseWrapper renderResponseWrapper = new BufferedRenderResponseWrapper(response);

		chain.doFilter(request, renderResponseWrapper);

		String text = renderResponseWrapper.toString();
		
		if (text != null) {
			String interestingText = "<input  class=\"field form-control\"";

			int index = text.lastIndexOf(interestingText);

			if (index >= 0) {
				String newText1 = text.substring(0, index);
				String newText2 = "\n<p>Added by Blogs Render Filter!</p>\n";
				String newText3 = text.substring(index);
				
				String newText = newText1 + newText2 + newText3;
				
				response.getWriter().write(newText);
			}
		}
	}

}
