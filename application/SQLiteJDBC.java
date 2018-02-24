package application;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.sql.*;
import java.util.ArrayList;

public class SQLiteJDBC{
    private String table = "Stored";
    private String UserTable = "Info";
    private String query;


    public SQLiteJDBC(){


        try{
            Class.forName("org.sqlite.JDBC");
            String location = "jdbc:sqlite:DB/passWords.sqlite";
            DriverManager.getConnection(location);

    	}catch(Exception e){
            //System.err.println(e);
    		System.out.println("Except");
        	try {
        		createDatabase();
        		createTables();
        	}catch(Exception e1) {
        		//e1.printStackTrace();
        	}
        }


    }

    public SQLiteJDBC(int n){
    	//if n is not null it is on reset mode

		System.out.println("int n");
    	try {
    		createDatabase();
    		createTables();
    	}catch(Exception e1) {
    		//e1.printStackTrace();
    	}



    }

    private void createDatabase() throws FileNotFoundException, UnsupportedEncodingException {
    	File dir = new File("DB");

    	System.out.println(dir.mkdir());

    	PrintWriter writer = new PrintWriter("DB/passWords.sqlite", "UTF-8");
    	writer.close();
    	System.out.println("Created database file");
    }

    private void createTables() {
    	 // SQLite connection string
        String url = "jdbc:sqlite:DB/passWords.sqlite";

        // SQL statement for creating a new table
        String query1 = "CREATE TABLE IF NOT EXISTS stored (\n"
                + "	id integer PRIMARY KEY ,\n"
                + "	name varchar(30) NOT NULL,\n"
                + "	password varchar(30) NOT NULL\n"
                + ");";

        String query2 = "CREATE TABLE IF NOT EXISTS info (\n"
                + "	name varchar(30) NOT NULL,\n"
                + "	pwd varchar(30) NOT NULL\n"
                + ");";

        try (Connection conn = DriverManager.getConnection(url);
                Statement stmt = conn.createStatement()) {
            // create a new table
            stmt.execute(query1);
            stmt.execute(query2);
            conn.close();
            stmt.close();
            System.out.println("Could not create tables");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        System.out.println("Succesfully created tables");
    }

    //Borrar segun etiqueta
    public boolean Delete(String name) {
    	Connection conn = null;
    	try {
        name = Secure.Encrypt(name);
    	}catch(Exception e) {
    		e.printStackTrace();
    	}
        try{

            Class.forName("org.sqlite.JDBC");
            conn = DriverManager.getConnection("jdbc:sqlite:DB/passWords.sqlite");
            conn.setAutoCommit(false);

            Statement stmnt = conn.createStatement();
            query = String.format("DELETE FROM %s WHERE name = '%s'",table, name);

            stmnt.executeUpdate(query);

            stmnt.close();
            conn.commit();
            conn.close();
        }catch(Exception e){
            System.err.println(e);
            return false;
        }

        return true;
    }

    public boolean Insert(String name, String pwd){
        Connection conn = null;
        try {
        name = Secure.Encrypt(name);
        pwd = Secure.Encrypt(pwd);
        }catch(Exception e) {
        	e.printStackTrace();
        }
        try{

            Class.forName("org.sqlite.JDBC");
            conn = DriverManager.getConnection("jdbc:sqlite:DB/passWords.sqlite");
            conn.setAutoCommit(false);

            Statement stmnt = conn.createStatement();
            query = String.format("INSERT INTO %s (name, password) VALUES ('%s','%s')",table, name , pwd);

            stmnt.executeUpdate(query);

            stmnt.close();
            conn.commit();
            conn.close();
        }catch(Exception e){
            System.err.println(e);
            return false;
        }

        return true;
    }


    public boolean UpdatePWD(String pwd, String id){

        Connection conn = null;
        try {
        pwd = Secure.Encrypt(pwd);
        }catch(Exception e) {
        	e.printStackTrace();
        }
        try{

            Class.forName("org.sqlite.JDBC");
            conn = DriverManager.getConnection("jdbc:sqlite:DB/passWords.sqlite");
            conn.setAutoCommit(false);

            Statement stmnt = conn.createStatement();
            query = String.format("UPDATE %s set password = '%s' WHERE id = %s",table, pwd , id);
            System.out.println("DEBUG");
            stmnt.executeUpdate(query);

            stmnt.close();
            conn.commit();
            conn.close();
        }catch(Exception e){
            e.printStackTrace();
            return false;
        }
        System.out.println("DEBUG");
        return true;
    }

    public boolean UpdateName(String name, String id){
        Connection conn = null;
        try {
        name = Secure.Encrypt(name);

        }catch(Exception e) {
        	e.printStackTrace();
        }
        try{

            Class.forName("org.sqlite.JDBC");
            conn = DriverManager.getConnection("jdbc:sqlite:DB/passWords.sqlite");
            conn.setAutoCommit(false);

            Statement stmnt = conn.createStatement();
            query = String.format("UPDATE %s set NAME = '%s' WHERE id = %s",table, name , id);

            stmnt.executeUpdate(query);

            stmnt.close();
            conn.commit();
            conn.close();
        }catch(Exception e){
            System.err.println(e);
            return false;
        }

        return true;
    }


    public String GetID(String name){
        Connection conn = null;
        String out = "";
        try {
        name = Secure.Encrypt(name);
        }catch(Exception e) {
        	e.printStackTrace();
        }

        try{

            Class.forName("org.sqlite.JDBC");
            conn = DriverManager.getConnection("jdbc:sqlite:DB/passWords.sqlite");
            conn.setAutoCommit(false);

            Statement stmnt = conn.createStatement();
            //query = String.format("INSERT INTO %s (name, password) VALUES ('%s','%s')",table, name , pwd);
            query = String.format("SELECT id FROM Stored WHERE name = '%s'",name);
            ResultSet result = stmnt.executeQuery(query);
            out = result.getString("id");

            System.out.println("HERE "+out);


            result.close();
            stmnt.close();
            conn.close();
        }catch(Exception e){

            System.err.println(e);
            return null;
        }

        return out;
    }


    //Consigue la contrasena segun la etiqueta que de
    public String Get(String name){
        Connection conn = null;
        String out = "";
        try {
        name = Secure.Encrypt(name);
        }catch(Exception e) {
        	e.printStackTrace();
        }

        try{

            Class.forName("org.sqlite.JDBC");
            conn = DriverManager.getConnection("jdbc:sqlite:DB/passWords.sqlite");
            conn.setAutoCommit(false);

            Statement stmnt = conn.createStatement();
            //query = String.format("INSERT INTO %s (name, password) VALUES ('%s','%s')",table, name , pwd);
            query = String.format("SELECT * FROM Stored WHERE name = '%s'",name);
            ResultSet result = stmnt.executeQuery(query);
            out = result.getString("password");
            out = Secure.Decrypt(out);
            System.out.println(out);


            result.close();
            stmnt.close();
            conn.close();
        }catch(Exception e){

            System.err.println(e);
            return null;
        }

        return out;
    }



    //retornar todas las etiquetas
    public ArrayList<String> Get(){
        Connection conn = null;
        ArrayList<String> out = new ArrayList<String>();
        //name = Secure.Encrypt(name);

        try{

            Class.forName("org.sqlite.JDBC");
            conn = DriverManager.getConnection("jdbc:sqlite:DB/passWords.sqlite");
            conn.setAutoCommit(false);

            Statement stmnt = conn.createStatement();

            query = String.format("SELECT name FROM Stored");
            ResultSet result = stmnt.executeQuery(query);
            //out = result.getString("password");
            //out = Secure.Decrypt(out);
            while(result.next()) {
            	String encrypted = result.getString("name");
            	String decrypted = Secure.Decrypt(encrypted);
            	out.add(decrypted);
            }


            result.close();
            stmnt.close();
            conn.close();
        }catch(Exception e){
        	createTables();
            //System.err.println(e);
            return null;
        }

        return out;
    }


    //Revisa base de datos para ver si tiene cuenta
    @SuppressWarnings("unused")
	public boolean revisarCuenta() {
    	Connection conn = null;
        String nameRes = null;

        try{

            Class.forName("org.sqlite.JDBC");
            conn = DriverManager.getConnection("jdbc:sqlite:DB/passWords.sqlite");
            conn.setAutoCommit(false);

            Statement stmnt = conn.createStatement();

            query = String.format("SELECT name FROM Info");
            ResultSet result = stmnt.executeQuery(query);
            //out = result.getString("password");
            //out = Secure.Decrypt(out);

            while(result.next()) {
            	nameRes = result.getString("name");
                //System.out.println(nameRes+" aqui");


            }

            System.out.println(nameRes);



            result.close();
            stmnt.close();
            conn.close();
            if(nameRes == null)
            	return false;
        }catch(Exception e){

            e.printStackTrace();
            return false;
        }

        if(nameRes == null)
    		return false;
        return true;
        //System.out.println(!Secure.Decrypt(nameRes).isEmpty());
        /*
        try {
        	//System.out.println(Secure.Decrypt(nameRes));
        	return !Secure.Decrypt(nameRes).isEmpty();
        }catch(Exception e) {
        	return false;
        }*/
    }

    public boolean guardaUsuario(String nombre, String contra) {

    	try {
	    	nombre = nombre;
	    	contra = Secure.Encrypt(contra);
    	}catch(Exception e) {
    		e.printStackTrace();
    	}

    	Connection conn = null;

        try{

            Class.forName("org.sqlite.JDBC");
            conn = DriverManager.getConnection("jdbc:sqlite:DB/passWords.sqlite");
            conn.setAutoCommit(false);

            Statement stmnt = conn.createStatement();
            query = String.format("INSERT INTO %s (name, pwd) VALUES ('%s','%s')",UserTable, nombre , contra);

            stmnt.executeUpdate(query);

            stmnt.close();
            conn.commit();
            conn.close();
        }catch(Exception e){
            e.printStackTrace();
            return false;
        }
        System.out.println("Usuario guardado");
        return true;


    }

    public boolean iniciarUsuario(String nombre, String contra) {


    	Connection conn = null;
    	String decryptedN = "";
    	String decryptedP = "";
    	boolean out = false;

    	try{

            Class.forName("org.sqlite.JDBC");
            conn = DriverManager.getConnection("jdbc:sqlite:DB/passWords.sqlite");
            conn.setAutoCommit(false);

            Statement stmnt = conn.createStatement();

            query = String.format("SELECT * FROM info");
            ResultSet result = stmnt.executeQuery(query);
            //out = result.getString("password");
            //out = Secure.Decrypt(out);
            while(result.next()) {
            	String encryptedN = result.getString("name");
            	decryptedN = encryptedN;
            	String encryptedP = result.getString("pwd");
            	System.out.println("okEn "+encryptedP);
            	decryptedP = Secure.Decrypt(encryptedP);
            	System.out.println("okDe "+decryptedP);

            }
            System.out.println(decryptedN+decryptedP);
             out = (decryptedN.equals(nombre) && decryptedP.equals(contra));



            result.close();
            stmnt.close();
            conn.close();
        }catch(Exception e){
            System.err.println(e);
            return false;
        }

        return out;


    }

}
