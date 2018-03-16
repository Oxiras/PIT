# PIT


USAGE
1. Navigate to main project directory: ../pit-project
2. Execute following command: mvn org.pitest:pitest-maven:mutationCoverage


STEP BY STEP:
1. Clone pitest from : https://github.com/hcoles/pitest
2. Assume we already had maven installed. Then go to the main directory of cloned folder, run command:
	 mvn clean install -DskipTests
	 // This to install, and also generate 1.4.0-SNAPSHOT as well
3. Copy pit-project (which we created) to this main directory
4. Go to our pit-project directory and run :  mvn org.pitest:pitest-maven:mutationCoverage
  => We can get the desired result
By this way, we do not need to change anything in POM file.


Everytime we edit java files in pitest, we need to run again: 
 	mvn clean install -DskipTests 
then
	mvn org.pitest:pitest-maven:mutationCoverage

