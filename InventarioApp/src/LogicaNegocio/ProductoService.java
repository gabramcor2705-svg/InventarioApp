/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package LogicaNegocio;

import AccesoDatos.MovimientoDAO;
import AccesoDatos.MovimientoDAOImpl;
import AccesoDatos.ProductoDAO;
import AccesoDatos.ProductoDAOImpl;
import Entidades.Movimiento;
import Entidades.Producto;
import java.util.Locale;
import java.util.List;

public class ProductoService {

    private final ProductoDAO dao = new ProductoDAOImpl();
    private final MovimientoDAO movDAO = new MovimientoDAOImpl();

    public void registrar(Producto p) {
        if (p == null) {
            throw new IllegalArgumentException("El producto no puede ser nulo");
        }
        if (p.getId() <= 0) {
            throw new IllegalArgumentException("El ID debe ser mayor que cero");
        }
        if (p.getNombre() == null || p.getNombre().trim().isEmpty()) {
            throw new IllegalArgumentException("Nombre vacio");
        }
        if (p.getCantidad() < 0) {
            throw new IllegalArgumentException("La cantidad no puede ser negativa");
        }
        if (p.getPrecio() < 0) {
            throw new IllegalArgumentException("El precio no puede ser negativo");
        }
        if (p.getUbicacion() == null || p.getUbicacion().trim().isEmpty()) {
            throw new IllegalArgumentException("La ubicacion es obligatoria");
        }
        if (p.getStockMinimo() < 0) {
            throw new IllegalArgumentException("El stock minimo no puede ser negativo");
        }
        if (dao.buscar(p.getId()) != null) {
            throw new IllegalArgumentException("Ya existe un producto con ese ID");
        }
        dao.agregar(p);
    }

    public List<Producto> listar() {
        return dao.listar();
    }

    public Producto buscar(int id) {
        return dao.buscar(id);
    }

    public void eliminar(int id) {
        if (dao.buscar(id) == null) {
            throw new IllegalArgumentException("Producto no encontrado");
        }
        dao.eliminar(id);
    }

    public void entradaStock(int id, int cantidad) {
        if (cantidad <= 0) {
            throw new IllegalArgumentException("La cantidad debe ser mayor que cero");
        }
        Producto p = dao.buscar(id);
        if (p != null) {
            p.setCantidad(p.getCantidad() + cantidad);
            dao.actualizar(p);
            movDAO.registrarMovimiento(new Movimiento(id, "ENTRADA", cantidad));
            return;
        }
        throw new IllegalArgumentException("Producto no encontrado");
    }

    public void salidaStock(int id, int cantidad) {
        if (cantidad <= 0) {
            throw new IllegalArgumentException("La cantidad debe ser mayor que cero");
        }
        Producto p = dao.buscar(id);
        if (p == null) {
            throw new IllegalArgumentException("Producto no encontrado");
        }
        if (p.getCantidad() < cantidad) {
            throw new IllegalArgumentException("Stock insuficiente");
        }
        p.setCantidad(p.getCantidad() - cantidad);
        dao.actualizar(p);
        movDAO.registrarMovimiento(new Movimiento(id, "SALIDA", cantidad));
    }

    public void alertaStock() {
        boolean hayAlertas = false;
        for (Producto p : dao.listar()) {
            if (p.getCantidad() <= p.getStockMinimo()) {
                System.out.println("ALERTA: Producto bajo stock: " + p.getNombre());
                hayAlertas = true;
            }
        }
        if (!hayAlertas) {
            System.out.println("No hay productos con stock bajo.");
        }
    }

    public void reporteInventario() {
        List<Producto> productos = dao.listar();
        if (productos.isEmpty()) {
            System.out.println("No hay productos registrados.");
            return;
        }

        double total = 0;
        for (Producto p : productos) {
            total += p.getCantidad() * p.getPrecio();
        }
        System.out.println("Valor total inventario: " + String.format(Locale.US, "%.2f", total));
    }
}
