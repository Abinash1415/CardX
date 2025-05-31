--- Pré-requis

- Java 11+ (testé avec JDK 20)
- Apache NetBeans 15+ (ou autre IDE compatible Maven)
- GlassFish Server 7+
- MySQL ou MariaDB + PhpMyAdmin (via WAMP ou XAMPP)
- Maven

--- Import rapide

1. Lancer PhpMyAdmin : 'http://localhost/phpmyadmin'
2. Créer une base 'jeu'
3. Importer le script 'jeu-db.sql'

--- Problème au lancement 

1. Ouvrir le projet avec NetBeans
2. Vérifier que GlassFish est bien configuré
3. S'assurer que le driver JDBC est bien installé :
   - Placer `mysql-connector-j-8.0.33.jar` dans : 'glassfish/domains/domain1/lib/'
   - Puis redémarrer GlassFish
4. Lancer le projet

--- Utilisateurs test

| Login   | Mot de passe | Accès                |
|---------|--------------|----------------------|
| meyer   | 1111         | ✅ autorisé (adulte) |
| dupont  | 2222         | ✅ autorisé (adulte  |
| legrand | 3333         | ❌ refusé (mineur)   |

---
