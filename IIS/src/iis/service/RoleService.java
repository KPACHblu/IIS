package iis.service;

import iis.domain.Role;

import java.io.Serializable;
import java.util.List;

public interface RoleService extends Serializable {

	Role create(Role entity);

	Role update(Role entity);

	void deleteById(Long id);

	Role findById(Long id);
	
	Role findByName(String roleName);

	List<Role> findAll();

}
