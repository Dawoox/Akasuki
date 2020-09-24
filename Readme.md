# Akasuki
> Simple and easy to custom Java Discord bot, build with Discord4J and many more libraries.

## Table of contents
* [General info](#general-info)
* [Technologies](#technologies)
* [Setup](#setup)
* [Features](#features)
* [Status](#status)
* [Inspiration](#inspiration)
* [NotaBene](#nb)
* [Contact](#contact)

## General info
This project use Circle CI and JUnit Jupiter for testing and CI/CD
	
## Technologies
Yua is created with:
* Discord4J: 3.1.1
* Lavaplayer: 1.3.50
* Logback: 1.2.3
* MongoDB Java Driver: 2.12.3
* MongoDB Sync Driver: 4.1.0
* Maven Compiler: 3.8.1
	
## Setup  
Note: You must first install a [MongoDB database](https://docs.mongodb.com/manual/installation/).

#### Using git clone:  
```
$ git clone https://github.com/Dawoox/Akasuki.git
$ cd ./Akasuki
$ mvn clean install
$ java -jar Akasuki-jar-with-dependencies.fr {mongodb_username} {mongodb_password}
```

#### Using docker:
> Not currently available

## Features
List of commands ready and TODOS for futur developement:
* Random gif (*kiss, *hug, etc)
* UserInfo

To-do list:
* Marry
* More gifs
* Moderation (*ban, *kick, *warn etc)
* Prefix customization
* Module system
* Inventory and items system

## Status
Maintained

## Inspiration
The gif system is inspired by the bot Koya

## NB

I know at the beginning of this project some Discord token and database id leaked, these id had been changed a long time ago, and the IPs have changed.
This is no longer a security flaw, the problem has been solved.

## Contact
Created by [@Dawoox](https://www.github.com/dawoox) - feel free to contact me!
