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

	public Password(int MIN_LENGTH, int MAX_LENGTH) {
		PasswordUtils.MAX_LENGTH = MAX_LENGTH;
		PasswordUtils.MIN_LENGTH = MIN_LENGTH;
		this.setLength();
		this.setPassword();
	}

	public void setLength(int length) {
		if (PasswordUtils.MIN_LENGTH <= length && length <= PasswordUtils.MAX_LENGTH)
			this.passwordLength = length;
	}

	public void setLength() {
		this.passwordLength = new SecureRandom().nextInt(PasswordUtils.MIN_LENGTH, PasswordUtils.MAX_LENGTH);
	}

	public int getLength() {
		return this.passwordLength;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setPassword() {
		this.password = PasswordUtils.generatePassword(this.passwordLength);
	}

	public final String getPassword() {
		return this.password;
	}
}
