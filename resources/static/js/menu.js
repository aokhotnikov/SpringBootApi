function getTovarsFromCategory(id) {
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
                        <!--<p align="center"><i>характеристики</i></p>-->
                    `);
                    // data[i].values.forEach(function(item) {
                    //     $('.tovar').append(`<p><b>${item.character.name}:</b> ${item.name}</p>`);
                    // });
                }
            }else{
                alert("NULL");
            }
        }
    });
}

function getTovar(id) {
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
                        <a onclick="getTovar(${data.id})" href="#"><h3>${data.name}</h3></a>
                        <p><b>Наличие:</b> ${data.available ? "ДА" : "НЕТ"}</p>
                        <p><b>Стоимость:</b> ${data.price} грн</p>
                        <p><b>Гарантия:</b> ${data.garanty} года</p>
                        <p align="center"><i>характеристики</i></p>
                    `);
                    data.values.forEach(function(item) {
                        $('.tovar').append(`<p><b>${item.character.name}:</b> ${item.name}</p>`);
                    });

            }else{
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
                if (response){
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
                                $(parent_li).html(`<a class="dropdown-toggle" data-toggle="dropdown" href="#">${parentName}<span class="caret"></span></a>
                                              <ul class="dropdown-menu">
                                                    ${li}
                                              </ul>`);
                            }
                        }
                    }
                }else{
                    alert("NULL");
                }
            }
        });
    }
    drawMenu();

});
