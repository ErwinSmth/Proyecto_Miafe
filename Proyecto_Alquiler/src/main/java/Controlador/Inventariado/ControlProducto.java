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

public class ControlProducto implements ActionListener {

    private ConsultaProducto conPro;
    private Productos pro;
    private ConsultaCategoria conCat;

    public ControlProducto(ConsultaProducto conPro, Productos pro, ConsultaCategoria conCat) {
        this.conPro = conPro;
        this.pro = pro;
        this.conCat = conCat;

        pro.btn_agregar.addActionListener(this);
        pro.btn_editarPre.addActionListener(this);
        pro.btn_eliminar.addActionListener(this);
        pro.cbo_CATE.addActionListener(this);
        LLENARCOMBO();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
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

}
