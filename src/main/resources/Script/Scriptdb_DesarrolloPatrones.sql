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

INSERT INTO proyecto_FindYourHome.usuario (id_usuario,nombre, apellidos,contrasena,correo,ruta_imagen) VALUES 
(1,'juan','Castro Mora','$228uytgh','jcastro@gmail.com', 'https://upload.wikimedia.org/wikipedia/commons/thumb/2/2a/Juan_Diego_Madrigal.jpg/250px-Juan_Diego_Madrigal.jpg'),
(2,'rebeca','Contreras Mora','$2alQRi','acontreras@gmail.com','https://upload.wikimedia.org/wikipedia/commons/0/06/Photo_of_Rebeca_Arthur.jpg'),
(3,'pedro','Mena Loria','YlYqXnPbO','lmena@gmail.com','https://upload.wikimedia.org/wikipedia/commons/thumb/f/fd/Eduardo_de_Pedro_2019.jpg/480px-Eduardo_de_Pedro_2019.jpg?20200109230854');

    
    -- Tabla de vendedor
CREATE TABLE proyecto_FindYourHome.vendedor (
	id_vendedor INT NOT NULL AUTO_INCREMENT,
    id_usuario INT NOT NULL,
    telefono VARCHAR(15) NULL,
    activo bool NOT NULL,
    PRIMARY KEY (id_vendedor),
	foreign key fk_vendedor_usuario (id_usuario) references usuario(id_usuario)  
    )
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4;
    
-- Tabla de Urbanizacion o Condominios
CREATE TABLE proyecto_FindYourHome.comunidad (
    id_comunidad INT NOT NULL AUTO_INCREMENT,
    nombre VARCHAR(100) NOT NULL,
    tipo VARCHAR(100) NOT NULL,
    descripcion VARCHAR(1600) NOT NULL,
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
    tipoPropiedad VARCHAR(50) NOT NULL,  -- Por ejemplo, 'casa', 'apartamento', 'terreno', etc.
    ubicacion VARCHAR(100),
    fecha_publicacion DATE NOT NULL,
    PRIMARY KEY (id_propiedad),
	foreign key fk_propiedad_vendedor (id_vendedor) references vendedor(id_vendedor),
    foreign key fk_propiedad_comunidad (id_comunidad) references comunidad(id_comunidad) 
    )
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4;

INSERT INTO proyecto_FindYourHome.propiedad (id_propiedad,id_vendedor,id_comunidad,nombre, descripcion, precio, tipoPropiedad,ubicacion,fecha_publicacion) VALUES 
('1','21', '19', 'bella vista','linda casa con mirador para atardeser en las lomas','1500000','Quinta','limon','10/8/22' ), 
('2','52',  '23','mira mar','Justo cerca del mar para bañarse todos los dias','2500000', 'casa','guanacaste','19/2/23'),
('3','33','39','el roble', 'rodeado de un bosque genial con olor a madera','3500000','granja','heredia','12/12/21'),
('4','54','14','las olas', 'apenas para los amantes de la arena y el sol','9500000', 'villa','puntarenas','16/1/20');


-- Tabla de Comentarios
CREATE TABLE proyecto_FindYourHome.comentario (
    id_comentario INT NOT NULL AUTO_INCREMENT,
    id_comunidad INT NOT NULL,
    id_usuario INT NOT NULL,
    comentario VARCHAR(1600) NOT NULL,
    fecha DATE,
    PRIMARY KEY (id_comentario),
	foreign key fk_comentario_comunidad (id_comunidad) references comunidad(id_comunidad),
    foreign key fk_comentario_usuario (id_usuario) references usuario(id_usuario)
)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4;

CREATE TABLE proyecto_FindYourHome.imagenComunidad (
    id_imagenComunidad INT NOT NULL AUTO_INCREMENT,
    id_comunidad INT NOT NULL,
    ruta_imagen varchar(1024)  NOT NULL,
    PRIMARY KEY (id_imagenComunidad),
	foreign key fk_comentario_comunidad (id_comunidad) references comunidad(id_comunidad)
)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4;

CREATE TABLE proyecto_FindYourHome.imagenPropiedad (
    id_imagenPropiedad INT NOT NULL AUTO_INCREMENT,
    id_propiedad INT NOT NULL,
    ruta_imagen varchar(1024)  NOT NULL,
    PRIMARY KEY (id_imagenPropiedad),
	foreign key fk_comentario_propiedad (id_propiedad) references propiedad(id_propiedad)
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

