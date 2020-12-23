<%@page import="model.MemberDTO"%>
<%@page import="model.MemberDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%
//폼값 받기
String id = request.getParameter("user_id");
String pw = request.getParameter("user_pw");

//web.xml에 저장된 컨텍스트 초기화 파라미터 가져옴
String drv = application.getInitParameter("JDBCDriver");
String url = application.getInitParameter("ConnectionURL");

//DAO객체 생성 및 디비 연결
MemberDAO dao = new MemberDAO(drv, url);

//폼값으로 받은 아이디, 패스워드를 통해 로그인 처리 함수 호출
MemberDTO memberDTO = dao.getMemberDTO(id, pw);

//if(id.equals(memberDTO.getId()) && pw.equals(memberDTO.getPass())){
if(memberDTO.getId() != null){
	//로그인 성공시 세션영역에 속성에 아래 속성을 저장한다.
	session.setAttribute("USER_ID", memberDTO.getId());
	session.setAttribute("USER_PW", memberDTO.getPass());
	session.setAttribute("USER_NAME", memberDTO.getName());
	
	response.sendRedirect("Login.jsp");
}
else{
	request.setAttribute("ERROR_MSG","넌 회원이 아니시군");
	request.getRequestDispatcher("Login.jsp").forward(request, response);
}
%>
