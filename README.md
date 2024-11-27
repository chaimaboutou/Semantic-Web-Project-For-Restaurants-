# Restaurant Finder

Restaurant Finder est une application web permettant de rechercher des restaurants en fonction de la ville sélectionnée. L'utilisateur peut choisir une ville, afficher la liste des restaurants disponibles et explorer les options offertes par chaque restaurant.

## Fonctionnalités

- **Recherche de restaurants par ville** : L'utilisateur peut sélectionner une ville pour afficher les restaurants disponibles dans cette ville.
- **Affichage des restaurants** : Les restaurants sont présentés sous forme de liste avec des informations de base sur chaque restaurant.
- **Option "Tous"** : L'utilisateur peut sélectionner "All" pour afficher tous les restaurants disponibles, indépendamment de la ville.

## Technologies utilisées

- **Frontend** : React.js
- **Backend** : API REST avec Jetty (Java)
- **Gestion des requêtes API** : Fetch API pour récupérer les données
- **CSS** : Pour le style des composants de l'interface
- **Base de données RDF** : Utilisation de **Protege** pour la création du schéma de la base de données RDF, **SPARQL** pour l'interrogation des données, et **Apache Jena Fuseki** pour le stockage et l'accès aux données RDF.

## Base de données RDF

Dans ce projet, une base de données RDF (Resource Description Framework) a été utilisée pour gérer les informations sur les restaurants et les villes.

- **Protege** : Outil utilisé pour la création du schéma de la base de données RDF et la modélisation des données sous forme de triplets RDF.
- **SPARQL** : Langage de requête utilisé pour interroger et manipuler les données RDF stockées dans la base de données.
- **Apache Jena Fuseki** : Serveur de données RDF utilisé pour stocker, gérer et exposer les données RDF via des API REST. Les données sont accessibles à l'aide de requêtes SPARQL.

### Exemple d'interrogation SPARQL

Voici un exemple de requête SPARQL pour récupérer tous les restaurants d'une ville :

```sparql
PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>
PREFIX db: <http://example.org/db#>

SELECT ?restaurant ?name ?city
WHERE {
  ?restaurant rdf:type db:Restaurant .
  ?restaurant db:hasName ?name .
  ?restaurant db:locatedIn ?city .
  FILTER (?city = "Paris")
}
```
## Base de données RDF
Vous trouver le fichier DB_Fuseki.rdf le shema de la base de données que vous pouvez porteer dans dataset dans Fuseki Jena 



## Prérequis:
Node.js (version 14 ou plus récente)
npm (gestionnaire de paquets Node.js)
Java (version 8 ou plus récente)
Apache Jena Fuseki pour gérer la base de données RDF
Protege pour la modélisation des données RDF
SPARQL pour interroger la base de données RDF


### Installation et démarrage
**1. Cloner le projet**
Pour commencer, clonez ce dépôt sur votre machine locale :

git clone https://github.com/username/restaurant-finder.git
cd semantic-web-project-for-restaurants


**2. Installer les dépendances du frontend**

npm install

**3. Démarrer le serveur**
Pour démarrer l'application en mode développement, utilisez la commande suivante :
npm start
Cela démarrera l'application frontend sur http://localhost:3000.

**4. Démarrer l'API backend**
Si vous avez configuré l'API backend en Java avec Jetty, vous pouvez la démarrer avec la commande suivante (assurez-vous que votre serveur backend est configuré pour écouter sur le port 8080) :

mvn jetty:run

L'API backend sera alors accessible à http://localhost:8080/api/restaurant.

**5. Démarrer Fuseki**
Si vous avez configuré Apache Jena Fuseki pour stocker vos données RDF, vous devez démarrer Fuseki et connecter le serveur à votre base de données RDF. Voici une commande pour démarrer Fuseki :

fuseki-server --port=3030 --dataset=datasetRestaurant
Assurez-vous que les données RDF sont chargées dans votre serveur Fuseki.


## Screen Shoots : 


