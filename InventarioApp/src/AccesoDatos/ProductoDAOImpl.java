/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package AccesoDatos;
import Entidades.Producto;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ProductoDAOImpl implements ProductoDAO {

    private final String archivo = "productos.txt";

    private File obtenerArchivo() {
        return new File(archivo);
    }

    @Override
    public void agregar(Producto p) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(obtenerArchivo(), true))) {
            bw.write(p.toString());
            bw.newLine();
        } catch (IOException e) {
            throw new RuntimeException("No se pudo guardar el producto", e);
        }
    }

    @Override
    public List<Producto> listar() {
        List<Producto> lista = new ArrayList<>();
        File file = obtenerArchivo();
        if (!file.exists()) {
            return lista;
        }
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                if (linea.trim().isEmpty()) {
                    continue;
                }

                try {
                    String[] d = linea.split(",");
                    if (d.length < 6) {
                        continue;
                    }

                    lista.add(new Producto(
                            Integer.parseInt(d[0]),
                            d[1],
                            Integer.parseInt(d[2]),
                            Double.parseDouble(d[3]),
                            d[4],
                            Integer.parseInt(d[5])
                    ));
                } catch (NumberFormatException e) {
                    // Ignora lineas corruptas y sigue cargando el resto.
                }
            }
        } catch (IOException e) {
            throw new RuntimeException("No se pudo leer el inventario", e);
        }
        return lista;
    }

    @Override
    public Producto buscar(int id) {
        return listar().stream().filter(p -> p.getId() == id).findFirst().orElse(null);
    }

    @Override
    public void actualizar(Producto p) {
        List<Producto> lista = listar();
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(obtenerArchivo()))) {
            for (Producto prod : lista) {
                if (prod.getId() == p.getId()) {
                    bw.write(p.toString());
                } else {
                    bw.write(prod.toString());
                }
                bw.newLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("No se pudo actualizar el producto", e);
        }
    }

    @Override
    public void eliminar(int id) {
        List<Producto> lista = listar();
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(obtenerArchivo()))) {
            for (Producto p : lista) {
                if (p.getId() != id) {
                    bw.write(p.toString());
                    bw.newLine();
                }
            }
        } catch (IOException e) {
            throw new RuntimeException("No se pudo eliminar el producto", e);
        }
    }
}
