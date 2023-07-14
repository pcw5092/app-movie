package filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import data.User;

@WebFilter({"/main/*", "/post/*", "/comment/*", "/detail/*"})
public class AuthFilter extends HttpFilter {
	
	@Override
	protected void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		
		String url = request.getRequestURI();
		System.out.println("URL ==> "+ url);
		
		// if를 걸어서
		// 현재 요청을 보낸 사용자의 세션에 logon값이 true면
		// 필터를 통과시키고 아니면 로그인으로 보낸다
		User logonUser = (User)request.getSession().getAttribute("logonUser");
		boolean logon = (boolean) request.getSession().getAttribute("logon");
		if(logon && logonUser != null) {
			chain.doFilter(request, response);
		}else {
			System.out.println("필터ON");
			response.sendRedirect("/index");
		}
	}
}
