<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <%@ include file="bootstrap-css.jsp" %>
    <title>References Converter</title>
</head>
<body>

<nav class="navbar navbar-expand-lg navbar-light bg-light sticky-top">
    <div class="container" style="max-width: 1440px">
        <a class="navbar-brand mb-0 h1" href="./">RC</a>
        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent"
                aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarSupportedContent">
            <ul class="navbar-nav mr-auto">
                <li class="nav-item active">
                    <a class="nav-link" href="./">Главная</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="./result">Результаты</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link disabled" href="#" tabindex="-1" aria-disabled="true">Настройка шаблонов</a>
                </li>
                <li class="nav-item mr-5"></li>
                <li class="nav-item">
                    <div class="input-group">
                        <select class="custom-select" aria-label="" disabled>
                            <option selected>Шаблоны не доступны</option>
                            <option value="1">One</option>
                            <option value="2">Two</option>
                            <option value="3">Three</option>
                        </select>
                        <div class="input-group-append">
                            <button class="btn btn-outline-primary" type="submit" form="inputForm">Преобразовать
                            </button>
                        </div>
                    </div>
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

<div class="container" style="max-width: 1440px">
    <form method="POST" action="<%= request.getContextPath() %>" id="inputForm">
            <textarea class="form-control" aria-label="" name="inputText"
                      placeholder="Введите библиографические ссылки..."
                      style="height: calc(100vh - 5rem); min-height: 150px; resize: none"></textarea>
    </form>
</div>

<%@ include file="bootstrap-scripts.jsp" %>
</body>
</html>