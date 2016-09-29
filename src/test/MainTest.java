import org.junit.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;

import static org.junit.Assert.*;

/**
 * Created by joshuakeough on 9/29/16.
 */
public class MainTest {
    public Connection startConnection() throws SQLException {
        Connection conn = DriverManager.getConnection("jdbc:h2:mem:test");
        Main.createTables(conn);
        return conn;
    }
    @Test
    public void insertUser() throws Exception {
        Connection conn = startConnection();
        Main.insertUser(conn, "alice", "1234", "1234");
        ArrayList<User> users = Main.selectUsers(conn);
        User user = users.get(0);
        conn.close();
        assertTrue(user != null);

    }

    @Test
    public void selectUsers() throws Exception {
        Connection conn = startConnection();
        Main.insertUser(conn, "alice", "123", "123");


        ArrayList<User> users = Main.selectUsers(conn);
        conn.close();

        assertTrue(users.size()==1);

    }

    @Test
    public void deleteUser() throws Exception {
        Connection conn = startConnection();
        Main.insertUser(conn, "alice", "123", "123");

        Main.deleteUser(conn,1);
        ArrayList<User> items = Main.selectUsers(conn);


        conn.close();

        assertTrue(items.size()==0);

    }

    @Test
    public void updateUser() throws Exception {
        Connection conn = startConnection();
        Main.insertUser(conn, "alice", "123" , "123");


        ArrayList<User> users = Main.selectUsers(conn);
        User item = users.get(0);
        Main.updateUser(conn, 1, "123" , "1234" , "1234");
        ArrayList<User> users1 = Main.selectUsers(conn);
        User item1 = users1.get(0);


        conn.close();

        assertTrue(item != item1);

    }

}