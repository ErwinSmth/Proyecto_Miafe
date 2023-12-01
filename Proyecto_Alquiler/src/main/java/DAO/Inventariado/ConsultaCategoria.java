/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO.Inventariado;

import DAO.ConexionBD;
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

    //1 Representara que se agrego, elimino, etc correctamente
    //2 Representa que hubo un error
    ConexionBD conectar = ConexionBD.getConexion();

    //Retornara 3 en caso se quiera ingresar una categoria que ya esta repetida
    @Override
    public int agregar(Categoria_Mobiliario obj) {

        if (catRepetido(obj.getNom_cat())) {
            return 3;//La categoria ya existe en la bd
        }

        String consulta = "Insert into Categoria_Objeto(nombre_cat, descripcion) values (?,?)";

        PreparedStatement ps = null;

        try {

            conectar.conectar().setAutoCommit(false);

            ps = conectar.conectar().prepareStatement(consulta);
            ps.setString(1, obj.getNom_cat());
            ps.setString(2, obj.getDescrip());
            int exito = ps.executeUpdate();

            if (exito > 0) {
<<<<<<< HEAD
                return 1;
            } 
=======
                conectar.conectar().commit();
                return 1;
            } else {
                conectar.conectar().rollback();
            }
>>>>>>> aa454cd4816c942fca4242646f5d94114af81b4b

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return 2;

    }

    //Metodo para verificar si la categoria ya existe en la bd antes de agregar alguna
    private boolean catRepetido(String nombre) {

        String consulta = "SELECT * FROM Categoria_Objeto WHERE nombre_cat = ?";
        try ( PreparedStatement ps = conectar.conectar().prepareStatement(consulta)) {
            ps.setString(1, nombre);

            try ( ResultSet rs = ps.executeQuery()) {
                return rs.next(); // True si existe esa categoria en la bd
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;

    }

    @Override
    public int eliminar(Categoria_Mobiliario obj) {

        String consulta = "Delete from Categoria_Objeto where nombre_cat = ? ";
        PreparedStatement ps = null;

        try {

            ps = conectar.conectar().prepareStatement(consulta);
            ps.setString(1, obj.getNom_cat());
            ps.executeUpdate();

            return 1;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return 2;

    }

    @Override
    public int editar(Categoria_Mobiliario obj) {

        String consulta = "UPDATE Categoria_Objeto SET descripcion = ? WHERE nombre_cat = ?";
        PreparedStatement ps = null;

        try {

            ps = conectar.conectar().prepareStatement(consulta);
            ps.setString(1, obj.getDescrip());
            ps.setString(2, obj.getNom_cat());

            ps.executeUpdate();

            return 1;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return 2;

    }

    @Override
    public List<Categoria_Mobiliario> getListado() {

        List<Categoria_Mobiliario> listado = new ArrayList<>();
        String consulta = "Select nombre_cat, descripcion from Categoria_Objeto";
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {

            ps = conectar.conectar().prepareStatement(consulta);
            rs = ps.executeQuery();

            while (rs.next()) {
                Categoria_Mobiliario cat = new Categoria_Mobiliario();
                cat.setNom_cat(rs.getString("nombre_cat"));
                cat.setDescrip(rs.getString("descripcion"));

                listado.add(cat);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return listado;
    }

//    public static void main(String[] args) {
//
//        ConsultaCategoria con = new ConsultaCategoria();
//        Categoria_Mobiliario cat = new Categoria_Mobiliario();
//        List<Categoria_Mobiliario> listado = con.getListado();
//
//        for (Categoria_Mobiliario obj : listado) {
//            System.out.println("Nombre: " + obj.getNom_cat() + ", Descripcion: " + obj.getDescrip());
//        }
//
//    }
}
