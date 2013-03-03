package iis.service;

import iis.domain.User;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

public interface UserService extends Serializable {

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
