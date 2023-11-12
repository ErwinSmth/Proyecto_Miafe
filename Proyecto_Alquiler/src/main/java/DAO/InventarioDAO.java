/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import java.util.List;

/**
 *
 * @author DAVID
 */
public interface InventarioDAO<T> {
    
    public boolean agregar(T obj);
    
    public boolean eliminar(T obj);
    
    public boolean editar(T obj);
    
    public List<T> getListado();
    
}
