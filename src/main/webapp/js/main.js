$(document).ready(
    $.ajax({
        type: 'GET',
        url: 'http://localhost:8080/cars/post.do',
        contentType: 'application/json',
        dataType: 'json',
        success: function (data) {
            let cars = data.cars;
            let content = "";
            for (let i = 0; i < cars.length; i++) {
                content += '<tr><td>' + cars[i].mark + '</td>';
                content += '<td>' + cars[i].body + '</td>';
                content += '<td>' + cars[i].desc + '</td>';
                if (cars[i].status === true) {
                    content += '<td> Продано' + '</td>';
                } else {
                    if (cars[i].author === data.curUser) {
                        content += '<td>';
                        content += '<form action="http://localhost:8080/cars/update.do" method="post">';
                        content += '<button type="submit" class="btn btn-primary" id="carId" name="carId", value="'
                            + cars[i].id +
                            '" className="btn btn-primary">В продаже | Изменить статус';
                        content += '</button>';
                        content += '</form>';
                    } else {
                        content += '<td> В продаже' + '</td>';
                    }

                }
                if (cars[i].photo === false) {
                    content += '<td><div className="card-body">';
                    content += '<form action="http://localhost:8080/cars/upload.jsp?id='
                        + cars[i].id
                        + '"method="post">';
                    content += '<button type="submit" class="btn btn-primary" className="btn btn-primary">Добавить фото</button>';
                    content += '</form></div></td>';
                } else {
                    content += '<td>';
                    content += '<img src="http://localhost:8080/cars/download?name='
                        + cars[i].id +
                        '.jpg" width="100px" height="100px"/>';
                    content += '</td>';
                }
                content += "</tr>";
            }
            $('#table_body').html(content);
        }
    })
)