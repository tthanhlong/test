/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package meap.stew.Entities;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 *
 * @author vcnduong
 */
@Entity
public class UserData implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String databaseName;
    private Long userId;
    private Long appId;
    private String schemaXML;
    private Long appVersion;

    public UserData() {
    }

    public Long getAppVersion() {
        return appVersion;
    }

    public void setAppVersion(Long appVersion) {
        this.appVersion = appVersion;
    }

    public String getDatabaseName() {
        return databaseName;
    }

    public void setDatabaseName(String databaseName) {
        this.databaseName = databaseName;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getAppId() {
        return appId;
    }

    public void setAppId(Long appId) {
        this.appId = appId;
    }

    public String getSchemaXML() {
        return schemaXML;
    }

    public void setSchemaXML(String schemaXML) {
        this.schemaXML = schemaXML;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

}
