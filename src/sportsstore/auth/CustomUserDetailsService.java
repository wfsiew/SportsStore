package sportsstore.auth;

import java.util.*;

import sportsstore.domain.AuthUsers;
import sportsstore.domain.DbUser;

import org.springframework.dao.DataAccessException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.GrantedAuthorityImpl;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
public class CustomUserDetailsService implements UserDetailsService {
	private AuthUsers user = new AuthUsers();

	public UserDetails loadUserByUsername(String username)
			throws UsernameNotFoundException {
		UserDetails o = null;
		
		try {
			DbUser u = user.getUser(username);
			
			o = new User(u.getUsername(),
					u.getPassword().toLowerCase(),
					true,
					true,
					true,
					true,
					getAuthorities(u.getAccess()));
		}
		
		catch (Exception e) {
			// TODO: handle exception
		}
		
		return o;
	}
	
	public Collection<GrantedAuthority> getAuthorities(Integer access) {
		List<GrantedAuthority> authList = new ArrayList<GrantedAuthority>(2);
		
		authList.add(new GrantedAuthorityImpl("ROLE_USER"));
		
		if (access.compareTo(1) == 0) {
			authList.add(new GrantedAuthorityImpl("ROLE_ADMIN"));
		}
		
		return authList;
	}
}
