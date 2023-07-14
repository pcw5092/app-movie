package controller.search;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import data.Post;
import repository.PostsDAO;
import util.MovieAPI;

@WebServlet("/main/search")
public class SearchController extends HttpServlet {
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		
		String page = req.getParameter("page");
		String search = req.getParameter("search").replaceAll(" ", "+");
		
		// page 파라미터값이 null 이면 1로 고정 그게아니면 파람값으로
		int p;
		if(req.getParameter("page") == null) {
			p = 1;
		}else {
			p = Integer.parseInt(req.getParameter("page"));
		}
		
		data.movielist.Results searchList = MovieAPI.getMovieSearchList(search, page == null ? "1" : page);
		if(searchList == null) {
			req.getRequestDispatcher("/WEB-INF/views/main/search.jsp?error=1").forward(req, resp);
			return;
		}
		
		int total = searchList.getTotal_results();
		int totalPage = total/20 + (total % 20 > 0 ? 1 : 0);
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
		
		req.setAttribute("searchList", searchList.getResults());
		req.setAttribute("searchResult", search.contains("+") ? search.replaceAll("\\+", " ") : search);
		req.setAttribute("searchSize", searchList.getTotal_results());
		
		req.getRequestDispatcher("/WEB-INF/views/main/search.jsp").forward(req, resp);
	}
}
