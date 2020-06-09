<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <%@ include file="bootstrap-css.jsp" %>
    <style><%@ include file="css/rc-style.css" %></style>
    <title>References Converter</title>
</head>
<body>

<% String str = (String) request.getAttribute("coloredText"); %>
<table class="table table-sm table-hover">
    <thead>
    <tr>
        <th scope="col">#</th>
        <th scope="col">First</th>
    </tr>
    </thead>
    <tbody>
    <tr>
        <th scope="row">1</th>
        <td><%= str %></td>
    </tr>
    </tbody>
</table>

<%@ include file="bootstrap-scripts.jsp" %>
</body>
</html>