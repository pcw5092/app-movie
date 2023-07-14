package util;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.google.gson.Gson;

import data.credit.Cast;
import data.credit.Casts;
import data.detail.MovieDetail;
import data.detail.MovieGenre;
import data.detail.MovieGenres;
import data.movielist.Result;
import data.movielist.Results;

public class MovieAPI {
	
	// 추천영화 리스트를 타입별로 가져오는 메서드
	public static Results getMovieList(String type, String page) {
		try {
			// type
			// top_rated(평점순), popular(인기순), now_playing(현재상영중), latest(최신순), upcoming(상영예정작)
			String target = "https://api.themoviedb.org/3/movie/"+ type +"";

			Map<String, String> params = new LinkedHashMap<>();
			params.put("api_key", "8069e75d4de0c85b5ade5fc677a893a5");
			params.put("language", "ko-KR");
			params.put("region", "KR");
			params.put("page", page);
			String queryString = QueryStringBuilder.build(params);

			URI uri = new URI(target + "?" + queryString);

			HttpClient client = HttpClient.newHttpClient();
			HttpRequest req = HttpRequest.newBuilder().uri(uri).GET().build();
			HttpResponse<String> resp = client.send(req, BodyHandlers.ofString());

			Gson gson = new Gson();

			Results result = gson.fromJson(resp.body(), Results.class);
			
			return result;

		} catch (Exception e) {
			e.printStackTrace();

			return null;
		}
	}
	
	// 특정 영화의 디테일을 가져오는 메서드
	public static MovieDetail getMoiveDetail(String movieId) {
		try {
			String target = "https://api.themoviedb.org/3/movie/" + movieId + "";

			Map<String, String> params = new LinkedHashMap<>();
			params.put("api_key", "8069e75d4de0c85b5ade5fc677a893a5");
			params.put("language", "ko-KR");

			String queryString = QueryStringBuilder.build(params);

			URI uri = new URI(target + "?" + queryString);

			HttpClient client = HttpClient.newHttpClient();
			HttpRequest req = HttpRequest.newBuilder().uri(uri).GET().build();
			HttpResponse<String> resp = client.send(req, BodyHandlers.ofString());

			Gson gson = new Gson();

			MovieDetail result = gson.fromJson(resp.body(), MovieDetail.class);
			
			return result; 

		} catch (Exception e) {
			e.printStackTrace();
			
			return null;
		}
	}

	// 특정 영화의 크레딧을 가져오는 메서드
	public static data.credit.Cast getCreditList(String movieId) {
		try {
			String target = "https://api.themoviedb.org/3/movie/" + movieId + "/credits";

			Map<String, String> params = new LinkedHashMap<>();
			params.put("api_key", "8069e75d4de0c85b5ade5fc677a893a5");
			params.put("language", "ko-KR");

			String queryString = QueryStringBuilder.build(params);
			
			URI uri = new URI(target + "?" + queryString);

			HttpClient client = HttpClient.newHttpClient();
			HttpRequest req = HttpRequest.newBuilder().uri(uri).GET().build();
			HttpResponse<String> resp = client.send(req, BodyHandlers.ofString());

			Gson gson = new Gson();
			
			Cast result = gson.fromJson(resp.body(), Cast.class);
			
			return result;
			
		} catch (Exception e) {
			e.printStackTrace();
			
			return null;
		}
	}
	
	// 해당 인물의 출연작을 가져오는 메서드
	public static data.person.Cast getRandomList(String id) {
		try {
			String target = "https://api.themoviedb.org/3/person/"+ id +"/movie_credits";
			
			Map<String, String> params = new LinkedHashMap<>();
			params.put("api_key", "8069e75d4de0c85b5ade5fc677a893a5");
			params.put("language", "ko-KR");
			
			String queryString = QueryStringBuilder.build(params);
			
			URI uri = new URI(target + "?" + queryString);
			
			HttpClient client = HttpClient.newHttpClient();
			HttpRequest req = HttpRequest.newBuilder().uri(uri).GET().build();
			HttpResponse<String> resp = client.send(req, BodyHandlers.ofString());
			
			Gson gson = new Gson();
			
			data.person.Cast result = gson.fromJson(resp.body(), data.person.Cast.class);
			
			return result;
			
		} catch (Exception e) {
			e.printStackTrace();
			
			return null;
		}
	}
	
