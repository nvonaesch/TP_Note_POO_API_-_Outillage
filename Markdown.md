## TP3

### Etape 5

- **Web** : Permet de créer des applications web (APIs ou sites) et de gérer les requêtes HTTP.  
- **JPA** : Simplifie l’interaction avec les bases de données via un mapping objet-relationnel, en évitant d’écrire beaucoup de SQL.  
- **Hibernate** : Une implémentation de JPA, qui gère le mapping des objets Java vers les tables de la base de données.  
- **H2** : Une base de données légère en mémoire, idéale pour tester rapidement les fonctionnalités.  
- **DevTools** : Outil pour développeurs qui recharge automatiquement l’application à chaque modification du code.  
- **Thymeleaf** : Moteur de template pour générer des pages HTML dynamiques à partir de données.

### Etape 13

1. **Avec quelle partie du code avons-nous paramétré l'URL d'appel `/greeting` ?**  
   Avec l'annotation `@GetMapping("/greeting")` dans la classe `HelloWorldController`.

2. **Avec quelle partie du code avons-nous choisi le fichier HTML à afficher ?**  
   Avec la ligne `return "greeting";` qui indique que le fichier `greeting.html` doit être utilisé comme vue.

3. **Comment envoyons-nous le nom à qui nous disons bonjour avec le second lien ?**  
   En passant un paramètre `name` dans l'URL, récupéré grâce à `@RequestParam`.

### Étape 17 : Réponse à l'observation de la console H2
Après avoir relancé l'application, une nouvelle table appelée `Address` apparaît dans la base de données.  

**Pourquoi cette table a-t-elle été créée ?**  
Hibernate, en analysant l'annotation `@Entity` de la classe `Address`, a automatiquement généré une table pour cette classe dans la base de données.

### Étape 20 : Contenu du fichier `data.sql`
Après avoir ajouté et exécuté le script `data.sql`, les deux adresses spécifiées sont bien présentes dans la table `Address` lors de l'exécution d'une requête `SELECT`.

### Étape 22 : Réponse sur l'annotation `@Autowired`
L'annotation `@Autowired` permet d’injecter automatiquement une instance d’une classe (ici, `AddressRepository`) dans une autre classe (ici, `AddressController`). Cela permet d'utiliser les fonctionnalités du repository sans instanciation manuelle.


## TP4

### Etape 6

- Il est nécessaire d'utiliser une clef pour utiliser l'API, celle-ci nous donne avec le plan gratuit au maximum 500 requêtes/jour.
- L'url à appeller est la suivante: https://api.meteo-concept.com/api/forecast/daily afin de pouvoir effectuer notre requête.
- La méthode HTTP à utiliser est la méthode GET
- Afin de passer les bons paramètres, j'utilise un queryParam prenant en paramètre la longitude et la latitude
- L'information nécessaire dans la réponse se trouve dans la balise forecast, ensuite dans ce tableau, plusieurs valeurs peuvent être interessantes (températures max, min, heures de soleil etc...)
- L'information se trouve dans la clef tmin et tmax
- L'information se trouve dans la clef forecast


