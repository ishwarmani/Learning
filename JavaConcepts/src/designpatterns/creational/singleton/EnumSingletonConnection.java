package designpatterns.creational.singleton;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public enum EnumSingletonConnection {

    INSTANCE;

    private Connection connection;

    EnumSingletonConnection(){
        try{
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/poc","root","root");
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    // Static getter
    public static EnumSingletonConnection getInstance() {
        return INSTANCE;
    }

    public Connection getConnection() {
        return connection;
    }
}

/*
Now you use can use final Singleton s = Singleton.getInstance().
Remember that since this is an enum you can always access this via Singleton.INSTANCE as well.
 */