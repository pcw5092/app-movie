package controller.post;

import java.io.IOException;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import data.User;
import repository.PostsDAO;

/*
 * 글작성 완료를 누르면 게시글 정보를 받아 처리할 컨트롤러
 */

@WebServlet("/post/write-task")
public class PostWriteTaskController extends HttpServlet{
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 인코딩
		req.setCharacterEncoding("utf-8");
		
		// 파라미터 받아오기
		String title = req.getParameter("title");
		String contents = req.getParameter("contents");
		String postId = UUID.randomUUID().toString().split("-")[0];
		
		// 빈값 체크
		if(title.equals("") || contents.equals("")) {
			resp.sendRedirect("/post/write?error=1");
			return;
		}
		
		// 유저정보를 가져온다.
		HttpSession session = req.getSession();
		User user = (User)session.getAttribute("logonUser");
		
		// DB에 게시글정보 등록하기
		int result = PostsDAO.createPost(postId, user.getId(), user.getName(), title, contents);
		
		// 등록여부 확인
		
		// 글등록 실패
		if(result != 1) {
			resp.sendRedirect("/post/write?error=1");
			return;
		}
		
		// 글등록 성공시 본인 글 디테일로 이동
		resp.sendRedirect("/post/detail?postId="+postId);
		return;
	}
}
