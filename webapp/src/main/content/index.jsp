<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <%@ include file="bootstrap-css.jsp" %>
    <title>References Converter</title>
</head>
<body>

<nav class="navbar navbar-expand-md navbar-light bg-light sticky-top">
    <div class="container col-9">
        <a class="navbar-brand mb-0 h1" href="<%= request.getContextPath() %>">RC</a>
        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent"
                aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarSupportedContent">
            <ul class="navbar-nav mr-auto">
                <li class="nav-item active">
                    <a class="nav-link" href="<%= request.getContextPath() %>">Главная</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="#">Результаты</a>
                </li>
                <li class="nav-item mr-5"></li>
                <li class="nav-item">
                    <button type="submit" form="inputForm" class="btn btn-outline-primary">Обработать
                    </button>
                </li>
            </ul>
            <ul class="navbar-nav">
                <li class="nav-item">
                    <a class="nav-link" href="https://github.com/bertRC/references-converter">Проект на Github</a>
                </li>
            </ul>
        </div>
    </div>
</nav>

<div class="container col-9">
    <form method="POST" action="<%= request.getContextPath() %>" id="inputForm">
            <textarea class="form-control" aria-label="" name="text" placeholder="Введите текст"
                      style="height: calc(100vh - 5rem); min-height: 150px; resize: none"></textarea>
    </form>
</div>

<%@ include file="bootstrap-scripts.jsp" %>
</body>
</html>