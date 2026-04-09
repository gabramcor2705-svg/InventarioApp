# Manual Tecnico

## Sistema de Inventario

### 1. Arquitectura del sistema
El sistema esta estructurado en capas:

`Presentacion`  
`LogicaNegocio`  
`AccesoDatos`  
`Entidades`

Esta separacion facilita mantenimiento, escalabilidad y reutilizacion del codigo.

### 2. Descripcion de paquetes

#### 2.1 Presentacion
Archivos principales:

- `Presentacion/Main.java`
- `Presentacion/InventarioFrame.java`

Responsabilidades:

- Inicializar la aplicacion
- Configurar el `LookAndFeel`
- Mostrar la interfaz Swing
- Capturar eventos del usuario
- Refrescar la tabla y los indicadores visuales

#### 2.2 Entidades

##### `Entidades/Producto.java`
Representa un producto del inventario.

Atributos:

- `id`
- `nombre`
- `cantidad`
- `precio`
- `ubicacion`
- `stockMinimo`

##### `Entidades/Movimiento.java`
Representa un movimiento de inventario.

Tipos:

- `ENTRADA`
- `SALIDA`

#### 2.3 Logica de Negocio

##### `LogicaNegocio/ProductoService.java`
Contiene la logica principal del sistema.

Funciones principales:

- Registrar producto
- Listar productos
- Buscar producto
- Eliminar producto
- Registrar entrada de stock
- Registrar salida de stock
- Generar alertas de stock bajo
- Calcular valor total del inventario

Validaciones implementadas:

- Producto no nulo
- ID mayor que cero
- Nombre obligatorio
- Cantidad no negativa
- Precio no negativo
- Ubicacion obligatoria
- Stock minimo no negativo
- ID no duplicado
- Stock suficiente para salidas

#### 2.4 Acceso a Datos
Interfaces:

- `AccesoDatos/ProductoDAO.java`
- `AccesoDatos/MovimientoDAO.java`

Implementaciones:

- `AccesoDatos/ProductoDAOImpl.java`
- `AccesoDatos/MovimientoDAOImpl.java`

Funciones:

- Leer y escribir productos en archivo de texto
- Registrar movimientos de inventario
- Actualizar y eliminar productos persistidos

### 3. Persistencia de datos
El sistema usa archivos de texto planos:

- `productos.txt`
- `movimientos.txt`

Ejemplo de formato en `productos.txt`:

```text
ID,Nombre,Cantidad,Precio,Ubicacion,StockMinimo
```

Ejemplo de registro real:

```text
1,Laptop,10,599.99,Bodega A,3
```

### 4. Flujo del sistema
Ejemplo: registrar salida de stock

1. El usuario selecciona un producto en `InventarioFrame`.
2. La capa de presentacion solicita la cantidad.
3. Se llama a `ProductoService.salidaStock`.
4. Se valida existencia del producto y stock suficiente.
5. `ProductoDAOImpl` actualiza `productos.txt`.
6. `MovimientoDAOImpl` registra el movimiento en `movimientos.txt`.
7. La interfaz refresca tabla, metricas y estado.

### 5. Manejo de errores
Se utilizan principalmente:

- `IllegalArgumentException` para validaciones funcionales
- `RuntimeException` para errores de persistencia
- `JOptionPane` para informar errores al usuario en la UI

### 6. Mejoras implementadas

- Interfaz grafica profesional en Swing
- Panel de formulario y tabla de inventario
- Tarjetas de metricas
- Validaciones de entrada
- Busqueda por ID
- Eliminacion con confirmacion
- Registro visual de entradas y salidas

### 7. Posibles mejoras futuras

- Edicion completa de productos
- Base de datos relacional como MySQL o PostgreSQL
- Reportes PDF o Excel
- Autenticacion de usuarios
- Historial visual de movimientos
- Filtros y busqueda avanzada

### 8. Requisitos del sistema

- Java JDK 11 o superior
- NetBeans o cualquier IDE compatible con proyectos Java Ant

### 9. Ejecucion del sistema

1. Abrir el proyecto en NetBeans.
2. Verificar que la clase principal sea `Presentacion.Main`.
3. Ejecutar el proyecto.

### 10. Estructura del proyecto

```text
InventarioApp/
|-- build.xml
|-- manifest.mf
|-- nbproject/
|-- productos.txt
|-- src/
|   |-- AccesoDatos/
|   |-- Entidades/
|   |-- LogicaNegocio/
|   |-- Presentacion/
|-- test/
```
