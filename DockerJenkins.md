# CI/CD with Docker and Jenkins
## Things I'm assuming you have/Things to be explained in another markdown
- AWS EC2 instance
- Tomcat server on that EC2 instance
- Jenkins running on the Tomcat server
- Jenkins has had it's basic set-up (I should just be using the plugins they recommend)

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

## GitHub Webhook
1. On the repository that is being deployed, go to the "Settings" tab
2. From here, go to the "Webhooks" section (this may prompt you to verify your password)
3. Click "Add webhook"
4. Under "Payload URL", you'll use the same address you use to access the Jenkins dashboard, and tack on a "/github-webhook/"
    - This should look something like http://{ AWS EC2 IP address }:{ Tomcat's port }/jenkins/github-webhook/
    - I hate how that looks, so here it is with a fake address http://121.341.561.781:8080/jenkins/github-webhook/
5. Under "Content Type", I think both options are okay, at least for what we're doing, but I usually select "application/json"
6. Leave "Secret" blank
7. For "Which events would you like to trigger this webhook?", select "Just the push event"

webhook testing