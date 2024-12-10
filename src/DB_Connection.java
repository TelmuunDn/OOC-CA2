/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author telmuun
 */
public abstract class DB_Connection {
// the following is the basic database connection information.
// we will use these variables to continually connect and write to the database.

// this way it is scalable coding as we dont have to worry about our code in case any of the SQL server information changes in the future
protected final static String DB_BASE_URL = "jdbc:mysql://localhost";
protected final static String USER = "ooc2023";
protected final static String PASSWORD = "ooc2023";
// the schema name and the table names that we will need
protected final static String DB_NAME = "tax_office";
protected final static String TABLE = "user_data";
protected final static String TABLE2 = "admin_data";


// now we create the final database URL with the schema name
protected final static String DB_URL = DB_BASE_URL + "/" + DB_NAME;
}
