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

	<!-- 영화 리스트 -->
	<div class="list_wrap">
		<div class="width">
			<h2 class="title">관심영화</h2>
			<div class="list_page">
				<c:if test="${empty movieList}">
					<span style="color: white; font-size: 24px;">좋아요 정보가 없습니다.</span>
				</c:if>
				<c:forEach items="${movieList}" var="list">
					<div class="list_box">
						<a href="/detail/unlike?movieId=${list.movieId}&position=mylist" class="detail_like"><i class="fa-solid fa-heart"></i></a>
						<a href="/main/detail?movieId=${list.movieId}" class="list_img"><img src="https://image.tmdb.org/t/p/w300/${list.posterURL}"></a>
					</div>
				</c:forEach>
			</div>
		</div>
	</div>
	<!-- 영화 리스트 -->
	
	<!-- 배우 리스트 -->
	<div class="list_wrap">
		<div class="width">
			<h2 class="title">관심배우</h2>
			<div class="list_page">
				<c:if test="${empty actorList}">
					<span style="color: white; font-size: 24px;">좋아요 정보가 없습니다.</span>
				</c:if>
				<c:forEach items="${actorList}" var="list">
					<div class="list_box">
						<a href="/detail/actor-unlike?actorId=${list.actorId}&position=mylist" class="detail_like"><i class="fa-solid fa-heart"></i></a>
						<c:choose>
							<c:when test="${list.posterURL eq null}">
								<p class="no_img">이미지 정보가 없습니다.</p>
							</c:when>
							<c:otherwise>
								<img src="https://image.tmdb.org/t/p/w300/${list.posterURL}">
							</c:otherwise>
						</c:choose>
					</div>
				</c:forEach>
			</div>
		</div>
	</div>
	<!-- 배우 리스트 -->
	
	<!-- 감독 리스트 -->
	<div class="list_wrap">
		<div class="width">
			<h2 class="title">관심감독</h2>
			<div class="list_page">
				<c:if test="${empty directorList}">
					<span style="color: white; font-size: 24px;">좋아요 정보가 없습니다.</span>
				</c:if>
				<c:forEach items="${directorList}" var="list">
					<div class="list_box">
						<a href="/detail/director-unlike?directorId=${list.directorId}&position=mylist" class="detail_like"><i class="fa-solid fa-heart"></i></a>
						<c:choose>
							<c:when test="${list.posterURL eq null}">
								<p class="no_img">이미지 정보가 없습니다.</p>
							</c:when>
							<c:otherwise>
								<img src="https://image.tmdb.org/t/p/w300/${list.posterURL}">
							</c:otherwise>
						</c:choose>
					</div>
				</c:forEach>
			</div>
		</div>
	</div>
	<!-- 감독 리스트 -->
	
	
	
	
	
	
	
	
	
	
	
</body>
</html>