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
    <title>회원 목록</title>
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
            <h1>회원 목록</h1>
            <h2>A subtitle for your page goes here</h2>
        </div>
        <div>
            <table class="pure-table pure-table-horizontal content">
                <thead>
                <tr style="text-align: center">
                    <th>#</th>
                    <th>아이디</th>
                    <th>이름</th>
                    <th>등급</th>
                    <th>가입일</th>
                    <th>강퇴</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${userList}" var="list">
                    <tr style="text-align: center">
                        <td>${list.id}</td>
                        <td><a href="${path}/user/detail.do?name=${list.name}" style="text-decoration: none;color: #3d3d3d;font-weight: bold;">${list.name}</a></td>
                        <td>${list.userName}</td>
                        <td>
                            <c:if test="${list.lev eq 'ADMIN'}"> 관리자 </c:if>
                            <c:if test="${list.lev eq 'EMP'}"> 사원 </c:if>
                            <c:if test="${list.lev eq 'USER'}"> 회원 </c:if>
                        </td>
                        <td>
                            <fmt:parseDate value="${list.regdate }" var="resdate" pattern="yyyy-MM-dd HH:mm:ss"/>
                            <fmt:formatDate value="${resdate }" pattern="yyyy-MM-dd"/>
                        </td>
                        <td><a href="${path}/user/delete.do?id=${list.id}" class="pure-button pure-button-primary">강퇴</a></td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
    </div>
</div>

<script src="${path}/resources/js/ui.js"></script>

</body>

</html>
