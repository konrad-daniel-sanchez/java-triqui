-- Crea la tabla en la base de datos:

CREATE TABLE IF NOT EXISTS EstadoJuego (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    tamano INTEGER,
    simboloActual CHAR(1),
    turnoJugadorA BOOLEAN,
    matriz TEXT,
    noMovimientos INTEGER,
    triqui BOOLEAN,
    finJuego BOOLEAN
);

-- Ingresa un registro en la base de datos:

INSERT INTO EstadoJuego(tamano, simboloActual,turnoJugadorA,matriz,noMovimientos,triqui,finJuego) VALUES(3, 'X', true, '[]', 0, false, false);
