package com.persistence.dao.impl;

import java.util.List;

import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;

import com.persistence.dao.IRoleDao;
import com.persistence.dao.entities.Role;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;


@Transactional(readOnly=true)
@Repository
public class RoleDao extends AbstractDao<Role> implements IRoleDao {

    private static final Log log = LogFactory.getLog(RoleDao.class);
    private final static String className = RoleDao.class.getSimpleName();

    private RoleDao() {
        super(Role.class);
        log.debug("--> ************ Initialisation de " + className + " ************");
    }

    /**
     * Holder
     */
    private static class MySingletonHolder {
        /**
         * Instance unique non préinitialisée
         */
        private final static RoleDao instance = new RoleDao();
    }

    /**
     * Point d'accès pour l'instance unique du singleton
     *
     * @return
     */
    public static RoleDao getInstance() {
        if (RoleDao.MySingletonHolder.instance == null) {
            log.debug("--> Nouvelle Instance de " + className);
        } else {
            log.debug("--> Re-Utilisation de l'instance " + className + " dejà existante");
        }
        return RoleDao.MySingletonHolder.instance;
    }

    @Override
    public List<Role> findAllRoles() {
        List<Role> roles = entityManager.createNamedQuery("Role.findAll")
        .getResultList();
        return roles;
    }

    @Override
    public Role findRoleById(int idRole) {
        Role role = entityManager.find(Role.class, idRole);
        return role;
    }

    @Override
    @Transactional(readOnly= false, propagation=Propagation.REQUIRED)
    public Role createRole(Role role) {
        create(role);
        return role;
    }

    @Override
    @Transactional(readOnly= false, propagation=Propagation.REQUIRED)
    public Role updateRole(Role role) {
       return update(role);
    }

    @Override
    @Transactional(readOnly= false, propagation=Propagation.REQUIRED)
    public boolean deleteRole(Role role) {
        delete(role);
        return true;
    }
}
