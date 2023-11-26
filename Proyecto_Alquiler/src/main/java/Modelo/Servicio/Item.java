/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo.Servicio;

import Modelo.Inventariado.Producto;

/**
 *
 * @author DAVID
 */
public class Item {
    
    private int cantidad;
    private float conteo_Precio;
    private Producto producto;

    public Item() {
    }

    public Item(int cantidad, float conteo_Precio, Producto producto) {
        this.cantidad = cantidad;
        this.conteo_Precio = conteo_Precio;
        this.producto = producto;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public float getConteo_Precio() {
        return conteo_Precio;
    }

    public void setConteo_Precio(float conteo_Precio) {
        this.conteo_Precio = conteo_Precio;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }
    
    public float calcularTotal(){
        
        return cantidad * producto.getPrecio_uni();
        
    }
    
}
