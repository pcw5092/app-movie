package controller.post;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import repository.PostsDAO;

/*
 * 글삭제를 누르면 처리해줄 컨트롤러
 */

@WebServlet("/post/delete")
public class PostDeleteController extends HttpServlet{
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 인코딩
		req.setCharacterEncoding("utf-8");
		
		// 파라미터 가져오기
		String postId = req.getParameter("postId");
		
		// 빈값 체크
		if(postId.equals("")) {
			resp.sendRedirect("/post/detail?postId="+postId);
			return;
		}
		
		// DB에서 글삭제
		int result = PostsDAO.postDelecte(postId);
		
		// 글삭제 실패시
		if(result != 1) {
			resp.sendRedirect("/post/detail?postId="+postId+"&error=1");
			return;
		}

		// 글삭제 성공시 게시판 리스트 페이지로
		resp.sendRedirect("/main/postlist");
		return;
	}
}
