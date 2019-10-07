package rest.api;

import java.util.ArrayList;

import com.persistence.dao.entities.Role;
import com.persistence.dao.entities.User;
import com.persistence.service.IServiceFacade;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/login")
public class LogInController {

    @Autowired
    private  IServiceFacade serviceFacade;

    private  PasswordEncoder passwordEncoder;

    @PostMapping
    User signin(@RequestParam String email, @RequestParam String password) {
        // Role role = new Role();
        // role.setName("USER");
        // ArrayList<Role> array = new ArrayList<Role>();
        // array.add(role);

        User user = new User();
        // user.setRolesSet(array);

        return user;
    }

}