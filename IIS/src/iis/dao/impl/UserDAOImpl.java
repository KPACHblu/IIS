package iis.dao.impl;

import iis.dao.UserDAO;
import iis.domain.User;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

@Repository
public class UserDAOImpl extends GenericDAOImpl<User> implements UserDAO {

	private static final long serialVersionUID = 7101145854731575271L;

	@Override
	public User findByLogin(String login) {
		return executeNamedQuery(User.FIND_BY_LOGIN_QUERY,
				new Object[] { login });
	}

	@Override
	public List<User> findByRole(Long roleId) {
		return executeNamedQueryList(User.FIND_BY_ROLE_ID, new Object[] { roleId });
	}
	
	@Override
	public List<User> findByRole(String roleName) {
		return executeNamedQueryList(User.FIND_BY_ROLE_Name, new Object[] { roleName });
	}

	@Override
	public List<User> find(Long roleId, int first, int pageSize,
			String sortField, String sortOrder, Map<String, String> filters) {

		StringBuilder query = new StringBuilder();
		query.append("SELECT OBJECT(e) FROM User AS e INNER JOIN e.roles r WHERE r.id = "
				+ roleId);
		whereClause(sortField, sortOrder, filters, true, query);
		return executeQueryList(query.toString(), first, pageSize);
	}

	@Override
	public int getCount(Long roleId, String sortField, String sortOrder,
			Map<String, String> filters) {

		StringBuilder query = new StringBuilder();
		query.append("SELECT COUNT(e) FROM User AS e INNER JOIN e.roles r WHERE r.id = "
				+ roleId);
		whereClause(sortField, sortOrder, filters, true, query);
		Long count = (Long) executeQuerySingleResult(query.toString());
		if (count == null) {
			count = 0l;
		}
		return count.intValue();
	}

}
