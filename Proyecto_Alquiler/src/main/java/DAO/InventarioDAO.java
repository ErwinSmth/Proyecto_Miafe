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
    
    //1 Representara que se agrego, elimino, etc correctamente
    //2 Representa que hubo un error
    
    public int agregar(T obj);
    
    public int eliminar(T obj);
    
    public int editar(T obj);
    
    public List<T> getListado();
    
}
