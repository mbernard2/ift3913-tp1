DÉPÔT GITHUB
------------

Le code est disponible sur GitHub à l'adresse suivante :

    https://github.com/mbernard2/ift3913-tp1


COMPILATION
-----------

Le projet a été développé avec IntelliJ IDEA 2021.3.2 (Community Edition). Le
plus simple pour compiler le projet, une fois extrait de GitHub, est de
l'ouvrir avec cet IDE et de lancer la compilation, qui a déjà été configurée.

Un JAR comportant toutes les dépendances sera produit dans ce dossier :

    out/artifacts/CodeQualityEvaluator

Alternativement, pour le compiler avec la commande `javac` plutôt qu'avec un
IDE, il est important d'inclure la librairie fournie dans le dossier lib/ dans
le `classpath` lors de la compilation. Pour produire un JAR, le manifeste est
sous le dossier ressources/.


EXÉCUTION
---------

Se rendre dans le dossier du JAR généré, et exécuter la commande :

    java -jar CodeQualityEvaluator.jar {chemin}

où {chemin} est soit le chemin d'un dossier contenant du code JAVA, ou celui
d'un fichier source JAVA.

Il peut s'agir d'un chemin absolu ou relatif.

Deux fichiers seront générés dans le dossier d'où la commande est lancée :
- classes.csv
- paquets.csv
Les chemins dans ces fichiers seront relatifs au parent de l'emplacement fourni
comme argument dans la ligne de commande.


BIBLIOTHÈQUE EXTERNE UTILISÉE
-----------------------------

Ce programme utilise JavaParser pour l'analyse syntaxique du code. Cette
bibliothèque est disponible à l'adresse suivante :

    https://github.com/javaparser/javaparser/
