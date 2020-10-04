<%@ page import="education.bert.rc.generator.collection.TemplateCollection" %>
<%@ page import="education.bert.rc.generator.templates.Template" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <%@ include file="css-collection.jsp" %>
    <title>References Converter</title>
</head>
<body>

<%
    TemplateCollection templateCollection =
            (TemplateCollection) getServletConfig().getServletContext().getAttribute("templateCollection");
    String templateSelected = (String) session.getAttribute("templateSelected");
    List<Template> templateList = null;
    if (templateCollection != null) {
        templateList = templateCollection.getAll();
    }
%>

<nav class="navbar navbar-expand-lg navbar-dark bg-darkblue sticky-top">
    <a class="navbar-brand mb-0 h1" href="./">RC</a>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent"
            aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" id="navbarSupportedContent">
        <ul class="navbar-nav mr-auto">
            <li class="nav-item">
                <a class="nav-link" href="./">Главная</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="./result">Результаты</a>
            </li>
            <li class="nav-item active">
                <a class="nav-link" href="./template">Настройка шаблона</a>
            </li>
            <li class="nav-item mr-5"></li>
            <li class="nav-item mr-1">
                <form class="mb-0" method="POST" action="./template?action=convert"
                      id="convertForm">
                    <select class="custom-select" aria-label="" name="selectTemplate">
                        <% if (templateList != null) {
                            for (int i = 0; i < templateList.size(); i++) {
                        %>
                        <option value="<%=i%>" <%=String.valueOf(i).equals(templateSelected) ? "selected" : ""%>><%=templateList.get(i).getName()%>
                        </option>
                        <% }
                        } %>
                    </select>
                </form>
            </li>
            <li class="nav-item">
                <button class="btn btn-icon" type="submit" form="convertForm" data-toggle="tooltip"
                        title="Преобразовать">
                    <svg xmlns="http://www.w3.org/2000/svg" width="24" height="24" viewBox="0 0 24 24" fill="none"
                         stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round"
                         class="feather feather-play">
                        <polygon points="5 3 19 12 5 21 5 3"></polygon>
                    </svg>
                </button>
            </li>
        </ul>
        <ul class="navbar-nav">
            <li class="nav-item">
                <a class="nav-link" href="https://github.com/bertRC/references-converter" data-toggle="tooltip"
                   title="Наш проект на Github">
                    <svg xmlns="http://www.w3.org/2000/svg" width="24" height="24" viewBox="0 0 24 24" fill="none"
                         stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round"
                         class="feather feather-github">
                        <path d="M9 19c-5 1.5-5-2.5-7-3m14 6v-3.87a3.37 3.37 0 0 0-.94-2.61c3.14-.35 6.44-1.54 6.44-7A5.44 5.44 0 0 0 20 4.77 5.07 5.07 0 0 0 19.91 1S18.73.65 16 2.48a13.38 13.38 0 0 0-7 0C6.27.65 5.09 1 5.09 1A5.07 5.07 0 0 0 5 4.77a5.44 5.44 0 0 0-1.5 3.78c0 5.42 3.3 6.61 6.44 7A3.37 3.37 0 0 0 9 18.13V22"></path>
                    </svg>
                </a>
            </li>
        </ul>
    </div>
</nav>

<div class="container-fluid">
    <div class="row">
        <div class="col-sm">
            <form class="mt-3">
                <div class="form-group">
                    <label for="articleLatinTemplate">Формат статьи (англ.)</label>
                    <textarea class="form-control" id="articleLatinTemplate" rows="2"></textarea>
                </div>
                <div class="form-group">
                    <label for="articleCyrillicTemplate">Формат статьи (рус.)</label>
                    <textarea class="form-control" id="articleCyrillicTemplate" rows="2"></textarea>
                </div>
                <div class="form-group">
                    <label for="bookLatinTemplate">Формат книги (англ.)</label>
                    <textarea class="form-control" id="bookLatinTemplate" rows="2"></textarea>
                </div>
                <div class="form-group">
                    <label for="bookCyrillicTemplate">Формат книги (рус.)</label>
                    <textarea class="form-control" id="bookCyrillicTemplate" rows="2"></textarea>
                </div>
                <button type="submit" class="btn btn-icon">Сохранить и применить</button>
            </form>
        </div>
        <div class="col-sm">
            <form class="mt-3">
                <div class="form-group">
                    <label for="articleLatinTemplateExample">Пример статьи (англ.)</label>
                    <textarea class="form-control" id="articleLatinTemplateExample" rows="2" readonly></textarea>
                </div>
                <div class="form-group">
                    <label for="articleCyrillicTemplateExample">Пример статьи (рус.)</label>
                    <textarea class="form-control" id="articleCyrillicTemplateExample" rows="2" readonly></textarea>
                </div>
                <div class="form-group">
                    <label for="bookLatinTemplateExample">Пример книги (англ.)</label>
                    <textarea class="form-control" id="bookLatinTemplateExample" rows="2" readonly></textarea>
                </div>
                <div class="form-group">
                    <label for="bookCyrillicTemplateExample">Пример книги (рус.)</label>
                    <textarea class="form-control" id="bookCyrillicTemplateExample" rows="2" readonly></textarea>
                </div>
            </form>
        </div>
    </div>
</div>

<%@ include file="bootstrap-scripts.jsp" %>
</body>
</html>