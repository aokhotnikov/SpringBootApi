$(document).ready(function () {

    drawMenu();

    $(".main").on('click',function () {
        $("#tovars").html('');
        $(".characters").html('');
    });

});

function drawMenu() {
    $.ajax({
        url: 'http://localhost:8080/categories',
        type: "GET",
        dataType: "json",
        success: function (arrayCategories) {
            if (!arrayCategories) {
                alert("ARRAY OF CATEGORIES IS NULL");
            } else {
                for (let i = 0; i < arrayCategories.length; i++) {

                    let li = `<li id="menu-${arrayCategories[i].id}">
                                <a href="#" onclick="getTovarsByCategory(${arrayCategories[i].id})">${arrayCategories[i].name}</a>
                              </li>`;
                    if (arrayCategories[i].parent_id == 0) {
                        $(".nav").append(li);
                    }
                    else {
                        let parent_li = "#menu-" + arrayCategories[i].parent_id;
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
            }
        }
    });
}

function getTovarsByCategory(id) {

    //get characters
    drawCategoryCharacters(id);

    //get tovars
    drawTovarsList(id);

}

function drawCategoryCharacters(id) {
    $.ajax({
        url: 'http://localhost:8080/category/' + id + '/characters',
        type: "GET",
        dataType: "json",
        success: function (arrayCharacters) {
            if (!arrayCharacters) {
                alert("ARRAY OF CHARACTERS IS NULL");
            }else {
                $(".characters").html(`
                    <div id="characters"></div>
                    <div class="filter">
                        <button type="button" onclick="getTovarsByValues(${id});" class="filter btn btn-success">Фильтр</button>
                    </div>
                `);
                arrayCharacters.forEach(function(item) {
                    let char_id = item.id;
                    getCharacterValues(char_id).then(   //Promise
                        response => {
                            $('#characters').append(`
                                <div>${item.name}:</div>
                                <div>
                                    <select class="nameChar-${char_id}">
                                        <option value="all">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</option>
                                    </select>
                                </div>
                            `);
                            response.forEach(function (item) {
                                $('.nameChar-' + char_id).append(`<option value="${item.id}">${item.name}</option>`);
                            });
                        },
                        error => alert(`Ошибка: ${error}`)
                    );
                });
             }
        }
    });
}

function drawTovarsList(id, values = '') {
    var characterValues = '';
    if (values !== '') {
        characterValues = '?values=' + values;
    }
    $.ajax({
        url: 'http://localhost:8080/category/' + id + '/tovars' + characterValues,
        type: "GET",
        dataType: "json",
        success: function (arrayTovars) {
            if (!arrayTovars) {
                alert("ARRAY OF TOVARS IS NULL");
            } else {
                $('#tovars').html('');
                for (let i = 0; i < arrayTovars.length; i++) {
                    $('#tovars').append(`
                        <a onclick="getTovar(${arrayTovars[i].id})" href="#"><h3>${arrayTovars[i].name}</h3></a>
                        <p><b>Наличие:</b> ${arrayTovars[i].available ? 'ДА' : 'НЕТ'}</p>
                        <p><b>Стоимость:</b> ${arrayTovars[i].price} грн</p>
                        <p><b>Гарантия:</b> ${arrayTovars[i].garanty} года</p>
                        <hr>
                    `);
                }
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

function getTovarsByValues(id) {

    $("#tovars").html('');// clear div
    let values = '';
    $('select').each(function() {
        if ($(this).val() !== 'all') {
            if (values === '')
                values += $(this).val();
            else {
                values += ',' + $(this).val();
            }
        }
    });

    drawTovarsList(id, values);
}

function getTovar(id) {
    //$(".characters").html(''); // clear div
    $.ajax({
        url: 'http://localhost:8080/tovar/' + id,
        type: "GET",
        dataType: "json",
        success: function (tovar) {
            if (!tovar) {
                alert("OBJECT TOVAR IS NULL");
            } else {
                $('#tovars').html(`
                    <h3 align="center">${tovar.name}</h3>
                    <p><b>Наличие:</b> ${tovar.available ? "ДА" : "НЕТ"}</p>
                    <p><b>Стоимость:</b> ${tovar.price} грн</p>
                    <p><b>Гарантия:</b> ${tovar.garanty} года</p>
                    <p><i>Ответственный:</i> ${tovar.owner.name}</p>
                    <p align="center"><i>характеристики</i></p>
                `);

                tovar.values.forEach(function (item) {
                    $('#tovars').append(`
                        <p><b>${item.character.name}:</b> ${item.name}</p>
                    `);
                });
            }
        }
    });
}