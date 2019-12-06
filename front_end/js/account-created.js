$(document).ready(function(){
    var customerId = getUrlParameter('customerId');
    var balance = getUrlParameter('balance');
    var accountId = getUrlParameter('accountId');
    var transactionId = getUrlParameter('transactionId');
    
    if (customerId == undefined || customerId == "" ){
        window.location.replace("./index.html");
    }

    var link = "./customer.html?id=" + customerId;
    if (balance == undefined || balance == ""  || balance == "undefined" ||
        accountId == undefined || accountId == ""  || accountId == "undefined") {
        
        window.location.replace(link);
    }

    $('#balance').text(balance);
    $('#accountId').text(accountId);

    if (transactionId == undefined || transactionId == "" || transactionId == "undefined"){
        $('#transaction').hide();
    }else{
        $('#transactionId').text(transactionId);

    }

    $("#profileLink").attr("href", link);

    setTimeout(function() { 
        window.location.replace(link);
    }, 5000);
    //settimeout
});
