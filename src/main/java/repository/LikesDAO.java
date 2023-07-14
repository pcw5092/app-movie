package repository;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import data.LikeActor;
import data.LikeDirector;
import data.LikeMovie;

public class LikesDAO extends DAO {
	
	
	// =================영화 좋아요 부분 시작
	// DB에 영화 좋아요 등록 하는 메서드		==> insert
	public static int createLikeMoive(String movieId, String id, String posterURL, String movieName) {
		SqlSession session = factory.openSession(true);
		Map<String, Object> map = new HashMap<>();
		map.put("movieId", movieId);
		map.put("id", id);
		map.put("posterURL", posterURL);
		map.put("movieName", movieName);
		try {
			int result = session.insert("like.movieLikeCreate", map);
			session.close();
			return result;
		} catch(Exception e) {
			e.printStackTrace();
			return -1;
		}
	}
	
	// DB에서 사용자의 영화 좋아요 목록을 가져오는 메서드		==> select
	public static List<LikeMovie> findByLikeMoives(String id) {
		SqlSession session = factory.openSession();
		try {
			List<LikeMovie> list = new LinkedList<>();
			list = session.selectList("like.movieFindById", id);
			return list;
		} finally {
			session.close();
		}
	}
	
	// DB에서 사용자의 특정 영화 좋아요를 삭제하는 메서드		==> delete
	public static int deleteLikeMovie(String movieId, String id) {
		SqlSession session = factory.openSession(true);
		Map<String, Object> map = new HashMap<>();
		map.put("movieId", movieId);
		map.put("id", id);
		try {
			int result = session.delete("like.movieLikeDelete", map);
			session.close();
			return result;
		} catch(Exception e) {
			e.printStackTrace();
			return -1;
		}
	}
	
	// DB에서 사용자의 영화 좋아요 목록중 하나를 랜덤 추출하는 메서드			==> select
	public static LikeMovie movieFindRandom(String id) {
		SqlSession session = factory.openSession(true);
		try {
			return session.selectOne("like.movieFindRandom", id);
		} finally {
			session.close();
		}
	}
	// =================영화 좋아요 부분 끝
	
	// =================배우 좋아요 부분 시작
	// DB에 배우 좋아요 등록 하는 메서드		==> insert
	public static int createLikeActor(String actorId, String id, String posterURL, String actorName) {
		SqlSession session = factory.openSession(true);
		Map<String, Object> map = new HashMap<>();
		map.put("actorId", actorId);
		map.put("id", id);
		map.put("posterURL", posterURL);
		map.put("actorName", actorName);
		try {
			int result = session.insert("like.actorLikeCreate", map);
			session.close();
			return result;
		} catch(Exception e) {
			e.printStackTrace();
			return -1;
		}
	}
	
	// DB에서 사용자의 배우 좋아요 목록을 가져오는 메서드		==> select
	public static List<LikeActor> findByLikeActors (String id) {
		SqlSession session = factory.openSession();
		try {
			List<LikeActor> list = new LinkedList<>();
			list = session.selectList("like.actorFindById", id);
			return list;
		} finally {
			session.close();
		}
	}
		
	// DB에서 사용자의 특정 배우 좋아요를 삭제하는 메서드		==> delete
	public static int deleteLikeActor (String actorId, String id) {
		SqlSession session = factory.openSession(true);
		Map<String, Object> map = new HashMap<>();
		map.put("actorId", actorId);
		map.put("id", id);
		try {
			int result = session.delete("like.actorLikeDelete", map);
			session.close();
			return result;
		} catch(Exception e) {
			e.printStackTrace();
			return -1;
		}
	}
	
	// DB에서 사용자의 배우 좋아요 목록중 하나를 랜덤 추출하는 메서드		==> select
		public static LikeActor actorFindRandom(String id) {
			SqlSession session = factory.openSession(true);
			try {
				return session.selectOne("like.actorFindRandom", id);
			} finally {
				session.close();
			}
		}
	// =================배우 좋아요 부분 끝
		
