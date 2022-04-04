package pentadelay;

/**
 * Questa classe permette di interfacciarsi con un database Access.
 */



import java.sql.*;



public class ConnessioneDB {
    
    /*Attributi*/
    
    private Connection conn;
    
    
    
    /**
     * Instaura una connessione con il database.
     * 
     * @param urlToDB
     * @throws ClassNotFoundException
     * @throws SQLException 
     */
    
    public ConnessioneDB(String urlToDB) throws ClassNotFoundException, SQLException{
        String driver="net.ucanaccess.jdbc.UcanaccessDriver";
        Class.forName(driver);
        conn = DriverManager.getConnection("jdbc:ucanaccess://"+urlToDB);
    }
    
    /**
     * Invia una query (passata come parametro) al database e 
     * restituisce i risultati con un array di stringhe.
     * 
     * @param sql
     * @return
     * @throws SQLException 
     */
    
    public String[] insertQueryDB(String sql) throws SQLException{
        
        String[] risQuery = new String[12];
        
        Statement stm = conn.createStatement();
        ResultSet rs = stm.executeQuery(sql);
        
        while (rs.next())
            for (int i = 0; i < 12; i++)
                risQuery[i] = rs.getString(i+1);
        
        rs.close();
        stm.close();
        return risQuery;
    }
    
    /**
     * Invia una query (passata come parametro) al database di tipo
     * UPDATE restituendo il numero di righe coinvolte nell'operazione.
     * 
     * @param sql
     * @return
     * @throws SQLException 
     */
    
    public int updateQueryDB(String sql) throws SQLException{
        
        int affectedRows = 0;
        
        Statement stm = conn.createStatement();
        affectedRows = stm.executeUpdate(sql);
        
        return affectedRows;
    }
    
    /**
     * Chiude la connessione instaurata.
     * 
     * @throws SQLException 
     */
    
    public void closeDB() throws SQLException{
        conn.close();
    }
}