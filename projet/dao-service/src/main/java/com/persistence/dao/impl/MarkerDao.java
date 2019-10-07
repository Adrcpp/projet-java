package com.persistence.dao.impl;

import java.util.List;

import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
import javax.persistence.PersistenceUnit;

import com.persistence.dao.IMarkerDao;
import com.persistence.dao.entities.Marker;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.NonUniqueObjectException;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;


@Transactional(readOnly=true)
@Repository
public class MarkerDao extends AbstractDao<Marker> implements IMarkerDao {

    private static final Log log = LogFactory.getLog(MarkerDao.class);
    private final static String className = MarkerDao.class.getSimpleName();

    private MarkerDao() {
        super(Marker.class);
        log.debug("--> ************ Initialisation de " + className + " ************");
    }

    /**
     * Holder
     */
    private static class MySingletonHolder {
        /**
         * Instance unique non préinitialisée
         */
        private final static MarkerDao instance = new MarkerDao();
    }

    /**
     * Point d'accès pour l'instance unique du singleton
     *
     * @return
     */
    public static MarkerDao getInstance() {
        if (MarkerDao.MySingletonHolder.instance == null) {
            log.debug("--> Nouvelle Instance de " + className);
        } else {
            log.debug("--> Re-Utilisation de l'instance " + className + " dejà existante");
        }
        return MarkerDao.MySingletonHolder.instance;
    }

    @Override
    public List<Marker> findAllMarkers() {
        List<Marker> markers = entityManager.createNamedQuery("Marker.findAll")
        .getResultList();
        return markers;
    }

    @Override
    public Marker findMarkerById(int idMarker) {
        Marker marker = entityManager.find(Marker.class, idMarker);
        return marker;
    }

    @Override
    public List<Marker> findMarkersByUserId(int idUser) {
        List<Marker> markers = null;
        markers = entityManager.createNamedQuery("Marker.findMarkersByUserId")
            .setParameter("idUser", idUser)
            .getResultList();

        return markers;
    }

    @Override
    @Transactional(readOnly= false, propagation=Propagation.REQUIRED)
    public Marker createMarker(Marker Marker) {
        create(Marker);
        return Marker;
    }

    @Override
    @Transactional(readOnly= false, propagation=Propagation.REQUIRED)
    public Marker updateMarker(Marker Marker) {
       return update(Marker);
    }

    @Override
    @Transactional(readOnly= false, propagation=Propagation.REQUIRED)
    public boolean deleteMarker(Marker Marker) {
        delete(Marker);
        return true;
    }
}
