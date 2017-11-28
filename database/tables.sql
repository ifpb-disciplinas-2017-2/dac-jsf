CREATE TABLE Banda(
id SERIAL PRIMARY KEY,
nomeFantasia VARCHAR(100)
);
CREATE TABLE INTEGRANTE(
id serial PRIMARY KEY,
nome varchar(50),
CPF VARCHAR(15),
id_banda int,
FOREIGN KEY (id_banda) REFERENCES banda(id) ON DELETE RESTRICT

);
CREATE TABLE Musica(
 id serial PRIMARY KEY,
nome varchar(50)
);
CREATE TABLE Album(
id serial PRIMARY KEY,
descricao varchar(50),
 dataDeLancamento DATE,
banda_id int
FOREIGN KEY (id_banda) REFERENCES banda(id) ON DELETE RESTRICT

)

