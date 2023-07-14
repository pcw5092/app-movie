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

	<!-- 포스터 부분 -->
	<div class="detail_wrap">
		<div class="width detail_top">
			<div class="detail_poster">
				<img src="https://image.tmdb.org/t/p/w400/${detail.poster_path}">
				<!-- 포스터 부분 -->
				
				<!-- 한줄평 부분 -->
				<div class="detail_star">
					<h4>한줄평</h4>
					<form class="" action="/comment/create" method="post">
						<input type="hidden" name="movieId" value="${param.movieId}">
						<input type="text" name="comment" placeholder="한줄평을 작성해주세요.">
						<button type="submit" name="button">
							<i class="fa-regular fa-paper-plane"></i> 작성
						</button>
					</form>
					<c:if test="${param.error eq 1}">
						<p class="error_text">한 줄 평을 입력해 주세요.</p>
					</c:if>
					<c:forEach items="${comments}" var="com">
					<div>
						<ul class="detail_comment">
							<li><i class="fa-solid fa-message-smile"></i>${com.name}</li>
							<c:if test="${logonUser.id eq com.id}">
								<li><a href="/comment/delete?commentId=${com.commentId}&movieId=${param.movieId}">
								<i class="fa-solid fa-xmark"></i></a></li>
							</c:if>
						</ul>
						<span>${com.comments}</span>
					</div>
					</c:forEach>
				</div>
				<!-- 한줄평 부분 -->
			
				<!-- 페이징 처리 부분 -->
				<c:set var="currentPage" value="${empty param.page ? 1: param.page }"/>
     			<div class="page_nav">

					<%-- prve 처리 --%>
					<c:choose>
						<c:when test="${existPrev }">
							<a href="/main/detail?movieId=${param.movieId}&page=${start - 1}" class="prev"><i class="fa-solid fa-angle-left"></i></a>
						</c:when>
						<c:otherwise>
							<a class="prev">←</a>
						</c:otherwise>
					</c:choose>
					<%-- prve 처리 --%>
		
					<%-- 페이지 넘버 처리 --%>
					<c:forEach begin="${start}" end="${last}" var="idx">
						<c:choose>
							<c:when test="${idx eq currentPage}">
								<a class="active">${idx}</a>
							</c:when>
							<c:otherwise>
								<a href="/main/detail?movieId=${param.movieId}&page=${idx}">${idx}</a>
							</c:otherwise>
						</c:choose>
					</c:forEach>
					<%-- 페이지 넘버 처리 --%>
					
					<%-- next 처리 --%>
					<c:choose>
						<c:when test="${existNext }">
							<a href="/main/detail?movieId=${param.movieId}&page=${last + 1}" class="next"><i class="fa-solid fa-angle-right"></i></a>
						</c:when>
						<c:otherwise>
							<a class="next">→</a>
						</c:otherwise>
					</c:choose>
					<%-- next 처리 --%> 
					
      			</div>
      			<!-- 페이징 처리 부분 -->
			</div>

			<!-- 영화정보 -->
			<div class="detail_text">
				<h2>${detail.title}
					<c:choose>
						<c:when test="${movielike}">
							<a href="/detail/unlike?movieId=${param.movieId}&position=detail" class="active">
							✔</a>
						</c:when> 
						<c:otherwise>
							<a href="/detail/movielike?movieId=${param.movieId}&posterURL=${detail.poster_path}&movieName=${detail.title}" >
							➕ </a>
						</c:otherwise>
					</c:choose>
				</h2>
				<h3>${detail.tagline }</h3>
				<p>${detail.overview }</p>
				<!-- 영화정보 -->

				<!-- 감독 정보 -->
				<div class="detail_actor">
					<h4>감독</h4>
					<ul>
						<li>
						<c:forEach items="${directors}" var="d">
						<c:choose>
							<c:when test="${d.like}">
								<a href="/detail/director-unlike?movieId=${param.movieId}&directorId=${d.id}&position=detail" class="detail_like">
								💗</a>
							</c:when>
						
							<c:otherwise>
								<a href="/detail/director-like?movieId=${param.movieId}&directorId=${d.id}&posterURL=${d.profile_path}&directorName=${d.name}" class="detail_like">
								🖤<a>
							</c:otherwise>
						</c:choose>
						
						<!-- 감독 이미지 -->
						<c:choose>
							<c:when test="${d.profile_path eq null}">
								<p class="no_img">이미지 정보가 없습니다.</p>
								<span>${d.name}</span>
							</c:when>
							<c:otherwise>
								<img src="https://image.tmdb.org/t/p/w200/${d.profile_path}">
								<span>${d.name}</span>
							</c:otherwise>
						</c:choose>
					</c:forEach>
					</li>
					</ul>
					<!-- 감독 이미지 -->
				</div>
				<!-- 감독 정보 -->

				<!-- 배우 정보 -->
				<div class="detail_actor">
					<h4>배우</h4>
					<!-- 배우 이미지 -->
					<ul>
						<c:forEach items="${actors}" var="a">
							<li>
								<c:choose>
									<c:when test="${a.like}">
										<a href="/detail/actor-unlike?movieId=${param.movieId}&actorId=${a.id}&position=detail" class="detail_like">
										💗</a>
									</c:when> 
									<c:otherwise>
										<a href="/detail/actor-like?movieId=${param.movieId}&actorId=${a.id}&posterURL=${a.profile_path}&actorName=${a.name}" class="detail_like">
										🖤</a>
									</c:otherwise>
								</c:choose>
								
								<c:choose>
										<c:when test="${a.profile_path eq null}">
											<p class="no_img">이미지 정보가 없습니다.</p>
											<span>${a.name}</span>
										</c:when>
										<c:otherwise>
											<img src="https://image.tmdb.org/t/p/w300/${a.profile_path}">
											<span>${a.name}</span>
										</c:otherwise>
								</c:choose>
							</li>
						</c:forEach>
					</ul>
					<!-- 배우 이미지 -->
				</div>
				<!-- 배우 정보 -->
				
				<!-- 유튜브 정보 -->
				<div class="de_youtube">
					<c:if test="${detail.key != null}">
					<iframe width="980" height="550"
						src="https://www.youtube.com/embed/${detail.key}"
						title="YouTube video player" frameborder="0"
						allow="accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture; web-share"
						allowfullscreen></iframe>
					</c:if>
					<c:if test="${detail.key eq null}">
						<p>예고편 정보가 없습니다!</p>
					</c:if>
				</div>
				<!-- 유튜브 정보 -->
			</div>
		</div>
	</div>
</body>
</html>