package iis.dao;

import java.io.Serializable;
import java.util.List;

import iis.domain.Role;

public interface RoleDAO extends Serializable {

	Role create(Role entity);

	Role update(Role entity);

	void deleteById(Long id);

	Role findById(Long id);
	
	Role findByName(String roleName);
	
	List<Role> findAll();


}
