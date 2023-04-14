# Projet carte des festivals Bretons

## 1 - Description du projet

Projet permettant l'ajout de festivals dans la région Bretagne, France, via un site web, avec une carte qui affiche leur
position, la possibilité de modifier ou supprimer un festival.

## 2 - Pages du site :

Page du site :

- index.html : page d'accueil qui contient la liste des festivals avec une carte qui affiche leur position, de plus si
  on clique sur le nom du festival dans le tableau cela zoom sur le marqueur présent sur la map.
- inscription.html : permet l'ajout d'un festival dans la liste de la page index, celui-ci est enregistré dans la base
  de donnée du site, de plus on peux directement le positionner sur la carte afin de récupérer sa géolocalisation (
  latitude, longitude).
- modification.html : Permet la modification ou la suppression d'un festival existant, cela le supprime également dans
  la base de données.

## 3 - Outils de réalisation :

Code réalisé avec : Intellij  
Outils collaboratifs : GitHub, Microsoft Teams  
Projet réalisé en Java, HTML, CSS et Javascript  
Framework utilisé : Spring Boot

### Bibliothèques utilisées :

- Spring Boot DevTools
- Spring Web
- Mysql driver
- Spring Date JPA
- Thymeleaf

## 4 - Installation du projet :

- Logiciel requis : Intellij
- Une base de donnée avec une table "festivals"
- Attention penser à changer les informations de la classe : application.properties afin d'adapter le code pour que
  celui-ci fonctionne avec votre base de donnée.

## 5 - Equipes :

Répartition des taches dès le démarrage du projet.  
Travail partagé entre les deux collaborateurs pour la réalisation du code.  
Après test d'une fonctionnalité, transfert vers GitHub.

### Merci d'avoir pris le temp de lire le ReadMe
