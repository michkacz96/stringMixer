package org.example.stringMixer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class StringMixerApplication {
	private static String conn = "jdbc:mysql://localhost/";
	private static String table = "stringmixer";
	private static String db_user = "root";
	private static String db_pwd = "";

	public static void main(String[] args) {
		SpringApplication.run(StringMixerApplication.class, args);
	}

	/**
	 * return full sql connection string
	 * @return variable conn + variable table
	 */
	public static String get_sql_connection(){
		return get_conn() + get_table();
	}

	/**
	 * return sql connection string
	 * @return variable conn
	 */
	public static String get_conn(){
		return conn;
	}

	/**
	 * return sql table
	 * @return variable table
	 */
	public static String get_table(){
		return table;
	}

	/**
	 * return sql database user
	 * @return variable db_user
	 */
	public static String get_db_user(){
		return db_user;
	}

	/**
	 * return sql database password
	 * @return variable db_pwd
	 */
	public static String get_db_pwd(){
		return db_pwd;
	}

}
