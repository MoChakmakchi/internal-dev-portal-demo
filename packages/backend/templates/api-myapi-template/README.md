* This is an archetype structural Reference Project which will be used for migrating Digital Integrations API's.
This project will gives a basic idea of every package,classes and how the configurations should be used.
This project will also provides a Example working code,helps to understand Developers how API's is working.

* This API  is a collection of core-libraries,config files & adapters which will provide their respective services.

# Getting Started
The following is a guide to work on this project:
* Run the **create-new-project** pipeline which will creates a container project & repo for you in ADO.
1. Clone the project & Import the project as **maven** project & Run the UT's which will provide a basic idea about Request & Response mapping .
2. Check whether the project is created with correct artifactId, other configurations & compiles or not.All the beans-configs & application properties needs to be in Test folder only.
3. Add the respective files,properties & classes required for the container Application.Add Backend endpoint in application.yaml
4. Run the sonarlint Analysis in IDE to solve sonar issues locally
5. Write the Respective Testclasses Required & to pass sonar.
6. Try running the UnitTests and Run the Application.If Application starts up then hit the endpoint Request to check it is providing correct Response.
7. Run the BDD's pointing to local i.e.,"localhost:8080" &  if BDD's works fine.
8. Do **mvn install** & package using mvn package.
9. Push the code to ADO and Raise a PR from feature Branch to Develop ,If PR completes ,Run the **spring-boot-pipeline** Pipeline for API , checks it runs through sonar,checkmarx. 
10. If your API works with the container application also then deploy a "SNAPSHOT" version to artifactory using spring-boot-pipeline.
11. Embedded the API's Lean Jars as a dependency to your API's container & check the API jars imported or not& run the pipeline for the container for deployment.
12. If Container is deployed to **Rancher** Environment then run the BDD's pointing to container not local.
13. If Bdd's works against container url then configure Vault & kong for this API's related container Application & Run the Pipeline so that it would provide the response when we hit the Endpoint.
14. If API works Fine then Raise a PR from develop to master and Release a Main version.