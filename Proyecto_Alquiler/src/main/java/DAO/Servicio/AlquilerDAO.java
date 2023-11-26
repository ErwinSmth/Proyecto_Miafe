/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO.Servicio;

import DAO.ConexionBD;
import Modelo.Inventariado.Categoria_Mobiliario;
import Modelo.Inventariado.Producto;
import Modelo.Servicio.ContratoAlquiler;
import Modelo.Servicio.Item;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author DAVID
 */
public class AlquilerDAO {

    //1 Representara que se agrego, elimino, etc correctamente
    //2 Representa que hubo un error
    ConexionBD conectar = ConexionBD.getConexion();

    //Metodo para mostrar en una tabla para que el empleado pueda ver toda la lista de los productos
    //(Solo mostrara el nombre y disponible de un producto)
    public List<Producto> getProducto() {

        String query = "SELECT nombre, cant_disponible FROM inventario";
        List<Producto> productos = new ArrayList<>();

        try (PreparedStatement ps = conectar.conectar().prepareStatement(query); ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Producto producto = new Producto();
                producto.setNom_pro(rs.getString("nombre"));
                producto.setCantDisponible(rs.getInt("cant_disponible"));
                productos.add(producto);
            }
        } catch (SQLException e) {
            // Manejo de la excepción aquí
            e.printStackTrace();
        }
        return productos;

    }

    //Metodo para buscar un ID de un Cliente a partir de su correo
    //Retornara 0 en caso no encuentre el id
    public int getIDCliente(String correo) {
        int idCliente = 0;
        String query = "SELECT id_cliente FROM Cliente WHERE correo = ?";

        try (PreparedStatement ps = conectar.conectar().prepareStatement(query)) {
            ps.setString(1, correo);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                idCliente = rs.getInt("id_cliente");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return idCliente;
    }

    //Metodo que se usara para registrar un Alquiler(Se hara uso de la tabla Alquiler), los objetos se añadiran
    //al alquiler en otro metodo
    public int addAlquiler(ContratoAlquiler obj) {

        String query = "Insert into Alquiler (id_cliente, fecha_contrato) values (?, ?)";

        try (PreparedStatement ps = conectar.conectar().prepareStatement(query)) {

            ps.setString(1, obj.getCliente().getId_cliente());

            //Convertir Local a java.sql.Date
            java.sql.Date fechaSQL = java.sql.Date.valueOf(obj.getFecha_Contrato());
            ps.setDate(2, fechaSQL);

            ps.executeUpdate();

            return 1;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return 2;
    }

    //Este metodo se usara para registrar en que fecha el cliente devolvio los productos
    public int setDevolucion(ContratoAlquiler obj) {

        String query = "UPDATE Alquiler AS a\n"
                + "JOIN Cliente AS c ON a.id_cliente = c.id_cliente\n"
                + "SET a.fecha_devolucion = ?\n"
                + "WHERE c.correo = ?;";

        try (PreparedStatement ps = conectar.conectar().prepareStatement(query)) {

            //Convertir Local a java.sql.Date
            java.sql.Date fechaSQL = java.sql.Date.valueOf(obj.getFecha_devolucion());
            ps.setDate(1, fechaSQL);
            ps.setString(2, obj.getCliente().getCorreo());

            int fila = ps.executeUpdate();

            if (fila > 0) {
                return 1;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return 2;
    }

    //Este metodo se usara si en caso el cliente desea cancelar un alquiler 
    public int eliminarAlquiler(ContratoAlquiler obj) {

        String query = "Delete from Alquiler where id_cliente = ?";

        try (PreparedStatement ps = conectar.conectar().prepareStatement(query)) {

            ps.setString(1, obj.getCliente().getId_cliente());

            ps.executeUpdate();

            return 1;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return 2;
    }

    //Este metodo se usara para agregar Items o productos a un alquiler
    //para esto se hara uso de la tabla alquiler_inventario, se añadiran objetos a un alquiler
    //cada vez que el empleado o administrador haga click en la tabla
    public int addItem(Item i, int idAlquiler) {

        String query = "insert into Alquiler_Inventario(id_alquiler, nombre, cantidad_alquilada, precio_alquiler)\n"
                + "values(?, ?, ?, ?)";

        try (PreparedStatement ps = conectar.conectar().prepareStatement(query)) {

            ps.setInt(1, idAlquiler);
            ps.setString(2, i.getProducto().getNom_pro());
            ps.setInt(3, i.getCantidad());
            ps.setFloat(4, i.calcularTotal());

            int fila = ps.executeUpdate();

            if (fila > 0) {
                return 1;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return 2;
    }

    //Este metodo se usara en caso se desee editar la cantidad de objeto que ya se asocio a un alquiler
    public int updateCant(int idAlquiler, String nombrePro, int nuevaCant) {

        String query = "Update Alquiler_Inventario SET cantidad_alquilada = ? WHERE id_alquiler = ? AND nombre = ?";

        try (PreparedStatement ps = conectar.conectar().prepareStatement(query)) {

            ps.setInt(1, idAlquiler);
            ps.setString(2, nombrePro);
            ps.setInt(1, nuevaCant);

            return ps.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    //Recupera todos los objetos o Items asociados a un alquiler 
    //Usando la tabla Alquiler_Inventario
    public List<Item> getItems(int idAlquiler) {
        List<Item> itemsAlquiler = new ArrayList<>();

        String query = "Select nombre, cantidad_alquilada, precio_alquiler FROM Alquiler_Inventario WHERE id_alquiler = ?";

        try (PreparedStatement ps = conectar.conectar().prepareStatement(query)) {
            ps.setInt(1, idAlquiler);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                String nombre = rs.getString("nombre");
                int cantidadAlquilada = rs.getInt("cantidad_alquilada");
                float precioAlquiler = rs.getFloat("precio_alquiler");

                // Crear el objeto Item con los datos recuperados de la base de datos
                // Aquí, deberás obtener el Producto asociado a través del nombre
                Producto producto = getProducNombre(nombre); // Debes implementar este método

                // Crear un nuevo Item y agregarlo a la lista
                Item item = new Item(cantidadAlquilada, precioAlquiler, producto);
                itemsAlquiler.add(item);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return itemsAlquiler;
    }

    //Este metodo se usara despues de que ya se selecciono todos los objetos
    //que el cliente desea alquilar (solamente actualizar los campos fecha_entrega,
    //cantidad_total y precio_total de un registro de la tabla Alquiler
    public int terminarContrato(ContratoAlquiler obj) {

        String query = "UPDATE Alquiler AS a\n"
                + "JOIN Cliente AS c ON a.id_cliente = c.id_cliente\n"
                + "SET a.fecha_entrega = ?, a.cantidad_total = ?, a.precio_total = ?\n"
                + "WHERE c.correo = ?;";

        try (PreparedStatement ps = conectar.conectar().prepareStatement(query)) {

            //Convertir Local a java.sql.Date
            java.sql.Date fechaSQL = java.sql.Date.valueOf(obj.getFecha_entrega());
            ps.setDate(1, fechaSQL);

            //Calcular la cantidad de todos los items asociados a un contrato
            int cantTotal = 0;
            for (Item item : obj.getItemsAlquiler()) {
                cantTotal += item.getCantidad();
            }
            ps.setInt(2, cantTotal);

            //Calcular el costo total de los items asociados a un contrato
            float costoTotal = obj.costoTotal(obj.getItemsAlquiler());
            ps.setFloat(3, costoTotal);

            ps.setString(4, obj.getCliente().getCorreo());

            int fila = ps.executeUpdate();

            if (fila > 0) {
                return 1;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return 2;
    }

    //Metodo para obtener toda la informacion de un producto a partir de su nombre
    private Producto getProducNombre(String nombre) {
        String query = "SELECT id, nom_pro, categoria, precio_uni, cantDisponible, cantPrestada, cantMantenimiento "
                + "FROM Producto "
                + "WHERE nom_pro = ?";

        try (PreparedStatement ps = conectar.conectar().prepareStatement(query)) {
            ps.setString(1, nombre);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                int id = rs.getInt("id");
                String nom_pro = rs.getString("nom_pro");
                String categoria = rs.getString("categoria");
                float precio_uni = rs.getFloat("precio_uni");
                int disponible = rs.getInt("cantDisponible");
                int prestada = rs.getInt("cantPrestada");
                int mantenimiento = rs.getInt("cantMantenimiento");

                // Crear y devolver un nuevo objeto Producto con los datos recuperados
                return new Producto(id, nom_pro, new Categoria_Mobiliario(categoria), precio_uni, disponible,
                        prestada, mantenimiento);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null; // Si no se encontró ningún producto con ese nombre
    }

    //Metodo que actualizara el inventario cada vez que agreguemos un producto a un alquiler
    //El parametro agregar si es true pues la consulta lo interpretera que se desea agregar 
    //un producto a un alquiler y por lo tanto se resta la cantidad disponible de un objeto en el inventario
    //asi como tambien se suma la cantidad prestada de dicho objeto, y en caso sea false
    //detectara que quiere quitar objetos de ese inventario y por lo tanto hara lo contrario
    public int actualizarInventario(String nombrePro, int cantAlquilada, boolean agregar) {

        String query = "UPDATE inventario AS i \"\n"
                + "\"INNER JOIN Alquiler_Inventario AS ai ON i.nombre = ai.nombre \"\n"
                + "\"SET i.cant_disponible = CASE WHEN ? THEN i.cant_disponible - ? ELSE i.cant_disponible + ? END, \"\n"
                + "\"    i.cant_prestada = CASE WHEN ? THEN i.cant_prestada + ? ELSE i.cant_prestada - ? END \"\n"
                + "\"WHERE ai.nombre = ?";

        try (PreparedStatement ps = conectar.conectar().prepareStatement(query)) {

            ps.setBoolean(1, agregar);
            ps.setInt(2, cantAlquilada);
            ps.setInt(3, cantAlquilada);
            ps.setBoolean(4, agregar);
            ps.setInt(5, cantAlquilada);
            ps.setInt(6, cantAlquilada);
            ps.setString(7, nombrePro);

            return ps.executeUpdate(); //Retornara el numero de filas modificadas

        } catch (Exception e) {
        }

        return 0;
    }

}
