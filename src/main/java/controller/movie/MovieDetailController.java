package controller.movie;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import data.Comment;
import data.LikeActor;
import data.LikeDirector;
import data.LikeMovie;
import data.User;
import data.credit.Cast;
import data.credit.Casts;
import data.detail.MovieDetail;
import repository.CommentsDAO;
import repository.LikesDAO;
import repository.PostsDAO;
import util.MovieAPI;

/*
 * 영화 ID 를 이용해 영화 상세정보를 받아 상세보기페이지로 넘겨줄 컨트롤러
 */

@WebServlet("/main/detail")
public class MovieDetailController extends HttpServlet{
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 인코딩
		req.setCharacterEncoding("utf-8");
		
		// 파라미터값 받기
		String movieId = req.getParameter("movieId");
		
		// 파라미터 빈값 체크
		if(movieId.equals("")) {
			resp.sendRedirect("/main/list");
			return;
		}
		
		// 유저정보를 가져온다.
		HttpSession session = req.getSession();
		User user = (User)session.getAttribute("logonUser");
		
		// 해당 영화 디테일 받아오기
		MovieDetail detail = MovieAPI.getMoiveDetail(movieId);
		
		// 예고편 받아오기
		data.youtube.Results youtube = MovieAPI.getTrailer(movieId);
		
		// 메인 예고편 하나만 디테일 객체에 세팅해주기
		for(data.youtube.Result r : youtube.getResults()) {
			if(r.getType().equals("Trailer")) {
				detail.setKey(r.getKey());
			}
		}
		
		// 해당 영화 크레딧 받아오기
		Cast cast = MovieAPI.getCreditList(movieId);
		

		
		// 좋아요 여부 체크하기
		List<LikeMovie> likeList = LikesDAO.findByLikeMoives(user.getId());
		boolean check = false;
		if(likeList != null) {
			for(LikeMovie a : likeList) {
				if(a.getMovieId().equals(movieId)) {
					check = true;
					break;
				}
			}
		}
		
		// 크레딧 감독 1명 배우 5명 만 빼기
		int cnt = 0;
		// 배우 5명 빼기
		List<Casts> actors = new LinkedList<>();
		for(Casts c : cast.getCast()) {
			if(c.getKnown_for_department().equals("Acting") && Integer.parseInt(c.getOrder()) < 5) {
				actors.add(c);
				cnt ++;
			}
			if(cnt == 5) {
				break;
			}
		}
		
		// 유저의 배우 좋아요 리스트 뽑기
		List<LikeActor> likeActors = LikesDAO.findByLikeActors(user.getId());
		
		// 유저의 배우 좋아요 목록의 배우ID와 해당 디테일 배우들 ID를
		// 비교해서 같은ID가 있다면 Casts의 like를 true로 변경한다.
		for(Casts c : actors) {
			for(LikeActor l : likeActors) {
				if(c.getId().equals(l.getActorId())) {
					c.setLike(true);
				}
			}
		}
		
		// 감독 1명 빼기
		List<Casts> directors = new LinkedList<>();
		for(Casts c : cast.getCrew()) {
			if(c.getKnown_for_department().equals("Directing")) {
				directors.add(c);
				break;
			}
		}
		
		// 유저의 감독 좋아요 리스트 뽑기
		List<LikeDirector> likeDirector = LikesDAO.findByLikeDirectors(user.getId());
		
		// 유저의 감독 좋아요 목록의 감독ID와 해당 영화 디테일 감독 ID를
		// 비교해서 같은ID가 있다면 Casts의 like를 true로 변경한다.
		for(Casts c : directors) {
			for(LikeDirector l : likeDirector) {
				if(c.getId().equals(l.getDirectorId())) {
					c.setLike(true);
				}
			}
		}
		
		// 영화 디테일 값 및 배우와 감독값 / 한줄평 / 좋아요여부 세팅하기
		req.setAttribute("detail", detail);
		req.setAttribute("actors", actors);
		req.setAttribute("directors", directors);
		req.setAttribute("movielike", check);
		
		// 페이징 처리 시작
		String page = req.getParameter("page");
		
		// page 파라미터값이 null 이면 1로 고정 그게아니면 파람값으로
		int p;
		if(req.getParameter("page") == null) {
			p = 1;
		}else {
			p = Integer.parseInt(req.getParameter("page"));
		}
		
		// 파라미터 a, b값을 설계
		int a = (p-1)*10+1;
		int b = 10*p;
		
		// 한줄평 받아오기
		List<Comment> comList = CommentsDAO.findByCommentsAtoB(movieId, a, b);
		System.out.println(comList);
		// 한줄평 셋팅하기
		req.setAttribute("comments", comList);
		
		int total = CommentsDAO.commentCount(movieId);
		int totalPage = total/10 + (total % 10 > 0 ? 1 : 0);
		int viewPage = 5;
		
		int endPage = (((p-1)/viewPage)+1) * viewPage;
		if(totalPage < endPage) {
		    endPage = totalPage;
		}
		
		int startPage = ((p-1)/viewPage) * viewPage + 1;
		
		req.setAttribute("start", startPage);
		req.setAttribute("last", endPage);
		boolean existPrev = p >= 6;
		boolean existNext = true;
		if(endPage >= totalPage)
		{
			existNext = false;
		}
		
		req.setAttribute("existPrev", existPrev);
		req.setAttribute("existNext", existNext);
		
		req.getRequestDispatcher("/WEB-INF/views/main/detail.jsp").forward(req, resp);
	}
}
