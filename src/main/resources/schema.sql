CREATE TABLE Pokemon (
Id_pokemon INT,
Nombre VARCHAR(4000),
Categoría VARCHAR(4000),
Habilidad VARCHAR(4000),
Peso VARCHAR(4000),
Altura VARCHAR(4000),
Generacion VARCHAR(4000),
Evoluciones VARCHAR(4000),
Tipo VARCHAR(4000),
CONSTRAINT pk_pokemons PRIMARY KEY (Id_pokemon)
);
CREATE TABLE Tipo (
Id_pokemon INT,
Tipo VARCHAR(4000),
CONSTRAINT fk_tipos FOREIGN KEY (Id_pokemon) REFERENCES Pokemon(Id_pokemon)
);
CREATE TABLE Movimientos(
Id_pokemon INT,
Nivel VARCHAR(4000),
Nombre VARCHAR(4000),
Tipo VARCHAR(4000),
Clase VARCHAR(4000),
CONSTRAINT fk_movimientos FOREIGN KEY (Id_pokemon) REFERENCES Pokemon(Id_pokemon)
);

