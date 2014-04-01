/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import eventos.AlterarUsuarioHandler;
import eventos.ButtonHandlerCancelarCadastroUsuario;
import eventos.CadastrarUsuarioHandler;
import eventos.PegaValorCheckBoxButtonHandler;
import java.text.ParseException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JCheckBox;
import javax.swing.JOptionPane;
import negocio.UsuarioLogado;
import service.relatorios.UtilDatas;

/**
 *
 * @author Gilmar
 */
public class NovoUsuario extends javax.swing.JFrame {
    

    /**
     * Creates new form NovoUsuario
     */
    public NovoUsuario(String[] valores) throws ParseException {
          initComponents();
          JOptionPane.setRootFrame(this);
          setLocationRelativeTo(null);
                  
          jLabel15.setText("Bem vindo, " + UsuarioLogado.getInstancia().getUsuarioLogado().getNome().toUpperCase());

          ButtonHandlerCancelarCadastroUsuario buttonHandlerCancelarCadastroUsuario = new ButtonHandlerCancelarCadastroUsuario(this);
          jButton2.addActionListener(buttonHandlerCancelarCadastroUsuario);
          if(valores == null){
          CadastrarUsuarioHandler cadastrarUsuarioHandler = new CadastrarUsuarioHandler(this);
//          PegaValorCheckBoxButtonHandler pegaValorCheckBoxButtonHandler = new PegaValorCheckBoxButtonHandler(this);
//          jButton1.addActionListener(pegaValorCheckBoxButtonHandler);
          jButton1.addActionListener(cadastrarUsuarioHandler);
          }
          if (valores != null) {
            AlterarUsuarioHandler alterarUsuarioHandler = new AlterarUsuarioHandler(this, valores[4]);
            jButton1.addActionListener(alterarUsuarioHandler);
            jTextField1.setText(valores[0]);
            jTextField2.setText(valores[2]);
            jTextField3.setText(valores[1]);
            jTextField4.setText(UtilDatas.DateToString(valores[5]));
            jFormattedTextField2.setText(valores[6]);
            if (valores[7].equals("true")) {
                jComboBox1.setSelectedIndex(1);
            } else {
                jComboBox1.setSelectedIndex(0);
            }
            jButton1.setText("Alterar");
            String[] split = valores[8].split("-");
            for(String dia : split){
                if(dia.equals("0")){
                    jCheckBox1.setSelected(true);
                }else if(dia.equals("1")){
                    jCheckBox2.setSelected(true);
                }else if(dia.equals("2")){
                    jCheckBox3.setSelected(true);
                }else if(dia.equals("3")){
                    jCheckBox4.setSelected(true);
                }else if(dia.equals("4")){
                    jCheckBox5.setSelected(true);
                }
            }
        }
          
          
          
    }
    
   public JCheckBox[] getJcheckboxes(){
       JCheckBox[] jcheckboxes = new JCheckBox[5];
       jcheckboxes[0] = jCheckBox1;
       jcheckboxes[1] = jCheckBox2;
       jcheckboxes[2] = jCheckBox3;
       jcheckboxes[3] = jCheckBox4;
       jcheckboxes[4] = jCheckBox5;
       
       return jcheckboxes;
   }
    
   public String getNome(){
       return jTextField1.getText();
   }
   
   public void setNomeTF(String nomeTF){
       jTextField1.setText(nomeTF);
   }
   
   public String getDataAdmissao(){
       return jTextField4.getText();
   }
   
   public Double getSalario(){
       return new Double(jFormattedTextField2.getText());
   }
   
   public String getPortaria(){
       return jTextField2.getText();
   }
   
   public String getlogin(){
       return jTextField3.getText();
   }
   
   public char[] getsenha(){
       return jPasswordField1.getPassword();
   }

