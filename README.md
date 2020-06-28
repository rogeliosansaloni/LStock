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

1. Asignar tu IP publico:
	
	1.Columna de izquierda, Security Groups
	2.Edit Rules
	3. Añadir tu IP en (SSH, MySQL/Aurora,HTTPS)
2. Connectar a AWS

	1. Acceder desde : https://438369310031.signin.aws.amazon.com/console
	2. Usar tus credenciales de AWS (pedir accesso: nicolemarie.jimenez@students.salle.url.edu).
	3. Region: eu-west-3 / Paris
	4. EC2
	5. Instances > DPOO-C2 StockLS DDBB > Actions > Instance State > Start
	6. Una vez terminado lo que se tiene que hacer, apagar el servidor.


## Uso

1.Ejecutar el script MySql
2.Primero ejecutamos el servidor y después el cliente.
  -Podemos ejecutar diferentes instancias del cliente pero solo puede haber una instancia del servidor arrancado.

## Autores
Mary Grace Adina - marygrace.adina<br/>
Claudia Alonso Carrasco - claudia.alonso<br/>
Kaye Ann Ignacio Jove - kayeann.ignacio<br/>
Nicole Marie Jimenez Burayag - nicolemarie.jimenez<br/>
Rafael Rebollo Cacín - rafael.rebollo<br/>
Rogelio Sansaloni Sanjuan - rogelio.sansaloni

DPOO @ Mayo 2020
