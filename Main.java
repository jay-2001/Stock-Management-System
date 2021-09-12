import java.lang.*;
import java.io.*;
import java.util.*;
interface color{
    public static final String RESET = "\u001B[0m";
    public static final String RED = "\u001B[31m";
    public static final String GREEN = "\u001B[32m";
    public static final String YELLOW = "\u001B[33m";
    public static final String BLUE = "\u001B[34m";
    public static final String PURPLE = "\u001B[35m";
    public static final String CYAN = "\u001B[36m";
}
class User
{
    private static int i=0;
    String user_name,password;
    String firm_name;
    int user_id;
    User(String user_name, String password, String firm_name)
    {
        this.user_name=user_name;
        this.password=password;
        this.user_id=i;
        this.firm_name=firm_name;
        i++;
    }

}
class Manage extends User implements color{
    Scanner sc = new Scanner(System.in);
    int stock_id = 0;
    private Stock[] stocks = new Stock[100];
    private Stock[] dispatch = new Stock[100];
    int dispatch_id = 0;
    int extra = 0;

    Manage(String user_name, String password, String firm_name) {
        super(user_name, password, firm_name);
    }
    private class Stock {
        String category, item_name, supplier, item_companyname;
        int no_of_items;
        int price;
        int value;

        Stock(String category, String item_name, String supplier, String item_companyname, int price, int no_of_items) {
            this.category = category;
            this.item_name = item_name;
            this.item_companyname = item_companyname;
            this.supplier = supplier;
            this.no_of_items = no_of_items;
            this.price = price;
            this.value = price * no_of_items;
        }
    }

    void enter_stock() {
        System.out.print(YELLOW+"Category:");
        String category = sc.next();
        System.out.print("Item Name:");
        String item_name = sc.nextLine();
        item_name = sc.nextLine();
        System.out.print("Company Name of item:");
        String item_companyname = sc.nextLine();
        System.out.print("Supplier:");
        String supplier = sc.nextLine();
        System.out.print("Quantity:"+RESET);
        int no_of_items = 0;
        while (no_of_items <= 0) {
            no_of_items = sc.nextInt();
            if (no_of_items <= 0)
                System.out.print(RED+"Please Enter Valid Quantity!"+RESET);
        }

        System.out.print(BLUE+"Price Of a Item:"+RESET);
        int price = 0;
        while (price <= 0) {
            price = sc.nextInt();
            if (price <= 0)
                System.out.print(RED+"Please Enter Valid Price!"+RESET);
        }

        stocks[stock_id] = new Stock(category, item_name, supplier, item_companyname, price, no_of_items);
        stock_id++;
    }

    void view_stock() {
        System.out.println(CYAN+"=================================================================================================================== ");
        System.out.println(PURPLE+"id\tCategory\tItem_Name\tCompany_name\tSupplier\tQuantity\tPrice(Rs)\tValue(Rs)");
        System.out.println(CYAN+"=================================================================================================================== ");
        for (int j = 0; j < stock_id; j++) {
            System.out.printf(PURPLE+"%d\t%-8s\t%-9s\t%-12s\t%-8s\t%-10d\t%-8d\t%-8d\t", (j + 1), stocks[j].category, stocks[j].item_name, stocks[j].item_companyname, stocks[j].supplier, stocks[j].no_of_items, stocks[j].price, stocks[j].value);
            System.out.println();
        }
        System.out.println(CYAN+"=================================================================================================================== "+RESET);
    }

    void view_stock_category(String s) {
        System.out.println(CYAN+"=================================================================================================================== ");
        System.out.println(YELLOW+"id\tCategory\tItem_Name\tCompany_name\tSupplier\tQuantity\tPrice(Rs)\tValue(Rs)");
        System.out.println(CYAN+"=================================================================================================================== ");
        for (int j = 0; j < stock_id; j++) {
            if (stocks[j].category.equals(s)) {
                System.out.printf(YELLOW+"%d\t%-8s\t%-9s\t%-12s\t%-8s\t%-10d\t%-8d\t%-8d\t", (j + 1), stocks[j].category, stocks[j].item_name, stocks[j].item_companyname, stocks[j].supplier, stocks[j].no_of_items, stocks[j].price, stocks[j].value);
                System.out.println();
            }
        }
        System.out.println(CYAN+"=================================================================================================================== "+RESET);
    }

    void view_stock_supplier(String s) {
        System.out.println(CYAN+"=================================================================================================================== ");
        System.out.println(PURPLE+"id\tCategory\tItem_Name\tCompany_name\tSupplier\tQuantity\tPrice(Rs)\tValue(Rs)");
        System.out.println(CYAN+"=================================================================================================================== ");
        for (int j = 0; j < stock_id; j++) {
            if (stocks[j].supplier.equals(s)) {
                System.out.printf(YELLOW+"%d\t%-8s\t%-9s\t%-12s\t%-8s\t%-10d\t%-8d\t%-8d\t", (j + 1), stocks[j].category, stocks[j].item_name, stocks[j].item_companyname, stocks[j].supplier, stocks[j].no_of_items, stocks[j].price, stocks[j].value);
                System.out.println();
            }
        }
        System.out.println(CYAN+"=================================================================================================================== "+RESET);
    }

