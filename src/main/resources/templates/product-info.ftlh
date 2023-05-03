
<!DOCTYPE html>
<html>
<head>
    <title>TourAgency</title>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
</head>
<body>
    <div class="container">
        <h1>TourAgency</h1>
        <hr>
        <h4>Подробная информация о товаре</h4>
        <div class="card">
            <div class="card-body">
                <h5 class="card-title">${product.title}</h5>
                <p class="card-text">${product.description}</p>
                <p class="card-text">Цена: ${product.price} руб.</p>
                <p class="card-text">Город: ${product.city}</p>
                <p class="card-text">Автор: <a href="/user/${product.user.id}">${product.user.name}</a></p>

                <a href="/product/edit/${product.id}" class="btn btn-primary">Редактировать</a>
                <a href="/product/delete/${product.id}" class="btn btn-danger">Удалить</a>

            </div>
        </div>
    </div>
</body>
</html>