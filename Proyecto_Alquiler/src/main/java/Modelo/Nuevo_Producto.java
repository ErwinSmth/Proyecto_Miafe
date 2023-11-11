/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

/**
 *
 * @author DAVID
 */
public class Nuevo_Producto {
    
    private int cantidad;
    private Producto producto;

    public Nuevo_Producto(int cantidad, Producto producto) {
        this.cantidad = cantidad;
        this.producto = producto;
    }

    public Nuevo_Producto() {
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    public Producto nuevoProducto(){
        
        Producto nuevoProducto = new Producto();
        nuevoProducto.setNom_pro(producto.getNom_pro());
        nuevoProducto.setCategoria(producto.getCategoria());
        nuevoProducto.setPrecio_uni(producto.getPrecio_uni());
        
        return nuevoProducto;
    }
    
    
    
}
