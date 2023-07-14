package controller.comment;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import repository.CommentsDAO;

@WebServlet("/comment/delete")
public class CommentDeleteController extends HttpServlet {
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 인코딩
		req.setCharacterEncoding("utf-8");
		
		// 파라미터 받아오기
		String commentId = req.getParameter("commentId");
		String movieId = req.getParameter("movieId");
		
		// 파라미터 빈값 체크
		if(commentId.equals("") && movieId.equals("")) {
			resp.sendRedirect("/index");
			return;
		}
		
		// 한줄평 삭제하기
		int reuslt = CommentsDAO.deleteComment(commentId);
		
		// 삭제 여부 확인
		if(reuslt != 1) {
			resp.sendRedirect("/main/detail?movieId="+movieId+"&error=2");
			return;
		}
		
		// 삭제 후 다시 해당영화 디테일로 이동
		resp.sendRedirect("/main/detail?movieId="+movieId);
		return;
	}
}
