/*
 * Questa interfaccia serve a definire i due stati di accensione
 * e spegnimento dei led.
 */

package pentadelay;

/**
 *
 * @author Mattia Tritto
 */

public interface LedStatus {
    public int READY = 1;
    public int NOT_READY = 0;
}