package controller.user;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import repository.UsersDAO;
import util.Verify;

/*
 * 회원가입 정보를 넘겨받아 처리할 컨트롤러
 */

@WebServlet("/user/join-task")
public class JoinTaskController extends HttpServlet {
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 인코딩
		req.setCharacterEncoding("utf-8");
		
		// 파라미터값 받아오기
		String id = req.getParameter("id");
		String pass = req.getParameter("pass");
		String name = req.getParameter("name");
						
		// 회원가입 유효성 검사
		int error = Verify.SignupValidator(id, pass, name);
		if(error == 1) {
			resp.sendRedirect("/user/join?error=1");
			return;
		}else if(error == 2) {
			resp.sendRedirect("/user/join?error=2");
			return;
		}else if(error == 3) {
			resp.sendRedirect("/user/join?error=3");
			return;
		}
		
		// 유효성 통과 후 회원가입 정보를 DB에 넣기
		int result = UsersDAO.createUser(id, pass, name);
		
		// 회원가입정보가 DB에 등록됐는지 여부 체크
		if(result != 1) {
			error = 4;
			resp.sendRedirect("/user/join?error=4");
			return;
		}
		
		// 유효성 통과 / 회원가입 정보가 DB에 등록됐다면 로그인페이지로 넘겨줌
		req.getRequestDispatcher("/WEB-INF/views/user/login.jsp").forward(req, resp);
	}
}
