#Authetication login heroku
heroku login

#Init Project git repository
mkdir myproject/ && cd my-project/
git init

#Append Project remote heroku
heroku git:remote -a [my-project]

# Commit your code to the repository and deploy it to Heroku using Git.

git add .
git commit -am "make it better"
git push heroku master

#Existing Git repository
## For existing repositories, simply add the heroku remote

$ heroku git:remote -a [my-project]