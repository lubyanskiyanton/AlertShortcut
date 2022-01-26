/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package alertbutton.lanton.spb.ru;

import java.applet.Applet;
import java.applet.AudioClip;
import java.awt.Toolkit;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author antoxa
 */
public class Processor implements Runnable {

    @Override
    public void run() {
        System.out.println("Run, run, run!!!");
        AudioClip clip = Applet.newAudioClip(getClass().getResource("/sound/alert.wav"));
        // выбираем единожды проиграть звук или зациклить
        clip.loop(); // цикл
        //clip.play(); // проиграть один раз
        
        // отправляем сообщение в Telegram Bot
        try {
            Telegram.send();
        } catch (Exception ex) {
            Logger.getLogger(Processor.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println("Message sended!");
    }
    
}
