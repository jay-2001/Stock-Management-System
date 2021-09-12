package StockManagement;
import java.util.Scanner;
import java.io.*;
import java.util.*;
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
class Manage extends User
{
    Scanner sc= new Scanner(System.in);
    int stock_id=0;
    private Stock[] stocks=new Stock[100];
    private Stock[] dispatch=new Stock[100];
    int dispatch_id=0;
    int extra=0;
    Manage(String user_name, String password, String firm_name)
    {
        super(user_name,password,firm_name);
    }
    private class Stock
    {
        String category,item_name,supplier,item_companyname;
        int no_of_items;
        int price;
        int value;
        Stock(String category,String item_name, String supplier,String item_companyname,int price,int no_of_items)
        {
            this.category=category;
            this.item_name=item_name;
            this.item_companyname=item_companyname;
            this.supplier=supplier;
            this.no_of_items=no_of_items;
            this.price=price;
            this.value=price*no_of_items;
        }
    }
    void enter_stock()
    {
        //sc.nextLine();
        System.out.print("Category:");
        String category=sc.next();
        System.out.print("Item Name:");
        String item_name=sc.nextLine();
        item_name=sc.nextLine();
        System.out.print("Company Name of item:");
        String item_companyname=sc.nextLine();
        System.out.print("Supplier:");
        String supplier=sc.nextLine();
        System.out.print("Quantity:");
        int no_of_items=0;
        while(no_of_items<=0)
        {
            no_of_items=sc.nextInt();
            if(no_of_items<=0)
            System.out.print("Please Enter Valid Quantity!");
        }
        System.out.print("Price Of a Item:");
        int price=0;
        while(price<=0)
        {
            price=sc.nextInt();
            if(price<=0)
            System.out.print("Please Enter Valid Price!");
        }
        
        stocks[stock_id]=new Stock(category,item_name,supplier,item_companyname,price,no_of_items);
        stock_id++;
    }
    void view_stock()
    {
        System.out.println("=================================================================================================================== ");
        System.out.println("id\tCategory\tItem_Name\tCompany_name\tSupplier\tQuantity\tPrice(Rs)\tValue(Rs)");
        for(int j=0;j<stock_id;j++)
        {
            System.out.printf("%d\t%-8s\t%-9s\t%-12s\t%-8s\t%-10d\t%-8d\t%-8d\t",(j+1),stocks[j].category,stocks[j].item_name,stocks[j].item_companyname,stocks[j].supplier,stocks[j].no_of_items,stocks[j].price,stocks[j].value);
            System.out.println();
        }
    }
    void view_stock_category(String s)
    {
        System.out.println("=================================================================================================================== ");
        System.out.println("id\tCategory\tItem_Name\tCompany_name\tSupplier\tQuantity\tPrice(Rs)\tValue(Rs)");
        for(int j=0;j<stock_id;j++)
        {
            if(stocks[j].category.equals(s))
            {
                System.out.printf("%d\t%-8s\t%-9s\t%-12s\t%-8s\t%-10d\t%-8d\t%-8d\t",(j+1),stocks[j].category,stocks[j].item_name,stocks[j].item_companyname,stocks[j].supplier,stocks[j].no_of_items,stocks[j].price,stocks[j].value);
                System.out.println();
            }
        }
    }
    void view_stock_supplier(String s)
    {
        System.out.println("=================================================================================================================== ");
        System.out.println("id\tCategory\tItem_Name\tCompany_name\tSupplier\tQuantity\tPrice(Rs)\tValue(Rs)");
        for(int j=0;j<stock_id;j++)
        {
            if(stocks[j].supplier.equals(s))
            {
                System.out.printf("%d\t%-8s\t%-9s\t%-12s\t%-8s\t%-10d\t%-8d\t%-8d\t",(j+1),stocks[j].category,stocks[j].item_name,stocks[j].item_companyname,stocks[j].supplier,stocks[j].no_of_items,stocks[j].price,stocks[j].value);
                System.out.println();
            }   
        }
    }
    void view_stock_itemname(String s)
    {
        System.out.println("=================================================================================================================== ");
        System.out.println("id\tCategory\tItem_Name\tCompany_name\tSupplier\tQuantity\tPrice(Rs)\tValue(Rs)");
        for(int j=0;j<stock_id;j++)
        {
            if(stocks[j].item_name.equals(s))
            {
                System.out.printf("%d\t%-8s\t%-9s\t%-12s\t%-8s\t%-10d\t%-8d\t%-8d\t",(j+1),stocks[j].category,stocks[j].item_name,stocks[j].item_companyname,stocks[j].supplier,stocks[j].no_of_items,stocks[j].price,stocks[j].value);
                System.out.println();
            }           
        }
    }
    boolean dispatch_id(int id,int q)
    {
        id--;
        if(id>=stock_id || id<0)
        {
            System.out.println("Id is out of range!! Please enter Id in range.");
            return false;
        }
        else if(q>stocks[id].no_of_items || q<0)
        {
            System.out.println("Quantity is Limited!! Please Dispatch item in lesser Qauntity.");
            return false;
        }
        else
        {
            if(q==stocks[id].no_of_items)
            {
                dispatch[dispatch_id]= new Stock(stocks[id].category,stocks[id].item_name,stocks[id].item_companyname,stocks[id].supplier,stocks[id].price,stocks[id].no_of_items);
                dispatch_id++;
                for(int j=id;id<stock_id-1;j++)
                {
                    stocks[j]=stocks[j+1];
                }
                stock_id--;
            }
            else
            {
                dispatch[dispatch_id]= new Stock(stocks[id].category,stocks[id].item_name,stocks[id].item_companyname,stocks[id].supplier,stocks[id].price,q);
                stocks[id].value=q*stocks[id].price;
                stocks[id].no_of_items-=q;
                stocks[id].value=stocks[id].no_of_items*stocks[id].price;
                dispatch_id++;
            }
            return true;
        }
    }
    void view_dispatch_stock()
    {
        System.out.println("=================================================================================================================== ");
        System.out.println("id\tCategory\tItem_Name\tCompany_name\tSupplier\tQuantity\tPrice(Rs)\tValue(Rs)");
        for(int j=0;j<dispatch_id;j++)
        {
            System.out.printf("%d\t%-8s\t%-9s\t%-12s\t%-8s\t%-10d\t%-8d\t%-8d\t",(j+1),dispatch[j].category,dispatch[j].item_name,dispatch[j].item_companyname,dispatch[j].supplier,dispatch[j].no_of_items,dispatch[j].price,dispatch[j].value);
            System.out.println();
        }
    }

}