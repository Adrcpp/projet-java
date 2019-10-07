package com.persistence.service;

import com.persistence.dao.IMarkerDao;
import com.persistence.dao.IRoleDao;
import com.persistence.dao.IUserDao;

public interface IServiceFacade {

    public IUserDao getUserDao();

    public IRoleDao getRoleDao();

    public IMarkerDao getMarkerDao();
}