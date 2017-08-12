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

