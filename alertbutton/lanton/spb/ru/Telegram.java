package alertbutton.lanton.spb.ru;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Класс для отправки сообщений в Телеграм
 *
 * @author antoxa
 */
public class Telegram {

    private static HttpURLConnection con;
    private static String token = ""; // токен бота
    private static String userId = ""; // id получателя
    private static String sendUrl = "https://api.telegram.org/bot" + token + "/sendMessage";

    public static void send() throws Exception {
        //текст сообщения
        String txt = "Alert!!!";
        // данные для передачи боту
        String urlParameters = "chat_id=" + userId + "&text=" + txt;
        byte[] postData = urlParameters.getBytes(StandardCharsets.UTF_8);
        
        // создаем подключение
        URL url = new URL(sendUrl);
        con = (HttpURLConnection) url.openConnection();
        con.setDoOutput(true);
        con.setRequestMethod("POST");
        con.setRequestProperty("User-Agent", "Ahtung alert!");
        con.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
        System.out.println("Try send message...");
        // передаем данные
        try (DataOutputStream io = new DataOutputStream(con.getOutputStream())) {
            io.write(postData);
            System.out.println("message sended. " + con.getResponseMessage());
        } catch (IOException ex) {
            Logger.getLogger(Processor.class.getName()).log(Level.SEVERE, null, ex);
        }   
        finally {       
            con.disconnect();
        }
        System.out.println("Well done!");
    }
}
