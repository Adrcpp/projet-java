package com.persistence.dao;

import com.persistence.dao.entities.Role;
import java.util.List;

public interface IRoleDao {

    public List<Role> findAllRoles();

    public Role findRoleById(int idRole);

    public Role createRole(Role role);

    public Role updateRole(Role role);

    public boolean deleteRole(Role role);
}
