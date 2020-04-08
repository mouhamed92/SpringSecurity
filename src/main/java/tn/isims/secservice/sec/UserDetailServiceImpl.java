package tn.isims.secservice.sec;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import tn.isims.secservice.dao.AppUserRepository;
import tn.isims.secservice.entities.AppUser;
import tn.isims.secservice.service.AccountService;

import java.util.ArrayList;
import java.util.Collection;

@Service
public class UserDetailServiceImpl implements UserDetailsService {

    @Autowired
    private AccountService accountService ;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        AppUser appUser = accountService.loadUserByUserName(username);
        if (appUser==null)throw new UsernameNotFoundException("invalid user");
        Collection<GrantedAuthority> authorities = new ArrayList<>();
        appUser.getRoles().forEach(appRole -> {
            authorities.add(new SimpleGrantedAuthority(appRole.getRoleName()));

        });
        return new User(appUser.getUsername(),appUser.getMotDepasse(),authorities);
    }
}
