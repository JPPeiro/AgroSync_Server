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
    cantidad      FLOAT,
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
-- Inserts para la tabla Usuario
INSERT INTO Usuario (nombre, contrasena, permisos)
VALUES ('juan_perez', 'juan123', 'admin'),
       ('ana_garcia', 'ana456', 'normal'),
       ('carlos_rodriguez', 'carlos789', 'normal'),
       ('laura_martinez', 'laura321', 'normal'),
       ('david_gomez', 'david654', 'normal'),
       ('maria_lopez', 'maria987', 'normal'),
       ('javier_sanchez', 'javier123', 'normal'),
       ('luisa_fernandez', 'luisa456', 'normal'),
       ('pablo_gonzalez', 'pablo789', 'normal'),
       ('carmen_ruiz', 'carmen321', 'normal'),
       ('sergio_diaz', 'sergio654', 'normal'),
       ('ana_jimenez', 'ana987', 'normal'),
       ('alberto_martin', 'alberto123', 'normal'),
       ('clara_romero', 'clara456', 'normal'),
       ('oscar_suarez', 'oscar789', 'normal'),
       ('natalia_dominguez', 'natalia321', 'normal'),
       ('manuel_gil', 'manuel654', 'normal'),
       ('sofia_hernandez', 'sofia987', 'normal'),
       ('miguel_perez', 'miguel123', 'normal'),
       ('eva_gomez', 'eva456', 'normal');


-- Inserts en la tabla Ingredientes
INSERT INTO Ingredientes (Nombre, CantidadInventario)
VALUES ('Harina de maiz', 500),
       ('Harina de trigo', 400),
       ('Harina de soja', 300),
       ('Harina de pescado', 200),
       ('Harina de carne', 250),
       ('Aceite de girasol', 150),
       ('Aceite de pescado', 100),
       ('Salvado de arroz', 200),
       ('Salvado de trigo', 150),
       ('Melaza', 100),
       ('Cascara de cacahuete', 120),
       ('Cascara de almendra', 80),
       ('Carbonato de calcio', 300),
       ('Fosfato dicalcico', 200),
       ('Vitamina E', 100);


-- Inserts para la tabla Piensos
INSERT INTO Piensos (Nombre, CantidadInventario)
VALUES ('Pienso para cachorros', 1000),
       ('Pienso para perros adultos', 1500),
       ('Pienso para gatos jovenes', 800),
       ('Pienso para gatos adultos', 1200),
       ('Pienso para aves de corral', 2000),
       ('Pienso para gallinas ponedoras', 1800),
       ('Pienso para cerdos de engorde', 2500),
       ('Pienso para cerdas lactantes', 1700),
       ('Pienso para vacas lecheras', 2200),
       ('Pienso para terneros', 1600),
       ('Pienso para caballos de trabajo', 1300),
       ('Pienso para caballos de competicion', 1100),
       ('Pienso para conejos en crecimiento', 1400),
       ('Pienso para conejos adultos', 1900),
       ('Pienso para peces tropicales', 1000);


-- Inserts para la tabla ComposicionPiensos

-- Pienso 1: Pienso Premium para cachorros
INSERT INTO ComposicionPiensos (PiensosID, IngredienteID, Cantidad)
VALUES (1, 1, '0.2'), -- Harina de carne de pollo
       (1, 2, '0.3'), -- Arroz
       (1, 3, '0.1'), -- Maíz
       (1, 4, '0.2'), -- Harina de pescado
       (1, 5, '0.1');
-- Grasa animal

-- Pienso 2: Pienso Premium para perros adultos
INSERT INTO ComposicionPiensos (PiensosID, IngredienteID, Cantidad)
VALUES (2, 1, '0.25'), -- Harina de carne de pollo
       (2, 2, '0.2'),  -- Arroz
       (2, 3, '0.15'), -- Maíz
       (2, 6, '0.1'),  -- Trigo
       (2, 7, '0.1'),  -- Avena
       (2, 8, '0.1'),  -- Cebada
       (2, 9, '0.1');
-- Pulpa de remolacha

-- Pienso 3: Pienso Premium para gatos jóvenes
INSERT INTO ComposicionPiensos (PiensosID, IngredienteID, Cantidad)
VALUES (3, 10, '0.3'),  -- Harina de pescado
       (3, 11, '0.2'),  -- Maíz
       (3, 12, '0.2'),  -- Harina de ave
       (3, 13, '0.15'), -- Trigo
       (3, 14, '0.15');
