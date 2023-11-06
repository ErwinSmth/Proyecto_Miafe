package Controlador;

import DAO.ConsultaCliente;
import Modelo.Cliente;
import Modelo.Estado_Persona;
import Modelo.Tipo_Documentos;
import Vista.Gestion_Clientes;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Roberth
 */
public class ControladorGestionCLI implements ActionListener {

    private Gestion_Clientes gesCLI;
    private ConsultaCliente conCli;

    public ControladorGestionCLI(Gestion_Clientes gesCLI, ConsultaCliente conCLI) {
        this.gesCLI = gesCLI;
        this.conCli = conCLI;
        mostrarDatos();
        mostrarDatosINAC();
        this.gesCLI.btn_actualizar.addActionListener((ActionListener) this);
        this.gesCLI.btn_obtener.addActionListener((ActionListener) this);
        this.gesCLI.btn_editar.addActionListener((ActionListener) this);
        this.gesCLI.btn_limpiar.addActionListener((ActionListener) this);
        this.gesCLI.btn_eliminar.addActionListener(this);
        this.gesCLI.btn_reactivar.addActionListener(this);
//        gesCLI.tabla_cli1.getColumn(6).setPreferredWidth(100);
//        gesCLI.tabla_cli1.getColumn(7).setPreferredWidth(100);
//        gesCLI.tabla_clINAC.getColumn(6).setPreferredWidth(100);
//        gesCLI.tabla_clINAC.getColumn(7).setPreferredWidth(100);
    }

    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == gesCLI.btn_obtener) {

            int filaSeleccionada = gesCLI.tabla_cli1.getSelectedRow();

            if (filaSeleccionada != -1) {

                String pri_nombre = gesCLI.tabla_cli1.getValueAt(filaSeleccionada, 0).toString();
                String seg_nombre = gesCLI.tabla_cli1.getValueAt(filaSeleccionada, 1).toString();
                String ape_paterno = gesCLI.tabla_cli1.getValueAt(filaSeleccionada, 2).toString();
                String ape_materno = gesCLI.tabla_cli1.getValueAt(filaSeleccionada, 3).toString();
                String tipo_Doc = gesCLI.tabla_cli1.getValueAt(filaSeleccionada, 4).toString();
                String num_Doc = gesCLI.tabla_cli1.getValueAt(filaSeleccionada, 5).toString();
                String correo = gesCLI.tabla_cli1.getValueAt(filaSeleccionada, 6).toString();
                String direccion = gesCLI.tabla_cli1.getValueAt(filaSeleccionada, 7).toString();
                String telefono = gesCLI.tabla_cli1.getValueAt(filaSeleccionada, 8).toString();

                gesCLI.txt_priN_cli.setText(pri_nombre);
                gesCLI.txt_segN_cli.setText(seg_nombre);
                gesCLI.txt_apeP_cli.setText(ape_paterno);
                gesCLI.txt_apeM_cli.setText(ape_materno);
                gesCLI.cbo_tiDoc.setSelectedItem(tipo_Doc);
                gesCLI.txt_numD_cli.setText(num_Doc);
                gesCLI.txt_correo.setText(correo);
                gesCLI.txa_direccion.setText(direccion);
                gesCLI.txt_telefo.setText(telefono);
            }

        } else if (e.getSource() == gesCLI.btn_limpiar) {
            limpiar();
        } else if (e.getSource() == gesCLI.btn_editar) {

            if (camposVacios()) {
                JOptionPane.showMessageDialog(null, "Por favor, complete todos los campos obligatorios.");
                return;
            } else if (!tipoDoc()) {
                JOptionPane.showMessageDialog(null, "Numero de Documento no Valido");
                return;
            } else if (!numTele()) {
                JOptionPane.showMessageDialog(null, "Numero de Telefono no valido");
                return;
            } else if (!correo()) {
                JOptionPane.showMessageDialog(null, "Correo no valido");
                return;
            }

            int filaSeleccionada = gesCLI.tabla_cli1.getSelectedRow();

            if (filaSeleccionada != -1) {
                int respuesta = JOptionPane.showConfirmDialog(null, "¿Está seguro de editar el Cliente?", "Confirmación de Edición", JOptionPane.YES_NO_OPTION);

                if (respuesta == JOptionPane.YES_OPTION) {

                    String numDoc = (String) gesCLI.tabla_cli1.getValueAt(filaSeleccionada, 5);
                    String idPersona = conCli.getId(numDoc);

                    if (idPersona != null) {

                        String nuevoNumDoc = gesCLI.txt_numD_cli.getText();

                        if (conCli.Repetido(idPersona, nuevoNumDoc)) {
                            JOptionPane.showMessageDialog(null, "El numero de documento ya existe en la base de datos");
                            limpiar();
                            
                        } else {

                            Cliente cli = new Cliente();
                            cli.setId_persona(idPersona);
                            cli.setPri_nombre(gesCLI.txt_priN_cli.getText());
                            cli.setSeg_nombre(gesCLI.txt_segN_cli.getText());
                            cli.setApe_paterno(gesCLI.txt_apeP_cli.getText());
                            cli.setApe_materno(gesCLI.txt_apeM_cli.getText());

                            if (gesCLI.cbo_tiDoc.isEnabled()) {
                                cli.setTipo_doc(Tipo_Documentos.valueOf(gesCLI.cbo_tiDoc.getSelectedItem().toString()));
                            }

                            if (!nuevoNumDoc.isEmpty()) {
                                cli.setNum_doc(nuevoNumDoc);
                            }

                            cli.setEstado(Estado_Persona.Activo);
                            cli.setCorreo(gesCLI.txt_correo.getText());
                            cli.setDireccion(gesCLI.txa_direccion.getText());
                            cli.setTelefono(gesCLI.txt_telefo.getText());

                            if (conCli.Editar(cli)) {
                                JOptionPane.showMessageDialog(null, "Cliente Editado Exitosamente");
                                mostrarDatos();
                                limpiar();
                            } else {
                                JOptionPane.showMessageDialog(null, "Error al Editar al Cliente");
                            }

                        }

                    }

                }
            } else {
                return;
            }

        } else if (e.getSource() == gesCLI.btn_actualizar) {
            mostrarDatos();
        } else if (e.getSource() == gesCLI.btn_eliminar) {

            int filaSeleccionada = gesCLI.tabla_cli1.getSelectedRow();

            if (filaSeleccionada != -1) {

                int respuesta = JOptionPane.showConfirmDialog(null, "¿Esta seguro de eliminar al Cliente?", "Confirmacion de Eliminacion", JOptionPane.YES_NO_OPTION);

                if (respuesta == JOptionPane.YES_OPTION) {

                    String numDoc = (String) gesCLI.tabla_cli1.getValueAt(filaSeleccionada, 5);
                    String idCliente = conCli.getId(numDoc);

                    if (idCliente != null && !idCliente.isEmpty()) {
                        Cliente cli = new Cliente();
                        cli.setId_persona(idCliente);

                        if (conCli.eliminar(cli)) {
                            JOptionPane.showMessageDialog(null, "Cliente Eliminado Exitosamente");
                            mostrarDatos();
                            mostrarDatosINAC();
                            limpiar();
                        } else {
                            JOptionPane.showMessageDialog(null, "Error al Eliminar un Cliente");
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "El ID del Cliente es invalido");
                    }

                }
            } else {
                return;
            }

        } else if (e.getSource() == gesCLI.btn_reactivar) {

            int filaSeleccionada = gesCLI.tabla_clINAC.getSelectedRow();

            if (filaSeleccionada != -1) {

                String numDoc = (String) gesCLI.tabla_clINAC.getValueAt(filaSeleccionada, 5);
                String idUsuario = conCli.getId(numDoc);

                if (idUsuario != null && !idUsuario.isEmpty()) {
                    Cliente cli = new Cliente();
                    cli.setId_persona(idUsuario);

                    if (conCli.Reactivar(cli)) {
                        JOptionPane.showMessageDialog(null, "Cliente Reactivado Exitosamente");
                        mostrarDatos();
                        mostrarDatosINAC();
                    } else {
                        JOptionPane.showMessageDialog(null, "Error al reactivar un Cliente");
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "El ID del Cliente es invalido");
                }

            }

        }

    }

    public boolean camposVacios() {
        if (gesCLI.txt_priN_cli.getText().isEmpty() || gesCLI.txt_apeP_cli.getText().isEmpty()
                || gesCLI.txt_apeM_cli.getText().isEmpty() || gesCLI.txt_numD_cli.getText().isEmpty()) {
            return true; // Devuelve true si hay campos vacíos.
        } else {
            return false; // Devuelve false si no hay campos vacíos.
        }
    }

    public boolean tipoDoc() {

        String tiDoc = gesCLI.cbo_tiDoc.getSelectedItem().toString();
        String numDoc = gesCLI.txt_numD_cli.getText();

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

        String num = gesCLI.txt_telefo.getText();

        if (num.matches("^\\d{9}$")) {
            return true;
        }

        return false;

    }

    public boolean correo() {

        String correo = gesCLI.txt_correo.getText();

        if (correo.matches("^[A-Za-z0-9+_.-]+@(.+)$")) {
            return true;
        }

        return false;

    }

    public void limpiar() {

        gesCLI.txt_priN_cli.setText("");
        gesCLI.txt_segN_cli.setText("");
        gesCLI.txt_apeM_cli.setText("");
        gesCLI.txt_apeP_cli.setText("");
        gesCLI.cbo_tiDoc.setSelectedItem(1);
        gesCLI.txt_numD_cli.setText("");
        gesCLI.txt_correo.setText("");
        gesCLI.txa_direccion.setText("");
        gesCLI.txt_telefo.setText("");

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
        tusuario.addColumn("Correo");
        tusuario.addColumn("Direccion");
        tusuario.addColumn("Telefono");
        tusuario.addColumn("Estado");

        gesCLI.tabla_cli1.setModel(tusuario);

        List<Cliente> clientes = conCli.getPersonaAc();

        String[] datos = new String[12];

        for (Cliente cli : clientes) {

            datos[0] = cli.getPri_nombre();
            datos[1] = cli.getSeg_nombre();
            datos[2] = cli.getApe_paterno();
            datos[3] = cli.getApe_materno();
            datos[4] = cli.getTipo_doc().toString();
            datos[5] = cli.getNum_doc();
            datos[6] = cli.getCorreo();
            datos[7] = cli.getDireccion();
            datos[8] = cli.getTelefono();
            datos[9] = cli.getEstado().toString();

            tusuario.addRow(datos);

        }

        gesCLI.tabla_cli1.setModel(tusuario);

    }

    public void mostrarDatosINAC() {

        DefaultTableModel tcliINAC = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                // Devuelve false para hacer que todas las celdas no sean editables
                return false;
            }
        };

        tcliINAC.addColumn("Primer Nombre");
        tcliINAC.addColumn("Segundo Nombre");
        tcliINAC.addColumn("Apellido Paterno");
        tcliINAC.addColumn("Apellido Materno");
        tcliINAC.addColumn("Tipo de Documento");
        tcliINAC.addColumn("Numero de Documento");
        tcliINAC.addColumn("Correo");
        tcliINAC.addColumn("Direccion");
        tcliINAC.addColumn("Telefono");
        tcliINAC.addColumn("Estado");

        gesCLI.tabla_clINAC.setModel(tcliINAC);

        List<Cliente> clientes = conCli.getPersonaInac();

        String[] datos = new String[10];

        for (Cliente cli : clientes) {

            datos[0] = cli.getPri_nombre();
            datos[1] = cli.getSeg_nombre();
            datos[2] = cli.getApe_paterno();
            datos[3] = cli.getApe_materno();
            datos[4] = cli.getTipo_doc().toString();
            datos[5] = cli.getNum_doc();
            datos[6] = cli.getCorreo();
            datos[7] = cli.getDireccion();
            datos[8] = cli.getTelefono();
            datos[9] = cli.getEstado().toString();

            tcliINAC.addRow(datos);

        }

        gesCLI.tabla_clINAC.setModel(tcliINAC);

    }
}
