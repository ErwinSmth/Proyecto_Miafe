/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador.Servicio;

import DAO.Servicio.AlquilerDAO;
import Modelo.Inventariado.Producto;
import Modelo.Servicio.ContratoAlquiler;
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
        addItem.btn_cancelar.addActionListener(this);
        addItem.btn_actualizar.addActionListener(this);
        addItem.btn_eliminar.addActionListener(this);
        addItem.btn_Editar.addActionListener(this);

        addItem.btn_Editar.setVisible(false);
        addItem.btn_eliminar.setVisible(false);
        addItem.jLabel4.setVisible(false);
        addItem.calendario.setVisible(false);
        addItem.btn_cancelar.setVisible(false);
        addItem.btn_contrato.setVisible(false);
    }

    public Control_AgregarItem(AgregarItems addItem, String correo, String idAlquiler) {
        this.addItem = addItem;
        this.correo = correo;
        this.idAlquiler = idAlquiler;

        addItem.btn_Editar.setVisible(false);
        addItem.btn_eliminar.setVisible(false);
        addItem.jLabel4.setVisible(false);
        addItem.calendario.setVisible(false);
        addItem.btn_cancelar.setVisible(false);
        addItem.btn_contrato.setVisible(false);
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

                int cantidad = Integer.parseInt(JOptionPane.showInputDialog(null, "Ingrese la cantidad a alquilar"));

                if (cantidad > 0) {

                    Object valorDipo = addItem.table_prod.getValueAt(fila, 1);
                    int disponible = 0;

                    if (valorDipo instanceof String) {
                        try {
                            disponible = Integer.parseInt((String) valorDipo);
                        } catch (NumberFormatException ex) {
                            ex.printStackTrace();
                        }
                    } else if (valorDipo instanceof Integer) {
                        disponible = (Integer) valorDipo;
                    }

                    if (cantidad > disponible) {

                        JOptionPane.showMessageDialog(null, "Debe Seleccionar una Cantidad Correcta");
                        return;

                    } else {
                        String nombre = (String) addItem.table_prod.getValueAt(fila, 0);

                        Object valor = addItem.table_prod.getValueAt(fila, 2);
                        float costo = 0.0f;

                        if (valor instanceof String) {
                            try {
                                costo = Float.parseFloat((String) valor);
                            } catch (NumberFormatException ex) {
                                ex.printStackTrace();
                            }
                        } else if (valor instanceof Float) {
                            costo = (Float) valor;
                        }

                        pro.setNom_pro(nombre);

                        item.setProducto(pro);
                        item.setCantidad(cantidad);

                        //Significa que se agrego correctamente
                        if (alquiDAO.addItem(item, idAlquiler, costo) == 1) {

                            if (alquiDAO.actualizarInventarioADD(nombre, cantidad) > 0) {
                                addItem.txt_filtrar.setText("");
                                mostrarProductos();
                                seleccionados();
                            } else {
                                JOptionPane.showMessageDialog(null, "Ocurrio un Error en la actualizacion del inventario");
                            }

                        } else {
                            JOptionPane.showMessageDialog(null, "Ocurrio un Error al momento de agregar un producto al alquiler");
                        }
                    }

                } else {
                    JOptionPane.showMessageDialog(null, "Seleccionar una cantidad mayor a 0");
                }

            }

        } else if (e.getSource() == addItem.btn_cancelar) {

            ContratoAlquiler obj = new ContratoAlquiler();
            obj.setId_alquiler(addItem.txt_id.getText());

            if (alquiDAO.eliminarAlquiler(obj) == 1) {

                JOptionPane.showMessageDialog(null, "Eliminado Correctamente");
                mostrarProductos();
                seleccionados();

            }

        } else if (e.getSource() == addItem.btn_actualizar) {

            mostrarProductos();

        } else if (e.getSource() == addItem.btn_eliminar) {

            int fila = (int) addItem.table_selected.getSelectedRow();

            if (fila != -1) {

                String nombrePro = (String) addItem.table_selected.getValueAt(fila, 0);
                String cantiadadString = (String) addItem.table_selected.getValueAt(fila, 1);
                int cantidad = Integer.parseInt(cantiadadString);
                int idalquiler = Integer.parseInt(addItem.txt_id.getText());

                if (alquiDAO.eliminarPro(nombrePro, idalquiler) > 0) {

                    if (alquiDAO.actualizarInventarioELI(nombrePro, cantidad) > 0) {

                        mostrarProductos();
                        seleccionados();

                    }

                }

            }

        } else if (e.getSource() == addItem.btn_Editar) {

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
            Object[] datos = {pro.getNom_pro(), pro.getCantDisponible(), pro.getPrecio_uni()};
            modelo.addRow(datos);
        }

    }

    public void mostrarVista() {

        addItem.txt_id.setText(idAlquiler);
        addItem.lbl_correo.setText(correo);

        addItem.setVisible(true);

    }

}
