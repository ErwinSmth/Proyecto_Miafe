/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo.Inventariado;

/**
 *
 * @author DAVID
 */
public class Producto {

    private int id;
    private String nom_pro;
    private Categoria_Mobiliario categoria;
    private float precio_uni;
    private int cantDisponible;
    private int cantPrestada;
    private int cantMantenimiento;

    public Producto(int id, String nom_pro, Categoria_Mobiliario categoria, float precio_uni, int cantDisponible, int cantPrestada, int cantMantenimiento) {
        this.id = id;
        this.nom_pro = nom_pro;
        this.categoria = categoria;
        this.precio_uni = precio_uni;
        this.cantDisponible = cantDisponible;
        this.cantPrestada = cantPrestada;
        this.cantMantenimiento = cantMantenimiento;
    }

    public Producto(String nom_pro, Categoria_Mobiliario categoria, float precio_uni, int cantDisponible, int cantPrestada, int cantMantenimiento) {
        this.nom_pro = nom_pro;
        this.categoria = categoria;
        this.precio_uni = precio_uni;
        this.cantDisponible = cantDisponible;
        this.cantPrestada = cantPrestada;
        this.cantMantenimiento = cantMantenimiento;
    }

    public Producto(String nom_pro, Categoria_Mobiliario categoria, float precio_uni, int cantDisponible) {
        this.nom_pro = nom_pro;
        this.categoria = categoria;
        this.precio_uni = precio_uni;
        this.cantDisponible = cantDisponible;

    }

    public Producto(String nom_pro, Categoria_Mobiliario categoria, float precio_uni) {
        this.nom_pro = nom_pro;
        this.categoria = categoria;
        this.precio_uni = precio_uni;
    }

    public Producto() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom_pro() {
        return nom_pro;
    }

    public void setNom_pro(String nom_pro) {
        this.nom_pro = nom_pro;
    }

    public Categoria_Mobiliario getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria_Mobiliario categoria) {
        this.categoria = categoria;
    }

    public float getPrecio_uni() {
        return precio_uni;
    }

    public void setPrecio_uni(float precio_uni) {
        this.precio_uni = precio_uni;
    }

    public int getCantDisponible() {
        return cantDisponible;
    }

    public void setCantDisponible(int cantDisponible) {
        this.cantDisponible = cantDisponible;
    }

    public int getCantPrestada() {
        return cantPrestada;
    }

    public void setCantPrestada(int cantPrestada) {
        this.cantPrestada = cantPrestada;
    }

    public int getCantMantenimiento() {
        return cantMantenimiento;
    }

    public void setCantMantenimiento(int cantMantenimiento) {
        this.cantMantenimiento = cantMantenimiento;
    }

    public int total() {

        int total;
        return total = cantDisponible + cantMantenimiento + cantPrestada;

    }

}
