package data.credit;


// 출연진(감독, 배우)
public class Cast {

	Casts[] cast;		// 캐스팅 객체들을 담는 배열
	Casts[] crew;
	
	public Casts[] getCrew() {
		return crew;
	}

	public void setCrew(Casts[] crew) {
		this.crew = crew;
	}

	public Casts[] getCast() {
		return cast;
	}

	public void setCast(Casts[] cast) {
		this.cast = cast;
	}
}