	// =================감독 좋아요 부분 시작
	// DB에 감독 좋아요 등록 하는 메서드			==> insert
	public static int createLikeDirector(String directorId, String id, String posterURL, String directorName) {
		SqlSession session = factory.openSession(true);
		Map<String, Object> map = new HashMap<>();
		map.put("directorId", directorId);
		map.put("id", id);
		map.put("posterURL", posterURL);
		map.put("directorName", directorName);
		try {
			int result = session.insert("like.directorLikeCreate", map);
			session.close();
			return result;
		} catch(Exception e) {
			e.printStackTrace();
			return -1;
		}
	}
	
	// DB에서 사용자의 감독 좋아요 목록을 가져오는 메서드		==> select
	public static List<LikeDirector> findByLikeDirectors (String id) {
		SqlSession session = factory.openSession();
		try {
			List<LikeDirector> list = new LinkedList<>();
			list = session.selectList("like.directorFindById", id);
			return list;
		} finally {
			session.close();
		}
	}
	
	// DB에서 사용자의 특정 배우 좋아요를 삭제하는 메서드		==> delete
	public static int deleteLikeDirector(String directorId, String id) {
		SqlSession session = factory.openSession(true);
		Map<String, Object> map = new HashMap<>();
		map.put("directorId", directorId);
		map.put("id", id);
		try {
			int result = session.delete("like.directorLikeDelete", map); 
			session.close();
			return result;
		} catch(Exception e) {
			e.printStackTrace();
			return -1;
		}
	}
	
	// DB에서 사용자의 감독 좋아요 목록중 하나를 랜덤 추출하는 메서드		==> select
	public static LikeDirector directorFindRandom(String id) {
		SqlSession session = factory.openSession(true);
		try {
			return session.selectOne("like.directorFindRandom", id);
		} finally {
			session.close();
		}
	}
	// =================감독 좋아요 부분 끝
		
	
	public static void main(String[] args) {
		
		/* 사용자의 감독 좋아요 삭제 테스트
		int result = deleteLikeDirector("3f3f3f", "anwnlro");
		System.out.println("1이면 성공 그외는 실패 ==> "+ result);
		*/
		
		/* 사용자의 감독 좋아요 리스트 가져오기 테스트
		List<LikeDirector> list = findByLikeDirectors("anwnlro");
		list.forEach(l -> {
			System.out.println("좋아요 한사람 ==> "+ l.getId());
			System.out.println(l.getDirectorid());
			System.out.println(l.getPosterUrl());
			System.out.println();
		});
		*/
		
		/* 감독 좋아요 등록 테스트
		int result = createLikeDirector("4f4f4f", "master", "abc.png");
		System.out.println("1이면 성공 그외는 실패 ==> "+ result);
		*/
		
		/* 사용자의 배우 좋아요 삭제 테스트
		int result = deleteLikeActor("33f2bw", "anwnlro");
		System.out.println("1이면 성공 그외는 실패 ==> "+ result);
		*/
		
		/* 사용자의 배우 좋아요 리스트 가져오기 테스트
		List<LikeActor> list = findByLikeActors("anwnlro");
		list.forEach(l -> {
			System.out.println("좋아요 한사람 ==> "+ l.getId());
			System.out.println(l.getActorId());
			System.out.println(l.getPosterUrl());
			System.out.println();
		});
		*/
		
		/* 배우 좋아요 등록 테스트
		int result = createLikeActor("bbrbretewt", "anwnlro", "abc.png");
		System.out.println("1이면 성공 그외는 실패 ==> "+ result);
		*/
		
		/* 사용자의 영화 좋아요 삭제 테스트
		int result = deleteLikeMovie("1q2w3e4r", "anwnlro");
		System.out.println("1이면 성공 그외는 실패 ==> "+ result);
		*/
		
		/* 사용자의 영화 좋아요 리스트 가져오기 테스트
		List<LikeMovie> list = findByLikeMoives("anwnlro");
		list.forEach(l -> {
			System.out.println("좋아요 한사람 ==> "+ l.getId());
			System.out.println(l.getMovieId());
			System.out.println(l.getPosterUrl());
			System.out.println();
		});
		*/
		
		/* 영화 좋아요 등록 테스트
		int result = createLikeMoive("berg31rf1", "anwnlro", "abc.png");
		System.out.println("1이면 성공 그외는 실패 ==> "+ result);
		*/
		
	}
}

