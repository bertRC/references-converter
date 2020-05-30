<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <%@ include file="bootstrap-css.jsp" %>
    <title>References Converter</title>
</head>
<body>

<nav class="navbar navbar-expand-lg navbar-light bg-light sticky-top">
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent"
            aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>

    <div class="collapse navbar-collapse" id="navbarSupportedContent">
        <ul class="navbar-nav mr-auto">
            <li class="nav-item">
                <button class="btn btn-outline-success my-2 my-sm-0" type="submit" form="myForm">Work</button>
            </li>
        </ul>
    </div>
</nav>

<div class="container-fluid">
    <form method="POST" action="<%= request.getContextPath() %>" id="myForm">
        <div class="form-group">
            <label for="exampleFormControlTextarea1">Example textarea</label>
            <textarea class="form-control" id="exampleFormControlTextarea1" name="text"
                      style="height: calc(100vh - 10rem); min-height: 150px; resize: none"></textarea>
        </div>
        <button type="submit" class="btn btn-primary">Submit</button>
    </form>
</div>

<%@ include file="bootstrap-scripts.jsp" %>
</body>
</html>