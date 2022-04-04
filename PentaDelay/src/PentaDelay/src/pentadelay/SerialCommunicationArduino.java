/*
 * Questa classe permette di mandare byte sulla seriale
 * di Arduino.
 */

package pentadelay;

import com.fazecast.jSerialComm.SerialPort;
import java.io.IOException;

/**
 *
 * @author Mattia Tritto
 */

public class SerialCommunicationArduino {
    
    /*Attributi*/
    
    private SerialPort sp;
    
    
    
    /**
     * Costruttore senza parametri che setta tutti i parametri
     * della connessione seriale.
     */
    
    public SerialCommunicationArduino() {
        sp = SerialPort.getCommPort("COM3");
        sp.setComPortParameters(9600, 8, 1, 0);
        sp.setComPortTimeouts(SerialPort.TIMEOUT_WRITE_BLOCKING, 0, 0);
    }
    
    
    
    /**
     * Metodo che apre la connessione seriale.
     * 
     * @throws IOException
     * @throws InterruptedException 
     */
    
    public void openPort() throws IOException, InterruptedException{
        if (sp.openPort())
            Thread.sleep(2000);
        else
            throw new IOException();
    }
    
    /**
     * Metodo che invia un carattere sulla seriale.
     * 
     * @param data
     * @throws IOException 
     */
    
    public void sendData(int data) throws IOException{
        
        Integer value = data;
        sp.getOutputStream().write(value.byteValue());
        sp.getOutputStream().flush();
    }
    
    /**
     * Metodo che chiude la connessione seriale.
     * 
     * @throws IOException 
     */
    
    public void closePort() throws IOException{
        if (!sp.closePort())
            throw new IOException();
    }
}