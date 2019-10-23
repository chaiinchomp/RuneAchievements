# RuneAchievements
 
## Local development

1. Compile and start up spring boot app

    `mvn spring-boot:run`

2. Go to [http://localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html)

## Deploying with Heroku

Full heroku deployment instructions [here](https://devcenter.heroku.com/articles/deploying-spring-boot-apps-to-heroku).

1. Compile and deploy latest changes
    ```
    mvn compile
    git push heroku master
    ```
2. Run `heroku open`. 
3. The page opened in your browser will show as Not Found. Add `/swagger-ui.html` to the URL, or go to an API route directly, and reload.
