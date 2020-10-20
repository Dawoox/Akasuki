# Akasuki
> Un bot Discord fait en java simple et amusant, construit via Discord4J.

Cette page est disponible en [Anglais](../readme.md)

## Sommaire
* [Informations](#Informations)
* [Captures d'écrans](#captures-dcran)
* [Technologies](#technologies)
* [Installation](#installation)
* [Fonctionnalités](#fonctionnalits)
* [Status](#statut)
* [Inspiration](#inspiration)
* [Support](#support)
* [NB](#nb)
* [Contact](#contact)

## Informations
Ce projet est un bot tolérant LGBT appelé Akasuki, elle peut vous trouver des gifs pour vous permettre de vous exprimer, 
vous aider dans la modération de votre serveur, ou même encourager vos membres à 
être actif via un système d'inventaires, de récompenses et de niveaux !

## Captures d'écran
![Exemple de la commande ban](../img/ban.gif)
![Exemple de la commande hug](../img/hug.gif)
![Exemple de la commande userinfo](../img/userinfo.png)

## Technologies
Akasuki est créée grâce à :
* Discord4J: 3.1.1
* Lavaplayer: 1.3.50
* Logback: 1.2.3
* MongoDB Sync Driver: 4.1.0
* Maven Compiler: 3.8.1
* ImageJ: 1.53e
* Rssreader: 2.3.1
	
## Installation  
```
$ git clone https://github.com/Dawoox/Akasuki.git
$ cd ./Akasuki
$ mvn clean install
$ touch config.properties
$ vim config.properties

token={votre token}
db_user={nom d'utilisateur de la base de données}
db_passwd={mot de passe de la base de données}
db_ip={ip de la abse de données}
db_retry={true/false La base de données va essayer de se reconnecter après une erreure}
db_main={le nom de la base de données}
nasa_api_key={une clé de l'API de la NASA}
~
~

$ java -jar Akasuki-jar-with-dependencies.fr
```
> **Attention : Nous ne donnons aucune aide sur comment exécuter le bot vous-même et comment le faire fonctionner**

## Fonctionnalités

Fonctions de la version stable
* Gif aléatoires (*kiss, *hug, etc)
* Informations sur un utilisateur
* Moderation (*ban, *kick, *warn etc)

To-do list:
* Mariage
* Plus de gif
* Prefix personnalisé
* Système de module
* Système d'inventaire

## Statut
Maintained

## Inspiration
Le système de gif est inspiré de [Koya](https://koya.gg/) <br>
La gestion de la base de données pour le système d'inventaire vient de PWK (un bot privé)

## Support
Si vous avez besoin d'aide avec Akasuki, sur comment la configurer ou si vous avez trouvé des bogues, venez sur le [Discord](https://discord.com/invite/973paeN)

## NB
Je suis au courant des mots de passe et identifiants qui ont fuités au début du projet, ils ont été modifiés depuis.
Ce n'est plus une faille de sécurité.

## Contact
Crée par [@Dawoox](https://www.github.com/dawoox) - n'hésitez pas à me contacter !