package pentadelay;

/*
 * Questa classe definisce un'eccezione di tipo EmptyFormsException
 * lanciata quando non vengono compilati tutti i form necessari.
 */



/**
 *
 * @author Mattia Tritto
 */

public class EmptyFormsException extends PentaDelayException {

    /*Attributi*/
    
    private String msgError;
    
    
    
    /**
     * Costruttore senza parametri.
     */
    
    public EmptyFormsException() {msgError = "";}
    
    public EmptyFormsException(String msgError){this.msgError = msgError;}
    
    
    
    /**
     * Metodo che restituisce il messaggio di errore.
     * 
     * @return msgError
     */
    
    public String getError(){
        return msgError;
    }
}