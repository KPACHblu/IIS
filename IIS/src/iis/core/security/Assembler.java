package iis.core.security;

import iis.domain.Role;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.GrantedAuthorityImpl;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;

@Service("assembler")
public class Assembler {

	User buildUserFromUserEntity(iis.domain.User userEntity) {
		String username = userEntity.getLogin();
		String password = userEntity.getPassword();
		boolean enabled = userEntity.getActive();
		boolean accountNonExpired = userEntity.getActive();
		boolean credentialsNonExpired = userEntity.getActive();
		boolean accountNonLocked = userEntity.getActive();

		Collection<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		for (Role role : userEntity.getRoles()) {
			authorities.add(new GrantedAuthorityImpl(role.getName()));
		}

		User user = new User(username, password, enabled, accountNonExpired,
				credentialsNonExpired, accountNonLocked, authorities);
		return user;
	}

}
