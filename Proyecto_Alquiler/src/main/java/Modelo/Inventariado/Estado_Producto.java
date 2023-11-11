/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo.Inventariado;

/**
 *
 * @author DAVID
 */
public class Estado_Producto {

    private int id;
    private Producto producto;
    private Estado_Mobiliario estado;
    private int cantidad;

    public Estado_Producto(Producto producto, Estado_Mobiliario estado, int cantidad) {
        this.producto = producto;
        this.estado = estado;
        this.cantidad = cantidad;
    }

    public Estado_Producto(int id, Producto producto, Estado_Mobiliario estado, int cantidad) {
        this.id = id;
        this.producto = producto;
        this.estado = estado;
        this.cantidad = cantidad;
    }

    public Estado_Producto() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    public Estado_Mobiliario getEstado() {
        return estado;
    }

    public void setEstado(Estado_Mobiliario estado) {
        this.estado = estado;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

}
