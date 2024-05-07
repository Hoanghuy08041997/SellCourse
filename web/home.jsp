<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="model.Course" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@include file="includes/language.jsp" %>
<%@include file="includes/homeButton.jsp"%>
<fmt:setBundle basename="i18n.language" var="langSet"/>
<!DOCTYPE html>
<html>
    <head>
        <title><fmt:message key="home.title" bundle="${langSet}"/></title>
        <jsp:include page="/includes/header.jsp" />   
        <link rel="stylesheet" href="css/home.css"/>
    </head>
    <body>
        <jsp:include page="/includes/navbar.jsp" />
        <div id="bannerHome"><jsp:include page="/includes/banner.jsp"/></div>
        <div class="container">
            <div class="row">
                <div class="col">
                    <nav aria-label="breadcrumb">
                        <ol class="breadcrumb">
                            <li class="breadcrumb-item"><a href="home"><fmt:message key="home.breadcrumb.home" bundle="${langSet}"/></a></li>
                            <li class="breadcrumb-item"><a href="#"><fmt:message key="home.breadcrumb.category" bundle="${langSet}"/></a></li>
                            <li class="breadcrumb-item active" aria-current="true">${cname}</li>
                        </ol>
                    </nav>
                </div>
            </div>
        </div>

        <div class="container">
            <div class="row">
                <jsp:include page="/includes/left.jsp"/>
                <div class="col-sm-9">
                    <div id="content" class="row">
                        <c:forEach items="${listP}" var="o">
                            <div class="col-12 col-md-6 col-lg-4" style="position: relative;">
                                <div class="card" onmouseover="showPopup(this)" onmouseout="closePopup(this)">
                                    <img class="card-img-top" src="${o.image}" alt="Card image cap">
                                    <div class="card-body">
                                        <h4 class="card-title show_txt"><a href="detail?id=${o.id}" title="View Product">${o.name}</a></h4>
                                        <h5 class="price"><fmt:message key="home.card.price" bundle="${langSet}"/> $${o.price}</h5>
                                        <c:forEach items="${listC}" var="category">
                                            <c:if test="${category.cid eq o.cid}">
                                                <h5 class="category"><fmt:message key="home.card.category" bundle="${langSet}"/> ${category.cname}</h5>
                                            </c:if>
                                        </c:forEach>

                                        <c:if test="${tagCourseId == o.id}">
                                            <h6 style='color: crimson; text-align: center'>${mes}</h6>
                                        </c:if>
                                        <div class="mt-3 d-flex justify-content-between">
                                            <a class="btn btn-dark" href="add-to-cart?id=${o.id}&quantity=1"><fmt:message key="home.card.add_to_cart" bundle="${langSet}"/></a> 
                                            <a class="btn btn-danger" href="buynow?id=${o.id}&quantity=1"><fmt:message key="home.card.buy_now" bundle="${langSet}"/></a>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </c:forEach>
                    </div>
                </div>
            </div>
        </div>

        <jsp:include page="/includes/footer.jsp"/>

        <div style="width: 300px; height: 600px" id="activePopup" class="popup" style="display: none;">        
            <div class='course-info'>
                <img src='' alt=''>
                <div class='details'>
                    <h4 id="activeCourseName"></h4>

                    <!-- Form -->
                    <form id="activateCourseForm" action="ActiveServlet" method="post">
                        <input type="hidden" id="activeCourseId" name="courseId">
                        <label for="activeCourseText"><fmt:message key="home.card.enter_activation_key" bundle="${langSet}"/>:</label>
                        <input type="text" id="activeCourseText" name="activeCourseText">
                        <button type="submit" class="btn btn-primary"><fmt:message key="home.card.activate" bundle="${langSet}"/></button>
                    </form>
                </div>
            </div>
        </div>

        <div id="coursePopup" class="popup"></div> 
        <script>
            document.addEventListener("DOMContentLoaded", function () {
                var categoryBlock = document.querySelector('.category_block');
                categoryBlock.style.display = 'none';
                // Hiển thị danh sách danh mục khi trỏ chuột vào
                categoryBlock.parentNode.addEventListener('mouseover', function () {
                    categoryBlock.style.display = 'block';
                });

                // Ẩn danh sách danh mục khi trỏ chuột ra ngoài
                categoryBlock.parentNode.addEventListener('mouseout', function () {
                    categoryBlock.style.display = 'none';
                });

                var popup = document.getElementById('coursePopup');
                var activePopup = document.getElementById('activePopup');

                // Thêm sự kiện để ngừng đếm ngược khi người dùng trỏ chuột vào popup
                popup.addEventListener('mouseenter', function () {
                    clearTimeout(popupTimer); // Xóa timeout nếu có
                });

                // Thêm sự kiện để đóng popup khi người dùng rời khỏi popup
                popup.addEventListener('mouseleave', closePopupImmediately);
            });

            var popupTimer; // Biến để lưu trữ đối tượng timeout

            var listP = [
            <c:forEach items="${listP}" var="course" varStatus="loop">
            {
            id: "${course.id}",
                    name: "${course.name}",
                    price: ${course.price},
                    cname: "${course.cname}",
                    image: "${course.image}",
                    description: "${course.description}",
                    duration_month: ${course.duration_month},
                    publicDate: "${course.publicDate}",
                    discount: ${course.discount}
            }<c:if test="${not loop.last}">,</c:if>
            </c:forEach>
            ];
            function showPopup(element) {
                clearTimeout(popupTimer);

                var popup = document.getElementById('coursePopup');
                if (element) {
                    var courseId = element.querySelector('.card-title a').getAttribute('href').split('=')[1];
                    var courseFound = listP.find(function (item) {
                        return item.id === courseId;
                    });

                    if (courseFound) {
                        var content = "<div class='course-info'>" +
                                "<img src='" + courseFound.image + "' alt='" + courseFound.name + "'>" +
                                "<div class='details'>" +
                                "<h4>" + courseFound.name + "</h4>" +
                                "<p><strong>Price:</strong> $" + courseFound.price + "</p>" +
                                "<p><strong>Discount:</strong> " + courseFound.discount*100 + "%</p>" +
                                "<p><strong>Category:</strong> " + courseFound.cname + "</p>" +
                                "<p><strong>Description:</strong> " + courseFound.description + "</p>" +
                                "<p><strong>Duration:</strong> " + courseFound.duration_month + " months</p>" +
                                "<p><strong>Public Date:</strong> " + courseFound.publicDate + "</p>" +
                                "<p>If you have key:</p>" +
                                "<button class='btn btn-primary' onclick='openActivePopup(\"" + courseId + "\")'>Active this Course</button>" +
                                "</div>" +
                                "</div>";

                        popup.innerHTML = content;
                        popup.style.display = 'block';
                        var rect = element.getBoundingClientRect();
                        var popupWidth = popup.offsetWidth;
                        var leftPos = rect.left + window.pageXOffset + rect.width + 10;
                        var screenWidth = window.innerWidth;

                        if (leftPos + popupWidth > screenWidth) {
                            leftPos = rect.left - popupWidth - 10;
                        }

                        // Set the position of the popup
                        var topPos = rect.top + window.pageYOffset;
                        popup.style.top = topPos + 'px';
                        popup.style.left = leftPos + 'px';

                        // Thêm sự kiện để theo dõi khi người dùng rời khỏi course
                        element.addEventListener('mouseleave', closePopupWithDelay);
                    } else {
                        console.error('Không tìm thấy khóa học với ID: ' + courseId);
                    }
                } else {
                    console.error('Không thể tìm thấy dữ liệu');
                }
            }

            // Function để đóng popup khi người dùng rời khỏi course
            function closePopupWithDelay() {
                popupTimer = setTimeout(function () {
                    var popup = document.getElementById('coursePopup');
                    popup.style.display = 'none';
                }, 500);
            }

            function closePopupImmediately() {
                clearTimeout(popupTimer);
                var popup = document.getElementById('coursePopup');
                popup.style.display = 'none';
            }

            function openActivePopup(courseId) {
                var activePopup = document.getElementById('activePopup');
                activePopup.style.display = 'block';

                var courseFound = listP.find(function (item) {
                    return item.id === courseId;
                });

                // Tạo nội dung cho activePopup
                var content = "<div class='course-info'>" +
                        "<button id=\"closePopupButton\" onclick=\"closePopupInActive()\"></button>" +
                        "<img src='" + courseFound.image + "' alt='" + courseFound.name + "'>" +
                        "<div class='details'>" +
                        "<h4>" + courseFound.name + "</h4>" +
                        "<p><strong>Price:</strong> $" + courseFound.price + "</p>" +
                        "<p><strong>Discount:</strong> " + courseFound.discount*100 + "%</p>" +
                        "<p><strong>Category:</strong> " + courseFound.cname + "</p>" +
                        "<p><strong>Description:</strong> " + courseFound.description + "</p>" +
                        "<p><strong>Duration:</strong> " + courseFound.duration_month + " months</p>" +
                        "<p><strong>Public Date:</strong> " + courseFound.publicDate + "</p>" +
                        "<input type='text' id='activeCourseKey' name='activeCourseKey'>" +
                        "<button id='activateCourseBtn' class='btn btn-primary'>Activate Course</button>" +
                        "</div>" +
                        "</div>";

                activePopup.innerHTML = content;

                document.getElementById('activateCourseBtn').addEventListener('click', function () {
                    var activeCourseKey = document.getElementById('activeCourseKey').value;
                    if (activeCourseKey.trim() === '') {
                        alert('Please enter the activation key.');
                    } else {
                        var xhttp = new XMLHttpRequest();
                        xhttp.onreadystatechange = function () {
                            if (this.readyState == 4) {
                                if (this.status == 200) {
                                    alert("Course activated successfully!");
                                    window.location.href = "detail?id=" + courseFound.id;
                                } else if (this.status == 400) {
                                    alert("This code is invalid or missing!");
                                } else if (this.status == 401) {
                                    alert("Unauthorized! Please login");
                                    window.location.href = "login";
                                } else {
                                    alert("An error occurred while processing the activation. Please try again later.");
                                }
                            }
                        };
                        xhttp.open("POST", "activeCourse", true);
                        xhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
                        var courseId = encodeURIComponent(courseId);
                        xhttp.send("id=" + courseId + "&key=" + encodeURIComponent(activeCourseKey));
                    }
                });
            }

            function closePopupInActive() {
                var popup = document.getElementById("activePopup");
                popup.style.display = "none";
            }
        </script>
    </body>
</html>
