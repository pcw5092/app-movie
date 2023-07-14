package controller.post;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import data.Post;
import data.User;
import repository.PostsDAO;

/*
 * 게시글 상세페이지 화면으로 넘겨줄 컨트롤러
 */

@WebServlet("/post/detail")
public class PostDetailController extends HttpServlet{
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 인코딩
		req.setCharacterEncoding("utf-8");
		
		// 파라미터 받아오기
		String postId = req.getParameter("postId");
		
		// 빈값 체크
		if(postId.equals("")) {
			resp.sendRedirect("/main/postlist");
			return;
		}
		
		// 유저정보를 가져온다.
		HttpSession session = req.getSession();
		User user = (User)session.getAttribute("logonUser");
		
		// DB에서 글 내용 뽑아오기
		Post post = PostsDAO.findByPost(postId);
		
		// 세션유저와 게시글 유저가 다르다면 조회수 증가
		if(!user.getId().equals(post.getId())) {
			int result = PostsDAO.postViewUpdate(postId);
		}
		
		// 글내용 세팅해주기
		req.setAttribute("post", post);
		
		// 디테일로 넘기기
		req.getRequestDispatcher("/WEB-INF/views/main/postdetail.jsp").forward(req, resp);
	}
}
