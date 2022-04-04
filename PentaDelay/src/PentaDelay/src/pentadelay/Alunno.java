package pentadelay;

/*
 * Questa classe definisce un ADT di tipo alunno
 * necessario per la stampa del ritardo.
 */



/**
 *
 * @author Mattia Tritto
 */

public class Alunno {

    /*Attributi*/
    
    private final String name;
    private final String surname;
    private final String classe;
    private final String sezione;
    private final String data;
    private final String delays;
    private final String time;


    
    /**
     * Costruttore con parametri che inizializza i campi del report.
     * 
     * @param name
     * @param surname
     * @param classe
     * @param sezione 
     * @param data 
     * @param delays 
     * @param time 
     */
    
    public Alunno(String name, String surname, String classe, String sezione, String data, String delays, String time) {
            this.name = name;
            this.surname = surname;
            this.classe = classe;
            this.sezione = sezione;
            this.data = data;
            this.delays = delays;
            this.time = time;
    }

    
    
    /*Metodi getter*/
    
    public String getName() {
            return name;
    }

    public String getSurname(){
        return surname;
    }

    public String getClasse(){
        return classe;
    }

    public String getSezione(){
        return sezione;
    }
    
    public String getData(){
        return data;
    }
    
    public String getDelays(){
        return delays;
    }
    
    public String getTime(){
        return time;
    }
}