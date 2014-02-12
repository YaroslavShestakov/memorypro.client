package memorypro;

import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author 
 */
public class ErrorHandler {
    private HashMap<String, ArrayList<Error>> actions  = new HashMap();
    
    public ArrayList getLastErrors(String action){
        return actions.get(action);
    }
    
    public void setLastError(String action, Error type){
        if (actions.containsKey(action)){
            ArrayList errors = actions.get(action);
            errors.add(this);
        } else {
            ArrayList errors = new ArrayList();
            errors.add(type);
            actions.put(action, errors);
        }
    }
    
    public void clearErrors(String action){
        actions.remove(action);
    }
}
