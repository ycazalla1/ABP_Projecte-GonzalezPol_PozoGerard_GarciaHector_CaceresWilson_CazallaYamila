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


CREATE DATABASE IF NOT EXISTS retrotenis;
USE retrotenis;

create table traduccions (
	clau varchar(50),
    text_catala varchar (50),
    text_castella varchar (50),
    text_angles varchar (50),
    primary key (clau)
);

INSERT INTO traduccions VALUES
('jugar', 'Jugar', 'Jugar', 'Play'),
('opcions', 'Opcions', 'Opciones', 'Options'),
('sortir', 'Sortir', 'Salir', 'Exit'),

-- Menú opcions
('idioma', 'Seleccionar Idioma', 'Idioma', 'Language'),
('resolucio', 'Seleccionar resolució', 'Resolución', 'Resolution'),
('volum', 'Volum', 'Volumen', 'Volume'),
('guardar', 'Guardar Opcions', 'Guardar Opciones', 'Save Options'),

-- Menú jugar
('configuracio_jugadors', 'Configuració dels Jugadors', 'Configuración de los Jugadores', 'Player Settings'),
('nivell', 'Nivell', 'Nivel', 'Level'),
('jugador1', 'Jugador 1', 'Jugador 1', 'Player 1'),
('jugador2', 'Jugador 2', 'Jugador 2', 'Player 2'),
('iniciar_joc', 'Iniciar Joc', 'Iniciar Juego', 'Start Game'),

-- Menú pausa
('renaudar', 'Renaudar', 'Reanudar', 'Resume'),
('pausa', 'Pausa', 'Pausa', 'Pause'),
('reiniciar', 'Reiniciar', 'Reiniciar', 'Restart'),
('salir_al_menu', 'Sortir del menu', 'Salir al menú', 'Exit to Menu');