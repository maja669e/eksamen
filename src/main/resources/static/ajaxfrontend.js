function getParish() {
    //Ajax request - this gets all the parishes
    $.ajax({
        url: "/parish",
        type: "GET",
        contentType: "application/JSON",
        success: function (data) {
            $.each(data, function (index, data) { //iterer over collection i data
                const html = `
                    <div id="parishId-${data.id}">
                        ${data.name} ${data.parishCode} ${data.infectionPressure} ${data.shutDownTime} 
                        <button class="btn btn-danger" onclick="parishDelete(${data.id})">
                            Delete
                         </button> 
                         
                          <button class="btn btn-info" onclick="location.href='/updateParish?id=${data.id}'">
                            Update
                         </button> 
                         
                         <input onclick="return false;" type="checkbox" id="checkBoxId" ${compareDate(data.shutDownTime) ? "checked" : ""}>
                     </div>
                     <hr>
                `
                $("#parishList").append(html)
            })
        },
    });
}

function parishDelete(parishid) {

    $.ajax({
        url: '/parish/' + parishid,
        type: 'DELETE',
        success: function () {
            $(`#parishId-${parishid}`).remove()
        }
    });
}

$("#create_parish").submit(function (e) {
    e.preventDefault(); // avoid to execute the actual submit of the form.
    let newParish = {
        parishCode: $('#parish_code').val(),
        name: $('#parish_name').val(),
        infectionPressure: $('#infection_pressure').val(),
        shutDownTime: $('#shutdown_time').val(),
        commune: parseInt($('#commune_id').val())
    };
    let json = JSON.stringify(newParish);
    $.ajax({
        type: "POST",
        url: "/parish",
        data: json,
        contentType: "application/json; charset=utf-8",
        dataType: "json",
        success: function (msg) {
            alert('Parish created!');
            location.href = "/parishes"
        }
    });

});


function compareDate(shutDownTime) {
    if (shutDownTime == null) {
        return false
    }return new Date() >= new Date(shutDownTime);
}

