/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador.Servicio;

import DAO.Servicio.AlquilerDAO;
import Modelo.Cliente;
import Modelo.Servicio.ContratoAlquiler;
import Vista.RegistrarAlquiler;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import javax.swing.JOptionPane;

/**
 *
 * @author DAVID
 */
public class Control_RegistroAlquiler implements ActionListener {

    private AlquilerDAO alquiDAO;
    private RegistrarAlquiler regAlqui;
    private Cliente cliente;

    public Control_RegistroAlquiler(AlquilerDAO alquiDAO, RegistrarAlquiler regAlqui) {
        this.alquiDAO = alquiDAO;
        this.regAlqui = regAlqui;
        fechaActual();
        
        regAlqui.btn_continuar.addActionListener(this);
    }

    public Control_RegistroAlquiler(RegistrarAlquiler regAlqui, Cliente cliente) {
        this.regAlqui = regAlqui;
        this.cliente = cliente;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        
        if (e.getSource() == regAlqui.btn_continuar) {
            
            ContratoAlquiler obj = new ContratoAlquiler();
            Cliente cli = new Cliente();
            
            int idcliente;
            idcliente = alquiDAO.getIDCliente(regAlqui.lbl_correo.getText());
            
            cli.setId_cliente(String.valueOf(idcliente));
            obj.setCliente(cli);
            obj.setFecha_Contrato(LocalDate.now());
            
            System.out.println(idcliente);
            
            if (alquiDAO.addAlquiler(obj) == 1) {
                //Se agrego Exitosamente
                JOptionPane.showMessageDialog(null, "Alquiler Agregado Exitosamente");
            } else {
                //Ocurrio un Error
                JOptionPane.showMessageDialog(null, "Ocurrio un Error");
            }
            
        }
        
    }

    public void fechaActual() {

        LocalDate actual = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy"); // Define el formato deseado
        String fechaFormateada = actual.format(formatter);
        regAlqui.txt_contrato.setText(fechaFormateada);

    }

    public void mostrarVista() {

        regAlqui.lbl_informacion.setText("Cliente: " + cliente.getPri_nombre() + " " + cliente.getApe_paterno() + " " + cliente.getApe_materno());
        regAlqui.lbl_correo.setText(cliente.getCorreo());

        regAlqui.setVisible(true);
    }

}
