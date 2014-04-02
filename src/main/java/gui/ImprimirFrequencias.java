/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import eventos.GerarRelatorioTodosFuncionariosHandler;
import eventos.VoltarRelatorioGeralHandler;

import javax.swing.JOptionPane;

import service.UtilDatas;

import javax.swing.JButton;

/**
 *
 * @author Gilmar
 */
public class ImprimirFrequencias extends javax.swing.JFrame {

    /**
     * Creates new form ImprimirFrequencias
     */
    
    public String getData(){
        return jTextField1.getText();
    }
    
    public ImprimirFrequencias() {
    
        
        initComponents();
        setLocationRelativeTo(null);
//        System.out.println("jTextField1.getText()" + jTextField1.getText());
        GerarRelatorioTodosFuncionariosHandler gerarRelatorioTodosFuncionariosHandler = new GerarRelatorioTodosFuncionariosHandler(this);
        jButton1.addActionListener(gerarRelatorioTodosFuncionariosHandler);
        VoltarRelatorioGeralHandler voltarRelatorioGeralHandler = new VoltarRelatorioGeralHandler(this);
        btnVoltar.addActionListener(voltarRelatorioGeralHandler);
        
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
        jTextField1 = new javax.swing.JTextField();
        try{ 
            javax.swing.text.MaskFormatter data = new javax.swing.text.MaskFormatter("##/####"); 
            jTextField1 = new javax.swing.JFormattedTextField(data); 
        } 
        catch (Exception e){ 
        } 
        jLabel4 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        btnVoltar = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMaximumSize(new java.awt.Dimension(1035, 690));
        setMinimumSize(new java.awt.Dimension(1035, 690));
        setPreferredSize(new java.awt.Dimension(1035, 690));
        setResizable(false);
        getContentPane().setLayout(null);

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gui/images/tarjaGovernoAzul.png"))); // NOI18N
        getContentPane().add(jLabel2);
        jLabel2.setBounds(0, -2, 1100, 30);
        getContentPane().add(jTextField1);
        jTextField1.setBounds(466, 340, 90, 20);

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(102, 102, 102));
        jLabel4.setText("Data: ");
        getContentPane().add(jLabel4);
        jLabel4.setBounds(416, 342, 40, 15);
        
//        JButton btnVoltar = new JButton("Voltar");
        btnVoltar.setText("Voltar");
        btnVoltar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gui/images/back.png")));
        btnVoltar.setBounds(379, 405, 89, 23);
        getContentPane().add(btnVoltar);

        jButton1.setText("Gerar Relatórios");
        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gui/images/ok.png")));
        getContentPane().add(jButton1);
        jButton1.setBounds(506, 405, 119, 23);
        
        jLabel5.setBorder(javax.swing.BorderFactory.createTitledBorder("Emissão de Relatórios"));
        getContentPane().add(jLabel5);
        jLabel5.setBounds(354, 290, 300, 206);

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gui/images/backgroundBlueClaro3.jpg"))); // NOI18N
        getContentPane().add(jLabel3);
        jLabel3.setBounds(130, 160, 770, 430);

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gui/images/backgroundBlue.jpg"))); // NOI18N
        jLabel1.setText("jLabel1");
        jLabel1.setMinimumSize(new java.awt.Dimension(1024, 700));
        getContentPane().add(jLabel1);
        jLabel1.setBounds(0, 0, 1060, 720);

        pack();
    }// </editor-fold>//GEN-END:initComponents

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
            java.util.logging.Logger.getLogger(ImprimirFrequencias.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ImprimirFrequencias.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ImprimirFrequencias.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ImprimirFrequencias.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ImprimirFrequencias().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton btnVoltar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JTextField jTextField1;
}
