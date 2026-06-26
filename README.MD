# Web-Technologies Analyzing Tasks

## 1) Architecture
1. Identify the main architectural layers in the project. Which classes belong to each layer?
2. Explain the responsibility of `RestApi` classes vs `Service` classes in this application.
3. Where are DTOs used, and why are they separated from entity/domain classes?
4. Choose one feature flow (for example "create user" or "get note by id") and trace it end-to-end across files and classes.
5. Which classes represent the public API surface (public boundary) of the application, and which are internal implementation details?
6. Does the architecture enforce separation of concerns consistently? Give one good example and one weak spot.

## 2) Request/Response Handling
7. For one `GET`, one `POST`, and one `PATCH/PUT` endpoint, describe expected request format and response format.
8. How does validation work for incoming request bodies?
9. What is the behavior when invalid input is sent (for example missing required fields, bad password format)? Which HTTP status is returned and why?
10. How are "not found" cases handled? Compare returning `null` vs throwing an exception in controllers/services.
11. Find where `NotFoundException` is translated into an HTTP response. Explain the mechanism in Spring terms.

## 3) Persistence and Data Model (Architecture + Runtime)
12. List the main entities and their relationships (draw a domain model).
13. Explain one JPA mapping annotation used in the project and what effect it has on database schema/behavior.
14. Why can naming an entity/table `user` cause issues in H2? How should this be fixed cleanly?
15. Which repository interfaces exist, and what role do they play in the architecture?

## 4) Testing
16. Consider the integration tests in this repository. What do they test? Are they focused on happy paths, edge cases, negative cases, or a mix?
17. Pick one integration test and explain what behavior it verifies and what setup it depends on.
18. Do tests validate only status codes, or also response bodies and side effects in the database? Provide examples.
19. Do the tests ensure reasonable architecture design? Why or why not? Do they ensure separation of concerns, or do they test multiple layers at once?

## 5) Static Code Analysis and Code Quality
20. Run the gradle task verification `check` and explain the results. What rules are violated and why? How can you fix them?
21. Create a new test class temporary class `EchoClass` witch two functions: public `echoString` that receives a string A and returns "Echo: A" and a function private `echoInt` accepts a number N that return "Echo: n". Run `check` again and explain the results. Are private and public functions treated differently by the static analysis rules? Why or why not?
22. Fix your TestClass to comply with the rules and run `check` again. Explain the results and how you fixed the issues.
23. What static analysis tooling is configured in this project? Where do you find its configuration and reports?
24. Explain why code documentation and quality matters for maintainability/reliability.

## 6) Synthesis / Design Reflection
25. What could happen if you data (user and notes) grow over time in the current state to the client? Explain the problem.
26. Propose one architectural improvement for the previous tasks problem and one API-design improvement that can be implemented with minimal risk.
27. Propose a strategy to make error responses consistent across all endpoints.
28. Assume we add a login to our application. The client sends credentials to the `/login` endpoint and the login fails. Which message can we send back (why or why not)?
    1. "Invalid username or password"
    2. "User with username '...' not found"
    3. "Password for user '...' is incorrect"
29. You are tasks with implementing the password storage which you reveice as plain text from the client. Which of the following approaches would you choose and why?
    1. Store the password as is in the database for later processing.
    2. Hash the password first with a global salt (once generated und reused for all users) using SHA-512 hash function and store the hash.
    3. Propose a different approach. (Explain which and why.)
