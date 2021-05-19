package r;

import b.AnimeNewBot;
import com.mysql.jdbc.Connection;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Timer;
import java.util.TimerTask;

public class Main {
    public static void main(String[] args) {
        String DB_URL = "jdbc:mysql://localhost:3306/animenews";
        String USER_NAME = "root";
        String PASSWORD = "123456789";
        System.out.println("Start crawler");
        PetControl petControl = new PetControl();
        petControl.run();
//        try {
//            // connnect to database 'testdb'
//            Connection conn = getConnection(DB_URL, USER_NAME, PASSWORD);
//            // crate statement
//            Statement stmt = conn.createStatement();
//            // get data from table 'student'
//            ResultSet rs = stmt.executeQuery("select * from animenews.student");
//            // show data
//            while (rs.next()) {
//                System.out.println(rs.getInt(1) + "  " + rs.getString(2)
//                        + "  " + rs.getString(3));
//            }
//            // close connection
//            conn.close();
//        } catch (Exception ex) {
//            ex.printStackTrace();
//        }
    }


//    /**
//     * create connection
//     *
//     * @author viettuts.vn
//     * @param dbURL: database's url
//     * @param userName: username is used to login
//     * @param password: password is used to login
//     * @return connection
//     */
//    public static Connection getConnection(String dbURL, String userName,
//                                           String password) {
//        Connection conn = null;
//        try {
//            Class.forName("com.mysql.jdbc.Driver");
//            conn = (Connection) DriverManager.getConnection(dbURL, userName, password);
//            System.out.println("connect successfully!");
//        } catch (Exception ex) {
//            System.out.println("connect failure!");
//            ex.printStackTrace();
//        }
//        return conn;
//    }

    public static class PetControl {
        long delay = 0;
        long period = 1000 * 60 * 15;
        Timer timer = new Timer();

        AnimeNewBot animeNewBot = new AnimeNewBot();

        PetControl() {
            AppData.getInstance();
        }

        void run() {
            System.out.println("Start schedule for all bot");
            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    System.out.println("Check all bot");
                    if (!animeNewBot.isRunning()) animeNewBot.run();
                }
            }, delay, period);
        }
    }
}
