/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Modelo.Inventariado.Categoria_Mobiliario;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author DAVID
 */
public class ConsultaCategoria implements InventarioDAO<Categoria_Mobiliario> {

    ConexionBD conectar = ConexionBD.getConexion();

    @Override
    public boolean agregar(Categoria_Mobiliario obj) {

        String consulta = "Insert into Categoria_Objeto(nombre, descripcion) values (?,?)";

        PreparedStatement ps = null;

        try {

            ps = conectar.conectar().prepareStatement(consulta);
            ps.setString(1, obj.getNom_cat());
            ps.setString(2, obj.getDescrip());
            ps.executeUpdate();

            return true;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;

    }

    @Override
    public boolean eliminar(Categoria_Mobiliario obj) {

        String consulta = "Delete from Categoria_Objeto where nombre = ? ";
        PreparedStatement ps = null;

        try {

            ps = conectar.conectar().prepareStatement(consulta);
            ps.setString(1, obj.getNom_cat());
            ps.executeUpdate();

            return true;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;

    }

    @Override
    public boolean editar(Categoria_Mobiliario obj) {

        String obtenerId = "Select id_categoria from Categoria_Objeto where nombre = ?";
        PreparedStatement ps = null;

        try {

            int id = 0;
            ps = conectar.conectar().prepareStatement(obtenerId);
            ps.setString(1, obj.getNom_cat());
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {

                id = rs.getInt("id_categoria");

                if (id != 0) {

                    String consulta = "Update Categoria_Objeto set descripcion = ? where id_categoria = ?";
                    ps = conectar.conectar().prepareStatement(consulta);

                    ps.setString(1, obj.getDescrip());
                    ps.setInt(2, id);

                    ps.executeUpdate();

                    return true;

                } else {
                    return false;
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;

    }

    @Override
    public List<Categoria_Mobiliario> getListado() {

        List<Categoria_Mobiliario> listado = new ArrayList<>();
        String consulta = "Select nombre, descripcion from Categoria_Objeto";
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {

            ps = conectar.conectar().prepareStatement(consulta);
            rs = ps.executeQuery();

            while (rs.next()) {
                Categoria_Mobiliario cat = new Categoria_Mobiliario();
                cat.setNom_cat(rs.getString("nombre"));
                cat.setDescrip(rs.getString("descripcion"));

                listado.add(cat);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return listado;
    }

    public static void main(String[] args) {

        ConsultaCategoria con = new ConsultaCategoria();
        Categoria_Mobiliario cat = new Categoria_Mobiliario();
        List<Categoria_Mobiliario> listado = con.getListado();   

        for (Categoria_Mobiliario obj : listado) {
            System.out.println("Nombre: " + obj.getNom_cat() + ", Descripcion: " + obj.getDescrip());
        }

    }

}
