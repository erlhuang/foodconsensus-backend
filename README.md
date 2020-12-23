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

# POST /users
To add a user to this POST request, a JSON object must be added in the body. Follow this example format: 
{
    "name":"testuser2",
    "password":"pass"
}