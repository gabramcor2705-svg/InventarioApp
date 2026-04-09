/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package AccesoDatos;

import Entidades.Movimiento;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class MovimientoDAOImpl implements MovimientoDAO {

    private final String archivo = "movimientos.txt";

    private File obtenerArchivo() {
        return new File(archivo);
    }

    @Override
    public void registrarMovimiento(Movimiento m) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(obtenerArchivo(), true))) {
            bw.write(m.toString());
            bw.newLine();
        } catch (IOException e) {
            throw new RuntimeException("No se pudo registrar el movimiento", e);
        }
    }
}
