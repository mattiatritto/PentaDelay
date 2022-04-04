package pentadelay;

/*
 * Questa classe definisce un'eccezione di tipo WrongDataException
 * lanciata quando i dati inseriti nei form sono errati.
 */



/**
 *
 * @author Mattia Tritto
 */

public class WrongDataException extends PentaDelayException {

    /*Attributi*/
    
    private String msgError;
    
    
    
    /**
     * Costruttore senza parametri.
     */
    
    public WrongDataException() {msgError = "";}
    
    public WrongDataException(String msgError){this.msgError = msgError;}
    
    
    
    /**
     * Metodo che restituisce il messaggio di errore.
     * 
     * @return msgError
     */
    
    public String getError(){
        return msgError;
    }
}