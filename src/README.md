# Club Sport Manager

Club Sport Manager est une application dédiée à la gestion d'un club de sports de raquette, notamment le tennis (indoor), le badminton, et le squash. Conçue sur l'architecture MVC, cette application cible les propriétaires de clubs sportifs, leur offrant une solution complète pour le suivi des installations, des réservations, des abonnements, et l'état des joueurs.

L'application vise à appliquer les principes fondamentaux du framework de mapping objet-relationnel Hibernate, afin de simplifier et d'optimiser la gestion des données liées aux activités du club.

## Fonctionnalités

- **Gestion des joueurs** : Créer, lire, mettre à jour et supprimer des informations sur les joueurs.
- **Gestion des installations** : Suivre l'état des installations et planifier leur maintenance.
- **Réservations** : Permettre aux joueurs de réserver des installations pour des activités sportives.
- **Abonnements** : Gérer les différents types d'abonnements offerts aux joueurs.
- **Tarification flexible** : Les tarifs horaires varient selon le sport et le type d'abonnement, avec des options plus économiques pour les membres au forfait.
- **Rapports financiers** : Suivre les paiements et calculer le chiffre d'affaires mensuel.

## Technologies Utilisées

- Backend : Java avec Hibernate et Servlets
- Frontend : JSP et JSTL avec des feuilles de style CSS personnalisées
- Base de données : MySQL
- Serveur : Apache Tomcat

## Installation

1. Clonez le dépôt sur votre machine locale : git clone https://github.com/99IDRISS/club-sport-nsy135-.git
2. Importez le projet dans votre IDE (par exemple Eclipse).
3. Configurez votre serveur Tomcat et la base de données MySQL.
4. Exécutez l'application via votre IDE ou déployez le fichier WAR sur votre serveur Tomcat.

## Configuration de la Base de Données

1. Créez une nouvelle base de données MySQL.
2. Exécutez le script SQL pour initialiser la base de données.
3. Configurez le fichier `hibernate.cfg.xml` avec vos paramètres de connexion.

## Utilisation

- Accédez à l'application via `http://localhost:8080/nsy135` sur votre navigateur.
- Utilisez les menus pour naviguer entre les différentes fonctionnalités.
