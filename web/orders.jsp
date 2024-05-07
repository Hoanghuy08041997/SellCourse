<%-- 
    Document   : index
    Created on : Feb 23, 2024, 10:26:29 PM
    Author     : huypd
--%>

<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="dao.DAO"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Orders Page</title>
        <jsp:include page="/includes/header.jsp" />
    </head>
    <body>
        <jsp:include page="/includes/navbar.jsp" />
        <div class="container">
            <div class="card-header my-3">All Orders</div>
            <table class="table table-light">
                <thead>
                    <tr>
                        <th scope="col">Transaction Code</th>
                        <th scope="col">Date</th>
                        <th scope="col">Name</th>
                        <th scope="col">Category</th>
                        <th scope="col">Quantity</th>
                        <th scope="col">Discount</th>
                        <th scope="col">Price</th>
                        <th scope="col">Key</th>
                        <th scope="col">Cancel</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach items="${transactionList}" var="t">
                        <c:forEach items="${t.listOrders}" var="o">
                            <tr>
                                <td>${t.transactionId}</td>
                                <td>${t.date}</td>
                                <td>${o.course.name}</td>
                                <td>${o.course.cname}</td>
                                <td>${o.quantity}</td>
                                <td>${o.course.discount}</td>                               
                                <td>$${o.course.price*o.quantity}</td>
                                <td>${o.activationCode}</td>
                                <td><a class="btn btn-sm btn-danger" href="cancel-order?tid=${t.transactionId}&pid=${o.course.id}">Cancel Order</a></td>
                            </tr>
                        </c:forEach>
                    </c:forEach>

                </tbody>
            </table>
        </div>
    </body>
</html>