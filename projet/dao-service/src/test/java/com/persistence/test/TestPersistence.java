package com.persistence.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.Reader;
import java.util.ArrayList;

import javax.sql.DataSource;

import com.ibatis.common.jdbc.ScriptRunner;
import com.persistence.dao.entities.Marker;
import com.persistence.dao.entities.Role;
import com.persistence.dao.entities.User;
import com.persistence.errors.UsernameAlreadyExistException;
import com.persistence.service.IServiceFacade;
import com.persistence.service.ServiceFacade;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;


public class TestPersistence {

    private static final Log log = LogFactory.getLog(TestPersistence.class);

    @Autowired
    private static IServiceFacade serviceFacade;

    @Before
    public void init() throws Exception {
        initDataBase();
        AbstractApplicationContext context = new ClassPathXmlApplicationContext("testContext.xml");
        serviceFacade = (ServiceFacade) context.getBean("serviceFacade");
    }

    public void initDataBase() {
        try {
            AbstractApplicationContext ctx = new ClassPathXmlApplicationContext("testContext.xml");  
            DataSource dataSource = (DataSource) ctx.getBean("dataSource");

            ScriptRunner runner = new ScriptRunner(dataSource.getConnection(),false, false);
            Reader reader = new BufferedReader(
                new FileReader("projet-libre_test.sql"));

            runner.runScript(reader);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test_UserDao_findAllUsers() {
        ArrayList<User> users = new ArrayList<User>(serviceFacade.getUserDao().findAllUsers());
        assertNotNull(users);
        assertEquals(1, users.size());
    }

    @Test
    public void test_UserDao_findByUsername() {
        User user = serviceFacade.getUserDao().findByUsername("admin@gmail.com");

        assertNotNull(user);
        assertEquals("admin@gmail.com", user.getUsername());

        assertNotNull(user.getRole());
        assertNotNull(user.getRole());
        assertEquals("Admin", user.getRole().getName());
    }

    @Test
    public void test_UserDao_findUserById() {
        User user = serviceFacade.getUserDao().findUserById(1);

        assertNotNull(user);
        assertEquals("admin@gmail.com", user.getUsername());

        assertNotNull(user.getRole());
        assertNotNull(user.getRole());
        assertEquals("Admin", user.getRole().getName());
    }

    @Test
    public void test_UserDao_createUser() {
        User user = new User();
        user.setRole(serviceFacade.getRoleDao().findRoleById(1));
        user.setUsername("username");
        
        user = serviceFacade.getUserDao().createUser(user);
        assertNotNull(user);
        assertEquals((Integer) 2, user.getIdUser());
    }

    @Test(expected = UsernameAlreadyExistException.class)
    public void test_UserDao_createUser_Exception() throws UsernameAlreadyExistException {
        User user = new User();
        user.setRole(serviceFacade.getRoleDao().findRoleById(1));
        user.setUsername("admin@gmail.com");
        
        user = serviceFacade.getUserDao().createUser(user);
    }

    @Test
    public void test_UserDao_updateUser() {
        User user =serviceFacade.getUserDao().findUserById(1);
        user.setUsername("username");
        assertEquals((Integer) 1, user.getVersion());

        user = serviceFacade.getUserDao().updateUser(user);
        assertNotNull(user);
        assertEquals((Integer) 2, user.getVersion());
    }

    @Test
    public void test_UserDao_deleteUser() {
        User user = serviceFacade.getUserDao().findUserById(1);
        serviceFacade.getUserDao().deleteUser(user);
        assertNull(serviceFacade.getUserDao().findUserById(1));
    }

    // --------------------------------------------------------------

    @Test
    public void test_RoleDao_findAllRoles() {
        ArrayList <Role> roles = new ArrayList<Role>(serviceFacade.getRoleDao().findAllRoles());

        assertNotNull(roles);
        assertEquals(2, roles.size());
    }

    @Test
    public void test_RoleDao_findRoleById() {
        Role role = serviceFacade.getRoleDao().findRoleById(1);
       
        assertNotNull(role);
        assertEquals("Admin", role.getName());
    }

    @Test
    public void test_RoleDao_createRole() {
        Role role = new Role();
        role.setName("User");
        role = serviceFacade.getRoleDao().createRole(role);

        assertNotNull(role);
        assertEquals((Integer) 3, role.getIdRole());
    }

    @Test
    public void test_RoleDao_updateRole() {
        Role role = serviceFacade.getRoleDao().findRoleById(1);
        role.setName("User");
        assertEquals((Integer) 1, role.getVersion());

        role = serviceFacade.getRoleDao().updateRole(role);
        assertNotNull(role);
        assertEquals((Integer) 2, role.getVersion());
    }

    @Test
    public void test_RoleDao_deleteRole() {
        Role role = serviceFacade.getRoleDao().findRoleById(1);
        serviceFacade.getRoleDao().deleteRole(role);

        assertNull(serviceFacade.getRoleDao().findRoleById(1));
    }

   // --------------------------------------------------------------

    @Test
    public void test_MarkerDao_findAllMarker() {
        ArrayList<Marker> markers = new ArrayList<Marker> (serviceFacade.getMarkerDao().findAllMarkers());

        assertNotNull(markers);
        assertEquals(1, markers.size());
    }

    @Test
    public void test_MarkerDao_findMarkerById() {
        Marker marker = serviceFacade.getMarkerDao().findMarkerById(1);
        
        assertNotNull(marker);
        assertEquals("Restaurant", marker.getName());
    }

    @Test
    public void test_MarkerDao_findMarkersByUserId() {
        ArrayList<Marker> markers = new ArrayList<Marker> (serviceFacade.getMarkerDao().findMarkersByUserId(1));
        
        assertNotNull(markers);
        assertEquals(1, markers.size());
        assertEquals("Restaurant", markers.get(0).getName());
    }

    @Test
    public void test_MarkerDao_createMarker() {
        Marker marker = new Marker();
        marker.setName("Restaurant 2");
        marker.setUser(serviceFacade.getUserDao().findUserById(1));
        marker = serviceFacade.getMarkerDao().createMarker(marker);

        assertNotNull(marker);
        assertEquals((Integer) 2, marker.getIdMarker());
    }

    @Test
    public void test_MarkerDao_updateMarker() {
        Marker marker = serviceFacade.getMarkerDao().findMarkerById(1);
        marker.setName("Restaurant 2");
        assertEquals((Integer) 1, marker.getVersion());

        marker = serviceFacade.getMarkerDao().updateMarker(marker);
        assertNotNull(marker);
        assertEquals((Integer) 2, marker.getVersion());
    }

    @Test
    public void test_MarkerDao_deleteMarker() {
        Marker marker = serviceFacade.getMarkerDao().findMarkerById(1);
        assertNotNull(serviceFacade.getMarkerDao().findMarkerById(1));
        
        serviceFacade.getMarkerDao().deleteMarker(marker);
        assertNull(serviceFacade.getMarkerDao().findMarkerById(1));
    }

    @AfterClass
    public static void terminate() throws Exception {
        serviceFacade = null;
    }
}