CREATE DATABASE tennis;
use tennis;

CREATE TABLE jugadors (
    id_jugador int auto_increment PRIMARY KEY,
    nom varchar(50),
    punts int
);