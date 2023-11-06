/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author DAVID
 */
public class ConsultaDATOSFIJOS {
    
    ConexionBD conectar = ConexionBD.getConexion();

    //Obtener el nombre del tipo de documento por su ID
    public String obtenerNombreT_DOC(int tiDocID) {

        String notiDoc = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        String consulta = "SELECT nombre_tipo FROM Tipo_Documento WHERE id_tipo_doc = ?";

        try {

            ps = conectar.conectar().prepareStatement(consulta);
            ps.setInt(1, tiDocID);
            rs = ps.executeQuery();

            if (rs.next()) {
                notiDoc = rs.getString("nombre_tipo");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return notiDoc;
    }
    //----------------------------------------------------------------------------------------------------------

    //Obtener el id del tipo de documento por su nombre
    public int obtenerIDTipoDoc(String nombreTIDOC) {

        int tipoDocID = -1;
        String consulta = "SELECT id_tipo_doc FROM Tipo_Documento WHERE nombre_tipo = ?";

        try (PreparedStatement ps = conectar.conectar().prepareStatement(consulta)) {
            ps.setString(1, nombreTIDOC);

            try (ResultSet rs = ps.executeQuery()) {

                if (rs.next()) {
                    tipoDocID = rs.getInt("id_tipo_doc");
                }

            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return tipoDocID;
    }
    //----------------------------------------------------------------------------------------------------------

    //obtener el nombre del rol por su ID
    public String ObtenerNombreROL(int rolID) {

        String noROL = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        String consulta = "SELECT nombre_rol FROM Rol_Empleado WHERE id_rol = ?";

        try {

            ps = conectar.conectar().prepareCall(consulta);
            ps.setInt(1, rolID);
            rs = ps.executeQuery();

            if (rs.next()) {
                noROL = rs.getString("nombre_rol");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return noROL;
    }
    //----------------------------------------------------------------------------------------------------------

    //Obtener el id del rol por su nombre
    public int obtenerIDRol(String nombreROL) {

        int rolID = -1;
        String consulta = "SELECT id_rol FROM Rol_Empleado WHERE nombre_rol = ?";

        try (PreparedStatement ps = conectar.conectar().prepareStatement(consulta)) {
            ps.setString(1, nombreROL);

            try (ResultSet rs = ps.executeQuery()) {

                if (rs.next()) {
                    rolID = rs.getInt("id_rol");
                }

            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return rolID;
    }
    //----------------------------------------------------------------------------------------------------------
    //obtener el ultimo id insertado en una tabla
    public int obtenerUltimoID() {
        int lastID = -1;
        PreparedStatement ps = null;
        ResultSet rs = null;

        String consulta = "SELECT MAX(id_persona) FROM Persona";

        try {
            ps = conectar.conectar().prepareCall(consulta);
            rs = ps.executeQuery();

            if (rs.next()) {
                lastID = rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lastID;
    }
    //----------------------------------------------------------------------------------------------------------
}
