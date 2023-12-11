/*Se crea la base de datos */
drop schema if exists proyecto_FindYourHome;
drop user if exists admin_site;
CREATE SCHEMA proyecto_FindYourHome ;

create user 'admin_site'@'%' identified by 'admin_clave.';

grant all privileges on proyecto_FindYourHome.* to 'admin_site'@'%';
flush privileges;

-- Tabla de Usuarios
CREATE TABLE proyecto_FindYourHome.usuario (
    id_usuario INT NOT NULL AUTO_INCREMENT,
    nombre VARCHAR(100),
    apellidos VARCHAR(100),
    correo VARCHAR(100),
    contrasena VARCHAR(512),  -- Almacenar contraseñas cifradas
    tipo VARCHAR(50),  -- Puede ser 'usuario registrado', 'administrador', 'agente de bienes raíces', etc.
    ruta_imagen varchar(1024),
    PRIMARY KEY (id_usuario)
)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4;

 
    -- Tabla de vendedor
CREATE TABLE proyecto_FindYourHome.vendedor (
	id_vendedor INT NOT NULL AUTO_INCREMENT,
	nombre VARCHAR(100),
    apellidos VARCHAR(100),
    telefono VARCHAR(15) NULL,
    activo bool NOT NULL,
    PRIMARY KEY (id_vendedor)
    )
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4;


    
-- Tabla de Urbanizacion o Condominios
CREATE TABLE proyecto_FindYourHome.comunidad (
    id_comunidad INT NOT NULL AUTO_INCREMENT,
    nombre VARCHAR(100) NOT NULL,
    tipo VARCHAR(100) NOT NULL,
    descripcion VARCHAR(1600) NOT NULL,
    ruta_imagen varchar(1024)  NOT NULL,
    PRIMARY KEY (id_comunidad)
)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4;

    
-- Tabla de Propiedad
CREATE TABLE proyecto_FindYourHome.propiedad (
    id_propiedad INT NOT NULL AUTO_INCREMENT,
    id_vendedor INT NOT NULL,
    id_comunidad INT,
    nombre VARCHAR(255) NOT NULL,
    descripcion VARCHAR(1600) NOT NULL,
    precio DECIMAL(10, 2) NOT NULL,
    tipo_propiedad VARCHAR(50) NOT NULL,  -- Por ejemplo, 'casa', 'apartamento', 'terreno', etc.
    ubicacion VARCHAR(100),
    ruta_imagen varchar(1024)  NOT NULL,
    PRIMARY KEY (id_propiedad),
	foreign key fk_propiedad_vendedor (id_vendedor) references vendedor(id_vendedor),
    foreign key fk_propiedad_comunidad (id_comunidad) references comunidad(id_comunidad) 
    )
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4;




-- Tabla de Comentarios
CREATE TABLE proyecto_FindYourHome.comentario (
    id_comentario INT NOT NULL AUTO_INCREMENT,
    id_comunidad INT NOT NULL,
    id_usuario INT NOT NULL,
    comentario VARCHAR(1600) NOT NULL,
    PRIMARY KEY (id_comentario),
	foreign key fk_comentario_comunidad (id_comunidad) references comunidad(id_comunidad),
    foreign key fk_comentario_usuario (id_usuario) references usuario(id_usuario)
)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4;

-- Tabla de Favoritos
CREATE TABLE proyecto_FindYourHome.favorito (
    id_favorito INT NOT NULL AUTO_INCREMENT,
    id_usuario INT NOT NULL,  -- Clave foránea relacionada con la tabla Usuarios
    id_propiedad INT NOT NULL,  -- Clave foránea relacionada con la tabla Propiedades
    PRIMARY KEY (id_favorito),
	foreign key fk_favorito_usuario (id_usuario) references usuario(id_usuario),
    foreign key fk_favorito_propiedad (id_propiedad) references propiedad(id_propiedad)
)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4;

