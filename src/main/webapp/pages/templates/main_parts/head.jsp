<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>

<html>
<head>
    <meta charset="utf-8">
    <meta name="_csrf" content="${_csrf.token}"/>
    <meta name="_csrf_header" content="${_csrf.headerName}"/>
    <title>Штамп-Майстер. Виготовлення печаток та штампів.</title>
    <link rel="stylesheet" href="/bs_css/bootstrap.min.css">
    <link rel="stylesheet" href="/css/mp.css">
    <link rel="stylesheet" href="/css/modal_main.css">
    <link rel="stylesheet" href="/css/rating.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
    <script src="/bs_js/bootstrap.min.js"></script>

</head>
<body class="bodybg">