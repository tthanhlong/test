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
import meap.stew.Utils.StewConstant;

/**
 *
 * @author vcnduong
 */
@Entity
public class User {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String passwordHash;    
    private int defaultTokenAge = StewConstant.defaultAgeToken;
    private String roleId;
    private String email;
    private String userToken;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date tokenExpireDate;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date loginDate;

    public User(String passwordHash, String email) {
        this.passwordHash = passwordHash;
        this.email = email;
    }

    public String getUserToken() {
        return userToken;
    }

    public Date getTokenExpireDate() {
        return tokenExpireDate;
    }

    public Date getLoginDate() {
        return loginDate;
    }

    public void setUserToken(String userToken) {
        this.userToken = userToken;
    }

    public void setTokenExpireDate(Date tokenExpireDate) {
        this.tokenExpireDate = tokenExpireDate;
    }

    public void setLoginDate(Date loginDate) {
        this.loginDate = loginDate;
    }

    public User(String passwordHash, String roleId, String email, String userToken, Date tokenExpireDate, Date loginDate) {
        this.passwordHash = passwordHash;
        this.roleId = roleId;
        this.email = email;
        this.userToken = userToken;
        this.tokenExpireDate = tokenExpireDate;
        this.loginDate = loginDate;
    }

    public User() {
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }
    public int getDefaultTokenAge() {
        return defaultTokenAge;
    }

    public void setDefaultTokenAge(int defaultTokenAge) {
        this.defaultTokenAge = defaultTokenAge;
    }

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
