/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo.Servicio;

import Modelo.Cliente;
import java.time.LocalDate;
import java.util.List;

/**
 *
 * @author DAVID
 */
public class ContratoAlquiler {
    
    private String id_alquiler;
    private LocalDate fecha_Contrato;
    private LocalDate fecha_entrega;
    private LocalDate fecha_devolucion;
    private Cliente cliente;
    private float costolTotal;
    private int cantidadTotal;
    private List<Item> itemsAlquiler;

    public ContratoAlquiler() {
    }

    public ContratoAlquiler(LocalDate fecha_Contrato, LocalDate fecha_entrega, LocalDate fecha_devolucion, Cliente cliente, 
            float costolTotal, int cantidadTotal, List<Item> itemsAlquiler) {
        this.fecha_Contrato = fecha_Contrato;
        this.fecha_entrega = fecha_entrega;
        this.fecha_devolucion = fecha_devolucion;
        this.cliente = cliente;
        this.costolTotal = costolTotal;
        this.cantidadTotal = cantidadTotal;
        this.itemsAlquiler = itemsAlquiler;
    }

    public String getId_alquiler() {
        return id_alquiler;
    }

    public void setId_alquiler(String id_alquiler) {
        this.id_alquiler = id_alquiler;
    }

    public LocalDate getFecha_Contrato() {
        return fecha_Contrato;
    }

    public void setFecha_Contrato(LocalDate fecha_Contrato) {
        this.fecha_Contrato = fecha_Contrato;
    }

    public LocalDate getFecha_entrega() {
        return fecha_entrega;
    }

    public void setFecha_entrega(LocalDate fecha_entrega) {
        this.fecha_entrega = fecha_entrega;
    }

    public LocalDate getFecha_devolucion() {
        return fecha_devolucion;
    }

    public void setFecha_devolucion(LocalDate fecha_devolucion) {
        this.fecha_devolucion = fecha_devolucion;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public float getCostolTotal() {
        return costolTotal;
    }

    public void setCostolTotal(float costolTotal) {
        this.costolTotal = costolTotal;
    }

    public int getCantidadTotal() {
        return cantidadTotal;
    }

    public void setCantidadTotal(int cantidadTotal) {
        this.cantidadTotal = cantidadTotal;
    }

    public List<Item> getItemsAlquiler() {
        return itemsAlquiler;
    }

    public void setItemsAlquiler(List<Item> itemsAlquiler) {
        this.itemsAlquiler = itemsAlquiler;
    }
    
    public float costoTotal(List<Item> itemAlquiler){
        
        float costoTotal = 0;
        
        for(Item item : itemAlquiler){
            costoTotal += item.calcularTotal();
        }
        return costoTotal;
    }
    
}
