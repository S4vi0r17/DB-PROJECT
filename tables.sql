CREATE database SistemaCxP USE SistemaCxP;

CREATE TABLE CondicionPago (
    CondicionID INT PRIMARY KEY IDENTITY (1, 1),
    Detalles VARCHAR(200) not null
);

CREATE TABLE MedioPago (
    MedioID INT PRIMARY KEY IDENTITY (1, 1),
    Detalles VARCHAR(200) not null
);

CREATE TABLE Empleado (
    EmpleadoID INT PRIMARY KEY IDENTITY (1, 1),
    Nombre_Empleado VARCHAR(100) not null,
    Direccion VARCHAR(200) null,
    Telefono VARCHAR(15) null
);

CREATE TABLE Proveedor (
    ProveedorID INT PRIMARY KEY IDENTITY (1, 1),
    Nombre_Proveedor VARCHAR(100) NOT NULL,
    Direccion VARCHAR(200) null,
    Telefono VARCHAR(15) not null
);

CREATE TABLE Producto (
    ProductoID INT PRIMARY KEY IDENTITY (1, 1),
    Nombre_Producto VARCHAR(200) not null,
    PrecioUnidad DECIMAL(6, 2),
    Cantidad INT,
    MontoTotal AS (Cantidad * PrecioUnidad) PERSISTED
);

CREATE TABLE GuiaAlmacen (
    GuiaID INT PRIMARY KEY IDENTITY (1, 1),
    ProveedorID INT,
    NumeroGuia VARCHAR(50),
    Fecha DATE,
    Detalles VARCHAR(200) null,
    FOREIGN KEY (ProveedorID) REFERENCES Proveedor (ProveedorID)
);

CREATE TABLE EmpleadoAlmacen (
    EmpleadoID INT,
    GuiaID INT,
    PRIMARY KEY (EmpleadoID, GuiaID),
    FOREIGN KEY (EmpleadoID) REFERENCES Empleado (EmpleadoID),
    FOREIGN KEY (GuiaID) REFERENCES GuiaAlmacen (GuiaID)
);

CREATE TABLE Factura (
    FacturaID INT PRIMARY KEY IDENTITY (1, 1),
    ProveedorID INT,
    CondicionPagoID INT,
    EmpleadoID INT,
    FechaEmision DATE not null,
    FechaVencimiento DATE not null,
    FOREIGN KEY (ProveedorID) REFERENCES Proveedor (ProveedorID),
    FOREIGN KEY (CondicionPagoID) REFERENCES CondicionPago (CondicionID),
    FOREIGN KEY (EmpleadoID) REFERENCES Empleado (EmpleadoID)
);

CREATE TABLE FacturaProducto (
    FacturaID INT,
    ProductoID INT,
    PRIMARY KEY (FacturaID, ProductoID),
    FOREIGN KEY (FacturaID) REFERENCES Factura (FacturaID),
    FOREIGN KEY (ProductoID) REFERENCES Producto (ProductoID)
);

CREATE TABLE CajaBanco (
    PagoID INT PRIMARY KEY IDENTITY (1, 1),
    MedioID INT not null,
    FacturaID INT not null,
    FechaPago DATE not null,
    MontoPagado DECIMAL(6, 2) not null,
    SaldoRestante DECIMAL(6, 2),
    FOREIGN KEY (MedioID) REFERENCES MedioPago (MedioID),
    FOREIGN KEY (FacturaID) REFERENCES Factura (FacturaID)
);