/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

/**
 *
 * @author LAB-USR-CHIM-A0202
 */
public class Cliente extends Persona {

    private String id_cliente;
    private String correo;
    private String direccion;
    private String telefono;

    public Cliente(String correo, String direccion, String telefono, String id_persona, String pri_nombre, String seg_nombre, String ape_paterno, String ape_materno, Tipo_Documentos tipo_doc, String num_doc, Estado_Persona estado) {
        super(id_persona, pri_nombre, seg_nombre, ape_paterno, ape_materno, tipo_doc, num_doc, estado);
        this.correo = correo;
        this.direccion = direccion;
        this.telefono = telefono;
    }

    public Cliente() {
    }

    public Cliente(String correo, String direccion, String telefono, String pri_nombre, String seg_nombre, String ape_paterno, String ape_materno, Tipo_Documentos tipo_doc, String num_doc, Estado_Persona estado) {
        super(pri_nombre, seg_nombre, ape_paterno, ape_materno, tipo_doc, num_doc, estado);
        this.correo = correo;
        this.direccion = direccion;
        this.telefono = telefono;
    }
    
    

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getId_cliente() {
        return id_cliente;
    }

    public void setId_cliente(String id_cliente) {
        this.id_cliente = id_cliente;
    }

    
    
}
