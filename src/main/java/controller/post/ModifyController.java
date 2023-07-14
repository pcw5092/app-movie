package controller.post;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import data.Post;
import repository.PostsDAO;

/*
 * 글수정을 누르면 게시글 정보들을 담아 수정화면으로 넘겨줄 컨트롤러
 */

@WebServlet("/post/modify")
public class ModifyController extends HttpServlet {
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 인코딩
		req.setCharacterEncoding("utf-8");
		
		// 파라미터 받아오기
		String postId = req.getParameter("postId");
		
		// 빈값 체크
		if(postId.equals("")) {
			resp.sendRedirect("/post/detail?postId="+postId+"&error=1");
			return;
		}
		
		// DB에서 글 정보 뽑아오기
		Post post = PostsDAO.findByPost(postId);
		
		// 글정보 셋팅해주기
		req.setAttribute("post", post);

		req.getRequestDispatcher("/WEB-INF/views/main/modifypost.jsp").forward(req, resp);
	}
}
