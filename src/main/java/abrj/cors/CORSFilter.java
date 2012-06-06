package abrj.cors;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

// Written by Matthias Hryniszak in:
// http://padcom13.blogspot.com/2011/09/cors-filter-for-java-applications.html
// See also: http://enable-cors.org/
public class CORSFilter implements Filter {

    public CORSFilter() {
    }

    public void init(FilterConfig fConfig) throws ServletException {
    }

    public void destroy() {
    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        ((HttpServletResponse)response).addHeader("Access-Control-Allow-Origin", "*");
        chain.doFilter(request, response);
    }
}
