# Food Consensus Backend 

Back-end for Revature Project 2: Food Consensus.
Team Members: Tyler Kim, Eric Huang, Macy McAnally, Karl Matthes, Tianyuan Deng

# Project Description
This is the back-end API for our application Food Consensus. It's a simple app where a user can select 4 restaurants as possible choices,
and then invite some friends to begin a voting process (which we've called a motion). Because of memory constraints, this backend is currently not deployed on an EC2 (although it was before).

# Technologies Used
* Spring Boot 
* Hibernate 
* Spring Security (JWT Tokens) 
* Amazon RDS

# Features
* Login/authentication functionality
* Ability to view your current motions you are invited to, and past motions
* Ability for a motion owner to invite other users to their motion
* Distinct functionality between a motion owner and the users who are invited to a motion
    * Motion users can add suggestions to the list of choices, along with putting in their vote.
    * Motion owners can approve or deny the suggestions, can put in their vote, and can also end the motion to begin tallying votes to determine a winner. 

To-do list:
* Search function to filter users and restaurant choices
* Pagination for viewing users and restaurant choices
* Need a way to handle tiebreakers
* Need to figure out a better way to handle polling 

# Instructions 
Go to the main branch, click the green "Code" button and copy the HTTPS link. Use git clone followed by the copied HTTPS link to clone the repo.

The project can be run by using 
```
mvn spring-boot:run
``` 
(assuming you have maven installed). 

The project can also be run by opening it in Spring Tools Suite and using the "Run as Spring Boot App" command. 

This project needs an application.properties file which should be added to src/main/resources.
The application.properties needs:
```
server.port
spring.datasource.url
spring.datasource.username
spring.datasource.password

spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.PostgreSQL92Dialect

spring.jpa.hibernate.ddl-auto=update

app.jwtSecret
app.jwtExpirationMs 
```
A local database can be used for testing purposes; so you will have to set the necessary values for url, username, and password. the jwtSecret can be some random String, and a fair default for jwtExpirationMs is 86400000.

# Usage 

## Current Endpoints 

Base URL: localhost:8081 (or whichever port you decide to use)

### NOTE: All endpoints in this branch are locked quite firmly behind authentication. Only the POST ```/api/auth/signup``` and POST ```/api/auth/signin``` routes should be available without authenticating first.

### A successful login attempt will return a JWT token. This token can be appended to the header of HTTP requests. How you set this header may change between fetch, Axios, and Postman, but the rough format would be something like:
```
"Authorization" : "Bearer <insert the JWT token here>"
```

## POST /api/auth/signup
Creates a new user account. Works very similarly to the old POST route, but the password will be encoded before it is stored on the database. On success, a message will be returned.

Follow this example format: 
```JSON
{
    "name":"testuser2",
    "password":"pass"
}
```

## POST /api/auth/signin
Logs in a user account. Simply send the matching username and password you signed up with, and in the same format. The password will be encoded, checked against the encoded password in the database, and if they match, a JWT token will be returned, which will allow access to all other endpoints.

Follow this example format: 
```JSON
{
    "name":"testuser2",
    "password":"pass"
}
```

## GET /hello
Simple Hello World test endpoint

## GET /users
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
## GET /users/{id}
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

## POST /users
To add a user to this POST request, a JSON object must be added in the body. 
>http://localhost:8081/users

Follow this example format: 
```JSON
{
    "name":"testuser2",
    "password":"pass"
}
```
## GET /choices
Get all choices in the database.
> http://localhost:8081/choices

Output:
```JSON
[
 {
        "id": 16,
        "name": "Burger King",
        "owner_id": {
            "id": 17,
            "name": "testuser22",
            "password": "$2a$10$Kbq6IqA.88ZjPJktQD6TWuQyOFqiwvnSWgyp90f0YcDROHNPbSKC6",
            "admin": false
        }
    },
    {
        "id": 17,
        "name": "Pizza Hut",
        "owner_id": {
            "id": 17,
            "name": "testuser22",
            "password": "$2a$10$Kbq6IqA.88ZjPJktQD6TWuQyOFqiwvnSWgyp90f0YcDROHNPbSKC6",
            "admin": false
        }
    }
]
```

## POST /choices
Add a choice to this POST request, a JSON object must be added in the body.

Input:
> http://localhost:8081/addChoices

```JSON
{
    "name": "Burger King2222",
}
```

You may also had an optional imageurl field in the JSON body:
```JSON
{
    "name": "In-n-Out",
    "imageurl": "https://upload.wikimedia.org/wikipedia/commons/thumb/f/f2/InNOut.svg/1200px-InNOut.svg.png"
}
```

Output:
```JSON
{
    "id": 42,
    "name": "In-n-Out",
    "owner_id": {
        "id": 17,
        "name": "testuser22",
        "password": "$2a$10$Kbq6IqA.88ZjPJktQD6TWuQyOFqiwvnSWgyp90f0YcDROHNPbSKC6",
        "admin": false
    },
    "imageurl": null
}
```

## GET /motions
Gets and returns a motion from the database. Only displays motions the current user is invited to.

Example output:
```JSON
[
    {
        "id": 4,
        "title": "Test Motion 1",
        "owner_id": {
            "id": 17,
            "name": "testuser22",
            "password": "$2a$10$Kbq6IqA.88ZjPJktQD6TWuQyOFqiwvnSWgyp90f0YcDROHNPbSKC6",
            "admin": false
        },
        "status": true,
        "winner": {
            "id": 16,
            "name": "Burger King",
            "owner_id": {
                "id": 17,
                "name": "testuser22",
                "password": "$2a$10$Kbq6IqA.88ZjPJktQD6TWuQyOFqiwvnSWgyp90f0YcDROHNPbSKC6",
                "admin": false
            }
        }
    }
]
``` 

## GET /motions/{motionId}
Gets and returns a motion based off a given motion ID. 

URL input:
> http://localhost:5000/motions/4

Example output:
```JSON
{
    "id": 4,
    "title": "Test Motion 1",
    "owner_id": {
        "id": 17,
        "name": "testuser22",
        "password": "$2a$10$Kbq6IqA.88ZjPJktQD6TWuQyOFqiwvnSWgyp90f0YcDROHNPbSKC6",
        "admin": false
    },
    "status": true,
    "winner": {
        "id": 16,
        "name": "Burger King",
        "owner_id": {
            "id": 12,
            "name": "karltest3",
            "password": "$2a$10$pn4l4nr5sxPYnGF3ybU6ouaiJdiNHmY46ThzZvN68UPFIlpD5s1Ra",
            "admin": false
        },
        "imageurl": null
    }
}
```
## POST /motions
Adds a motion to the database. An example JSON object that should be added in the body is as follows:
```JSON
{
    "title":"Test Motion 1"
}
```

Example output:
```JSON
{
    "id": 5,
    "title": "Test Motion 2",
    "owner_id": {
        "id": 17,
        "name": "testuser22",
        "password": "$2a$10$Kbq6IqA.88ZjPJktQD6TWuQyOFqiwvnSWgyp90f0YcDROHNPbSKC6",
        "admin": false
    },
    "status": false,
    "winner": null
}
```

## PUT /motions/{motionId} 
Tallies up a winner for the respective motion (determined by the motionId in the URL parameter) and updates the motion's status to true. Does not require a JSON body, but the user submitting the PUT request must be the owner of this motion. 

Example output: 
```JSON
{
    "id": 4,
    "title": "Test Motion 1",
    "owner_id": {
        "id": 17,
        "name": "testuser22",
        "password": "$2a$10$Kbq6IqA.88ZjPJktQD6TWuQyOFqiwvnSWgyp90f0YcDROHNPbSKC6",
        "admin": false
    },
    "status": true,
    "winner": {
        "id": 16,
        "name": "Burger King",
        "owner_id": {
            "id": 17,
            "name": "testuser22",
            "password": "$2a$10$Kbq6IqA.88ZjPJktQD6TWuQyOFqiwvnSWgyp90f0YcDROHNPbSKC6",
            "admin": false
        }
    }
}
``` 

## GET /motionchoices

Displays all motion choices.

## GET /motionchoices/{motionId} 
Displays motion choices in accordance to the specified motion 
and whether the logged in user is invited to the motion. 

Example Output: 
```JSON
[
    {
        "id": 2,
        "motion": {
            "id": 4,
            "title": "Test Motion 1",
            "owner_id": {
                "id": 17,
                "name": "testuser22",
                "password": "$2a$10$Kbq6IqA.88ZjPJktQD6TWuQyOFqiwvnSWgyp90f0YcDROHNPbSKC6",
                "admin": false
            },
            "status": true,
            "winner": {
                "id": 16,
                "name": "Burger King",
                "owner_id": {
                    "id": 17,
                    "name": "testuser22",
                    "password": "$2a$10$Kbq6IqA.88ZjPJktQD6TWuQyOFqiwvnSWgyp90f0YcDROHNPbSKC6",
                    "admin": false
                }
            }
        },
        "choice": {
            "id": 16,
            "name": "Burger King",
            "owner_id": {
                "id": 17,
                "name": "testuser22",
                "password": "$2a$10$Kbq6IqA.88ZjPJktQD6TWuQyOFqiwvnSWgyp90f0YcDROHNPbSKC6",
                "admin": false
            }
        }
    },
    {
        "id": 3,
        "motion": {
            "id": 4,
            "title": "Test Motion 1",
            "owner_id": {
                "id": 17,
                "name": "testuser22",
                "password": "$2a$10$Kbq6IqA.88ZjPJktQD6TWuQyOFqiwvnSWgyp90f0YcDROHNPbSKC6",
                "admin": false
            },
            "status": true,
            "winner": {
                "id": 16,
                "name": "Burger King",
                "owner_id": {
                    "id": 17,
                    "name": "testuser22",
                    "password": "$2a$10$Kbq6IqA.88ZjPJktQD6TWuQyOFqiwvnSWgyp90f0YcDROHNPbSKC6",
                    "admin": false
                }
            }
        },
        "choice": {
            "id": 17,
            "name": "Pizza Hut",
            "owner_id": {
                "id": 17,
                "name": "testuser22",
                "password": "$2a$10$Kbq6IqA.88ZjPJktQD6TWuQyOFqiwvnSWgyp90f0YcDROHNPbSKC6",
                "admin": false
            }
        }
    }
]
``` 

## POST /motionchoices 
Adds a motion choice to the database. A Motion choice is a specific option that will be displayed for a corresponding motion. The necessary information for the body are the corresponding IDs for the corresponding motion and choice entries in the database. An example JSON object is as follows: 
```JSON 
{
    "motionId": 4,
    "choiceId": 18
}
```

Output:
```JSON
{
    "id": 4,
    "motion": {
        "id": 4,
        "title": "Test Motion 1",
        "owner_id": {
            "id": 17,
            "name": "testuser22",
            "password": "$2a$10$Kbq6IqA.88ZjPJktQD6TWuQyOFqiwvnSWgyp90f0YcDROHNPbSKC6",
            "admin": false
        },
        "status": false,
        "winner": null
    },
    "choice": {
        "id": 18,
        "name": "McDonald's",
        "owner_id": {
            "id": 17,
            "name": "testuser22",
            "password": "$2a$10$Kbq6IqA.88ZjPJktQD6TWuQyOFqiwvnSWgyp90f0YcDROHNPbSKC6",
            "admin": false
        }
    }
}
``` 


## POST /motionuser
Adds a motion user entry to the database. This is necessary as it maps a user to a motion, essentially inviting them to that motion while also keeping track of information like their vote. 
An example JSON object is as follows:
```JSON
{
    "motionId": 4,
    "userId": 21
}
``` 
The fields voteId (the choice they select) will automatically be set to null. 

Output:
```JSON
{
    "id": 5,
    "userid": {
        "id": 21,
        "name": "testingagain",
        "password": "$2a$10$xCYy8.lbJRMDCNmxeF4Rtukk.hMmqfrqbzGG7KAAV8TYfpCVivuSC",
        "admin": false
    },
    "motion": {
        "id": 4,
        "title": "Test Motion 1",
        "owner_id": {
            "id": 17,
            "name": "testuser22",
            "password": "$2a$10$Kbq6IqA.88ZjPJktQD6TWuQyOFqiwvnSWgyp90f0YcDROHNPbSKC6",
            "admin": false
        },
        "status": false,
        "winner": null
        }
    },
    "voteid": null
}
```

## PUT /motionuser 
Updates a motion user entry so that they can add a vote to a user's entry in the motion. An example JSON object is as follows:
```JSON
{
    "motionId":"4",
    "voteid":"17"
}
``` 
17 indicates the ID of the choice they are voting for. 

Output: 
```JSON
{
    "id": 4,
    "userid": {
        "id": 20,
        "name": "testuser44",
        "password": "$2a$10$DBUBn7F.IEIiOkI8h925Qe.q626tY0KpbgdR3Hssh.pLzGas1Xfz2",
        "admin": false
    },
    "motion": {
        "id": 4,
        "title": "Test Motion 1",
        "owner_id": {
            "id": 17,
            "name": "testuser22",
            "password": "$2a$10$Kbq6IqA.88ZjPJktQD6TWuQyOFqiwvnSWgyp90f0YcDROHNPbSKC6",
            "admin": false
        },
        "status": false,
        "winner": null
    },
    "voteid": {
        "id": 17,
        "name": "Pizza Hut",
        "owner_id": {
            "id": 17,
            "name": "testuser22",
            "password": "$2a$10$Kbq6IqA.88ZjPJktQD6TWuQyOFqiwvnSWgyp90f0YcDROHNPbSKC6",
            "admin": false
        }
    }
}
```

## GET /suggestions 
Gets all suggestions from the database. Example output:
```JSON
[
    {
        "suggestionId": 1,
        "motion_id": {
            "id": 4,
            "title": "Test Motion 1",
            "owner_id": {
                "id": 17,
                "name": "testuser22",
                "password": "$2a$10$Kbq6IqA.88ZjPJktQD6TWuQyOFqiwvnSWgyp90f0YcDROHNPbSKC6",
                "admin": false
            },
            "status": true,
            "winner": {
                "id": 16,
                "name": "Burger King",
                "owner_id": {
                    "id": 17,
                    "name": "testuser22",
                    "password": "$2a$10$Kbq6IqA.88ZjPJktQD6TWuQyOFqiwvnSWgyp90f0YcDROHNPbSKC6",
                    "admin": false
                }
            }
        },
        "choice_id": {
            "id": 19,
            "name": "Chipotle",
            "owner_id": {
                "id": 17,
                "name": "testuser22",
                "password": "$2a$10$Kbq6IqA.88ZjPJktQD6TWuQyOFqiwvnSWgyp90f0YcDROHNPbSKC6",
                "admin": false
            }
        },
        "user_id": {
            "id": 20,
            "name": "testuser44",
            "password": "$2a$10$DBUBn7F.IEIiOkI8h925Qe.q626tY0KpbgdR3Hssh.pLzGas1Xfz2",
            "admin": false
        },
        "approval_status": "APPROVED"
    },
    {
        "suggestionId": 2,
        "motion_id": {
            "id": 4,
            "title": "Test Motion 1",
            "owner_id": {
                "id": 17,
                "name": "testuser22",
                "password": "$2a$10$Kbq6IqA.88ZjPJktQD6TWuQyOFqiwvnSWgyp90f0YcDROHNPbSKC6",
                "admin": false
            },
            "status": true,
            "winner": {
                "id": 16,
                "name": "Burger King",
                "owner_id": {
                    "id": 17,
                    "name": "testuser22",
                    "password": "$2a$10$Kbq6IqA.88ZjPJktQD6TWuQyOFqiwvnSWgyp90f0YcDROHNPbSKC6",
                    "admin": false
                }
            }
        },
        "choice_id": {
            "id": 19,
            "name": "Chipotle",
            "owner_id": {
                "id": 17,
                "name": "testuser22",
                "password": "$2a$10$Kbq6IqA.88ZjPJktQD6TWuQyOFqiwvnSWgyp90f0YcDROHNPbSKC6",
                "admin": false
            }
        },
        "user_id": {
            "id": 20,
            "name": "testuser44",
            "password": "$2a$10$DBUBn7F.IEIiOkI8h925Qe.q626tY0KpbgdR3Hssh.pLzGas1Xfz2",
            "admin": false
        },
        "approval_status": "PENDING"
    }
]
```
## GET /suggestions/{motionId}
Only gets suggestions from a specified motion. 
Follows the same output as the above example. 

## POST /suggestions
Adds a suggestion to the database for a motion owner to either
approve or decline. The JSON body will look as follows:
```JSON
{
    "motionId":"4",
    "choiceId":"19"
}
``` 
with the motionId being the id for the motion the user is suggesting for, choiceId being the id for the choice the user is suggesting. The output will be as follows:
```JSON
{
    "suggestionId": 2,
    "motion_id": {
        "id": 4,
        "title": "Test Motion 1",
        "owner_id": {
            "id": 17,
            "name": "testuser22",
            "password": "$2a$10$Kbq6IqA.88ZjPJktQD6TWuQyOFqiwvnSWgyp90f0YcDROHNPbSKC6",
            "admin": false
        },
        "status": false,
        "winner": null
    },
    "choice_id": {
        "id": 19,
        "name": "Chipotle",
        "owner_id": {
            "id": 17,
            "name": "testuser22",
            "password": "$2a$10$Kbq6IqA.88ZjPJktQD6TWuQyOFqiwvnSWgyp90f0YcDROHNPbSKC6",
            "admin": false
        }
    },
    "user_id": {
        "id": 20,
        "name": "testuser44",
        "password": "$2a$10$DBUBn7F.IEIiOkI8h925Qe.q626tY0KpbgdR3Hssh.pLzGas1Xfz2",
        "admin": false
    },
    "approval_status": "PENDING"
}
```

## POST /suggestions/{suggestionsId}
Allows a motion owner to approve or decline a suggestion. If the suggestion is approved, a motion choice is created and returned as output. If it's declined, null is returned. The suggestion's approval_status is updated as needed. SuggestionId in the path param  is the id of the suggestion being approved/declined. 

Example of JSON body:
Can set status to APPROVED, or DECLINED.

```JSON
{
    "status":"APPROVED"
}
```
Output (if approved, nothing is outputted if declined):
```JSON
{
    "id": 5,
    "motion": {
        "id": 4,
        "title": "Test Motion 1",
        "owner_id": {
            "id": 17,
            "name": "testuser22",
            "password": "$2a$10$Kbq6IqA.88ZjPJktQD6TWuQyOFqiwvnSWgyp90f0YcDROHNPbSKC6",
            "admin": false
        },
        "status": false,
        "winner": null
    },
    "choice": {
        "id": 19,
        "name": "Chipotle",
        "owner_id": {
            "id": 17,
            "name": "testuser22",
            "password": "$2a$10$Kbq6IqA.88ZjPJktQD6TWuQyOFqiwvnSWgyp90f0YcDROHNPbSKC6",
            "admin": false
        }
    }
}
```
## DELETE /suggestions/{suggestionsId}
Deletes a suggestion by the given suggestion id. 
Output:
```
Suggestion id 2 succesfully deleted.
```

# Contributors: 
* Eric Huang - Built the bulk of this API 
    * The Controllers layer (minus the authentication controllers)
    * The Service layer (includes logic for inviting users, users putting in their votes, and the final voting process)
    * Models in Hibernate (which automatically maps our objects to fields/tables in our database)
    * DAO layer (little code necessary thanks to JpaRepository)

* Karl 
    * Incorporated Spring Security for authentication with JWT tokens
    * Built the Jenkins pipeline for Continuous Integration for deployment on our EC2
    * Built the initial database mockup. 
* Tianyuan Deng 
    * Built the choices endpoint 
    * Helped with some refactoring of our models
* Tyler Kim 
    * Helped test the endpoints and reported bugs
    * Suggested high level changes and additions
* Macy McAnally 
    * Helped test the endpoints and reported bugs
    * Suggested some necessary changes to our database models  