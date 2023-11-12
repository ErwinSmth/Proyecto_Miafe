/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;

import Controlador.Inventariado.ControlCategoria;
import Modelo.Roles_Usuarios;
import Modelo.Usuario;
import Vista.Gestion_Categorias;
import Vista.Gestion_Usuarios;
import Vista.Gestion_UsuariosINACT;
import Vista.Login;
import Vista.Principal;
import Vista.RegistroClientes;
import Vista.Gestion_Clientes;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ControladorPrincipal implements ActionListener {

    private Principal pri;
    private Gestion_Usuarios gesUs;
    private Gestion_UsuariosINACT gesUSINAC;
    private Usuario usActivo;
    private Login log;
    private RegistroClientes regisCLI;
    private Gestion_Clientes gestCLI;
    private Gestion_Categorias gesCat;

    public ControladorPrincipal(Principal pri, Gestion_Usuarios gesUs, Gestion_UsuariosINACT gesUSINAC, Login log, RegistroClientes regisCLI,
            Gestion_Clientes gestCLI, Gestion_Categorias gesCat) {

        this.pri = pri;
        this.gesUs = gesUs;
        this.log = log;
        this.gesUSINAC = gesUSINAC;
        this.regisCLI = regisCLI;
        this.gestCLI = gestCLI;
        this.gesCat = gesCat;

        this.pri.mni_Clientes.addActionListener(this);
        this.pri.mni_regCLIENTE.addActionListener(this);
        this.pri.mni_Us.addActionListener(this);
        this.pri.mni_USINAC.addActionListener(this);
        this.pri.btn_cerrar.addActionListener(this);
        this.pri.mni_categoria.addActionListener(this);
    }

    public ControladorPrincipal(Principal pri, Usuario usActivo) {
        this.pri = pri;
        this.usActivo = usActivo;
        opciones();
    }

    public ControladorPrincipal() {
    }

    //Retornada true si el usuario que esta logeado en ese momento es administrador
    //Retornada falso si es el de caja
    public boolean esAdmin() {

        if (usActivo.getRol() == Roles_Usuarios.Administrador) {
            return true;
        }

        return false;
    }

    public void opciones() {

        //Si el usuario logeado si es Admin
        if (esAdmin()) {
            pri.mnu_Clientes.setVisible(false);
        } else {
            pri.mnu_Us.setVisible(false);
        }

    }

    public Usuario getUsActivo() {
        return usActivo;
    }
    

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == pri.mni_Us) {
            pri.Escritorio.add(gesUs);
            gesUs.setVisible(true);
            //Registro Clientes
        } else if (e.getSource() == pri.mni_regCLIENTE) {
            pri.Escritorio.add(regisCLI);
            regisCLI.setVisible(true);
            //Gestion  Clientes    
        } else if (e.getSource() == pri.mni_Clientes) {
            pri.Escritorio.add(gestCLI);
            gestCLI.setVisible(true);
        } else if (e.getSource() == pri.mni_USINAC) {
            pri.Escritorio.add(gesUSINAC);
            gesUSINAC.setVisible(true);
        } else if (e.getSource() == pri.btn_cerrar) {
            pri.dispose();
            log.setVisible(true);
            log.Limpiar();
        } else if (e.getSource() == pri.mni_categoria) {
            pri.Escritorio.add(gesCat);
            gesCat.setVisible(true);            
        }

    }

    public void mostrarPrincipal() {

        pri.lblmensaje.setText("Bienvenido Usuario: " + usActivo.getPri_nombre() + " " + usActivo.getApe_paterno() + " con el Rol: " + usActivo.getRol());
        pri.setVisible(true);

    }
}