-- Grasa de pollo

-- Pienso 4: Pienso Premium para gatos adultos
INSERT INTO ComposicionPiensos (PiensosID, IngredienteID, Cantidad)
VALUES (4, 10, '0.25'), -- Harina de pescado
       (4, 11, '0.2'),  -- Maíz
       (4, 12, '0.2'),  -- Harina de ave
       (4, 13, '0.15'), -- Trigo
       (4, 14, '0.1'),  -- Grasa de pollo
       (4, 15, '0.1');
-- Aceite de pescado

-- Pienso 5: Pienso Económico para cachorros
INSERT INTO ComposicionPiensos (PiensosID, IngredienteID, Cantidad)
VALUES (5, 2, '0.25'), -- Arroz
       (5, 3, '0.35'), -- Maíz
       (5, 4, '0.15'), -- Harina de pescado
       (5, 5, '0.1'),  -- Grasa animal
       (5, 6, '0.05'), -- Trigo
       (5, 7, '0.05'), -- Avena
       (5, 8, '0.05');
-- Cebada

-- Pienso 6: Pienso Económico para perros adultos
INSERT INTO ComposicionPiensos (PiensosID, IngredienteID, Cantidad)
VALUES (6, 2, '0.3'),  -- Arroz
       (6, 3, '0.25'), -- Maíz
       (6, 6, '0.15'), -- Trigo
       (6, 7, '0.15'), -- Avena
       (6, 8, '0.1'),  -- Cebada
       (6, 9, '0.05');
-- Pulpa de remolacha

-- Pienso 7: Pienso Económico para gatos jóvenes
INSERT INTO ComposicionPiensos (PiensosID, IngredienteID, Cantidad)
VALUES (7, 11, '0.35'), -- Maíz
       (7, 12, '0.3'),  -- Harina de ave
       (7, 13, '0.15'), -- Trigo
       (7, 14, '0.1'),  -- Grasa de pollo
       (7, 15, '0.1');
-- Aceite de pescado

-- Pienso 8: Pienso Económico para gatos adultos
INSERT INTO ComposicionPiensos (PiensosID, IngredienteID, Cantidad)
VALUES (8, 11, '0.3'),  -- Maíz
       (8, 12, '0.25'), -- Harina de ave
       (8, 13, '0.15'), -- Trigo
       (8, 14, '0.15'), -- Grasa de pollo
       (8, 15, '0.15');
-- Aceite de pescado

-- Pienso 9: Pienso Premium para aves de corral
INSERT INTO ComposicionPiensos (PiensosID, IngredienteID, Cantidad)
VALUES (9, 3, '0.2'),  -- Maíz
       (9, 4, '0.25'), -- Harina de pescado
       (9, 6, '0.15'), -- Trigo
       (9, 7, '0.15'), -- Avena
       (9, 8, '0.15'), -- Cebada
       (9, 9, '0.1');
-- Pulpa de remolacha

-- Pienso 10: Pienso Premium para ganado bovino
INSERT INTO ComposicionPiensos (PiensosID, IngredienteID, Cantidad)
VALUES (10, 3, '0.3'),  -- Maíz
       (10, 4, '0.25'), -- Harina de pescado
       (10, 6, '0.15'), -- Trigo
       (10, 7, '0.15'), -- Avena
       (10, 8, '0.1'),  -- Cebada
       (10, 9, '0.05'), -- Pulpa de remolacha
       (10, 10, '0.05');
-- Harina de carne de pollo

-- Pienso 11: Pienso Premium para ganado porcino
INSERT INTO ComposicionPiensos (PiensosID, IngredienteID, Cantidad)
VALUES (11, 3, '0.25'), -- Maíz
       (11, 4, '0.2'),  -- Harina de pescado
       (11, 6, '0.2'),  -- Trigo
       (11, 7, '0.2'),  -- Avena
       (11, 8, '0.1'),  -- Cebada
       (11, 9, '0.05'), -- Pulpa de remolacha
       (11, 10, '0.1');
-- Harina de carne de pollo

