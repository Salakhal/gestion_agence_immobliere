# 🏠 Gestion d'Agence Immobilière


---
## 1.Contexte

Le secteur de l'immobilier génère une grande quantité de données (propriétaires, biens, contrats de location). Pour une agence immobilière, la gestion efficace de ces informations est cruciale pour assurer un bon suivi de la clientèle et optimiser la rentabilité. Ce projet vise à informatiser les processus quotidiens d'une agence.

## 2.Problématique

Actuellement, la gestion des biens et des locations se fait souvent manuellement ou via des fichiers dispersés (Excel, papier), ce qui entraîne des risques d'erreurs, des pertes de temps dans la recherche d'informations et une difficulté à obtenir des statistiques fiables sur l'activité de l'agence.


## 🚀 3.Fonctionnalités Principales

### 3.1. Authentification & Sécurité
* **Login sécurisé :** Accès restreint via identifiant et mot de passe.
* **Récupération de mot de passe :** Fonctionnalité "Mot de passe oublié" (Simulation d'envoi ou via Email selon configuration).

### 3.2. Gestion (CRUD)
* **Gestion des Propriétaires :** Ajout, modification, suppression et listage des propriétaires.
* **Gestion des Biens :** Gestion des appartements, villas, etc., avec détails (Surface, Prix, Ville...).
* **Gestion des Locations :** Liaison entre un bien et un locataire/contrat.

### 3.3. Recherche Avancée
* Moteur de recherche filtrant les résultats par **Ville**, **Type de bien** ou **Nom de propriétaire**.

### 4.3. Tableau de Bord (Dashboard)
* **Indicateurs Clés (KPIs) :** Affichage du nombre total de biens, propriétaires et revenus.
* **Graphique Dynamique :** Histogramme visuel affichant les revenus locatifs par ville.

---
## 5. MCD (Modèle Conceptuel de Données)

Le système d'information repose sur les entités suivantes :

* **Utilisateur (Admin) :** Gère l'accès à l'application.
* **Propriétaire :** Possède un ou plusieurs biens.
* **Bien :** Appartient à un propriétaire et peut faire l'objet de locations.
* **Location :** Lie un bien à une période donnée et génère un revenu.

  ![MCD du projet](src/images/mcd.png)

  ## 6. Architecture du projet

L'application respecte une architecture en couches (Layered Architecture) pour séparer la logique métier de l'interface utilisateur :

* **Couche Présentation (Vues) :** Interfaces graphiques développées avec **Java Swing** (`MainFrame`, `LoginForm`, `DashboardForm`...).
* **Couche Service (DAO) :** Gestion de la logique métier et communication avec la base de données via **JDBC**.
* **Couche Modèle (Entities) :** Représentation des objets (Classes `Bien`, `Proprietaire`, `User`).
* **Base de Données :** Stockage persistant avec **MySQL**.

![Architecture Technique](src/images/architecture.png)

## 🛠️ Technologies Utilisées

* **Langage :** Java (JDK 8+)
* **Interface Graphique :** Swing (JFrame, JInternalFrame, JPanel)
* **Base de Données :** MySQL
* **Outils de Développement :** NetBeans IDE
* **Gestion de Base de Données :** phpMyAdmin / XAMPP ou WAMP
* **Bibliothèques externes :** `mysql-connector.jar` (Connexion BD), `javax.mail.jar` (Envoi emails).
---

## ⚙️ Installation et Configuration

Pour lancer le projet sur votre machine, suivez ces étapes :

### 1. Configuration de la Connexion
Vérifiez la classe `connexion.Connexion.java` si vos identifiants MySQL sont différents :
```java
String url = "jdbc:mysql://localhost:3306/agence_immo";
String user = "root";
String password = ""; // Ou votre mot de passe
```

## 2. Structure de la Base de Données

Le système repose sur quatre tables principales :

1.  **Users** : Informations d'authentification des agents (login, password, email pour la récupération).
2.  **Proprietaire** : Informations sur les propriétaires des biens (nom, contact, email, adresse).
3.  **Bien** : Détails des biens immobiliers (type, surface, prix, ville, statut).
4.  **Location** : Gestion des contrats de location (date début, date fin, montant).



# Vidéo sur les interfaces de l'application

Voici une démonstration complète de l'application illustrant le scénario : Login, Gestion des biens, Recherche et visualisation du Dashboard.

**Cliquez ici pour voir la vidéo** :
https://raw.githubusercontent.com/Salakhal/gestion_agence_immobliere/main/vidio_immo.mp4

------

**Projet Académique 2024/2025**

 **Réalisé par :** Salma LAKHAL
 
