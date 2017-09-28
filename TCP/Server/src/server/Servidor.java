package server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.*;
import java.util.ArrayList;
import javax.swing.JOptionPane;

public class Servidor {

    public static void main(String[] args) {
        
        ArrayList<PrintStream> clientes = new ArrayList<PrintStream>();
        
        try{
            ServerSocket server = new ServerSocket(5000);
            Socket socket;
            
            while(true){
                socket = server.accept();
                clientes.add(new PrintStream(socket.getOutputStream())); //Guarda o endereço do cliente
                
                Mensagem mensagem  = new Mensagem(socket,clientes);
                
            }
            
        } catch (IOException e){
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }
    
}
