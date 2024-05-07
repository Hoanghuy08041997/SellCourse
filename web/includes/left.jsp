<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@include file="/includes/language.jsp" %>
<link rel="stylesheet" href="../css/left.css"/>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<div class="col-sm-3">
    <div class="card bg-light mb-3">
        <div class="card-header bg-primary text-white text-uppercase"><i class="fa fa-list"> <fmt:message key="left.categories" bundle="${langSet}"/></i></div>
        <ul class="list-group category_block">
            <c:forEach items="${listC}" var="o">
                <li class="list-group-item text-white ${tag == o.cid? "active":""}"><a href="category?cid=${o.cid}">${o.cname}</a></li>
            </c:forEach>
        </ul>
    </div>
    <div class="card bg-light mb-3">
        <div class="card-header bg-success text-white text-uppercase"><fmt:message key="left.most_order_course" bundle="${langSet}"/></div>
        <div class="card-body">
            <img class="img-fluid" src="${mostOrderP.image}" />
            <h5 class="card-title">${mostOrderP.name}</h5>
            <p class="card-text">${mostOrderP.description}</p>
            <p class="bloc_left_price">${mostOrderP.price} $</p>
        </div>
    </div>
    <div class="card bg-light mb-3">
        <div class="card-header bg-success text-white text-uppercase"><fmt:message key="left.lastest_course" bundle="${langSet}"/></div>
        <div class="card-body">
            <img class="img-fluid" src="${lastP.image}" />
            <h5 class="card-title">${lastP.name}</h5>
            <p class="card-text">${lastP.description}</p>
            <p class="bloc_left_price">${lastP.price} $</p>
        </div>
    </div>
</div>
        
