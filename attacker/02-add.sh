#!/bin/sh
set -x
curl -H 'Content-Type:application/json' -i -X POST -d '{"customerName":"Chetan","accountType":"Checking", "accountNumber" : "12345679", "balance" : 10000, "accountNotes":["java.util.HashMap",{"randomNotes":"BlahBlah"}]}' http://localhost:4040/accounts; echo