INSERT INTO proyecto_FindYourHome.usuario (nombre, apellidos, contrasena, correo, tipo, ruta_imagen) VALUES
('Ana', 'López Pérez', 'a1b2c3', 'alopez@gmail.com', 'usuario registrado', 'https://vivolabs.es/wp-content/uploads/2022/03/perfil-mujer-vivo.png'),
('Carlos', 'Gutiérrez Rodríguez', 'xYz123', 'cgutierrez@gmail.com', 'usuario registrado', 'https://i.pinimg.com/originals/ec/b5/0a/ecb50a4d065ea8e0a993df0be6ff9542.jpg'),
('Luis', 'Martínez Solís', 'p@ssw0rd', 'lmartinez@gmail.com', 'administrador', 'https://i.pinimg.com/originals/a6/82/db/a682dbfb9bfd60236191c303ff8056de.jpg'),
('Laura', 'Hernández Jiménez', 'qwerty', 'lhernandez@gmail.com', 'usuario registrado', 'https://i.pinimg.com/originals/bf/d6/d5/bfd6d59c28f5665d73b20f5df4419d72.jpg'),
('Miguel', 'Sánchez Ramírez', 'm1guel123', 'msanchez@gmail.com', 'agente de bienes raíces', 'https://i.pinimg.com/originals/eb/69/97/eb69971b571b5faa04fe4e49eb5fabea.jpg'),
('Isabel', 'Torres Castro', 'pass123', 'itorres@gmail.com', 'usuario registrado', 'https://www.okchicas.com/wp-content/uploads/2018/01/Poses-para-una-buena-foto-de-perfil-15-1-701x700.jpg'),
('Gabriel', 'Mora García', 'g@briel', 'gmora@gmail.com', 'agente de bienes raíces', 'https://vivolabs.es/wp-content/uploads/2022/03/perfil-hombre-vivo.png'),
('Patricia', 'Ortiz Ruiz', 'pati123', 'portiz@gmail.com', 'usuario registrado', 'https://img.aeroexpo.online/images_ar/projects/images-g/elevar-perfil-mujeres-ingenieria-6393-14652745.jpg'),
('Ricardo', 'Díaz Vargas', 'r1cardo', 'rdiaz@gmail.com', 'usuario registrado', 'https://photografos.com.br/wp-content/uploads/2020/09/fotografia-para-perfil.jpg'),
('María', 'Castillo Méndez', 'maria456', 'mcastillo@gmail.com', 'usuario registrado', 'https://img.freepik.com/fotos-premium/mujer-sonriendo-gafas-sol-cabeza_362480-2259.jpg');

INSERT INTO proyecto_FindYourHome.vendedor (id_vendedor,nombre,apellidos, telefono, activo) VALUES
(1,'Pedro','marin', '70001234', TRUE),
(2,'Samuel','Perez', '88997766', TRUE),
(3,'Federica','Rojas', '66554433', TRUE),
(4,'Rosibel', 'Araya', '77778888', TRUE),
(5,'Kendall','Tijerino', '55556666', TRUE),
(6,'Fabiola','Acosta', '44445555', TRUE),
(7,'Ligia','Castro', '33334444', TRUE),
(8,'David','Luna', '99998888', TRUE),
(9,'Allan','Mendoza', '22221111', TRUE),
(10,'Sandra','Fernandez', '11112222', TRUE);

INSERT INTO proyecto_FindYourHome.comunidad (nombre, tipo, descripcion,ruta_imagen) VALUES
('Residencial San José', 'Residencial', 'Hermosa comunidad con parques y áreas verdes.', 'https://mls.re.cr/api/images/ll1500248/ll1500248_img-14_large.jpg'),
('Condominio Los Alamos', 'Condominio', 'Condominio exclusivo con seguridad las 24 horas.', 'https://mls.re.cr/api/images/ll1500248/ll1500248_img-14_large.jpg'),
('Villas del Sol', 'Residencial', 'Comunidad tranquila y segura para vivir en familia.', 'https://mls.re.cr/api/images/ll1500248/ll1500248_img-14_large.jpg'),
('Bosques de la Montaña', 'Condominio', 'Condominio rodeado de naturaleza y vistas espectaculares.', 'https://mls.re.cr/api/images/ll1500248/ll1500248_img-14_large.jpg'),
('Parque Residencial', 'Residencial', 'Urbanización con parques recreativos y canchas deportivas.', 'https://mls.re.cr/api/images/ll1500248/ll1500248_img-14_large.jpg'),
('La Joya', 'Condominio', 'Condominio de lujo con servicios exclusivos.', 'https://mls.re.cr/api/images/ll1500248/ll1500248_img-14_large.jpg'),
('Los Pinos', 'Residencial', 'Comunidad residencial con amplias zonas verdes.', 'https://mls.re.cr/api/images/ll1500248/ll1500248_img-14_large.jpg'),
('Vista Hermosa', 'Condominio', 'Condominio con vistas panorámicas y diseño moderno.', 'https://mls.re.cr/api/images/ll1500248/ll1500248_img-14_large.jpg'),
('Rincón del Valle', 'Residencial', 'Urbanización con acceso controlado y seguridad.', 'https://mls.re.cr/api/images/ll1500248/ll1500248_img-14_large.jpg'),
('Altos de la Montaña', 'Condominio', 'Condominio en la cima de la montaña con vistas impresionantes.', 'https://mls.re.cr/api/images/ll1500248/ll1500248_img-14_large.jpg');