-- Pienso 12: Pienso Económico para aves de corral
INSERT INTO ComposicionPiensos (PiensosID, IngredienteID, Cantidad)
VALUES (12, 3, '0.35'), -- Maíz
       (12, 6, '0.2'),  -- Trigo
       (12, 7, '0.15'), -- Avena
       (12, 8, '0.15'), -- Cebada
       (12, 9, '0.1'),  -- Pulpa de remolacha
       (12, 10, '0.05');
-- Harina de carne de pollo

-- Pienso 13: Pienso Económico para ganado bovino
INSERT INTO ComposicionPiensos (PiensosID, IngredienteID, Cantidad)
VALUES (13, 3, '0.4'),  -- Maíz
       (13, 6, '0.2'),  -- Trigo
       (13, 7, '0.2'),  -- Avena
       (13, 8, '0.15'), -- Cebada
       (13, 9, '0.05'), -- Pulpa de remolacha
       (13, 10, '0.1'); -- Harina de carne de pollo

-- Pienso 14: Pienso Económico para ganado porcino
INSERT INTO ComposicionPiensos (PiensosID, IngredienteID, Cantidad)
VALUES (14, 3, '0.35'), -- Maíz
       (14, 6, '0.25'), -- Trigo
       (14, 7, '0.15'), -- Avena
       (14, 8, '0.15'), -- Cebada
       (14, 9, '0.05'), -- Pulpa de remolacha
       (14, 10, '0.05'),-- Harina de carne de pollo
       (14, 11, '0.05');-- Harina de pescado

-- Pienso 15: Pienso Premium para ganado ovino
INSERT INTO ComposicionPiensos (PiensosID, IngredienteID, Cantidad)
VALUES (15, 3, '0.35'), -- Maíz
       (15, 6, '0.3'),  -- Trigo
       (15, 7, '0.2'),  -- Avena
       (15, 8, '0.1'),  -- Cebada
       (15, 9, '0.05'), -- Pulpa de remolacha
       (15, 10, '0.05'),-- Harina de carne de pollo
       (15, 11, '0.05');-- Harina de pescado

-- Inserts para la tabla Proveedores

-- Proveedor 1
INSERT INTO Proveedores (nombre)
VALUES ('Agroalimentos S.A.');

-- Proveedor 2
INSERT INTO Proveedores (nombre)
VALUES ('Nutripecuaria');

-- Proveedor 3
INSERT INTO Proveedores (nombre)
VALUES ('Alimentos del Campo');

-- Proveedor 4
INSERT INTO Proveedores (nombre)
VALUES ('Veterinaria La Granja');

-- Proveedor 5
INSERT INTO Proveedores (nombre)
VALUES ('Cereales y Forrajes Pérez');

-- Proveedor 6
INSERT INTO Proveedores (nombre)
VALUES ('Dist Alimentos Animales');

-- Proveedor 7
INSERT INTO Proveedores (nombre)
VALUES ('Agricola Ganadera del Sur');

-- Proveedor 8
INSERT INTO Proveedores (nombre)
VALUES ('Insumos Rodriguez');

-- Proveedor 9
INSERT INTO Proveedores (nombre)
VALUES ('Comercializadora Ortiz');

-- Proveedor 10
INSERT INTO Proveedores (nombre)
VALUES ('Ganadería Garcia');


-- Inserts para la tabla IngredientesProveedor

-- Proveedor 1
INSERT INTO IngredientesProveedor (idProveedor, idIngrediente, precio)
VALUES (1, 1, 5.50), -- Proveedor 1 provee Ingrediente 1 con precio de 5.50
       (1, 4, 4.75), -- Proveedor 1 provee Ingrediente 4 con precio de 4.75
       (1, 6, 6.20); -- Proveedor 1 provee Ingrediente 6 con precio de 6.20

-- Proveedor 2
INSERT INTO IngredientesProveedor (idProveedor, idIngrediente, precio)
VALUES (2, 2, 6.80), -- Proveedor 2 provee Ingrediente 2 con precio de 6.80
       (2, 5, 3.90); -- Proveedor 2 provee Ingrediente 5 con precio de 3.90

-- Proveedor 3
INSERT INTO IngredientesProveedor (idProveedor, idIngrediente, precio)
VALUES (3, 1, 5.20), -- Proveedor 3 provee Ingrediente 1 con precio de 5.20
       (3, 3, 4.00), -- Proveedor 3 provee Ingrediente 3 con precio de 4.00
       (3, 7, 7.10); -- Proveedor 3 provee Ingrediente 7 con precio de 7.10

