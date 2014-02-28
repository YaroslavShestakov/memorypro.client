package memorypro;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

/**
 *
 * @author 
 */
public class Server {
  private static final String base = "http://koti.tamk.fi/~c2yshest/mp/api/" ;
//    private static final String base = "http://localhost/mp/api/" ;
       
  
    public static URLConnection getConnection(String request){
        try {
            URLConnection connection = new URL(base + request).openConnection();
            connection.connect();
            return connection ;
        } catch (Exception e){
            e.printStackTrace();
        }
        return null ;
    }
    
    /**
     * Returns the response from database in form of a json object.
     * @param connection The connection to the database.
     * @return String of database response in json form.
     */
    public static String getResponse(URLConnection connection){
        try {
            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                String response = "";
                String inputLine;

                while ((inputLine = in.readLine()) != null) {
                    response += inputLine ;
                }

                in.close();
             return response ;
        } catch (Exception e){
            
        }
        return null ;
    }
}
