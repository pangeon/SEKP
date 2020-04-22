### REQUIREMENTS

---

### DEPLOYMENT GUIDE

---

##### PREPARE SERWER AND DATASOURCE
For the application to be properly implemented on the local GlassFish server, we must do the following:
* Set up a database connection
* Create appropriate data structures
* Define the authentication space on GlassFish (Fig. 15 in *Startup Guide*)

To see a program in action, you must create, using SQL,
first accounts with initial data.
 
(it would be good if at least one account would remain in the role of admin).



In my application, the finished glassfish-resources file looks like this 
(details about creating chapter 1.2 b. *Startup Guide*):

```xml
<resources>  
    <jdbc-resource enabled="true" jndi-name="jdbc/system" object-type="user" pool-name="Pool-System">    
        <description/>  
    </jdbc-resource>  
    <jdbc-connection-pool .../>    
        <property name="URL" value="jdbc:mysql://localhost:3306/system?zeroDateTimeBehavior=convertToNull"/>    
        <property name="serverName" value="localhost"/>    
        <property name="PortNumber" value="1527"/>    
        <property name="DatabaseName" value="system"/>    
        <property name="User" value="root"/>    
        <property name="Password" value="admin"/>  
    </jdbc-connection-pool> 
</resources>
```
In the file presented above we define:
* Database URL: host name, port, database name, configuration settings downloaded from the address
* GlassFish server port
* database name
* user name
* user password

The defined settings should allow the application server to work with the database. Permissions should be shared
for MySQL server and application server.

##### OPTIONAL POLISH CHARSET CONFIG

You need to determine the appropriate coding method before creating the database structure, 
this will ensure the possibility of handling Polish characters.

###### XAMPP 
The following changes should be made to my.ini (xampp/mysql/bin/):

```
[mysqld] character-set-server = utf8 collation-server = utf8_unicode_ci 
[mysql] default-character-set = utf8 
[mysqldump] default-character-set = utf8

```
##### CONFIG ORM - USAGE JPA

For tables to be generated, a change in persistence.xml must be made.

```xml
<persistence version="2.1" ...>
  <persistence-unit name="System.PU" transaction-type="JTA">
    <provider>org.eclipse.persistence.jpa.PersistenceProvider</provider>
    <jta-data-source>jdbc/system</jta-data-source>
    <class>app.model.entity.Education</class>
    <class>app.model.entity.HistoryWork</class>
    <class>app.model.entity.Qualifications</class>
    <class>app.model.entity.Trainings</class>
    <class>app.model.entity.Users</class>
    <exclude-unlisted-classes>true</exclude-unlisted-classes>
    <shared-cache-mode>NONE</shared-cache-mode>
    <validation-mode>NONE</validation-mode>
    <properties>
      <property name="eclipselink.logging.level" value="INFO"/>
      <property name="eclipselink.canonicalmodel.subpackage" value="System.PU"/>
      <property name="javax.persistence.schema-generation.database.action" value="none"/>
    </properties>
  </persistence-unit>
</persistence>
```

Change make our properties javax.persistence.schema-generation.database.action to Create.
This option generates available data, after creating a dataset it is a good habit to re-select None.

###### INSERT DATA INTO TABLES

The final stage is loading the sample data.

