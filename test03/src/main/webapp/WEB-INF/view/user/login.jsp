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
    <title>이메일 로그인</title>
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
            <h1>이메일 로그인</h1>
            <h2>A subtitle for your page goes here</h2>
        </div>

        <div class="content">
            <form class="pure-form pure-form-stacked" action="${path}/user/login.do" method="post">
                <fieldset>
                    <legend>로그인</legend>
                    <select>
                        <option>email</option>
                        <option>id</option>
                    </select>
                    <label for="stacked-email">Email</label>
                    <input type="email" id="stacked-email" placeholder="이메일을 입력해주세요" />
                    <label for="stacked-password">비밀번호</label>
                    <input type="password" id="stacked-password" placeholder="비밀번호를 입력해주세요" />
                    <input type="submit" class="pure-button pure-button-primary" value="로그인">
                </fieldset>
            </form>
        </div>
    </div>
</div>

<script src="${path}/resources/js/ui.js"></script>

</body>

</html>