   public Boolean getNivelAcesso(){
       String nivelAcesso = jComboBox1.getSelectedItem().toString();
       System.out.println(nivelAcesso);
       if(nivelAcesso.equals("Administrador")){
           return true;
       }else{
           return false;
       }
   }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel6 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jPasswordField1 = new javax.swing.JPasswordField();
        jFormattedTextField2 = new javax.swing.JFormattedTextField();
        jTextField2 = new javax.swing.JTextField();
        jTextField3 = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jTextField1 = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        jLabel13 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jTextField4 = new javax.swing.JTextField();
        try{ 
            javax.swing.text.MaskFormatter data = new javax.swing.text.MaskFormatter("##/##/####"); 
            jTextField4 = new javax.swing.JFormattedTextField(data); 
        } 
        catch (Exception e){ 
        } 
        jLabel12 = new javax.swing.JLabel();
        jCheckBox1 = new javax.swing.JCheckBox();
        jCheckBox2 = new javax.swing.JCheckBox();
        jCheckBox3 = new javax.swing.JCheckBox();
        jCheckBox4 = new javax.swing.JCheckBox();
        jCheckBox5 = new javax.swing.JCheckBox();
        jLabel8 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(1035, 690));
        setName("NovoUsuario"); // NOI18N
        setPreferredSize(new java.awt.Dimension(1035, 690));
        setResizable(false);
        getContentPane().setLayout(null);

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(153, 153, 153));
        jLabel6.setText("Salário:");
        getContentPane().add(jLabel6);
        jLabel6.setBounds(490, 250, 70, 30);

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(153, 153, 153));
        jLabel5.setText("Data de Admissão: ");
        getContentPane().add(jLabel5);
        jLabel5.setBounds(210, 250, 132, 14);

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(153, 153, 153));
        jLabel4.setText("Senha:");
        getContentPane().add(jLabel4);
        jLabel4.setBounds(210, 430, 60, 14);

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(153, 153, 153));
        jLabel3.setText("Usuário:");
        getContentPane().add(jLabel3);
        jLabel3.setBounds(210, 390, 70, 17);

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(153, 153, 153));
        jLabel2.setText("Nº da Portaria: ");
        getContentPane().add(jLabel2);
        jLabel2.setBounds(210, 290, 120, 20);
        getContentPane().add(jPasswordField1);
        jPasswordField1.setBounds(280, 420, 140, 25);

        jFormattedTextField2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jFormattedTextField2ActionPerformed(evt);
            }
        });
        getContentPane().add(jFormattedTextField2);
        jFormattedTextField2.setBounds(550, 250, 170, 27);
        getContentPane().add(jTextField2);
        jTextField2.setBounds(350, 290, 120, 27);

        jTextField3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField3ActionPerformed(evt);
            }
        });
        getContentPane().add(jTextField3);
        jTextField3.setBounds(280, 380, 140, 25);

        jButton1.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jButton1.setForeground(new java.awt.Color(102, 102, 102));
        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/ok.png"))); // NOI18N
        jButton1.setText("Cadastrar");
        getContentPane().add(jButton1);
        jButton1.setBounds(530, 475, 120, 30);
        getContentPane().add(jTextField1);
        jTextField1.setBounds(350, 210, 370, 27);

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(153, 153, 153));
        jLabel1.setText("Nome Completo:");
        getContentPane().add(jLabel1);
        jLabel1.setBounds(210, 210, 120, 30);

        jLabel10.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setText("Copyright © 2014 Virtua Softwares. Todos os direitos reservados.");
        getContentPane().add(jLabel10);
        jLabel10.setBounds(310, 630, 407, 15);

        jLabel11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/tarjaGovernoAzul.png"))); // NOI18N
        jLabel11.setText("jLabel11");
        getContentPane().add(jLabel11);
        jLabel11.setBounds(0, 0, 1030, 25);

        jButton2.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jButton2.setForeground(new java.awt.Color(102, 102, 102));
        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/cancel_10x10.png"))); // NOI18N
        jButton2.setText("Cancelar");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton2);
        jButton2.setBounds(350, 475, 120, 30);

        jLabel13.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(255, 255, 255));
        jLabel13.setText("Cadastro de Servidores");
        getContentPane().add(jLabel13);
        jLabel13.setBounds(380, 90, 220, 30);

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Servidor", "Administrador" }));
        jComboBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox1ActionPerformed(evt);
            }
        });
        getContentPane().add(jComboBox1);
        jComboBox1.setBounds(610, 280, 91, 20);

        jLabel14.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(153, 153, 153));
        jLabel14.setText("Nível de Acesso:");
        getContentPane().add(jLabel14);
        jLabel14.setBounds(490, 290, 111, 17);

        jLabel15.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(153, 153, 153));
        jLabel15.setText("Bem Vindo, ");
        getContentPane().add(jLabel15);
        jLabel15.setBounds(640, 160, 160, 15);
        getContentPane().add(jTextField4);
        jTextField4.setBounds(350, 250, 120, 27);

        jLabel12.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Dados Pessoais", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, null, java.awt.Color.darkGray));
        getContentPane().add(jLabel12);
        jLabel12.setBounds(180, 180, 640, 160);

        jCheckBox1.setText("Segunda-Feira");
        jCheckBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBox1ActionPerformed(evt);
            }
        });
        getContentPane().add(jCheckBox1);
        jCheckBox1.setBounds(490, 380, 120, 23);

        jCheckBox2.setText("Terça-Feira");
        getContentPane().add(jCheckBox2);
        jCheckBox2.setBounds(610, 380, 90, 23);

        jCheckBox3.setText("Quarta-Feira");
        getContentPane().add(jCheckBox3);
        jCheckBox3.setBounds(700, 380, 90, 23);

        jCheckBox4.setText("Quinta-Feira");
        getContentPane().add(jCheckBox4);
        jCheckBox4.setBounds(490, 410, 110, 23);

        jCheckBox5.setText("Sexta-Feira");
        jCheckBox5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBox5ActionPerformed(evt);
            }
        });
        getContentPane().add(jCheckBox5);
        jCheckBox5.setBounds(610, 410, 100, 23);

        jLabel8.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Login", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, null, new java.awt.Color(51, 51, 51)));
        getContentPane().add(jLabel8);
        jLabel8.setBounds(180, 350, 270, 120);

        jLabel17.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Expediente", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, null, java.awt.Color.black));
        getContentPane().add(jLabel17);
        jLabel17.setBounds(470, 350, 350, 120);

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/backgroundBlueCadastro.jpg"))); // NOI18N
        getContentPane().add(jLabel9);
        jLabel9.setBounds(150, 150, 710, 390);

        jLabel7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/backgroundBlue.jpg"))); // NOI18N
        jLabel7.setAutoscrolls(true);
        jLabel7.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jLabel7.setMaximumSize(new java.awt.Dimension(1024, 700));
        jLabel7.setMinimumSize(new java.awt.Dimension(1024, 700));
        getContentPane().add(jLabel7);
        jLabel7.setBounds(0, 0, 1050, 690);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jTextField3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField3ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jFormattedTextField2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jFormattedTextField2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jFormattedTextField2ActionPerformed

    private void jComboBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBox1ActionPerformed

    private void jCheckBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBox1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jCheckBox1ActionPerformed

    private void jCheckBox5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBox5ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jCheckBox5ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(final String args[]) {
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
            java.util.logging.Logger.getLogger(NovoUsuario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(NovoUsuario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(NovoUsuario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(NovoUsuario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    new NovoUsuario(args).setVisible(true);
                } catch (ParseException ex) {
                    Logger.getLogger(NovoUsuario.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JCheckBox jCheckBox1;
    private javax.swing.JCheckBox jCheckBox2;
    private javax.swing.JCheckBox jCheckBox3;
    private javax.swing.JCheckBox jCheckBox4;
    private javax.swing.JCheckBox jCheckBox5;
    private javax.swing.JComboBox jComboBox1;
    private javax.swing.JFormattedTextField jFormattedTextField2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPasswordField jPasswordField1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTextField jTextField4;
    // End of variables declaration//GEN-END:variables
}
