<%-- 
    Document   : detail
    Created on : Feb 21, 2024, 4:04:27 PM
    Author     : huypd
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Detail</title>
        <jsp:include page="/includes/header.jsp"/>
        <style>
            .gallery-wrap .img-big-wrap img {
                height: 450px;
                width: auto;
                display: inline-block;
                cursor: zoom-in;
            }


            .gallery-wrap .img-small-wrap .item-gallery {
                width: 60px;
                height: 60px;
                border: 1px solid #ddd;
                margin: 7px 2px;
                display: inline-block;
                overflow: hidden;
            }

            .gallery-wrap .img-small-wrap {
                text-align: center;
            }
            .gallery-wrap .img-small-wrap img {
                max-width: 100%;
                max-height: 100%;
                object-fit: cover;
                border-radius: 4px;
                cursor: zoom-in;
            }
            .img-big-wrap img{
                width: 100% !important;
                height: auto !important;
            }
        </style>
    </head>
    <body>
        <jsp:include page="/includes/navbar.jsp"/>
        <jsp:include page="/includes/banner.jsp"/>
        <div class="container">
            <div class="row">
                <jsp:include page="/includes/left.jsp"/>
                <div class="col-sm-9">
                    <div class="container">
                        <div class="card">
                            <div class="row">
                                <aside class="col-sm-5 border-right">
                                    <article class="gallery-wrap"> 
                                        <div class="img-big-wrap">
                                            <div> <a href="#"><img src="${c.image}"></a></div>
                                        </div> <!-- slider-product.// -->
                                    </article> <!-- gallery-wrap .end// -->
                                </aside>
                                <aside class="col-sm-7">
                                    <article class="card-body p-5">
                                        <h3 class="title mb-3">${c.name}</h3>

                                        <p class="price-detail-wrap"> 
                                            <span class="price h3 text-warning"> 
                                                <span class="currency">US $</span><span class="num">${c.price}</span>
                                            </span> 
                                            <!--<span>/per kg</span>--> 
                                        </p> <!-- price-detail-wrap .// -->
                                        <dl class="item-property">
                                            <dt>Description</dt>
                                            <dd><p>${c.description} </p></dd>
                                        </dl>

                                        <hr>
<!--                                        <div class="row">
                                            <div class="col-sm-5">
                                                <dl class="param param-inline">
                                                    <dt>Quantity: </dt>
                                                    <dd>
                                                        <select id="quantitySelect" class="form-control form-control-sm" style="width:70px;">
                                                            <option> 1 </option>
                                                            <option> 2 </option>
                                                            <option> 3 </option>
                                                            <option> 4 </option>
                                                            <option> 5 </option>
                                                        </select>
                                                    </dd>
                                                </dl>   item-property .// 
                                            </div>  col.// 

                                        </div>  row.// -->
                                        <hr>
                                        <a id="buy-now" href="buynow?id=${c.id}&quantity=1" class="btn btn-lg btn-primary text-uppercase"> Buy now </a>
                                        <a id="add-to-cart" href="add-to-cart?id=${c.id}&quantity=1" class="btn btn-lg btn-outline-primary text-uppercase"> <i class="fas fa-shopping-cart"></i> Add to cart </a>
                                    </article> <!-- card-body.// -->
                                </aside> <!-- col.// -->
                            </div> <!-- row.// -->
                        </div> <!-- card.// -->
                    </div>
                <div id="notification" style="display: none;">
                    <c:if test="${not empty mes}">
                        <div class="alert alert-info alert-dismissible fade show" role="alert">
                            <strong>${mes}</strong>
                            <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
                        </div>
                    </c:if>
                </div>                    
                </div>
            </div>
        </div>
        <jsp:include page="/includes/footer.jsp"/>
    </body>
    <script>
        // Lấy thẻ select và thẻ a cần cập nhật href
        var quantityInput = document.getElementById('quantitySelect');
        var buyNowLink = document.getElementById('buy-now');
        var addToCartLink = document.getElementById('add-to-cart');
        // Lắng nghe sự kiện onchange của select
        quantityInput.addEventListener('change', function () {
            // Lấy giá trị số lượng đã chọn
            const selectedQuantity = this.value;
            // Lấy href hiện tại của thẻ a
            var currentBuyNowHref = buyNowLink.getAttribute('href');
            var currentAddToCartHref = addToCartLink.getAttribute('href');
            // Tìm và thay thế giá trị số lượng trong href
            currentBuyNowHref = currentBuyNowHref.replace(/quantity=\d+/, 'quantity=' + selectedQuantity);
            currentAddToCartHref = currentAddToCartHref.replace(/quantity=\d+/, 'quantity=' + selectedQuantity);
            // Cập nhật href mới cho cả hai thẻ a
            buyNowLink.setAttribute('href', currentBuyNowHref);
            addToCartLink.setAttribute('href', currentAddToCartHref);
        });
    </script>
</html>
