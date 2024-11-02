package com.yet.another.Database.DatabaseUtils.Utility;

public class Defaults {
	public static int DEFAULT_PORT = 3306;

	public static String DEFAULT_DATABASE_TYPE = "mariadb";
	public static String DEFAULT_URL = String.format("jdbc:%s://localhost:%d", DEFAULT_DATABASE_TYPE, DEFAULT_PORT);
	public static String DEFAULT_DATABASE = "YetAnotherDatabase";

	final public static String[] attribute = new String[] { "employee_id", "first_name", "last_name", "dob", "role",
			"department", "email" };

	public static void changeDefaultDatabaseName(final String database) {
		DEFAULT_DATABASE = database;
	}

	public static void changeDefaultDatabaseType(final String databaseType) {
		DEFAULT_DATABASE_TYPE = databaseType;
	}
}
