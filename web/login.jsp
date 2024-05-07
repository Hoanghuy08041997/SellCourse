<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@include file="includes/language.jsp" %>
<%
    String language = (String) session.getAttribute("language");
    if (language == null) {
        session.setAttribute("language", "en_US");
    }
%>

<!DOCTYPE html>
<html>
    <head>   
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title><fmt:message key="login.title" bundle="${langSet}"/></title>
        <link href="//maxcdn.bootstrapcdn.com/bootstrap/3.3.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
        <script src="//maxcdn.bootstrapcdn.com/bootstrap/3.3.0/js/bootstrap.min.js"></script>
        <script src="//code.jquery.com/jquery-1.11.1.min.js"></script>
        <link rel="stylesheet" href="css/login.css"/>
        <script>
            $(function () {
                $('#login-form-link').click(function (e) {
                    $("#login-form").delay(100).fadeIn(100);
                    $("#register-form").fadeOut(100);
                    $('#register-form-link').removeClass('active');
                    $(this).addClass('active');
                    e.preventDefault();
                });
                
                $('#register-form-link').click(function (e) {
                    $("#register-form").delay(100).fadeIn(100);
                    $("#login-form").fadeOut(100);
                    $('#login-form-link').removeClass('active');
                    $(this).addClass('active');
                    e.preventDefault();                  
                });
                
                $(function () {
                    $('#register-submit').click(function (e) {
                        e.preventDefault();
                        $.ajax({
                            type: 'POST',
                            url: 'sign-up',
                            data: $('#register-form').serialize(),
                            success: function (response) {
                                window.location.href = "home";
                            },
                            error: function (xhr) {
                                if (xhr.status == 400) {
                                    var errorMessage = xhr.responseText;
                                    var startIndex = errorMessage.indexOf("Message") + "Message".length;
                                    var endIndex = errorMessage.indexOf("</p>", startIndex);
                                    var errorMessageContent = errorMessage.substring(startIndex, endIndex);
                                    var errorMessageText = errorMessageContent.replace(/<[^>]+>/g, '');
                                    alert("An error occurred: " + errorMessageText); 
                                } else {
                                    alert("An error occurred while processing the activation. Please try again later.");
                                }
                            }
                        });
                    });
                });         
            });    
        </script>      
    </head>
    <body>
        <div class="container">
            <div class="row">
                <div class="col-md-6 col-md-offset-3">
                    <div class="panel panel-login">
                        <div class="panel-heading">
                            <div class="row">
                                <div class="col-xs-6">
                                    <a href="#" class="active" id="login-form-link"><fmt:message key="login.form.heading" bundle="${langSet}"/></a>
                                </div>
                                <div class="col-xs-6">
                                    <a href="#" id="register-form-link"><fmt:message key="login.form.register" bundle="${langSet}"/></a>
                                </div>
                            </div>
                            <hr>
                        </div>
                        <div class="panel-body">
                            <div class="row">
                                <div class="col-lg-12">
                                    <p class="text-danger"> ${mes} </p>
                                    <form id="login-form" action="login" method="post" role="form" style="display: block;">
                                        <div class="form-group">
                                            <input type="text" name="email" id="email" tabindex="1" class="form-control" placeholder="<fmt:message key="login.form.email_label" bundle="${langSet}"/>" value="">
                                        </div>
                                        <div class="form-group">
                                            <input type="password" name="password" id="password" tabindex="2" class="form-control" placeholder="<fmt:message key="login.form.password_label" bundle="${langSet}"/>">
                                        </div>
                                        <div class="form-group text-center">
                                            <input type="checkbox" tabindex="3" class="" name="remember" id="remember">
                                            <label for="remember"><fmt:message key="login.form.remember_me" bundle="${langSet}"/></label>
                                        </div>
                                        <div class="form-group">
                                            <div class="row">
                                                <div class="col-sm-6 col-sm-offset-3">
                                                    <input type="submit" name="login-submit" id="login-submit" tabindex="4" class="form-control btn btn-login" value="<fmt:message key="login.form.submit" bundle="${langSet}"/>">
                                                </div>
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <div class="row">
                                                <div class="col-lg-12">
                                                    <div class="text-center">
                                                        <a href="#" tabindex="5" class="forgot-password"><fmt:message key="login.form.forgot_password" bundle="${langSet}"/></a>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </form>
                                    <form id="register-form" action="sign-up" method="post" role="form" style="display: none;">
                                        <div class="form-group">
                                            <input type="text" name="fullName" id="fullName" tabindex="1" class="form-control" placeholder="<fmt:message key="login.form.full_name" bundle="${langSet}"/>" value="" required>
                                        </div>
                                        <div class="form-group">
                                            <input type="text" name="address" id="address" tabindex="1" class="form-control" placeholder="<fmt:message key="login.form.address" bundle="${langSet}"/>" value="" required>
                                        </div>
                                        <div class="form-group">
                                            <input type="text" name="phoneNumber" id="phoneNumber" tabindex="1" class="form-control" placeholder="<fmt:message key="login.form.phone_number" bundle="${langSet}"/>" value="" required>
                                        </div>
                                        <div class="form-group">
                                            <input type="email" name="email" id="email" tabindex="1" class="form-control" placeholder="<fmt:message key="login.form.email_label" bundle="${langSet}"/>" value="" required>
                                        </div>
                                        <div class="form-group">
                                            <input type="password" name="password" id="password" tabindex="2" class="form-control" placeholder="<fmt:message key="login.form.password_label" bundle="${langSet}"/>" required>
                                        </div>
                                        <div class="form-group">
                                            <input type="password" name="confirm-password" id="confirm-password" tabindex="2" class="form-control" placeholder="<fmt:message key="login.form.confirm_password" bundle="${langSet}"/>" required>
                                        </div>
                                        <div class="form-group">
                                            <div class="row">
                                                <div class="col-sm-6 col-sm-offset-3">
                                                    <input type="submit" name="register-submit" id="register-submit" tabindex="4" class="form-control btn btn-register" value="<fmt:message key="login.form.register" bundle="${langSet}"/>">
                                                </div>
                                            </div>
                                        </div>
                                    </form>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <div class="text-center">
             <a class="btn" href="#" onclick="updateLanguage('vi_VN')"><fmt:message key="login.language.vietnamese" bundle="${langSet}"/></a> | <a class="btn" href="#" onclick="updateLanguage('en_US')"><fmt:message key="login.language.english" bundle="${langSet}"/></a>
        </div>  
    <script src="script/language.js"></script>
    </body>
</html>
