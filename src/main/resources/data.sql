BEGIN;

INSERT INTO `producto` (descripcion, fecha_registro, nombre, activo) VALUES ('Comic',NOW(),'El Increible Hulk',1),
																			('Comic',NOW(),'Thor',1),
																			('Camiseta',NOW(),'Avenger',1),
																			('Camiseta',NOW(),'Capitan America',1),
																			('Vasos',NOW(),'Batman',1),
																			('Vasos',NOW(),'Ironman',1),
																			('Comic',NOW(),'Superman',1),
																			('Figura',NOW(),'Flash',1),
																			('Figura',NOW(),'Aquaman',1),
																			('Comic',NOW(),'Mujer Maravilla',1),
																			('Camiseta',NOW(),'Joker',1),
																			('Comic',NOW(),'Spider-Man Adventure',1);
																														
INSERT INTO `stock` (cantidad_comprada, cantidad_stock, fecha_registro, precio_compra, precio_unidad, producto_id) VALUES   (20,10,NOW(),100,130,1), 
																															(30,25,NOW(),100,130,2),
																															(10,5,NOW(),500,650,3),
																															(20,15,NOW(),500,650,4),
																															(40,30,NOW(),200,260,5),
																															(50,25,NOW(),200,260,6), 
																															(20,5,NOW(),100,130,7),
																															(60,50,NOW(),800,1040,8),
																															(10,2,NOW(),800,1040,9),
																															(30,25,NOW(),100,130,10),
																															(40,10,NOW(),800,1040,11),
																															(10,0,NOW(),100,130,12);


COMMIT;