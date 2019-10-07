package rest.api;

import com.persistence.dao.entities.Marker;
import com.persistence.dao.entities.User;
import com.persistence.service.IServiceFacade;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
public class MarkerController {

    private IServiceFacade serviceFacade;
 
    @Autowired
    public void setIServiceFacade(IServiceFacade serviceFacade) {
        this.serviceFacade = serviceFacade;
    }

    @GetMapping(value = "/api/markers")
    public ResponseEntity<?> index() {
        return new ResponseEntity<>(serviceFacade.getMarkerDao().findAllMarkers(), HttpStatus.OK);
    }

    @GetMapping(value = "/api/markers/{idUser}/user")
    public ResponseEntity<?> markersByUserId(@PathVariable Integer idUser) {
        return new ResponseEntity<>(serviceFacade.getMarkerDao().findMarkerById(idUser), HttpStatus.OK);
    }

    @DeleteMapping("/api/markers/{id}")
    public ResponseEntity<?> deleteMarker(@PathVariable Integer id) {
        Marker markerAPi = serviceFacade.getMarkerDao().findMarkerById(id);
        if (markerAPi == null) {
            return ResponseEntity.notFound().build();
        }

        return new ResponseEntity<>(serviceFacade.getMarkerDao().deleteMarker(markerAPi), HttpStatus.OK);
    }

    @PostMapping("/api/markers/{idUser}/user")
    public ResponseEntity<?> createMarker(@RequestBody Marker marker, @PathVariable Integer idUser) {
        User user = serviceFacade.getUserDao().findUserById(idUser);
        if (user == null) {
            return new ResponseEntity<>("User not found, we can't add marker.", HttpStatus.NOT_FOUND);
        }
        marker.setUser(user);
        marker = serviceFacade.getMarkerDao().createMarker(marker);
        return new ResponseEntity<>(marker, HttpStatus.OK);
    }

    @PutMapping("/api/markers/{id}")
    public ResponseEntity<?> updateMarker(@RequestBody Marker marker, @PathVariable Integer id) {

        Marker savedMarker = serviceFacade.getMarkerDao().findMarkerById(id);
        if (savedMarker == null) {
            return ResponseEntity.notFound().build();
        }

        marker.setIdMarker(id);
        return new ResponseEntity<>(serviceFacade.getMarkerDao().updateMarker(savedMarker), HttpStatus.OK);
    }
}