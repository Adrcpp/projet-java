package com.persistence.service;

import com.persistence.dao.IMarkerDao;
import com.persistence.dao.IRoleDao;
import com.persistence.dao.IUserDao;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ServiceFacade implements IServiceFacade {

    private static final Log log = LogFactory.getLog(ServiceFacade.class);

    @Autowired
    private IUserDao userDao;

    @Autowired
    private IRoleDao roleDao;

    @Autowired
    private IMarkerDao markerDao;

    @Override
    public IUserDao getUserDao() {
        return userDao;
    }

    @Override
    public IRoleDao getRoleDao() {
        return roleDao;
    }

    @Override
    public IMarkerDao getMarkerDao() {
        return markerDao;
    }
}