/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Ventanas;

import LogicaDeBingo.JuegoEnX;
import LogicaDeBingo.JuegoEnZ;
import LogicaDeBingo.JuegoCuatroEsquinas;
import LogicaDeBingo.JuegoLleno;
import LogicaDeBingo.Configuracion;
import javax.swing.JFrame;
/**
 *
 * @author quiri
 */
public class MenuTipoDeJuego extends javax.swing.JFrame {
  public static Object tipoDeJuego;
  /**
   * Creates new form MenuTipoDeJuego
   */
  public MenuTipoDeJuego() {
    initComponents();
    jTextFieldPremio.setText("Introduzca el premio");
    jTextFieldPremio.setForeground(java.awt.Color.GRAY);

    jTextFieldPremio.addFocusListener(new java.awt.event.FocusAdapter() {
      @Override
      public void focusGained(java.awt.event.FocusEvent evt) {
        if (jTextFieldPremio.getText().equals("Introduzca el premio")) {
          jTextFieldPremio.setText("");
          jTextFieldPremio.setForeground(java.awt.Color.BLACK);
        }
      }

      @Override
      public void focusLost(java.awt.event.FocusEvent evt) {
        if (jTextFieldPremio.getText().isEmpty()) {
          jTextFieldPremio.setForeground(java.awt.Color.GRAY);
          jTextFieldPremio.setText("Introduzca el premio");
        }
      }
    });
    jComboBoxTipoDeJuego.setModel(new javax.swing.DefaultComboBoxModel<>(new String[]{"cartón lleno", "cuatro esquinas", "jugar en Z", "jugar en X"}));
    jComboBoxTipoDeJuego.setSelectedIndex(0); // Esto selecciona "cartón lleno"

  }

  /**
   * This method is called from within the constructor to initialize the form.
   * WARNING: Do NOT modify this code. The content of this method is always
   * regenerated by the Form Editor.
   */
  @SuppressWarnings("unchecked")
  // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
  private void initComponents() {

    jButtonSalir = new javax.swing.JButton();
    jButtonVolver = new javax.swing.JButton();
    jComboBoxTipoDeJuego = new javax.swing.JComboBox<>();
    jTextFieldPremio = new javax.swing.JTextField();
    jLabelTextPremio = new javax.swing.JLabel();
    jButtonInciar = new javax.swing.JButton();
    jLabelFondo = new javax.swing.JLabel();

    setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
    getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

    jButtonSalir.setBackground(new java.awt.Color(255, 0, 0));
    jButtonSalir.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
    jButtonSalir.setForeground(new java.awt.Color(255, 255, 255));
    jButtonSalir.setText("Salir");
    getContentPane().add(jButtonSalir, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 10, -1, -1));

    jButtonVolver.setBackground(new java.awt.Color(0, 153, 255));
    jButtonVolver.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
    jButtonVolver.setForeground(new java.awt.Color(255, 255, 255));
    jButtonVolver.setText("Volver");
    getContentPane().add(jButtonVolver, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, -1, -1));

    jComboBoxTipoDeJuego.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
    jComboBoxTipoDeJuego.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        jComboBoxTipoDeJuegoActionPerformed(evt);
      }
    });
    getContentPane().add(jComboBoxTipoDeJuego, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 220, 140, 30));

    jTextFieldPremio.setText("Introduzca el premio");
    getContentPane().add(jTextFieldPremio, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 160, 140, 30));

    jLabelTextPremio.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
    jLabelTextPremio.setText("Premio:");
    getContentPane().add(jLabelTextPremio, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 160, 80, 30));

    jButtonInciar.setBackground(new java.awt.Color(0, 153, 255));
    jButtonInciar.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
    jButtonInciar.setForeground(new java.awt.Color(255, 255, 255));
    jButtonInciar.setText("Iniciar");
    jButtonInciar.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        jButtonInciarActionPerformed(evt);
      }
    });
    getContentPane().add(jButtonInciar, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 350, -1, -1));

    jLabelFondo.setIcon(new javax.swing.ImageIcon("Imagenes\\fondo.jpg"));
    getContentPane().add(jLabelFondo, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 490, 407));

    pack();
  }// </editor-fold>//GEN-END:initComponents

  private void jButtonInciarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonInciarActionPerformed

    String tipoDeJuegoSeleccionado = (String) jComboBoxTipoDeJuego.getSelectedItem();

    if ("cartón lleno".equals(tipoDeJuegoSeleccionado)) {
        tipoDeJuego = new JuegoLleno();
    } else if ("cuatro esquinas".equals(tipoDeJuegoSeleccionado)) {
        tipoDeJuego = new JuegoCuatroEsquinas();
    } else if ("jugar en Z".equals(tipoDeJuegoSeleccionado)) {
        tipoDeJuego = new JuegoEnZ();
    } else if ("jugar en X".equals(tipoDeJuegoSeleccionado)) {
        tipoDeJuego = new JuegoEnX();
    }
    new VentanaJuego().setVisible(true);
    this.dispose();  // Cierra la ventana MenuInicio
  }//GEN-LAST:event_jButtonInciarActionPerformed

  private void jComboBoxTipoDeJuegoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBoxTipoDeJuegoActionPerformed
    // TODO add your handling code here:
  }//GEN-LAST:event_jComboBoxTipoDeJuegoActionPerformed

  /**
   * @param args the command line arguments
   */
  public static void main(String args[]) {
    /* Set the Nimbus look and feel */
    //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
    /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
     */
    try {
      for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
        if ("Nimbus".equals(info.getName())) {
          javax.swing.UIManager.setLookAndFeel(info.getClassName());
          break;
        }
      }
    } catch (ClassNotFoundException ex) {
      java.util.logging.Logger.getLogger(MenuTipoDeJuego.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
    } catch (InstantiationException ex) {
      java.util.logging.Logger.getLogger(MenuTipoDeJuego.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
    } catch (IllegalAccessException ex) {
      java.util.logging.Logger.getLogger(MenuTipoDeJuego.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
    } catch (javax.swing.UnsupportedLookAndFeelException ex) {
      java.util.logging.Logger.getLogger(MenuTipoDeJuego.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
    }
    //</editor-fold>

    /* Create and display the form */
    java.awt.EventQueue.invokeLater(new Runnable() {
      public void run() {
        new MenuTipoDeJuego().setVisible(true);
      }
    });
  }

  // Variables declaration - do not modify//GEN-BEGIN:variables
  private javax.swing.JButton jButtonInciar;
  private javax.swing.JButton jButtonSalir;
  private javax.swing.JButton jButtonVolver;
  private javax.swing.JComboBox<String> jComboBoxTipoDeJuego;
  private javax.swing.JLabel jLabelFondo;
  private javax.swing.JLabel jLabelTextPremio;
  private javax.swing.JTextField jTextFieldPremio;
  // End of variables declaration//GEN-END:variables
}
