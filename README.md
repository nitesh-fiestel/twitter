## Twitter application

# Requirements covered
 - User can signup
 - User can follow other user
 - User can tweet
 - User can fetch their tweets
 - User can get feed which will have tweets from users which they follow
   - The newer posted tweets will come first
 
# Requirements not covered
  - Authentication & Authorization
  - Session management
  - Adding custom error handling
  - Unfollow users
  - Caching for faster response
  - Hashtag capability
  - Find trending tweets

# Application details
  Framework: Dropwizard
  Database: mysql-8
  ORM: Hibernate
  Main class: Manager Application
  Dependency manager: Gradle
  Additionally, used lombok library

# Step to run the application
 - clone the repo
 - build using gradle
 - setup mysql on environment where we will be running this application
 - Run ManagerApplication class
 - Access all apis under localhost:8080/api