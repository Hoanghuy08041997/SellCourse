<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<c:choose>
    <c:when test="${language eq 'en_US'}">
        <fmt:setLocale value="en_US"/>  
    </c:when>
    <c:when test="${language eq 'vi_VN'}">
        <fmt:setLocale value="vi_VN"/>
    </c:when>
</c:choose>              
        
<fmt:setBundle basename="i18n.language" var="langSet"/>
