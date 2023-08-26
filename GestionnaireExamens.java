
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class GestionnaireExamens {
    // port number is optional in database server url
    String MYSQL_SERVER_URL = "jdbc:mysql://localhost:3306/";
    String DB_URL = "jdbc:mysql://localhost:3306/MyFaculty";
    // ?serverTimezone=UTC
    String USERNAME = "root";
    String PASSWORD = "";

    Connection connection = null;
    public java.sql.Statement statement = null;
    ResultSet resultSet = null;
    public Notes nt;

    public GestionnaireExamens() {

    }

    {
        try {
            // connect to MYSQL server
            Connection connection = DriverManager.getConnection(MYSQL_SERVER_URL,
                    USERNAME, PASSWORD);
            statement = connection.createStatement();
            statement.executeUpdate("CREATE DATABASE IF NOT EXISTS MyFaculty");
            connection.close();
            statement.close();
            // Second, connect to database and create table "notes" if not created
            connection = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
            statement = connection.createStatement();

            String sql = "CREATE TABLE IF NOT EXISTS notes (" +
                    "id INT( 10 )NOT NULL PRIMARY KEY AUTO_INCREMENT," + " nom VARCHAR(200) NOT NULL,"
                    + " email VARCHAR(200) NOT NULL UNIQUE," + " securite VARCHAR(200),"
                    + " reseau VARCHAR(200), Java VARCHAR(200)," + "php VARCHAR(200)," + "android VARCHAR(200),"
                    + "sgbd VARCHAR(200)," + " math VARCHAR(200)," + " francais VARCHAR(200),"
                    + " anglais VARCHAR(200)," + " pfe VARCHAR(200)"
                    + ")";
            statement.executeUpdate(sql);
            statement.close();

        } catch (SQLException sqlEx) {
            sqlEx.printStackTrace();
        }
    }

    public Notes lireNotes(String email) throws SQLException {
        // read all rows from table users

        connection = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
        statement = connection.createStatement();

        resultSet = statement.executeQuery("SELECT *  FROM notes WHERE email = '" + email + "';");
        while (resultSet.next()) {
            String securite = resultSet.getString(4);
            String reseau = resultSet.getString(5);
            String Java = resultSet.getString(6);
            String php = resultSet.getString(7);
            String android = resultSet.getString(8);
            String sgbd = resultSet.getString(9);
            String math = resultSet.getString(10);
            String francais = resultSet.getString(11);
            String anglais = resultSet.getString(12);
            String pfe = resultSet.getString(13);
            nt = new Notes(securite, reseau, Java, php, android, sgbd, math, francais, anglais, pfe);
            return nt;
        }
        // read the rows using resultSet
        statement.close();
        return null;

    }

    public void sauvegarderNotesEtudiant(String email, String nom, String securite, String reseau, String Java,
            String php, String android,
            String sgbd, String math, String francais, String anglais, String pfe) throws SQLException {
        connection = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
        statement = connection.createStatement();

        resultSet = statement.executeQuery("SELECT *  FROM notes WHERE email = '" + email + "';");
        if (resultSet.next()) {

            if (email != null) {
                String sqlUpdate = "UPDATE notes SET "
                        + "nom = '" + nom + "',"
                        + "email = '" + email + "', "
                        + "securite = '" + securite + "',"
                        + "reseau = '" + reseau + "', "
                        + "Java = '" + Java + "',"
                        + "php = '" + php + "',"
                        + "android = '" + android + "',"
                        + "sgbd = '" + sgbd + "', "
                        + "math = '" + math + "', "
                        + "francais = '" + francais + "',"
                        + "anglais = '" + anglais + "',"
                        + "pfe = '" + pfe + "' WHERE email = '" + email + "';";

                int rows = statement.executeUpdate(sqlUpdate);
                if (rows > 0) {
                    System.out.println("Row updatd successfully");
                }
            } else {
                String sqlInsert2 = "INSERT INTO notes (nom, email,securite,reseau,Java,php,android,sgbd,math,francais,anglais,pfe) "
                        +
                        "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ? , ?, ?)";
                PreparedStatement preparedStatement = connection.prepareStatement(sqlInsert2);
                preparedStatement.setString(1, nom);
                preparedStatement.setString(2, email);
                preparedStatement.setString(3, securite);
                preparedStatement.setString(4, reseau);
                preparedStatement.setString(5, Java);
                preparedStatement.setString(6, php);
                preparedStatement.setString(7, android);
                preparedStatement.setString(8, sgbd);
                preparedStatement.setString(9, math);
                preparedStatement.setString(10, francais);
                preparedStatement.setString(11, anglais);
                preparedStatement.setString(12, pfe);

                int addedRows = preparedStatement.executeUpdate();
                if (addedRows > 0) {
                    System.out.println("Row added successfully");
                }

            }

        } else {
            System.out.println("empty");
            String sqlInsert2 = "INSERT INTO notes (nom, email,securite,reseau,Java,php,android,sgbd,math,francais,anglais,pfe) "
                    +
                    "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ? , ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sqlInsert2);
            preparedStatement.setString(1, nom);
            preparedStatement.setString(2, email);
            preparedStatement.setString(3, securite);
            preparedStatement.setString(4, reseau);
            preparedStatement.setString(5, Java);
            preparedStatement.setString(6, php);
            preparedStatement.setString(7, android);
            preparedStatement.setString(8, sgbd);
            preparedStatement.setString(9, math);
            preparedStatement.setString(10, francais);
            preparedStatement.setString(11, anglais);
            preparedStatement.setString(12, pfe);

            int addedRows = preparedStatement.executeUpdate();
            if (addedRows > 0) {
                System.out.println("Row added successfully");
            }

        }
        statement.close();
    }
}