```MYSQL
    INSERT INTO `users` (`user_id`, `activation_date`, `email`, `login`, `nick_name`, `password`, `role_name`, `version`) VALUES
        (1, '2015-12-03', 'pangeon@op.pl', 'pangeon11', 'pangeon11_nick', 'd9ff6503ab0051f7bc2b0f75515e6c71386a50da615778bccfd7aa6be08d828a', 'admin_role', 3),
        (2, '2015-12-03', 'pangeon22@tlen.pl', 'pangeon22', 'pangeon22_nick', 'b595d626857208ecb42312ab294768b60e1be27346e97a28930064c6da6d52aa', 'user_role', 5);
    
    INSERT INTO `education` (`id`, `education_begin`, `education_end`, `kind_of_school`, `name_of_school`, `specialization`, `user_id`) VALUES
        (1, '1987-09-12', '1990-09-13', 'uczelnia wyższa', 'Wyższa Szkoła Informatyki i Nauk o Technologii', 'robotyka', 1),
        (2, '1990-10-16', '1995-09-13', 'studia podyplomowe', 'Akademia Nauki i Techniki ', 'mechanika działania obwodów energetycznych', 1),
        (3, '1995-10-16', '2000-09-13', 'studia doktoranckie', 'Politechnika Łódzka', 'zastosowanie informatyki w medycynie', 1),
        (4, '1987-09-12', '1995-09-13', 'studia doktoranckie', 'Politechnika Łódzka', 'aplikacje w chmurze, transakcje', 2);
    
    INSERT INTO `historywork` (`id`, `acquired_skills`, `character_of_work`, `company`, `position`, `work_begin`, `work_end`, `user_id`) VALUES
        (1, 'java ee, html', 'budowanie aplikacji webowych', 'Firma', 'programista', '1998-05-08', '2010-09-12', 1),
        (2, 'photoshop', 'tworzenie banerów i infografik', 'Instytucja X', 'grafik', '1999-05-03', '2010-05-12', 1),
        (3, 'projektowanie relacyjnych baz danych, UML, C++', 'administracja bazami danych', 'Miejsce B', 'administrator baz danych', '2000-06-03', '2005-06-12', 1),
        (4, 'ejb, jsf, sql', 'administracja zasobami wirtualnymi uczelni, prowadzenie prac magistrantów', 'Politechnika Łódzka', 'wykładowca', '1999-05-03', '2015-06-12', 2),
        (5, 'spring, hibernate', 'refaktoring aplikacji', 'Comarch', 'programista', '2015-05-03', '2015-06-11', 2);
    
    INSERT INTO `qualifications` (`id`, `foreign_languages`, `frameworks`, `other_skills`, `programming_language`, `software`, `user_id`) VALUES
        (1, 'ang, rus', 'borland c++', 'graficzne', 'c++', 'netbeans', 1),
        (2, 'niem', 'ejb, spring', 'debugowanie aplikacji', 'java', 'eclipse', 1),
        (3, 'ang', 'zend', 'sklepy internetowe', 'php', 'phpDesigner', 1),
        (4, 'ang', 'brak', 'automatyka wykonywania kodu', 'python', 'notepad++', 2);
    
    INSERT INTO `trainings` (`id`, `content_training`, `training_begin`, `training_end`, `training_time`, `user_id`) VALUES
        (1, 'Projektowanie stron www', '1998-02-13', '1998-09-16', 130, 1),
        (2, 'Podstawy Javy EE', '2005-02-13', '2005-09-22', 60, 1),
        (3, 'UML, xml', '2008-12-18', '2009-01-01', 30, 1),
        (4, 'Nauczenie metodą Bowlinga', '1998-02-13', '1998-09-22', 300, 2),
        (5, 'Graficzna oprawa aplikacji Java EE', '1999-02-18', '1999-02-14', 40, 2);
    
    INSERT INTO `workers` (`user_id`, `birth_date`, `city`, `country`, `first_name`, `last_name`, `phone_number`, `street`, `zip_code`) VALUES
        (1, '1987-09-14', 'Łódź', 'Polska', 'Kamil', 'Cecherz', '798 422 996', 'Powstańców Wielkopolskich 24/17', '91-018'),
        (2, '1986-02-15', 'Łódź', 'Polska', 'Bolesław', 'Karbowańczyk', '798 422 888', 'Klonowa 10', '91-099');
```
Account passwords and logins are as follows:
* login: pangeon11 - password: pangeon11 
* login: pangeon22 - password: pangeon22

After entering data in the system's login panel, the user is authenticated and will gain access to all functionalities.


