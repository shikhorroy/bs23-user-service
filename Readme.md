### Required: Java 21

Please set Java 21 bin in environment variable.

### Step I: run the docker containers:

`docker compose up`

### Step II: run spring boot app

`gradlew.bat bootRun`

### user-service CURLs:

### Create

    curl --location 'http://localhost:8080/api/1.0.0/user' \
    --header 'Content-Type: application/json' \
    --data-raw '{
        "firstName": "Shikhor",
        "lastName": "Roy",
        "email": "shikhor@gmail.com"
    }'

### Read

    curl --location 'http://localhost:8080/api/1.0.0/user/id/1'

### Update

    curl --location --request PUT 'http://localhost:8080/api/1.0.0/user' \
    --header 'Content-Type: application/json' \
    --data-raw '{
        "id": 1,
        "firstName": "Shikhor",
        "lastName": "Roy 2",
        "email": "shikhor@gmail.com"
    }'

### Delete

    curl --location --request DELETE 'http://localhost:8080/api/1.0.0/user/id/1'