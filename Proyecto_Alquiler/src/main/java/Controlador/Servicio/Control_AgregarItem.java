/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador.Servicio;

import DAO.Servicio.AlquilerDAO;
import Modelo.Inventariado.Producto;
import Modelo.Servicio.Item;
import Vista.AgregarItems;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author DAVID
 */
public class Control_AgregarItem implements ActionListener {

    private String correo;
    private String idAlquiler;
    private AlquilerDAO alquiDAO;
    private AgregarItems addItem;

    public Control_AgregarItem(AlquilerDAO alquiDAO, AgregarItems addItem) {
        this.alquiDAO = alquiDAO;
        this.addItem = addItem;
        mostrarProductos();
        seleccionados();
        addItem.btn_buscar.addActionListener(this);
        addItem.btn_añadir.addActionListener(this);
    }

    public Control_AgregarItem(AgregarItems addItem, String correo, String idAlquiler) {
        this.addItem = addItem;
        this.correo = correo;
        this.idAlquiler = idAlquiler;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == addItem.btn_buscar) {

            String textoBusqueda = addItem.txt_filtrar.getText().trim();

            if (!textoBusqueda.isEmpty()) {
                List<Producto> productosFiltrados = alquiDAO.filtrarProducto(textoBusqueda);
                mostrarProductosFiltrados(productosFiltrados);
            } else {
                mostrarProductos();
            }

        } else if (e.getSource() == addItem.btn_añadir) {

            int idAlquiler = Integer.parseInt(addItem.txt_id.getText());

            Item item = new Item();
            Producto pro = new Producto();

            int fila = addItem.table_prod.getSelectedRow();

            if (fila != -1) {

                int cantidad = Integer.parseInt(JOptionPane.showInputDialog(null, "Ingrse la cantidad a alquilar"));

                if (cantidad > 0) {

                    String nombre = (String) addItem.table_prod.getValueAt(fila, 0);

                    String costoString = (String) addItem.table_prod.getValueAt(fila, 2);
                    float costo = Float.parseFloat(costoString);

                    pro.setNom_pro(nombre);

                    item.setProducto(pro);
                    item.setCantidad(cantidad);

                    //Significa que se agrego correctamente
                    if (alquiDAO.addItem(item, idAlquiler, costo) == 1) {

                        if (alquiDAO.actualizarInventario(nombre, cantidad, true) > 0) {
                            mostrarProductos();
                            seleccionados();
                        } else {
                            JOptionPane.showMessageDialog(null, "Ocurrio un Error en la actualizacion del inventario");
                        }

                    } else {
                        JOptionPane.showMessageDialog(null, "Ocurrio un Error al momento de agregar un producto al alquiler");
                    }

                }

            }

        }

    }

    public void mostrarProductos() {

        DefaultTableModel tproducto = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                // Devuelve false para hacer que todas las celdas no sean editables
                return false;
            }
        };

        tproducto.addColumn("Nombre");
        tproducto.addColumn("Disponibles");
        tproducto.addColumn("Precio Unitario");

        addItem.table_prod.setModel(tproducto);

        List<Producto> productos = alquiDAO.getProducto();

        String[] datos = new String[3];

        for (Producto pro : productos) {

            datos[0] = pro.getNom_pro();
            datos[1] = String.valueOf(pro.getCantDisponible());
            datos[2] = String.valueOf(pro.getPrecio_uni());

            tproducto.addRow(datos);

        }

        addItem.table_prod.setModel(tproducto);
    }

    public void seleccionados() {

        DefaultTableModel tseleccionados = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                // Devuelve false para hacer que todas las celdas no sean editables
                return false;
            }
        };

        String idTexto = addItem.txt_id.getText().trim();

        if (idTexto != null) {
            tseleccionados.addColumn("Nombre");
            tseleccionados.addColumn("Cantidad Alquilada");
            tseleccionados.addColumn("Precio Alquiler");

            addItem.table_selected.setModel(tseleccionados);

            List<Item> items = alquiDAO.getItems(idTexto);

            String[] datos = new String[3];

            for (Item i : items) {
                datos[0] = i.getProducto().getNom_pro();
                datos[1] = String.valueOf(i.getCantidad());
                datos[2] = String.valueOf(i.getConteo_Precio());

                tseleccionados.addRow(datos);
            }

            addItem.table_selected.setModel(tseleccionados);
        }

    }

    private void mostrarProductosFiltrados(List<Producto> productos) {
        DefaultTableModel modelo = (DefaultTableModel) addItem.table_prod.getModel();
        modelo.setRowCount(0); // Limpiar la tabla

        for (Producto pro : productos) {
            Object[] datos = {pro.getNom_pro(), pro.getCantDisponible()};
            modelo.addRow(datos);
        }

    }

    public void mostrarVista() {

        addItem.txt_id.setText(idAlquiler);
        addItem.lbl_correo.setText(correo);

        addItem.setVisible(true);

    }

}
