package rest.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Arrays;

import com.persistence.dao.entities.User;
import com.persistence.service.IServiceFacade;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    private IServiceFacade serviceFacade;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User user = serviceFacade.getUserDao().findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException(String.format("username is invalid !"));
        }

        GrantedAuthority authority = new SimpleGrantedAuthority(user.getRole().getName());
        UserDetails usr = new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), Arrays.asList(authority));
        System.out.println(usr);

        return usr;
    }
}