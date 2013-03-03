package iis.dao.impl;

import iis.dao.RoleDAO;
import iis.domain.Role;

import org.springframework.stereotype.Repository;

@Repository
public class RoleDAOImpl extends GenericDAOImpl<Role> implements RoleDAO {

	private static final long serialVersionUID = -7564566685676111421L;

	@Override
	public Role findByName(String roleName) {
		return executeNamedQuery(Role.FIND_BY_NAME_QUERY,
				new Object[] { roleName });
	}

}
