<%@page import="java.util.Map"%>
<%@page import="model.MemberDTO"%>
<%@page import="model.MemberDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<% 
//폼값 받기
String id = request.getParameter("user_id");
String pw = request.getParameter("user_pw");

//web.xml에 저장된 컨텍스트 초기화 파라미터 가져옴
String drv = application.getInitParameter("MariaJDBCDriver");
String url = application.getInitParameter("MariaConnectURL");
String mid = application.getInitParameter("MariaUser");
String mpw = application.getInitParameter("MariaPass");

//DAO객체 생성 및 디비 연결
MemberDAO dao = new MemberDAO(drv, url, mid, mpw);

//폼값으로 받은 아이디, 패스워드를 통해 로그인 처리 함수 호출
//방법3 : Map컬렉션에 저장된 회원정보를 통해 세션 영역에 저장
Map<String, String> memberMap = dao.getMemberMap(id, pw);

//if(id.equals(memberDTO.getId()) && pw.equals(memberDTO.getPass())){
if(memberMap.get("id") != null){
	//로그인 성공시 세션영역에 속성에 아래 속성을 저장한다.
	session.setAttribute("USER_ID", memberMap.get("id"));
	session.setAttribute("USER_PW", memberMap.get("pass"));
	session.setAttribute("USER_NAME", memberMap.get("name"));
	
	response.sendRedirect("Login.jsp");
}
else{
	request.setAttribute("ERROR_MSG","넌 회원이 아니시군");
	request.getRequestDispatcher("Login.jsp").forward(request, response);
}
%>
