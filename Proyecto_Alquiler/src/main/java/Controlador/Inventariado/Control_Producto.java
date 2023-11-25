/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador.Inventariado;

import DAO.Inventariado.ConsultaProducto;
import Modelo.Inventariado.Producto;
import Vista.Gestion_Productos;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author DAVID
 */
public class Control_Producto implements ActionListener {

    private ConsultaProducto conPro;
    private Gestion_Productos gesPro;

    public Control_Producto(ConsultaProducto conPro, Gestion_Productos gesPro) {
        this.conPro = conPro;
        this.gesPro = gesPro;

        mostrarTodo();

        gesPro.btn_actualizar.addActionListener(this);
        gesPro.btn_eliminar.addActionListener(this);
        gesPro.btn_precio.addActionListener(this);
        gesPro.btn_disponible.addActionListener(this);

    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == gesPro.btn_eliminar) {

            int fila = gesPro.table_produc.getSelectedRow();

            if (fila != -1) {

                int respuesta = JOptionPane.showConfirmDialog(null, "¿Esta seguro de eliminar el Producto?", "Confirmacion de Eliminacion", JOptionPane.YES_NO_OPTION);

                if (respuesta == JOptionPane.YES_OPTION) {

                    String nombre = (String) gesPro.table_produc.getValueAt(fila, 0);

                    if (!nombre.isEmpty()) {

                        Producto pro = new Producto();
                        pro.setNom_pro(nombre);

                        if (conPro.eliminar(pro) == 1) {
                            JOptionPane.showMessageDialog(null, "Producto Eliminado Exitosamente");
                            mostrarTodo();
                        } else if (conPro.eliminar(pro) == 2) {
                            JOptionPane.showMessageDialog(null, "Ocurrio un problema");
                        }

                    }

                }

            }

        } else if (e.getSource() == gesPro.btn_actualizar) {
            mostrarTodo();
        } else if (e.getSource() == gesPro.btn_precio) {

            int fila = gesPro.table_produc.getSelectedRow();

            if (fila != -1) {

                float nuevoPrecio = 0;

                nuevoPrecio = Float.parseFloat(JOptionPane.showInputDialog(null, "Ingrese el nuevo precio"));

                if (nuevoPrecio != 0) {

                    String nombre = (String) gesPro.table_produc.getValueAt(fila, 0);

                    if (!nombre.isEmpty()) {

                        Producto pro = new Producto();
                        pro.setNom_pro(nombre);
                        pro.setPrecio_uni(nuevoPrecio);

                        switch (conPro.editar(pro)) {
                            case 1:
                                JOptionPane.showMessageDialog(null, "Precio cambiado exitosamente");
                                mostrarTodo();
                                break;
                            case 3:
                                JOptionPane.showMessageDialog(null, "Ingrese un valor diferente de 0 y positivo");
                                break;
                            case 2:
                                JOptionPane.showMessageDialog(null, "Ocurrio un error");
                                break;
                            default:
                                break;
                        }

                    }

                }

            }

        } else if (e.getSource() == gesPro.btn_disponible) {

            int fila = gesPro.table_produc.getSelectedRow();

            if (fila != -1) {

                int cantidad = 0;

                cantidad = Integer.parseInt(JOptionPane.showInputDialog(null, "Ingrese la cantidad de productos disponibles"));

                if (cantidad != 0) {

                    String nombre = (String) gesPro.table_produc.getValueAt(fila, 0);
                    
                     if (!nombre.isEmpty()) {
                         
                         Producto pro = new Producto();
                         pro.setNom_pro(nombre);
                         
                        switch (conPro.setDisponibles(pro, cantidad)) {
                            case 1:
                                JOptionPane.showMessageDialog(null, "Cantidad añadidad correctamente");
                                mostrarTodo();
                                break;
                            case 2:
                                JOptionPane.showMessageDialog(null, "Ocurrio un Error");
                                break;
                            case 3:
                                JOptionPane.showMessageDialog(null, "Debe ingresar una cantidad mayor a 0");
                                break;
                            default:
                                break;
                        }
                         
                     }

                }

            }

        }
    }

    public void mostrarTodo() {

        DefaultTableModel tproducto = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column
            ) {
                // Devuelve false para hacer que todas las celdas no sean editables
                return false;
            }
        };

        tproducto.addColumn("Nombre");
        tproducto.addColumn("Categoria");
        tproducto.addColumn("Precio");
        tproducto.addColumn("Disponibles");
        tproducto.addColumn("Prestados");
        tproducto.addColumn("En Mantenimiento");

        gesPro.table_produc.setModel(tproducto);

        List<Producto> productos = conPro.getListado();

        String datos[] = new String[6];

        for (Producto pro : productos) {

            datos[0] = pro.getNom_pro();
            datos[1] = pro.getCategoria().getNom_cat();
            datos[2] = String.valueOf(pro.getPrecio_uni());
            datos[3] = String.valueOf(pro.getCantDisponible());
            datos[4] = String.valueOf(pro.getCantPrestada());
            datos[5] = String.valueOf(pro.getCantMantenimiento());

            tproducto.addRow(datos);

        }

        gesPro.table_produc.setModel(tproducto);

    }

}
