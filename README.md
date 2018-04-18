# PIT Augmentation Project

Project Goals
This project augmented the <a href="https://github.com/hcoles/pitest">PIT Mutation Tool</a> with three additional mutators:
- Arithmetic operation deletion
- Arithmetic operator replacement
- Relation operator replacement

Instructions
1. Clone repository to your local machine.
2. Navigate to subdirectory /PIT/pitest of newly cloned folder.
3. Run following command: mvn clean install -DskipTests.
4. Navigate to the sample project folder location.
5. Run following command: mvn org.pitest:pitest-maven:mutationCoverage.

Running project with failed testcase
1. Commenting line 311 of CoverageData.java inside the package org.pitest.coverage of sub-project pitest-entry
2. Navigate to subdirectory /PIT/pitest-entry
3. Run following command: mvn clean install -DskipTests.
4. Navigate to subdirectory /PIT/pitest 
5. Run following command: mvn clean install -DskipTests.
6. Navigate to the sample project folder location.
7. Run following command: mvn clean test -DskipTests
8. Run following command: mvn org.pitest:pitest-maven:mutationCoverage.
