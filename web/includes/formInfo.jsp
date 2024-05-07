<style>
    body {
        font-family: Arial, sans-serif; 
        background-color: #f2f2f2;
    }

    #checkoutForm {
        max-width: 1200px; 
        margin: 0 auto; 
        padding: 20px;
        border: 1px solid #ccc;
        border-radius: 5px; 
        background-color: #fff; 
        box-shadow: 0px 0px 10px rgba(0, 0, 0, 0.1);
    }

    .form-group {
        margin-bottom: 5px;
        display: flex; 
        align-items: center;
    }

    label {
        margin-bottom: 2px;
        margin-right: 10px;
        font-weight: bold; 
        flex: 1;
        max-width: 25%;
    }

    input[type="text"],
    input[type="email"] {
        padding: 5px; 
        border: 1px solid #ccc;
        border-radius: 5px; 
        box-sizing: border-box; 
        flex: 3; 
        max-width: 75%; 
        font-family: Arial, sans-serif; 
        font-size: 14px;
    }

    h4 {
        font-family: Arial, sans-serif;
        font-size: 20px; 
        margin-bottom: 5px;
    }
</style>
<head>
    <%@include file="language.jsp" %>
</head>
<body>
<div style="padding-top: 100px">
    <form id="checkoutForm" action="cart-check-out" method="post"> 
        <div>						
            <h4><fmt:message key="form.information" bundle="${langSet}" /></h4>
        </div>
        <div>
            <div class="form-group">
                <label for="name"><fmt:message key="form.fullname" bundle="${langSet}" /></label>
                <input value="${sessionScope.acc.fullName}" type="text" id="name" name="name" placeholder="Enter your full name">
            </div>
            <div class="form-group">
                <label for="phone"><fmt:message key="form.phone" bundle="${langSet}" /></label>
                <input value="${sessionScope.acc.phoneNumber}" type="text" id="phone" name="phone" placeholder="Enter your phone number">
            </div>
            <div class="form-group">
                <label for="address"><fmt:message key="form.address" bundle="${langSet}" /></label>
                <input value="${sessionScope.acc.address}" type="text" id="address" name="address" placeholder="Enter your address">
            </div>
            <div class="form-group">
                <label for="email"><fmt:message key="form.email" bundle="${langSet}" /></label>
                <input value="${sessionScope.acc.email}" type="email" id="email" name="email" placeholder="Enter your email address">
            </div>
        </div>
    </form>
</div>

</body>
