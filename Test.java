package Program_6;
import java.util.Scanner;

public class Test {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        //default lists
        boolean type = true;
        List one = new List();
        List two = new List();
        List three = new List();
        List modList;


        System.out.println("Which list would you like to modify?" +
                "\n1. list one (Strings)" +
                "\n2. list two (Inventories)" +
                "\n3. list three (Inventories)");

        int listChoice = scan.nextInt();

        //allocating modList to the one user chooses
        switch(listChoice) {
            case 1:
                modList = one;
                type = false;
                break;
            case 2:
                modList = two;
                break;
            default:
                modList = three;
                break;
        }

        boolean loop;
        do {

            loop = true;

            //picks what user wants to do
            int choice = mainMenuChoice();

            //runs the command on modList
            switch (choice) {
                case 1:
                    List newList = new List();
                    type = pickType();
                    modList = newList;
                    break;
                case 2:
                    insertItem(modList, type);
                    break;
                case 3:
                    insertAtBack(modList, type);
                    break;
                case 4:
                    deleteRange(modList);
                    break;
                case 5:
                    deleteItem(modList, type);
                    break;
                case 6:
                    retrieve(modList);
                    break;
                case 7:
                    find(modList, type);
                    break;
                case 8:
                    getSize(modList);
                    break;
                case 9:
                    double val = totalValue(modList, type);
                    System.out.println("total value of the list is $" + val);
                    break;
                default:
                    System.out.println("program finished");
                    loop = false;
                    break;
            }
            display(modList);
        } while(loop);

    }

    //displays list after every operation
    public static void display(List list) {
        System.out.println(list.toString() + "\n");
    }

    //process of selecting an option from the main menu
    public static int mainMenuChoice() {
        Scanner scan = new Scanner(System.in);
        System.out.println("Select an option: " +
                "\n1). modify a new empty list" +
                "\n2). insert an item at a specific position" +
                "\n3). insert an item at the back" +
                "\n4). delete a range of positions" +
                "\n5). delete a specific item" +
                "\n6). retrieve the item from a position" +
                "\n7). find positions at which an item is located" +
                "\n8). get the size of the list" +
                "\n9). compute the total value of the list" +
                "\n10). quit program");
        int choice = scan.nextInt();
        return choice;
    }

    //lets user choose what kind of object to store in a new list
    public static boolean pickType() {
        Scanner scan = new Scanner(System.in);

        System.out.println("\twill this list contain inventory items? (y/n): ");
        String choice = scan.next();
        boolean b = false;
        if(choice.equalsIgnoreCase("y"))
            b = true;

        return b;
    }

    //process of inserting item at specific position
    public static void insertItem(List list, boolean type) {
        Scanner scan = new Scanner(System.in);
        System.out.println("\tposition you'd like to insert item: ");
        int pos = scan.nextInt();
        System.out.println("\tname of the item: ");
        String name = scan.next();

        if(type) {
            System.out.println("\t\tenter the quantity: ");
            int quant = scan.nextInt();
            System.out.println("\t\tenter the price: ");
            int price = scan.nextInt();
            Inventory IAB = new Inventory(name, quant, price);
            list.insert(IAB, pos);
        }

        else
            list.insert(name, pos);

        System.out.println("\tadded successfully\n");

    }

    //process of inserting item at back of list
    public static void insertAtBack(List list, boolean type) {
        Scanner scan = new Scanner(System.in);
        System.out.println("\tinsert the name of the item: ");
        String name = scan.next();

        if(type) {
            System.out.println("\t\tenter the quantity: ");
            int quant = scan.nextInt();
            System.out.println("\t\tenter the price: ");
            int price = scan.nextInt();
            Inventory IAB = new Inventory(name, quant, price);
            list.insertAtEnd(IAB);
        }

        else
            list.insertAtEnd(name);

        System.out.println("\tadded successfully\n");
    }

    //process of deleting range of items
    public static void deleteRange(List list) {
        Scanner scan = new Scanner(System.in);
        System.out.println("\tenter left bound: ");
        int left = scan.nextInt();
        System.out.println("\tenter right bound: ");
        int right = scan.nextInt();

        list.deleteRange(left, right);
        System.out.println("\titems deleted successfully\n");
    }

    //process of deleting specific item
    public static void deleteItem(List list, boolean type) {
        Scanner scan = new Scanner(System.in);
        System.out.println("\tname of item to delete: ");
        String name = scan.next();

            for(int i = 1; i < list.getSize(); i++) {
                if(type) {
                    if(((Inventory) list.get(i).data).getName().equalsIgnoreCase(name))
                        list.deleteItem(list.get(i).data);
                }

                else
                    if(((String) list.get(i).data).equalsIgnoreCase(name)) {
                        System.out.println("match found");
                        list.deleteItem(list.get(i).data);
                    }
            }

            System.out.println("item deleted successfully\n");
    }

    //process of returning item at specific position
    public static void retrieve(List list) {
        Scanner scan = new Scanner(System.in);
        System.out.println("\tenter the position you'd like to retrieve: ");
        int pos = scan.nextInt();

        System.out.println("\n" + list.retrieve(pos).toString());
    }

    //process of giving positions at which an item is located
    public static void find(List list, boolean type) {
        Scanner scan = new Scanner(System.in);
        List found = new List();
        System.out.println("\tname of item: ");
        String name = scan.next();

        if(type) {
            Inventory key = new Inventory(name, 0, 0);
            for(int i = 1; i < list.getSize(); i++) {
                if(key.equals(list.get(i).data))
                    found.insertAtEnd(i);
            }
        }
        else
            found = list.find(name);

        System.out.println("\n\tfound at positions: " + found.toString() + "\n");
    }

    //process of returning number of items in the list
    public static void getSize(List list) {
        System.out.println("\tlist is " + list.getSize() + " items long.\n");
    }

    //process of computing total value of list
    public static double totalValue(List list, boolean type) {
        int value = 0;
        if(type) {
            for(int i = 1; i <= list.getSize(); i++) {
                value += ((Inventory) list.get(i).data).getPrice()*((Inventory) list.get(i).data).getQuantity();
            }
        } return value;
    }

}
