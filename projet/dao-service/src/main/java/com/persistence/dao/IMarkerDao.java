package com.persistence.dao;

import com.persistence.dao.entities.Marker;
import java.util.List;

public interface IMarkerDao {

    public List<Marker> findAllMarkers();

    public Marker findMarkerById(int idMarker);

    public List<Marker> findMarkersByUserId(int idUser);
    
    public Marker createMarker(Marker role);

    public Marker updateMarker(Marker role);

    public boolean deleteMarker(Marker role);
}
