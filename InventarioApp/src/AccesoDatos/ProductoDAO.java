/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package AccesoDatos;

/**
 *
 * @author gabriel
 */
import Entidades.Producto;
import java.util.List;

public interface ProductoDAO {
    void agregar(Producto p);
    List<Producto> listar();
    Producto buscar(int id);
    void actualizar(Producto p);
    void eliminar(int id);
}
