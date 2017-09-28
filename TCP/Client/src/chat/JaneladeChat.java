package chat;

import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.*;
import javax.swing.JOptionPane;
import static javax.swing.JOptionPane.*;

public class JaneladeChat extends javax.swing.JFrame {
    
    private String nome;
    private Socket s;
    private BufferedReader br;
    private InputStreamReader isr;
    
    //Construtor
    public JaneladeChat(String nome, String ip, int porta) {
        
        initComponents();
        
        this.nome = nome;
        
        try{
            s = new Socket(ip, porta);
        } catch (Exception e){
            JOptionPane.showMessageDialog(rootPane, "Não se conectou ao servidor!", "Alerta!", 0);
        }
        
        Thread();
    }
    
    private void Thread(){
        Thread t = new Thread(new Runnable() {
            
            String msg;
            
            @Override
            public void run() {
                
                try{
                    isr = new InputStreamReader(s.getInputStream());
                    br = new BufferedReader(isr);
                    
                    while ((msg = br.readLine()) != null){
                        jTextAreaVisor.setText(jTextAreaVisor.getText() + msg + "\n");
                    }
                } catch (IOException e){
                    
                    showMessageDialog(rootPane, "Erro na conexão com o servidor!", "Erro", 0);
                    
                }
                
            }
        });
        
        t.start();
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTextAreaVisor = new javax.swing.JTextArea();
        jButtonEnviar = new javax.swing.JButton();
        jTextFieldEscritor = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jTextAreaVisor.setEditable(false);
        jTextAreaVisor.setColumns(20);
        jTextAreaVisor.setRows(5);
        jScrollPane1.setViewportView(jTextAreaVisor);

        jButtonEnviar.setText("Enviar");
        jButtonEnviar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonEnviarActionPerformed(evt);
            }
        });

        jTextFieldEscritor.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextFieldEscritorKeyPressed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jTextFieldEscritor, javax.swing.GroupLayout.PREFERRED_SIZE, 369, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButtonEnviar, javax.swing.GroupLayout.DEFAULT_SIZE, 106, Short.MAX_VALUE))
                    .addComponent(jScrollPane1))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 238, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jTextFieldEscritor)
                    .addComponent(jButtonEnviar, javax.swing.GroupLayout.DEFAULT_SIZE, 34, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents
    
    private void jButtonEnviarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonEnviarActionPerformed
        
        String msg = nome + ": ";
        
        try{
            
            PrintStream ps = new PrintStream(s.getOutputStream());
            msg += jTextFieldEscritor.getText() + "\n";
            
            ps.println(msg);
            ps.flush();
            
            jTextFieldEscritor.setText("");
            jTextFieldEscritor.requestFocus();
            
        } catch (IOException e){
            JOptionPane.showMessageDialog(rootPane, "Não conseguiu enviar a mensagem!", "Alerta!", 0);
        }
    }//GEN-LAST:event_jButtonEnviarActionPerformed

    private void jTextFieldEscritorKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldEscritorKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER){
            String msg = nome + ": ";
        
            try{

                PrintStream ps = new PrintStream(s.getOutputStream());
                msg += jTextFieldEscritor.getText() + "\n";

                ps.println(msg);
                ps.flush();

                jTextFieldEscritor.setText("");
                jTextFieldEscritor.requestFocus();

            } catch (IOException er){
                JOptionPane.showMessageDialog(rootPane, "Não conseguiu enviar a mensagem!", "Alerta!", 0);
            }
        }
    }//GEN-LAST:event_jTextFieldEscritorKeyPressed
    
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonEnviar;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea jTextAreaVisor;
    private javax.swing.JTextField jTextFieldEscritor;
    // End of variables declaration//GEN-END:variables
}
