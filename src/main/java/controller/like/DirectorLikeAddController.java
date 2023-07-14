package controller.like;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import data.User;
import repository.LikesDAO;

@WebServlet("/detail/director-like")
public class DirectorLikeAddController extends HttpServlet {
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 인코딩
		req.setCharacterEncoding("utf-8");
		
		// 파라미터 받아오기
		String movieId = req.getParameter("movieId");
		String directorId = req.getParameter("directorId");
		String posterURL = req.getParameter("posterURL");
		String directorName = req.getParameter("directorName");
		
		// 유저정보를 가져온다.
		HttpSession session = req.getSession();
		User user = (User)session.getAttribute("logonUser");
		
		// DB에 좋아요 추가
		int result = LikesDAO.createLikeDirector(directorId, user.getId(), posterURL, directorName);
		
		// 추가 여부 확인
		if(result != 1) {
			resp.sendRedirect("/main/detail?movieId="+movieId+"&error=3");
			return;
		}
		
		resp.sendRedirect("/main/detail?movieId="+movieId);
		return;
		
	}
}
