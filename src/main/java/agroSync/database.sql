CREATE DATABASE IF NOT EXISTS Agro_Sync;
USE Agro_Sync;

-- Tablas
CREATE TABLE IF NOT EXISTS Usuario
(
    id         INT AUTO_INCREMENT PRIMARY KEY,
    nombre     VARCHAR(255) NOT NULL,
    contrasena VARCHAR(255) NOT NULL,
    permisos   VARCHAR(255) NOT NULL
);

CREATE TABLE IF NOT EXISTS Piensos
(
    id                 INT AUTO_INCREMENT PRIMARY KEY,
    Nombre             VARCHAR(255) NOT NULL,
    CantidadInventario FLOAT
);

CREATE TABLE IF NOT EXISTS Ingredientes
(
    id                 INT AUTO_INCREMENT PRIMARY KEY,
    Nombre             VARCHAR(255) NOT NULL,
    CantidadInventario FLOAT
);

CREATE TABLE IF NOT EXISTS ComposicionPiensos
(
    PiensosID     INT,
    IngredienteID INT,
    Cantidad      VARCHAR(25) NOT NULL,
    PRIMARY KEY (PiensosID, IngredienteID),
    FOREIGN KEY (PiensosID) REFERENCES Piensos (id),
    FOREIGN KEY (IngredienteID) REFERENCES Ingredientes (id)
);

CREATE TABLE IF NOT EXISTS Proveedores
(
    id     INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(25) NOT NULL
);


CREATE TABLE IF NOT EXISTS PedidosIngredientes
(
    id            INT AUTO_INCREMENT PRIMARY KEY,
    proveedorId   INT,
    ingredienteId INT,
    cantidad      INT,
    coste         FLOAT NOT NULL,
    FOREIGN KEY (ingredienteId) REFERENCES Ingredientes (id),
    FOREIGN KEY (proveedorId) REFERENCES Proveedores (id)
);

CREATE TABLE IF NOT EXISTS IngredientesProveedor
(
    idProveedor   INT,
    idIngrediente INT,
    precio        FLOAT,
    PRIMARY KEY (idProveedor, idIngrediente),
    FOREIGN KEY (idProveedor) REFERENCES Proveedores (id),
    FOREIGN KEY (idIngrediente) REFERENCES Ingredientes (id)
);

-- Inserts
-- Insertar usuario con permisos "admin"
INSERT INTO Usuario (nombre, contrasena, permisos)
VALUES ('admin_user', '123', 'admin');

-- Insertar usuario con permisos "prueba"
INSERT INTO Usuario (nombre, contrasena, permisos)
VALUES ('test_user', '123', 'prueba');

-- Inserts en la tabla Ingredientes
INSERT INTO Ingredientes (Nombre, CantidadInventario)
VALUES ('Ingrediente1', 500),
       ('Ingrediente2', 700),
       ('Ingrediente3', 300),
       ('Ingrediente4', 450),
       ('Ingrediente5', 600);

-- Inserts en la tabla ComposicionPiensos
INSERT INTO ComposicionPiensos (PiensosID, IngredienteID, Cantidad)
VALUES (1, 1, '250'),
       (1, 2, '500'),
       (2, 2, '150'),
       (2, 3, '180'),
       (3, 4, '600'),
       (3, 1, '350'),
       (4, 5, '110'),
       (4, 2, '360'),
       (5, 3, '560'),
       (5, 4, '280');

-- Inserts en la tabla Proveedores
INSERT INTO Proveedores (nombre)
VALUES ('Proveedor A');
INSERT INTO Proveedores (nombre)
VALUES ('Proveedor B');
INSERT INTO Proveedores (nombre)
VALUES ('Proveedor C');

-- Inserts en la tabla IngredientesProveedor
INSERT INTO IngredientesProveedor (idProveedor, idIngrediente, precio)
VALUES (1, 1, 10.50), -- Proveedor A suministra Ingrediente1
       (1, 2, 15.75), -- Proveedor A suministra Ingrediente2
       (2, 3, 8.20),  -- Proveedor B suministra Ingrediente3
       (2, 4, 12.00), -- Proveedor B suministra Ingrediente4
       (3, 5, 20.00);
-- Proveedor C suministra Ingrediente5

-- Procedimientos
DELIMITER //

CREATE PROCEDURE obtener_usuarios()
BEGIN
    SELECT id, nombre, contrasena, permisos FROM Usuario;
END //

DELIMITER ;
DELIMITER //

CREATE PROCEDURE obtener_piensos()
BEGIN
    SELECT * FROM Piensos;
END //

DELIMITER ;
DELIMITER //

CREATE PROCEDURE obtener_composiciones()
BEGIN
    SELECT * FROM ComposicionPiensos;
END //

DELIMITER ;

DELIMITER //

CREATE PROCEDURE obtener_pedidos()
BEGIN
    SELECT * FROM PedidosIngredientes;
END //

DELIMITER ;

DELIMITER //

