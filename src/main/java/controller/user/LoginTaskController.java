package controller.user;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import data.User;
import repository.UsersDAO;

/*
 * 로그인 정보를 넘겨받아 처리할 컨트롤러
 */

@WebServlet("/user/login-task")
public class LoginTaskController extends HttpServlet{
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 인코딩
		req.setCharacterEncoding("utf-8");
		
		// 파라미터값 받아오기
		String id = req.getParameter("id");
		String pass = req.getParameter("pass");
				
		// 파라미터값 빈값 체크하기
		if(id.equals("") || pass.equals("")) {
			resp.sendRedirect("/user/login?error=1");
			return;
		}
		
		// 빈값이 없다면 그 정보를 토대로 DB에서 유저정보 받아오기
		User user = UsersDAO.findById(id);
		
		// 유저정보가 제대로 담겼는지 확인하기
		if(user == null) {
			resp.sendRedirect("/user/login?error=3");
			return;
		}
		
		// 정보가 제대로 담겼다면 파라미터 pass와 DB pass 일치 확인
		if(!pass.equals(user.getPass())) {
			resp.sendRedirect("/user/login?error=2");
			return;
		}
		
		// 세션에 로그인 정보 담기
		HttpSession session = req.getSession();
		session.setAttribute("logon", true);
		session.setAttribute("logonUser", user);
		
		// 로그인에 성공했다면 메인페이지로 이동
		resp.sendRedirect("/main/list");
		return;
	}
}
