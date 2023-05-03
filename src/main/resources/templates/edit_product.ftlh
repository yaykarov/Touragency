<!DOCTYPE html>
<html>
<head>
    <title>Редактирование тура</title>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
</head>
<body>
    <div class="container">
        <h1 class="mt-5 mb-3">Редактирование тура</h1>
        <form method="post" action="/product/edit/${product.id}">
            <input type="hidden" name="_csrf" value="${_csrf.token}">
            <div class="form-group">
                <label for="title">Название:</label>
                <input type="text" class="form-control" id="title" name="title" value="${product.title}" required>
            </div>
            <div class="form-group">
                <label for="description">Описание:</label>
                 <input type="text" class="form-control" id=description" name="description" value="${product.description}" required>
            </div>
            <div class="form-group">
                <label for="price">Цена:</label>
                <input type="number" class="form-control" id="price" name="price" value="${product.price}" required>
            </div>
            <div class="form-group">
                <label for="city">Город:</label>
                 <input type="text" class="form-control" id="city" name="city" value="${product.city}" required>
            </div>
            <button type="submit" class="btn btn-primary">Сохранить</button>
        </form>
    </div>
</body>
</html>