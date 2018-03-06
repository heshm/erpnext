package com.erpnext.oa.act.dto;

import com.erpnext.framework.domain.AdminUser;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

public class UserRepresentation {

	protected String login;
    protected String password;
    protected String firstName;
    protected String lastName;
    protected String email;
    protected Boolean adminUser;
    protected Boolean clusterUser;

    public UserRepresentation() {
    }

    public UserRepresentation(AdminUser user) {
        this.login = user.getLoginName();
        this.firstName = user.getUserName();
        //this.lastName = user.getLastName();
        this.email = user.getEmail();
        
        if(user.getUserId().equals("admin")) {
        	this.adminUser = true;
        }

        /*if (user.getAuthorities() != null) {
            for (Authority authority : user.getAuthorities()) {
                if (Authority.ROLE_ADMIN.equals(authority.getName())) {
                    this.adminUser = true;
                } else if (Authority.ROLE_CLUSTER_MANAGER.equals(authority.getName())) {
                    this.clusterUser = true;
                }
            }
        }*/
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }
    
    @JsonInclude(Include.NON_NULL)
    public String getPassword() {
        return password;
    }
    
    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @JsonInclude(Include.NON_NULL)
    public Boolean getAdminUser() {
        return adminUser;
    }

    public void setIsAdmin(Boolean adminUser) {
        this.adminUser = adminUser;
    }
    
    @JsonInclude(Include.NON_NULL)
    public Boolean getClusterUser() {
        return clusterUser;
    }
    
    public void setClusterUser(Boolean clusterUser) {
        this.clusterUser = clusterUser;
    }
    
}
