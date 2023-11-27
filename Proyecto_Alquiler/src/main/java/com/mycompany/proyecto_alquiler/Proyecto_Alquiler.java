/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package com.mycompany.proyecto_alquiler;

import Controlador.ControladorGestionUS;
import Controlador.ControladorGestionUSINACTIVOS;
import Controlador.ControladorLogin;
import Controlador.ControladorPrincipal;
import Controlador.ControladorRegistroCLI;
import Controlador.ControladorGestionCLI;
import Controlador.Inventariado.ControlCategoria;
import Controlador.Inventariado.Control_AgregarPro;//este
import Controlador.Inventariado.Control_Producto;
import Controlador.Servicio.Control_AgregarItem;
import Controlador.Servicio.Control_RegistroAlquiler;

import DAO.ConsultaUsuario;
import DAO.ConsultaCliente;
import DAO.Inventariado.ConsultaCategoria;
import DAO.Inventariado.ConsultaProducto;//este
import DAO.Servicio.AlquilerDAO;
import Modelo.Usuario;
import Vista.AgregarItems;


import Vista.Gestion_Categorias;
import Vista.Agregar_Productos;//este
import Vista.Gestion_Usuarios;
import Vista.Gestion_UsuariosINACT;
import Vista.Login;
import Vista.Principal;
import Vista.RegistroClientes;
import Vista.Gestion_Clientes;
import Vista.Gestion_Productos;
import Vista.RegistrarAlquiler;

public class Proyecto_Alquiler {

    public static void main(String[] args) {
        
        Usuario us = new Usuario();
        
        ConsultaUsuario conUsus = new ConsultaUsuario();
        ConsultaCliente conCL = new ConsultaCliente();
        ConsultaCategoria conCat = new ConsultaCategoria();
        ConsultaProducto conPRO = new ConsultaProducto();
        AlquilerDAO alquiDAO = new AlquilerDAO();
        AgregarItems addItem = new AgregarItems();
        
        Login log = new Login();
        Principal pri = Principal.getInstancia();

        ControladorLogin contUsu = new ControladorLogin(us, log, conUsus, pri);

        Gestion_Usuarios gesUs = new Gestion_Usuarios();
        Gestion_UsuariosINACT gesUSINAC = new Gestion_UsuariosINACT();
        RegistroClientes regisCLI = new RegistroClientes();
        Gestion_Clientes gesCLI = new Gestion_Clientes();
        Gestion_Categorias gesCat = new Gestion_Categorias();
        Agregar_Productos gesPRO = new Agregar_Productos();
        Gestion_Productos Proges = new Gestion_Productos();
        RegistrarAlquiler regAlqui = new RegistrarAlquiler();

        Control_AgregarItem conItem = new Control_AgregarItem(alquiDAO, addItem);
        ControladorGestionUS controGus = new ControladorGestionUS(gesUs, conUsus);
        Control_RegistroAlquiler conRegAlqui = new Control_RegistroAlquiler(alquiDAO, regAlqui, addItem);
        ControladorRegistroCLI conREGCLI = new ControladorRegistroCLI(regisCLI, conCL, regAlqui);
        ControladorGestionCLI conGESTCLI = new ControladorGestionCLI(gesCLI, conCL);
        Control_Producto conPro = new Control_Producto(conPRO, Proges);

        ControladorPrincipal conPri = new ControladorPrincipal(pri, gesUs, gesUSINAC, log, regisCLI, gesCLI, gesCat, gesPRO, Proges, regAlqui, addItem);

        ControladorGestionUSINACTIVOS conUSINAC = new ControladorGestionUSINACTIVOS(gesUSINAC, conUsus);
        ControlCategoria controCat = new ControlCategoria(gesCat, conCat);
        Control_AgregarPro controPRO = new Control_AgregarPro(conPRO, gesPRO, conCat);

        log.setVisible(true);
    }
}
