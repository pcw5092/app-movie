package util;

public class Verify {
	
	public static int SignupValidator(String id, String pass, String name) {
		
		// id, pass, name 값이 빈값인지?
		if(id.equals("") || name.equals("") || pass.equals("")) {
			return 1; 
			
		// id, pass가 4글자 이상인지? name이 10글자 초과인지?
		}else if(id.length() < 4 || pass.length() < 4 || name.length() > 10) {
			return 2;
			
		// 영어 아이디를 사용하였는지?
		}else if(!id.matches("^[a-zA-Z0-9]+$")) {
			return 3;
		}
		
		return 0;
	}
}
