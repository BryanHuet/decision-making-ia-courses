*Université de CAEN 2020/2021*

# Decision-making and Artificial intelligence  repository.

*------------------------------*
|    COMPILATION & EXECUTION   |
*------------------------------*

You can do it with [ant apache](https://ant.apache.org/).
- You have to open a bash at the location of the build.xml file
- Then simply enter the key : ant

or without
- mkdir build
- javac -d "build"  -cp "ressource/solvertests.jar;ressource/representationtests.jar;ressource/planningtests.jar;ressource/dataminingtests.jar"  $(find . -name "*.java")
- java -cp  "build;ressource/solvertests.jar;ressource/representationtests.jar;ressource/planningtests.jar;ressource/dataminingtests.jar"  src.[packageToTest].Test
