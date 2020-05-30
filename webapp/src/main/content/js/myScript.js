$('tr').click(function () {
    var row_index = $(this).index();
    console.log(row_index);
    // alert(row_index);
});

const myConst = 'AAA';
$(document).ready(doWork());

function doWork() {
    $.get('/scriptHandler', {scriptToServlet: myConst}, function (resp) {
        // console.log(resp);
        $('#myElement').text(resp);
    })
    setTimeout(doWork, 1000);
}
