*Université de CAEN 2020/2021*

# Decision-making and Artificial intelligence L3 INFORMATIQUE.


*------------------------------*
|            GROUPE            |
*------------------------------*

- HUET Bryan 21701042
- ACUNA Mithian 21805281
- TANNI Matteo 21807118
- BOURNONVILLE Aurélien 21802829

*------------------------------*
|    COMPILATION & EXECUTION   |
*------------------------------*

Nous utilisons ant (https://ant.apache.org/) pour compiler et executer le code. Par défaut, l'application execute la classe
HouseDemo, pour changer de classe executable il suffit de modifier la value de "Main-class" dans le fichier build.xml
- Pour compiler et executer il faut ouvrir un terminal à l'emplacement du fichier build.xml et de simplement entrer la commande :
$ ant

Il est egalement possible de faire sans ant avec les commandes suivantes :
$ mkdir build
$ javac -d "build"  -cp "ressource/solvertests.jar;ressource/representationtests.jar;ressource/planningtests.jar;ressource/dataminingtests.jar"  $(find . -name "*.java")
$ java -cp  "build;ressource/solvertests.jar;ressource/representationtests.jar;ressource/planningtests.jar;ressource/dataminingtests.jar"  src.examples.HouseDemo
