<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en" class="fly-html-layui fly-html-store">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/statics/front/layui/dist/css/layui.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/statics/front/css/global.css" charset="utf-8">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/statics/front/css/global(1).css" charset="utf-8">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/statics/front/css/store.css" charset="utf-8">
    <link rel="icon" href="${pageContext.request.contextPath}/statics/front/images/favicon.ico">
    <title>酒店管理系统</title>
<body>
<!-- 顶部start -->
<div class="layui-header header header-store" style="background-color: #393D49;">
    <div class="layui-container">
        <a class="logo" href="index.html">
            <img src="${pageContext.request.contextPath}/statics/front/images/logo.png" alt="layui">
        </a>
        <div class="layui-form component" lay-filter="LAY-site-header-component"></div>
        <ul class="layui-nav" id="layui-nav-userinfo">
            <li data-id="index" class="layui-nav-item layui-hide-xs"><a class="fly-case-active" data-type="toTopNav"
                                                                        href="/index.jsp">首页</a></li>
            <li data-id="room" class="layui-nav-item layui-hide-xs layui-this"><a class="fly-case-active"
                                                                                  data-type="toTopNav"
                                                                                  href="/room/list.html">房间</a></li>
            <li data-id="login" class="layui-nav-item layui-hide-xs "><a class="fly-case-active" data-type="toTopNav"
                                                                         href="/login.jsp">登入</a></li>
            <li data-id="register" class="layui-nav-item layui-hide-xs "><a class="fly-case-active" data-type="toTopNav"
                                                                            href="/register.jsp">注册</a></li>
            <span class="layui-nav-bar" style="left: 560px; top: 55px; width: 0px; opacity: 0;"></span></ul>
    </div>
</div>
<!-- 顶部end -->

<!-- 中间区域开始 -->
<!-- 搜索框start -->
<div class="shop-nav shop-index">
    <!--搜索 start-->
    <div id="LAY-topbar" style="height: auto;">
        <form class="layui-form layuimini-form">
            <div class="input-search">
                <div id="searchRoom"><input type="text" placeholder="搜索你需要的房间" name="keywords" id="searchKeywords"
                                            autocomplete="off" value="">
                    <button class="layui-btn layui-btn-shop" lay-submit="" lay-filter="searchHotelRoom" style="background-color: #009688"><i
                            class="layui-icon layui-icon-search"></i></button>
                </div>
                <div class="layui-container layui-hide-xs"><a href="#" class="topbar-logo"> <img
                        src="${pageContext.request.contextPath}/statics/front/images/logo-1.png" alt="layui"> </a></div>
            </div>
        </form>
    </div>
    <!--搜索 end-->

</div>
<!-- 搜索框end -->
<!-- 筛选条件start -->
<div class="shoplist-filter">
    <div class="layui-container">
        <div class="layui-card">
            <div class="layui-card-header"> <span class="layui-breadcrumb" style="visibility: visible;"> <a href="../hotel/index.html">酒店首页</a><span lay-separator="">/</span> <a href="../hotel/lists.html">类别</a><span lay-separator="">/</span> <a><cite>全部</cite></a>
						</span> </div>
            <div class="layui-card-body">
                <div class="store-cat-item"> <span><i class="layui-icon layui-icon-shop-fenlei"></i>类别：</span>
                    <ul id="getAllRoomType">
                        <%-- 判断typeId房型ID值是否为空，为空表示当前点击的是全部这个超链接 --%>
                        <li data-id="0" <c:if test="${typeId == null}">class="active"</c:if>> <a class="fly-case-active" href="/room/list.html" data-type="toRoomTypeListByLists">全部</a></li>
                        <c:forEach var="roomType" items="${roomTypeList}">
                            <%-- 判断typeId房型ID值是否与房型列表中ID值相等，相等则选择该超链接，设置字体颜色为绿色 --%>
                            <li data-id="${roomType.id}" <c:if test="${typeId ==  roomType.id}">class="active"</c:if>> <a class="fly-case-active" href="/room/list/${roomType.id}" data-type="toRoomTypeListByLists">${roomType.typename}</a> </li>
                        </c:forEach>
                    </ul>
                </div>

                <p class="filtEnd">筛选出<span id="filtTotal">${roomList.size()}</span>个房间</p>
            </div>
        </div>
    </div>
</div>
<!-- 筛选条件end -->

<!-- 房间列表start -->
<div class="shop-temp shoplist">
    <div class="temp-normal">
        <div class="layui-container">
            <div class="layui-row layui-col-space20 shoplist" id="roomList">
                <c:forEach var="room" items="${roomList}">
                    <div data-id="${room.id}" class="layui-col-xs12 layui-col-sm6 layui-col-md4 layui-col-lg3">
                    <a class="template store-list-box fly-case-active" href="/room/${room.id}.html" data-type="toRoomInfo">
                        <img src="/hotel/show/${room.photo}" class="store-list-cover">
                        <h2 class="layui-elip">${room.typeName}</h2>
                        <div> <label class="layui-badge-rim store-list-pay"> ￥${room.price} </label>
                            <div class="store-list-colorbar">
                                <span class="store-color-bar" style="position:relative;bottom:3px;border-color: #009688;color: #009688;border-width: 1px;border-style: solid;background-color: #fff;    text-align: center;">NO.${room.roomnum}</span>
                                <span class="store-color-bar" style="border-color: #5fb878;color: #5fb878;border-width: 1px;border-style: solid;background-color: #fff;    text-align: center;">${room.typeName}</span>
                                <span class="store-color-bar" style="border-color: #01aaed;color: #01aaed;border-width: 1px;border-style: solid;background-color: #fff;    text-align: center;">${room.floorName}</span>
                            </div>
                        </div>
                    </a>
                </div>
                </c:forEach>
            </div>
            <div style="margin: 50px 0 80px; text-align: center;"> </div>
        </div>
    </div>
</div>
<!-- 房间列表end -->



<!-- 中间区域结束 -->




<!-- 脚本开始 -->
<script src="${pageContext.request.contextPath}/statics/front/layui/dist/layui.js"></script>
<script>
    layui.use(["form","element","carousel"], function () {
        var form = layui.form,
            layer = layui.layer,
            element = layui.element,
            carousel = layui.carousel,
            $ = layui.$;

        //渲染轮播图
        carousel.render({
            elem: '#LAY-store-banner'
            ,width: '100%' //设置容器宽度
            ,height: '460' //设置容器高度
            ,arrow: 'always' //始终显示箭头
        });
    });
</script>
<!-- 脚本结束 -->
<ul class="layui-fixbar">
    <li class="layui-icon layui-fixbar-top" lay-type="top" style=""></li>
</ul>
<div class="layui-layer-move"></div>

</body>
</html>
