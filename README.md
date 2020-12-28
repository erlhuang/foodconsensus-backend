# Food Consensus Backend 

Back-end for Revature Project 2: Food Consensus.
Team Members: Tyler Kim, Eric Huang, Macy McAnally, Karl Matthes, Tianyuan Deng

# Instructions 

I haven't figured out how to use environment variables in an application.properties,
so I've currently added it to the .gitignore and we'll have to add it manually. 
As a workaround for now, please check #back-end in the Discord for instructions & 
the application.properties file.

# Current Endpoints 

Base URL: localhost:8081

# GET /hello
Simple Hello World test endpoint

# GET /users
Gets all users in the database. Test endpoint.
>http://localhost:8081/users
```JSON
[
    {
        "id": 1,
        "name": "testuser",
        "password": "pass",
        "admin": false
    },
    {
        "id": 3,
        "name": "testUser7",
        "password": "user",
        "admin": false
    }
]
```
# GET /users/{id}
Gets a user by id in the database.
>http://localhost:8081/users/8
```JSON
[
    {
        "id": 8,
        "name": "testuser",
        "password": "pass",
        "admin": false
    }
]
```

# POST /users
To add a user to this POST request, a JSON object must be added in the body. 
>http://localhost:8081/users

Follow this example format: 
```JSON
{
    "name":"testuser2",
    "password":"pass"
}
```
# GET /choices
Get all choices in the database.
> http://localhost:8081/choices

Output:
```JSON
[
    {
        "id": 14,
        "proposal": "Burger King2222",
        "owner": {
            "id": 1,
            "name": "testuser",
            "password": "pass",
            "admin": false
        },
        "approval_status": "DECLINED"
    },
    {
        "id": 15,
        "proposal": "Burger King2222",
        "owner": {
            "id": 2,
            "name": "testUser7",
            "password": "user",
            "admin": false
        },
        "approval_status": "DECLINED"
    }
]
```

# POST /addChoices
Add a choice to this POST request, a JSON object must be added in the body.

Input:
> http://localhost:8081/addChoices

```JSON
{
    "proposal": "Burger King2222",
    "owner": {
        "id": 2,
        "name": "testuser",
        "password": "pass",
        "admin": false
        },
    "approval_status": "DECLINED"
}
```
Output:
```JSON
{
    "id": 15,
    "proposal": "Burger King2222",
    "owner": {
        "id": 2,
        "name": "testuser",
        "password": "pass",
        "admin": false
    },
    "approval_status": "DECLINED"
}
```