CREATE TABLE Tipo (
    Id_tipo serial NOT NULL,
    Nombre VARCHAR(4000),
    CONSTRAINT pk_tipo PRIMARY KEY (Id_tipo)
);

CREATE TABLE Movimientos(
    Id_movimientos serial NOT NULL,
    Nivel VARCHAR(4000),
    Nombre VARCHAR(4000),
    Tipo VARCHAR(4000),
    Clase VARCHAR(4000),
    CONSTRAINT pk_movimientos PRIMARY KEY (Id_movimientos)
);

CREATE TABLE Pokemon (
    Id_pokemon serial NOT NULL,
    Id_movimientos INT,
    Id_tipo INT,
    Nombre VARCHAR(4000),
    Categoría VARCHAR(4000),
    Habilidad VARCHAR(4000),
    Peso VARCHAR(4000),
    Altura VARCHAR(4000),
    Generacion VARCHAR(4000),
    Evoluciones VARCHAR(4000),
    CONSTRAINT pk_pokemons PRIMARY KEY (Id_pokemon),
    CONSTRAINT fk_pokemons_tipo FOREIGN KEY (Id_tipo)
        REFERENCES Tipo (Id_tipo) MATCH SIMPLE
        ON UPDATE NO ACTION ON DELETE NO ACTION,
    CONSTRAINT fk_pokemons_movimientos FOREIGN KEY (Id_movimientos)
        REFERENCES Movimientos (Id_movimientos) MATCH SIMPLE
        ON UPDATE NO ACTION ON DELETE NO ACTION
);