package controller.movie;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
import data.movielist.Result;
import data.movielist.Results;
import data.person.Casts;
import repository.LikesDAO;
import util.MovieAPI;

/*
 * 영화 리스트 정보를 처리 후 담아서 메인화면으로 넘겨줄 컨트롤러
 */

@WebServlet("/main/list")
public class MovieListController extends HttpServlet {
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 인코딩
		req.setCharacterEncoding("utf-8");
		
		// 유저정보를 가져온다.
		HttpSession session = req.getSession();
		User user = (User)session.getAttribute("logonUser");
		
		// DB에 유저가 좋아요한 목록(영화,배우,감독)이 있는지 확인한 후 랜덤 추출한다
		if(user != null) {
			LikeActor actor = LikesDAO.actorFindRandom(user.getId());
			LikeDirector director = LikesDAO.directorFindRandom(user.getId());
			LikeMovie movie = LikesDAO.movieFindRandom(user.getId());
			
				// DB 좋아요 랜덤추출 데이터를 바탕으로 API 정보를 가져온다.(null이 아닐경우에)
				// 비슷한 영화 추천
				if(movie != null) {
					List<Result> similarList = new ArrayList<>();
					Results similarList1 = MovieAPI.getMovieSimilarList(movie.getMovieId());
					// 포스터 값이 null 이 아닌 영화만 담는다.
					if(similarList1.getTotal_results() != 0) {
						for(Result r : similarList1.getResults()) {
							if(r.getPoster_path() != null) {
								similarList.add(r);
							}
						}
					
						req.setAttribute("similarList", similarList);
						req.setAttribute("movieName", movie.getMovieName());
					}
				}
				
				// 감독의 연출작 추천
				if(director != null) {
					data.person.Cast directorfeat1 = MovieAPI.getRandomList(director.getDirectorId());
					List<Casts> directorfeat = new ArrayList<>();
					Set<String> list = new HashSet<>();					
					// 포스터 값이 null 이 아닌 영화만 담는다.
					for(data.person.Casts c : directorfeat1.getCrew()) {
						if(c.getPoster_path() != null) {
							list.add(c.getId());
						}
					}
					
					// 중복으로 들어가있는 ID를 하나로만 만든다.
					for(data.person.Casts c : directorfeat1.getCrew()) {
						if(list.contains(c.getId())) {
							directorfeat.add(c);
							list.remove(c.getId());
						}
						if(list.isEmpty()) {
							break;
						}
					}
										
					req.setAttribute("directorfeat", directorfeat);
					req.setAttribute("directorName", director.getDirectorName());
					req.setAttribute("directorfeatSize", directorfeat.size());
				}
				
				// 배우의 출연작 추천
				if(actor != null) {
					List<Casts> actorfeat = new ArrayList<>();
					data.person.Cast actorfeat1 = MovieAPI.getRandomList(actor.getActorId());
					// 포스터 값이 null 이 아닌 영화만 담는다.
					for(data.person.Casts c : actorfeat1.getCast()) {
						if(c.getPoster_path() != null) {
							actorfeat.add(c);
						}
					}
					req.setAttribute("actorfeat", actorfeat);
					req.setAttribute("actorName", actor.getActorName());
				}
		}
		
		// 인기순, 평점순, 현재 상영순으로 영화 리스트를 가져온다
		// 첫번째 파라미터 : 리스트타입, 두번째 파라미터 : 페이지
		Results topRateList = MovieAPI.getMovieList("top_rated", "1");
		Results popularList = MovieAPI.getMovieList("popular", "1");
		Results nowPlayingList = MovieAPI.getMovieList("now_playing", "1");
		
		// API 정보들을 담아준다
		req.setAttribute("topRate", topRateList.getResults());
		req.setAttribute("popular", popularList.getResults());
		req.setAttribute("nowPlaying", nowPlayingList.getResults());
		
		// 리스트화면으로 넘긴다.
		req.getRequestDispatcher("/WEB-INF/views/main/list.jsp").forward(req, resp);
	}
}
