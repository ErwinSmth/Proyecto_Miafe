/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JInternalFrame.java to edit this template
 */
package Vista;

/**
 *
 * @author Roberth
 */
public class Gestion_Clientes extends javax.swing.JInternalFrame {

    /**
     * Creates new form Gestion_Clientes
     */
    public Gestion_Clientes() {
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
        tabla_clINAC = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        btn_obtener = new javax.swing.JButton();
        btn_actualizar = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        txt_priN_cli = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txt_segN_cli = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        txt_apeP_cli = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        txt_apeM_cli = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        cbo_tiDoc = new javax.swing.JComboBox<>();
        jLabel7 = new javax.swing.JLabel();
        txt_numD_cli = new javax.swing.JTextField();
        btn_editar = new javax.swing.JButton();
        btn_limpiar = new javax.swing.JButton();
        jLabel8 = new javax.swing.JLabel();
        txt_correo = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        txa_direccion = new javax.swing.JTextArea();
        jLabel10 = new javax.swing.JLabel();
        txt_telefo = new javax.swing.JTextField();
        btn_eliminar = new javax.swing.JButton();
        btn_reactivar = new javax.swing.JButton();
        jLabel11 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jScrollPane3 = new javax.swing.JScrollPane();
        tabla_cli1 = new javax.swing.JTable();
        jLabel12 = new javax.swing.JLabel();

        setClosable(true);
        setResizable(true);
        setMinimumSize(new java.awt.Dimension(997, 684));
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        tabla_clINAC.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        tabla_clINAC.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jScrollPane1.setViewportView(tabla_clINAC);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 650, 1180, 280));

        jLabel1.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel1.setText("Clientes Registrados");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 20, -1, -1));

        btn_obtener.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        btn_obtener.setText("Obtener");
        getContentPane().add(btn_obtener, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 330, -1, -1));

        btn_actualizar.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        btn_actualizar.setText("Actualizar");
        getContentPane().add(btn_actualizar, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 330, -1, -1));

        jLabel2.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel2.setText("Primer Nombre");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 380, -1, -1));

        txt_priN_cli.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        txt_priN_cli.setMinimumSize(new java.awt.Dimension(140, 22));
        txt_priN_cli.setPreferredSize(new java.awt.Dimension(140, 22));
        getContentPane().add(txt_priN_cli, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 380, 140, -1));

        jLabel3.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel3.setText("Segundo Nombre");
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 430, -1, -1));

        txt_segN_cli.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        txt_segN_cli.setMinimumSize(new java.awt.Dimension(140, 22));
        txt_segN_cli.setPreferredSize(new java.awt.Dimension(140, 22));
        getContentPane().add(txt_segN_cli, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 430, 140, -1));

        jLabel5.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel5.setText("Apellido Paterno");
        getContentPane().add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 430, -1, -1));

        txt_apeP_cli.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        txt_apeP_cli.setMinimumSize(new java.awt.Dimension(140, 22));
        txt_apeP_cli.setPreferredSize(new java.awt.Dimension(140, 22));
        getContentPane().add(txt_apeP_cli, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 430, 140, -1));

        jLabel6.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel6.setText("Apellido Materno");
        getContentPane().add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 380, -1, -1));

        txt_apeM_cli.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        txt_apeM_cli.setMinimumSize(new java.awt.Dimension(140, 22));
        txt_apeM_cli.setPreferredSize(new java.awt.Dimension(140, 22));
        getContentPane().add(txt_apeM_cli, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 380, 140, -1));

        jLabel4.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel4.setText("Tipo de Documento");
        getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 390, -1, 20));

        cbo_tiDoc.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        cbo_tiDoc.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "DNI", "Pasaporte", "Ced_Identidad", "Carne_Extrangeria" }));
        getContentPane().add(cbo_tiDoc, new org.netbeans.lib.awtextra.AbsoluteConstraints(930, 380, -1, -1));

        jLabel7.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel7.setText("Num. Doc");
        jLabel7.setToolTipText("");
        getContentPane().add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(780, 430, -1, 20));

        txt_numD_cli.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        txt_numD_cli.setMinimumSize(new java.awt.Dimension(140, 22));
        txt_numD_cli.setPreferredSize(new java.awt.Dimension(140, 22));
        getContentPane().add(txt_numD_cli, new org.netbeans.lib.awtextra.AbsoluteConstraints(930, 430, 140, -1));

        btn_editar.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        btn_editar.setText("Editar");
        getContentPane().add(btn_editar, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 580, -1, -1));

        btn_limpiar.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        btn_limpiar.setText("Limpiar");
        getContentPane().add(btn_limpiar, new org.netbeans.lib.awtextra.AbsoluteConstraints(740, 330, -1, -1));

        jLabel8.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel8.setText("Correo");
        getContentPane().add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 480, 90, -1));

        txt_correo.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        txt_correo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_correoActionPerformed(evt);
            }
        });
        getContentPane().add(txt_correo, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 480, 140, -1));

        jLabel9.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel9.setText("Direccion");
        getContentPane().add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 480, 100, -1));

        txa_direccion.setColumns(20);
        txa_direccion.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        txa_direccion.setRows(5);
        jScrollPane2.setViewportView(txa_direccion);

        getContentPane().add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 470, -1, -1));

        jLabel10.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel10.setText("Telefono");
        getContentPane().add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(770, 490, 100, -1));

        txt_telefo.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        getContentPane().add(txt_telefo, new org.netbeans.lib.awtextra.AbsoluteConstraints(890, 490, 140, -1));

        btn_eliminar.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        btn_eliminar.setText("Eliminar");
        getContentPane().add(btn_eliminar, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 580, -1, -1));

        btn_reactivar.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        btn_reactivar.setText("Reactivar");
        getContentPane().add(btn_reactivar, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 580, -1, -1));

        jLabel11.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel11.setText("ACTIVOS");
        getContentPane().add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 20, -1, -1));
        getContentPane().add(jSeparator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 620, 1230, 10));

        tabla_cli1.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        tabla_cli1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jScrollPane3.setViewportView(tabla_cli1);

        getContentPane().add(jScrollPane3, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 50, 1160, 280));

        jLabel12.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel12.setText("INACTIVOS");
        getContentPane().add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 630, -1, -1));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txt_correoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_correoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_correoActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JButton btn_actualizar;
    public javax.swing.JButton btn_editar;
    public javax.swing.JButton btn_eliminar;
    public javax.swing.JButton btn_limpiar;
    public javax.swing.JButton btn_obtener;
    public javax.swing.JButton btn_reactivar;
    public javax.swing.JComboBox<String> cbo_tiDoc;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JSeparator jSeparator1;
    public javax.swing.JTable tabla_clINAC;
    public javax.swing.JTable tabla_cli1;
    public javax.swing.JTextArea txa_direccion;
    public javax.swing.JTextField txt_apeM_cli;
    public javax.swing.JTextField txt_apeP_cli;
    public javax.swing.JTextField txt_correo;
    public javax.swing.JTextField txt_numD_cli;
    public javax.swing.JTextField txt_priN_cli;
    public javax.swing.JTextField txt_segN_cli;
    public javax.swing.JTextField txt_telefo;
    // End of variables declaration//GEN-END:variables
}