    void view_stock_itemname(String s) {
        System.out.println(CYAN+"=================================================================================================================== ");
        System.out.println(PURPLE+"id\tCategory\tItem_Name\tCompany_name\tSupplier\tQuantity\tPrice(Rs)\tValue(Rs)");
        System.out.println(CYAN+"=================================================================================================================== ");
        for (int j = 0; j < stock_id; j++) {
            if (stocks[j].item_name.equals(s)) {
                System.out.printf(GREEN+"%d\t%-8s\t%-9s\t%-12s\t%-8s\t%-10d\t%-8d\t%-8d\t", (j + 1), stocks[j].category, stocks[j].item_name, stocks[j].item_companyname, stocks[j].supplier, stocks[j].no_of_items, stocks[j].price, stocks[j].value);
                System.out.println();
            }
        }
        System.out.println(CYAN+"=================================================================================================================== "+RESET);
    }

    boolean dispatch_id(int id, int q) {
        id--;
        if (id >= stock_id || id < 0) {
            System.out.println(RED+"Id is out of range!! Please enter Id in range."+RESET);
            return false;
        } else if (q > stocks[id].no_of_items || q < 0) {
            System.out.println(RED+"Quantity is Limited!! Please Dispatch item in lesser Qauntity."+RESET);
            return false;
        } else {
            if (q == stocks[id].no_of_items) {
                dispatch[dispatch_id] = new Stock(stocks[id].category, stocks[id].item_name, stocks[id].item_companyname, stocks[id].supplier, stocks[id].price, stocks[id].no_of_items);
                dispatch_id++;
                for (int j = id; id < stock_id - 1; j++) {
                    stocks[j] = stocks[j + 1];
                }
                stock_id--;
            } else {
                dispatch[dispatch_id] = new Stock(stocks[id].category, stocks[id].item_name, stocks[id].item_companyname, stocks[id].supplier, stocks[id].price, q);
                stocks[id].value = q * stocks[id].price;
                stocks[id].no_of_items -= q;
                stocks[id].value = stocks[id].no_of_items * stocks[id].price;
                dispatch_id++;
            }
            return true;
        }
    }

