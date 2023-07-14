package controller.user;
/*
 * 회원가입 화면으로 넘겨줄 컨트롤러
 */

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


// 회원가입 화면으로 넘겨줄 컨트롤러
@WebServlet("/user/join")
public class JoinController extends HttpServlet{
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		req.getRequestDispatcher("/WEB-INF/views/user/join.jsp").forward(req, resp);
	}
}
