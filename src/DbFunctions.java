import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.*;

public class DbFunctions {
    public Connection connect_to_db(String dbname, String user, String pass) {
        Connection conn = null;
        try {
            Class.forName("org.postgresql.Driver");
            conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/" + dbname, user, pass);
            if (conn != null) {
                System.out.println("Connection Established");
            } else {
                System.out.println("Connection Failed");
            }

        } catch (Exception e) {
            System.out.println(e);
        }
        return conn;

    }

//    public void createTable(Connection conn, String table_name) {
//        Statement statement;
//        try {
//            String query = "create table " + table_name + "(empid SERIAL, name varchar(200), address varchar(200), primary key(empid));";
//            statement = conn.createStatement();
//            statement.executeUpdate(query);
//            System.out.println("Table Created");
//
//        } catch (Exception e) {
//            System.out.println(e);
//        }
//    }


    //--------------TABLES------------------------------//
public void createColorTable(Connection conn) {
    Statement statement;
    try {
        String query = "create table color (" +
                "color_id SERIAL, " +
                "color_name varchar(200), " +
                "primary key(color_id));";
        statement = conn.createStatement();
        statement.executeUpdate(query);
        System.out.println("Color Table Created");

    } catch (Exception e) {
        System.out.println(e);
    }
}

    public void createShapeTable(Connection conn) {
        Statement statement;
        try {
            String query = "create table shape (" +
                    "shape_id SERIAL, " +
                    "shape_name varchar(200), " +
                    "primary key(shape_id));";
            statement = conn.createStatement();
            statement.executeUpdate(query);
            System.out.println("Shape Table Created");

        } catch (Exception e) {
            System.out.println(e);
        }
    }

public void createCottonCandyTable(Connection conn) {
    Statement statement;
    try {
        String query = "create table cotton_candy (" +
                "cotton_candy_id SERIAL, " +
                "color_id int, " +
                "shape_id int, " +
                "primary key(cotton_candy_id), " +
                "foreign key(color_id) references color(color_id), " +
                "foreign key(shape_id) references shape(shape_id));";
        statement = conn.createStatement();
        statement.executeUpdate(query);
        System.out.println("Cotton Candy Table Created");

    } catch (Exception e) {
        System.out.println(e);
    }
}
//------Inserting info------

//public void insert_row(Connection conn, String table_name, String name, String address) {
//    Statement statement;
//    try {
//        String query = String.format("insert into %s(name,address) values('%s', '%s');", table_name, name, address);
//        statement = conn.createStatement();
//        statement.executeUpdate(query);
//        System.out.println("Row inserted");
//
//    } catch (Exception e) {
//        System.out.println(e);
//    }
//
//
//}

public void insertColor(Connection conn, String colorName) {
    PreparedStatement statement;
    try {
        String query = "INSERT INTO color (color_name) VALUES (?);";
        statement = conn.prepareStatement(query);
        statement.setString(1, colorName);
        statement.executeUpdate();
        System.out.println("Inserted color: " + colorName);

    } catch (Exception e) {
        System.out.println(e);
    }
}

    public void insertShape(Connection conn, String shapeName) {
        PreparedStatement statement;
        try {
            String query = "INSERT INTO shape (shape_name) VALUES (?);";
            statement = conn.prepareStatement(query);
            statement.setString(1, shapeName);
            statement.executeUpdate();
            System.out.println("Inserted shape: " + shapeName);

        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void insertCottonCandy(Connection conn, String colorName, String shapeName) {
        PreparedStatement statement;
        try {
            // -----------Get color_id
            String getColorIdQuery = "SELECT color_id FROM color WHERE color_name = ?;";
            statement = conn.prepareStatement(getColorIdQuery);
            statement.setString(1, colorName);
            ResultSet colorResult = statement.executeQuery();
            colorResult.next();
            int colorId = colorResult.getInt("color_id");

            // ------------Get shape_id
            String getShapeIdQuery = "SELECT shape_id FROM shape WHERE shape_name = ?;";
            statement = conn.prepareStatement(getShapeIdQuery);
            statement.setString(1, shapeName);
            ResultSet shapeResult = statement.executeQuery();
            shapeResult.next();
            int shapeId = shapeResult.getInt("shape_id");

            // ------------------Insert into cotton_candy table
            String insertCottonCandyQuery = "INSERT INTO cotton_candy (color_id, shape_id) VALUES (?, ?);";
            statement = conn.prepareStatement(insertCottonCandyQuery);
            statement.setInt(1, colorId);
            statement.setInt(2, shapeId);
            statement.executeUpdate();
            System.out.println("Inserted cotton candy with color: " + colorName + " and shape: " + shapeName);

        } catch (Exception e) {
            System.out.println(e);
        }
    }

//    public void read_data(Connection conn, String table_name) {
//        Statement statement;
//        ResultSet rs = null;
//        try {
//            String query = String.format("select * from %s", table_name);
//            statement = conn.createStatement();
//            rs = statement.executeQuery(query);
//            while (rs.next()) {
//                System.out.print(rs.getString("empid") + " ");
//                System.out.print(rs.getString("name") + " ");
//                System.out.print(rs.getString("Address") + " ");
//            }
//        } catch (Exception e) {
//            System.out.println(e);
//        }
//    }

//    public void search_by_id(Connection conn, String table_name, int id) {
//        Statement statement;
//        ResultSet rs = null;
//        try {
//            String query = String.format("select * from %s where empid=%s", table_name, id);
//            statement = conn.createStatement();
//            rs = statement.executeQuery(query);
//            while (rs.next()) {
//                System.out.println(rs.getString("empid") + " ");
//                System.out.println(rs.getString("name") + " ");
//                System.out.println(rs.getString("address") + " ");
//            }
//
//        } catch (Exception e) {
//            System.out.println(e);
//        }
//    }

//    public void delete_row_by_name(Connection conn, String table_name, String name) {
//        Statement statement;
//        try {
//            String query = String.format("delete from %s where name='%s'", table_name, name);
//            statement = conn.createStatement();
//            statement.executeUpdate(query);
//            System.out.println("Data Deleted");
//        } catch (Exception e) {
//            System.out.println(e);
//        }
//    }

//    public void delete_row_by_id(Connection conn, String table_name, int id) {
//        Statement statement;
//        try {
//            String query = String.format("delete from %s where empid=%s", table_name, id);
//            statement = conn.createStatement();
//            statement.executeUpdate(query);
//            System.out.println("Data Deleted");
//        } catch (Exception e) {
//            System.out.println(e);
//        }
//    }

//    public void delete_table(Connection conn, String table_name) {
//        Statement statement;
//        try {
//            String query = String.format("drop table %s", table_name);
//            statement = conn.createStatement();
//            statement.executeUpdate(query);
//            System.out.println("Table Deleted");
//        } catch (Exception e) {
//            System.out.println(e);
//
//        }
//    }

}