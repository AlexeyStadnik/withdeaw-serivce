## Run process

`./gradlew build`    

`docker build -t withdraw-service .`     

`docker-compose -f src/main/resources/docker-compose.yml -f docker-compose.yml up `      


## Endpoints

- _/wallets/{walletId}/withdraw_ POST

Body example
```json
{
"requestId":"5e3a2144-0719-41a0-a288-5f4e2b676f12", 
"withdrawAmount":"10",
}
```

- _/wallets/{walletId}_ GET
