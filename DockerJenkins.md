# CI/CD with Docker and Jenkins
## Things I'm assuming you have/Things to be explained in another markdown
- AWS EC2 instance
- Tomcat server on that EC2 instance
- Jenkins running on the Tomcat server
- Jenkins has had it's basic set-up (I should just be using the plugins they recommend)
- Most of these are covered in [William's AWS EC2 guide](https://github.com/2011JavaReact/Training/blob/master/Week3/setup-cd-servlets-app.md) though it may be a bit scant on some nice explainations and details

## Jenkins Pipeline
1. Go to your Jenkins dashboard (http://{ AWS EC2 IP address }:{ Tomcat's port }/jenkins/)
2. Click on "New Item" to start a new Jenkins item
3. Provide a name for the new item, and from the pre-made jobs, select "Pipeline", and then hit "OK"
4. Under "Build Triggers", check the box for "GitHub hook trigger for GITScm polling"
5. Under "Pipeline", and under the "Definition" pulldown menu, select "Pipeline script from SCM"
    - This is the option that makes Jenkins look for a "Jenkinsfile" when it pulls over the GitHub repository
6. Still in the "Pipeline" section, change the "SCM" pulldown menu to "Git"
    1. This should open up a "Git" section
    2. Under "Repositories", provide the URL to the GitHub repository you want Jenkins to pull from
    3. Credentials may be needed, if the repository is private and Jenkins cannot see it
    4. Under "Branches to build", enter the name of the branch that Jenkins will be pulling from
        - This will probably be "master" or "main"
        - Follow the */{branch name} pattern that it starts you off with
7. Finally, "Script Path" should be left as "Jenkinsfile"
    - This means Jenkins will be looking for a file called "Jenkinsfile" in the root directory of the GitHub repository when it pulls it
    - You can change this as needed, but this is the default and basically the convention
8. Hit "Save" to finish, and go to the new pipeline's menus.

At this point, Jenkins is ready to listen for changes from the GitHub repository, and pull over the changes when they occur. Although Jenkins is listening, GitHub is not saying anything right now. For that, we need to set-up a webhook on GitHub.

## GitHub Webhook
1. On the repository that you intend to deploy, go to the "Settings" tab
2. From here, go to the "Webhooks" section (this may prompt you to verify your password)
3. Click "Add webhook"
4. Under "Payload URL", you'll use the same address you use to access the Jenkins dashboard, and tack on a "/github-webhook/"
    - This should look something like http://{ AWS EC2 IP address }:{ Tomcat's port }/jenkins/github-webhook/
    - I hate how that looks, so here it is with a fake address http://121.341.561.781:8080/jenkins/github-webhook/
5. Under "Content Type", I think both options are okay, at least for what we're doing, but I usually select "application/json"
6. Leave "Secret" blank
7. For "Which events would you like to trigger this webhook?", select "Just the push event"
8. Finally, hit "Add Webhook"

At this point, Jenkins is listening for GitHub's webhook, and GitHub is ready to tell Jenkins when a new branch got pushed. Now, we do something clever, and add directions for Jenkins to follow into the GitHub repository. You could add these directly to Jenkins, but GitHub is already a very good place to store small text files, so it's pretty appropriate to put these directions.

## Jenkinsfile
1. In the root directory of your repository, add a file called "Jenkinsfile"
    - This is the conventional name for it, especially if you only have one Jenkinsfile in your project.
    - The Jenkinsfile does not have a file extension, in case the looks kinda weird.
    - If you want to change any of this, you need to adjust the Jenkins pipeline to look in a different place and/or look for the file by a different name. Unless you have a good reason to do this, just stick to these simple names and set-up.
2. There are ton of ways to configure these files, but at their base, they are a series of terminal commands broken down into stages and steps. Here is the Jenkinsfile for this project:
```
pipeline {
    agent any

    stages {
        stage('clean') {
            steps {
                sh 'mvn clean'
            }
        }
        stage('package') {
            steps {
                sh 'mvn package'
            }
        }
        stage('dockerBuild'){
        	steps{
        		sh 'docker build -t foodimage .'
        	}
        }
    }
}
```
- pipline: The outermost block, and how Jenkins knows where to start in the file.
- agent: "The agent section specifies where the entire Pipeline, or a specific stage, will execute in the Jenkins environment depending on where the agent section is placed." I kinda get it, but I also kinda don't. This is required, and leave it as any, and you should mostly be okay. (But this may also be part of why I was having problems with Docker...)
- stages: Contains stages. Wow! But what are stages?
- stage: An individual stage will have a name for the stage in the parenthese and quotes, and its block will have...
- steps: The actual commands and instructions that Jenkins will carry out.

The example above is pretty simple, maybe a bit too organzied and split up, but it does demonstrate a nice flow of how Jenkins will do things. In this case, Jenkins will:
1. Go to stages, go to the first stage, "clean", go to the steps, see the "sh" which indicates to run something in a shell, and then run ```mvn clean``` on the GitHub repository.
2. This is the only step and command in this stage, so that's all that will happen here, but it will wait until the ```mvn clean``` is done before it moves on. Without this division and order, Jenkins might try to run ```mvn clean``` and ```mvn package``` at the same time. (And not like ```mvn clean package``` but the two separate commands, which would likely step on each other's toes.)
3. With that stage and step done, Jenkins moves on to the next stage, and sees it should run ```mvn package```
4. Once packaged, the .jar is ready to be put in a docker image. So, Jenkins runs ```docker build -t foodimage .```, which requires the presence of a Dockerfile. I'll explain the Dockerfile in a moment, but first, that command has a few interesting elements.
    1. docker: You'll need docker installed. Nothing hard, but here's [William's guide on Docker.](https://github.com/2011JavaReact/Training/blob/master/Week7/DockerDemo.md)
    2. build: Builds the docker image based on the instructions in the Dockerfile
    3. -t: Used to tag images, which are important in DockerHub and organization. However, I have not actually included a tag for the image in this line, but just in case I want to later, I leave the -t.
    4. foodimage: This will be the name of the image that is created, and at least one of the ways to call on it later.
    5. .: There's a dot there at the end, which is for the location of the Dockerfile. But because I'm trying to keep everything in the root folder, the "." just means "the current directory". Very simple, but it's also easy to overlook that ., so don't!

At this point, Jenkins has the GitHub repository, cleaned and packaged it with Maven, and now wants to build a Docker image out of it. Docker needs some rules and specifications for the image it will build, and these instructions are kept in a Dockerfile.

## Dockerfile
1. In the root directory of your repository, add a file called "Dockerfile"
    - This is gonna be a lot like the Jenkinsfile. This name and location are mostly convention, there's no file extension, and if you want to change anything about this, you're on your own!
2. When ```docker build``` is run, it will look in the Dockerfile for instructions on how to build the Docker image. Here is our Dockerfile:
```
FROM java:8
ADD ./target/foodConsensus-boot-0.0.1-SNAPSHOT.jar ./target/foodConsensus-boot-0.0.1-SNAPSHOT.jar
EXPOSE 8081
ENTRYPOINT ["java","-jar","./target/foodConsensus-boot-0.0.1-SNAPSHOT.jar"]
```
Another breakdown!
    - FROM: Starting point for most Dockerfiles, and indicates what the base image sound be. In this case, Java version 8.
    - ADD: The usage is a lot more robust than this, but we're using it to indicate the location of the .jar file. Technically, the first part is source, and the second is destination, but the image isn't placed in that location when this is done, so... I have no idea. I'm just following William's example on this one.
    - EXPOSE: Opens a port in the Docker image that it will use to connect with the outside world.
    - ENTRYPOINT: Also, more robust, but we're making a simple command out of it. This is a Docker conversion of the command ```java -jar ./target/foodConsensus-boot-0.0.1-SNAPSHOT.jar```

At this point, Jenkins would then run this Docker image... But I have no idea how to get that to happen! So, let's manually deploy it!

## Docker
1. Let's keep this part simple: run ```docker run -t -i -p 8081:8081 foodimage```, and the Spring Boot project will start running on the AWS IP address, on port 8081. You can stop this process with the standard Ctrl + C interruption.
2. If you want to check other docker commands, like stopping running images, showing all other built images, pushing and pulling from DockerHub, I'd recommend [William's guide on Docker.](https://github.com/2011JavaReact/Training/blob/master/Week7/DockerDemo.md)

## Wait, how can I keep the Docker image running when I close my terminal?
1. I have no idea!
2. Here's my crap solution: screen, or rather the ```screen``` command from Linux, which should already be available on your EC2.
3. Simply run ```screen```, this will open a new screen, kinda like a new tab, but with much less visual indication.
4. Start up the the Docker image like normal, and test that it's working.
5. Press Ctrl + a + d on your keyboard to detach from from this screen, and test that the image and it's endpoints are still working. If so, great! Image deployed!
6. If you need to stop the image, you can reconnect to this detached screen by first running ```screen -ls``` to list all screens, take note of the name of the detached screen (it'll look something like 12345.pts-0.ip-172-999-999-999)
7. Reconnect to the screen with ```screen -r {detached screen's name}```. Then you can stop the Spring Boot project, and then detach again, or run ```exit``` to stop it.
