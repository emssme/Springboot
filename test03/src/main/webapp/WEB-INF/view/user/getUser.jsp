<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<c:set var="path" value="${pageContext.request.contextPath }" />
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>회원 정보</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/purecss@3.0.0/build/pure-min.css" integrity="sha384-X38yfunGUhNzHpBaEBsWLO+A0HDYOQi8ufWDkZ0k9e0eXz/tH3II7uKZ9msv++Ls" crossorigin="anonymous">
    <link rel="stylesheet" href="${path}/resources/css/pure-min.css">
    <link rel="stylesheet" href="${path}/resources/css/styles.css">
</head>
<body>

<div id="layout">
    <!-- Menu toggle -->
    <a href="#menu" id="menuLink" class="menu-link">
        <!-- Hamburger icon -->
        <span></span>
    </a>

    <div id="menu">
        <div class="pure-menu">
            <a class="pure-menu-heading" href="#company">Company</a>

            <ul class="pure-menu-list">
                <li class="pure-menu-item"><a href="${path}/user/list.do" class="pure-menu-link">회원 목록</a></li>
                <li class="pure-menu-item"><a href="${path}/user/join.do" class="pure-menu-link">회원 가입</a></li>
                <li class="pure-menu-item"><a href="${path}/user/login.do" class="pure-menu-link">로그인</a></li>

                <li class="pure-menu-item menu-item-divided pure-menu-selected">
                    <a href="#" class="pure-menu-link">Services</a>
                </li>

                <li class="pure-menu-item"><a href="#contact" class="pure-menu-link">Contact</a></li>
            </ul>
        </div>
    </div>

    <div id="main">
        <div class="header">
            <h1>회원 정보</h1>
            <h2>A subtitle for your page goes here</h2>
        </div>

        <div class="content">
            <h2 class="content-subhead">아이디</h2>
            <p>${detail.name}</p>
            <h2 class="content-subhead">이름</h2>
            <p>${detail.userName}</p>
            <h2 class="content-subhead">이메일</h2>
            <p>${detail.email}</p>
            <h2 class="content-subhead">주소</h2>
            <p>${detail.address}</p>
            <h2 class="content-subhead">등급</h2>
            <h2 class="content-subhead">전화번호</h2>
            <p>${detail.tel}</p>
            <p>
                <c:if test="${detail.lev eq 'ADMIN'}"> 관리자 </c:if>
                <c:if test="${detail.lev eq 'EMP'}"> 사원 </c:if>
                <c:if test="${detail.lev eq 'USER'}"> 회원 </c:if>
            </p>
            <div class="pure-g">
                <div class="pure-u-1-4">
                    <img class="pure-img-responsive" src="http://farm3.staticflickr.com/2875/9069037713_1752f5daeb.jpg" alt="Peyto Lake">
                </div>
                <div class="pure-u-1-4">
                    <img class="pure-img-responsive" src="http://farm3.staticflickr.com/2813/9069585985_80da8db54f.jpg" alt="Train">
                </div>
                <div class="pure-u-1-4">
                    <img class="pure-img-responsive" src="http://farm6.staticflickr.com/5456/9121446012_c1640e42d0.jpg" alt="T-Shirt Store">
                </div>
                <div class="pure-u-1-4">
                    <img class="pure-img-responsive" src="http://farm8.staticflickr.com/7357/9086701425_fda3024927.jpg" alt="Mountain">
                </div>
            </div>
        </div>
    </div>
</div>

<script src="${path}/resources/js/ui.js"></script>

</body>

</html>
