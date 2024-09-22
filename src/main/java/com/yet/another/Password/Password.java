package com.yet.another.Password;

import java.security.SecureRandom;

public class Password {

	private String password;
	private int passwordLength;

	public Password() {
		/*
		 * This is an empty constructor that will be used when we want to transfer an
		 * password type object
		 * the attributes/members of this class with be set using setter and will be
		 * got using getter
		 */
		this.setLength();
		this.setPassword();
	}

	public void setLength(int length) {
		if (PasswordUtils.MIN_LENGTH <= length && length <= PasswordUtils.MAX_LENGTH)
			this.passwordLength = length;
	}

	private void setLength() {
		this.passwordLength = new SecureRandom().nextInt(PasswordUtils.MIN_LENGTH, PasswordUtils.MAX_LENGTH);
	}

	public int getLength() {
		return this.passwordLength;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	private void setPassword() {
		this.password = PasswordUtils.generatePassword(this.passwordLength);
	}

	public final String getPassword() {
		return this.password;
	}
}
