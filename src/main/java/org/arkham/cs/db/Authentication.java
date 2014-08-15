package org.arkham.cs.db;

public class Authentication {
	
    public static String sqlhost = "72.8.130.42";
    public static String sqldb = "arkham_cosmetics";
    public static String sqluser = "root"; // TODO: Replace w/ slave account, setup perms.
    public static String sqlpass = "oEtXfeo0AzpCbPG05QkumNA67IafKrTs4mz0";
    public static int sqlport = 3306;
    public static String sqlurl = "jdbc:mysql://" + sqlhost + ":" + sqlport + "/" + sqldb;

}
