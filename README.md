# RuneAchievements
 
## Local development

### Intial setup

The app is backed by a postgres DB and expects to find an environment variable `DATABASE_URL` pointing at 
the DB instance in the format `postgres://username:password@host:port/dbname`.

If you're running the app on a heroku instance, this variable will be configured for you automatically with 
the correct credentials (assuming you have correctly 
[provisioned a Postgres DB for your app](https://devcenter.heroku.com/articles/heroku-postgresql)). 

For local development, you should point this at a locally-running postgres instance (see 
[postgres setup docs](https://www.postgresql.org/docs/12/tutorial-createdb.html)). 

In either case, you will want to initialize the DB with the schema found in `src/java/resources/schema.sql`.

### Running locally

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
