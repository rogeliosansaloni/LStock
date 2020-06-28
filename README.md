# StockLS

Algunos alumnos de La Salle comenzaron a invertir parte de sus ahorros en Bolsa para dinamizar su capital. Para poder gestionar el portfolio, se ha implementado un sistema con una arquitectura servidor-cliente. El programa servidor almacena las credenciales de acceso y todos los datos relacionados a los valores bursátiles, y se comunicará estos datos a todos los clientes conectados al servidor. El servidor también puede mostrar las estadísticas y otra información.

El cliente puede realizar varias operaciones como acceder a su cuenta, visualizar, vender o comprar acciones.

## Requerimientos

Es imprescindible tener IntelliJ IDEA y tener instalado Java 11 SDK. Es un proyecto desarrollado en Java soportado por el framework Maven. El proyecto tiene 3 módulos: Client, Server y Shared.
Para ver las vistas correctamente, es imprescindible usar Windows.

## Instalación

1. Descargar el zip del proyecto o clonar el proyecto en Bitbucket.
```bash
git clone https://atlassian.salle.url.edu:7943/scm/dpoo/dpoo-stock-c2.git
```
2. Abrir IntelliJ.
3. File > New > Project from existing sources...
4. Seleccionar la carpeta donde se ha clonado el proyecto.
5. Asegurar que mediante el wizard importar los módulos y seleccionar 11 como versión del SDK. Seguir el wizard hasta el final, importando los tres módulos.
6. En caso de que no esté configurado correctamente el soporte al framework Maven, hacer clic derecho en cada módulo > Add Framework Support... > Maven. Detectará el fichero pom.xml para la configuración Maven.
7. Si IntelliJ muestre un mensaje sobre la importación automática de dependencias, hacer clic a Enable auto-import.

## Conexión BBDD

Hay dos maneras para la conexión a la base de datos
1. Conexión a la base de datos en remoto. Tenemos una base de datos en AWS. Para usar esta base de datos, hay que avisarnos y darnos una IP para tener acceso. Además para que podamos arrancar el
servidor donde se encuentra MySQL.
2. Conexión a la base de datos en local. Al ejecutar el script de MySQL en local, hay que cambiar también las credenciales de la base de datos especificadas en config.json.


## Uso

1.Ejecutar el script MySQL y la creación de las procedures.
2.Primero ejecutamos el servidor y después el cliente.
  -Podemos ejecutar diferentes instancias del cliente pero solo puede haber una instancia del servidor arrancado.

## Aviso

Hay que tener cuidado con el número de acciones que se insieren en la base de datos. A partir de un cierto número elevado de tuplas en la tabla Share (unas 300 aproximadamente), 
las queries que se usan para extraer la información comienzan a ralentizarse bastante y el programa funciona más lentamente. Por ello es recomendable no tener muchos bots activados a la vez,
y que estos no se activen muy seguidamente.

## Autores
Mary Grace Adina - marygrace.adina<br/>
Claudia Alonso Carrasco - claudia.alonso<br/>
Kaye Ann Ignacio Jove - kayeann.ignacio<br/>
Nicole Marie Jimenez Burayag - nicolemarie.jimenez<br/>
Rafael Rebollo Cacín - rafael.rebollo<br/>
Rogelio Sansaloni Sanjuan - rogelio.sansaloni

DPOO @ Mayo 2020