-- Proveedor 4
INSERT INTO IngredientesProveedor (idProveedor, idIngrediente, precio)
VALUES (4, 2, 6.30), -- Proveedor 4 provee Ingrediente 2 con precio de 6.30
       (4, 5, 4.20); -- Proveedor 4 provee Ingrediente 5 con precio de 4.20

-- Proveedor 5
INSERT INTO IngredientesProveedor (idProveedor, idIngrediente, precio)
VALUES (5, 3, 3.50), -- Proveedor 5 provee Ingrediente 3 con precio de 3.50
       (5, 4, 5.00), -- Proveedor 5 provee Ingrediente 4 con precio de 5.00
       (5, 8, 6.80); -- Proveedor 5 provee Ingrediente 8 con precio de 6.80

-- Proveedor 6
INSERT INTO IngredientesProveedor (idProveedor, idIngrediente, precio)
VALUES (6, 1, 5.80), -- Proveedor 6 provee Ingrediente 1 con precio de 5.80
       (6, 3, 4.30); -- Proveedor 6 provee Ingrediente 3 con precio de 4.30

-- Proveedor 7
INSERT INTO IngredientesProveedor (idProveedor, idIngrediente, precio)
VALUES (7, 2, 6.50), -- Proveedor 7 provee Ingrediente 2 con precio de 6.50
       (7, 5, 3.80), -- Proveedor 7 provee Ingrediente 5 con precio de 3.80
       (7, 7, 6.90); -- Proveedor 7 provee Ingrediente 7 con precio de 6.90

-- Proveedor 8
INSERT INTO IngredientesProveedor (idProveedor, idIngrediente, precio)
VALUES (8, 1, 5.40), -- Proveedor 8 provee Ingrediente 1 con precio de 5.40
       (8, 4, 4.90), -- Proveedor 8 provee Ingrediente 4 con precio de 4.90
       (8, 6, 6.40); -- Proveedor 8 provee Ingrediente 6 con precio de 6.40

-- Proveedor 9
INSERT INTO IngredientesProveedor (idProveedor, idIngrediente, precio)
VALUES (9, 3, 4.20), -- Proveedor 9 provee Ingrediente 3 con precio de 4.20
       (9, 6, 6.60); -- Proveedor 9 provee Ingrediente 6 con precio de 6.60

-- Proveedor 10
INSERT INTO IngredientesProveedor (idProveedor, idIngrediente, precio)
VALUES (10, 2, 6.70), -- Proveedor 10 provee Ingrediente 2 con precio de 6.70
       (10, 4, 5.10); -- Proveedor 10 provee Ingrediente 4 con precio de 5.10

-- Procedimientos
DELIMITER //

CREATE PROCEDURE obtener_usuarios()
BEGIN
    SELECT id, nombre, contrasena, permisos FROM Usuario;
END//

DELIMITER ;
DELIMITER //

CREATE PROCEDURE obtener_piensos()
BEGIN
    SELECT * FROM Piensos;
END//

DELIMITER ;
DELIMITER //

CREATE PROCEDURE obtener_composiciones()
BEGIN
    SELECT * FROM ComposicionPiensos;
END//

DELIMITER ;

DELIMITER //

CREATE PROCEDURE obtener_pedidos()
BEGIN
    SELECT * FROM PedidosIngredientes;
END//

DELIMITER ;

DELIMITER //

CREATE PROCEDURE obtener_ingredientes()
BEGIN
    SELECT * FROM Ingredientes;
END//

DELIMITER ;

DELIMITER //

CREATE PROCEDURE obtener_proveedores()
BEGIN
    SELECT * FROM Proveedores;
END//

DELIMITER ;

DELIMITER //

CREATE PROCEDURE obtener_ingredientes_proveedor()
BEGIN
    SELECT * FROM IngredientesProveedor;
END//

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
    read_loop:
    LOOP
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
END//

DELIMITER ;

DELIMITER //

CREATE PROCEDURE verificarStock(
    IN p_pienso_id INT,
    IN p_cantidad_total FLOAT,
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

    -- Si no faltan ingredientes, devolver verdadero
    IF no_negativo THEN
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
    SELECT CantidadInventario
    INTO v_cantidad_actual
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
END//

DELIMITER ;
