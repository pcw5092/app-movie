package controller.search;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import data.youtube.Results;
import util.MovieAPI;

/*
 * 서치 정보를 넘겨줄 컨트롤러
 */

@WebServlet("/search/ajax")
public class SearchAjaxController extends HttpServlet {
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		
		String search = req.getParameter("search").replaceAll(" ", "+");
		
		data.movielist.Results searchList = MovieAPI.getMovieSearchList(search, "1");
		
		Gson gson = new Gson();
		
		resp.setContentType("text/json;charset=utf-8");
		PrintWriter out = resp.getWriter();
		out.println(gson.toJson(searchList.getResults()));
		
	}
}
