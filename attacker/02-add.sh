#!/bin/sh
set -x
curl -H 'Content-Type:application/json' -i -X POST -d '{"customerName":"Jarkko","accountType":"Checking", "accountNumber" : "12345679", "balance" : 10000, "accountNotes":["java.util.HashMap",{"randomNotes":"BlahBlah"}]}' http://10.48.18.26:8080/accounts; echo
