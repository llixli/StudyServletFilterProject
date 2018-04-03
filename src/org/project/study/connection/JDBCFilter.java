package org.project.study.connection;

import java.io.IOException;
import java.sql.Connection;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;

/**
 * Servlet Filter implementation class JDBCFilter
 */
@WebFilter("/*")
public class JDBCFilter implements Filter {

    /**
     * Default constructor. 
     */
    public JDBCFilter() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		
		String servletPath = req.getServletPath();
		if (servletPath.contains("/specialPath1") || servletPath.contains("/specialPath2")) {
			Connection connection = null;
			try {
				connection = ConnectionUtils.getConnection();
				
				connection.setAutoCommit(false);
				
				MyUtils.storeConnection(request, connection);
				
				chain.doFilter(request, response);
			} catch(Exception e) {
				e.printStackTrace();
				ConnectionUtils.rollbackQuietly(connection);
				throw new ServletException();
			} finally {
				ConnectionUtils.closeQuietly(connection);
			}
		} else {
			chain.doFilter(request, response);
		}
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