    void view_dispatch_stock() {
        System.out.println(CYAN+"=================================================================================================================== ");
        System.out.println(PURPLE+"id\tCategory\tItem_Name\tCompany_name\tSupplier\tQuantity\tPrice(Rs)\tValue(Rs)");
        System.out.println(CYAN+"=================================================================================================================== ");
        System.out.print(PURPLE);
        for (int j = 0; j < dispatch_id; j++) {
            System.out.printf("%d\t%-8s\t%-9s\t%-12s\t%-8s\t%-10d\t%-8d\t%-8d\t", (j + 1), dispatch[j].category, dispatch[j].item_name, dispatch[j].item_companyname, dispatch[j].supplier, dispatch[j].no_of_items, dispatch[j].price, dispatch[j].value);
            System.out.println();
        }
        System.out.print(CYAN);
        System.out.println("=================================================================================================================== ");
        System.out.println(RESET);
    }
}
public class Main implements color{
    static String firm_name,user_name,password;
    static Scanner sc=new Scanner(System.in);
    static Manage[] user= new Manage[100];
    static int i=0;
    public static void main(String args[])
    {
        System.out.println();
        System.out.println(GREEN);

        System.out.println("                       #                #  # # # #  #          # # #    # #     #         #   # # # #          ");
        System.out.println("                        #      #       #   #        #        #        #     #   # #     # #   #                                   ");
        System.out.println("                         #   #   #    #    # # #    #        #        #     #   #  #   #  #   # # #               ");
        System.out.println("                          # #     #  #     #        #        #        #     #   #    #    #   #                         ");
        System.out.println("                           #       #       # # # #  # # # #    # # #    # #     #         #   # # # #                          ");
        System.out.println(RESET+"\n");
        run();
    }
    static void run()
    {
        System.out.println(BLUE+"==========================================================================");
        System.out.println(YELLOW+"                        Welcome to LOGIN                                  ");
        System.out.println(BLUE+"==========================================================================");
        System.out.println(GREEN);
        System.out.print("1. login\n2. create new Account \n3. exit: ");
        System.out.println(RESET);
        Scanner sc = new Scanner(System.in);
        int n = Integer.parseInt(sc.nextLine());

        if(n==1)
        {
            login();
            run();
        }
        else if(n==2)
        {
            user[i]=sign_up();
            i++;
            run();
        }
        else if(n==3)
            System.exit(0);
        else  System.out.println(RED+"Oops! Invalid Input."+RESET);
    }
    static void login()
    {
        System.out.print(YELLOW+"USERNAME: ");
        String user_name=sc.next();
        System.out.print("PASSWORD: ");
        String password=sc.nextLine();
        password=sc.nextLine();
        System.out.print(RESET);
        for(int j=0;j<i;j++)
        {
            if(user[j].user_name.equals(user_name) && user[j].password.equals(password))
            {
                home(user[j]);
            }
        }
        System.out.println(RED+"Invalid USERNAME or PASSWORD!! "+RESET);
    }
    static void home(Manage cur_user)
    {
        System.out.println(GREEN+"=================================================================================================================== ");
        System.out.println(BLUE+"Welcome "+cur_user.user_name+" to HOME PAGE::");
        System.out.println(GREEN+"=================================================================================================================== ");
        System.out.print(RESET);
        System.out.print(BLUE+"1. Enter Stocks\n2. View Stock\n3. Dispatch Stock\n4. Veiw Dispatched Stock\n5. Log Out --->  "+RESET);

        int k=Integer.parseInt(sc.nextLine());
        if(k==1)
        {
            cur_user.enter_stock();
            System.out.println(GREEN+"loading....loading....loading....") ;
            System.out.println("Stock Entered Successfully!!"+RESET);
            home(cur_user);
        }
        else if(k==2)
        {
            int t;
            System.out.print(PURPLE+"1. View whole Stock\n2. View Stock by Category\n3. View Stock by Supplier\n4. View Stock by Item Name --->  "+RESET);
            t=Integer.parseInt(sc.nextLine());
            if(t==1)
            {
                cur_user.view_stock();
                home(cur_user);
            }
            else if(t==2)
            {
                System.out.print(YELLOW+"Enter the category: "+RESET);
                String s=new String();
                s=sc.nextLine();
                cur_user.view_stock_category(s);
                home(cur_user);
            }
            else if(t==3)
            {
                System.out.print(YELLOW+"Enter the Supplier Name: "+RESET);
                String s=sc.nextLine();
                cur_user.view_stock_supplier(s);
                home(cur_user);
            }
            else if(t==4)
            {
                System.out.print(YELLOW+"Enter the Item Name: "+RESET);
                String s=sc.nextLine();
                cur_user.view_stock_itemname(s);
                home(cur_user);
            }
            else
            {
                System.out.println(GREEN+"Oops! Invalid Input.");
                System.out.println("Please Input Again."+RESET);
                home(cur_user);
            }

        }
        else if(k==3)
        {
            cur_user.view_stock();
            System.out.print(BLUE+"Enter the Id of stock to dispatch:");
            int id=Integer.parseInt(sc.nextLine());
            System.out.print("Enter the quantity of item to be dispatched:"+RESET);
            int q=Integer.parseInt(sc.nextLine());
            boolean b=cur_user.dispatch_id(id,q);
            if(b)
            {
                System.out.println(RED+"loading....loading....loading....") ;
                System.out.println("Stock Dispatched Successfully!!"+RESET);
            }
            home(cur_user);
        }
        else if(k==4)
        {
            cur_user.view_dispatch_stock();
            home(cur_user);
        }
        else if(k==5)
        {
            run();
        }
        else
        {
            System.out.println(RED+"Oops! Invalid Input.");
            System.out.println("Please Input Again."+RESET);
            home(cur_user);
        }

    }
    static Manage sign_up()
    {
        System.out.println(GREEN+"=================================================================================================================== ");
        System.out.println(YELLOW+"Creating new Account");
        System.out.print(GREEN+"Firm Name:");
        firm_name=sc.nextLine();
        System.out.print(GREEN+"USERNAME: "+RESET);
        boolean b=true;
        while(b)
        {
            user_name=sc.nextLine();
            b=check_username(user_name);
            if(b) System.out.println(RED+"USERNAME Already taken!!"+RESET);
        }
        System.out.print(RED+"PASSWORD (MIN 5 CHARACTER): "+RESET);
        b=true;
        while(b)
        {
            password=sc.nextLine();
            b=check_password(password);
            if(b) System.out.println(YELLOW+"PASSWORD must be five charcters long."+RESET);
        }
        Manage u = new Manage(user_name,password,firm_name);
        System.out.println(GREEN+"Account created Successfully!!"+RESET);
        return u;
    }
    static boolean check_username(String s)
    {
        for(int j=0;j<i;j++)
        {
            if(s.equals(user[j].user_name)) return true;
        }
        return false;
    }
    static boolean check_password(String s)
    {
        if(s.length()<5) return true;
        return false;
    }
}
