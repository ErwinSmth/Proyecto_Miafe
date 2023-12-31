/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;

import Controlador.Servicio.Control_RegistroAlquiler;
import DAO.ConsultaCliente;

import Modelo.Tipo_Documentos;
import Modelo.Cliente;
import Modelo.Estado_Persona;
import Vista.Principal;
import Vista.RegistrarAlquiler;
import Vista.RegistroClientes;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;

/**
 *
 * @author yeric
 */
public class ControladorRegistroCLI implements ActionListener {

    private RegistroClientes reCLI;
    private ConsultaCliente conCLI;
    private RegistrarAlquiler regAlqui;
    private Cliente cliente;
    private Principal pri;

    public ControladorRegistroCLI(RegistroClientes reCLI, ConsultaCliente conCLI, RegistrarAlquiler regAlqui, Principal pri) {
        this.reCLI = reCLI;
        this.conCLI = conCLI;
        this.regAlqui = regAlqui;
        this.pri = pri;
        this.reCLI.btn_registrarCLI.addActionListener(this);
        this.reCLI.btn_limpiar.addActionListener(this);

        reCLI.txt_bandera.setVisible(false);

    }

    public ControladorRegistroCLI(RegistroClientes reCLI, Cliente cliente) {
        this.reCLI = reCLI;
        this.cliente = cliente;
        reCLI.txt_bandera.setText("1");
        
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == reCLI.btn_registrarCLI) {

            if (camposVacios()) {
                JOptionPane.showMessageDialog(null, "Por favor, complete todos los campos son obligatorios.");
                return;
            } else if (!tipoDoc()) {
                JOptionPane.showMessageDialog(null, "Numero de documento invalido");
                return;

            } else if (!numTele()) {
                JOptionPane.showMessageDialog(null, "Numero de Telefono no Valido");
                return;
            } else if (!correo()) {
                JOptionPane.showMessageDialog(null, "Correo no Valido");
                return;
            } else {

                Cliente cli;
                String priNombre = reCLI.txt_priN_cli.getText();
                String segNombre = reCLI.txt_segN_cli.getText();
                String apePa = reCLI.txt_apeP_cli.getText();
                String apeMa = reCLI.txt_apeM_cli.getText();
                String tipoDoc = reCLI.cbo_tiDoc.getSelectedItem().toString();
                String numDoc = reCLI.txt_numD_cli.getText();
                String correo = reCLI.txt_correo_cli.getText();
                String direc = reCLI.txt_direc_cli.getText();
                String telef = reCLI.txt_telef_cli.getText();

                cli = new Cliente(correo, direc, telef, priNombre, segNombre, apePa, apeMa,
                        Tipo_Documentos.valueOf(tipoDoc), numDoc, Estado_Persona.Activo);

                if (reCLI.txt_bandera.getText().equals("0")) {

                    if (conCLI.nDocRepe(numDoc)) {
                        JOptionPane.showMessageDialog(null, "Este numero de Documento ya esta registrado");
                    } else if (conCLI.Registrar(cli)) {

                        Cliente newCliente = conCLI.getDatosCli(correo);

                        if (newCliente != null) {

                            newCliente.setCorreo(correo);
                            JOptionPane.showMessageDialog(null, "Cliente Registrado correctamente");
                            reCLI.dispose();

                            Control_RegistroAlquiler conRegAlqui = new Control_RegistroAlquiler(regAlqui, newCliente);
                            conRegAlqui.mostrarVista();
                        }

                    } else {
                        JOptionPane.showMessageDialog(null, "Error en el registro del usuario");
                        limpiar();
                    }

                    //Parte del codigo cuando se vuelva de una interfaz anterior a la de registro de clientes
                } else {                   
                    
                    //Obtenermos el ID de la persona
                    String id = conCLI.getId(numDoc);
                    cli.setId_persona(id);
                    if (id != null) {

                        if (conCLI.Repetido(id, numDoc)) {

                            JOptionPane.showMessageDialog(null, "Este Documento ya existe en la Base de Datos");

                        } else if (conCLI.Editar(cli)) {

                            Cliente newCliente = conCLI.getDatosCli(correo);

                            if (newCliente != null) {
                                reCLI.dispose();

                                newCliente.setCorreo(correo);
                                Control_RegistroAlquiler conRegAlqui = new Control_RegistroAlquiler(regAlqui, newCliente);
                                pri.Escritorio.add(regAlqui);
                                conRegAlqui.mostrarVista();

                            }

                        }

                    }

                }

            }

        } else if (e.getSource() == reCLI.btn_limpiar) {
            limpiar();
            reCLI.btn_registrarCLI.setEnabled(true);

        }

    }

    public boolean camposVacios() {
        if (reCLI.txt_priN_cli.getText().isEmpty() || reCLI.txt_apeP_cli.getText().isEmpty()
                || reCLI.txt_apeM_cli.getText().isEmpty() || reCLI.txt_numD_cli.getText().isEmpty() || reCLI.txt_correo_cli.getText().isEmpty()
                || reCLI.txt_telef_cli.getText().isEmpty() || reCLI.txt_direc_cli.getText().isEmpty()) {
            return true; // Devuelve true si hay campos vacíos.
        } else {
            return false; // Devuelve false si no hay campos vacíos.
        }
    }

    public boolean tipoDoc() {

        String tiDoc = reCLI.cbo_tiDoc.getSelectedItem().toString();
        String numDoc = reCLI.txt_numD_cli.getText();

        switch (tiDoc) {
            case "DNI":
                // Para DNI, se requieren 8 dígitos numéricos.
                if (numDoc.matches("^\\d{8}$")) {
                    return true;
                } else {
                    return false;
                }
            // Para Pasaporte, se requieren 12 caracteres alfanuméricos
            case "Pasaporte":
                if (numDoc.matches("^[a-zA-Z0-9]{12}$")) {
                    return true;
                } else {
                    return false;
                }
            // Para Ced_Identidad, se requieren 15 caracteres alfanuméricos.
            case "Ced_Identidad":
                if (numDoc.matches("^[a-zA-Z0-9]{15}$")) {
                    return true;
                } else {
                    return false;
                }
            // Para Carne_Extrangeria, se requieren 12 caracteres alfanuméricos.
            case "Carne_Extrangeria":
                if (numDoc.matches("^[a-zA-Z0-9]{12}$")) {
                    return true;
                } else {
                    return false;
                }

            default:
                return false;
        }

    }

    public boolean numTele() {

        String num = reCLI.txt_telef_cli.getText();

        if (num.matches("^\\d{9}$")) {
            return true;
        }

        return false;

    }

    public boolean correo() {

        String correo = reCLI.txt_correo_cli.getText();

        if (correo.matches("^[A-Za-z0-9+_.-]+@(.+)$")) {
            return true;
        }

        return false;
    }

    public void limpiar() {

        reCLI.txt_priN_cli.setText("");
        reCLI.txt_segN_cli.setText("");
        reCLI.txt_apeM_cli.setText("");
        reCLI.txt_apeP_cli.setText("");
        reCLI.cbo_tiDoc.setSelectedItem(1);
        reCLI.txt_numD_cli.setText("");
        reCLI.txt_correo_cli.setText("");
        reCLI.txt_telef_cli.setText("");
        reCLI.txt_direc_cli.setText("");

    }

    public void ultimoClie(Cliente c) {

        reCLI.txt_priN_cli.setText(c.getPri_nombre());
        reCLI.txt_segN_cli.setText(c.getSeg_nombre());
        reCLI.txt_apeM_cli.setText(c.getApe_materno());
        reCLI.txt_apeP_cli.setText(c.getApe_paterno());
        reCLI.cbo_tiDoc.setSelectedItem(c.getTipo_doc());
        reCLI.txt_numD_cli.setText(c.getNum_doc());
        reCLI.txt_correo_cli.setText(c.getCorreo());
        reCLI.txt_telef_cli.setText(c.getTelefono());
        reCLI.txt_direc_cli.setText(c.getDireccion());

        reCLI.txt_bandera.setText("1");
        Editable();

    }

    private void Editable() {

        if (reCLI.txt_correo_cli.getText() != null && !reCLI.txt_bandera.getText().equals("0")) {

            reCLI.txt_correo_cli.setEditable(false);
            reCLI.txt_numD_cli.setEditable(false);
            reCLI.cbo_tiDoc.setEditable(false);

        }

    }
}
