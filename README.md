# money_transfer_api
## Technology stack
  Java 8  
  Gradle  
  Javalin  
  Dagger   
  In-memory Data store  
  JUnit 4  
## Endpoint
Transfer Money  
curl -X POST \
  http://localhost:7000/transaction \
  -F senderAccountNumber=1111 \
  -F receiverAccountNumber=3333 \
  -F amount=10.00 \
