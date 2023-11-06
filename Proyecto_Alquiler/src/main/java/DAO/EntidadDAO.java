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
public interface EntidadDAO<T> {

    public boolean Registrar(T entidad);

    public boolean Editar(T entidad);

    public String getId(String numDoc);

    public List<T> getPersonaAc();

    public List<T> getPersonaInac();

    public boolean eliminar(T entidad);
    
    public boolean Reactivar(T entidad);

}
