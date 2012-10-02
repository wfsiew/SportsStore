package sportsstore.domain;

import java.util.*;

public class AuthUsers {
	
	public DbUser getUser(String username) {
		List<DbUser> users = internalDatabase();
		
		for (DbUser o : users) {
			if (o.getUsername().equalsIgnoreCase(username))
				return o;
		}
		
		throw new RuntimeException("User does not exist!");
	}
	
	private List<DbUser> internalDatabase() {
		List<DbUser> users = new ArrayList<DbUser>();
		
		DbUser o = null;
		
		o = new DbUser();
		o.setUsername("admin");
		o.setPassword("admin123");
		o.setAccess(1);
		
		users.add(o);
		
		o = new DbUser();
		o.setUsername("wfsiew");
		o.setPassword("pwd");
		o.setAccess(1);
		
		users.add(o);
		
		return users;
	}
}