	// 해당 영화와 비슷한 영화를 가져오는 메서드
	public static Results getMovieSimilarList(String id) {
		try {
			String target = "https://api.themoviedb.org/3/movie/"+ id +"/similar";
			
			Map<String, String> params = new LinkedHashMap<>();
			params.put("api_key", "8069e75d4de0c85b5ade5fc677a893a5");
			params.put("language", "ko-KR");
			params.put("page", "1");
			
			String queryString = QueryStringBuilder.build(params);
			
			URI uri = new URI(target + "?" + queryString);
			
			HttpClient client = HttpClient.newHttpClient();
			HttpRequest req = HttpRequest.newBuilder().uri(uri).GET().build();
			HttpResponse<String> resp = client.send(req, BodyHandlers.ofString());
			
			Gson gson = new Gson();
			
			Results result = gson.fromJson(resp.body(), Results.class);
			
			return result;
			
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	// 영화 검색에 사용하는 메서드
	public static Results getMovieSearchList(String query, String page) {
		try {
			String target = "https://api.themoviedb.org/3/search/movie";
			
			Map<String, String> params = new LinkedHashMap<>();
			params.put("api_key", "8069e75d4de0c85b5ade5fc677a893a5");
			params.put("language", "ko-KR");
			params.put("page", page);
			params.put("query", query);
			
			String queryString = QueryStringBuilder.build(params);
			
			URI uri = new URI(target + "?" + queryString);
			
			HttpClient client = HttpClient.newHttpClient();
			HttpRequest req = HttpRequest.newBuilder().uri(uri).GET().build();
			HttpResponse<String> resp = client.send(req, BodyHandlers.ofString());
			
			Gson gson = new Gson();
			
			Results result = gson.fromJson(resp.body(), Results.class);
			
			return result;
			
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	// 유튜브 예고편을 가져오는 메서드
	public static data.youtube.Results getTrailer(String movieId) {
		try {
			String target = "https://api.themoviedb.org/3/movie/"+ movieId +"/videos";
			
			Map<String, String> params = new LinkedHashMap<>();
			params.put("api_key", "8069e75d4de0c85b5ade5fc677a893a5");
			params.put("language", "ko-KR");
			
			String queryString = QueryStringBuilder.build(params);
			
			URI uri = new URI(target + "?" + queryString);
			
			HttpClient client = HttpClient.newHttpClient();
			HttpRequest req = HttpRequest.newBuilder().uri(uri).GET().build();
			HttpResponse<String> resp = client.send(req, BodyHandlers.ofString());
			
			Gson gson = new Gson();
			
			data.youtube.Results result = gson.fromJson(resp.body(), data.youtube.Results.class);
			
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			
			return null;
		}
	}

	public static void main(String[] args) {
		
		/* 동영상 키값 테스트
		data.youtube.Results result = getTrailer("76600");
		System.out.println(result.getResults()[0].getName());
		System.out.println(result.getResults()[0].getType());
		System.out.println(result.getResults()[0].getKey());
		*/
		
		/* 영화 검색 테스트
		Results result = getMovieSearchList("어벤져스+인피니티+워");
		System.out.println(result.getResults()[0].getId());
		System.out.println(result.getResults()[0].getTitle());
		System.out.println(result.getResults()[0].getOverview());
		System.out.println(result.getResults()[0].getPoster_path());
		System.out.println(result.getResults()[0].getVote_average());
		*/
		
		/* 무비크레딧 테스트
		Cast re = getCreditList("594767");
		System.out.println("ID.. "+ re.getCast()[0].getId());
		System.out.println("이름.. "+ re.getCast()[0].getName());
		System.out.println("직업.. "+ re.getCast()[0].getKnown_for_department());
		System.out.println("프로필사진.. "+ re.getCast()[0].getProfile_path());
		System.out.println("번호.. "+ re.getCast()[0].getOrder());
		*/
		
		/* 무비디테일 테스트
		MovieDetail re = getMoiveDetail("594767");
		System.out.println("ID.. "+ re.getId());
		System.out.println("제목.. "+ re.getTitle());
		System.out.println("슬로건.. "+ re.getTagline());
		System.out.println("내용.. "+ re.getOverview());
		System.out.println("포스터.. "+ re.getPoster_path());
		for(MovieGenre e : re.getGenres()) {
			System.out.println("장르ID.. "+ e.getId());
			System.out.println("장르ID.. "+ e.getName());
		}
		*/

		/* 무비리스트 테스트
		Results re = getMovieList("now_playing", "1");
		
		for(Result r : re.getResults()) {
			System.out.println("ID.. "+ r.getId());
			System.out.println("제목.. "+r.getTitle());
			System.out.println("줄거리.. "+r.getOverview());
			System.out.println("평점.. "+r.getVote_average());
			System.out.println("포스터.. "+r.getPoster_path());
			System.out.println();
		}
		*/
	}
}

