package rest.api;

import com.persistence.dao.entities.Role;
import com.persistence.service.IServiceFacade;

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
import org.springframework.web.bind.annotation.RestController;


@RestController
public class RoleController {

    private IServiceFacade serviceFacade;
 
    @Autowired
    public void setIServiceFacade(IServiceFacade serviceFacade) {
        this.serviceFacade = serviceFacade;
    }

    @PreAuthorize("hasAuthority('Admin')")
    @GetMapping(value = "/api/roles")
    public ResponseEntity<?> index() {
        return new ResponseEntity<>(serviceFacade.getRoleDao().findAllRoles(), HttpStatus.OK);
    }

    @PreAuthorize("hasAuthority('Admin')")
    @DeleteMapping("/api/roles/{id}")
    public ResponseEntity<?> deleteMarker(@PathVariable Integer id) {
        Role roleApi = serviceFacade.getRoleDao().findRoleById(id);
        if (roleApi == null) {
            return ResponseEntity.notFound().build();
        }

        return new ResponseEntity<>(serviceFacade.getRoleDao().deleteRole(roleApi), HttpStatus.OK);
    }

    @PreAuthorize("hasAuthority('Admin')")
    @PostMapping("/api/roles/{id}")
    public ResponseEntity<?> createMarker(@RequestBody Role role) {
        role = serviceFacade.getRoleDao().createRole(role);
        return new ResponseEntity<>(role, HttpStatus.OK);
    }

    @PreAuthorize("hasAuthority('Admin')")
    @PutMapping("/api/roles/{id}")
    public ResponseEntity<?> updateMarker(@RequestBody Role role, @PathVariable Integer id) {
        Role roleApi = serviceFacade.getRoleDao().findRoleById(id);
        if (roleApi == null) {
            return ResponseEntity.notFound().build();
        }

        role.setIdRole(id);
        return new ResponseEntity<>(serviceFacade.getRoleDao().updateRole(role), HttpStatus.OK);
    }
}