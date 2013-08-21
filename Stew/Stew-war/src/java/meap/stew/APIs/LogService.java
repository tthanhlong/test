/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package meap.stew.APIs;

import java.util.Date;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import meap.stew.Adapters.LogAdapter;
import meap.stew.Adapters.UserAdapter;
import meap.stew.Entities.Log;
import meap.stew.Entities.User;
import meap.stew.Utils.APIResponse;
import meap.stew.Utils.StewConstant;

/**
 * @name: LogService.java
 * @create: Aug 21, 2013 
 * @version 1.0
 * @brief: This class is implementation for Logging API
 */
@Path("/service")
public class LogService {
    /**
     * @brief: This is Logging web service
     * @param token
     * @param typeString
     * @param message
     * @return 
     */
    @POST
    @Path("/Logging")
    @Produces(MediaType.APPLICATION_JSON)
    public String logging(@QueryParam("token") String token, @QueryParam("type") String typeString, @QueryParam("message") String message, @QueryParam("appID") String appID) {
        
        int type = StewConstant.errorLogType;
        
        try {
            type = Integer.parseInt(typeString);
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        String result = logMessage(token, type, message, appID);
        
        return result;
    }
    
    /**
     * @brief: This method is process of log message
     * @param token
     * @param type
     * @param message
     * @return 
     */
     public String logMessage(String token, int type, String message, String appID){
        String result = "";
        UserAdapter userController = new UserAdapter();
        LogAdapter logController = new LogAdapter();
        
        //if token or appID is null or not specified: return error
        if (token == null || "".equals(token) || appID == null || "".equals(appID)) {
            result = new APIResponse().loggingResponse(StewConstant.errorStatus, StewConstant.requiredDescription);
        }else{
            //get user by token
            User user = userController.getUserByToken(token);
            
            //if user is not existed: return error
            if (user == null) {
                result = new APIResponse().loggingResponse(StewConstant.errorStatus, StewConstant.tokenNotExistDescription);
            }else{
                Date currentExpireDate = user.getTokenExpireDate();
                Date now = new Date();
                
                //if token is expired: return error
                if (now.getTime() > currentExpireDate.getTime()) {
                    result = new APIResponse().loggingResponse(StewConstant.errorStatus, StewConstant.expireToken);
                }else{
                    //update expire date of token
                    int defaultTokenAge = user.getDefaultTokenAge();
                    user.setTokenExpireDate(new Date(now.getTime() + defaultTokenAge * 3600 * 1000));
                    userController.updateUser(user);

                    //create log record
                    Log log = new Log();
                    log.setAppId(appID);
                    log.setMessageLog(message);
                    log.setTimeLog(now);
                    log.setUserId(user.getId());

                    logController.createLog(log);

                    result = new APIResponse().loggingResponse(StewConstant.okStatus, "");
                }
            }
        }
        
        return result;
    } 
}
