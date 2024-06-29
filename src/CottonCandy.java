import java.sql.Connection;
import java.util.*;


class CottonCandy{
    DbFunctions db = new DbFunctions();
    Connection conn = db.connect_to_db("tutdb", "postgres", "basecamp");
    List<String> colorList;
    List<String> shapeList;
    private Scanner scanner = new Scanner(System.in);
    String shape;
    String color;
    private String table_name;

    public CottonCandy(){
        this.colorList = db.readAllCottonCandyColors(conn);
        this.shapeList = db.readAllCottonCandyShapes(conn);
        this.table_name  = "cotton_candy";
    }

    void selectColor() {
        System.out.print("Here are the available colors:" + this.colorList+"> ");
        String action = scanner.nextLine();

        while (!this.colorList.contains(action)) {
            System.out.println("Unfortunately " + action + " is not an option, please try again!");
            System.out.print("Here are the available colors:" + this.colorList +"> ");
            action = scanner.nextLine();
        }
        this.color = action;
    }

    void selectShape(){
        System.out.println("Please choose a shape!");
        System.out.print("Available shapes:"+ this.shapeList + "> ");
        String action = scanner.nextLine();

        while (!this.shapeList.contains(action)){
            System.out.println("Unfortunately" + action + " is not an option, please try again!");
            System.out.print("Here are the available shapes:" + this.shapeList +"> ");
        }
        this.shape = action;
    }

    void tossOrKeep(){
        System.out.println(" ");
        System.out.print("Is your order okay? You can [c]hange your order or [t]hrow it away! If it's good, [s]end it through! ");
        String action = scanner.nextLine().toLowerCase();

        switch(action){
            case "c":
                selectColor();
                selectShape();
                db.insertCottonCandy(this.conn, this.color, this.shape);
                tossOrKeep();
                break;
            case "t":
                db.deleteCottonCandy(this.conn,this.table_name, this.shape, this.color);
                break;
            case "s":
                System.out.print("Cotton Candy accepted! Enjoy!");
                break;
            default:
                System.out.println("Invalid input");
                break;
        }
    }
}

class Main {
    public static void main(String[] args) {
        CottonCandy ccMachine = new CottonCandy();

        System.out.println("Welcome to the cotton candy machine! Please choose a color!");
        ccMachine.selectColor();
        ccMachine.selectShape();
        ccMachine.db.insertCottonCandy(ccMachine.conn, ccMachine.color, ccMachine.shape);
        ccMachine.tossOrKeep();

    }
}
//public class CottonCandy {
//
//    private List<String> colors = List.of("pink", "blue", "yellow", "purple", "orange", "green", "white");
//    private List<String> shapes = List.of("Flower", "Butterfly", "Star", "Heart", "Moon", "Paw", "No Shape");
////    private List<String> addIns = List.of("Sprinkles", "Nerds", "M&M’s", "Gummy Worms", "Gummy Sharks", "Cookie Dough", "Cookie Crumbs");
//
//    private Scanner scanner = new Scanner(System.in);
//
//    public static void main(String[] args){
//        CottonCandy ccMachine = new CottonCandy();
//        DbFunctions db = new DbFunctions();
//        Connection conn = db.connect_to_db("tutdb", "postgres", "basecamp");
//
////        db.createColorTable(conn);
////        db.createShapeTable(conn);
////        db.createCottonCandyTable(conn);
////        db.read_CottonCandy(conn);
//
//        ccMachine.makeCottonCandy(conn, db);
//    }
//
//    public void makeCottonCandy(Connection conn, DbFunctions db) {
//        // Insert colors into the color table from the colors list
////        for (String color : colors) {
////            db.insertColor(conn, color);
////        }
//
//        // Insert shapes into the shape table from the shapes list
////        for (String shape : shapes) {
////            db.insertShape(conn, shape);
////        }
//
//        String colorSelection = "";
//        String shapeSelection = "";
//
//        while (!colors.contains(colorSelection)) {
//            System.out.println("Welcome to the cotton candy machine! Please choose a color!");
//            System.out.println("Here are the available colors:");
//            for (String color : colors) {
//                System.out.println(color);
//            }
//            colorSelection = scanner.nextLine();
//            if (!colors.contains(colorSelection)) {
//                System.out.println("Unfortunately" + colorSelection + " is not an option, please try again!");
//            }
//        }
//
//        while (!shapes.contains(shapeSelection)) {
//            System.out.println("Please choose a shape!");
//            System.out.println("Available shapes:");
//            for (String shape : shapes) {
//                System.out.println(shape);
//            }
//            shapeSelection = scanner.nextLine();
//            if (!shapes.contains(shapeSelection)) {
//                System.out.println("Unfortunately " + shapeSelection + " is not an option, please try again!");
//            }
//        }
//
//        db.insertCottonCandy(conn, colorSelection, shapeSelection);
//
//        while(true){
//            System.out.println("Is your order okay? You can [c]hange your order or [t]hrow it away! If it's good, send it through!");
//            String action = scanner.nextLine();
//            if (action.equalsIgnoreCase("c")){
//
//            }
//            else if (action.equalsIgnoreCase("t")){
//                db.deleteCottonCandy(conn, "cotton_candy", shapeSelection, colorSelection);
//                break;
//            }
//            else{System.out.println("Invalid input provided.");}
//        }
//    }
//}


//cotton candy machine with shapes and colors
//Flavors maybe coming later....?
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