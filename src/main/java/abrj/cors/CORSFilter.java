package abrj.cors;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

// Original version written by Matthias Hryniszak in:
// http://padcom13.blogspot.com/2011/09/cors-filter-for-java-applications.html
// See also: http://enable-cors.org/
// Modified to change Access-Control-Allow-Headers to x-requested-with instead of *.
public class CORSFilter implements Filter {

    public CORSFilter() {
    }

    public void init(FilterConfig fConfig) throws ServletException {
    }

    public void destroy() {
    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        // this doesn't allow response to continue according to James:
        // http://stackoverflow.com/questions/5750696/how-to-get-a-cross-origin-resource-sharing-cors-post-request-working
        //((HttpServletResponse)response).addHeader("Access-Control-Allow-Origin", "*");
        // instead use Access-Control-Allow-Headers set to x-requested-with in service and in client
        ((HttpServletResponse)response).addHeader("Access-Control-Allow-Headers", "x-requested-with");
        chain.doFilter(request, response);
    }
}
