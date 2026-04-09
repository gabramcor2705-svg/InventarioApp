/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entidades;

/**
 *
 * este codigo esta echo para entradas y salidas
 */
public class Movimiento {
    private int idProducto;
    private String tipo; // ENTRADA o SALIDA
    private int cantidad;

    public Movimiento(int idProducto, String tipo, int cantidad) {
        this.idProducto = idProducto;
        this.tipo = tipo;
        this.cantidad = cantidad;
    }

    @Override
    public String toString() {
        return idProducto + "," + tipo + "," + cantidad;
    }
}