CREATE PROCEDURE obtener_ingredientes()
BEGIN
    SELECT * FROM Ingredientes;
END //

DELIMITER ;

DELIMITER //

CREATE PROCEDURE obtener_proveedores()
BEGIN
    SELECT * FROM Proveedores;
END //

DELIMITER ;

DELIMITER //

CREATE PROCEDURE obtener_ingredientes_proveedor()
BEGIN
    SELECT * FROM IngredientesProveedor;
END //

DELIMITER ;

DELIMITER //

CREATE PROCEDURE AgregarPienso(IN p_pienso_id INT, IN p_cantidad_total FLOAT)
BEGIN
    -- Declaración de variables locales
    DECLARE cantidad_ingrediente float;
    DECLARE id_ingrediente INT;

    -- Declaración del cursor
    DECLARE cur_composicion CURSOR FOR
        SELECT IngredienteID, Cantidad
        FROM ComposicionPiensos
        WHERE PiensosID = p_pienso_id;

    -- Añadir cantidadTotal a la cantidadInventario del pienso
    UPDATE Piensos
    SET CantidadInventario = CantidadInventario + p_cantidad_total
    WHERE id = p_pienso_id;

    -- Apertura del cursor
    OPEN cur_composicion;
    read_loop: LOOP
        FETCH cur_composicion INTO id_ingrediente, cantidad_ingrediente;
        IF (cantidad_ingrediente IS NULL) THEN
            LEAVE read_loop;
        END IF;

        -- Actualización de la cantidadInventario del ingrediente
        UPDATE Ingredientes
        SET CantidadInventario = CantidadInventario - (cantidad_ingrediente * p_cantidad_total)
        WHERE id = id_ingrediente;
    END LOOP;
    CLOSE cur_composicion; -- Cierre del cursor
END //

DELIMITER ;

DELIMITER //

CREATE PROCEDURE verificarStock(
    IN p_pienso_id INT,
    IN p_cantidad_total float,
    OUT p_result BOOLEAN,
    OUT p_map JSON
)
BEGIN
    DECLARE ingrediente_id INT;
    DECLARE cantidad_necesaria FLOAT;
    DECLARE resultado FLOAT;

    DECLARE no_negativo BOOLEAN DEFAULT TRUE;
    DECLARE ingredientes_cursor CURSOR FOR
        SELECT IngredienteID, Cantidad
        FROM ComposicionPiensos
        WHERE PiensosID = p_pienso_id;

    -- Variable que contendrá el resultado del cursor
    DECLARE CONTINUE HANDLER FOR NOT FOUND SET no_negativo := FALSE;

    SET p_map = JSON_ARRAY();

    -- Abrir cursor
    OPEN ingredientes_cursor;

    -- Iterar sobre los ingredientes
    ingredientes_loop: LOOP
        -- Fetch del cursor
        FETCH ingredientes_cursor INTO ingrediente_id, cantidad_necesaria;

        -- Salir del bucle si no hay más filas
        IF no_negativo = FALSE THEN
            LEAVE ingredientes_loop;
        END IF;

        -- Calcular la cantidad necesaria para no ser negativo
        SET resultado = cantidad_necesaria * p_cantidad_total;

        -- Comprobar si la cantidad en inventario es suficiente
        SELECT CantidadInventario INTO @cantidad_inventario
        FROM Ingredientes
        WHERE id = ingrediente_id;

        -- Si el resultado es negativo, calcular la cantidad necesaria
        IF (@cantidad_inventario - resultado) < 0 THEN
            SET p_map = JSON_ARRAY_APPEND(p_map, '$', JSON_OBJECT('idIngrediente', ingrediente_id, 'cantidad', -(@cantidad_inventario - resultado)));
            SET p_result = FALSE;
        END IF;
    END LOOP;

    -- Cerrar cursor
    CLOSE ingredientes_cursor;

    -- Si todos los ingredientes tienen suficiente cantidad, devolver verdadero
    IF p_result IS NULL THEN
        SET p_result = TRUE;
    END IF;
END//

DELIMITER ;

DELIMITER //

CREATE PROCEDURE aumentarCantidadInventario(
    IN p_id INT,
    IN p_cantidad_aumentar FLOAT
)
BEGIN
    DECLARE v_cantidad_actual FLOAT;

    -- Buscar la cantidad actual del ingrediente
    SELECT CantidadInventario INTO v_cantidad_actual
    FROM Ingredientes
    WHERE id = p_id;

    -- Verificar si se encontró el ingrediente
    IF v_cantidad_actual IS NOT NULL THEN
        -- Actualizar la cantidad de inventario sumando la cantidad a aumentar
        UPDATE Ingredientes
        SET CantidadInventario = CantidadInventario + p_cantidad_aumentar
        WHERE id = p_id;

        SELECT 'Cantidad de inventario actualizada correctamente' AS message;
    ELSE
        SELECT 'No se encontró un ingrediente con el ID proporcionado' AS message;
    END IF;
END //

DELIMITER ;