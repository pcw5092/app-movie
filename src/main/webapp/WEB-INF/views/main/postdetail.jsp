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
	<!-- 팝업창 관련 -->
	<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.0.0/jquery.min.js"></script>
	<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/jquery-modal/0.9.1/jquery.modal.min.css" />
	<!-- 팝업창 관련 -->
	
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
		<div class="commu_detail">
			<h2>${post.title}</h2>
			<div class="commu_info">
				<h3>
					<i class="fa-regular fa-user"></i> ${post.name}
				</h3>
				<ul>
					<li><i class="fa-regular fa-calendar"></i>${post.dates}</li>
					<li><i class="fa-regular fa-eye"></i>${post.views}</li>
				</ul>
			</div>
		</div>
		<div class="commu_text">
			<p>${post.contents}</p>
		</div>
			<div class="post_del">
				<a href="/main/postlist">글목록</a>
			</div>
		<c:if test="${logonUser.id eq post.id}">
			<div class="post_del">
				<!-- 팝업창 관련 -->
				<a href="#modify" rel="modal:open"><i class="fa-solid fa-square-pen"></i> 수정</a> 
				<a href="#delete" rel="modal:open"><i class="fa-solid fa-square-xmark"></i> 삭제</a>
				<!-- 팝업창 관련 -->
			</div>
			
			<!-- 팝업창 관련 -->
			<div id="modify" class="modal modalbox">
				<p>해당 글을 <b>수정</b> 하시겠습니까?</p>
				<div>
					<a href="/post/modify?postId=${post.postId }">수정</a>
					<a href=""  rel="modal:close">취소</a>
				</div>
			</div>
			<div id="delete" class="modal modalbox">
				<p>해당 글을 <b>삭제</b> 하시겠습니까?</p>
				<div>
					<a href="/post/delete?postId=${post.postId}">삭제</a>
					<a href=""  rel="modal:close">취소</a>
				</div>
			</div>
			<!-- 팝업창 관련 -->
		</c:if>
	</div>
	<!-- 팝업창 관련 -->
	<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery-modal/0.9.1/jquery.modal.min.js"></script>
	<!-- 팝업창 관련 -->
</body>
</html>