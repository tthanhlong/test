/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package meap.stew.Adapters;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;
import meap.stew.Entities.User;
import meap.stew.Managers.EventLogManager;
import meap.stew.Utils.StewUtils;

/**
 * @name: UserAdapter.java
 * @create: Aug 21, 2013 
 * @version 1.0
 * @brief: This class is UserAdapter which connect to database and query data
 */
public class UserAdapter {
    
    public UserAdapter() {
    }
    
    /**
     * @brief: this function is add new user
     * @param user
     * @return
     */
    public boolean addUser(User user) {
        boolean result = false;
        EntityManagerFactory entityManagerFactory = null;
        EntityManager entityManager = null;
        
        try
        {
            entityManagerFactory = Persistence.createEntityManagerFactory("JPA");   
            entityManager = entityManagerFactory.createEntityManager();
            entityManager.getTransaction().begin();
            
            entityManager.persist(user);
            
            entityManager.getTransaction().commit();
            result = true;
        }
        catch (Exception e)
        {
            EventLogManager.getInstance().log(e.getMessage());
        }
        finally{
            entityManager.close(); 
            entityManagerFactory.close();
        }
        return result;
    }

    /**
     * @brief: This function is get user by username and password
     * @param userName
     * @param password
     * @return 
     */
    public User getUser(String userName, String password) {
        User userResult = null;
        EntityManagerFactory entityManagerFactory = null;
        EntityManager entityManager = null;
        
        try {
            entityManagerFactory = Persistence.createEntityManagerFactory("JPA");   
            entityManager = entityManagerFactory.createEntityManager();
            entityManager.getTransaction().begin();
            
            Query query = entityManager.createQuery("from User where email =:email and passwordHash=:password");
            query.setParameter("email", userName);
            query.setParameter("password", password);
            List queryResult = query.getResultList();
            
            if (queryResult != null && queryResult.size() > 0) {
                User user = (User)queryResult.get(0);
                
                //if usertoken is not existed, re-generate token
                if (user.getUserToken() == null || "".equals(user.getUserToken())) {
                    String newUserToken = StewUtils.getUUID();
                    user.setUserToken(newUserToken);   
                }
                
                int defaultTokenAge = user.getDefaultTokenAge();
                Date now = new Date();
                long newExpiryDateLong = now.getTime() + defaultTokenAge * 3600 * 1000;
                user.setLoginDate(now);
                user.setTokenExpireDate(new Date(newExpiryDateLong));
                
                //update user with new expire date and user token
                if(updateUser(user)){
                    userResult = user;
                }
            }

            entityManager.getTransaction().commit();
        } catch (Exception e) {
            EventLogManager.getInstance().log(e.getMessage());
        }finally{
            entityManager.close();  
            entityManagerFactory.close();
        }
        
        return userResult;
    }
    
    /**
     * @brief: This function is update user with new information
     * @param user
     * @return 
     */
    public boolean updateUser(User user) {
        boolean result = false;
        EntityManagerFactory entityManagerFactory = null;
        EntityManager entityManager = null;
        
        try
        {
            entityManagerFactory = Persistence.createEntityManagerFactory("JPA");   
            entityManager = entityManagerFactory.createEntityManager();
            entityManager.getTransaction().begin();
            
            entityManager.merge(user);
            
            entityManager.getTransaction().commit();
            result = true;
        }
        catch (Exception e)
        {
            EventLogManager.getInstance().log(e.getMessage());
        }
        finally{
            entityManager.close(); 
            entityManagerFactory.close();
        }
        return result;
    }
    
    /**
     * @brief: This function is get user by userID
     * @param userId
     * @return 
     */
    public User getUserByUserID(Long userId) {
        User userResult = null;
        EntityManagerFactory entityManagerFactory = null;
        EntityManager entityManager = null;
        
        try {
            entityManagerFactory = Persistence.createEntityManagerFactory("JPA");   
            entityManager = entityManagerFactory.createEntityManager();
            entityManager.getTransaction().begin();
            
            Query query = entityManager.createQuery("from User where id =:id");
            query.setParameter("id", userId);
            List queryResult = query.getResultList();
            
            if (queryResult != null && queryResult.size() > 0) {
                userResult = (User)queryResult.get(0);
            }

            entityManager.getTransaction().commit();
        } catch (Exception e) {
            EventLogManager.getInstance().log(e.getMessage());
        }finally{
            entityManager.close();  
            entityManagerFactory.close();
        }
        
        return userResult;
    } 
    
    /**
     * @brief: This function is get all users
     * @return 
     */
    public List<User> getUsers(){
        EntityManagerFactory entityManagerFactory = null;
        EntityManager entityManager = null;
        List<User> users = new ArrayList<User>();
        
        try{
            entityManagerFactory = Persistence.createEntityManagerFactory("JPA");   
            entityManager = entityManagerFactory.createEntityManager();
            entityManager.getTransaction().begin();
            List result = (entityManager.createQuery("from User").getResultList());
            users = (List<User>)result;

            entityManager.getTransaction().commit();
        } catch (Exception e) {
            EventLogManager.getInstance().log(e.getMessage());
        }finally{
            entityManager.close();
            entityManagerFactory.close();
        }
        
        return users;
    }
    
    /**
     * @brief: This function is get user by userToken
     * @param token
     * @return 
     */
    public User getUserByToken(String token) {
        User result = null;
        EntityManagerFactory entityManagerFactory = null;
        EntityManager entityManager = null;
        
        try {
            entityManagerFactory = Persistence.createEntityManagerFactory("JPA");   
            entityManager = entityManagerFactory.createEntityManager();
            entityManager.getTransaction().begin();
            
            Query query = entityManager.createQuery("from User where userToken =:userToken");
            query.setParameter("userToken", token);
            List queryResult = query.getResultList();
            
            if (queryResult != null && queryResult.size() > 0) {
                result = (User)queryResult.get(0);
            }

            entityManager.getTransaction().commit();
        } catch (Exception e) {
            EventLogManager.getInstance().log(e.getMessage());
        }finally{
            entityManager.close();  
            entityManagerFactory.close();
        }
        
        return result;
    }
    
}
