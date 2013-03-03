package iis.dao;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import iis.domain.User;

public interface UserDAO extends Serializable {

	User create(User user);

	User update(User user);

	void deleteById(Long id);

	User findById(Long id);

	User findByLogin(String login);

	List<User> findAll();

	List<User> findByRole(Long roleId);
	
	List<User> findByRole(String roleName);

	List<User> find(Long roleId, int first, int pageSize, String sortField,
			String sortOrder, Map<String, String> filters);

	int getCount(Long roleId, String sortField, String sortOrder,
			Map<String, String> filters);
}

