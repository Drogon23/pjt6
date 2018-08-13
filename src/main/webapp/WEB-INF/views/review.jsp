<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="utf-8">
    <meta name="description" content="네이버 예약, 네이버 예약이 연동된 곳 어디서나 바로 예약하고, 네이버 예약 홈(나의예약)에서 모두 관리할 수 있습니다.">
    <meta name="viewport" content="width=device-width,initial-scale=1,maximum-scale=1,minimum-scale=1,user-scalable=no">
    <title>네이버 예약</title>
    <link href="/css/style.css" rel="stylesheet">
    <script src="https://cdnjs.cloudflare.com/ajax/libs/handlebars.js/4.0.11/handlebars.min.js"></script>
    <style>
        .container_visual {
            height: 414px;
        }
    </style>
</head>
<body>
	<div id="container">
		<div class="header">
			<header class="header_review">
				<a href="/detail/${displayInfoId}" class="item_book"><h1 class="back">←</h1></a>
				<p class="prdt_title">${product.description}</p>
			</header>
		</div>
		<hr>
		<div class="review_bar"></div>	
		<div class="ct main">
			<div>
				<input type="hidden" id=productId value = "${product.id}">
				<input type="hidden" id=start value = 0>
                <div class="section_review_list">
                    <div class="review_box">
                        <h3 class="title_h3">예매자 한줄평</h3>
                        <div class="short_review_area">
                            <div class="grade_area">
                                <!-- [D] 별점 graph_value는 퍼센트 환산하여 width 값을 넣어줌 -->
                                <span class="graph_mask"> <em class="graph_value" id = "graph_value" style="width: 0%;"></em> </span>
                                <strong class="text_value"> <span id = "avgScore"></span> <em class="total">5.0</em> </strong>
                                <span class="join_count"><em class="green" id = "commentCounts">0건</em> 등록</span>
                            </div>
                            <ul class="list_short_review">
         		                
                            </ul>
                        </div>
                        <p class="guide"> <i class="spr_book2 ico_bell"></i> <span>네이버 예약을 통해 실제 방문한 이용자가 남긴 평가입니다.</span> </p>
                    </div>
                </div>
        	</div>
		</div>
	</div>
               
</body>
<script type="text/javascript" src="/js/review.js"></script>
<script type="text/javascript" src="/js/utilities.js"></script>
<script type="rv-template" id ="comment">
	{{#each commentsInfo}}
	<li class="list_item">
    <div>
		{{#if reservationUserComment.reservationUserCommentImage}}
		<div class="review_area">
        	<div class="thumb_area">
            	<a href="#" class="thumb" title="이미지 크게 보기"> 
					<img width="90" height="90" class="img_vertical_top" src="/{{reservationUserComment.reservationUserCommentImage.saveFileName}}" alt="리뷰이미지"> 
				</a> 
				<span class="img_count" style="display:none;">1</span>
			</div>
		{{else}}
		<div class="review_area no_img">
		{{/if}}
            <h4 class="resoc_name">${product.description}</h4>
            <p class="review">{{reservationUserComment.comment}}</p>
        </div>
        <div class="info_area">
        	<div class="review_info"> 
				<span class="grade">{{reservationUserComment.score}}.0</span> 
				<span class="name">{{hideEmail reservationInfo.reservationEmail}}</span> 
				<span class="date">{{dateFormat reservationInfo.reservationDate}}방문</span> 
			</div>
		</div>
    </div>
	</li>
	{{/each}}
</script>
</html>