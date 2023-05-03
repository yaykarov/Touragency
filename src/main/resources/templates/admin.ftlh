<!DOCTYPE html>
<html>
<head>
    <title>Touragency</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">
</head>
<body>
<nav class="navbar navbar-expand-lg navbar-light bg-light">
  <a class="navbar-brand" href="/">Touragency</a>
  <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
    <span class="navbar-toggler-icon"></span>
  </button>
  <div class="collapse navbar-collapse" id="navbarNav">
    <ul class="navbar-nav">
      <li class="nav-item">
        <a class="nav-link" href="/">Home</a>
      </li>
    </ul>
  </div>
</nav>

<hr>
<h4>Панель администратора</h4>
<table class="table table-bordered">
    <thead>
        <tr>
            <th>Email</th>
            <th>Активность</th>
            <th>Роли</th>
            <th>Бан</th>
            <th>Редактирование</th>
            <th>Подробная информация</th>
        </tr>
    </thead>
    <tbody>
        <#list users as user>
            <tr>
                <td>${user.email}</td>
                <td><#if user.active>true<#else>false</#if></td>
                <td><#list user.roles as role>${role}</#list></td>
                <td>
                    <form action="/admin/user/ban/${user.id}" method="post">
                        <input type="hidden" name="_csrf" value="${_csrf.token}">
                        <input type="submit" class="btn btn-danger btn-sm" value="Бан">
                    </form>
                </td>
                <td><a href="/admin/user/edit/${user.id}" class="btn btn-info btn-sm">Редактирование</a></td>
                <td><a href="/user/${user.id}" class="btn btn-primary btn-sm">Подробная информация</a></td>
            </tr>
        <#else>
            <tr>
                <td colspan="6"><h3>Пользователей нет</h3></td>
            </tr>
        </#list>
    </tbody>
</table>
<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
</body>
</html>
