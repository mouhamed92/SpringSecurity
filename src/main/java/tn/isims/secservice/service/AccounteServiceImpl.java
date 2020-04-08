package tn.isims.secservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tn.isims.secservice.dao.AppRoleRepository;
import tn.isims.secservice.dao.AppUserRepository;
import tn.isims.secservice.entities.AppRole;
import tn.isims.secservice.entities.AppUser;

@Service
@Transactional
public class AccounteServiceImpl implements AccountService{

    @Autowired
    private AppUserRepository appUserRepository ;

    @Autowired
    private AppRoleRepository appRoleRepository ;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder ;

    @Override
    public AppUser saveUser(String username, String password, String confirmedPassWord) {
           AppUser user = appUserRepository.findByUsername(username);
           if (user!=null)throw new RuntimeException("User alreadu exist");
           if (!password.equals(confirmedPassWord))throw new RuntimeException("Please confirm your password");
           AppUser appUser = new AppUser( );
           appUser.setUsername(username);
           appUser.setActived(true);
           appUser.setMotDepasse(bCryptPasswordEncoder.encode(password));
           appUserRepository.save(appUser);
           addRoleToUser(username,"USER");
            return appUser;
    }

    @Override
    public AppRole save(AppRole role) {
        return appRoleRepository.save(role);
    }

    @Override
    public AppUser loadUserByUserName(String username) {
        return appUserRepository.findByUsername(username);
    }

    @Override
    public void addRoleToUser(String username, String Role) {

      AppUser user =  appUserRepository.findByUsername(username);
       AppRole role = appRoleRepository.findByRoleName(Role);
       user.getRoles().add(role) ;
    }
}
