package controller.post;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import repository.PostsDAO;

/*
 * 글수정 완료를 누르면 수정정보를 넘겨받아 처리할 컨트롤러
 */

@WebServlet("/post/modify-task")
public class ModifyTaskController extends HttpServlet {
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 인코딩
		req.setCharacterEncoding("utf-8");
		
		// 파라미터 값 받아오기
		String title = req.getParameter("title");
		String contents = req.getParameter("contents");
		String postId = req.getParameter("postId");
		
		// 빈값 체크
		if(title.equals("") || contents.equals("") || postId.equals("")) {
			resp.sendRedirect("/post/modify?postId="+postId+"&error=1");
			return;
		}
		
		// DB에 글정보 수정하기
		int result = PostsDAO.postUpdate(postId, title, contents);
		
		// 수정여부 확인하기
		
		// 수정 실패시
		if(result != 1) {
			resp.sendRedirect("/post/modify?postId="+postId+"&error=1");
			return;
		}
		
		// 수정 성공시 게시글 디테일로 보내기
		resp.sendRedirect("/post/detail?postId="+postId);
		return;
	}
}
