/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador.Inventariado;

import DAO.Inventariado.ConsultaProducto;
import Vista.Productos;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author DAVID
 */
public class ControlProducto implements ActionListener{

    private ConsultaProducto conPro;
    private Productos pro;

    public ControlProducto(ConsultaProducto conPro, Productos pro) {
        this.conPro = conPro;
        this.pro = pro;
        
        pro.btn_agregar.addActionListener(this);
        pro.btn_editarPre.addActionListener(this);
        pro.btn_eliminar.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    

    
    
}
