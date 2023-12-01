/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador.Servicio;

import DAO.ConsultaCliente;
import DAO.Servicio.AlquilerDAO;
import Modelo.Cliente;
import Modelo.Inventariado.Producto;
import Modelo.Servicio.ContratoAlquiler;
import Modelo.Servicio.Item;
import Vista.AgregarItems;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
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
    private ConsultaCliente conCli;

    public Control_AgregarItem(AlquilerDAO alquiDAO, AgregarItems addItem, ConsultaCliente conCli) {
        this.alquiDAO = alquiDAO;
        this.addItem = addItem;
        this.conCli = conCli;
        mostrarProductos();
        seleccionados();
        Totales();
        addItem.btn_buscar.addActionListener(this);
        addItem.btn_añadir.addActionListener(this);
        addItem.btn_cancelar.addActionListener(this);
        addItem.btn_actualizar.addActionListener(this);
        addItem.btn_eliminar.addActionListener(this);
        addItem.btn_Editar.addActionListener(this);
        addItem.btn_contrato.addActionListener(this);
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
                                Totales();
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
                addItem.dispose();

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
                        Totales();

                    }

                }

            }

        } else if (e.getSource() == addItem.btn_Editar) {

            int fila = (int) addItem.table_selected.getSelectedRow();

            if (fila != -1) {

                String nombrePro = (String) addItem.table_selected.getValueAt(fila, 0);
                int idalquiler = Integer.parseInt(addItem.txt_id.getText());
                String cantiadadString = (String) addItem.table_selected.getValueAt(fila, 1);
                int cantidad = Integer.parseInt(cantiadadString);

                int disponible = alquiDAO.getDisponibles(nombrePro);

                int newcantidad = Integer.parseInt(JOptionPane.showInputDialog(null, "Ingrese la nueva cantidad"));

                if (newcantidad <= 0) {

                    JOptionPane.showMessageDialog(null, "Debe seleccionar una cantidad valida");

                }
                if (newcantidad > disponible) {

                    JOptionPane.showMessageDialog(null, "Debe seleccionar una cantidad menor a la disponible");

                } else {
                    if (newcantidad != cantidad) {
                        //si la cantidad a agregar es mayor a la cantidad del producto que ya esta seleccionado se llama
                        //al metodo para que actualize el inventario cuando se agrega mas
                        float costoUnitario = alquiDAO.obtenerCosto(nombrePro);
                        int resultado = alquiDAO.updateCant(idalquiler, nombrePro, newcantidad, costoUnitario);

                        if (resultado == 1) {
                            mostrarProductos();
                            seleccionados();
                            Totales();
                        } else {
                            JOptionPane.showMessageDialog(null, "Error al actualizar la cantidad");
                        }

                    } else {
                        JOptionPane.showMessageDialog(null, "La cantidad ingresada es igual a la actual");
                    }
                }

            } else {
                JOptionPane.showMessageDialog(null, "Seleccione un producto");
            }

        } else if (e.getSource() == addItem.btn_contrato) {

            Date fecha = addItem.jdate_fechaDevolu.getDate();
            if (fecha != null) { 

                Instant instante = fecha.toInstant(); 
                LocalDate fechaDevolucion = instante.atZone(ZoneId.systemDefault()).toLocalDate();

                LocalDate fechaActual = LocalDate.now();

                if (fechaDevolucion.isBefore(fechaActual)) {
                    JOptionPane.showMessageDialog(null, "La fecha de entrega debe ser posterior a la fecha actual");
                } else {
                    ContratoAlquiler contrato = new ContratoAlquiler();

                    contrato.setFecha_entrega(fechaDevolucion);
                    Cliente cliente = conCli.getDatosCli(addItem.lbl_correo.getText());
                    System.out.println(cliente.toString());
                    contrato.setCliente(cliente);
                    contrato.setCantidadTotal(Integer.parseInt(addItem.txt_cantidad.getText()));
                    contrato.setCostolTotal(Float.parseFloat(addItem.txt_precioTotal.getText()));

                    if (alquiDAO.terminarContrato(contrato) == 1) {
                        System.out.println("Exito");
                        addItem.dispose();
                    } else {
                        System.out.println("Ocurrio un error");
                    }
                }
            } else {
                JOptionPane.showMessageDialog(null, "Debe seleccionar una fecha de entrega");
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

    private void Totales() {

        DefaultTableModel modelo = (DefaultTableModel) addItem.table_selected.getModel();
        int columna = modelo.getRowCount();

        float precioTotal = 0.0f;
        int cantidadTotal = 0;

        for (int i = 0; i < columna; i++) {

            float precioAlquiler = Float.parseFloat(modelo.getValueAt(i, 2).toString());
            int cantidadAlquiler = Integer.parseInt(modelo.getValueAt(i, 1).toString());

            precioTotal += precioAlquiler;
            cantidadTotal += cantidadAlquiler;

        }

        //Actualizamos los text field
        addItem.txt_cantidad.setText(String.valueOf(cantidadTotal));
        addItem.txt_precioTotal.setText(String.valueOf(precioTotal));

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
