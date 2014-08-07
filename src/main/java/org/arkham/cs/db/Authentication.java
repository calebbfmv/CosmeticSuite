package org.arkham.cs.db;

public class Authentication {
	
    public static String sql_host = "108.61.140.226";
    public static String sql_db = "arkham_purchases";
    public static String sql_user = "root"; // TODO: Replace w/ slave account, setup perms.
    public static String sql_pass = "7F4335A7D7FB892A174BAEFE9709282E71FB85C1AF9";
    public static int sql_port = 3306;
    public static final String sql_url = "jdbc:mysql://" + sql_host + ":" + sql_port + "/" + sql_db;

}
