# nttdata_bootcamp55_proyecto2


para generar el api y modelo, ejecutar comando

	mvn clean install

reemplazar accesos a base de datos mysql y mongodb en application.properties por las credenciales propias

crear base de datos mysql

	CREATE database banco_xyz;

crear base de datos mongodb

    database: banco_xyz
    collection: transactions

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

http://localhost:8080/transacciones/deposito

POST
{
"tipo": "DEPOSITO",
"monto": 50.00,
"fecha": "2024-10-07T10:30:00",
"cuentaOrigen": "",
"cuentaDestino": "0123456789"
}

http://localhost:8080/transacciones/retiro

POST
{
"tipo": "RETIRO",
"monto": 20.00,
"fecha": "2024-10-07T11:00:00",
"cuentaOrigen": "0123456789",
"cuentaDestino": ""
}


http://localhost:8080/transacciones/transferencia

POST
{
"tipo": "TRANSFERENCIA",
"monto": 20.00,
"fecha": "2024-10-07T12:00:00",
"cuentaOrigen": "0123456789",
"cuentaDestino": "01234567892"
}

http://localhost:8080/transacciones/historial
GET