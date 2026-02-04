package main;

import java.util.ArrayList;
import model.Product;
import model.Employee;
import model.Sale;
import model.Amount;
import java.util.Scanner;
import model.Client;

public class Shop {

    private Amount cash = new Amount(100.00);
    private static ArrayList<Product> inventory;
    private int numberProducts;
    private static ArrayList<Sale> sales;
    private double amount;

    final static double TAX_RATE = 1.04;

    public Shop() {
        inventory = new ArrayList<Product>();
        sales = new ArrayList<Sale>();
    }

    public static void main(String[] args) {
        Shop shop = new Shop();

        shop.loadInventory();

        Scanner scanner = new Scanner(System.in);
        int opcion = 0;
        boolean exit = false;

        initSession();

        do {
            System.out.println("\n");
            System.out.println("===========================");
            System.out.println("Menu principal miTienda.com");
            System.out.println("===========================");
            System.out.println("1) Contar caja");
            System.out.println("2) A\u00f1adir producto");
            System.out.println("3) A\u00f1adir stock");
            System.out.println("4) Marcar producto proxima caducidad");
            System.out.println("5) Ver inventario");
            System.out.println("6) Venta");
            System.out.println("7) Ver ventas");
            System.out.println("8) Ver venta total");
            System.out.println("9) Eliminar producto");
            System.out.println("10) Salir programa");
            System.out.print("Seleccione una opcion: ");
            opcion = scanner.nextInt();

            switch (opcion) {
                case 1:
                    shop.showCash();
                    break;

                case 2:
                    shop.addProduct();
                    break;

                case 3:
                    shop.addStock();
                    break;

                case 4:
                    shop.setExpired();
                    break;

                case 5:
                    shop.showInventory();
                    break;

                case 6:
                    shop.sale();
                    break;

                case 7:
                    shop.showSales();
                    break;

                case 8:
                    shop.showTotalSales();
                    break;

                case 9:
                    shop.deleteProduct();
                    break;

                case 10:
                    exit = true;
                    break;
            }
        } while (!exit);
    }

    /**
     * initialize employeee session
     */
    public static void initSession() {
        Scanner sc = new Scanner (System.in);
        String eName;
        int eId;
        Employee e;
        
        do{
        
        System.out.println("Introduzaca el nombre de empleado:");
        eName = sc.nextLine();
        System.out.println("Introduzaca el Id de empleado:");
        eId = sc.nextInt();
        sc.nextLine();
        System.out.println("Introduzaca el password de empleado:");
        String ePassword = sc.nextLine();
        
        e = new Employee(eId, ePassword,eName);
        
        
        }while (!e.login());
        
        //aqui se llama al logable??
        e.login();
        
        System.out.println("Login correcto");
    }

    /**
     * load initial inventory to shop
     */
    public void loadInventory() {
        addProduct(new Product("Manzana", new Amount(10.00), new Amount(20.00), true, 10));
        addProduct(new Product("Pera", new Amount(20.00), new Amount(40.00), true, 20));
        addProduct(new Product("Hamburguesa", new Amount(30.00), new Amount(60.00), true, 30));
        addProduct(new Product("Fresa", new Amount(5.00), new Amount(10.00), true, 20));
    }

    /**
     * show current total cash
     */
    private void showCash() {
        System.out.println("Dinero actual: " + cash.getValue());
    }

    /**
     * add a new product to inventory getting data from console
     */
    public void addProduct() {
//        if (isInventoryFull()) {
//            System.out.println("No se pueden a\u00f1adir mas productos");
//            return;
//        }

        Scanner scanner = new Scanner(System.in);
        System.out.print("Nombre: ");
        String name = scanner.nextLine();
        Product a = findProduct(name);

        if (a == null) {
            System.out.print("Precio mayorista: ");
            double wholesalerPrice = scanner.nextDouble();
            System.out.print("Precio a la venta: ");
            double publicPrice = scanner.nextDouble();
            System.out.print("Stock: ");
            int stock = scanner.nextInt();

            addProduct(new Product(name, new Amount(wholesalerPrice), new Amount(publicPrice), true, stock));

        } else {
            System.out.println("El producto ya existe");
        }
    }

    /**
     * add stock for a specific product
     */
    public void addStock() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Seleccione un nombre de producto: ");
        String name = scanner.next();
        Product product = findProduct(name);

