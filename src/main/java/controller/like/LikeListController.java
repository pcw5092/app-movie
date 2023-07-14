package controller.like;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import data.LikeActor;
import data.LikeDirector;
import data.LikeMovie;
import data.User;
import repository.LikesDAO;

/*
 * 찜 리스트 정보를 받아서 화면으로 넘겨줄 컨트롤러
 */

@WebServlet("/main/mylist")
public class LikeListController extends HttpServlet{
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 인코딩
		req.setCharacterEncoding("utf-8");
		
		// 유저정보를 가져온다.
		HttpSession session = req.getSession();
		User user = (User)session.getAttribute("logonUser");
		
		// DB에서 영화 좋아요 목록을 가져온다
		List<LikeMovie> movieLikeList = LikesDAO.findByLikeMoives(user.getId());
		// DB에서 배우 좋아요 목록을 가져온다
		List<LikeActor> actorLikeList = LikesDAO.findByLikeActors(user.getId());
		// DB에서 감독 좋아요 목록을 가져온다
		List<LikeDirector> directorLikeList = LikesDAO.findByLikeDirectors(user.getId());
		
		// 좋아요 리스트를 세팅한다.
		req.setAttribute("movieList", movieLikeList);
		req.setAttribute("actorList", actorLikeList);
		req.setAttribute("directorList", directorLikeList);
		
		// 마이리스트 페이지로 넘긴다.
		req.getRequestDispatcher("/WEB-INF/views/main/mylist.jsp").forward(req, resp);
	}
}
