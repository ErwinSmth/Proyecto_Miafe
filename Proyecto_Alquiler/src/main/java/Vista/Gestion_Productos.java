/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JInternalFrame.java to edit this template
 */
package Vista;

/**
 *
 * @author DAVID
 */
public class Gestion_Productos extends javax.swing.JInternalFrame {

    /**
     * Creates new form Gestion_Productos
     */
    public Gestion_Productos() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        table_produc = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        btn_eliminar = new javax.swing.JButton();
        btn_actualizar = new javax.swing.JButton();
        btn_precio = new javax.swing.JButton();
        btn_disponible = new javax.swing.JButton();

        setClosable(true);

        table_produc.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        table_produc.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jScrollPane1.setViewportView(table_produc);

        jLabel1.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel1.setText("Lista de Productos");

        btn_eliminar.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        btn_eliminar.setText("Eliminar");

        btn_actualizar.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        btn_actualizar.setText("Actualizar");

        btn_precio.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        btn_precio.setText("Editar Precio");

        btn_disponible.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        btn_disponible.setText("Agregar mas disponibles");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btn_eliminar)
                        .addGap(52, 52, 52)
                        .addComponent(btn_actualizar)
                        .addGap(46, 46, 46)
                        .addComponent(btn_precio)
                        .addGap(36, 36, 36)
                        .addComponent(btn_disponible))
                    .addComponent(jLabel1)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 880, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(37, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(11, 11, 11)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(40, 40, 40)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_eliminar)
                    .addComponent(btn_actualizar)
                    .addComponent(btn_precio)
                    .addComponent(btn_disponible))
                .addContainerGap(29, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JButton btn_actualizar;
    public javax.swing.JButton btn_disponible;
    public javax.swing.JButton btn_eliminar;
    public javax.swing.JButton btn_precio;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    public javax.swing.JTable table_produc;
    // End of variables declaration//GEN-END:variables
}
