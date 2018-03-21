package repository.security;

import model.Employee;
import model.Right;
import model.Role;

import java.util.List;

public interface RightsRolesRepository {

    void addRole(String role);

    void addRight(String right);

    Role findRoleByTitle(String role);

    Role findRoleById(Long roleId);

    Right findRightByTitle(String right);

    void addRolesToEmployee(Employee employee, List<Role> roles);

    List<Role> findRolesForEmployee(Long id);

    void addRoleRight(Long roleId, Long rightId);

    public void updateEmployeeRoles(Employee employee, List<Role> roles);
}
