package Program_6;

public class Inventory {

    private String name;
    private int quantity;
    private double price;

    public Inventory(String name, int quantity, double price) {
        this.name = name;
        this.quantity = quantity;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public int getQuantity() {
        return quantity;
    }

    public double getPrice() {
        return price;
    }

    public boolean equals(Object o) { //use this in test
        boolean b = false;
        System.out.println(".equals method activated");
        //case of Inventory
        if(o instanceof Inventory) {
            Inventory other = (Inventory) o;
            if(other.getName().equalsIgnoreCase(this.getName()))
                b = true;
        }

        //case of String
        else {
            System.out.println("case of string");
            String other = (String) o;
            if(getName().equalsIgnoreCase(other))
                b = true;
        }

        return b;
    }

    public String toString() {
        return "name: " + name + "    quantity: " + quantity + "    price: $" + price + "\n";
    }

}
