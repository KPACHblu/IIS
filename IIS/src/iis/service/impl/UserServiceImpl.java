package iis.service.impl;

import iis.dao.UserDAO;
import iis.domain.User;
import iis.service.UserService;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("userServiceImpl")
public class UserServiceImpl implements UserService{

	private static final long serialVersionUID = -4704547450671634682L;

	@Autowired
	private UserDAO userDAO;
	
	@Override
	public User create(User entity) {
		return userDAO.create(entity);
	}

	@Override
	public User update(User entity) {
		return userDAO.update(entity);
	}

	@Override
	public void deleteById(Long id) {
		userDAO.deleteById(id);
	}

	@Override
	public User findById(Long id) {
		return userDAO.findById(id);
	}

	@Override
	public List<User> findAll() {
		return userDAO.findAll();
	}

	@Override
	public User findByLogin(String login) {
		return userDAO.findByLogin(login);
	}

	@Override
	public List<User> findByRole(Long roleId) {
		return userDAO.findByRole(roleId);
	}
	
	@Override
	public List<User> findByRole(String roleName) {
		return userDAO.findByRole(roleName);
	}

	@Override
	public List<User> find(Long roleId, int first, int pageSize,
			String sortField, String sortOrder, Map<String, String> filters) {
		return userDAO.find(roleId, first, pageSize, sortField, sortOrder, filters);
	}

	@Override
	public int getCount(Long roleId, String sortField, String sortOrder,
			Map<String, String> filters) {
		return userDAO.getCount(roleId, sortField, sortOrder, filters);
	}

}
