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
public class DataSyncTracking {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String deviceUDID;
    private Long appId;
    private Long userId;
    private Long appVersion;
    private String dataToSyncXML;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date syncDate;

    public DataSyncTracking() {
    }

    public String getDeviceUDID() {
        return deviceUDID;
    }

    public void setDeviceUDID(String deviceUDID) {
        this.deviceUDID = deviceUDID;
    }

    public Long getAppId() {
        return appId;
    }

    public void setAppId(Long appId) {
        this.appId = appId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getAppVersion() {
        return appVersion;
    }

    public void setAppVersion(Long appVersion) {
        this.appVersion = appVersion;
    }

    public String getDataToSyncXML() {
        return dataToSyncXML;
    }

    public void setDataToSyncXML(String dataToSyncXML) {
        this.dataToSyncXML = dataToSyncXML;
    }

    public Date getSyncDate() {
        return syncDate;
    }

    public void setSyncDate(Date syncDate) {
        this.syncDate = syncDate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
