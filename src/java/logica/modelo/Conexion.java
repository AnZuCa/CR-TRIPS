/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logica.modelo;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
/**
 *
 * @author hp
 */
public class Conexion {
<<<<<<< HEAD
   private String USERNAME="root";
   private String PASSWORD="root";
    //private String USERNAME="globales@mysqlglobales";
    //private String PASSWORD="Lala1234";
=======
//    private String USERNAME="root";
//    private String PASSWORD="root";
    private String USERNAME="globales@mysqlglobales";
    private String PASSWORD="Lala1234";
>>>>>>> parent of 2f66e5a (Cambios)
    private String HOST="localhost";
    private String PORT="3306";
    private String DATABASE="cr_trips";
    private String CLASSNAME="com.mysql.cj.jdbc.Driver";
    //private String URL="jdbc:mysql://"+HOST+":"+PORT+"/"+DATABASE+"?useSSL=false";
<<<<<<< HEAD
    private String URL="jdbc:mysql://"+HOST+":"+PORT+"/"+DATABASE+"?allowPublicKeyRetrieval=true&useSSL=false";
    //private String URL="jdbc:mysql://mysqlglobales.mysql.database.azure.com:3306/cr_trips?useSSL=false&requireSSL=false";
=======
    private String URL="jdbc:mysql://mysqlglobales.mysql.database.azure.com:3306/cr_trips?useSSL=false&requireSSL=false";
>>>>>>> parent of 2f66e5a (Cambios)
    private Connection con;
    
    public Conexion() {
        try{
           Class.forName(CLASSNAME);
           con = DriverManager.getConnection(URL,USERNAME,PASSWORD);
           //con = DriverManager.getConnection(URL);
           System.out.println("Conexion establecida");
        }catch(ClassNotFoundException | SQLException e){
            System.err.println("Error"+ e); 
        }
    }
    
    public Connection getConexion(){
        return con;
    }
       
    public static void main(String[] args){
        Conexion con= new Conexion();
 
    }
}
