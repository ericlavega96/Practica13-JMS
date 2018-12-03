package services;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class ServiciosBootStrap {

    /**
     *
     * @throws SQLException
     */
    private static ServiciosBootStrap instancia;

    private ServiciosBootStrap(){

    }

    public static ServiciosBootStrap getInstancia(){
        if(instancia == null){
            instancia=new ServiciosBootStrap();
        }
        return instancia;
    }


    public void init(){
    }

}