<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <%@ include file="bootstrap-css.jsp" %>
    <title>References Converter</title>
</head>
<body>

<div class="container">
    <form method="POST" action="<%= request.getContextPath() %>" accept-charset="UTF-8">
        <div class="form-group">
            <label for="exampleFormControlTextarea1">Example textarea</label>
            <textarea class="form-control" id="exampleFormControlTextarea1" name="text" rows="30"></textarea>
        </div>
        <button type="submit" class="btn btn-primary">Submit</button>
    </form>
</div>

<%@ include file="bootstrap-scripts.jsp" %>
</body>
</html>