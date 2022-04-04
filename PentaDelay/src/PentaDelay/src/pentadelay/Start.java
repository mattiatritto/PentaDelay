/*
 * Questa classe contiene il metodo main che viene eseguito
 * quando viene lanciato l'applicativo.
 */

package pentadelay;

import java.io.IOException;
import javax.swing.JOptionPane;

/**
 *
 * @author Mattia Tritto
 */


public class Start {
    
    public static void main(String args[]) throws InterruptedException {
        
        /*SerialCommunicationArduino sp = new SerialCommunicationArduino();
        
        try {
            sp.openPort();
            sp.sendData(LedStatus.NOT_READY);
            sp.closePort();
        } catch (IOException | InterruptedException ex) {
            JOptionPane.showMessageDialog(null, "Collegare l'arduino alla macchina prima di continuare.", "Errore", JOptionPane.ERROR_MESSAGE);
            return;
        }*/
        
        JFPresentation presentazione = new JFPresentation();
        presentazione.setVisible(true);
        Thread.sleep(3000);
        presentazione.setVisible(false);
        
        JFChoise scelta = new JFChoise();
        scelta.setVisible(true);
    } 
}