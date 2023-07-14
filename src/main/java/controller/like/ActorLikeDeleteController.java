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

@WebServlet("/detail/actor-unlike")
public class ActorLikeDeleteController extends HttpServlet{
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 인코딩
		req.setCharacterEncoding("utf-8");
		
		// 파라미터 받아오기
		String movieId = req.getParameter("movieId");
		String actorId = req.getParameter("actorId");
		// 디테일로갈지 마이리스트로 갈지 정하는 파라미터
		String position = req.getParameter("position"); 
		
		// 유저정보를 가져온다.
		HttpSession session = req.getSession();
		User user = (User)session.getAttribute("logonUser");
		
		
		int result = LikesDAO.deleteLikeActor(actorId, user.getId());
		
		// 삭제 여부 확인
		if(result != 1) {
			resp.sendRedirect("/main/detail?movieId="+movieId+"&error=4");
			return;
		}
		
		// 다시 디테일로 보낸다.
		if(position.equals("detail")) {
			resp.sendRedirect("/main/detail?movieId="+movieId);
			return;
		}else if(position.equals("mylist")) {
			resp.sendRedirect("/main/mylist");
			return;
		}
	}
}
