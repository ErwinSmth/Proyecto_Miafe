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

                Producto prod;
                String nombre = pro.txt_nomb.getText();
                String categoria = pro.cbo_CATE.getSelectedItem().toString();
                float precio = Float.parseFloat(pro.txt_precio.getText());
                int cantidad = Integer.parseInt(pro.txt_cant.getText());

                prod = new Producto(nombre, new Categoria_Mobiliario(categoria, ""), precio, cantidad);


                

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

}
