package service;

import entity.UserRole;

public interface IUser {

    public String getUsername();

    public String getPassword();

    public UserRole getUserRole();

    public String getEmail();

    public void setUsername(String username);

    public void setPassword(String password);

    public void setUserRole(UserRole userRole);

    public void setEmail(String email);
}
