package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Map;

public class MemberDAO {
	Connection con;
	PreparedStatement psmt;
	ResultSet rs;

	// 기본생성자를 통한 DB연결
	public MemberDAO() {

		String driver = "org.mariadb.jdbc.Driver";
		String url = "jdbc:mariadb://127.0.0.1:3306/kosmo_db";

		try {
			// prepare객체를 통해 쿼리문을 전송한다.
			// 생성자에서 연결정보를 저장한 커넥션 객체를 사용함
			Class.forName(driver);
			String id = "kosmo_user";
			String pw = "12345";
			// DB에 연결된 정보를 맴버변수에 저장
			con = DriverManager.getConnection(url, id, pw);
			System.out.println("DB연결 성공(디폴트생성자)");
		} catch (Exception e) {
			System.out.println("DB연결 실패(디폴트생성자)");
			e.printStackTrace();
		}
	}

	// JSP에서 커텍스트 초기화 파라미터를 인자로 전달하여 DB연결
	public MemberDAO(String driver, String url, String id, String pw) {

		try {
			// prepare객체를 통해 쿼리문을 전송한다.
			// 생성자에서 연결정보를 저장한 커넥션 객체를 사용함
			Class.forName(driver);
			// DB에 연결된 정보를 맴버변수에 저장
			con = DriverManager.getConnection(url, id, pw);
			System.out.println("DB연결 성공(디폴트생성자)");
		} catch (Exception e) {
			System.out.println("DB연결 실패(디폴트생성자)");
			e.printStackTrace();
		}
	}

	// 그룹함수 count()를 통해 회원의 존재유무만 판단한다.
	public boolean isMember(String id, String pass) {
		// 쿼리문 작성
		String sql = "SELECT COUNT(*) FROM member  WHERE id=? AND pass=?";
		int isMember = 0;
		boolean isFlag = false;

		try {
			// prepare객체를 통해 쿼리문을 전송한다.
			psmt = con.prepareStatement(sql);
			// 쿼리문의 인파라미터 설정(DB의 인덱스는 1부터 시작)
			psmt.setString(1, id);
			psmt.setString(2, pass);
			// 쿼리문 실행후 결과는 ResultSet객체를 통해 반환받음
			rs = psmt.executeQuery();
			// 실행결과를 가져오기 위해 next()를 호출한다.
			rs.next();
			// select절의 첫번쨰 결과값을 얻어오기 위한 getInt()사용
			isMember = rs.getInt(1);
			System.out.println("affected : " + isMember);
			if (isMember == 0) { // 회원이 아닌경우
				isFlag = false;
			} else { // 회원인 경우(해당 아이디, 패스워드가 일치함)
				isFlag = true;
			}
		} catch (Exception e) {
			// 예외가 발생한다면 확인이 불가능함으로 무조건 false를 반환한다.
			isFlag = false;
			e.printStackTrace();
		}
		return isFlag;
	}

	// 로그인 방법3 : DTO객체 대신 Map컬렉션에 회원정보를 저장후 반환한다.
	public Map<String, String> getMemberMap(String id, String pwd) {

		// 회원정보를 저장할 Map컬렉션 생성
		Map<String, String> maps = new HashMap<String, String>();

		String query = "SELECT id, pass, name FROM  " + "  member WHERE id=? AND pass=?";

		try {
			psmt = con.prepareStatement(query);
			psmt.setString(1, id);
			psmt.setString(2, pwd);
			rs = psmt.executeQuery();

			// 회원정보가 있다면 put()을 통해 정보를 저장한다.
			if (rs.next()) {
				// 결과가 있다면 DTO객체에 정보 저장
				maps.put("id", rs.getString(1));
				maps.put("pass", rs.getString("pass"));
				maps.put("name", rs.getString("name"));
			} else {
				System.out.println("결과 셋이 없습니다.");
			}
		} catch (Exception e) {
			System.out.println("getMemberDTO오류");
			e.printStackTrace();
		}
		return maps;
	}

	public static void main(String[] args) {
		new MemberDAO();
	}
}
