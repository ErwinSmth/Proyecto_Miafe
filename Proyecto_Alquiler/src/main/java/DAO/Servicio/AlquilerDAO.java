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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

        String query = "SELECT nombre, cant_disponible, precio FROM inventario";
        List<Producto> productos = new ArrayList<>();

        try ( PreparedStatement ps = conectar.conectar().prepareStatement(query);  ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Producto producto = new Producto();
                producto.setNom_pro(rs.getString("nombre"));
                producto.setCantDisponible(rs.getInt("cant_disponible"));
                producto.setPrecio_uni(rs.getFloat("precio"));
                productos.add(producto);
            }
        } catch (SQLException e) {
            // Manejo de la excepción aquí
            e.printStackTrace();
        }
        return productos;

    }

    //Metodo para filtrar por nombres en la tabla donde se mostraran todos los productos
    public List<Producto> filtrarProducto(String nombre) {

        List<Producto> productos = new ArrayList<>();
        String query = "SELECT * FROM Inventario WHERE nombre LIKE ?";

        try ( PreparedStatement ps = conectar.conectar().prepareStatement(query)) {

            ps.setString(1, "%" + nombre + "%");
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Producto producto = new Producto();
                producto.setNom_pro(rs.getString("nombre"));
                producto.setCantDisponible(rs.getInt("cant_disponible"));
                producto.setPrecio_uni(rs.getFloat("precio"));
                productos.add(producto);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return productos;
    }

    //Metodo para buscar un ID de un Cliente a partir de su correo
    //Retornara 0 en caso no encuentre el id
    public int getIDCliente(String correo) {
        int idCliente = 0;
        String query = "SELECT id_cliente FROM Cliente WHERE correo = ?";

        try ( PreparedStatement ps = conectar.conectar().prepareStatement(query)) {
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

    public int getIDAlquiler() {

        int idAquiler = 0;
        String query = "Select MAX(id_alquiler) AS ultimo_id FROM Alquiler";

        try ( PreparedStatement ps = conectar.conectar().prepareStatement(query)) {

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                idAquiler = rs.getInt("ultimo_id");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return idAquiler;
    }

//Metodo que se usara para registrar un Alquiler(Se hara uso de la tabla Alquiler), los objetos se añadiran
    //al alquiler en otro metodo
    public int addAlquiler(ContratoAlquiler obj) {

        String query = "Insert into Alquiler (id_cliente, fecha_contrato) values (?, ?)";
        try ( PreparedStatement ps = conectar.conectar().prepareStatement(query)) {

            ps.setString(1, obj.getCliente().getId_cliente());

            //Convertir Local a java.sql.Date
            java.sql.Date fechaSQL = java.sql.Date.valueOf(obj.getFecha_Contrato());
            ps.setDate(2, fechaSQL);

            int exito = ps.executeUpdate();

            if (exito > 0) {
                return 1;
            }

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

        try ( PreparedStatement ps = conectar.conectar().prepareStatement(query)) {

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
        String query = "DELETE FROM Alquiler_Inventario WHERE id_alquiler = ?";
        String seleccionados = "SELECT nombre, cantidad_alquilada FROM Alquiler_Inventario WHERE id_alquiler = ?";
        String borrarAlquiler = "DELETE FROM Alquiler WHERE id_alquiler = ?";

        try ( PreparedStatement ps = conectar.conectar().prepareStatement(query);  PreparedStatement psSelect = conectar.conectar().prepareStatement(seleccionados);  PreparedStatement psBorrarAlquiler = conectar.conectar().prepareStatement(borrarAlquiler)) {

            psSelect.setString(1, obj.getId_alquiler());
            ResultSet rs = psSelect.executeQuery();

            Map<String, Integer> productosDevueltos = new HashMap<>();

            while (rs.next()) {
                String nombrePro = rs.getString("nombre");
                int cantidad = rs.getInt("cantidad_alquilada");
                productosDevueltos.put(nombrePro, cantidad);
            }

            ps.setString(1, obj.getId_alquiler());
            ps.executeUpdate();

            for (Map.Entry<String, Integer> entrada : productosDevueltos.entrySet()) {
                String nombrePro = entrada.getKey();
                int cantidad = entrada.getValue();

                if (actualizarInventarioELI(nombrePro, cantidad) <= 0) {
                    System.out.println("Ocurrio un Error en la actualizacion del Inventario");
                }
            }

            psBorrarAlquiler.setString(1, obj.getId_alquiler());
            psBorrarAlquiler.executeUpdate();

            return 1;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 2;
    }

    //Este metodo se usara para agregar Items o productos a un alquiler
    //para esto se hara uso de la tabla alquiler_inventario, se añadiran objetos a un alquiler
    //cada vez que el empleado o administrador haga click en la tabla
    public int addItem(Item i, int idAlquiler, float costo) {

        String query = "insert into Alquiler_Inventario(id_alquiler, nombre, cantidad_alquilada, precio_alquiler)\n"
                + "values(?, ?, ?, ?)";

        try ( PreparedStatement ps = conectar.conectar().prepareStatement(query)) {

            ps.setInt(1, idAlquiler);
            ps.setString(2, i.getProducto().getNom_pro());
            ps.setInt(3, i.getCantidad());

            //Calculamos el precio total a partir de la cantidad seleccionada de un producto
            //y su precio unitario
            float precioTotal = i.getCantidad() * costo;
            ps.setFloat(4, precioTotal);

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
    public int updateCant(int idAlquiler, String nombrePro, int nuevaCant, float nuevoCosto) {

        String query = "Update Alquiler_Inventario SET cantidad_alquilada = ?, precio_alquiler = ? WHERE id_alquiler = ? AND nombre = ?";
        String cantAnterior = "SELECT cantidad_alquilada FROM Alquiler_Inventario WHERE id_alquiler = ? AND nombre = ?";

        try ( PreparedStatement ps = conectar.conectar().prepareStatement(query);  PreparedStatement ps2 = conectar.conectar().prepareStatement(cantAnterior)) {

            //La nueva cantidad 
            ps.setInt(1, nuevaCant);
            ps.setInt(2, idAlquiler);
            ps.setString(3, nombrePro);

            //La cantidad anterior 
            ps2.setInt(1, idAlquiler);
            ps2.setString(2, nombrePro);

            ResultSet rs = ps2.executeQuery();

            if (rs.next()) {

                int cantidadAnterior = rs.getInt("cantidad_alquilada");
                int diferencia = nuevaCant - cantidadAnterior;

                //actualizar la cantidad y calcular el nuevo precio
                ps.setInt(1, nuevaCant);
                float nuevoPrecio = nuevoCosto * nuevaCant;
                ps.setFloat(2, nuevoPrecio);
                ps.setInt(3, idAlquiler);
                ps.setString(4, nombrePro);

                int resultado = ps.executeUpdate();

                if (diferencia != 0) {
                    int resultadoActualizar = actualizarInventario(diferencia, nombrePro);
                    if (resultado > 0 && resultadoActualizar > 0) {
                        return 1;
                    } else {
                        return 0;
                    }
                }
                return resultado;
            }

            return ps.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    private int actualizarInventario(int diferencia, String nombrePro) {
        String query = "UPDATE inventario SET cant_disponible = cant_disponible - ?, cant_prestada = cant_prestada + ? WHERE nombre = ?";

        try ( PreparedStatement ps = conectar.conectar().prepareStatement(query)) {
            ps.setInt(1, diferencia);
            ps.setInt(2, diferencia);
            ps.setString(3, nombrePro);

            return ps.executeUpdate(); // Retorna el número de filas modificadas en el inventario
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    public float obtenerCosto(String nombrePro) {
        String query = "Select precio from inventario where nombre = ?";

        try ( PreparedStatement ps = conectar.conectar().prepareStatement(query)) {

            ps.setString(1, nombrePro);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return rs.getFloat("precio");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    public int getDisponibles(String nombrePro) {

        String query = "Select cant_disponible from inventario where nombre = ?";

        try ( PreparedStatement ps = conectar.conectar().prepareStatement(query)) {

            ps.setString(1, nombrePro);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return rs.getInt("cant_disponible");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    //Recupera todos los objetos o Items asociados a un alquiler 
    //Usando la tabla Alquiler_Inventario
    public List<Item> getItems(String idAlquiler) {
        List<Item> itemsAlquiler = new ArrayList<>();

        String query = "Select nombre, cantidad_alquilada, precio_alquiler FROM Alquiler_Inventario WHERE id_alquiler = ?";

        try ( PreparedStatement ps = conectar.conectar().prepareStatement(query)) {
            ps.setString(1, idAlquiler);
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

        try ( PreparedStatement ps = conectar.conectar().prepareStatement(query)) {

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
        String query = "SELECT  nombre, nombre_cat, precio, cant_disponible, cant_prestada, cant_mantenimiento "
                + "FROM Inventario "
                + "WHERE nombre = ?";

        try ( PreparedStatement ps = conectar.conectar().prepareStatement(query)) {
            ps.setString(1, nombre);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                String nom_pro = rs.getString("nombre");
                String categoria = rs.getString("nombre_cat");
                float precio_uni = rs.getFloat("precio");
                int disponible = rs.getInt("cant_disponible");
                int prestada = rs.getInt("cant_prestada");
                int mantenimiento = rs.getInt("cant_mantenimiento");

                // Crear y devolver un nuevo objeto Producto con los datos recuperados
                return new Producto(nom_pro, new Categoria_Mobiliario(categoria), precio_uni, disponible,
                        prestada, mantenimiento);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null; // Si no se encontró ningún producto con ese nombre
    }

    //Metodo para actualizar el Inventario cuando voy a agregar productos a un alquiler
    public int actualizarInventarioADD(String nombrePro, int cantAlquilada) {
        String query = "UPDATE inventario SET cant_disponible = cant_disponible - ?, cant_prestada = cant_prestada + ? WHERE nombre = ?";

        try ( PreparedStatement ps = conectar.conectar().prepareStatement(query)) {
            ps.setInt(1, cantAlquilada);
            ps.setInt(2, cantAlquilada);
            ps.setString(3, nombrePro);

            return ps.executeUpdate(); // Retorna el número de filas modificadas
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    //Metodo para actualizar el Inventario cuando voy a eliminar productos a un alquiler
    public int actualizarInventarioELI(String nombrePro, int cantAlquilada) {
        String query = "UPDATE inventario SET cant_disponible = cant_disponible + ?, cant_prestada = cant_prestada - ? WHERE nombre = ?";

        try ( PreparedStatement ps = conectar.conectar().prepareStatement(query)) {
            ps.setInt(1, cantAlquilada);
            ps.setInt(2, cantAlquilada);
            ps.setString(3, nombrePro);

            return ps.executeUpdate(); //Retornara el numero de filas modificadas
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    public int eliminarPro(String nombrePro, int idAlquiler) {
        String query = "DELETE FROM Alquiler_Inventario WHERE nombre = ? and id_alquiler = ?";

        try ( PreparedStatement ps = conectar.conectar().prepareStatement(query)) {

            ps.setString(1, nombrePro);
            ps.setInt(2, idAlquiler);

            return ps.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return 0;
    }
}
