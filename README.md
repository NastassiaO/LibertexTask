# LibertexTask
Project solve the task: Write a function that takes a date of birth as input (in any convenient form) and returns whether the user is 18 years old
-Write unit tests for this feature

# Technology Stack
- Java 11
- Gradle
- TestNG

#  project directory structure
src
- main
  - Java
    - org.libertex.testtask - Domain model package consisting of all functionality
      - utils - Contains all helpers.
        - DateUtils.class - Contains all methods to work with dates in different formats. It contains the main function isUserOver18(String dateInStr) takes a date of birth as input (in any convenient form) and returns whether the user is 18 years old
        - JsonUtils.class - Contains methods to read JSON files.
        - MathUtil.class - Contains method to generate Random numbers in range.
        - StramUtils.class - Contains methods to read data from the stream.
   
      - DateFunctions - Allow the user to run the program and type the date of birth from the console.

  - resources
  -   dateFormats.json - list of date formats
  -   log4j.properties 
- test
  - java
    - tests
      - DateOfBirthUnitTests - contains unit tests for function. There are positive and negative tests — data generated using @DataProvider and dateFormats.json. Tests run in 10 threads.
- build.gradle.kts - contains all libs and run settings
# Installation and Test Execution
gradle test --tests DateOfBirthUnitTests


  
