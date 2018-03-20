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
