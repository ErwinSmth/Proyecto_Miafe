/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;

import Modelo.Usuario;
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

    public ControladorPrincipal(Principal pri, Gestion_Usuarios gesUs, Gestion_UsuariosINACT gesUSINAC, Login log, RegistroClientes regisCLI, Gestion_Clientes gestCLI) {
        this.pri = pri;
        this.gesUs = gesUs;
        this.log = log;        
        this.gesUSINAC = gesUSINAC;
        this.regisCLI = regisCLI;
        this.gestCLI = gestCLI;
        
        this.pri.mni_Clientes.addActionListener(this);
        this.pri.mni_regCLIENTE.addActionListener(this);
        this.pri.mni_Us.addActionListener(this);
        this.pri.mni_USINAC.addActionListener(this);
        this.pri.btn_cerrar.addActionListener(this);
    }

    public ControladorPrincipal(Principal pri, Usuario usActivo) {
        this.pri = pri;
        this.usActivo = usActivo;
    }

    public ControladorPrincipal() {
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
        }else if (e.getSource() == pri.mni_Clientes) {
            pri.Escritorio.add(gestCLI);
            gestCLI.setVisible(true);
        }else if (e.getSource() == pri.mni_USINAC) {
            pri.Escritorio.add(gesUSINAC);
            gesUSINAC.setVisible(true);
        }  else if (e.getSource() == pri.btn_cerrar) {
            pri.dispose();
            log.setVisible(true);
            log.Limpiar();
        }

    }

    public void mostrarPrincipal() {

        pri.lblmensaje.setText("Bienvenido Usuario: " + usActivo.getPri_nombre() + " " + usActivo.getApe_paterno() + " con el Rol: " + usActivo.getRol());
        pri.setVisible(true);

    }
}
