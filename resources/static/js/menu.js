function drawTovarsList(id) {
    $.ajax({
        url: 'http://localhost:8080/category/' + id + '/tovars',
        type: "GET",
        dataType: "json",
        success: function (response) {
            if (response) {
                $("#tovars").html(`
                    <div class="row">
                        <div class="tovar"></div>
                    </div>
                `);
                data = response;
                for (let i = 0; i < data.length; i++) {
                    let tovar = "tovar" + data[i].id;
                    $('.tovar').append(`
                        <hr>
                        <a onclick="getTovar(${data[i].id})" href="#"><h3>${data[i].name}</h3></a>
                        <p><b>Наличие:</b> ${data[i].available ? "ДА" : "НЕТ"}</p>
                        <p><b>Стоимость:</b> ${data[i].price} грн</p>
                        <p><b>Гарантия:</b> ${data[i].garanty} года</p>
                    `);
                }
            } else {
                alert("NULL");
            }
        }
    });
}

function drawCategoryCharacters(id) {
    $.ajax({
        url: 'http://localhost:8080/category/' + id + '/characters',
        type: "GET",
        dataType: "json",
        success: function (response) {
            if (response) {
                $("#characters").html(`
                    <div class="row"><br>
                        <div class="character"></div>
                    </div>
                `);
                response.forEach(function(item) {
                    let char_id = item.id;
                    getCharacterValues(char_id).then(   //Promise
                        response => {
                            $('.character').append(`
                                <div class="col-sm-3">${item.name}:</div>
                                <div class="col-sm-3">
                                    <select class="nameChar-${char_id}">
                                        <option value="all">Все</option>
                                    </select>
                                </div>
                            `);
                            $('.nameChar-' + char_id).on('change', function () {
                                alert("Xyu");
                            });
                            response.forEach(function (item) {
                                $('.nameChar-' + char_id).append(`<option value="${item.id}">${item.name}</option>`);
                            });
                        },
                        error => alert(`Ошибка: ${error}`)
                    );
                });
            }else {
                alert("NULL");
            }
        }
    });
}

function getCharacterValues(id) {
    return new Promise(function(resolve, reject) {
        $.ajax({
            url: 'http://localhost:8080/character/' + id + '/values',
            type: "GET",
            dataType: "json",
            success: function (response) {
                response ? resolve(response) : reject(new Error("Network Error"));
            }
        });
    });
}


function getTovarsFromCategory(id) {

    //get characters
    drawCategoryCharacters(id);

    //get tovars
    drawTovarsList(id);

}

function getTovar(id) {
    $("#characters").html('');
    $.ajax({
        url: 'http://localhost:8080/tovar/' + id,
        type: "GET",
        dataType: "json",
        success: function (response) {
            if (response) {
                $("#tovars").html(`
                    <div class="row">
                        <div class="tovar"></div>
                    </div>
                `);
                data = response;

                let tovar = "tovar" + data.id;
                $('.tovar').append(`
                    <hr>
                    <h3 align="center">${data.name}</h3>
                    <p><b>Наличие:</b> ${data.available ? "ДА" : "НЕТ"}</p>
                    <p><b>Стоимость:</b> ${data.price} грн</p>
                    <p><b>Гарантия:</b> ${data.garanty} года</p>
                    <p align="center"><i>характеристики</i></p>
                `);
                data.values.forEach(function (item) {
                    $('.tovar').append(`<p><b>${item.character.name}:</b> ${item.name}</p>`);
                });

            } else {
                alert("NULL");
            }
        }
    });
}


$(document).ready(function () {

    function drawMenu() {
        $.ajax({
            url: 'http://localhost:8080/categories',
            type: "GET",
            dataType: "json",
            success: function (response) {
                if (response) {
                    data = response;
                    for (let i = 0; i < data.length; i++) {

                        let li = '<li id="menu-' + data[i].id + '"><a href="#" onclick="getTovarsFromCategory(' + data[i].id + ');">' + data[i].name + '</a></li>';

                        if (data[i].parent_id == 0)
                            $(".nav").append(li);
                        else {
                            let parent_li = "#menu-" + data[i].parent_id;
                            $(parent_li).addClass("dropdown");
                            if($(parent_li + " a").attr('data-toggle') !== undefined) { //уже есть хотя бы 1 подменю
                                $(parent_li + " ul").append(li);
                            }
                            else{
                                let parentName = $(parent_li + " a").html();
                                $(parent_li).html(`
                                    <a class="dropdown-toggle" data-toggle="dropdown" href="#">${parentName}<span class="caret"></span></a>
                                    <ul class="dropdown-menu">
                                        ${li}
                                    </ul>
                                `);
                            }
                        }
                    }
                    $(".dropdown").bind('click',function () { //подсветка меню
                        $(".dropdown").removeClass("active");
                        $(this).addClass("active");
                    });
                } else {
                    alert("NULL");
                }
            }
        });
    }

    drawMenu();

    $(".main").on('click',function () {
        $("#tovars").html('');
        $("#characters").html('');
    });

});
