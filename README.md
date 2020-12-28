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

# GET /motion 
Gets and returns a motion from the database. Only displays motions you are invited to.

# POST /motion
Adds a motion to the database. An example JSON object that should be added in the body is as follows:
```JSON
{
    "title":"Test Motion 1"
}
```

# GET /motionchoices 
Displays motion choices in accordance to the specific motion
and whether the user is invited to the motion. 

# POST /motionchoices 
Adds a motion choice to the database. A Motion choice is a specific option that will be displayed for a corresponding motion. The necessary information for the body are the corresponding IDs for the corresponding motion and choice entries in the database. An example JSON object is as follows: 
```JSON 
{
    "motionId": 1,
    "choiceId": 3
}
```

# POST /motionuser
Adds a motion user entry to the database. This is necessary as it maps a user to a motion, essentially inviting them to that motion while also keeping track of information like their vote. 
An example JSON object is as follows:
```JSON
{
    "motionId": 1
}
``` 
The fields voteId (the choice they select) will automatically be set to null. The owner id is automatically mapped in the backend
in accordance to the user session (Which is still a WIP)

