$(document).ready(
    $.ajax({
        type: 'GET',
        url: 'http://localhost:8080/cars/car',
        contentType: 'application/json',
        dataType: 'json'
    })
        .done(function (response) {
            let content = "";
            let carMarks = response.carMarks;
            console.log(carMarks);
            content += '<div className = "form-group"><label> Марка </label> <select name="car_mark" id="car_mark" className="form-control">';
            content += '<option selected value="empty">Empty</option>';
            for (let i = 0; i < carMarks.length; i++) {
                content += "<option value=\"" + carMarks[i].carMark + "\">" + carMarks[i].carMark + "</option>"
                console.log(carMarks[i].carMark);
            }
            content += '</select></div>';
            let carBodies = response.carBodies;
            content += '<div className = "form-group"><label> Кузов </label> <select name="car_body" id="car_body" className="form-control">';
            content += '<option selected value="empty">Empty</option>';
            for (let i = 0; i < carBodies.length; i++) {
                content += "<option value=\"" + carBodies[i].carBody + "\">" + carBodies[i].carBody + "</option>"
            }
            content += '</select></div>';
            content += '<div className="form-group"> <label htmlFor="desc">Описание</label> ' +
                '<input name="desc" className="form-control" id="desc"> </div>';
            content += '<button type="submit" class="btn btn-primary" className="btn btn-primary">Сохранить</button>';
            $('#car').html(content);
        })
        .fail(function (err) {
            alert(err);
            alert('Error');
        }))

