# Diagrama de Clases UML

Este diagrama representa la arquitectura por capas actual del proyecto `InventarioApp`.

```mermaid
classDiagram
    class Main {
        +main(String[] args)
    }

    class InventarioFrame {
        -ProductoService service
        -JTable tblProductos
        -DefaultTableModel tableModel
        +InventarioFrame()
    }

    class ProductoService {
        -ProductoDAO dao
        -MovimientoDAO movDAO
        +registrar(Producto p)
        +listar() List~Producto~
        +buscar(int id) Producto
        +eliminar(int id)
        +entradaStock(int id, int cantidad)
        +salidaStock(int id, int cantidad)
        +alertaStock()
        +reporteInventario()
    }

    class Producto {
        -int id
        -String nombre
        -int cantidad
        -double precio
        -String ubicacion
        -int stockMinimo
        +Producto(int id, String nombre, int cantidad, double precio, String ubicacion, int stockMinimo)
        +getId() int
        +getNombre() String
        +getCantidad() int
        +getPrecio() double
        +getUbicacion() String
        +getStockMinimo() int
        +setCantidad(int cantidad)
        +toString() String
    }

    class Movimiento {
        -int idProducto
        -String tipo
        -int cantidad
        +Movimiento(int idProducto, String tipo, int cantidad)
        +toString() String
    }

    class ProductoDAO {
        <<interface>>
        +agregar(Producto p)
        +listar() List~Producto~
        +buscar(int id) Producto
        +actualizar(Producto p)
        +eliminar(int id)
    }

    class MovimientoDAO {
        <<interface>>
        +registrarMovimiento(Movimiento m)
    }

    class ProductoDAOImpl {
        -String archivo
        +agregar(Producto p)
        +listar() List~Producto~
        +buscar(int id) Producto
        +actualizar(Producto p)
        +eliminar(int id)
    }

    class MovimientoDAOImpl {
        -String archivo
        +registrarMovimiento(Movimiento m)
    }

    Main --> InventarioFrame : inicia
    InventarioFrame --> ProductoService : usa
    ProductoService --> ProductoDAO : depende de
    ProductoService --> MovimientoDAO : depende de
    ProductoService --> Producto : gestiona
    ProductoService --> Movimiento : crea
    ProductoDAOImpl ..|> ProductoDAO : implementa
    MovimientoDAOImpl ..|> MovimientoDAO : implementa
    ProductoDAOImpl --> Producto : persiste
    MovimientoDAOImpl --> Movimiento : persiste
```

## Resumen de relaciones

- `Main` inicia la aplicacion y abre `InventarioFrame`.
- `InventarioFrame` interactua con `ProductoService`.
- `ProductoService` concentra la logica de negocio.
- `ProductoDAO` y `MovimientoDAO` definen contratos de persistencia.
- `ProductoDAOImpl` y `MovimientoDAOImpl` implementan esos contratos usando archivos `.txt`.
- `Producto` y `Movimiento` representan las entidades del dominio.