INSERT INTO proyecto_FindYourHome.propiedad (id_vendedor, id_comunidad, nombre, descripcion, precio, tipo_propiedad, ubicacion,ruta_imagen) VALUES
(1, 1, 'Casa en Residencial San José', 'Amplia casa de dos pisos con jardín y garaje.', 350000, 'Casa', 'San José', 'https://mls.re.cr/api/images/ll1500248/ll1500248_img-14_large.jpg'),
(2, 2, 'Apartamento de Lujo en Los Alamos', 'Moderno apartamento con acabados de alta calidad.', 500000, 'Apartamento', 'Los Alamos', 'https://mls.re.cr/api/images/ll1500248/ll1500248_img-14_large.jpg'),
(3, 3, 'Casa Familiar en Villas del Sol', 'Acogedora casa con patio y espacios para niños.', 280000, 'Casa', 'Villas del Sol', 'https://mls.re.cr/api/images/ll1500248/ll1500248_img-14_large.jpg'),
(4, 4, 'Vista Panorámica en Bosques de la Montaña', 'Casa con vistas espectaculares y amplias áreas verdes.', 800000, 'Casa', 'Bosques de la Montaña', 'https://mls.re.cr/api/images/ll1500248/ll1500248_img-14_large.jpg'),
(5, 5, 'Terreno en Parque Residencial', 'Lote residencial ideal para construir la casa de tus sueños.', 120000, 'Terreno', 'Parque Residencial', 'https://mls.re.cr/api/images/ll1500248/ll1500248_img-14_large.jpg'),
(6, 6, 'Penthouse de Lujo en La Joya', 'Exclusivo penthouse con terraza privada y jacuzzi.', 750000, 'Apartamento', 'La Joya', 'https://mls.re.cr/api/images/ll1500248/ll1500248_img-14_large.jpg'),
(7, 7, 'Casa con Jardín en Los Pinos', 'Amplia casa con jardín y espacios para la familia.', 420000, 'Casa', 'Los Pinos', 'https://mls.re.cr/api/images/ll1500248/ll1500248_img-14_large.jpg'),
(8, 8, 'Vista Hermosa: Apartamento Moderno', 'Apartamento con diseño moderno y vistas impresionantes.', 550000, 'Apartamento', 'Vista Hermosa', 'https://mls.re.cr/api/images/ll1500248/ll1500248_img-14_large.jpg'),
(9, 9, 'Casa en Rincón del Valle', 'Casa con diseño contemporáneo y seguridad 24/7.', 320000, 'Casa', 'Rincón del Valle', 'https://mls.re.cr/api/images/ll1500248/ll1500248_img-14_large.jpg'),
(10, 10, 'Exclusivo Condominio en Altos de la Montaña', 'Condominio de lujo con servicios exclusivos y vistas panorámicas.', 900000, 'Condominio', 'Altos de la Montaña', 'https://mls.re.cr/api/images/ll1500248/ll1500248_img-14_large.jpg');

INSERT INTO proyecto_FindYourHome.comentario (id_comunidad, id_usuario, comentario) VALUES
(1, 1, 'Me encanta vivir en Residencial San José, es un lugar tranquilo y seguro.'),
(2, 2, 'Los Alamos tiene la mejor seguridad y comodidades, ¡recomendado!'),
(3, 3, 'Villas del Sol es el lugar perfecto para criar a mi familia, vecinos amigables y parques cercanos.'),
(4, 4, 'Bosques de la Montaña ofrece una conexión única con la naturaleza, estoy enamorado de las vistas.'),
(5, 5, 'Parque Residencial es un lugar ideal para vivir, mucha seguridad y ambiente familiar.'),
(6, 6, 'La Joya cumple con todas mis expectativas de lujo y comodidad, ¡excelente elección!'),
(7, 7, 'Los Pinos es una comunidad encantadora, me encanta la tranquilidad y la belleza del entorno.'),
(8, 8, 'Vista Hermosa es realmente hermosa, el diseño del apartamento es impresionante.'),
(9, 9, 'Rincón del Valle ofrece seguridad y estilo, la mejor elección para mi familia.'),
(10, 10, 'Altos de la Montaña es el lugar perfecto para aquellos que buscan exclusividad y vistas impresionantes.');

INSERT INTO proyecto_FindYourHome.favorito (id_usuario, id_propiedad) VALUES
(1, 1),
(2, 2),
(3, 3),
(4, 4),
(5, 5),
(6, 6),
(7, 7),
(8, 8),
(9, 9),
(10, 10),
(10, 5),
(7, 5),
(1, 7);
