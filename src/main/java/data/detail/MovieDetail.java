package data.detail;

//영화 상세내용
public class MovieDetail {


	String id; // 영화아이디
	String overview; // 줄거리
	String poster_path; // 포스터
	String tagline; // 문구 (슬로건)
	String title; // 제목
	MovieGenre[] genres; // 영화 장르
	String key;
	
	
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getOverview() {
		return overview;
	}
	public void setOverview(String overview) {
		this.overview = overview;
	}
	public String getPoster_path() {
		return poster_path;
	}
	public void setPoster_path(String poster_path) {
		this.poster_path = poster_path;
	}
	public String getTagline() {
		return tagline;
	}
	public void setTagline(String tagline) {
		this.tagline = tagline;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public MovieGenre[] getGenres() {
		return genres;
	}
	public void setGenres(MovieGenre[] genres) {
		this.genres = genres;
	}
	
	


}
