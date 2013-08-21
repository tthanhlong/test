/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package meap.stew.Entities;

import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;

/**
 *
 * @author vcnduong
 */
@Entity
public class Log{
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String messageLog;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date timeLog;
    private String appId;
    private Long userId;

    public String getMessageLog() {
        return messageLog;
    }

    public void setMessageLog(String messageLog) {
        this.messageLog = messageLog;
    }

    public Date getTimeLog() {
        return timeLog;
    }

    public void setTimeLog(Date timeLog) {
        this.timeLog = timeLog;
    }

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

}
