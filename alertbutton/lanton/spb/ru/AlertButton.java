/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package alertbutton.lanton.spb.ru;

import java.net.ServerSocket;
import java.net.Socket;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.IOException;

/**
 *
 * @author antoxa
 */
public class AlertButton {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        ServerSocket ss = new ServerSocket(9999);
        System.out.println("Waiting for attack...");
        
        while (true) {
            Socket s = ss.accept();
            System.out.println("Connection from " + s.getInetAddress());

            // читаем из потока в поисках ключа безопасности
            BufferedReader in = new BufferedReader(new InputStreamReader(s.getInputStream()));
            String inputLine;
            int i = 0;
            try {
                while (!(inputLine = in.readLine()).equals("")) {
                    if (inputLine.indexOf("token=4321") != -1) { // если находим ключ безопасности в запросе, тогда запускаем сигнализирующий поток
                        new Thread(new Processor()).start();
                    }
                    if (i > 10) { // определяем количество итераций посика ключа безопасности. По идеи должен быть в первой строке, при использовоании http ярлыка.
                        break;
                    }
                    i++;
                }
            } catch (NullPointerException ex) {
                System.out.println("Empty request.");
            }
            in.close();
            s.close();
            System.out.println("Connection from " + s.getInetAddress() + " closed.");
        }
    }

}
