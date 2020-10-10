# Requirements

## Vision

An easy to use organizational tool, My Career Assistant is a user oriented visual tool to stay on top of your job search process. Keeping track of jobs and applications can be challenging and frustrating. MCA is here to help you make the best of every opportunity without all the headache.

## Scope

### IN

  - User should be able to search for jobs based on keywords and location.
  - List of jobs should render
  - Be able to render a view of more specific details for a job, like description and contract type
  - Build functionality to save specific jobs to a user specific area
  - Add ability to update status of saved jobs and application process
  - Make registration/login use proper encryption
  - Add a feature for retaining notes about a specific saved job
  - Be able to save jobs manually when found through other sources


### OUT

  - Users who are not logged in should not have access beyond the home page
  - Users will not be able to apply for jobs directly through the site


### Minimum Viable Product

  - List of jobs should render
  - Be able to render a view of more specific details for a job, like description and contract type
  - Build functionality to save specific jobs to a user specific area
  - Make registration/login use proper encryption
  - Add a feature for retaining notes about a specific saved job


### Stretch

  - Add timed reminders for users to check in or close jobs after time
  - Create ability to share postings with other users
  - Add communication between users like messaging


## Functional Requirements

  - User should be able to search for jobs based on keywords and location
  - User should be able to remove jobs from saved area
  - User should be able to update status of saved jobs and application process


### Data Flow

Pages are served from the Spring Boot server backend based on requests by a browser. The requests for the home, login, and signup pages will be open to all users. But all other routes will require a user to be signed in. Their searches will hit routes that ping APIs to offer results. And relevant data will be saved to a PostgreSQL database for persistance.

## Non-Functional Requirements (301 & 401 only)

Security:

The site has login and user specific features. So security is a concern. User passwords will be hashed for protection so they are not visible in their original form to users or administrators. And input from users will be sanitized to prevent attacks like SQL injection.

Testability:

The pages should all be able to render cleanly and all the routes followed without issue. To maintain this, there will be a robust set of tests for page data, API access, database functionality, and user information handling.

