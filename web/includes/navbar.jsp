<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<link href="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
<script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<%@include file="/includes/language.jsp"%>
<link rel="stylesheet" href="css/navbar.css"/>
<script src="script/language.js"></script>

<div class="navbar navbar-expand-md navbar-dark bg-dark mb-4" style="margin-bottom: 0px !important;" role="navigation">
    <a class="navbar-brand" href="home"><fmt:message key="navbar.company" bundle="${langSet}"/></a>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarCollapse" aria-controls="navbarCollapse" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" id="navbarCollapse">
        <ul class="navbar-nav mr-auto">
            <li class="nav-item active">
                <a class="nav-link" href="home"><fmt:message key="navbar.home" bundle="${langSet}"/></a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="#"><fmt:message key="navbar.about" bundle="${langSet}"/></a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="#" target="_blank"><fmt:message key="navbar.github" bundle="${langSet}"/></a>
            </li>
            <li class="nav-item dropdown">
                <a class="nav-link dropdown-toggle" id="dropdown1" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false"><fmt:message key="navbar.categories" bundle="${langSet}"/></a>
                <ul class="dropdown-menu" aria-labelledby="dropdown1">
                    <c:forEach items="${listC}" var="o">
                        <li class="dropdown-item"><a href="category?cid=${o.cid}">${o.cname}</a></li>
                    </c:forEach>
                </ul>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="#" target="_blank"><fmt:message key="navbar.contact" bundle="${langSet}"/></a>
            </li>
            <li class="nav-item dropdown">
                <a class="nav-link dropdown-toggle" id="dropdown2-1" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false"><fmt:message key="navbar.language" bundle="${langSet}"/></a>
                <ul class="dropdown-menu" aria-labelledby="dropdown2-1">
                    <li class="dropdown-item"><a id="englishLink" href="#" onclick="updateLanguage('en_US')"><fmt:message key="navbar.english" bundle="${langSet}"/></a></li>
                    <li class="dropdown-item"><a id="vietnameseLink" href="#" onclick="updateLanguage('vi_VN')"><fmt:message key="navbar.vietnamese" bundle="${langSet}"/></a></li>
                </ul>
            </li>
        </ul>
        <form class="form-inline mt-2 mt-md-0" action="search" method="post">
            <input style="margin: 5px 5px 0 0 !important" class="form-control mr-sm-2 navbar-search" type="text" name="txt" value="${param.txt}" placeholder="<fmt:message key='navbar.search' bundle='${langSet}'/>" aria-label="Search">
            <button class="btn btn-outline-success my-2 my-sm-0" type="submit"><fmt:message key="navbar.search" bundle="${langSet}"/></button>
            <a class="btn btn-success btn-sm ml-3" href="cart">
                <i class="fa fa-shopping-cart"></i> <fmt:message key="navbar.cart" bundle="${langSet}"/>
                <span class="badge badge-light">${cookie.sizeC.value}</span>
            </a>
            <ul class="navbar-nav mr-auto">
                <c:choose>
                    <c:when test="${sessionScope.acc == null}">
                        <li class="nav-item">
                            <a class="nav-link" href="login.jsp"><fmt:message key="navbar.login" bundle="${langSet}"/></a>
                        </li>
                    </c:when>
                    <c:otherwise>
                        <li class="nav-item dropdown">
                            <a class="nav-link dropdown-toggle" id="dropdown2" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false"><fmt:message key="navbar.account" bundle="${langSet}"/></a>
                            <ul class="dropdown-menu" aria-labelledby="dropdown1"> 
                                <c:if test="${sessionScope.acc.isAdmin == 1}">
                                    <li class="dropdown-item"><a href="manager"><fmt:message key="navbar.manageProduct" bundle="${langSet}"/></a></li>
                                </c:if>
                                <li class="dropdown-item" href="#"><a href="orders"><fmt:message key="navbar.orders" bundle="${langSet}"/></a></li>
                                <li class="dropdown-item" href="#"><a href="logout"><fmt:message key="navbar.logout" bundle="${langSet}"/></a></li>
                            </ul>
                        </li>
                    </c:otherwise>
                </c:choose>
            </ul>
        </form>
    </div>
</div>

<script>
    document.addEventListener("DOMContentLoaded", function() {
        var prevScrollpos = window.pageYOffset;
        window.addEventListener("scroll", function() {
            var currentScrollPos = window.pageYOffset;
            var navbar = document.querySelector(".navbar");
            if (prevScrollpos > currentScrollPos || currentScrollPos === 0) {
                navbar.classList.remove("hidden");
            } else {
                navbar.classList.add("hidden");
            }
            prevScrollpos = currentScrollPos;
        });
    });
</script>
