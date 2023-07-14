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
<script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.5.1/jquery.js"></script>
	<link href="https://cdn.jsdelivr.net/gh/hung1001/font-awesome-pro-v6@44659d9/css/all.min.css" rel="stylesheet" type="text/css" />
	<link rel="stylesheet" href="/resource/css/initial.css" />
	<link href="https://webfontworld.github.io/SCoreDream/SCoreDream.css" rel="stylesheet" />
	<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/swiper@8/swiper-bundle.min.css" />
	<link rel="stylesheet" href="/resource/css/style.css" >
</head>
<body class="all_wrap">

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
				<li>
					<div class="search_box">
							<form action="/main/search">
								<input type="search" name="search" id="search" placeholder="Search..." list="slist">
								<span class="icon"><i class="fa fa-search"></i></span> 
								<ul class="search_list">
									<li id="ajax_list">
									</li>
								</ul>
							</form>
						</div> 
				</li>
			</ul>
			<ul class="topsearch">
				<li><span>${logonUser.name}님</span><a href="/user/logout" style="color: #C0FFFF;">로그아웃</a></li>
			</ul>
		</div>
	</nav>
	<!-- 탑 메뉴  끝 -->
	
	<!-- 서치 스크립트 -->
	<script type="text/javascript">
		const input = document.getElementById("search");
	
		$('#search').keyup(function() {
			$('.search_list').addClass('active')
		});
		
		$('#search').keyup(function() {
			if(input.value.length == 0){
				$('.search_list').removeClass('active')
			}
		});
		
		$('.all_wrap').click(function() {
			$('.search_list').removeClass('active')
		});
	</script>
	<!-- 서치 스크립트 -->
	
	<!-- 서치 AJAX -->
	<script type="text/javascript">
		const getValue = document.getElementById("search").onkeyup = function(evt) {
			let target = evt.target.value;
			
			const xhr = new XMLHttpRequest();
			xhr.open("get", "/search/ajax?search="+target, true);
			xhr.send();
			
			xhr.onreadystatechange = function() {
				if(xhr.readyState === 4) {
					const json = JSON.parse(xhr.responseText);
					const list = document.getElementById("ajax_list");
					list.innerHTML = "";
					let cnt = 0;
					for(let o of json) {
						console.log(o.poster_path);
						list.innerHTML += "<ul class=\"search_result\">"+
						"<li class=\"result_img\">"+
						"<img src=\"https://image.tmdb.org/t/p/w300/"+ o.poster_path +"\" onerror=\"this.src='/resource/img/no_img.png';\">"+
						 "</li>"+
						"<li><a href=\"/main/detail?movieId="+ o.id +" \" class=\"search_tit\">"+ o.title +"("+ o.release_date.substr(0, 4) +")</a>"+
						"<a class=\"search_stit\">"+ o.overview.substr(0, 30) +"...</a>"+
						"</li>"+
						"</ul>";
						
						cnt++;
						if(cnt == 10) {
							break;
						}
					}
				}
			}
		};
	</script>
	<script type="text/javascript">
	if($thumb['src']) {
         $img_content = '<img src="'.$thumb['src'].'" alt="'.$thumb['alt'].'" >';
     } else {
         $img_content = '<span class="no_image"><img src="'.G5_THEME_IMG_URL.'/noimg.png"></span>';
     }
	
	onerror="this.src='https://s.pstatic.net/static/www/img/uit/2019/sp_search.svg';"
	</script>
	<!-- 서치 AJAX -->

	<!-- 첫번째 슬라이드 시작 -->
	<div class="list_wrap">
		<div class="width">
			<h2 class="title">인기순</h2>
			<div class="swiper firstSwiper">
				<div class="swiper-wrapper">
					<c:forEach items="${popular}" var="pop">
						<div class="swiper-slide list_movie">
							<a href="/main/detail?movieId=${pop.id}"><img src="https://image.tmdb.org/t/p/w300/${pop.poster_path}"></a>
						</div>
					</c:forEach>
				</div>
			</div>
			<div class="swiper-btn">
				<div class="swiper-prev"><i class="fa-solid fa-chevron-left"></i>
				</div>
				<div class="swiper-next"><i class="fa-solid fa-chevron-right"></i>
				</div>
			</div>
		</div>
	</div>
	<!-- 첫번째 슬라이드 끝 -->

	<!-- 두번째 슬라이드 시작 -->
	<div class="list_wrap">
		<div class="width">
			<h2 class="title">평점순</h2>
			<div class="swiper secondSwiper">
				<div class="swiper-wrapper">
					<c:forEach items="${topRate}" var="top">
						<div class="swiper-slide list_movie">
							<a href="/main/detail?movieId=${top.id}"><img src="https://image.tmdb.org/t/p/w300/${top.poster_path}"></a>
						</div>
					</c:forEach>
				</div>
			</div>
			<div class="swiper-btn2">
				<div class="swiper-prev2"><i class="fa-solid fa-chevron-left"></i>
				</div>
				<div class="swiper-next2"><i class="fa-solid fa-chevron-right"></i>
				</div>
			</div>
		</div>
	</div>
	<!-- 두번째 슬라이드 끝 -->

	<!-- 세번째 슬라이드 시작 -->
	<div class="list_wrap">
		<div class="width">
			<h2 class="title">현재상영중</h2>
			<div class="swiper thirdSwiper">
				<div class="swiper-wrapper">
					<c:forEach items="${nowPlaying}" var="now">
						<div class="swiper-slide list_movie">
							<a href="/main/detail?movieId=${now.id}"><img src="https://image.tmdb.org/t/p/w300/${now.poster_path}"></a>
						</div>
					</c:forEach>
				</div>
			</div>
			<div class="swiper-btn2">
				<div class="swiper-prev3"><i class="fa-solid fa-chevron-left"></i>
				</div>
				<div class="swiper-next3"><i class="fa-solid fa-chevron-right"></i>
				</div>
			</div>
		</div>
	</div>
	<!-- 세번째 슬라이드 끝 -->
	
	 <!-- 네번째 슬라이드 시작 -->
	 <c:if test="${similarList != null}">
		<div class="list_wrap">
			<div class="width">
				<h2 class="title"><b>${movieName}</b> 작품과 비슷한 장르 작품을 추천 받아보세요!</h2>
				<div class="swiper fourthSwiper">
					<div class="swiper-wrapper">
						<c:forEach items="${similarList}" var="s">
							<div class="swiper-slide list_movie">
								<a href="/main/detail?movieId=${s.id}"><img src="https://image.tmdb.org/t/p/w300/${s.poster_path}"></a>
							</div>
						</c:forEach>
					</div>
				</div>
				<div class="swiper-btn2">
					<div class="swiper-prev4"><i class="fa-solid fa-chevron-left"></i>
					</div>
					<div class="swiper-next4"><i class="fa-solid fa-chevron-right"></i>
					</div>
				</div>
			</div>
		</div>
	</c:if>
	<!-- 네번째 슬라이드 끝 -->
	
	 <!-- 다섯번째 슬라이드 시작 -->
	 <c:if test="${directorfeat != null}">
		<div class="list_wrap">
			<div class="width">
				<h2 class="title"><b>${directorName}</b> 감독을 좋아하시나요? 이 작품은 어떠세요?</h2>
				<div class="swiper fifthSwiper">
					<div class="swiper-wrapper">
						<c:forEach items="${directorfeat}" var="d">
							<div class="swiper-slide list_movie">
								<a href="/main/detail?movieId=${d.id}"><img src="https://image.tmdb.org/t/p/w300/${d.poster_path}"></a>
							</div>
						</c:forEach>
					</div>
				</div>
				<div class="swiper-btn2">
					<div class="swiper-prev5"><i class="fa-solid fa-chevron-left"></i>
					</div>
					<div class="swiper-next5"><i class="fa-solid fa-chevron-right"></i>
					</div>
				</div>
			</div>
		</div>
	</c:if>
	<!-- 다섯번째 슬라이드 끝 -->
	
	 <!-- 여섯번째 슬라이드 시작 -->
	 <c:if test="${actorfeat != null}">
		<div class="list_wrap">
			<div class="width">
				<h2 class="title"><b>${actorName}</b> 배우를 좋아하시나요? 이 작품은 어떠세요?</h2>
				<div class="swiper sixthSwiper">
					<div class="swiper-wrapper">
						<c:forEach items="${actorfeat}" var="a">
							<div class="swiper-slide list_movie">
								<a href="/main/detail?movieId=${a.id}"><img src="https://image.tmdb.org/t/p/w300/${a.poster_path}"></a>
							</div>
						</c:forEach>
					</div>
				</div>
				<div class="swiper-btn2">
					<div class="swiper-prev6"><i class="fa-solid fa-chevron-left"></i>
					</div>
					<div class="swiper-next6"><i class="fa-solid fa-chevron-right"></i>
					</div>
				</div>
			</div>
		</div>
	</c:if>
	<!-- 여섯번째 슬라이드 끝 -->
	
	<script src="https://cdn.jsdelivr.net/npm/swiper@7/swiper-bundle.min.js"></script>
	<script>
		var swiper = new Swiper(".firstSwiper", {
			slidesPerView : 7,
			spaceBetween : 20,
			loop : true,
			navigation : {
				nextEl : ".swiper-next",
				prevEl : ".swiper-prev",
			},
		});
	</script>
	<script>
		var swiper = new Swiper(".secondSwiper", {
			slidesPerView : 7,
			spaceBetween : 20,
			loop : true,
			navigation : {
				nextEl : ".swiper-next2",
				prevEl : ".swiper-prev2",
			},
		});
	</script>
	<script>
		var swiper = new Swiper(".thirdSwiper", {
			slidesPerView : 7,
			spaceBetween : 20,
			loop : true,
			navigation : {
				nextEl : ".swiper-next3",
				prevEl : ".swiper-prev3",
			},
		});
	</script>
	<script>
		var swiper = new Swiper(".fourthSwiper", {
			slidesPerView : 7,
			spaceBetween : 20,
			loop : true,
			navigation : {
				nextEl : ".swiper-next4",
				prevEl : ".swiper-prev4",
			},
		});
	</script>
	<script>
		$(window).on('load', function() {
			slider();
		})

		function slider() {
			let swiper = undefined;
			let slideNum = $('.slider .swiper-slide').length //슬라이드 총 개수
			let slideInx = 0; //현재 슬라이드 index

			//디바이스 체크
			let oldWChk = window.innerWidth > 1180 ? 'pc' : 'mo';
			sliderAct();
			$(window).on('resize', function() {
				let newWChk = window.innerWidth > 1180 ? 'pc' : 'mo';
				if (newWChk != oldWChk) {
					oldWChk = newWChk;
					sliderAct();
				}
			})

			//슬라이드 실행
			function sliderAct() {
				//슬라이드 초기화 
				if (swiper != undefined) {
					swiper.destroy();
					swiper = undefined;
				}

				//slidesPerView 옵션 설정
				let viewNum = oldWChk == 'pc' ? 7 : 2.3;
				//loop 옵션 체크
				let loopChk = slideNum > viewNum;

				swiper = new Swiper(".fifthSwiper", {
					slidesPerView : viewNum,
					initialSlide : slideInx,
					spaceBetween : 10,
					loop : loopChk,
					navigation : {
						nextEl : ".swiper-next5",
						prevEl : ".swiper-prev5",
					},

					on : {
						activeIndexChange : function() {
							slideInx = this.realIndex; //현재 슬라이드 index 갱신
						}
					},
				});
			}
		}
	</script>
	<script>
		var swiper = new Swiper(".sixthSwiper", {
			slidesPerView : 7,
			spaceBetween : 20,
			loop : true,
			navigation : {
				nextEl : ".swiper-next6",
				prevEl : ".swiper-prev6",
			},
		});
	</script>

</body>

</html>