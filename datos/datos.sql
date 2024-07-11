insert into Proveedor values('Emertica SA','Calle Corpac 160 ','(+45)123456789');
insert into Proveedor values('America SA','1900 Squirrel Ln S','(+51)987654321');
insert into Proveedor values('Aetna','200 Calle Monsa','(+98)789123456');
insert into Proveedor values('CME','862 Feast Blvd NE','(+51)321654987');
insert into Proveedor values('MetLife','564 Development Street SE','(+52)654321789');
insert into Proveedor values('Kroger','862 Feast Blvd NE','(+98)159753486');
insert into Proveedor values('Sysco','177 Title Avenue','(+39)357159258');

insert into CondicionPago values ('Contado');
insert into CondicionPago values ('Anticipo 50%');
insert into CondicionPago values ('3 pagos mensuales');
insert into CondicionPago values ('6 pagos mensuales');
insert into CondicionPago values ('12 pagos mensuales');

insert into MedioPago values('Efectivo');
insert into MedioPago values('Tarjeta de debito');
insert into MedioPago values('Mercado Pago');
insert into MedioPago values('Tarjeta de credito');
insert into MedioPago values('Cheque');

insert into Empleado values ('Jamal Elphinstone', '9770 Rate Lane', '(508) 895-2625');
insert into Empleado values ('Rowan Orchard','1886 Fan Lane','(464) 597-1427');
insert into Empleado values ('Hadley Byrne','Av.Quilca 589','(228) 471-9497');
insert into Empleado values ('Cullen Paxton','465 Smash St S','(232) 412-2157');
insert into Empleado values ('Jordy Stubbins','9770 Rate Lane','(324) 516-4772');
insert into Empleado values ('Josue Preston','228 Train Ave E','(860) 837-1195');
insert into Empleado values ('Alejandro Chang','2000 Harmony Lane','(944) 046-7220');

insert into CajaBanco values (5,1, '2024-07-01', 100.00);
insert into CajaBanco values (3,2, '2024-07-11', 30.00);
insert into CajaBanco values (2,3, '2024-07-24', 375.00);
insert into CajaBanco values (5,4, '2024-07-09', 30.00);
insert into CajaBanco values (2,5, '2024-07-10', 250.00);
insert into CajaBanco values (1,6, '2024-06-24', 20.00);
insert into CajaBanco values (2,7, '2024-06-10', 41.70);
insert into CajaBanco values (4,8, '2024-06-28', 200.00);
insert into CajaBanco values (2,9, '2024-07-02', 262.50);
insert into CajaBanco values (4,10, '2024-07-15', 125.00);

insert into Factura values(1,1,'2024-07-01', '2024-07-01');
insert into Factura values(5,2,'2024-06-11', '2024-08-01');
insert into Factura values(7,2,'2024-06-01', '2024-09-01');
insert into Factura values(3,3,'2024-06-24', '2024-07-24');
insert into Factura values(2,3,'2024-07-03', '2024-08-03');
insert into Factura values(1,4,'2024-06-19', '2024-08-19');
insert into Factura values(3,5,'2024-06-04', '2025-05-04');
insert into Factura values(6,2,'2024-06-20', '2024-08-20');
insert into Factura values(4,2,'2024-06-05', '2024-08-25');
insert into Factura values(5,4,'2024-06-30', '2024-09-30');

SELECT* FROM GuiaAlmacen
insert into GuiaAlmacen values (1,'1-001', '2024-07-01', 1.5);
insert into GuiaAlmacen values (2,'2-022', '2024-06-12', 10.50 );
insert into GuiaAlmacen values (3,'3-345', '2024-06-03', 15.00 );
insert into GuiaAlmacen values (4,'4-451', '2024-06-25', 3.00  );
insert into GuiaAlmacen values (5,'5-045', '2024-07-05', 5.00  );
insert into GuiaAlmacen values (6,'6-078', '2024-06-20', 25.00 );
insert into GuiaAlmacen values (7,'7-075', '2024-06-05', 10.70  );
insert into GuiaAlmacen values (1,'8-078', '2024-06-22', 15.00 );
insert into GuiaAlmacen values (5,'9-075', '2024-06-06', 1.00 );
insert into GuiaAlmacen values (3,'10-075','2024-07-02', 4.00 );


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
SELECT* FROM EmpleadoAlmacen

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


