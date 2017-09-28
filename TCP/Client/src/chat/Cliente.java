package chat;

import static javax.swing.JOptionPane.*;

public class Cliente {
    public static void main(String[] args) {
        //Instância
        String nome = showInputDialog(null, "Digite seu nome: ", "", PLAIN_MESSAGE);
        String ip = showInputDialog(null, "Digite o ip: ", "", PLAIN_MESSAGE);
        int porta = Integer.parseInt(showInputDialog(null, "Digite a porta: ", "", PLAIN_MESSAGE));
        JaneladeChat chat = new JaneladeChat(nome, ip, porta);
        chat.setVisible(true);
        
        
    }    
}
