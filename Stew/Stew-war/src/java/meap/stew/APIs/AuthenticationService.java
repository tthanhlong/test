/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package meap.stew.APIs;

import java.util.ArrayList;
import java.util.List;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import meap.stew.Adapters.UserAdapter;
import meap.stew.Entities.User;
import meap.stew.Utils.APIResponse;
import meap.stew.Utils.StewConstant;

/**
 * @name: AuthenticationService.java
 * @create: Aug 21, 2013 
 * @version 1.0
 * @brief: This class is implementation for Authentication API
 */
@Path("/service")
public class AuthenticationService {
    
    /**
     * @brief: This is Authentication web service
     * @param username
     * @param password
     * @param appID
     * @return 
     */
    @POST
    @Path("/Authentication")
    @Produces(MediaType.APPLICATION_JSON)
    public String authentication(@QueryParam("username") String username, @QueryParam("password") String password) {
        String result = authenticateUser(username, password);
        
        return result;
    }
    
    /**
     * @brief: This method is process of authentication
     * @param userName
     * @param password
     * @param appID
     * @return 
     */
    public String authenticateUser(String userName, String password){
        String result = "";
        
        UserAdapter userController = new UserAdapter();
        
        //if required parameters are not existed: return error
        if (userName == null || "".equals(userName) || password == null || "".equals(password)) {
            result = new APIResponse().authenticateResponse(StewConstant.errorStatus, "", StewConstant.requiredDescription);
        }else{
            User user = userController.getUser(userName, password);
            
            //if user is not existed: return error
            if (user == null) {
                result = new APIResponse().authenticateResponse(StewConstant.errorStatus, "", StewConstant.userNotExistDescription);
            }else{
                result = new APIResponse().authenticateResponse(StewConstant.okStatus, user.getUserToken(), "");
            }
        }
        
        return result;
    }
}
