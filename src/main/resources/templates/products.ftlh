<!DOCTYPE html>
<html>
<head>
    <title>Touragency</title>
    <!-- Add Bootstrap stylesheet -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
          integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
          crossorigin="anonymous">
</head>
<body>
<nav class="navbar navbar-expand-lg navbar-light bg-light">
  <a class="navbar-brand" href="#">Touragency</a>
  <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNav"
          aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
    <span class="navbar-toggler-icon"></span>
  </button>
  <div class="collapse navbar-collapse" id="navbarNav">
    <ul class="navbar-nav ml-auto">
      <li class="nav-item">
        <a class="nav-link" href="/">Home</a>
      </li>
      <li class="nav-item">
        <a class="nav-link" href="/products/author">About</a>
      </li>

    </ul>
  </div>
</nav>
<div class="container mt-3">
  <h1 class="mb-4">Touragency</h1>
  <hr>
  <#if user.email??>
    <div class="row">
      <div class="col-md-8">
        <h3>Имя пользователя: <i>${user.name}</i></h3>
      </div>
      <div class="col-md-4">
        <form action="/logout" method="post">
          <input type="hidden" name="_csrf" value="${_csrf.token}">
          <button class="btn btn-outline-primary" type="submit">Выйти</button>
        </form>
      </div>
    </div>
    <#if user.isAdmin()>
      <a href="/admin">Панель администратора</a>
    </#if>
  <#else>
    <a href="/login" class="btn btn-primary">Войти</a>
  </#if>
  <hr>
  <h4>Туры по всему миру</h4>
  <form action="/" method="get" class="form-inline my-3">
    <div class="form-group mr-3">
      <input type="text" class="form-control" name="title" placeholder="Поиск по названию объявления">
    </div>
    <button class="btn btn-primary" type="submit">Поиск</button>
  </form>
  <#list products as product>
    <div class="card mb-3">
      <div class="card-body">
        <h5 class="card-title">${product.title}</h5>
        <p class="card-text">${product.price} руб.</p>
        <a href="/product/${product.id}" class="btn btn-primary">Подробнее</a>
      </div>
    </div>
    </#list>

      <#if user.isAdmin()>
        <hr>
        <h3>Создать новый товар</h3>
        <form action="/product/create" method="post" enctype="multipart/form-data">
          <div class="form-group">
            <label for="title">Название объявления:</label>
            <input type="text" name="title" class="form-control" id="title" required>
          </div>
          <div class="form-group">
            <label for="description">Описание объявления:</label>
            <input type="text" name="description" class="form-control" id="description" required>
          </div>
          <div class="form-group">
            <label for="price">Цена:</label>
            <input type="number" name="price" class="form-control" id="price" required>
          </div>
          <div class="form-group">
            <label for="city">Город:</label>
            <input type="text" name="city" class="form-control" id="city" required>
          </div>
          <input type="hidden" name="_csrf" value="${_csrf.token}">
          <input type="submit" value="Добавить товар" class="btn btn-primary"/>
        </form>
      </#if>
    </div>
  </div>
</div>
</body>
</html>