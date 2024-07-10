use SistemaCxP
--Proveedor
insert into Proveedor values('Emertica SA','Calle Corpac 160 ','(+45)123456789');
insert into Proveedor values('America SA','1900 Squirrel Ln S','(+51)987654321');
insert into Proveedor values('Aetna','200 Calle Monsa','(+98)789123456');
insert into Proveedor values('CME','862 Feast Blvd NE','(+51)321654987');
insert into Proveedor values('MetLife','564 Development Street SE','(+52)654321789');
insert into Proveedor values('Kroger','862 Feast Blvd NE','(+98)159753486');
insert into Proveedor values('Sysco','177 Title Avenue','(+39)357159258');

--productos
insert into Producto values('Mouse', 50.00, 2)
insert into Producto values('Lapicero', 3.00, 20)
insert into Producto values('Cuaderno', 15.00, 50)
insert into Producto values('Hojas bond', 5.00, 15)
insert into Producto values('Tasa', 25.00, 30)
insert into Producto values('Ventilador', 60.00, 4)
insert into Producto values('Audifonos', 100.00, 5)
insert into Producto values('Bicicleta', 200.00, 2)
insert into Producto values('Camiseta', 75.00, 7)
insert into Producto values('Teclado', 150.00, 3)

--Condicion
insert into CondicionPago values ('Contado');
insert into CondicionPago values ('Anticipo 50%');
insert into CondicionPago values ('3 pagos mensuales');
insert into CondicionPago values ('6 pagos mensuales');
insert into CondicionPago values ('12 pagos mensuales');

--Medio de pago
insert into MedioPago values('Efectivo');
insert into MedioPago values('Tarjeta de debito');
insert into MedioPago values('Mercado Pago');
insert into MedioPago values('Tarjeta de credito');
insert into MedioPago values('Cheque');

--Empleado--
insert into Empleado values ('Jamal Elphinstone', '9770 Rate Lane', '(508) 895-2625');
insert into Empleado values ('Rowan Orchard','1886 Fan Lane','(464) 597-1427');
insert into Empleado values ('Hadley Byrne','Av.Quilca 589','(228) 471-9497');
insert into Empleado values ('Cullen Paxton','465 Smash St S','(232) 412-2157');
insert into Empleado values ('Jordy Stubbins','9770 Rate Lane','(324) 516-4772');
insert into Empleado values ('Josue Preston','228 Train Ave E','(860) 837-1195');
insert into Empleado values ('Alejandro Chang','2000 Harmony Lane','(944) 046-7220');

--Factura
insert into Factura values(1,1,1,'2024-01-01', '2024-01-31');
insert into Factura values(5,1,3,'2024-01-11', '2024-02-01');
insert into Factura values(7,2,4,'2024-02-01', '2024-03-01');
insert into Factura values(3,1,7,'2024-02-24', '2024-03-30');
insert into Factura values(2,1,1,'2024-03-03', '2024-06-03');
insert into Factura values(1,4,2,'2024-03-19', '2024-10-19');
insert into Factura values(3,5,5,'2024-04-04', '2025-05-04');
insert into Factura values(6,5,1,'2024-04-20', '2025-04-20');
insert into Factura values(4,5,2,'2024-05-05', '2024-06-25');
insert into Factura values(5,2,6,'2024-05-30', '2025-01-30');


--Factura_producto
insert into FacturaProducto values (1,1)
insert into FacturaProducto values (2,2);
insert into FacturaProducto values (3,3);
insert into FacturaProducto values (4,4);
insert into FacturaProducto values (5,5);
insert into FacturaProducto values (6,6);
insert into FacturaProducto values (7,7);
insert into FacturaProducto values (8,8);
insert into FacturaProducto values (9,9);
insert into FacturaProducto values (10,10);

--Caja banco
insert into CajaBanco values (5,1, '2024-01-01', 100.00, 0.00);
insert into CajaBanco values (3,2, '2024-01-11', 450.00, 50.00);
insert into CajaBanco values (2,3, '2024-02-24', 375.00, 375.00 );
insert into CajaBanco values (5,4, '2024-02-24', 75.00, 0.00 );
insert into CajaBanco values (2,5, '2024-03-03', 250.00,500.00);
insert into CajaBanco values (1,6, '2024-03-24', 40,200.00 );
insert into CajaBanco values (2,7, '2024-04-10', 41.70, 458.30);
insert into CajaBanco values (4,8, '2024-04-21', 33.40,366.60 );
insert into CajaBanco values (2,9, '2024-05-10', 43.75,481.25 );
insert into CajaBanco values (4,10, '2024-05-30', 225.00,225.00 );



--GuiaAlmacen
insert into GuiaAlmacen values (1,'1-001', '2024-01-05', 0.90);
insert into GuiaAlmacen values (2,'2-022', '2024-01-15', 1.50 );
insert into GuiaAlmacen values (3,'3-345', '2024-02-20', 50.00 );
insert into GuiaAlmacen values (4,'4-451', '2024-02-25', 15.00  );
insert into GuiaAlmacen values (5,'5-045', '2024-03-03', 25.00  );
insert into GuiaAlmacen values (6,'6-078', '2024-03-19', 14.00 );
insert into GuiaAlmacen values (7,'7-075', '2024-04-05', 1.70  );
insert into GuiaAlmacen values (6,'8-078', '2024-04-22', 25.90 );
insert into GuiaAlmacen values (7,'9-075', '2024-05-05', 2.10 );
insert into GuiaAlmacen values (7,'10-075','2024-06-01', 1.95 );

--Empleado almacen
insert into EmpleadoAlmacen values (1, 1);
insert into EmpleadoAlmacen values (2, 2);
insert into EmpleadoAlmacen values (3, 3);
insert into EmpleadoAlmacen values (4, 4);
insert into EmpleadoAlmacen values (5, 5);
insert into EmpleadoAlmacen values (6, 6);
insert into EmpleadoAlmacen values (7, 7);
insert into EmpleadoAlmacen values (6, 8);
insert into EmpleadoAlmacen values (7, 9);
insert into EmpleadoAlmacen values (7, 10);