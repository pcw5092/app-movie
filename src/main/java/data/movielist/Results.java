package data.movielist;


//평점순영화 리스트
public class Results {
	
	Result[] results;
	int total_results; // 검색결과 갯수
	
	public Result[] getResults() {
		return results;
	}

	public void setResults(Result[] results) {
		this.results = results;
	}

	public int getTotal_results() {
		return total_results;
	}

	public void setTotal_results(int total_results) {
		this.total_results = total_results;
	}
}
