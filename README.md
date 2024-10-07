# nttdata_bootcamp55_proyecto1


para generar el api y modelo, ejecutar comando

	mvn clean install

reemplazar accesos a base de datos mysql en application.properties por las credenciales propias

crear base de datos mysql

	CREATE database banco_xyz;

al ejecutar el programa se crearan las tablas automaticamente.

probar en postman en rutas de ejemplo:

http://localhost:8080/clientes

Body (POST):
{
"nombre": "Jorge",
"apellido": "Tafur",
"dni": "74582556",
"email": "jorge@gmail.com"
}

http://localhost:8080/cuentas

Body (POST):
{
"numeroCuenta": "0123456789",
"saldo": 100.00,
"tipoCuenta": "AHORROS",
"clienteId": 1
}

http://localhost:8080/cuentas/1/deposit

Body (PUT):
100.00

http://localhost:8080/cuentas/1/withdraw

Body (PUT):
50.00