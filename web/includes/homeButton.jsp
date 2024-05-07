<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:choose>
    <c:when test="${language eq 'en_US'}">
        <style>
            .btn {
                font-size: 16px !important;
            }
        </style>
    </c:when>
    <c:when test="${language eq 'vi_VN'}">
        <style>
            .btn {
                font-size: 12px !important;
            }
        </style>
    </c:when>
</c:choose>
