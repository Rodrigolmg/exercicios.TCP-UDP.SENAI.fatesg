package server;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.*;
import java.util.ArrayList;

public class Mensagem {
    private Socket s;
    private ArrayList<PrintStream> clientes;
    
    //Construtor
    public Mensagem(Socket s, ArrayList<PrintStream> clientes){
        this.s = s;
        this.clientes = clientes;
        Thread();
    }
    
    private void Thread(){
        
        Thread t = new Thread(new Runnable() {
            
            @Override
            public void run() {
                
                 String mensagem = "";
        
                try{
                    InputStreamReader isr = new InputStreamReader(s.getInputStream());
                    BufferedReader br = new BufferedReader(isr);

                    while((mensagem = br.readLine()) != null){
                        enviarMsg(mensagem);
                    }
                } catch (Exception er){
                    er.printStackTrace();
                }
            }
        });
        
        t.start();
    }
    
    private void enviarMsg(String mensagem){
        
        for (int i = 0; i < clientes.size(); i++) {
            
            clientes.get(i).println(mensagem);
            clientes.get(i).flush();
        }
        
    }
}
