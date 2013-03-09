package iis.core.security;

import iis.dao.UserDAO;
import iis.domain.User;

import java.io.Serializable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service("userDetailsService")
public class UserDetailsServiceImpl implements UserDetailsService, Serializable {

	private static final long serialVersionUID = -8923106683254667369L;

	@Autowired
	private UserDAO userDAO;
	
	@Autowired
	private Assembler assembler;

	public UserDetails loadUserByUsername(String username)
			throws UsernameNotFoundException, DataAccessException {
		User userEntity;
		try {
			userEntity = userDAO.findByLogin(username);
			if (userEntity == null) {
				throw new UsernameNotFoundException("user not found");
			}
		} catch (Exception e) {
			throw new UsernameNotFoundException("user not found");
		}
		return assembler.buildUserFromUserEntity(userEntity);
	}

}
