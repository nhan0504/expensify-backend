# Expensify-backend

This serves as the backend for my [Expensify web app](https://github.com/nhan0504/expensify-frontend). It exposes a set of API endpoints that the frontend uses to interact with the database. It also verifies user credentials and authorize of a user is allowed to access an API endpoint.

# Installation
1. Clone the GitHub repository to your local machine using the following command:
    ```bash
    git clone https://github.com/nhan0504/expensify-frontend.git
    ```
2. Navigate to the cloned repository:
   ```
    cd expensify-frontend
   ```
3. Ensure JDK is installed and run the project which will be defaut on port 8080
   ```
   mvn spring-boot:run
   ```

# Api endpoints
[Authenticate an user](##Authenticate-an-user)    
[Get all expenses for an employee](##Get-all-expenses-for-an-employee)    
[Get all employees detail](##Get-all-employees-detail)    
[Add a new expenses for an employee](##Add-a-new-expenses-for-an-employee)    
[Delete an expense for an employee](##Delete-an-expense-for-an-employee)   
[Update the status of an expense](##Update-the-status-of-an-expense)


## Authenticate an user
Send a POST request to the URL
```
http://localhost:8080/login
```
with the user credential in a login form

## Get all expenses for an employee

```
http://localhost:8080/employees/{employee_id}/expenses
```
Calling this API endpoint will return a list of expenses for the employee with employee_id.
```
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
http://localhost:8080/employees
```
This will list all the employee related detail for all employees
```
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
http://localhost:8080/employees/{employee_id}/expenses
```
with the body
```
{
    "merchant": "merchant",
    "description": "description",
    "purchase_date": "2023-06-12",
    "amount": "123"
}
```

## Delete an expense for an employee
```
http://localhost:8080/employees/{employee_id}/expenses/{expense_id}
```

## Update the status of an expense
Send a PUT request to the URL
```
http://localhost:8080/expenses/{expense_id}/status
```
with the body
```
{
    "state": "APPROVED",
    "reviewed_by": "reviewer",
    "review_date": "2023-06-13",
    "comment": "Reviewed"
}
```