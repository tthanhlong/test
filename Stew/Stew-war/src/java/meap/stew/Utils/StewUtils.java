/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package meap.stew.Utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Properties;
import java.util.UUID;

/**
 *
 * @author tthanhlong
 */
public class StewUtils {
    private static StewUtils instance;
    //Log level
    public String logLevel;
    
    public static StewUtils getInstance() {
        if (instance == null) {
            instance = new StewUtils();
        }
        return instance;
    }
        
    private StewUtils(){
        Properties properties = new Properties();
        try {
            properties.load(this.getClass().getClassLoader().getResourceAsStream("stew.properties"));
            
            //load properties
            logLevel = properties.getProperty("eventlog.logLevel");
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public static  String SHA1Encrypt(String value) throws NoSuchAlgorithmException{
        
        MessageDigest mDigest = MessageDigest.getInstance("SHA1");
        byte[] result = mDigest.digest(value.getBytes());
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < result.length; i++) {
            sb.append(Integer.toString((result[i] & 0xff) + 0x100, 16).substring(1));
        }
         
        return sb.toString();
    }
    
    public static synchronized String getUUID() {
	String uuid = UUID.randomUUID().toString();
	return findAndReplace(uuid, "-", "");
    }
    
    public static String findAndReplace(String orig, String sub, String rep) {

        StringBuffer out = new StringBuffer();
        int index = 0;
        int oldIndex = index;
        while (index != -1) {
            index = orig.indexOf(sub, index);
            if (index != -1) {
                out.append(orig.substring(oldIndex, index));
                index += sub.length();
                oldIndex = index;
                out.append(rep);
            } else {
                out.append(orig.substring(oldIndex));
            }
        }

        return out.toString();
    }
}
