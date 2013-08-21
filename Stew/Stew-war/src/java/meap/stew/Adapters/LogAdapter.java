/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package meap.stew.Adapters;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import meap.stew.Entities.Log;
import meap.stew.Managers.EventLogManager;

/**
 * @name: LogAdapter.java
 * @create: Aug 21, 2013 
 * @version 1.0
 * @brief: This class is LogAdapter which connect to database and query data
 */
public class LogAdapter {
    /**
     * @brief create log
     * @param user
     * @return
     */
    public boolean createLog(Log log) {
        boolean result = false;
        EntityManagerFactory entityManagerFactory = null;
        EntityManager entityManager = null;
        
        try
        {
            entityManagerFactory = Persistence.createEntityManagerFactory("JPA");   
            entityManager = entityManagerFactory.createEntityManager();
            entityManager.getTransaction().begin();
            
            entityManager.persist(log);
            
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
}
