/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Modelo.Estado_Persona;
import Modelo.Roles_Usuarios;
import Modelo.Tipo_Documentos;
import static Modelo.Tipo_Documentos.Carne_Extrangeria;
import static Modelo.Tipo_Documentos.Ced_Identidad;
import static Modelo.Tipo_Documentos.Pasaporte;
import Modelo.Usuario;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author DAVID
 */
public class ConsultaUsuario implements EntidadDAO<Usuario> {

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

    public boolean Logearse(Usuario us) {
        PreparedStatement ps = null;
        ResultSet rs = null;
        String consulta = "SELECT p.id_persona, p.num_doc, u.contraseña "
                + "FROM Persona p "
                + "INNER JOIN Usuario u ON p.id_persona = u.id_persona "
                + "WHERE p.num_doc = ? AND u.contraseña = ? AND p.estado = 'Activo'";
        try {
            ps = conectar.conectar().prepareStatement(consulta);
            ps.setString(1, us.getNum_doc());
            ps.setString(2, us.getContraseña());
            rs = ps.executeQuery();

            while (rs.next()) {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    @Override
    public boolean Registrar(Usuario entidad) {

        ConsultaDATOSFIJOS coF = new ConsultaDATOSFIJOS();
        PreparedStatement ps = null;
        String consultaPersona = "INSERT INTO Persona(pri_nombre, seg_nombre, ape_paterno, ape_materno, id_tipo_doc, num_doc, estado) VALUES (?,?,?,?,?,?,?)";
        String consultaEmpleado = "INSERT INTO Usuario (id_persona, contraseña, id_rol) VALUES (?,?,?)";

        try {

<<<<<<< HEAD
=======
            conectar.conectar().setAutoCommit(false);

>>>>>>> aa454cd4816c942fca4242646f5d94114af81b4b
            //Si no existe, procede con el registro de la persona
            ps = conectar.conectar().prepareStatement(consultaPersona);
            ps.setString(1, entidad.getPri_nombre());
            ps.setString(2, entidad.getSeg_nombre());
            ps.setString(3, entidad.getApe_paterno());
            ps.setString(4, entidad.getApe_materno());

            //------------Tipo de Documento------------------------------------------------------------------------------
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
            //-------------------------------------------------------------------------------------------------------------

            ps.setString(6, entidad.getNum_doc());
            ps.setInt(7, 1);//Por defecto el estado es Activo

            int resPersona = ps.executeUpdate();

<<<<<<< HEAD
            if (resPersona > 0) {
                int ultimoID = coF.obtenerUltimoID();
=======
            int ultimoID = coF.obtenerUltimoID();

            ps = conectar.conectar().prepareStatement(consultaEmpleado);
            ps.setInt(1, ultimoID);
            ps.setString(2, entidad.getContraseña());
>>>>>>> aa454cd4816c942fca4242646f5d94114af81b4b

            //-----------------------Rol del Usuario--------------------------------------------------------------------
            int idRol = 1;
            if (entidad.getRol() == Roles_Usuarios.Caja) {
                idRol = 2;
            }
            ps.setInt(3, idRol);
            //---------------------------------------------------------------------------------------------------------
            int resEmpleado = ps.executeUpdate();

            if (resEmpleado > 0 && resPersona > 0) {
                conectar.conectar().commit();
                return true;
            } else {
                conectar.conectar().rollback();
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;

    }

    @Override
    public boolean Editar(Usuario entidad) {

        PreparedStatement ps = null;

        String consultaPersona = "UPDATE Persona SET pri_nombre = ?, seg_nombre = ?, ape_paterno = ?, ape_materno = ?, id_tipo_doc = ?,num_doc = ? WHERE id_persona = ?";

        try {

<<<<<<< HEAD
=======
            conectar.conectar().setAutoCommit(false);

>>>>>>> aa454cd4816c942fca4242646f5d94114af81b4b
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

//            ps.setInt(5, coF.obtenerIDTipoDoc(entidad.getTipo_doc().toString()));
            ps.setString(6, entidad.getNum_doc());
            ps.setInt(7, Integer.parseInt(entidad.getId_persona()));

            int resPersona = ps.executeUpdate();

<<<<<<< HEAD
            if (resPersona > 0) {
                String consultaEmpleado = "UPDATE Usuario SET contraseña = ?, id_rol = ? WHERE id_persona = ?";
                ps = conectar.conectar().prepareStatement(consultaEmpleado);
                ps.setString(1, entidad.getContraseña());
=======
            String consultaEmpleado = "UPDATE Usuario SET contraseña = ?, id_rol = ? WHERE id_persona = ?";
            ps = conectar.conectar().prepareStatement(consultaEmpleado);
            ps.setString(1, entidad.getContraseña());

            //-----------------------Rol del Usuario--------------------------------------------------------------------
            int idRol = 0;
            if (entidad.getRol() == Roles_Usuarios.Administrador) {
                idRol = 1;
            } else if (entidad.getRol() == Roles_Usuarios.Caja) {
                idRol = 2;
            }
            ps.setInt(2, idRol);
            //---------------------------------------------------------------------------------------------------------
>>>>>>> aa454cd4816c942fca4242646f5d94114af81b4b

            ps.setInt(3, Integer.parseInt(entidad.getId_persona()));

<<<<<<< HEAD
                ps.setInt(3, Integer.parseInt(entidad.getId_persona()));

                int resEmpleado = ps.executeUpdate();

                if (resEmpleado > 0) {

                    return true;
                }
=======
            int resEmpleado = ps.executeUpdate();

            if (resEmpleado > 0 && resPersona > 0) {
                conectar.conectar().commit();
                return true;
            } else {
                conectar.conectar().rollback();
>>>>>>> aa454cd4816c942fca4242646f5d94114af81b4b
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;

    }

    //Metodo para buscar su id_persona de un Usuario a partir de su numero de documento que nos servira para editar un usuario
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

    //metodo obtener los usuarios activos---------------------------------------------------------------------------------------------------------------------------------------
    @Override
    public List<Usuario> getPersonaAc() {

        List<Usuario> usuarios = new ArrayList<>();
        PreparedStatement ps = null;
        ResultSet rs = null;

        String consulta = "SELECT \n"
                + "	p.id_persona,\n"
                + "    p.pri_nombre,\n"
                + "    p.seg_nombre,\n"
                + "    p.ape_paterno,\n"
                + "    p.ape_materno,\n"
                + "	td.nombre_tipo AS tipo_documento,\n"
                + "    p.num_doc,\n"
                + "       p.estado,\n"
                + "    u. contraseña,\n"
                + "    re.nombre_rol AS rol\n"
                + "FROM\n"
                + "	Usuario u\n"
                + "INNER JOIN Persona p ON u.id_persona = p.id_persona\n"
                + "INNER JOIN Tipo_Documento td ON p.id_tipo_doc = td.id_tipo_doc\n"
                + "INNER JOIN Rol_Empleado re ON u.id_rol = re.id_rol\n"
                + "WHERE p.estado = 'Activo'";

        try {

            ps = conectar.conectar().prepareStatement(consulta);
            rs = ps.executeQuery();

            while (rs.next()) {
                Usuario us = new Usuario();
                us.setId_persona(rs.getString("id_persona"));
                us.setPri_nombre(rs.getString("pri_nombre"));
                us.setSeg_nombre(rs.getString("seg_nombre"));
                us.setApe_paterno(rs.getString("ape_paterno"));
                us.setApe_materno(rs.getString("ape_materno"));

                //--------------------------Tipo de Documento---------------------------------------------------
                String tiDocNom = rs.getString("tipo_documento");
                us.setTipo_doc(Tipo_Documentos.valueOf(tiDocNom));
                //---------------------------------------------------------------------------------------------
                us.setNum_doc(rs.getString("num_doc"));
                String estado = rs.getString("estado");
                us.setEstado(Estado_Persona.valueOf(estado));
                us.setContraseña(rs.getString("contraseña"));

                //-------------------------Rol del Uusario-----------------------------------------------------
                String nomRol = rs.getString("rol");
                us.setRol(Roles_Usuarios.valueOf(nomRol));
                //--------------------------------------------------------------------
                usuarios.add(us);

            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return usuarios;

    }

    @Override
    public List<Usuario> getPersonaInac() {

        List<Usuario> usInactivos = new ArrayList<>();
        PreparedStatement ps = null;
        ResultSet rs = null;

        String consulta = "SELECT \n"
                + "	p.id_persona,\n"
                + "    p.pri_nombre,\n"
                + "    p.seg_nombre,\n"
                + "    p.ape_paterno,\n"
                + "    p.ape_materno,\n"
                + "	td.nombre_tipo AS tipo_documento,\n"
                + "    p.num_doc,\n"
                + "    p.estado,\n"
                + "    u. contraseña,\n"
                + "    re.nombre_rol AS rol\n"
                + "FROM\n"
                + "	Usuario u\n"
                + "INNER JOIN Persona p ON u.id_persona = p.id_persona\n"
                + "INNER JOIN Tipo_Documento td ON p.id_tipo_doc = td.id_tipo_doc\n"
                + "INNER JOIN Rol_Empleado re ON u.id_rol = re.id_rol\n"
                + "WHERE p.estado = 'Inactivo';";

        try {

            ps = conectar.conectar().prepareStatement(consulta);
            rs = ps.executeQuery();

            while (rs.next()) {

                Usuario us = new Usuario();
                us.setId_persona(rs.getString("id_persona"));
                us.setPri_nombre(rs.getString("pri_nombre"));
                us.setSeg_nombre(rs.getString("seg_nombre"));
                us.setApe_paterno(rs.getString("ape_paterno"));
                us.setApe_materno(rs.getString("ape_materno"));

                //--------------------------Tipo de Documento---------------------------------------------------
                String tiDocNom = rs.getString("tipo_documento");
                us.setTipo_doc(Tipo_Documentos.valueOf(tiDocNom));
                //---------------------------------------------------------------------------------------------
                us.setNum_doc(rs.getString("num_doc"));
                String estado = rs.getString("estado");
                us.setEstado(Estado_Persona.valueOf(estado));
                us.setContraseña(rs.getString("contraseña"));

                //-------------------------Rol del Uusario-----------------------------------------------------
                String nomRol = rs.getString("rol");
                us.setRol(Roles_Usuarios.valueOf(nomRol));
                //--------------------------------------------------------------------
                usInactivos.add(us);

            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return usInactivos;
    }

    //borrar a un usuario (cambiar su estado a inactivo)
    @Override
    public boolean eliminar(Usuario entidad) {
        String consultaEmpleado = "UPDATE Persona SET estado = ? WHERE id_persona = ?";
        PreparedStatement ps = null;

        try {

            //por defecto establecemos que su estado del usuario al funcionar este metodo sea 2(inactivo)
            int Inactivo = 2;

            ps = conectar.conectar().prepareStatement(consultaEmpleado);
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
    public boolean Reactivar(Usuario entidad) {

        String consultaEmpleado = "UPDATE Persona SET estado = ? WHERE id_persona = ?";
        PreparedStatement ps = null;

        try {

            int activo = 1;

            ps = conectar.conectar().prepareStatement(consultaEmpleado);
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

    //metodo para obtener los datos de el Usuario Logeado en el sistema------------------------------------------------------------------------------------------------
    public Usuario getDatosUs(String numDoc) {

        Usuario us = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        String consulta = "SELECT p.pri_nombre, p.ape_paterno, u.id_rol,\n"
                + "	re.nombre_rol AS nombre_rol\n"
                + "FROM \n"
                + "	Persona p\n"
                + "INNER JOIN Usuario u ON p.id_persona = u.id_persona\n"
                + "INNER JOIN rol_empleado re ON u.id_rol = re.id_rol\n"
                + "WHERE p.num_doc = ? AND p.estado = 'Activo';";
        try {

            ps = conectar.conectar().prepareStatement(consulta);
            ps.setString(1, numDoc);
            rs = ps.executeQuery();

            if (rs.next()) {
                us = new Usuario();
                us.setPri_nombre(rs.getString("pri_nombre"));
                us.setApe_paterno(rs.getString("ape_paterno"));

                //-------Rol del Usuario------------------------------------------------------------------
                String nomRol = rs.getString("nombre_rol");
                us.setRol(Roles_Usuarios.valueOf(nomRol));
            }

        } catch (SQLException e) {

            e.printStackTrace();
        }

        return us;
    }

    public static void main(String[] args) {
        ConsultaUsuario conUsu = new ConsultaUsuario();

        try {

            String numDoc = "76862697";
            String clave = "12345";
            Usuario us = new Usuario();
            us.setNum_doc(numDoc);
            us.setContraseña(clave);

            if (conUsu.Logearse(us)) {
                System.out.println("Logeado");
            } else {
                System.out.println("Datos Erroneos");
            }

        } catch (Exception e) {
            System.out.println("Error");
            e.printStackTrace();
        }

    }

}
