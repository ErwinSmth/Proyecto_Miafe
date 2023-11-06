/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

/**
 *
 * @author DAVID
 */
public class Persona {

    private String id_persona;
    private String pri_nombre;
    private String seg_nombre;
    private String ape_paterno;
    private String ape_materno;
    private Tipo_Documentos tipo_doc;
    private String num_doc;
    private Estado_Persona estado;

    public Persona(String id_persona, String pri_nombre, String seg_nombre, String ape_paterno, String ape_materno, Tipo_Documentos tipo_doc, String num_doc, Estado_Persona estado) {
        this.id_persona = id_persona;
        this.pri_nombre = pri_nombre;
        this.seg_nombre = seg_nombre;
        this.ape_paterno = ape_paterno;
        this.ape_materno = ape_materno;
        this.tipo_doc = tipo_doc;
        this.num_doc = num_doc;
        this.estado = estado;
    }

    public Persona(String pri_nombre, String seg_nombre, String ape_paterno, String ape_materno, Tipo_Documentos tipo_doc, String num_doc, Estado_Persona estado) {
        this.pri_nombre = pri_nombre;
        this.seg_nombre = seg_nombre;
        this.ape_paterno = ape_paterno;
        this.ape_materno = ape_materno;
        this.tipo_doc = tipo_doc;
        this.num_doc = num_doc;
        this.estado = estado;
    }
    
    

    public Persona() {
    }

    public String getId_persona() {
        return id_persona;
    }

    public void setId_persona(String id_persona) {
        this.id_persona = id_persona;
    }

    public String getPri_nombre() {
        return pri_nombre;
    }

    public void setPri_nombre(String pri_nombre) {
        this.pri_nombre = pri_nombre;
    }

    public String getSeg_nombre() {
        return seg_nombre;
    }

    public void setSeg_nombre(String seg_nombre) {
        this.seg_nombre = seg_nombre;
    }

    public String getApe_paterno() {
        return ape_paterno;
    }

    public void setApe_paterno(String ape_paterno) {
        this.ape_paterno = ape_paterno;
    }

    public String getApe_materno() {
        return ape_materno;
    }

    public void setApe_materno(String ape_materno) {
        this.ape_materno = ape_materno;
    }

    public Tipo_Documentos getTipo_doc() {
        return tipo_doc;
    }

    public void setTipo_doc(Tipo_Documentos tipo_doc) {
        this.tipo_doc = tipo_doc;
    }

    public String getNum_doc() {
        return num_doc;
    }

    public void setNum_doc(String num_doc) {
        this.num_doc = num_doc;
    }

    public Estado_Persona getEstado() {
        return estado;
    }

    public void setEstado(Estado_Persona estado) {
        this.estado = estado;
    }

    
    
}
