/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author admin
 */
public class Files {

    public static void writeSales(ArrayList<Sale> sales)  {

        LocalDate date = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String dateFormatted = date.format(formatter);
        
        String fileName = "sales_" + dateFormatted + ".txt";
                
        FileWriter outputStreamInventory;
        BufferedWriter out1 = null;

        int index = 1;

        try {
            outputStreamInventory = new FileWriter(fileName);
            out1 = new BufferedWriter(outputStreamInventory);

            for (Sale sale : sales) {

                out1.write(index + ";" + "Client=" + sale.getClient().getName() + ";" + "\n" + index + ";" + "Products=" + sale.getProducts() + ";" + "\n" + index + ";" + "Amount=" + sale.getAmount().getValue() + " euro;" + "\t");

                out1.write("\n");

                index++;
            }

        } catch (java.io.FileNotFoundException ex) {
            System.out.println("No existe el archivo para ser leido.");
        } catch (java.io.IOException ex) {

        } finally {
            if (out1 != null) {
                try {
                    out1.close();
                } catch (IOException ex) {
                    System.out.println("No se puede acceder al archivo.");
                }
            }
        }
    }

    public static void FileReader(ArrayList<Product> inventory) {

        String line;

        String name = "";
        Amount wholesalerPrice = new Amount(0);
        int stock = 0;

        FileReader inputStream = null;
        BufferedReader in = null;

        try {
            inputStream = new FileReader("inputInventory.txt");
            in = new BufferedReader(inputStream);
            
            while ((line = in.readLine()) != null) {
               
                String data[] = line.split(";");
                //crear un arraylist para poner un objeto

                for (int i = 0; i < data.length; i++) {
                    String finalData[] = data[i].split(":");
                    switch (i) {
                        case 0:
                            name = finalData[1];
                            break;
                        case 1:
                            wholesalerPrice = new Amount(Double.parseDouble(finalData[1]));
                            break;
                        case 2:
                            stock = Integer.parseInt(finalData[1]);
                            break;
                        
                    }
                }
                inventory.add(new Product(name, wholesalerPrice, true, stock));
            }

        } catch (java.io.IOException ex) {
            System.out.println("No se puede acceder al archivo.");
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (IOException ex) {
                    System.out.println("No se puede acceder al archivo.");
                }
            }
        }
    }

}
