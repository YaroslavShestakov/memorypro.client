package memorypro.utils;

/**
 *
 * @author 
 */
public class Validator {
    public static boolean validateEmail(String email){
        return (email.length() >= 4) ; // During tests
    }
    
    public static boolean validateFName(String fname){
        return (fname.length() >= 3) ;
    }
    
    public static boolean validatePassword(String pass){
        return (pass.length() >= 8);
    }
    
    
}
