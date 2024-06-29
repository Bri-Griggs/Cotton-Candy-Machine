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
        System.out.print("So here's your " +this.color +" " + this.shape + ". Is your order okay? You can [c]hange your order or [t]hrow it away! If it's good, [s]end it through! ");
        String action = scanner.nextLine().toLowerCase();
        switch(action){
            case "c":
                System.out.println("I can change the shape for you if you'd like!");
                System.out.print("Here are the available shapes:" + this.shapeList +"> ");
                String newShape = scanner.nextLine().toLowerCase();
                this.shape = db.updateShapeCottonCandy(this.conn, this.shape, newShape);
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