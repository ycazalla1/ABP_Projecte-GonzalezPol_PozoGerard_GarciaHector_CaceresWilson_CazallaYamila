CREATE DATABASE tennis;
use tennis;

CREATE TABLE jugadors (
    id_jugador int auto_increment PRIMARY KEY,
    nom varchar(50),
    punts int
);




CREATE DATABASE IF NOT EXISTS retrotenis;
USE retrotenis;

create table traduccions (
	clau varchar(50),
    idioma varchar (10),
    text TEXT,
    primary key (clau, idioma)
);


INSERT INTO traduccions  VALUES
('jugar', 'cat', 'Jugar'),
('opcions', 'cat', 'Opcions'),
('sortir', 'cat', 'Sortir'),
('triar_idioma', 'cat', 'Tria un idioma');

INSERT INTO traduccions (clau, idioma, text) VALUES
('jugar', 'es', 'Jugar'),
('opcions', 'es', 'Opciones'),
('sortir', 'es', 'Salir'),
('triar_idioma', 'es', 'Elige un idioma');

INSERT INTO traduccions (clau, idioma, text) VALUES
('jugar', 'en', 'Play'),
('opcions', 'en', 'Options'),
('sortir', 'en', 'Exit'),
('triar_idioma', 'en', 'Choose a language');