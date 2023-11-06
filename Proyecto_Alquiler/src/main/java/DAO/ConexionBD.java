/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author DAVID
 */
public class ConexionBD {

    private ConexionBD() {

    }

    //Variable que conectara a la BD
    private static Connection con;

    //variable que sera la unica instancia
    private static ConexionBD instancia;

    //Metodo para iniciar la conexion
    public Connection conectar() {

        try {
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/tienda_miafe", "Admin_Miafe", "12345");
            return con;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    //Metodo para cerrar la conexion
    public void Desconectar() throws SQLException {
        try {
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
            con.close();
        } finally {
            con.close();
        }
    }

    //Patron Singleton para usar una sola conexion en la BD
    public static ConexionBD getConexion() {

        if (instancia == null) {
            instancia = new ConexionBD();
        }
        return instancia;

    }
    
//    public static void main(String[] args) {
//        
//        ConexionBD conectar = ConexionBD.getConexion();
//        
//        try {
//            
//            conectar.conectar();
//            System.out.println("Exito");
//            
//        } catch (Exception e) {
//            System.out.println("Error");
//        }
//        
//    }

}
