# Documentation Technique : Pipeline DevSecOps (EcoMove)

Ce document explique la structure et le fonctionnement du pipeline d'intégration et de déploiement continu (CI/CD) mis en place pour le projet EcoMove. Il est conçu pour servir de support lors d'une présentation devant un jury.

## 1. Architecture du Pipeline (Workflow)

Le pipeline est défini dans le fichier `.github/workflows/main.yml`. Il s'exécute automatiquement à chaque `push` sur la branche `main`.

### Les 4 étapes clés (Jobs/Steps) :

1.  **Compilation & Tests (Maven)** : 
    - **Rôle** : Vérifier que le code compile et que les tests unitaires passent.
    - **Outil** : `mvnw clean verify`.
    - **Base de données** : Utilisation d'un service Docker MySQL temporaire pour permettre aux tests d'intégration de s'exécuter dans un environnement réel.

2.  **Analyse de Code (SonarCloud)** :
    - **Rôle** : Scanner le code pour détecter les bugs, les failles de sécurité et la dette technique.
    - **Outil** : `sonar-maven-plugin`.
    - **Particularité** : L'analyse est déportée sur SonarCloud pour garantir une visibilité totale sans contrainte de serveur local.

3.  **Packaging (Artifact)** :
    - **Rôle** : Générer le fichier exécutable `.jar` de l'application.
    - **Action** : `actions/upload-artifact` pour sauvegarder le livrable.

4.  **Conteneurisation (Docker)** :
    - **Rôle** : Créer une image Docker de l'application et la publier sur Docker Hub.
    - **Outils** : `docker/login-action` et `docker/build-push-action`.

---

## 2. Configuration Maven (pom.xml)

Pour que SonarCloud puisse analyser le projet Java, nous avons configuré le `sonar-maven-plugin`. 

- **Version** : 4.0.0.4121 (Version stable et compatible Cloud).
- **Propriétés** : Le plugin utilise les paramètres passés par GitHub Actions (`projectKey`, `organization`, `token`) pour identifier le projet sur le Cloud.

---

## 3. Focus : Les Quality Gates (Portes de Qualité)

C'est l'élément le plus important pour garantir la "Santé" du projet.

### Qu'est-ce qu'une Quality Gate ?
C'est un ensemble de conditions que le code **doit** respecter pour être validé. Si une condition n'est pas remplie, le pipeline GitHub Actions s'arrête et affiche une erreur (rouge), empêchant ainsi la mise en production d'un code défectueux.

### Nos indicateurs clés :
- **Coverage (Couverture)** : Pourcentage de code testé. (Ex: > 40% ou 80%).
- **Bugs** : Doit être égal à 0 pour les bugs critiques.
- **Vulnerabilities** : Doit être égal à 0 pour garantir la sécurité.
- **Maintainability (Dette technique)** : Mesure la facilité à modifier le code dans le futur.

---

## 4. Glossaire pour le Jury (Speech)

Si le jury vous pose des questions, voici les définitions clés à utiliser :

- **CI/CD** : *Continuous Integration / Continuous Deployment*. C'est l'automatisation de la vérification et de la livraison du code.
- **Dépendance Circulaire** : Un problème de conception où deux classes dépendent l'une de l'autre (Sonar le détecte).
- **Dette Technique** : Le coût des corrections futures nécessaire à cause d'un code écrit trop rapidement ou sans respecter les standards.
- **False Positive** : Une erreur signalée par Sonar qui n'en est pas vraiment une (on peut la marquer comme "Won't Fix").
- **Secrets GitHub** : Variables cryptées (jetons, mots de passe) permettant au pipeline de se connecter à Docker ou Sonar en toute sécurité.

---

## 5. Pourquoi ce choix technologique ?
- **GitHub Actions** : Intégration native, pas de serveur à gérer.
- **SonarCloud** : Visibilité partageable avec l'équipe et rapports détaillés.
- **Docker** : "Write once, run anywhere". L'application fonctionnera de la même manière sur votre PC et sur n'importe quel serveur.
