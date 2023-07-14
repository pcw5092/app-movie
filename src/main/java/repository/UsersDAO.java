package repository;

import java.util.HashMap;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import data.User;

public class UsersDAO extends DAO {
	
	// DB에 회원가입을 처리하는 메서드		==> insert
	public static int createUser(String id, String pass, String name) {
			SqlSession session = factory.openSession(true);
			Map<String, Object> map = new HashMap<>();
			map.put("id", id);
			map.put("pass", pass);
			map.put("name", name);
		try {
			int result = session.insert("users.create", map);;
			session.close();
			
			return result;
		} catch(Exception e) {
			e.printStackTrace();
			return -1;
		}
	}
	
	// DB에서 유저정보를 가져오는 테스트(로그인시 필요함)		==> select
	public static User findById(String id) {
		SqlSession session = factory.openSession();
		try {
			return session.selectOne("users.findById", id);
		} finally {
			session.close();
		}
	}
	
	
	public static void main(String[] args) {
		
		/* 유저정보 가져오기 테스트
		User user = findById("anwnlro");
		System.out.println(user.getId());
		System.out.println(user.getName());
		System.out.println(user.getPass());
		*/
		
		/* 회원가입 테스트
		int result = createUser("anwnlro","1q2w3e4r", "잭스패로우");
		System.out.println(result);
		*/
	}
}
