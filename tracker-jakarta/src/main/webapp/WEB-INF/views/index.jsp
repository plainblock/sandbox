<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="en">
<head>
    <link href="${pageContext.request.contextPath}/static/css/bootstrap.css" rel="stylesheet"/>
    <link href="${pageContext.request.contextPath}/static/css/style.css" rel="stylesheet"/>
</head>
<body>

<main class="bd-main order-1">
    <div class="bd-intro ps-lg-4">
        <div class="d-md-flex align-items-center justify-content-between">
            <h1 class="bd-title" id="content">Tracker</h1>
        </div>
        <p class="lead">Simple alive monitoring application</p>
    </div>
    <br/>
    <div class="bd-content ps-lg-4">
        <h3 id="functions">Functions</h3>
        <ul>
            <li>
                <p><a href="/public/status/connection">Reachability monitoring</a></p>
            </li>
        </ul>
    </div>
</main>

</body>
</html>