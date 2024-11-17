# Introduction 
This is an archetype structural Reference Project which will be used for migrating Digital Integrations libraries & Adapters.
This project will gives a basic description of every package and how the libraries and adapters needs to be migrated.

# Getting Started
The following is a guide to work on this project:
1. Clone the project & Import the project as **maven** project .
2. Check whether the project is created with correct artifactid and other configurations.
3. Add the respective files & classes required for the library or Adapter.
4. migrate **camel2 to camel3 & Junit4 to Junit5**.
5. Try running **mvn clean compile**.
6. Run the UnitTest & **mvn Test** if everything works fine.
7. Do **mvn install** & package using mvn package.
8. Push the code to ADO and Raise a PR from feature Branch to Develop ,If PR completes release a "SNAPSHOT" version to artifactory .Test the Adapter/library by adding as a dependency .
9. If Adapter/Library works Fine then Raise a PR from develop to master and Release a Main version.
