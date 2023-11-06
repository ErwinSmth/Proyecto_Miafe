/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

/**
 *
 * @author DAVID
 */
public class Usuario extends Persona {
    
    private String id_usuario;
    private String contraseña;
    private Roles_Usuarios rol;

    public Usuario(String id_usuario, String contraseña, Roles_Usuarios rol, String id_persona, String pri_nombre, String seg_nombre, String ape_paterno, String ape_materno, Tipo_Documentos tipo_doc, String num_doc, Estado_Persona estado) {
        super(id_persona, pri_nombre, seg_nombre, ape_paterno, ape_materno, tipo_doc, num_doc, estado);
        this.id_usuario = id_usuario;
        this.contraseña = contraseña;
        this.rol = rol;
    }

    public Usuario(String contraseña, Roles_Usuarios rol, String pri_nombre, String seg_nombre, String ape_paterno, String ape_materno, Tipo_Documentos tipo_doc, String num_doc, Estado_Persona estado) {
        super(pri_nombre, seg_nombre, ape_paterno, ape_materno, tipo_doc, num_doc, estado);
        this.contraseña = contraseña;
        this.rol = rol;
    }
    
    public Usuario() {
    }

    public String getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(String id_usuario) {
        this.id_usuario = id_usuario;
    }

    public String getContraseña() {
        return contraseña;
    }

    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }

    public Roles_Usuarios getRol() {
        return rol;
    }

    public void setRol(Roles_Usuarios rol) {
        this.rol = rol;
    }
        
}
