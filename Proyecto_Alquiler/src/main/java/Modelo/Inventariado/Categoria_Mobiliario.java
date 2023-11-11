/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo.Inventariado;

/**
 *
 * @author DAVID
 */
public class Categoria_Mobiliario {

    private int id;
    private String nom_cat;
    private String descrip;

    public Categoria_Mobiliario(String nom_cat, String descrip) {
        this.nom_cat = nom_cat;
        this.descrip = descrip;
    }

    public Categoria_Mobiliario(int id, String nom_cat, String descrip) {
        this.id = id;
        this.nom_cat = nom_cat;
        this.descrip = descrip;
    }

    public Categoria_Mobiliario() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom_cat() {
        return nom_cat;
    }

    public void setNom_cat(String nom_cat) {
        this.nom_cat = nom_cat;
    }

    public String getDescrip() {
        return descrip;
    }

    public void setDescrip(String descrip) {
        this.descrip = descrip;
    }

}
