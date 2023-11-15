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
import Controlador.Inventariado.ControlProducto;//este

import DAO.ConsultaUsuario;
import DAO.ConsultaCliente;
import DAO.Inventariado.ConsultaCategoria;
import DAO.Inventariado.ConsultaProducto;//este

import Modelo.Usuario;
import Vista.Gestion_Categorias;
import Vista.Productos;//este
import Vista.Gestion_Usuarios;
import Vista.Gestion_UsuariosINACT;
import Vista.Login;
import Vista.Principal;
import Vista.RegistroClientes;
import Vista.Gestion_Clientes;

public class Proyecto_Alquiler {

    public static void main(String[] args) {
        Usuario us = new Usuario();
        ConsultaUsuario conUsus = new ConsultaUsuario();
        ConsultaCliente conCL = new ConsultaCliente();
        ConsultaCategoria conCat = new ConsultaCategoria();
        ConsultaProducto conPRO=new ConsultaProducto();//este
        
        Login log = new Login();
        Principal pri = Principal.getInstancia();

        ControladorLogin contUsu = new ControladorLogin(us, log, conUsus, pri);
        
        Gestion_Usuarios gesUs = new Gestion_Usuarios();
        Gestion_UsuariosINACT gesUSINAC = new Gestion_UsuariosINACT();
        RegistroClientes regisCLI = new RegistroClientes();
        Gestion_Clientes gesCLI = new Gestion_Clientes();
        Gestion_Categorias gesCat = new Gestion_Categorias();
       Productos gesPRO=new Productos();//este

        ControladorGestionUS controGus = new ControladorGestionUS(gesUs, conUsus);
        ControladorRegistroCLI conREGCLI = new ControladorRegistroCLI(regisCLI, conCL);
        ControladorGestionCLI conGESTCLI = new ControladorGestionCLI(gesCLI, conCL);
        
        
        ControladorPrincipal conPri = new ControladorPrincipal(pri, gesUs, gesUSINAC, log, regisCLI, gesCLI, gesCat,gesPRO);
        
        ControladorGestionUSINACTIVOS conUSINAC = new ControladorGestionUSINACTIVOS(gesUSINAC, conUsus);
        ControlCategoria controCat = new ControlCategoria(gesCat, conCat);
        ControlProducto controPRO=new ControlProducto(conPRO, gesPRO, conCat);
        
        

        log.setVisible(true);
    }
}