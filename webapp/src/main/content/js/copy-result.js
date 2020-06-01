function copyFunction() {
    $.get('/copyResult', function (resp) {
        if (resp) {
            navigator.clipboard.writeText(resp);
        }
    })
}
