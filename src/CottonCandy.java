import java.sql.Connection;
import java.util.List;
import java.util.Scanner;

public class CottonCandy {

    private List<String> colors = List.of("Pink", "Blue", "Yellow", "Purple", "Orange", "Green", "White");
    private List<String> shapes = List.of("Flower", "Butterfly", "Star", "Heart", "Moon", "Paw", "No Shape");

    private Scanner scanner = new Scanner(System.in);

    public static void main(String[] args){
        CottonCandy ccMachine = new CottonCandy();
        DbFunctions db = new DbFunctions();
        Connection conn = db.connect_to_db("tutdb", "postgres", "basecamp");

        db.createColorTable(conn);
        db.createShapeTable(conn);
        db.createCottonCandyTable(conn);

        ccMachine.makeCottonCandy(conn, db);
    }

    public void makeCottonCandy(Connection conn, DbFunctions db) {
        // Insert colors into the color table
        for (String color : colors) {
            db.insertColor(conn, color);
        }

        // Insert shapes into the shape table
        for (String shape : shapes) {
            db.insertShape(conn, shape);
        }

        String colorSelection = "";
        String shapeSelection = "";

        while (!colors.contains(colorSelection)) {
            System.out.println("Welcome to the cotton candy machine! Please choose a color!");
            System.out.println("Here are the available colors:");
            for (String color : colors) {
                System.out.println(color);
            }
            colorSelection = scanner.nextLine();
            if (!colors.contains(colorSelection)) {
                System.out.println(colorSelection + " is not an appropriate color, please try again!");
            }
        }

        while (!shapes.contains(shapeSelection)) {
            System.out.println("Please choose a shape!");
            System.out.println("Available shapes:");
            for (String shape : shapes) {
                System.out.println(shape);
            }
            shapeSelection = scanner.nextLine();
            if (!shapes.contains(shapeSelection)) {
                System.out.println(shapeSelection + " is not an option, try again!");
            }
        }

        db.insertCottonCandy(conn, colorSelection, shapeSelection);
        System.out.println("A " + colorSelection +  "cotton candy" + " with a " + shapeSelection + " shape, excellent choice! Enjoy");
    }
}


//cotton candy machine with shapes and colors
//Pink-Strawberry
//Blue-Vanilla
//Yellow-Banana
//Purple-Grape
//Orange-Dreamsicle
//Green- Green Bean
//White- Coconut

//flower
//butterfly
//star
//heart
//moon
//no shape
//paw