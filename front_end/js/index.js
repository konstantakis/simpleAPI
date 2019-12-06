var values = {};
$(document).ready(function(){

    $( "#createAccount" ).submit(function( event ) {

        $.each($('#createAccount').serializeArray(), function(i, field) {
            values[field.name] = field.value;
        });
        console.log(values);

        var url = serviceURL + "account?customerId=" + values.customerID + "&initialCredit=" + values.initialCredit 
        $.ajax({
            url: url,
            type: 'POST',
            success: postSuccess,
            error : postError
        });

        //send request
        event.preventDefault();
    });

});

function postSuccess(res){
    var resJSON = JSON.parse(res);
    window.location.replace("./account-created.html?customerId=" + values.customerID + "&balance=" + values.initialCredit + "&accountId=" + resJSON.accountId + "&transactionId=" + resJSON.transactionId)
}

function postError(xhr){
    console.log(xhr);
    $('#error_message').text(xhr.responseText);
    
}