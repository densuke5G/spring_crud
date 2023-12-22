package jp.co.sss.crud.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LoginFilter extends HttpFilter {

	@Override
	protected void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		String requestURL = request.getRequestURI();
		if (requestURL.endsWith("/") || requestURL.endsWith("/login")) {
			chain.doFilter(request, response);
			
		} else {
			HttpSession session = request.getSession();
			Object employee = session.getAttribute("employees");
			if (employee == null) {
				response.sendRedirect("/spring_crud/");
			} else {
				chain.doFilter(request, response);
			}
		}
	}
	
	
}
