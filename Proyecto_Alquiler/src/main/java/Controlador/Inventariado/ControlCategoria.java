/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador.Inventariado;

import DAO.Inventariado.ConsultaCategoria;
import Modelo.Inventariado.Categoria_Mobiliario;
import Vista.Gestion_Categorias;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author DAVID
 */
public class ControlCategoria implements ActionListener {

    private Gestion_Categorias gesCat;
    private ConsultaCategoria conCat;

    public ControlCategoria(Gestion_Categorias gesCat, ConsultaCategoria conCat) {
        this.gesCat = gesCat;
        this.conCat = conCat;
        mostrarDatos();

        this.gesCat.btn_agregar.addActionListener(this);
        this.gesCat.btn_editar.addActionListener(this);
        this.gesCat.btn_eliminar.addActionListener(this);
        this.gesCat.btn_actualizar.addActionListener(this);
        this.gesCat.btn_obtener.addActionListener(this);
        this.gesCat.btn_limpiar.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == gesCat.btn_agregar) {

            if (Vacio()) {
                JOptionPane.showMessageDialog(null, "Por favor, ingrese algo en el nombde de la categoria");
                return;
            } else {

                Categoria_Mobiliario cat = new Categoria_Mobiliario();
                cat.setNom_cat(gesCat.txt_nomCat.getText());
                cat.setDescrip(gesCat.txa_desCat.getText());

                switch (conCat.agregar(cat)) {
                    case 3:
                        JOptionPane.showMessageDialog(null, "La categoria ya existe en la Base de Datos");
                        break;
                    case 2:
                        JOptionPane.showMessageDialog(null, "Ocurrio un Error durante la Ejecucion");
                        break;
                    case 1:
                        JOptionPane.showMessageDialog(null, "Categoria Agegada Exitosamente");
                        mostrarDatos();
                        limpiar();
                        break;
                    default:
                        break;
                }

            }

        } else if (e.getSource() == gesCat.btn_actualizar) {
            mostrarDatos();
        } else if (e.getSource() == gesCat.btn_limpiar) {
            limpiar();
        } else if (e.getSource() == gesCat.btn_obtener) {

            gesCat.txt_nomCat.setEnabled(false);
            gesCat.btn_agregar.setEnabled(false);
            gesCat.btn_limpiar.setEnabled(false);

            int fila = gesCat.tabla_categoria.getSelectedRow();

            if (fila != -1) {

                String nombre = gesCat.tabla_categoria.getValueAt(fila, 0).toString();
                String descri = gesCat.tabla_categoria.getValueAt(fila, 1).toString();

                gesCat.txt_nomCat.setText(nombre);
                gesCat.txa_desCat.setText(descri);

            }

        } else if (e.getSource() == gesCat.btn_editar) {

            int fila = gesCat.tabla_categoria.getSelectedRow();

            if (fila != -1) {
                int rpa = JOptionPane.showConfirmDialog(null, "Desea continuar con la edicion?", "Confirmacion de edicion", JOptionPane.YES_NO_OPTION);

                if (rpa == JOptionPane.YES_OPTION) {

                    String nombre = (String) gesCat.tabla_categoria.getValueAt(fila, 0);
                    Categoria_Mobiliario cat = new Categoria_Mobiliario();
                    cat.setNom_cat(nombre);
                    cat.setDescrip(gesCat.txa_desCat.getText());

                    if (conCat.editar(cat) == 2) {
                        JOptionPane.showMessageDialog(null, "Ocurrio un error durante la ejecucion");
                    } else if (conCat.editar(cat) == 1) {
                        JOptionPane.showMessageDialog(null, "Categoria Editada Exitosamente");
                        mostrarDatos();
                        limpiar();
                        gesCat.btn_agregar.setEnabled(true);
                        gesCat.txt_nomCat.setEnabled(true);
                        gesCat.btn_limpiar.setEnabled(true);
                    }

                } else {
                    return;
                }
            }

        } else if (e.getSource() == gesCat.btn_eliminar) {

            int fila = gesCat.tabla_categoria.getSelectedRow();

            if (fila != -1) {

                int rpa = JOptionPane.showConfirmDialog(null, "¿Esta seguro de eliminar la Categoria?", "Confirmacion de Eliminacion", JOptionPane.YES_NO_OPTION);

                if (rpa == JOptionPane.YES_OPTION) {

                    String nombre = (String) gesCat.tabla_categoria.getValueAt(fila, 0);
                    Categoria_Mobiliario cat = new Categoria_Mobiliario();
                    cat.setNom_cat(nombre);

                    if (conCat.eliminar(cat) == 2) {
                        JOptionPane.showMessageDialog(null, "Ocurrio un error durante la ejecucion");
                    } else if (conCat.eliminar(cat) == 1) {
                        JOptionPane.showMessageDialog(null, "Categoria Eliminada Exitosamente");
                        mostrarDatos();
                    }

                }

            }

        }

    }

    public void limpiar() {
        gesCat.txt_nomCat.setText("");
        gesCat.txa_desCat.setText("");
    }

    public boolean Vacio() {
        if (gesCat.txt_nomCat.getText().isEmpty()) {
            return true;
        }
        return false;
    }

    public void mostrarDatos() {

        DefaultTableModel tcategoria = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                // Devuelve false para hacer que todas las celdas no sean editables
                return false;
            }
        };

        tcategoria.addColumn("Nombre");
        tcategoria.addColumn("Descripcion");

        gesCat.tabla_categoria.setModel(tcategoria);

        List<Categoria_Mobiliario> categorias = conCat.getListado();

        String[] datos = new String[2];

        for (Categoria_Mobiliario cat : categorias) {

            datos[0] = cat.getNom_cat();
            datos[1] = cat.getDescrip();

            tcategoria.addRow(datos);

        }

        gesCat.tabla_categoria.setModel(tcategoria);

    }

}
