 Introduction 
* This is an archetype structural Reference Project which will be used for migrating Digital Integrations container Application.
This project will gives a basic idea of every package and how the configurations should be used.

* This Container Application is a collection of API's , migrated API's are Embedded as lean jars to deploy it to the Higher Environment.

# Getting Started
The following is a guide to work on this project:
* Run the **create-new-project** pipeline which will creates a container project & repo for you in ADO.
1. Clone the project & Import the project as **maven** project .
2. Check whether the project is created with correct artifactId, other configurations & compiles or not.
3. Add the respective files,properties & classes required for the container Application.Add Backend endpoint in application.yaml
4. Embedded the API's Lean Jars as a dependency to this Application & check the API jars imported or not.
5. Write the Respective Testclasses Required & to pass Aquascan.
6. Try running the UnitTests and Run the Application.If Application starts up then hit the endpoint Request to check it is providing correct Response.
7. Run the BDD's pointing to local i.e.,"localhost:8080" &  if BDD's works fine.
8. Do **mvn install** & package using mvn package.
9. Push the code to ADO and Raise a PR from feature Branch to Develop ,If PR completes ,Run the **spring-boot-pipeline** Pipeline for container Application , checks it runs through sonar,checkmarx,Aqua and Helm Deployment. 
10. If Container is deployed to **Rancher** Environment then run the BDD's pointing to container not local.
11. If Bdd's works against container url then configure Vault & kong for this container Application & Run the Pipeline so that it would provide the response when we hit the Endpoint.
12. If your container application works with vault and kong then deploy a "SNAPSHOT" version to artifactory.
13. If container works Fine then Raise a PR from develop to master and Release a Main version.