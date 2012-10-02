package sportsstore.domain;

public class DbUser {
	private String username;
	private String password;
	/**
	 * Access level of the user. 1 = Admin user 2 = Regular user
	 */
	private Integer access;

	/**
	 * @return the username
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * @param username
	 *            the username to set
	 */
	public void setUsername(String username) {
		this.username = username;
	}

	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password
	 *            the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * @return the access
	 */
	public Integer getAccess() {
		return access;
	}

	/**
	 * @param access
	 *            the access to set
	 */
	public void setAccess(Integer access) {
		this.access = access;
	}
}
