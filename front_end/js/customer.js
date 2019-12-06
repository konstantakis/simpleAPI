$(document).ready(function(){
    var customerId = getUrlParameter('id');
    if(customerId == undefined || customerId == ""){
        window.location.replace("./index.html")
    }

    $.ajax({
        url: serviceURL + "customer/" + customerId,
        type: 'GET',
        success: fillData
    });
});

function fillData(res){
    var json = JSON.parse(res); 
    console.log(json)
    $('#customerID').text(json.customerId);
    $('#fullname').text(json.name + " " + json.surname);
    $('#totalBalance').text(json.totalBalance);
    var $emptyAccountDetails = $('.accountDetails').clone();
    var $accountDetails = $('.accountDetails');

    for (i = 0; i < json.accounts.length; i++) {
        var accountJSON = json.accounts[i]
        $accountDetails.find('.accountID').text(accountJSON.accountId);
        $accountDetails.find('.accountBalance').html(accountJSON.balance);

        var $emptyTransaction =  $accountDetails.find('.transactionDetails').clone();
        var $transaction =  $accountDetails.find('.transactionDetails');

        for (j = 0; j < accountJSON.transactions.length; j++) {
            var transactionJSON = accountJSON.transactions[j]
            $transaction.find('.id').text(transactionJSON.transactionID);
            $transaction.find('.timestamp').text(transactionJSON.timestamp);
            $transaction.find('.type').text(transactionJSON.type);
            $transaction.find('.amound').text(transactionJSON.amound);
            switch (transactionJSON.type) {
                case "DEPOSIT":
                    $transaction.find('.amound').text("+" + transactionJSON.amound);
                    $transaction.find('.details').text("-");
                    break;
                case "WITHDRAW":
                    $transaction.find('.amound').text("-" + transactionJSON.amound);
                    $transaction.find('.details').text("-");
                    break;
                case "TRANSFER":
                    if (transactionJSON.fromAccountId == accountJSON.accountId) {
                        $transaction.find('.amound').text("-" + transactionJSON.amound);
                        $transaction.find('.details').text("to account id: " + transactionJSON.toAccountId);
                    }else if (transactionJSON.toAccountId == accountJSON.accountId) {
                        $transaction.find('.amound').text("+" + transactionJSON.amound);
                        $transaction.find('.details').text("from account id: " + transactionJSON.fromAccountId);
                    }
                    break;
                default:
                    $transaction.find('.amound').text(transactionJSON.amound);
                    $transaction.find('.details').text("-");
                    break;
            }
            
            if (j + 1 != accountJSON.transactions.length) {
                $transaction = $emptyTransaction;
                $emptyTransaction = $transaction.clone();
                $('#transactions').append($transaction);
                
            }
        }
        if (i + 1 != json.accounts.length) {
            $accountDetails = $emptyAccountDetails;
            $emptyAccountDetails = $accountDetails.clone();
            $('#accountsContainer').append($accountDetails);

        }
    }

    
}

/** TODO
 * remove init values
 * do details
 * on error redirect
 * amound with prefix - or +
 * 
 *  
 * */  

