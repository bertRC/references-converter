<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <%@ include file="css-collection.jsp" %>

    <style>
        <%@ include file="css/rc-style.css" %>
    </style>

    <title>Hello, world!</title>
</head>
<body>

<nav class="navbar navbar-expand-lg navbar-light bg-light sticky-top">
    <a class="navbar-brand" href="#">Navbar</a>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent"
            aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>

    <div class="collapse navbar-collapse" id="navbarSupportedContent">
        <ul class="navbar-nav mr-auto">
            <li class="nav-item active">
                <a class="nav-link" href="#">Home <span class="sr-only">(current)</span></a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="#">Link</a>
            </li>
            <li class="nav-item dropdown">
                <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button"
                   data-toggle="dropdown"
                   aria-haspopup="true" aria-expanded="false">
                    Dropdown
                </a>
                <div class="dropdown-menu" aria-labelledby="navbarDropdown">
                    <a class="dropdown-item" href="#">Action</a>
                    <a class="dropdown-item" href="#">Another action</a>
                    <div class="dropdown-divider"></div>
                    <a class="dropdown-item" href="#">Something else here</a>
                </div>
            </li>
            <li class="nav-item">
                <a class="nav-link disabled" href="#" tabindex="-1" aria-disabled="true">Disabled</a>
            </li>
        </ul>
        <form class="form-inline my-2 my-lg-0">
            <input class="form-control mr-sm-2" type="search" placeholder="Search" aria-label="Search">
            <button class="btn btn-outline-success my-2 my-sm-0" type="submit">Search</button>
        </form>
    </div>
</nav>

<div class="container-fluid">

    <div class="row">
        <div class="col-2">
            <div style="position: sticky; top: 56px">
                <h1>Hello World!!!</h1>
<%--                <h2 id="myElement"><%= (String) request.getAttribute("someText") %></h2>--%>
                                <h2 id="myElement">${someText}</h2>
            </div>
        </div>
        <div class="col">
            <table class="table table-hover table-bordered table-sm table-fixed" id="myTable">
                <thead class="thead-light">
                <tr>
                    <th scope="col">#</th>
                    <th scope="col">First</th>
                    <th scope="col">Last</th>
                </tr>
                </thead>
                <tbody>
                <tr>
                    <td>1</td>
                    <td>Mark</td>
                    <td>Otto</td>
                </tr>
                <tr>
                    <td>2</td>
                    <td>Jacob</td>
                    <td><span style="background-color: red">Thorn</span>ton</td>
                </tr>
                <tr>
                    <td>3</td>
                    <td>Larry the Bird</td>
                    <td>Twitter</td>
                </tr>
                <tr>
                    <td>1</td>
                    <td>Mark</td>
                    <td>Otto</td>
                </tr>
                <tr>
                    <td>2</td>
                    <td>Jacob</td>
                    <td>Thornton</td>
                </tr>
                <tr>
                    <td>3</td>
                    <td>Larry the Bird</td>
                    <td>Twitter</td>
                </tr>
                <tr>
                    <td>1</td>
                    <td>Mark</td>
                    <td>Otto</td>
                </tr>
                <tr>
                    <td>2</td>
                    <td>Jacob</td>
                    <td>Thornton</td>
                </tr>
                <tr>
                    <td>3</td>
                    <td>Larry the Bird</td>
                    <td>Twitter</td>
                </tr>
                <tr>
                    <td>1</td>
                    <td>Mark</td>
                    <td>Otto</td>
                </tr>
                <tr>
                    <td>2</td>
                    <td>Jacob</td>
                    <td>Thornton</td>
                </tr>
                <tr>
                    <td>3</td>
                    <td>Larry the Bird</td>
                    <td>Twitter</td>
                </tr>
                <tr>
                    <td>1</td>
                    <td>Mark</td>
                    <td>Otto</td>
                </tr>
                <tr>
                    <td>2</td>
                    <td>Jacob</td>
                    <td>Thornton</td>
                </tr>
                <tr>
                    <td>3</td>
                    <td>Larry the Bird</td>
                    <td>Twitter</td>
                </tr>
                <tr>
                    <td>1</td>
                    <td>Mark</td>
                    <td>Otto</td>
                </tr>
                <tr>
                    <td>2</td>
                    <td>Jacob</td>
                    <td>Thornton</td>
                </tr>
                <tr>
                    <td>3</td>
                    <td>Larry the Bird</td>
                    <td>Twitter</td>
                </tr>
                <tr>
                    <td>1</td>
                    <td>Mark</td>
                    <td>Otto</td>
                </tr>
                <tr>
                    <td>2</td>
                    <td>Jacob</td>
                    <td>Thornton</td>
                </tr>
                <tr>
                    <td>3</td>
                    <td>Larry the Bird</td>
                    <td>Twitter</td>
                </tr>
                <tr>
                    <td>1</td>
                    <td>Mark</td>
                    <td>Otto</td>
                </tr>
                <tr>
                    <td>2</td>
                    <td>Jacob</td>
                    <td>Thornton</td>
                </tr>
                <tr>
                    <td>3</td>
                    <td>Larry the Bird</td>
                    <td>Twitter</td>
                </tr>
                <tr>
                    <td>1</td>
                    <td>Mark</td>
                    <td>Otto</td>
                </tr>
                <tr>
                    <td>2</td>
                    <td>Jacob</td>
                    <td>Thornton</td>
                </tr>
                <tr>
                    <td>3</td>
                    <td>Larry the Bird</td>
                    <td>Twitter</td>
                </tr>
                <tr>
                    <td>1</td>
                    <td>Mark</td>
                    <td>Otto</td>
                </tr>
                <tr>
                    <td>2</td>
                    <td>Jacob</td>
                    <td>Thornton</td>
                </tr>
                <tr>
                    <td>3</td>
                    <td>Larry the Bird</td>
                    <td>Twitter</td>
                </tr>
                <tr>
                    <td>1</td>
                    <td>Mark</td>
                    <td>Otto</td>
                </tr>
                <tr>
                    <td>2</td>
                    <td>Jacob</td>
                    <td>Thornton</td>
                </tr>
                <tr>
                    <td>3</td>
                    <td>Larry the Bird</td>
                    <td>Twitter</td>
                </tr>
                <tr>
                    <td>1</td>
                    <td>Mark</td>
                    <td>Otto</td>
                </tr>
                <tr>
                    <td>2</td>
                    <td>Jacob</td>
                    <td>Thornton</td>
                </tr>
                <tr>
                    <td>3</td>
                    <td>Larry the Bird</td>
                    <td>Twitter</td>
                </tr>
                <tr>
                    <td>1</td>
                    <td>Mark</td>
                    <td>Otto</td>
                </tr>
                <tr>
                    <td>2</td>
                    <td>Jacob</td>
                    <td>Thornton</td>
                </tr>
                <tr>
                    <td>3</td>
                    <td>Larry the Bird</td>
                    <td>Twitter</td>
                </tr>
                </tbody>
            </table>
        </div>
    </div>

</div>

<%@ include file="bootstrap-scripts.jsp" %>

<script>
    <%@include file="js/myScript.js" %>
</script>

</body>
</html>