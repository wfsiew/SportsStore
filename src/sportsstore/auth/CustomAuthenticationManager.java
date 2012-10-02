package sportsstore.auth;

import java.util.*;

import sportsstore.domain.DbUser;
import sportsstore.domain.AuthUsers;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.GrantedAuthorityImpl;

public class CustomAuthenticationManager implements AuthenticationManager {
	private AuthUsers user = new AuthUsers();
	private Md5PasswordEncoder passwordEncoder = new Md5PasswordEncoder();

	public Authentication authenticate(Authentication auth)
			throws AuthenticationException {
		DbUser o = null;

		try {
			o = user.getUser(auth.getName());
		}

		catch (Exception e) {
			throw new BadCredentialsException("User does not exists!");
		}

		if (o.getPassword().equals((String) auth.getCredentials()) == false) {
			throw new BadCredentialsException("Wrong password!");
		}

		if (auth.getName().equals(auth.getCredentials())) {
			throw new BadCredentialsException(
					"Entered username and password are the same!");
		}

		else {
			return new UsernamePasswordAuthenticationToken(auth.getName(),
					auth.getCredentials(), getAuthorities(o.getAccess()));
		}
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
