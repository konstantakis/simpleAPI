$(document).ready(function(){

    $( "#createAccount" ).submit(function( event ) {

        var values = {};
        $.each($('#createAccount').serializeArray(), function(i, field) {
            values[field.name] = field.value;
        });
        console.log(values);

        //send request
        event.preventDefault();
    });

});