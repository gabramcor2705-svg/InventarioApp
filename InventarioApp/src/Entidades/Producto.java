/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entidades;

public class Producto {
    private int id;
    private String nombre;
    private int cantidad;
    private double precio;
    private String ubicacion;
    private int stockMinimo;

    public Producto(int id, String nombre, int cantidad, double precio, String ubicacion, int stockMinimo) {
        this.id = id;
        this.nombre = nombre;
        this.cantidad = cantidad;
        this.precio = precio;
        this.ubicacion = ubicacion;
        this.stockMinimo = stockMinimo;
    }

    public int getId() { return id; }
    public String getNombre() { return nombre; }
    public int getCantidad() { return cantidad; }
    public double getPrecio() { return precio; }
    public String getUbicacion() { return ubicacion; }
    public int getStockMinimo() { return stockMinimo; }

    public void setCantidad(int cantidad) { this.cantidad = cantidad; }

    @Override
    public String toString() {
        return id + "," + nombre + "," + cantidad + "," + precio + "," + ubicacion + "," + stockMinimo;
    }
}
