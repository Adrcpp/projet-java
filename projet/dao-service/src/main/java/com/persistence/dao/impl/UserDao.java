package com.persistence.dao.impl;

import java.util.List;

import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;

import com.persistence.dao.IUserDao;
import com.persistence.dao.entities.User;
import com.persistence.errors.UsernameAlreadyExistException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.NonUniqueObjectException;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
@Repository
public class UserDao extends AbstractDao<User> implements IUserDao {

    private static final Log log = LogFactory.getLog(UserDao.class);

    private final static String className = UserDao.class.getSimpleName();

    private UserDao() {
        super(User.class);
        log.debug("--> ************ Initialisation de " + className + " ************");
    }

    /**
     * Holder
     */
    private static class MySingletonHolder {

        /**
         * Instance unique non préinitialisée
         */
        private final static UserDao instance = new UserDao();
    }

    /**
     * Point d'accès pour l'instance unique du singleton
     *
     * @return
     */

    public static UserDao getInstance() {
        if (UserDao.MySingletonHolder.instance == null) {
            log.debug("--> Nouvelle Instance de " + className);
        } else {
            log.debug("--> Re-Utilisation de l'instance " + className + " dejà existante");
        }
        return UserDao.MySingletonHolder.instance;
    }

    @Override
    public List<User> findAllUsers() {
        List<User> users = entityManager.createNamedQuery("User.findAll").getResultList();

        return users;
    }

    @Override
    public User findByUsername(String username) {
        User user = null;
        try {
            user = (User) entityManager.createNamedQuery("User.findByUsername").setParameter("username", username)
                    .getSingleResult();
        } catch (NoResultException | NonUniqueObjectException | NonUniqueResultException e) {
            return null;  
        }
        return user;
    }

    @Override
    public User findUserById(int idUser) {
        User user = entityManager.find(User.class, idUser);
        return user;
    }

    @Override
    @Transactional(readOnly= false, propagation=Propagation.REQUIRED)
    public User createUser(User user) {
        if (findByUsername(user.getUsername()) != null) {
            throw new UsernameAlreadyExistException(1);
        }

        return create(user);
    }

    @Override
    @Transactional(readOnly= false, propagation=Propagation.REQUIRED)
    public User updateUser(User user) {
        return update(user);
    }

    @Override
    @Transactional(propagation=Propagation.REQUIRED)
    public boolean deleteUser(User user) {
        return delete(user);
    }
}