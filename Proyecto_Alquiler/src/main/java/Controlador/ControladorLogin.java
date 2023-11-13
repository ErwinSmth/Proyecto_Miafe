/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;

import DAO.ConsultaUsuario;
import Modelo.Usuario;
import Vista.Login;
import Vista.Principal;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;

/**
 *
 * @author DAVID
 */
public class ControladorLogin implements ActionListener {

    private Usuario us;
    private Login log;
    private ConsultaUsuario conUsu;
    private Principal pri;

    public ControladorLogin(Usuario us, Login log, ConsultaUsuario conUsu, Principal pri) {
        this.us = us;
        this.log = log;
        this.conUsu = conUsu;
        this.pri = Principal.getInstancia();
        this.log.btn_ingresar.addActionListener(this);

    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == log.btn_ingresar) {
            us.setNum_doc(log.txt_numDOC.getText());
            us.setContraseña(log.txt_contra.getText());
            if (conUsu.Logearse(us)) {

                Usuario usLogeado = conUsu.getDatosUs(us.getNum_doc());

                if (usLogeado != null) {
                    us.setPri_nombre(usLogeado.getPri_nombre());
                    us.setApe_paterno(usLogeado.getApe_paterno());
                    us.setRol(usLogeado.getRol());

                    JOptionPane.showMessageDialog(null, "Logeado Correctamente");
                    log.dispose();

                    //Inicializamos el controlador Principal
                    ControladorPrincipal conPri = new ControladorPrincipal(Principal.getInstancia() , usLogeado);
                    conPri.mostrarPrincipal();
                    Principal principal = Principal.getInstancia();
                } else {
                    JOptionPane.showMessageDialog(null, "Error al obtener los datos del Usuario");
                }

            } else {
                JOptionPane.showMessageDialog(null, "Credenciales Incorrectas");
                limpiar();
            }
        }

    }

    public void limpiar() {
        log.txt_numDOC.setText("");
        log.txt_contra.setText("");
    }

}
