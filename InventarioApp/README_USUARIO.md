# Manual de Usuario

## Sistema de Inventario

### 1. Introduccion
Este sistema permite gestionar productos dentro de un inventario, controlando entradas, salidas y consultas de stock de manera sencilla desde una interfaz grafica.

Esta disenado para que cualquier usuario pueda utilizarlo sin conocimientos tecnicos.

### 2. Inicio del sistema
Al ejecutar el proyecto en NetBeans, el usuario vera una ventana principal con:

- Formulario para registrar productos
- Tabla con el inventario actual
- Botones para buscar, eliminar, registrar entradas y salidas
- Indicadores con total de productos, valor del inventario y alertas

### 3. Funcionalidades del sistema

#### 3.1 Agregar producto
Permite registrar un nuevo producto en el inventario.

Pasos:

1. Completar los campos del formulario.
2. Ingresar:
   - ID
   - Nombre del producto
   - Cantidad inicial
   - Precio
   - Ubicacion
   - Stock minimo
3. Presionar `Registrar producto`.

Resultado:
El producto queda guardado en el sistema y aparece en la tabla.

#### 3.2 Listar productos
Muestra todos los productos registrados.

Resultado:
Se despliega una tabla con:

- ID
- Nombre
- Cantidad disponible
- Precio
- Ubicacion
- Stock minimo
- Estado

#### 3.3 Buscar producto
Permite localizar un producto por su ID.

Pasos:

1. Escribir el ID en el campo `Buscar por ID`.
2. Presionar `Buscar producto`.

Resultado:
El producto se selecciona en la tabla y sus datos se cargan en el formulario.

#### 3.4 Registrar entrada
Permite agregar unidades a un producto existente.

Pasos:

1. Seleccionar un producto en la tabla.
2. Presionar `Entrada stock`.
3. Ingresar la cantidad a agregar.

Resultado:
El stock del producto aumenta.

#### 3.5 Registrar salida
Permite retirar unidades del inventario.

Pasos:

1. Seleccionar un producto en la tabla.
2. Presionar `Salida stock`.
3. Ingresar la cantidad a retirar.

Resultado:
El stock disminuye.

Importante:
Si no hay suficiente stock, el sistema mostrara un error.

#### 3.6 Eliminar producto
Permite eliminar un producto del inventario.

Pasos:

1. Seleccionar un producto en la tabla.
2. Presionar `Eliminar`.
3. Confirmar la accion.

Resultado:
El producto se elimina del sistema.

### 4. Recomendaciones de uso

- Verificar siempre el ID del producto antes de hacer movimientos.
- No ingresar cantidades negativas.
- Revisar el stock antes de hacer salidas.
- Usar el boton `Actualizar vista` si deseas refrescar la tabla manualmente.

### 5. Manejo de errores
El sistema puede mostrar mensajes como:

- `Producto no encontrado`
- `Stock insuficiente`
- `Ya existe un producto con ese ID`
- `Datos invalidos`

En estos casos, el usuario debe volver a intentar ingresando datos correctos.

### 6. Ejecucion del sistema

1. Abrir el proyecto en NetBeans.
2. Ejecutar la clase principal `Presentacion.Main`.
3. Usar la ventana principal para administrar el inventario.
