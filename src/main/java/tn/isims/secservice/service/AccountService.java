package tn.isims.secservice.service;

import tn.isims.secservice.entities.AppRole;
import tn.isims.secservice.entities.AppUser;

public interface AccountService {

    public AppUser saveUser(String username,String password,String confirmedPassWord);
    public AppRole save(AppRole role);
    public AppUser loadUserByUserName(String username);
    public void addRoleToUser(String username , String Role);

}
