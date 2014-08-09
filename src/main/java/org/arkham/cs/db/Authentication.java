package org.arkham.cs.db;

public class Authentication {
	
    public static String sqlhost = "localhost";
    public static String sqldb = "arkham_cosmetics";
    public static String sqluser = "root"; // TODO: Replace w/ slave account, setup perms.
    public static String sqlpass = "pippintook";
    public static int sqlport = 3306;
    public static final String sqlurl = "jdbc:mysql://" + sqlhost + ":" + sqlport + "/" + sqldb;

}
