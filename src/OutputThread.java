
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.Socket;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Admin
 */
public class OutputThread extends Thread{
    Socket socket;
    JTextArea txt;
    BufferedReader br;
    String sender;
    String receiver;
    
    public OutputThread(Socket s, JTextArea txt, String sender, String receiver) {
        super();
        this.socket = s;
        this.txt = txt;
        this.sender = sender;
        this.receiver = receiver;
        try {
            br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Network error");
            System.exit(0);
        }
    }
    
    @Override
    public void run(){
        while(true) {
            try {
                if (socket != null) {
                    String msg = "";
                    if ((msg = br.readLine()) != null && msg.length() > 0) 
                        txt.append("\n +" + receiver + ": " + msg);
                }
            } catch (Exception e) {
            }
        }
    }
}

