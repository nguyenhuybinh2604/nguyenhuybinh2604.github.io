package service;

import entity.UserRole;
import entity.UserStatus;

public interface IUser {

    public String getUsername();

    public String getPassword();

    public UserRole getUserRole();

    public UserStatus getUserStatus();

    public String getEmail();

    public void setUsername(String username);

    public void setPassword(String password);

    public void setUserRole(UserRole userRole);

    public void setUserStatus(UserStatus userStatus);

    public void setEmail(String email);
}
