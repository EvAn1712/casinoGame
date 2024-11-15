# Casino 🎰

## 1. Prérequis et Lancement du Projet

### Prérequis

Avant de commencer, assurez-vous d'avoir installé les outils suivants :
- **Java 17+**
- **Spring Boot**
- **Maven** (pour gérer les dépendances)
- **IDE** de votre choix (par exemple IntelliJ IDEA, Eclipse)
- **Docker**

### Téléchargement du Projet

1. Clonez le projet depuis GitHub.
2. Ouvrez le projet dans votre IDE.

### Base de Données

1. Ajoutez le fichier `.env` joint à votre projet dans le dossier `back-casino`.
2. Créez le conteneur de la base de données PostgreSQL avec Docker.
3. Connectez-vous à la base de données depuis votre IDE (le nom, l'utilisateur et le mot de passe sont définis dans le fichier `.env`).
4. Exécutez le script `1_TABLES.sql` situé dans le dossier `initbd` pour construire les tables nécessaires.

### Configuration de l'environnement

1. Dans votre IDE, allez dans **Edit Configurations**.
2. Sélectionnez `BackCasinoApplication`.
3. Cochez **Enable env file** et ajoutez le fichier `.env`.

Une fois ces étapes terminées, vous pouvez lancer le backend depuis votre IDE.

---

## 2. Lancer le Frontend

1. Clonez le projet frontend depuis GitHub.
2. Installez les dépendances nécessaires avec `npm install`.
3. Lancez le frontend avec `npm start`.

Une fois le projet lancé, vous arriverez sur une **page de connexion** où vous pouvez :
- Vous inscrire ou vous connecter avec un compte existant.
- Sur la **page d'accueil**, vous pouvez :
  - Visualiser vos statistiques de jeu.
  - Lancer une partie de Blackjack en choisissant votre mise.

---

## 3. Règles du Blackjack 🃏

### Objectif du Jeu

L'objectif est d'obtenir une main dont la somme des cartes est aussi proche que possible de 21 **sans dépasser ce total**. Vous jouez contre le croupier.

### Valeur des Cartes

- **Cartes numérotées (2 à 10)** : La valeur correspond au chiffre indiqué.
- **Figures (Valet, Dame, Roi)** : Chaque figure vaut **10 points**.
- **As** : Peut valoir **1 point** ou **11 points**, selon ce qui est le plus avantageux.

### Déroulement du Jeu

1. **Distribution des Cartes** :
   - Le joueur et le croupier reçoivent chacun **deux cartes**.
   - Les cartes du joueur sont visibles, tandis que le croupier a une carte visible et une carte cachée.
   
2. **Tour du Joueur** :
   - Le joueur peut choisir de **tirer** (recevoir une nouvelle carte) ou de **rester** (conserver sa main actuelle).
   - Si la somme des cartes dépasse **21**, le joueur **buste** et perd la partie.
   
3. **Tour du Croupier** :
   - Une fois le tour du joueur terminé, le croupier révèle sa carte cachée.
   - Il choisit de **tirer** ou de **rester** jusqu'à atteindre un minimum de **17**.
   - Si la somme des cartes du croupier dépasse **21**, il **buste** et le joueur gagne.

### Résultat

- Si la main du joueur est plus proche de **21** que celle du croupier, le joueur gagne.
- Si la main du croupier est plus proche de **21**, le croupier gagne.
- En cas d'égalité, la mise est retournée au joueur.

### Blackjack

- Si le joueur reçoit un **As** et une carte valant **10** dans ses deux premières cartes, il a un **Blackjack**.
  - Il gagne immédiatement, sauf si le croupier a également un Blackjack (dans ce cas, il y a égalité).

### Mise

- Le joueur place une mise avant chaque partie.
- En cas de victoire, le joueur récupère sa mise doublée (sauf en cas de Blackjack, où il gagne 1,5 fois la mise).

---

## 4. Règles Spécifiques du Croupier

- Le croupier **doit tirer des cartes jusqu'à atteindre 17**.
- Le croupier **buste** si sa main dépasse 21.

---

### Bon jeu ! 🎉
