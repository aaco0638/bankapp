SWAGGER
localhost:<port>/swagger-ui.html
users Service Endpoints
Endpoint: POST /users/register
Request Body:
{
"username": "tchico",
"password": "password",
}

Login users
Endpoint: POST /users/login
Request Body:
{
"username": "tchico",
"password": "password"
}
Get users Details
Endpoint: GET /users/{id}

Account Service Endpoints
Create Account
Endpoint: POST /accounts
{
"account_user_id": 1,
"account_number": "1234567890",
"account_type": "Savings",
"balance": 1000.00
}
Get Account Details
Endpoint: GET /accounts/{account_number}
Delete Account
Endpoint: DELETE /accounts/{account_number}

Transaction Service Endpoints
Create Transaction (Deposit)
Endpoint: POST /transactions/deposit
Request Body:
{
"from_account_id": null,
"to_account_id": 1,
"amount": 500.00,
"transaction_type": "DEPOSIT"
}
Create Transaction (Withdraw)
Endpoint: POST /transactions/withdraw
Request Body:
{
"from_account_id": 1,
"to_account_id": null,
"amount": 200.00,
"transaction_type": "WITHDRAWAL"
}
Create Transaction (Transfer)
Endpoint: POST /transactions/transfer
Request Body:
{
"from_account_id": 1,
"to_account_id": 2,
"amount": 100.00,
"transaction_type": "TRANSFER"
}
Get All Transactions
Endpoint: GET /transactions

Summary of all the endpoints.
POST /users/register
POST /users/login
GET /users/{id}
Account Service:

POST /accounts
GET /accounts/{account_number}
DELETE /accounts/{account_number}
Transaction Service:

POST /transactions/deposit
POST /transactions/withdraw
POST /transactions/transfer
GET /transactions