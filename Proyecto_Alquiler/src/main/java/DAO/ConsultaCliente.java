package DAO;

import Modelo.Tipo_Documentos;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import Modelo.Cliente;
import Modelo.Estado_Persona;

public class ConsultaCliente implements EntidadDAO<Cliente> {

    ConexionBD conectar = ConexionBD.getConexion();

    //metodo para verificiar si un num de documento ya existe en la bd
    public boolean nDocRepe(String numDoc) {

        PreparedStatement ps = null;
        ResultSet rs = null;
        String consulta = "SELECT COUNT(*) FROM Persona WHERE num_doc = ?";
        //se selecciona a todas las persona que su num_doc sea igual pero en id_persona se excluye al usuario
        //que estoy editanto o registrando en ese momento

        try {
            ps = conectar.conectar().prepareStatement(consulta);
            ps.setString(1, numDoc);
            rs = ps.executeQuery();

            if (rs.next()) {
                int contar = rs.getInt(1);
                return contar > 0; //devuele true si el numDoc repetidos es mayor de 0
            }

            conectar.Desconectar();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    //metodo para verificiar si un num de documento ya existe en la bd para usarse en la edicion de un Usuario
    public boolean Repetido(String id_persona, String numDoc) {

        PreparedStatement ps = null;
        ResultSet rs = null;
        String consulta = "SELECT COUNT(*) FROM Persona WHERE num_doc = ? AND id_persona != ?";
        //se selecciona a todas las persona que su num_doc sea igual pero en id_persona se excluye al usuario
        //que estoy editanto o registrando en ese momento

        try {
            ps = conectar.conectar().prepareStatement(consulta);
            ps.setString(1, numDoc);
            ps.setString(2, id_persona);
            rs = ps.executeQuery();

            if (rs.next()) {
                int contar = rs.getInt(1);
                return contar > 0; //devuele true si el numDoc repetidos es mayor de 0
            }

            conectar.Desconectar();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    //-------------------------------------------------------------------------------------------------------------------------------------------------------
    //------------------------Registrar un Cliente-----------------------------------------------------------------------------------------------------
    //------------------------Registrar un Cliente-----------------------------------------------------------------------------------------------------
    @Override
    public boolean Registrar(Cliente entidad) {
        ConsultaDATOSFIJOS coF = new ConsultaDATOSFIJOS();

        PreparedStatement ps = null;
        String consultaPersona = "INSERT INTO Persona(pri_nombre, seg_nombre, ape_paterno, ape_materno, id_tipo_doc, num_doc, estado) VALUES (?,?,?,?,?,?,?)";
        String consultaCliente = "INSERT INTO Cliente (id_persona, correo, direccion, telefono) VALUES (?,?,?,?)";

        try {

            //Si no existe, procede con el registro de la persona
            ps = conectar.conectar().prepareStatement(consultaPersona);
            ps.setString(1, entidad.getPri_nombre());
            ps.setString(2, entidad.getSeg_nombre());
            ps.setString(3, entidad.getApe_paterno());
            ps.setString(4, entidad.getApe_materno());
            //------------Tipo de Documento-----------------------------------------------------------------
            int tipoDoc = 1;//Por defecto es el DNI
            switch (entidad.getTipo_doc()) {
                case Pasaporte:
                    tipoDoc = 2;
                    break;
                case Ced_Identidad:
                    tipoDoc = 3;
                    break;
                case Carne_Extrangeria:
                    tipoDoc = 4;
                    break;
                default:
                    break;
            }
            ps.setInt(5, tipoDoc);
            //-----------------------------------------------------------------------------------------------

            ps.setString(6, entidad.getNum_doc());
            ps.setInt(7, 1);//Por defecto el estado es Activo

            int resPersona = ps.executeUpdate();

            if (resPersona > 0) {
                int ultimoID = coF.obtenerUltimoID();

                ps = conectar.conectar().prepareStatement(consultaCliente);
                ps.setInt(1, ultimoID);
                ps.setString(2, entidad.getCorreo());
                ps.setString(3, entidad.getDireccion());
                ps.setString(4, entidad.getTelefono());

                //--------------------------------------------------------------------
                int resEmpleado = ps.executeUpdate();

                if (resEmpleado > 0) {
                    return true;
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    //---------------------------------------------------------------------------------------------------------------------------------------------------------
    //-------Editar un Cliente---------------------------------------------------------------------------------------------------------------------------------
    @Override
    public boolean Editar(Cliente entidad) {
        PreparedStatement ps = null;

        String consultaPersona = "UPDATE Persona SET pri_nombre = ?, seg_nombre = ?, ape_paterno = ?, ape_materno = ?, id_tipo_doc = ?,num_doc = ? WHERE id_persona = ?";

        try {

            ps = conectar.conectar().prepareStatement(consultaPersona);
            ps.setString(1, entidad.getPri_nombre());
            ps.setString(2, entidad.getSeg_nombre());
            ps.setString(3, entidad.getApe_paterno());
            ps.setString(4, entidad.getApe_materno());

            //------------Tipo de Documento------------------------------------------------------------------------------
            int tipoDoc = 0;//Por defecto es el DNI
            switch (entidad.getTipo_doc()) {
                case DNI:
                    tipoDoc = 1;
                    break;
                case Pasaporte:
                    tipoDoc = 2;
                    break;
                case Ced_Identidad:
                    tipoDoc = 3;
                    break;
                case Carne_Extrangeria:
                    tipoDoc = 4;
                    break;
                default:
                    break;
            }
            ps.setInt(5, tipoDoc);
            //-------------------------------------------------------------------------------------------------------------

            ps.setString(6, entidad.getNum_doc());
            ps.setInt(7, Integer.parseInt(entidad.getId_persona()));

            int resPersona = ps.executeUpdate();

            String consultaCliente = "UPDATE Cliente SET correo = ?, direccion = ?, telefono = ? WHERE id_persona = ?";
            ps = conectar.conectar().prepareStatement(consultaCliente);
            ps.setString(1, entidad.getCorreo());
            ps.setString(2, entidad.getDireccion());
            ps.setString(3, entidad.getTelefono());
            ps.setInt(4, Integer.parseInt(entidad.getId_persona()));
            //---------------------------------------------------------------------------------------------------------

            int resCliente = ps.executeUpdate();

            if (resCliente > 0 && resPersona > 0) {
                return true;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    //-----------------------------------------------------------------------------------------------------------------------------------------------------------
    //Metodo para buscar su id_persona de un Usuario a partir de su numero de documento que nos servira para editar un usuario------------------------------------
    @Override
    public String getId(String numDoc) {
        String idPersona = null;
        String consulta = "SELECT id_persona FROM Persona WHERE num_doc = ?";
        PreparedStatement ps = null;

        try {

            ps = conectar.conectar().prepareStatement(consulta);
            ps.setString(1, numDoc);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                idPersona = rs.getString("id_persona");
            }

        } catch (SQLException e) {

            e.printStackTrace();
        }

        return idPersona;
    }

    //---------------------------------------------------------------------------------------------------------------------------------------------------------------
    //-------Metodo para Obtener todos los Clientes Activos--------------------------------------------------------------------------------------------------------------
    @Override
    public List<Cliente> getPersonaAc() {
        List<Cliente> clientes = new ArrayList<>();
        PreparedStatement ps = null;
        ResultSet rs = null;

        String consulta = "SELECT \n"
                + "    p.id_persona,\n"
                + "    p.pri_nombre,\n"
                + "    p.seg_nombre,\n"
                + "    p.ape_paterno,\n"
                + "    p.ape_materno,\n"
                + "    td.nombre_tipo AS tipo_documento,\n"
                + "    p.num_doc,\n"
                + "    c.correo,\n"
                + "    c.direccion,\n"
                + "    c.telefono,\n"
                + "    p.estado\n"
                + "FROM\n"
                + "    Cliente c\n"
                + "INNER JOIN Persona p ON c.id_persona = p.id_persona\n"
                + "INNER JOIN Tipo_Documento td ON p.id_tipo_doc = td.id_tipo_doc\n"
                + "WHERE p.estado = 'Activo'";

        try {

            ps = conectar.conectar().prepareStatement(consulta);
            rs = ps.executeQuery();

            while (rs.next()) {
                Cliente cli = new Cliente();
                cli.setId_persona(rs.getString("id_persona"));
                cli.setPri_nombre(rs.getString("pri_nombre"));
                cli.setSeg_nombre(rs.getString("seg_nombre"));
                cli.setApe_paterno(rs.getString("ape_paterno"));
                cli.setApe_materno(rs.getString("ape_materno"));

                //--------------------------Tipo de Documento---------------------------------------------------
                String tiDocNom = rs.getString("tipo_documento");
                cli.setTipo_doc(Tipo_Documentos.valueOf(tiDocNom));
                //---------------------------------------------------------------------------------------------
                cli.setNum_doc(rs.getString("num_doc"));
                String estado = rs.getString("estado");
                cli.setEstado(Estado_Persona.valueOf(estado));
                //--------------------------------------------------------------------
                cli.setCorreo(rs.getString("correo"));
                cli.setDireccion(rs.getString("direccion"));
                cli.setTelefono(rs.getString("telefono"));

                clientes.add(cli);

            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return clientes;
    }

    //-------------------------------------------------------------------------------------------------------------------------------------------------------------
    //----------Metodo para Obtener todos los Clientes Inactivos-----------------------------------------------------------------------------------------------
    @Override
    public List<Cliente> getPersonaInac() {
        List<Cliente> cliInactivos = new ArrayList<>();
        PreparedStatement ps = null;
        ResultSet rs = null;

        String consulta = "SELECT \n"
                + "    p.id_persona,\n"
                + "    p.pri_nombre,\n"
                + "    p.seg_nombre,\n"
                + "    p.ape_paterno,\n"
                + "    p.ape_materno,\n"
                + "    td.nombre_tipo AS tipo_documento,\n"
                + "    p.num_doc,\n"
                + "    c.correo,\n"
                + "    c.direccion,\n"
                + "    c.telefono,\n"
                + "    p.estado\n"
                + "FROM\n"
                + "    Cliente c\n"
                + "INNER JOIN Persona p ON c.id_persona = p.id_persona\n"
                + "INNER JOIN Tipo_Documento td ON p.id_tipo_doc = td.id_tipo_doc\n"
                + "WHERE p.estado = 'Inactivo'";

        try {

            ps = conectar.conectar().prepareStatement(consulta);
            rs = ps.executeQuery();

            while (rs.next()) {

                Cliente cli = new Cliente();
                cli.setId_persona(rs.getString("id_persona"));
                cli.setPri_nombre(rs.getString("pri_nombre"));
                cli.setSeg_nombre(rs.getString("seg_nombre"));
                cli.setApe_paterno(rs.getString("ape_paterno"));
                cli.setApe_materno(rs.getString("ape_materno"));

                //--------------------------Tipo de Documento---------------------------------------------------
                String tiDocNom = rs.getString("tipo_documento");
                cli.setTipo_doc(Tipo_Documentos.valueOf(tiDocNom));
                //---------------------------------------------------------------------------------------------
                cli.setNum_doc(rs.getString("num_doc"));
                String estado = rs.getString("estado");
                cli.setEstado(Estado_Persona.valueOf(estado));
                cli.setCorreo(rs.getString("correo"));
                cli.setDireccion(rs.getString("direccion"));
                cli.setTelefono(rs.getString("telefono"));

                //--------------------------------------------------------------------
                cliInactivos.add(cli);

            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return cliInactivos;
    }

    //----------------------------------------------------------------------------------------------------------------------------------------------------------
    //borrar a un usuario (cambiar su estado a inactivo)-----------------------------------------------------------------------------------------------------
    @Override
    public boolean eliminar(Cliente entidad) {
        String consultaCliente = "UPDATE Persona SET estado = ? WHERE id_persona = ?";
        PreparedStatement ps = null;

        try {

            //por defecto establecemos que su estado del usuario al funcionar este metodo sea 2(inactivo)
            int Inactivo = 2;

            ps = conectar.conectar().prepareStatement(consultaCliente);
            ps.setInt(1, Inactivo);
            ps.setInt(2, Integer.parseInt(entidad.getId_persona()));

            int resEmpleado = ps.executeUpdate();

            if (resEmpleado > 0) {
                return true;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    @Override
    public boolean Reactivar(Cliente entidad) {
        String consultaCliente = "UPDATE Persona SET estado = ? WHERE id_persona = ?";
        PreparedStatement ps = null;

        try {

            int activo = 1;

            ps = conectar.conectar().prepareStatement(consultaCliente);
            ps.setInt(1, activo);
            ps.setInt(2, Integer.parseInt(entidad.getId_persona()));

            int resEmpleado = ps.executeUpdate();

            if (resEmpleado > 0) {
                return true;
            }

        } catch (Exception e) {

            e.printStackTrace();
        }

        return false;
    }

    public Cliente getDatosCli(String correo) {

        Cliente cli = null;
        ResultSet rs = null;

        String query = "SELECT p.id_persona, p.pri_nombre, p.seg_nombre, p.ape_paterno, p.ape_materno, td.nombre_tipo, p.num_doc, p.estado, "
                + "c.id_cliente, c.direccion, c.telefono, c.correo "
                + "FROM Persona p "
                + "INNER JOIN Cliente c ON p.id_persona = c.id_persona "
                + "INNER JOIN Tipo_Documento td ON p.id_tipo_doc = td.id_tipo_doc "
                + "WHERE c.correo = ?";

        try ( PreparedStatement ps = conectar.conectar().prepareStatement(query)) {

            ps.setString(1, correo);
            rs = ps.executeQuery();

            if (rs.next()) {

                cli = new Cliente();
                cli.setPri_nombre(rs.getString("pri_nombre"));
                cli.setSeg_nombre(rs.getString("seg_nombre"));
                cli.setApe_paterno(rs.getString("ape_paterno"));
                cli.setApe_materno(rs.getString("ape_materno"));
                cli.setTelefono(rs.getString("telefono"));
                cli.setNum_doc(rs.getString("num_doc"));
                cli.setTipo_doc(Tipo_Documentos.valueOf(rs.getString("nombre_tipo")));
                cli.setDireccion(rs.getString("direccion"));
                cli.setCorreo(correo);

            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return cli;
    }

    //------------------------------------------------------------------------------------------------------------------------------------------------------------
    //metodo para verificiar si un num de documento ya existe en la bd-----------------------------------------------------------------------------------------
    //--------------------------------------------------------------------------------------------------------------------------------------------------------------------
    public static void main(String[] args) {

        ConsultaCliente conCli = new ConsultaCliente();

        Cliente cliente = new Cliente("email@email.com", "enrique palacios", "12345678", null, "ivan", "josue",
                "jacinto", "perez", Tipo_Documentos.DNI, "87654321", Estado_Persona.Activo);

        try {

            conCli.Registrar(cliente);
            System.out.println("Exito");

        } catch (Exception e) {
            System.out.println("Error");
            e.printStackTrace();
        }

    }

}
