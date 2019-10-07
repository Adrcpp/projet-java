package rest.api;

import com.persistence.dao.IUserDao;
import com.persistence.dao.entities.Role;
import com.persistence.dao.entities.User;
import com.persistence.errors.UsernameAlreadyExistException;
import com.persistence.service.IServiceFacade;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
public class UserController {

    private IServiceFacade serviceFacade;
 
    @Autowired
    public void setIServiceFacade(IServiceFacade serviceFacade) {
            this.serviceFacade = serviceFacade;
    }

    @PreAuthorize("hasAuthority('Admin')")
    @GetMapping(value = "/api/users")
    public ResponseEntity<?> index() {
        return  new ResponseEntity<>(serviceFacade.getUserDao().findAllUsers(), HttpStatus.OK);
    }

    @PreAuthorize("hasAuthority('Admin')")
    @GetMapping(value = "/api/users/{id}")
    public ResponseEntity<?> detail(@PathVariable Integer id) {

        User userApi = serviceFacade.getUserDao().findUserById(id);
        if (userApi == null) {
            return ResponseEntity.notFound().build();
        }

        return new ResponseEntity<>(userApi, HttpStatus.OK);
    }

    @PreAuthorize("hasAuthority('Admin')")
    @DeleteMapping("/api/users/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable Integer id) {
        User userApi = serviceFacade.getUserDao().findUserById(id);
        if (userApi == null) {
            return ResponseEntity.notFound().build();
        }

        return new ResponseEntity<>(serviceFacade.getUserDao().deleteUser(userApi), HttpStatus.OK);
    }

    @PreAuthorize("hasAuthority('Admin')")
    @PostMapping("/api/users")
    public ResponseEntity<?> createUser(@RequestBody User user) {

        System.out.println(user);
        User savedUser = null;
        try {
            savedUser = serviceFacade.getUserDao().createUser(user);
        } catch (UsernameAlreadyExistException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.FORBIDDEN);
        }

        return new ResponseEntity<>(savedUser, HttpStatus.OK);
    }

    @PreAuthorize("hasAuthority('Admin')")
    @PutMapping("/api/users/{id}")
    public ResponseEntity<?> updateUser(@RequestBody User user, @PathVariable Integer id) {

        User userApi = serviceFacade.getUserDao().findUserById(id);
        if (userApi == null) {
            return ResponseEntity.notFound().build();
        }

        user.setIdUser(id);
        return new ResponseEntity<>(serviceFacade.getUserDao().updateUser(user), HttpStatus.OK);
    }
}