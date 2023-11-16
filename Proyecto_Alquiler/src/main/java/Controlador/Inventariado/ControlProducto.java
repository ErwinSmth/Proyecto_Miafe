/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador.Inventariado;

import DAO.Inventariado.ConsultaProducto;
import Vista.Productos;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import DAO.Inventariado.ConsultaCategoria;
import Modelo.Inventariado.Categoria_Mobiliario;
import java.util.List;
import javax.swing.JOptionPane;

import Modelo.Inventariado.Producto;
import javax.swing.table.DefaultTableModel;

public class ControlProducto implements ActionListener {

    private ConsultaProducto conPro;
    private Productos pro;
    private ConsultaCategoria conCat;

    public ControlProducto(ConsultaProducto conPro, Productos pro, ConsultaCategoria conCat) {
        this.conPro = conPro;
        this.pro = pro;
        this.conCat = conCat;

        this.pro.btn_agregar.addActionListener(this);
        this.pro.btn_editarPre.addActionListener(this);
        this.pro.btn_eliminar.addActionListener(this);
        this.pro.cbo_CATE.addActionListener(this);
        LLENARCOMBO();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == pro.btn_agregar) {
            if (camposVacios()) {
                JOptionPane.showMessageDialog(null, "Por favor, complete todos los campos obligatorios.");

            } else {

                Producto prod = new Producto();
                String nombre = pro.txt_nomb.getText();
                String categoria = pro.cbo_CATE.getSelectedItem().toString();
                float precio = Float.parseFloat(pro.txt_precio.getText());
                int cantidad = Integer.parseInt(pro.txt_cant.getText());

                prod = new Producto(nombre, new Categoria_Mobiliario(categoria, ""), precio, cantidad);

                switch (conPro.agregar(prod)) {
                    case 3:
                        JOptionPane.showMessageDialog(null, "El producto ya existe en la Base de Datos");
                        break;
                    case 2:
                        JOptionPane.showMessageDialog(null, "Ocurrio un Error durante la Ejecucion");
                        break;
                    case 1:
                        JOptionPane.showMessageDialog(null, "Producto agregado exitosamente");
                        MOSTRAPRODUCTOS();
                        limpiar();
                        break;
                    default:
                        break;
                }

            }

        } else if (e.getSource() == pro.btn_actualizar) {
            MOSTRAPRODUCTOS();
        } else if (e.getSource() == pro.btn_limpiar) {
            limpiar();
        } else if (e.getSource() == pro.btn_obtener) {

            pro.txt_nomb.setEnabled(false);
            pro.txt_cant.setEnabled(false);
            pro.cbo_CATE.setEnabled(false);
            pro.btn_agregar.setEnabled(false);
            pro.btn_limpiar.setEnabled(false);

            int fila = pro.tabla_producto.getSelectedRow();

            if (fila != -1) {

                String nombre = pro.tabla_producto.getValueAt(fila, 0).toString();
                String categoria = pro.tabla_producto.getValueAt(fila, 1).toString();
                String precio = pro.tabla_producto.getValueAt(fila, 2).toString();
                String cantidad = pro.tabla_producto.getValueAt(fila, 3).toString();

                pro.txt_nomb.setText(nombre);
                pro.cbo_CATE.setSelectedItem(categoria);
                pro.txt_precio.setText(precio);
                pro.txt_cant.setText(cantidad);

            }

        } else if (e.getSource() == pro.btn_editarPre) {

            int fila = pro.tabla_producto.getSelectedRow();

            if (camposVacios()) {
                JOptionPane.showMessageDialog(null, "Obtenga un producto para editar el precio");
            } else {
                if (fila != -1) {
                    int rpa = JOptionPane.showConfirmDialog(null, "Desea continuar con la edicion?", "Confirmacion de edicion", JOptionPane.YES_NO_OPTION);

                    if (rpa == JOptionPane.YES_OPTION) {

                        String nombre = (String) pro.tabla_producto.getValueAt(fila, 0);
                        String categoria = (String) pro.tabla_producto.getValueAt(fila, 1);
                        String cantidad = (String) pro.tabla_producto.getValueAt(fila, 3);

                        Producto prod = new Producto();
                        prod.setNom_pro(nombre);
                        prod.setCategoria(new Categoria_Mobiliario(categoria, ""));
                        //SOLO SE CAMBIARÁ EL PRECIO
                        prod.setPrecio_uni(Float.parseFloat(pro.txt_precio.getText()));
                        prod.setCantDisponible(Integer.parseInt(cantidad));

                        if (conPro.editar(prod) == 2) {
                            JOptionPane.showMessageDialog(null, "Ocurrio un error durante la ejecucion");
                        } else if (conPro.editar(prod) == 1) {
                            JOptionPane.showMessageDialog(null, "Precio editado exitosamente");
                            MOSTRAPRODUCTOS();
                            limpiar();
                            pro.txt_nomb.setEnabled(true);
                            pro.txt_cant.setEnabled(true);
                            pro.cbo_CATE.setEnabled(true);
                            pro.btn_agregar.setEnabled(true);
                            pro.btn_limpiar.setEnabled(true);
                        }

                    } else {
                        return;
                    }
                }
            }
        } else if (e.getSource() == pro.btn_eliminar) {

            int fila = pro.tabla_producto.getSelectedRow();

            if (fila != -1) {

                int rpa = JOptionPane.showConfirmDialog(null, "¿Esta seguro de eliminar el producto?", "Confirmacion de Eliminacion", JOptionPane.YES_NO_OPTION);

                if (rpa == JOptionPane.YES_OPTION) {

                    String nombre = (String) pro.tabla_producto.getValueAt(fila, 0);
                    Producto pr = new Producto();
                    pr.setNom_pro(nombre);

                    if (conPro.eliminar(pr) == 2) {
                        JOptionPane.showMessageDialog(null, "Ocurrio un error durante la ejecucion");
                    } else if (conPro.eliminar(pr) == 1) {
                        JOptionPane.showMessageDialog(null, "Categoria Eliminada Exitosamente");
                        MOSTRAPRODUCTOS();
                    }

                }

            }

        }
    }

    public void LLENARCOMBO() {

        List<Categoria_Mobiliario> categorias = conCat.getListado();

        String[] datos = new String[2];

        for (Categoria_Mobiliario listacat : categorias) {

            pro.cbo_CATE.addItem(listacat.getNom_cat());

        }

    }

    public void limpiar() {

        pro.txt_nomb.setText("");
        pro.txt_cant.setText("");
        pro.txt_precio.setText("");

    }

    public boolean camposVacios() {
        if (pro.txt_nomb.getText().isEmpty() || pro.txt_cant.getText().isEmpty()
                || pro.txt_precio.getText().isEmpty()) {
            return true; // Devuelve true si hay campos vacíos.
        } else {
            return false; // Devuelve false si no hay campos vacíos.
        }
    }

    public void MOSTRAPRODUCTOS() {

        DefaultTableModel tproducto = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                // Devuelve false para hacer que todas las celdas no sean editables
                return false;
            }
        };

        tproducto.addColumn("Producto");
        tproducto.addColumn("Categoría");
        tproducto.addColumn("Precio alquiler");
        tproducto.addColumn("Cantidad disponible");

        pro.tabla_producto.setModel(tproducto);

        List<Producto> productos = conPro.getListado();

        String[] datos = new String[4];

        for (Producto produc : productos) {

            datos[0] = produc.getNom_pro();
            datos[1] = produc.getCategoria().toString();
            datos[2] = "" + produc.getPrecio_uni();
            datos[3] = "" + produc.getCantDisponible();

            tproducto.addRow(datos);

        }

        pro.tabla_producto.setModel(tproducto);

    }

}
