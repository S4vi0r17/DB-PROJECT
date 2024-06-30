-- Crear roles
CREATE ROLE Contador;

CREATE ROLE AlmacenManager;

CREATE ROLE Gerente;

CREATE ROLE Auditor;

-- Crear usuarios
CREATE LOGIN ContadorUsuario WITH PASSWORD = 'z4v4123';

CREATE USER ContadorUsuario FOR LOGIN ContadorUsuario;

CREATE LOGIN AlmacenUsuario WITH PASSWORD = 'AlmacenSeguro456!';

CREATE USER AlmacenUsuario FOR LOGIN AlmacenUsuario;

CREATE LOGIN GerenteUsuario WITH PASSWORD = 'GerenteSeguro789!';

CREATE USER GerenteUsuario FOR LOGIN GerenteUsuario;

CREATE LOGIN AuditorUsuario WITH PASSWORD = 'AuditorSeguro101!';

CREATE USER AuditorUsuario FOR LOGIN AuditorUsuario;

-- Asignar usuarios a roles
ALTER ROLE Contador ADD MEMBER ContadorUsuario;

ALTER ROLE AlmacenManager ADD MEMBER AlmacenUsuario;

ALTER ROLE Gerente ADD MEMBER GerenteUsuario;

ALTER ROLE Auditor ADD MEMBER AuditorUsuario;

-- Asignar permisos a roles
GRANT SELECT, INSERT , UPDATE ON Factura TO Contador;

GRANT SELECT, INSERT , UPDATE ON CajaBanco TO Contador;

GRANT SELECT ON Producto TO Contador;

GRANT SELECT, INSERT , UPDATE ON GuiaAlmacen TO AlmacenManager;

GRANT SELECT, INSERT , UPDATE ON EmpleadoAlmacen TO AlmacenManager;

GRANT SELECT ON Producto TO AlmacenManager;

GRANT SELECT ON SCHEMA::dbo TO Gerente;

GRANT INSERT , UPDATE, DELETE ON Proveedor TO Gerente;

GRANT INSERT , UPDATE, DELETE ON Producto TO Gerente;

GRANT SELECT ON SCHEMA::dbo TO Auditor;

DENY INSERT, UPDATE, DELETE ON SCHEMA::dbo TO Auditor;