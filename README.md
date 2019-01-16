Bienvenue dans la nouvelle application de Proxibanque Assurance !


Table des Matières : 
	1 - Installation
	2 - Utilisation
	3 - Ressources complémentaires
  
  
<h3>Installation</h3>

Avant de lancer ProxiBanqueSi, vous devez vous assurer que Java est bien installé sur votre appareil. Si ce n'est pas le cas, vous 
pouvez le télécharger à cette adresse : https://www.oracle.com/technetwork/java/javase/downloads/jre8-downloads-2133155.html
Pensez bien à vérifier le niveau de votre Système d'exploitation avant de télécharger. Vous pouvez vérifier votre système d'exploitation 
sous Windows dans Panneau de Configuration -> Système et Sécurité -> Système. Votre niveau de Système correspond à l'indication 
32bits ou 64bits, et vous devez télécharger la distribution de Java correpondante.

Vous devez également avoir un outil pour décompresser les fichiers installé sur votre ordinateur. Si vous n'en avez pas, 
vous pouvez en télécharger un gratuitement ici : https://www.7-zip.org/

Nous recommandons le navigateur Chrome de Google pour naviguer sur l'application (téléchargeable à cette adresse : https://www.google.com/chrome/ )

Pour information, l'application utilise un système de log pour vérifier son bon déroulement. Pour y avoir accès, il suffit de créer un dossier "logs" dans C://logs. L'application créera un fichier "proxibanquev4.log" et enregistrera les messages dedans.

Afin de pouvoir profiter de l'application , il est nécessaire de télécharger et d'installer Tomcat. Il est téléchargeable à l'adresse suivante : https://tomcat.apache.org/download-80.cgi
Veuillez choisir l'installation correspondante à votre système d'exploitation dans "Binary Distributions --> Core" pour la version la plus récente (version 8.5.37 au 16/01/2019).
Par exemple, pour Windows 7 ou supérieur, veuillez cliquer sur "64-bit Windows.zip".
Une fois l'archive téléchargée, veuillez l'extraire sur votre PC, le dossier de destination n'ayant pas d'importance.

Il est également nécessaire de définir une nouvelle variable d'environnement "JRE_HOME". Pour cela, dans l'explorateur de fichiers, faites clic droit sur "Ce PC" -> Propriétés. Selectionnez "Paramètres système avancés" sur la gauche. Dans la fenêtre ouverte, choisissez "variables d'environnement". Sous le tableau "Variables système", cliquez sur "Nouvelle...". En "Nom de variable" indiquez JRE_HOME, et en "Chemin de variable" il faut récupérer le chemin vers l'installation du jre ( par défaut C://Programmes/java/jrexxx ).

Pour avoir accès aux données client, veuillez télécharger l'application MySQL à l'adresse suivante : https://dev.mysql.com/downloads/windows/installer/8.0.html
Sélectionnez l'installation à 313Mo. Vérifiez bien que MySQL Workbench est installé.


Afin de deployer l'application ProxiBanque, veuillez coller l'archive proxibanquev4.war dans le dossier webapps de votre dossier apache-tomcat-8.5.xx
Ensuite, allez dans le dossier bin et double cliquez sur le fichier "startup.bat".
Attendez qu'un dossier proxibanquev4 apparaisse dans le dossier webapps.


Veuillez ensuite lancer MySQL Workbench et créer un nouveau schéma nommé "proxibanquev4".
Ensuite, sélectionnez "Server" dans le menu supérieur puis "Data import". Sélectionnez l'option "Import from Self-Contained File" et renseignez le chemin vers le fichier structure.sql fourni. Indiquez "proxibanquev4" dans "Default Target Schema".
Enfin, répétez l'opération précédente avec le script "donnees.sql" fourni afin d'importer les données proprement dites.


<h3>Utilisation</h3>

Pour lancer l'application, lancez Chrome et rentrez l'adresse suivante : http://localhost:8080/proxibanquev4
Cette application vous permet de gérer les différents sondages déployés dans les agences locales Proxibanque. Vous pouvez clôturer le sondage en cours, créer un nouveau sondage qui sera directement visible dans la partie "front-end" de l'application ou encore consulter l'ensemble des sondages déjà effectués.


<h3>Ressources complémentaires</h3>

L'application ProxiBanqueSI est livrée avec :
	- Deux diagrammes UML : 
		- Le diagramme de cas d'utilisation;
		- Le diagramme de classe;
	- Les screens des maquettes d'écran. 
	- Le diagramme des bases de données SQL
	- Les scripts sql pour générer la base de données. 

Afin de profiter également de l'application "front-end", veuillez également suivre les instructions à cette adresse : https://github.com/Khantain/proxibanquev4_angular
