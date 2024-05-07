<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@include file="includes/language.jsp" %>
<%@include file="includes/header.jsp" %>

<!DOCTYPE html>
<html>
<head>
    <title><fmt:message key="cart.title"/></title>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="css/cart.css" type="text/css"/>
    <script src="script/language.js"></script>
</head>
<body>
    <%@include file="includes/navbar.jsp" %>
    <div style="padding-top: 100px" class="container my-3">
        <div class="HLRhQB">
            <img width="44" height="40" src="https://static.vecteezy.com/system/resources/thumbnails/015/452/522/small/discount-icon-in-trendy-flat-style-isolated-on-background-discount-icon-page-symbol-for-your-web-site-design-discount-icon-logo-app-ui-discount-icon-eps-vector.jpg" alt="fs-icon">
            <span class="UqssKR"><fmt:message key="cart.discount_message" bundle="${langSet}"/></span>
        </div>
        <table class="table table-light">
            <thead>
                <tr>
                    <th scope="col"></th>
                    <th scope="col"><fmt:message key="cart.product" bundle="${langSet}"/></th>
                    <th scope="col"><fmt:message key="cart.category" bundle="${langSet}"/></th>
                    <th scope="col"><fmt:message key="cart.discount" bundle="${langSet}"/></th>
                    <th scope="col"><fmt:message key="cart.price" bundle="${langSet}"/></th>
                    <th scope="col"><fmt:message key="cart.remove" bundle="${langSet}"/></th>
                </tr>
            </thead>
            <tbody>              
                <c:forEach items="${cart}" var="o">
                    <tr>
                        <td><input type="checkbox" value="${o.key.id}" onchange="updateSelectedCount()"/></td>
                        <td>${o.key.name}</td>
                        <td>
                            <c:forEach items="${listC}" var="category">
                                <c:if test="${category.cid eq o.key.cid}">
                                    ${category.cname}
                                </c:if>
                            </c:forEach>
                        </td>
                        <td><fmt:formatNumber value="${o.key.discount*100}" pattern="#" />%</td>
                        <td>$<fmt:formatNumber pattern="#.##" value="${o.key.price * o.value * (1-o.key.discount)}"/></td>
                        <td><a href="remove-from-cart?id=${o.key.id}" class="btn btn-sm btn-danger"><fmt:message key="cart.remove" bundle="${langSet}"/></a></td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </div>

    <div class="fixed-bottom">
        <div class="container">
            <div class="d-flex justify-content-end">
                <h3 class="mr-3"><fmt:message key="cart.total_price" bundle="${langSet}"/> $<fmt:formatNumber pattern="#.##" value="${total}"/></h3>
                <div class="text-right mb-3">
                    <button onclick="removeSelected('<fmt:message key="cart.confirmation_popup_delete" bundle="${langSet}"/>')" id="deleteSelectedBtn" class="btn btn-danger">
                        <fmt:message key="cart.remove_selected" bundle="${langSet}"/>
                        <span id="count"></span>
                    </button>
                       
                    <button onclick="buySelected('<fmt:message key="cart.confirmation_popup_remove" bundle="${langSet}"/>')" id="buySelectedBtn" class="btn btn-primary mr-2">
                        <fmt:message key="cart.buy_selected" bundle="${langSet}"/>
                        <span id="count1"></span>
                    </button>              
                </div>  
            </div>
        </div>
    </div>

    <!-- Confirmation Popup -->
    <div id="confirmationPopup" class="popup">
        <div class="popup-content">
            <h2 id="popupMessage"></h2>
            <button id="confirmBtn"><fmt:message key="cart.confirm" bundle="${langSet}"/></button>
            <button id="cancelBtn"><fmt:message key="cart.cancel" bundle="${langSet}"/></button>
        </div>
    </div>
    <script src="script/cart.js"></script>
</body>
</html>
