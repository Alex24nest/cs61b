public class Dessert {
    private static int numDesserts;
    public int flavor;
    public int price;

    public Dessert(int f, int p) {
        flavor = f;
        price = p;
        numDesserts++;
    }

    public void printDessert() {
        System.out.println(flavor + " " + price + " " + numDesserts);
    }

    public static void main(String[] args) {
        System.out.println("I love dessert!");
    }
}
