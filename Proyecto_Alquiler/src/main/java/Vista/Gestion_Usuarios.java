/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JInternalFrame.java to edit this template
 */
package Vista;

/**
 *
 * @author DAVID
 */
public class Gestion_Usuarios extends javax.swing.JInternalFrame {

    /**
     * Creates new form Gestion_Usuarios
     */
    public Gestion_Usuarios() {
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

        jLabel2 = new javax.swing.JLabel();
        txt_priN_us = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txt_segN_us = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        txt_apeM_us = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        txt_apeP_us = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        cbo_tiDoc = new javax.swing.JComboBox<>();
        jLabel7 = new javax.swing.JLabel();
        txt_numD_us = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        txt_contra = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        cbo_Rol = new javax.swing.JComboBox<>();
        btn_registrarUS = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabla_us = new javax.swing.JTable();
        btn_editar = new javax.swing.JButton();
        btn_obtener = new javax.swing.JButton();
        btn_limpiar = new javax.swing.JButton();
        btn_eliminar = new javax.swing.JButton();
        btn_actualizar = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();

        setBackground(new java.awt.Color(251, 233, 225));
        setClosable(true);
        setResizable(true);
        setMaximumSize(new java.awt.Dimension(1100, 650));
        setMinimumSize(new java.awt.Dimension(1100, 650));
        setOpaque(true);
        setPreferredSize(new java.awt.Dimension(1100, 650));
        getContentPane().setLayout(null);

        jLabel2.setFont(new java.awt.Font("Arial", 2, 12)); // NOI18N
        jLabel2.setText("Primer Nombre");
        getContentPane().add(jLabel2);
        jLabel2.setBounds(40, 410, 90, 22);

        txt_priN_us.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        txt_priN_us.setMaximumSize(new java.awt.Dimension(140, 22));
        txt_priN_us.setMinimumSize(new java.awt.Dimension(140, 22));
        txt_priN_us.setPreferredSize(new java.awt.Dimension(140, 22));
        getContentPane().add(txt_priN_us);
        txt_priN_us.setBounds(150, 410, 140, 22);

        jLabel3.setFont(new java.awt.Font("Arial", 2, 12)); // NOI18N
        jLabel3.setText("Segundo Nombre");
        getContentPane().add(jLabel3);
        jLabel3.setBounds(40, 460, 99, 22);

        txt_segN_us.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        txt_segN_us.setMaximumSize(new java.awt.Dimension(140, 22));
        txt_segN_us.setMinimumSize(new java.awt.Dimension(140, 22));
        txt_segN_us.setPreferredSize(new java.awt.Dimension(140, 22));
        getContentPane().add(txt_segN_us);
        txt_segN_us.setBounds(150, 460, 140, 22);

        jLabel6.setFont(new java.awt.Font("Arial", 2, 12)); // NOI18N
        jLabel6.setText("Apellido Materno");
        getContentPane().add(jLabel6);
        jLabel6.setBounds(350, 410, 92, 22);

        txt_apeM_us.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        txt_apeM_us.setMaximumSize(new java.awt.Dimension(140, 22));
        txt_apeM_us.setMinimumSize(new java.awt.Dimension(140, 22));
        txt_apeM_us.setPreferredSize(new java.awt.Dimension(140, 22));
        txt_apeM_us.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_apeM_usActionPerformed(evt);
            }
        });
        getContentPane().add(txt_apeM_us);
        txt_apeM_us.setBounds(470, 410, 140, 22);

        jLabel5.setFont(new java.awt.Font("Arial", 2, 12)); // NOI18N
        jLabel5.setText("Apellido Paterno");
        getContentPane().add(jLabel5);
        jLabel5.setBounds(350, 460, 91, 22);

        txt_apeP_us.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        txt_apeP_us.setMaximumSize(new java.awt.Dimension(140, 22));
        txt_apeP_us.setMinimumSize(new java.awt.Dimension(140, 22));
        txt_apeP_us.setPreferredSize(new java.awt.Dimension(140, 22));
        getContentPane().add(txt_apeP_us);
        txt_apeP_us.setBounds(470, 460, 140, 22);

        jLabel4.setFont(new java.awt.Font("Arial", 2, 12)); // NOI18N
        jLabel4.setText("Tipo de Documento");
        getContentPane().add(jLabel4);
        jLabel4.setBounds(640, 410, 108, 22);

        cbo_tiDoc.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "DNI", "Pasaporte", "Ced_Identidad", "Carne_Extrangeria", " " }));
        getContentPane().add(cbo_tiDoc);
        cbo_tiDoc.setBounds(770, 410, 129, 30);

        jLabel7.setFont(new java.awt.Font("Arial", 2, 12)); // NOI18N
        jLabel7.setText("Num. Doc");
        jLabel7.setToolTipText("");
        getContentPane().add(jLabel7);
        jLabel7.setBounds(350, 510, 70, 22);

        txt_numD_us.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        txt_numD_us.setMaximumSize(new java.awt.Dimension(140, 22));
        txt_numD_us.setMinimumSize(new java.awt.Dimension(140, 22));
        txt_numD_us.setPreferredSize(new java.awt.Dimension(140, 22));
        getContentPane().add(txt_numD_us);
        txt_numD_us.setBounds(470, 510, 140, 22);

        jLabel8.setFont(new java.awt.Font("Arial", 2, 12)); // NOI18N
        jLabel8.setText("Contraseña");
        getContentPane().add(jLabel8);
        jLabel8.setBounds(40, 510, 64, 22);

        txt_contra.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        txt_contra.setMaximumSize(new java.awt.Dimension(140, 22));
        txt_contra.setMinimumSize(new java.awt.Dimension(140, 22));
        txt_contra.setPreferredSize(new java.awt.Dimension(140, 22));
        getContentPane().add(txt_contra);
        txt_contra.setBounds(150, 510, 140, 22);

        jLabel9.setFont(new java.awt.Font("Arial", 2, 12)); // NOI18N
        jLabel9.setText("Rol de Trabajo");
        getContentPane().add(jLabel9);
        jLabel9.setBounds(640, 460, 90, 22);

        cbo_Rol.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Administrador", "Caja" }));
        getContentPane().add(cbo_Rol);
        cbo_Rol.setBounds(770, 460, 130, 30);

        btn_registrarUS.setFont(new java.awt.Font("Segoe UI Semibold", 2, 14)); // NOI18N
        btn_registrarUS.setText("Registrar");
        getContentPane().add(btn_registrarUS);
        btn_registrarUS.setBounds(650, 520, 100, 26);

        tabla_us.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        tabla_us.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jScrollPane1.setViewportView(tabla_us);

        getContentPane().add(jScrollPane1);
        jScrollPane1.setBounds(40, 70, 990, 250);

        btn_editar.setFont(new java.awt.Font("Segoe UI Semibold", 2, 14)); // NOI18N
        btn_editar.setText("Editar");
        getContentPane().add(btn_editar);
        btn_editar.setBounds(810, 520, 86, 26);

        btn_obtener.setFont(new java.awt.Font("Segoe UI Semibold", 2, 14)); // NOI18N
        btn_obtener.setText("Obtener");
        getContentPane().add(btn_obtener);
        btn_obtener.setBounds(40, 340, 86, 26);

        btn_limpiar.setFont(new java.awt.Font("Segoe UI Semibold", 2, 14)); // NOI18N
        btn_limpiar.setText("Limpiar");
        getContentPane().add(btn_limpiar);
        btn_limpiar.setBounds(160, 340, 86, 26);

        btn_eliminar.setFont(new java.awt.Font("Segoe UI Semibold", 2, 14)); // NOI18N
        btn_eliminar.setText("Eliminar");
        getContentPane().add(btn_eliminar);
        btn_eliminar.setBounds(280, 340, 86, 26);

        btn_actualizar.setFont(new java.awt.Font("Segoe UI Semibold", 2, 14)); // NOI18N
        btn_actualizar.setText("Actualizar");
        getContentPane().add(btn_actualizar);
        btn_actualizar.setBounds(930, 340, 100, 26);

        jLabel1.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel1.setText("Usuarios Activos");
        getContentPane().add(jLabel1);
        jLabel1.setBounds(460, 30, 150, 20);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txt_apeM_usActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_apeM_usActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_apeM_usActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JButton btn_actualizar;
    public javax.swing.JButton btn_editar;
    public javax.swing.JButton btn_eliminar;
    public javax.swing.JButton btn_limpiar;
    public javax.swing.JButton btn_obtener;
    public javax.swing.JButton btn_registrarUS;
    public javax.swing.JComboBox<String> cbo_Rol;
    public javax.swing.JComboBox<String> cbo_tiDoc;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    public javax.swing.JTable tabla_us;
    public javax.swing.JTextField txt_apeM_us;
    public javax.swing.JTextField txt_apeP_us;
    public javax.swing.JTextField txt_contra;
    public javax.swing.JTextField txt_numD_us;
    public javax.swing.JTextField txt_priN_us;
    public javax.swing.JTextField txt_segN_us;
    // End of variables declaration//GEN-END:variables
}
