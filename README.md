# 🏠 Gestion d'Agence Immobilière


---
## Contexte

Le secteur de l'immobilier génère une grande quantité de données (propriétaires, biens, contrats de location). Pour une agence immobilière, la gestion efficace de ces informations est cruciale pour assurer un bon suivi de la clientèle et optimiser la rentabilité. Ce projet vise à informatiser les processus quotidiens d'une agence.

## Problématique

Actuellement, la gestion des biens et des locations se fait souvent manuellement ou via des fichiers dispersés (Excel, papier), ce qui entraîne des risques d'erreurs, des pertes de temps dans la recherche d'informations et une difficulté à obtenir des statistiques fiables sur l'activité de l'agence.


## 🚀 Fonctionnalités Principales

### 1. Authentification & Sécurité
* **Login sécurisé :** Accès restreint via identifiant et mot de passe.
* **Récupération de mot de passe :** Fonctionnalité "Mot de passe oublié" (Simulation d'envoi ou via Email selon configuration).

### 2. Gestion (CRUD)
* **Gestion des Propriétaires :** Ajout, modification, suppression et listage des propriétaires.
* **Gestion des Biens :** Gestion des appartements, villas, etc., avec détails (Surface, Prix, Ville...).
* **Gestion des Locations :** Liaison entre un bien et un locataire/contrat.

### 3. Recherche Avancée
* Moteur de recherche filtrant les résultats par **Ville**, **Type de bien** ou **Nom de propriétaire**.

### 4. Tableau de Bord (Dashboard)
* **Indicateurs Clés (KPIs) :** Affichage du nombre total de biens, propriétaires et revenus.
* **Graphique Dynamique :** Histogramme visuel affichant les revenus locatifs par ville.

---

## 🛠️ Technologies Utilisées

* **Langage :** Java (JDK 8+)
* **Interface Graphique :** Swing (JFrame, JInternalFrame, JPanel)
* **Base de Données :** MySQL
* **Outils de Développement :** NetBeans IDE
* **Gestion de Base de Données :** phpMyAdmin / XAMPP ou WAMP

---

## ⚙️ Installation et Configuration

Pour lancer le projet sur votre machine, suivez ces étapes :

### 1. Base de Données
1.  Assurez-vous d'avoir un serveur MySQL lancé (via XAMPP ou WAMP).
2.  Ouvrez **phpMyAdmin**.
3.  Créez une base de données nommée `agence_immo`.
4.  Importez le fichier **`agence_immo.sql`** fourni dans le dossier `database` ou à la racine du projet.

### 2. Configuration de la Connexion
Vérifiez la classe `connexion.Connexion.java` si vos identifiants MySQL sont différents :
```java
String url = "jdbc:mysql://localhost:3306/agence_immo";
String user = "root";
String password = ""; // Ou votre mot de passe

## Structure de la Base de Données

Le système repose sur quatre tables principales :

1.  **Users** : Informations d'authentification des agents (login, password, email pour la récupération).
2.  **Proprietaire** : Informations sur les propriétaires des biens (nom, contact, email, adresse).
3.  **Bien** : Détails des biens immobiliers (type, surface, prix, ville, statut).
4.  **Location** : Gestion des contrats de location (date début, date fin, montant).

### Script de la Base de Données

```sql
CREATE DATABASE IF NOT EXISTS agence_immo;
USE agence_immo;

-- Table des Utilisateurs (Administrateurs)
CREATE TABLE users (
    id INT AUTO_INCREMENT PRIMARY KEY,
    login VARCHAR(50) NOT NULL,
    password VARCHAR(50) NOT NULL,
    email VARCHAR(100) -- Pour la récupération du mot de passe
);

-- Table des Propriétaires
CREATE TABLE proprietaire (
    id_prop INT AUTO_INCREMENT PRIMARY KEY,
    nom VARCHAR(100) NOT NULL,
    contact VARCHAR(50),
    email VARCHAR(100),
    adresse VARCHAR(255)
);

-- Table des Biens Immobiliers
CREATE TABLE bien (
    id_bien INT AUTO_INCREMENT PRIMARY KEY,
    type VARCHAR(50) NOT NULL, -- Appartement, Villa, etc.
    surface DOUBLE,
    prix_mensuel DOUBLE NOT NULL,
    ville VARCHAR(50),
    disponible BOOLEAN DEFAULT TRUE,
    id_prop INT,
    FOREIGN KEY (id_prop) REFERENCES proprietaire(id_prop) ON DELETE CASCADE
);

-- Table des Locations
CREATE TABLE location (
    id_loc INT AUTO_INCREMENT PRIMARY KEY,
    date_debut DATE,
    date_fin DATE,
    montant DOUBLE,
    id_bien INT,
    FOREIGN KEY (id_bien) REFERENCES bien(id_bien) ON DELETE CASCADE
);

-- Insertion admin par défaut
INSERT INTO users (login, password, email) VALUES ('admin', '1234', 'votre_email@gmail.com');
