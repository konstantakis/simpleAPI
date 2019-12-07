#!/usr/bin/env bash
URL="http://localhost:8090/"
echo '===================== Start testing Service ====================='
echo 'url:' $URL

echo '=============== Account Service, Post Request ==================='
echo $URL'account?customerId=cus0&initialCredit=50.0'

sleep 2
echo 'customeId = cus0, initialCredit = 50 
Expected status: success. 
Expected result: acountId & transactionId'
curl --data "customerId=cus0&initialCredit=50.0" $URL'account'
echo 
echo '------------------------------------------------'

sleep 2
echo 'customeId = cus0, initialCredit = 0 
Expected status: success. 
Expected result: acountId'
curl --data "customerId=cus0&initialCredit=0" $URL'account'
echo 
echo '------------------------------------------------'

sleep 2
echo 'initialCredit=10 
Expected status: fail (customer ID missing)
Expected result: BAD REQUEST'
curl --data "initialCredit=10" $URL'account'
echo 
echo '------------------------------------------------'

sleep 2
echo 'customeId = cus0 
Expected status: fail (initial credit missing) 
Expected result: BAD REQUEST'
curl --data "customerId=cus0" $URL'account'
echo 
echo '------------------------------------------------'

sleep 2
echo 'customeId = cus0 initialCredit = -34
Expected status: fail (initial credit < 0)
Expected result: BAD REQUEST'
curl --data "customerId=cus0&initialCredit = -34" $URL'account'
echo 
echo '------------------------------------------------'

sleep 2
echo 'customeId = cus893 initialCredit = 34
Expected status: fail (cus893 does not exist) 
Expected result: BAD REQUEST'
curl --data "customerId=cus893&initialCredit = 34" $URL'account'
echo 
echo '------------------------------------------------'

sleep 1
echo '=============== Deposit Service, Post Request ==================='
echo $URL'/transaction/deposit/acc2?amound=50.0'

sleep 2
echo 'accountId = acc2, amound = 50.0
Expected status: success. 
Expected result: transactionId'
curl --data "amound=50.0" $URL'/transaction/deposit/acc2'
echo 
echo '------------------------------------------------'

sleep 2
echo 'accountId = acc701654, amound = 13.0
Expected status: fail (acc106 does not exist) 
Expected result: BAD REQUEST'
curl --data "amound=50.0" $URL'/transaction/deposit/acc701654'
echo 
echo '------------------------------------------------'

sleep 2
echo 'amound = 13.0
Expected status: no account id to the url 
Expected result: 404'
curl --data "amound=50.0" $URL'/transaction/deposit'
echo 
echo '------------------------------------------------'

sleep 2
echo 'accountId = acc2
Expected status: fail (no amound var) 
Expected result: BAD REQUEST'
curl --data "" $URL'/transaction/deposit/acc2'
echo 
echo '------------------------------------------------'

sleep 2
echo 'accountId = acc2, amound=-1
Expected status: fail (amound < 0) 
Expected result: BAD REQUEST'
curl --data "amound=-51.0" $URL'/transaction/deposit/acc2'
echo 
echo '------------------------------------------------'

sleep 2
echo 'accountId = acc2, amound=abc
Expected status: fail (amound is string) 
Expected result: BAD REQUEST'
curl --data "amound=abc" $URL'/transaction/deposit/acc2'
echo 
echo '------------------------------------------------'

sleep 1
echo '=============== Customer Service, Get Request ==================='
echo $URL'/customer/cus0'

sleep 2
echo 'customer = cus0
Expected status: success. 
Expected result: customer data'
curl $URL'/customer/cus0'
echo 
echo '------------------------------------------------'

sleep 2
echo 'customer = cus4
Expected status: fail (cus4 does not exist) 
Expected result: customer not found'
curl $URL'/customer/cus4'
echo 
echo '------------------------------------------------'

sleep 2
echo 'customer = -
Expected status: fail (no customer id provided) 
Expected result: 404 url not found'
curl $URL'/customer'
echo 
echo '------------------------------------------------'