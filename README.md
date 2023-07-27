# Expensify Backend

This serves as the backend for my [Expensify web app](https://github.com/nhan0504/expensify-frontend). It exposes a set of API endpoints that the frontend uses to interact with the database. It also verifies user credentials and authorize if a user is allowed to access an API endpoint.

# Installation
1. Clone the GitHub repository to your local machine using the following command:
    ```bash
    git clone https://github.com/nhan0504/expensify-backend.git
    ```
2. Navigate to the cloned repository:
   ```
   cd expensify-backend
   ```
3. Create a .env file in the root directory with your PostgreSQL credential and ALLOWED_ORIGIN with the value of the URL of the frontend
   ```
   DB_PORT=<port>
   DB_NAME=<database_name>
   DB_USERNAME=<username>
   DB_PASSWORD=<password>

   ALLOWED_ORIGIN=http://127.0.0.1:3000
   ``` 
4. Ensure JDK is installed and run the project which will be defaut on port 8080
   ```
   mvn spring-boot:run
   ```

# API endpoints
- [Authenticate a user](#authenticate-a-user)
- [Get all expenses for an employee](#get-all-expenses-for-an-employee)
- [Get all employees detail](#get-all-employees-detail)
- [Add a new expenses for an employee](#add-a-new-expenses-for-an-employee)
- [Delete an expense for an employee](#delete-an-expense-for-an-employee)
- [Update the status of an expense](#update-the-status-of-an-expense)


## Authenticate a user
Send a POST request to the URL
```
http://127.0.0.1:8080/login
```
with the user credential in a login form

## Get all expenses for an employee

```
http://127.0.0.1:8080/employees/{employee_id}/expenses
```
Calling this API endpoint will return a list of expenses for the employee with employee_id.
```json
[
    {
        "id": 1,
        "merchant": "merchant_1",
        "description": "description_1",
        "amount": 123.45,
        "status": {
            "state": "IN_REVIEW",
            "comment": null,
            "reviewed_by": null,
            "review_date": null
        },
        "purchase_date": "2023-06-06"
    }
]
```

## Get all employees detail
```
http://127.0.0.1:8080/employees
```
This will list all the employee related detail for all employees
```json
[
    {
        "id": 1,
        "username": "employee1",
        "role": "ROLE_EMPLOYEE",
        "expenses": [
            {
                "id": 1,
                "merchant": "merchant_1",
                "description": "description_1",
                "amount": 123.45,
                "status": {
                    "state": "IN_REVIEW",
                    "comment": null,
                    "reviewed_by": null,
                    "review_date": null
                },
                "purchase_date": "2023-06-06"
            }
        ]
    },
    {
        "id": 2,
        "username": "employee2",
        "role": "ROLE_EMPLOYEE",
        "expenses": []
    }
]
```
## Add a new expenses for an employee
Send a POST request to the URL
```
http://127.0.0.1:8080/employees/{employee_id}/expenses
```
with the body
```json
{
    "merchant": "merchant",
    "description": "description",
    "purchase_date": "2023-06-12",
    "amount": "123"
}
```

## Delete an expense for an employee
```
http://127.0.0.1:8080/employees/{employee_id}/expenses/{expense_id}
```

## Update the status of an expense
Send a PUT request to the URL
```
http://127.0.0.1:8080/expenses/{expense_id}/status
```
with the body
```json
{
    "state": "APPROVED",
    "reviewed_by": "reviewer",
    "review_date": "2023-06-13",
    "comment": "Reviewed"
}
```