/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Modelo.Inventariado.Categoria_Mobiliario;
import Modelo.Inventariado.Producto;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author DAVID
 */
public class ConsultaProducto implements InventarioDAO<Producto> {

    //1 Representara que se agrego, elimino, etc correctamente
    //2 Representa que hubo un error
    ConexionBD conectar = ConexionBD.getConexion();

    //Metodo para agregar un producto al inventario por defecto solo permitira agregar objetos que estan disponibles a prestar
    //Retornada 3 en caso los productos a agregar sean negativo o cero
    //Retornada 4 en caso el precio a insertar sea negatico o cero
    @Override
    public int agregar(Producto obj) {

        String consulta = "Insert into Inventario (nombre_cat, nombre, cant_disponible, precio) values (?,?,?,?)";
        PreparedStatement ps = null;

        try {

            ps = conectar.conectar().prepareStatement(consulta);
            ps.setString(1, obj.getCategoria().getNom_cat());
            ps.setString(2, obj.getNom_pro());
            ps.setInt(3, obj.getCantDisponible());
            ps.setFloat(4, obj.getPrecio_uni());

            if (obj.getCantDisponible() <= 0) {
                return 3;
            } else if (obj.getPrecio_uni() <= 0) {
                return 4;
            }

            ps.executeUpdate();

            return 1;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return 2;
    }

    @Override
    public int eliminar(Producto obj) {

        String consulta = "Delete from Inventario where nombre = ?";
        PreparedStatement ps = null;

        try {

            ps = conectar.conectar().prepareStatement(consulta);
            ps.setString(1, obj.getNom_pro());
            ps.executeUpdate();

            return 1;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 2;
    }

    //Metodo para editar el precio de un producto
    @Override
    public int editar(Producto obj) {

        String consulta = "Update Inventario set precio = ? where nombre = ?";
        PreparedStatement ps = null;

        try {

            ps = conectar.conectar().prepareStatement(consulta);
            ps.setFloat(1, obj.getPrecio_uni());
            ps.setString(2, obj.getNom_pro());

            ps.executeUpdate();

            return 1;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return 2;
    }

    //Metodo para agregar mas productos disponibles
    //Se usara especialmente en casos en los que de un producto que ya esta en la bd se compren mas
    //Retornada 3 en caso la cantidad a poner disponible sea negativa o cero
    public int setDisponibles(Producto obj, int cantidad) {

        String consulta = "Update Inventario set cant_disponible = cant_disponible + ? where nombre = ?";
        PreparedStatement ps = null;

        if (cantidad <= 0) {
            return 3;
        } else {
            try {

                ps = conectar.conectar().prepareStatement(consulta);
                ps.setInt(1, cantidad);
                ps.setString(2, obj.getNom_pro());
                ps.executeUpdate();

                return 1;

            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return 2;
    }

    //Metodo a usar cuando vas a quitar producto disponibles porque los vas a prestar
    //Retornada 3 cuando se ingrese una cantidad a prestar que supere los productos que esten disponibles
    //Retornada 4 cuando la cantidad a prestar sea negativa 0 cero
    public int setPrestado(Producto obj, int cantidad) {

        PreparedStatement ps = null;
        String check = "Select cant_disponible from Inventario where nombre = ?";
        int disponible = 0;
        ResultSet rs = null;

        if (cantidad <= 0) {
            return 4;
        } else {
            try {

                ps = conectar.conectar().prepareStatement(check);
                ps.setString(1, obj.getNom_pro());
                rs = ps.executeQuery();

                if (rs.next()) {

                    disponible = rs.getInt("cant_disponible");
                    if (disponible >= cantidad) {

                        String consulta = "UPDATE Inventario\n"
                                + "SET cant_prestada = cant_prestada + ?,\n"
                                + "    cant_disponible = cant_disponible - ?\n"
                                + "WHERE nombre = ? ";

                        ps = conectar.conectar().prepareStatement(consulta);
                        ps.setInt(1, cantidad);
                        ps.setInt(2, cantidad);
                        ps.setString(3, obj.getNom_pro());

                        ps.executeUpdate();
                        return 1;

                    } else {
                        return 3; //No hay suficientes disponibles para prestar
                    }
                }

            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return 2;
    }

    //Metodo a usarse cuando se va a cambiar cierta cantidad de productos disponibles a en mantenimiento
    //Retornada 3 cuando se ingrese una cantidad a cambiar a mantenimiento que supere los productos que esten disponibles
    //Retornada 4 cuando la cantidad a cambiar a mantenimiento sea negativa 0 cero
    public int setMantenimiento(Producto obj, int cantidad) {

        PreparedStatement ps = null;
        String check = "Select cant_disponible from Inventario where nombre = ?";
        int disponible = 0;
        ResultSet rs = null;

        if (cantidad <= 0) {
            return 4;
        } else {
            try {

                ps = conectar.conectar().prepareStatement(check);
                ps.setString(1, obj.getNom_pro());
                rs = ps.executeQuery();

                if (rs.next()) {

                    disponible = rs.getInt("cant_disponible");

                    if (disponible >= cantidad) {

                        String consulta = "UPDATE Inventario\n"
                                + "SET cant_mantenimiento = cant_mantenimiento + ?,\n"
                                + "    cant_disponible = cant_disponible - ?\n"
                                + "WHERE nombre = ?;";

                        ps = conectar.conectar().prepareStatement(consulta);
                        ps.setInt(1, cantidad);
                        ps.setInt(2, cantidad);
                        ps.setString(3, obj.getNom_pro());

                        ps.executeUpdate();

                    } else {
                        return 3;//No hay suficientes disponibles para cambiar a mantenimiento
                    }

                }

            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return 2;
    }

    @Override
    public List<Producto> getListado() {

        List<Producto> listado = new ArrayList<>();
        String consulta = "Select nombre, \n"
                + "nombre_cat AS Categoria,\n"
                + "cant_disponible AS Disponibles,\n"
                + "cant_prestada AS Prestadas,\n"
                + "cant_mantenimiento AS Mantenimiento,\n"
                + "precio from Inventario";
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {

            ps = conectar.conectar().prepareStatement(consulta);
            rs = ps.executeQuery();

            while (rs.next()) {

                Producto pro = new Producto();
                Categoria_Mobiliario cat = new Categoria_Mobiliario();
                pro.setNom_pro(rs.getString("nombre"));
                cat.setNom_cat(rs.getString("categoria"));
                pro.setCategoria(cat);
                pro.setCantDisponible(rs.getInt("Disponibles"));
                pro.setCantPrestada(rs.getInt("Prestadas"));
                pro.setCantMantenimiento(rs.getInt("Mantenimiento"));
                pro.setPrecio_uni(rs.getFloat("precio"));

                listado.add(pro);

            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return listado;
    }

//    public static void main(String[] args) {
//
//        ConsultaProducto con = new ConsultaProducto();
//        // Caso 2: Operación válida
//        Producto producto2 = new Producto();
//        producto2.setNom_pro("Silla artesanal");
//        int resultado2 = con.setPrestado(producto2, 5);
//
//        if (resultado2 == 1) {
//            System.out.println("Caso 2: Operación exitosa. Productos prestados correctamente.");
//        } else {
//            System.out.println("Caso 2: Error al prestar productos.");
//        }
//
//    }
}
