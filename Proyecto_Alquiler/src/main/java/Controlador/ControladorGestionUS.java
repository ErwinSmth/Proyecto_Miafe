/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;

import DAO.ConsultaUsuario;
import Modelo.Estado_Persona;
import Modelo.Roles_Usuarios;
import Modelo.Tipo_Documentos;
import Modelo.Usuario;
import Vista.Gestion_Usuarios;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author DAVID
 */
public class ControladorGestionUS implements ActionListener {

    private Gestion_Usuarios gesUS;
    private ConsultaUsuario conUs;

    public ControladorGestionUS(Gestion_Usuarios gesUS, ConsultaUsuario conUs) {
        this.gesUS = gesUS;
        this.conUs = conUs;
        mostrarDatos();
        this.gesUS.btn_registrarUS.addActionListener(this);
        this.gesUS.btn_obtener.addActionListener(this);
        this.gesUS.btn_editar.addActionListener(this);
        this.gesUS.btn_limpiar.addActionListener(this);
        this.gesUS.btn_eliminar.addActionListener(this);
        this.gesUS.btn_actualizar.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == gesUS.btn_registrarUS) {

            if (camposVacios()) {
                JOptionPane.showMessageDialog(null, "Por favor, complete todos los campos obligatorios.");

            } else if (!tipoDoc()) {
                JOptionPane.showMessageDialog(null, "Numero de documento no valido");

            } else {

                Usuario us;
                String priNombre = gesUS.txt_priN_us.getText();
                String segNombre = gesUS.txt_segN_us.getText();
                String apePa = gesUS.txt_apeP_us.getText();
                String apeMa = gesUS.txt_apeM_us.getText();
                String tipoDoc = gesUS.cbo_tiDoc.getSelectedItem().toString();
                String numDoc = gesUS.txt_numD_us.getText();
                String contra = gesUS.txt_contra.getText();
                String rol = gesUS.cbo_Rol.getSelectedItem().toString();

                us = new Usuario(contra, Roles_Usuarios.valueOf(rol), priNombre, segNombre, apePa, apeMa,
                        Tipo_Documentos.valueOf(tipoDoc), numDoc, Estado_Persona.Activo);

                if (conUs.nDocRepe(us.getNum_doc())) {
                    JOptionPane.showMessageDialog(null, "Este numero de Documento ya esta registrado");
                } else if (conUs.Registrar(us)) {
                    JOptionPane.showMessageDialog(null, "Usuario Registrado correctamente");
                    mostrarDatos();
                    limpiar();
                } else {
                    JOptionPane.showMessageDialog(null, "Error en el registro del usuario");
                    limpiar();
                }
            }

        } else if (e.getSource() == gesUS.btn_obtener) {

            gesUS.btn_registrarUS.setEnabled(false);

            int filaSeleccionada = gesUS.tabla_us.getSelectedRow();

            if (filaSeleccionada != -1) {

                String pri_nombre = gesUS.tabla_us.getValueAt(filaSeleccionada, 0).toString();
                String seg_nombre = gesUS.tabla_us.getValueAt(filaSeleccionada, 1).toString();
                String ape_paterno = gesUS.tabla_us.getValueAt(filaSeleccionada, 2).toString();
                String ape_materno = gesUS.tabla_us.getValueAt(filaSeleccionada, 3).toString();
                String tipo_Doc = gesUS.tabla_us.getValueAt(filaSeleccionada, 4).toString();
                String num_Doc = gesUS.tabla_us.getValueAt(filaSeleccionada, 5).toString();
                String contra = gesUS.tabla_us.getValueAt(filaSeleccionada, 6).toString();
                String Rol = gesUS.tabla_us.getValueAt(filaSeleccionada, 7).toString();

                gesUS.txt_priN_us.setText(pri_nombre);
                gesUS.txt_segN_us.setText(seg_nombre);
                gesUS.txt_apeP_us.setText(ape_paterno);
                gesUS.txt_apeM_us.setText(ape_materno);
                gesUS.cbo_tiDoc.setSelectedItem(tipo_Doc);
                gesUS.txt_numD_us.setText(num_Doc);
                gesUS.txt_contra.setText(contra);
                gesUS.cbo_Rol.setSelectedItem(Rol);
            }

        } else if (e.getSource() == gesUS.btn_limpiar) {
            limpiar();
            gesUS.btn_registrarUS.setEnabled(true);
        } else if (e.getSource() == gesUS.btn_editar) {

            if (camposVacios()) {
                JOptionPane.showMessageDialog(null, "Por favor, complete todos los campos obligatorios.");
                return;
            } else if (!tipoDoc()) {
                JOptionPane.showMessageDialog(null, "Numero de Documento no Valido");
                return;
            }

            int filaSeleccionada = gesUS.tabla_us.getSelectedRow();

            if (filaSeleccionada != -1) {
                int respuesta = JOptionPane.showConfirmDialog(null, "¿Está seguro de editar al Usuario?", "Confirmación de Edición", JOptionPane.YES_NO_OPTION);

                if (respuesta == JOptionPane.YES_OPTION) {
                    String numDOC = (String) gesUS.tabla_us.getValueAt(filaSeleccionada, 5);
                    String idPersona = conUs.getId(numDOC);

                    if (idPersona != null) {

                        String nuevoNumDoc = gesUS.txt_numD_us.getText();

                        if (conUs.Repetido(idPersona, nuevoNumDoc)) {
                            JOptionPane.showMessageDialog(null, "El numero de documento ya existe en la base de datos");
                            limpiar();

                        } else {

                            Usuario us = new Usuario();
                            us.setId_persona(idPersona);
                            us.setPri_nombre(gesUS.txt_priN_us.getText());
                            us.setSeg_nombre(gesUS.txt_segN_us.getText());
                            us.setApe_paterno(gesUS.txt_apeP_us.getText());
                            us.setApe_materno(gesUS.txt_apeM_us.getText());

                            String nuevaContrasena = gesUS.txt_contra.getText();

                            if (!nuevaContrasena.isEmpty()) {
                                us.setContraseña(nuevaContrasena);
                            }

                            // Edición de campos que usan ComboBox
                            if (gesUS.cbo_tiDoc.isEnabled()) {
                                us.setTipo_doc(Tipo_Documentos.valueOf(gesUS.cbo_tiDoc.getSelectedItem().toString()));
                            }

                            if (!nuevoNumDoc.isEmpty()) {
                                us.setNum_doc(nuevoNumDoc);
                            }

                            if (gesUS.cbo_Rol.isEnabled()) {
                                us.setRol(Roles_Usuarios.valueOf(gesUS.cbo_Rol.getSelectedItem().toString()));
                            }

                            us.setEstado(Estado_Persona.Activo);

                            if (conUs.Editar(us)) {
                                JOptionPane.showMessageDialog(null, "Usuario Editado Exitosamente");
                                mostrarDatos();
                                limpiar();
                                gesUS.btn_registrarUS.setEnabled(true);
                            } else {
                                JOptionPane.showMessageDialog(null, "Error al editar un Usuario");
                            }
                        }
                    }

                } else {
                    return;
                }
            }

        } else if (e.getSource() == gesUS.btn_eliminar) {

            int filaSeleccionada = gesUS.tabla_us.getSelectedRow();

            if (filaSeleccionada != -1) {

                int respuesta = JOptionPane.showConfirmDialog(null, "¿Esta seguro de eliminar al Usuario?", "Confirmacion de Eliminacion", JOptionPane.YES_NO_OPTION);

                if (respuesta == JOptionPane.YES_OPTION) {

                    String numDoc = (String) gesUS.tabla_us.getValueAt(filaSeleccionada, 5);
                    String idUsuario = conUs.getId(numDoc);

                    if (idUsuario != null && !idUsuario.isEmpty()) {
                        Usuario us = new Usuario();
                        us.setId_persona(idUsuario);

                        if (conUs.eliminar(us)) {
                            JOptionPane.showMessageDialog(null, "Usuario Eliminado Exitosamente");
                            mostrarDatos();
                            limpiar();
                        } else {
                            JOptionPane.showMessageDialog(null, "Error al Eliminar un Usuario");
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "El ID del Usuario es invalido");
                    }

                } else {
                    return;
                }

            }

        } else if (e.getSource() == gesUS.btn_actualizar) {
            mostrarDatos();
        }

    }

    public boolean camposVacios() {
        if (gesUS.txt_priN_us.getText().isEmpty() || gesUS.txt_apeP_us.getText().isEmpty()
                || gesUS.txt_apeM_us.getText().isEmpty() || gesUS.txt_numD_us.getText().isEmpty() || gesUS.txt_contra.getText().isEmpty()) {
            return true; // Devuelve true si hay campos vacíos.
        } else {
            return false; // Devuelve false si no hay campos vacíos.
        }
    }

    //formato para todos los tipos de documento
    public boolean tipoDoc() {

        String tiDoc = gesUS.cbo_tiDoc.getSelectedItem().toString();
        String numDoc = gesUS.txt_numD_us.getText();

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

    public void limpiar() {

        gesUS.txt_priN_us.setText("");
        gesUS.txt_segN_us.setText("");
        gesUS.txt_apeM_us.setText("");
        gesUS.txt_apeP_us.setText("");
        gesUS.cbo_tiDoc.setSelectedItem(1);
        gesUS.txt_numD_us.setText("");
        gesUS.txt_contra.setText("");
        gesUS.cbo_Rol.setSelectedItem(1);

    }

    public void mostrarDatos() {

        DefaultTableModel tusuario = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                // Devuelve false para hacer que todas las celdas no sean editables
                return false;
            }
        };

        tusuario.addColumn("Primer Nombre");
        tusuario.addColumn("Segundo Nombre");
        tusuario.addColumn("Apellido Paterno");
        tusuario.addColumn("Apellido Materno");
        tusuario.addColumn("Tipo de Documento");
        tusuario.addColumn("Numero de Documento");
        tusuario.addColumn("Contraseña");
        tusuario.addColumn("Rol");
        tusuario.addColumn("Estado");

        gesUS.tabla_us.setModel(tusuario);

        List<Usuario> usuarios = conUs.getPersonaAc();

        String[] datos = new String[9];

        for (Usuario us : usuarios) {

            datos[0] = us.getPri_nombre();
            datos[1] = us.getSeg_nombre();
            datos[2] = us.getApe_paterno();
            datos[3] = us.getApe_materno();
            datos[4] = us.getTipo_doc().toString();
            datos[5] = us.getNum_doc();
            datos[6] = us.getContraseña();
            datos[7] = us.getRol().toString();
            datos[8] = us.getEstado().toString();

            tusuario.addRow(datos);

        }

        gesUS.tabla_us.setModel(tusuario);

    }

}
