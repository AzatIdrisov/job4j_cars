<%@ page import="ru.job4j.model.User" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<!doctype html>
<html lang="en">
<head>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <!-- Bootstrap CSS -->
    <script src="https://code.jquery.com/jquery-3.4.1.slim.min.js"
            integrity="sha384-J6qa4849blE2+poT4WnyKhv5vZF5SrPo0iEjwBvKU7imGFAV0wwj1yYfoRSJoZ+n"
            crossorigin="anonymous"></script>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"
          integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"
            integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo"
            crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"
            integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo"
            crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"
            integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6"
            crossorigin="anonymous"></script>
    <script type="text/javascript" src="js/main.js"></script>

    <title>Cars</title>
</head>
<body>
<%
    List<User> usersList = (List<User>) request.getSession().getAttribute("user");
%>
<div class="container">
    <div class="row">
        <ul class="nav">
            <% if (usersList == null) { %>
            <li class="nav-item">
                <a class="nav-link" href="<%=request.getContextPath()%>/login.do">Войти</a>
            </li>
            <% } else { %>
            <li class="nav-item">
                <a class="nav-link" href="<%=request.getContextPath()%>/login.do"><%=usersList.get(0).getName()%> | Выйти</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="<%=request.getContextPath()%>/newPost.jsp">Добавить объявление</a>
            </li>
            <% } %>
        </ul>
    </div>
    <table id='table' class="table">
        <thead>
        <tr>
            <th>Марка</th>
            <th>Кузов</th>
            <th>Описание </th>
            <th>Статус</th>
            <th>Фото</th>
        </tr>
        </thead>
        <tbody id="table_body">
        </tbody>
    </table>
</div>
</body>
</html>
