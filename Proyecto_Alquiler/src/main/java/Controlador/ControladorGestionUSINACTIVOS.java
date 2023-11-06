/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;

import DAO.ConsultaUsuario;
import Modelo.Usuario;
import Vista.Gestion_UsuariosINACT;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author DAVID
 */
public class ControladorGestionUSINACTIVOS implements ActionListener {

    private Gestion_UsuariosINACT gesINAC;
    private ConsultaUsuario conUS;

    public ControladorGestionUSINACTIVOS(Gestion_UsuariosINACT gesINAC, ConsultaUsuario conUS) {
        this.gesINAC = gesINAC;
        this.conUS = conUS;
        mostrarDatos();
        this.gesINAC.btn_reactivar.addActionListener(this);
        this.gesINAC.btn_actualizar.addActionListener(this);

    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == gesINAC.btn_reactivar) {

            System.out.println("Funciona");
            int filaSeleccionada = gesINAC.tabla_usINAC.getSelectedRow();

            if (filaSeleccionada != -1) {

                String numDoc = (String) gesINAC.tabla_usINAC.getValueAt(filaSeleccionada, 5);
                String idUsuario = conUS.getId(numDoc);

                if (idUsuario != null && !idUsuario.isEmpty()) {
                    Usuario us = new Usuario();
                    us.setId_persona(idUsuario);

                    if (conUS.Reactivar(us)) {
                        JOptionPane.showMessageDialog(null, "Usuario Reactivado Exitosamente");
                        mostrarDatos();
                    } else {
                        JOptionPane.showMessageDialog(null, "Error al reactivar un Usuario");
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "El ID del Usuario es invalido");
                }

            }

        } else if (e.getSource() == gesINAC.btn_actualizar) {
            mostrarDatos();
        }

    }

    public void mostrarDatos() {

        DefaultTableModel tusINAC = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        tusINAC.addColumn("Primer Nombre");
        tusINAC.addColumn("Segundo Nombre");
        tusINAC.addColumn("Apellido Paterno");
        tusINAC.addColumn("Apellido Materno");
        tusINAC.addColumn("Tipo de Documento");
        tusINAC.addColumn("Numero de Documento");
        tusINAC.addColumn("Contraseña");
        tusINAC.addColumn("Rol");
        tusINAC.addColumn("Estado");

        gesINAC.tabla_usINAC.setModel(tusINAC);

        List<Usuario> usuInactivos = conUS.getPersonaInac();

        String[] datos = new String[9];

        for (Usuario us : usuInactivos) {

            datos[0] = us.getPri_nombre();
            datos[1] = us.getSeg_nombre();
            datos[2] = us.getApe_paterno();
            datos[3] = us.getApe_materno();
            datos[4] = us.getTipo_doc().toString();
            datos[5] = us.getNum_doc();
            datos[6] = us.getContraseña();
            datos[7] = us.getRol().toString();
            datos[8] = us.getEstado().toString();

            tusINAC.addRow(datos);

        }

        gesINAC.tabla_usINAC.setModel(tusINAC);

    }

}
