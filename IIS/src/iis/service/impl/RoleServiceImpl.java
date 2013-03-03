package iis.service.impl;

import iis.dao.RoleDAO;
import iis.domain.Role;
import iis.service.RoleService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleServiceImpl implements RoleService{

	private static final long serialVersionUID = -3474404822215657720L;
	
	@Autowired
	private RoleDAO roleDAO;

	@Override
	public Role create(Role entity) {
		return roleDAO.create(entity);
	}

	@Override
	public Role update(Role entity) {
		return roleDAO.update(entity);
	}

	@Override
	public void deleteById(Long id) {
		roleDAO.deleteById(id);
	}

	@Override
	public Role findById(Long id) {
		return roleDAO.findById(id);
	}

	@Override
	public List<Role> findAll() {
		return roleDAO.findAll();
	}

	@Override
	public Role findByName(String roleName) {
		return roleDAO.findByName(roleName);
	}

}
