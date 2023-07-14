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
<body>

	<!-- 탑 메뉴 -->
	<nav class="header">
		<div class="topmenu">
			<ul class="toplist">
				<li class="toplogo">
				<a href="/main/list">
				<i class="fa-duotone fa-camera-movie" style="--fa-primary-color: #ffffff; --fa-secondary-color: #ff0000; --fa-secondary-opacity: 1;"></i>
					<span style="color: #fff">FLIXFIX</span>
					</a>
					</li>
				<li><a href="/main/postlist">커뮤니티</a></li>
				<li><a href="/main/mylist">관심목록</a></li>
			</ul>
			<ul class="topsearch">
				<li><span>${logonUser.name}님</span><a href="/user/logout" style="color: #C0FFFF;">로그아웃</a></li>
			</ul>
		</div>
	</nav>
	<!-- 탑 메뉴  끝 -->

	<div class="commu_wrap">
		<h2>커뮤니티 글 수정하기</h2>
		<form class="commu_write" action="/post/modify-task" method="post">
			<input type="hidden" name="postId" value="${post.postId}">
			<h2>제목</h2>
			<input type="text" name="title" value="${post.title}" placeholder="제목을 입력해주세요.">
			<h2>내용</h2>
			<textarea name="contents" rows="8" cols="80" placeholder="내용을 입력해주세요.">${post.contents }</textarea>
			<c:if test="${param.error eq 1}">
				<p class="error_text">제목과 내용을 입력해주세요.</p>
			</c:if>
			<div class="commu_btn">
				<a href="/post/detail?postId=${post.postId}"><button type="button" name="button">취소</button></a>
				<button type="submit" name="button">수정</button>
			</div>
		</form>
	</div>

</body>
</html>