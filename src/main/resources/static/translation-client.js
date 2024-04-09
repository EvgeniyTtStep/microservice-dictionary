function updateStats(nickname) {
    $.ajax({
        url: "http://localhost:8080/results?nickname=" + nickname
    }).then(function(data) {
        $('#stats-body').empty();
        data.forEach(function(row) {
            $('#stats-body').append('<tr><td>' + row.id +
                '</td>' +
                '<td>' + row.dictionary.word + '</td>' +
                '<td>' + row.resultAttempt + '</td>' +
                '<td>' + (row.correct === true ? 'YES' : 'NO')
                + '</td></tr>');
        });
    });
}





function updateWord() {
    $.ajax({
        url: "http://localhost:8080/translations/random"
    }).then(function(word) {
        $("#attempt-form").find( "input[name='result-attempt']" ).val("");
        $("#attempt-form").find( "input[name='user-nickname']" ).val("");
        $('.dictionary-word').empty().append(word);
    });
}

$(document).ready(function() {
    updateWord();

    $("#attempt-form").submit(function( event ) {

        event.preventDefault();

        var w = $('.dictionary-word').text();
        var $form = $( this ),
            wordAttempt = $form.find( "input[name='result-attempt']" ).val(),
            userNickname = $form.find( "input[name='user-nickname']" ).val();

        var data = {
            user: { nickname: userNickname},
            dictionary:{word:w, translate:""},
            resultAttempt: wordAttempt
        };

        // Send the data using post
        $.ajax({
            url: '/results',
            type: 'POST',
            data: JSON.stringify(data),
            contentType: "application/json; charset=utf-8",
            dataType: "json",
            async: false,
            success: function(result){
                if(result.correct) {
                    $('.result-message').empty().append("Correct!");
                } else {
                    $('.result-message').empty().append("Wrong!");
                }
            }
        });
        updateWord();

        updateStats(userNickname);
    });
});