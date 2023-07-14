<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>FLIXFIX</title>
	<link href="https://cdn.jsdelivr.net/gh/hung1001/font-awesome-pro-v6@44659d9/css/all.min.css" rel="stylesheet" type="text/css" />
	<link rel="stylesheet" href="/resource/css/initial.css" />
	<link href="https://webfontworld.github.io/SCoreDream/SCoreDream.css" rel="stylesheet" />
	<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/swiper@8/swiper-bundle.min.css" />
	<link rel="stylesheet" href="/resource/css/style.css" >
</head>
<body class="login_bg">
    <!-- 탑 메뉴 -->
    <nav class="header">
      <div class="topmenu">
        <ul class="toplist">
          <li class="toplogo"><a href="/index"><i class="fa-duotone fa-camera-movie" style="--fa-primary-color: #ffffff; --fa-secondary-color: #ff0000; --fa-secondary-opacity: 1;">
              </i><span style="color: #fff">FLIXFIX</span></a></li>
        </ul>
        <ul class="topsearch toplogin">
          <li><a href="/user/login">로그인</a></li>
          <li><a href="/user/join">회원가입</a></li>
        </ul>
      </div>
    </nav>
    <!-- 탑 메뉴  끝 -->
    
    <div class="res_wrap">
      <div class="res_dox">
        <h2>회원가입</h2>
        <form class="login_form" action="/user/join-task" method="post">
          <p>닉네임</p>
          <input type="text" name="name" placeholder="닉네임(필수)">
          <p>아이디</p>
          <input type="text" name="id" placeholder="아이디(필수)">
          <c:if test="${param.error eq 3}">
          	<p class="error_text">아이디는 영어/숫자만 가능합니다.</p>
          </c:if>
          <c:if test="${param.error eq 4}">
          	<p class="error_text">이미 사용 중인 아이디입니다.</p>
          </c:if>
          <p>비밀번호</p>
          <input type="password" name="pass" placeholder="비밀번호(필수)">
          <c:if test="${param.error eq 1}">
          	<p class="error_text">정보를 빠짐없이 입력해 주세요.</p>
          </c:if>
           <c:if test="${param.error eq 2}">
          	<p class="error_text">아이디/비밀번호는 4글자 이상<br>닉네임은 9글자 이하만 가능합니다.</p>
          </c:if>
          
          <button type="submit" name="button">회원가입</button>
        </form>

        <div class="login_noid">
          <span>아이디가 있으신가요?</span> <a href="/user/login"><i class="fa-solid fa-arrow-up-right-from-square"></i>로그인</a>
        </div>
      </div>
    </div>

</body>
</html>