package repository;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.apache.ibatis.session.SqlSession;

import data.Post;

public class PostsDAO extends DAO {
	
	// DB에 글등록을 처리해주는 메서드	==> insert
	public static int createPost(String postId, String id, String name, String title, String contents) {
		SqlSession session = factory.openSession(true);
		Map<String, Object> map = new HashMap<>();
		map.put("postId", postId);
		map.put("id", id);
		map.put("title", title);
		map.put("name", name);
		map.put("contents", contents);
		try {
			int result = session.insert("post.create", map);
			session.close();
			return result;
		} catch(Exception e) {
			e.printStackTrace();
			return -1;
		}
	}
	
	// DB에서 해당글 정보를 가저오는 메서드(글 상세보기에 필요함)	==> select
	public static Post findByPost(String postId) {
		SqlSession session = factory.openSession();
		try {
			return session.selectOne("post.findByPost", postId);
		} finally {
			session.close();
		}
	}
	
	// DB에서 a ~ b 번의 글 정보를 가저오는 메서드(게시판 리스트에 필요)	==> select
	public static List<Post> findByPostAtoB(int a, int b) {
		SqlSession session = factory.openSession();
		Map<String, Object> map = new HashMap<>();
		map.put("a", a);
		map.put("b", b);
		try {
			List<Post> list = new LinkedList<>();
			list = session.selectList("post.findByPostAtoB", map);
			
			return list;
		} finally {
			session.close();
		}
	}
	
	// DB에서 해당글을 삭제하는 메서드	==> delete
	public static int postDelecte(String postId) {
		SqlSession session = factory.openSession(true);
		try {
			int result = session.delete("post.postDelecte", postId); 
			session.close();
			return result;
		} catch(Exception e) {
			e.printStackTrace();
			return -1;
		}
	}
	
	// DB에서 글을 수정하는 메서드		==> update
	public static int postUpdate(String postId, String title, String contents) {
		SqlSession session = factory.openSession(true);
		Map<String, Object> map = new HashMap<>();
		map.put("postId", postId);
		map.put("title", title);
		map.put("contents", contents);
		try {
			int result = session.update("post.postUpdate", map);
			session.close();
			return result;
		} catch(Exception e) {
			e.printStackTrace();
			return -1;
		}
	}
	
	// DB에서 조회수를 증가시키는 메서드		==> update
	public static int postViewUpdate(String postId) {
		SqlSession session = factory.openSession(true);
		try {
			int result = session.update("post.updateViews", postId);
			session.close();
			return result;
		} catch(Exception e) {
			e.printStackTrace();
			return -1;
		}
	}
	
	// 게시글 전체 갯수 가져오는 메서드		==> select
	public static int postCount() {
		SqlSession session = factory.openSession(true);
		try {
			return session.selectOne("post.countPost");
		} finally {
			session.close();
		}
	}
	
	
	public static void main(String[] args) {
		
		/* 글 삭제 테스트
		int result = postDelecte("59959d44");
		System.out.println("1이면 성공 그외는 실패 ==> "+ result);
		*/
		
		/* 글 수정 테스트
		int result = postUpdate("59959d44", "잭스패로우입니다..", "내일도 또 오겠습니다.....");
		System.out.println("1이면 성공 그외는 실패 ==> "+ result);
		*/
		
		/* 글정보 갯수만큼 가저오는 테스트
		List<Post> list = findByPostAtoB(1, 4);
		for(Post p : list) {
			System.out.println("제목 ==> "+ p.getTitle());
			System.out.println("내용 ==> "+ p.getContents());
			System.out.println("작성자 ==> "+ p.getName());
			System.out.println("작성일 ==> "+ p.getDates());
			System.out.println();
		}
		*/
		
		/* 글 디테일 테스트
		Post post = findByPost("59959d44");
		System.out.println("제목 ==> "+ post.getTitle());
		System.out.println("내용 ==> "+ post.getContents());
		System.out.println("작성자 ==> "+ post.getName());
		System.out.println("작성일 ==> "+ post.getDates());
		*/
		
		/* 글등록 테스트
		String postId = UUID.randomUUID().toString().split("-")[0];
		int result = createPost(postId, "anwnlro", "잭스패로우", "가입했습니다.", "오늘가입했어요 잘부탁드려요");
		System.out.println("1이면 성공 그외는 실패 ==> "+ result);
		*/
	}
}
