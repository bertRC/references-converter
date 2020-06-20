function copyFunction() {
    $.get('/copyResult', function (resp) {
        navigator.clipboard.writeText(resp);
    })
}
