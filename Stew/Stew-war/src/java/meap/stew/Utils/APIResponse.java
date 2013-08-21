/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package meap.stew.Utils;

/**
 *
 * @author tthanhlong
 */
public class APIResponse {
    
    /**
     * @brief API response defined for authentication
     * @param status
     * @param userToken
     * @param description
     * @return 
     */
    public String authenticateResponse(String status, String userToken, String description){
        String result = "";
        
        if (status == null ? StewConstant.okStatus == null : status.equals(StewConstant.okStatus)) {
            result = "{\"status\": \""+ status +"\", \"token\": \""+ userToken +"\"}";
        }else if(status == null ? StewConstant.errorStatus == null : status.equals(StewConstant.errorStatus)){
            result = "{\"status\": \""+ status +"\", \"description\": \""+ description +"\"}";
        }
        
        return result;
    }

    /**
     * @brief API response defined for logging
     * @param status
     * @param description
     * @return 
     */
    public String loggingResponse(String status, String description) {
        String result = "";
        
        if (status == null ? StewConstant.errorStatus == null : status.equals(StewConstant.errorStatus)) {
            result = "{\"status\": \""+ status +"\", \"description\": \""+ description +"\"}";
        }else if(status == null ? StewConstant.okStatus == null : status.equals(StewConstant.okStatus)){
            result = "{\"status\": \""+ status +"\"}";
        }
        
        return result;
    }
}