        if (product != null) {
            // ask for stock
            System.out.print("Seleccione la cantidad a a\u00f1adir: ");
            int stock = scanner.nextInt();
            // update stock product
            product.setStock(product.getStock() + stock);
            System.out.println("El stock del producto " + name + " ha sido actualizado a " + product.getStock());

        } else {
            System.out.println("No se ha encontrado el producto con nombre " + name);
        }
    }

    /**
     * set a product as expired
     */
    private void setExpired() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Seleccione un nombre de producto: ");
        String name = scanner.next();

        Product product = findProduct(name);

        if (product != null) {
            product.expire();
            System.out.println("El stock del producto " + name + " ha sido actualizado a " + product.getPublicPrice());

        }
    }

    /**
     * show all inventory
     */
    public void showInventory() {
        System.out.println("Contenido actual de la tienda:");
        for (Product product : inventory) {
            if (product != null) {
                System.out.println(product);
            }
        }
    }

    /**
     * make a sale of products to a client
     */
    public void sale() {
        // ask for client name
        Scanner sc = new Scanner(System.in);
        System.out.println("Realizar venta, escribir nombre cliente");
        String cName = sc.nextLine();
        System.out.println("Realizar venta, escribir id cliente");
        int cId = sc.nextInt();
        
        Client c1 = new Client(cId, cName);
       
        Product[] soldProducts = new Product[10];
        int cP = 0;

        if (cName.equals("0")) {
            return;
        }
        // sale product until input name is not 0
        double totalAmount = 0.0;
        String name = "";
        while (!name.equals("0")) {
            System.out.println("Introduce el nombre del producto, escribir 0 para terminar:");
            name = sc.nextLine();

            if (name.equals("0")) {
                break;
            }
            Product product = findProduct(name);
            boolean productAvailable = false;

            if (product != null && product.isAvailable()) {
                productAvailable = true;
                totalAmount += product.getPublicPrice().getValue();
                product.setStock(product.getStock() - 1);
                // if no more stock, set as not available to sale
                if (product.getStock() == 0) {
                    product.setAvailable(false);
                }
                //add products
                soldProducts[cP] = product;
                cP++;
                System.out.println("Producto a\u00f1adido con éxito");

//                como lo hice yo:
//                for (int i = 0; i < soldProducts.length; i++) {
//                    if (soldProducts[i] == null) {
//                        soldProducts[i] = product;
//                        break;
//                    }
//                }//falta poner el contador
            }

            if (!productAvailable) {
                System.out.println("Producto no encontrado o sin stock");
            }
        }

        // show cost total
        totalAmount = totalAmount * TAX_RATE;
        cash.setValue(cash.getValue() + totalAmount);
        amount += totalAmount;
        System.out.println("Venta realizada con exito, total: " + totalAmount);
        Amount a1 = new Amount (totalAmount);
        Sale lastSale = new Sale(c1, a1);
        
        //Si pay() es true con?nuar, si es false con?nuar y mostrar mensaje con la
        //can?dad a deber por parte del cliente
        c1.pay(a1);
        if (c1.pay(a1)) {
            System.out.println("Su salde restante es de:");
        } else {
            
        }
    }

    /**
     * show all sales
     */
    private void showSales() {
        //preguntarle a dios una mejor manera de hacerlo?
        boolean empty = true;
        System.out.println("Lista de ventas:");
        for (Sale sale : sales) {
            if (sale != null) {
                System.out.println(sale);
                empty = false;
            }
        } //sale todos los null

        if (empty) {
            System.out.println("La lista de ventas esta vacia");
        }
    }

    /**
     * show all sales
     */
    private void showTotalSales() {
        boolean empty = true;
        System.out.println("Lista de ventas:");
        for (Sale sale : sales) {
            if (sale != null) {
                System.out.println(sale);
                empty = false;
            }
        }

        if (empty) {
            System.out.println("La lista de ventas esta vacia");
        }

        System.out.println("La venta total es de: " + amount);
    }

    /**
     * add a product to inventory
     *
     * @param product
     */
    public void addProduct(Product product) {

        inventory.add(product);

    }

    /**
     * delete products
     */
    public void deleteProduct() {

        Scanner scanner = new Scanner(System.in);
        System.out.print("Introduzca nombre de producto: ");
        String name = scanner.next();
        Product product = findProduct(name);

        if (product != null) {
            inventory.remove(product);
            System.out.println("Producto eliminado con exito");
        } else {
            System.out.println("No se ha encontrado producto");
        }
    }

    /**
     * find product by name
     *
     * @param name
     * @return product found by name
     */
    public Product findProduct(String name) {
        if (inventory.contains(new Product(name))) {
            return inventory.get(inventory.indexOf(new Product(name)));
        } else {
            return null;
        }
    }

}
