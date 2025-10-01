import java.util.Scanner;

class Product{
    int id;
    int namecode;
    double price;

    public Product(int id, int nc, double price) {
        this.id = id;
        namecode = nc;
        this.price = price;
    }
    void displayProductDetails()
    {
        System.out.println("Product ID: "+id+"\nName Code: "+namecode+"\nPrice: "+price);
    }

}
public class P_MST {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int id = sc.nextInt();
        int namecode = sc.nextInt();
        double price = sc.nextDouble();
        Product pt = new Product(id, namecode, price);
        pt.displayProductDetails();
    }
}