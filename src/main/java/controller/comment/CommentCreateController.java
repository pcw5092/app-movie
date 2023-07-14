package controller.comment;

import java.io.IOException;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import data.User;
import repository.CommentsDAO;

/*
 * 한줄평 작성 후 정보를 넘겨받아서 처리할 컨트롤러
 */

@WebServlet("/comment/create")
public class CommentCreateController extends HttpServlet{
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 인코딩
		req.setCharacterEncoding("utf-8");
		
		// 파라미터 값 받기
		String comment = req.getParameter("comment");
		String movieId = req.getParameter("movieId");
		
		// 유저정보를 가져온다.
		HttpSession session = req.getSession();
		User user = (User)session.getAttribute("logonUser");
				
		// 파라미터 값 빈값 체크
		if(comment == "") {
			resp.sendRedirect("/main/detail?movieId="+movieId+"&error=1");
			return;
		}
		
		// 한줄평 DB에 넣기
		String commentId = UUID.randomUUID().toString().split("-")[0];
		int result = CommentsDAO.createComment
		(commentId, movieId, user.getId(), user.getName(), comment);
		
		// DB에 데이터 등록 성공여부 확인
		if(result != 1) {
			resp.sendRedirect("/main/detail?movieId="+movieId+"&error=1");
			return;
		}
		
		resp.sendRedirect("/main/detail?movieId="+movieId);
		return;
	}
}
