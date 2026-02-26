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
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author admin
 */
public class Files {

    public static void FileWriter(ArrayList<Product> inventory) throws IOException {

        FileWriter outputStreamInventory;
        BufferedWriter out1 = null;

        try {
            outputStreamInventory = new FileWriter("inputInventory.txt");
            out1 = new BufferedWriter(outputStreamInventory);

            for (Product product : inventory) {

                out1.write("PRODUCT:" + "\t" + product.getName() + "\t" + "WHOLESALER PRICE:" + "\t" + product.getPublicPrice() + "\t" + "STOCK:" + "\t" + product.getStock() + "\t");

                out1.write("\n");
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

        String name;
        Amount wholesalerPrice;
        int stock;

        FileReader inputStream = null;
        BufferedReader in = null;

        try {
            inputStream = new FileReader("inputInventory.txt");
            in = new BufferedReader(inputStream);

            while ((line = in.readLine()) != null) {

                String data[] = line.split(";");
                //crear un arraylist para poner un objeto

                for (int i = 0; i < data.length - 1; i++) {
                    String finalData[] = line.split(":");

                    name = finalData[1];
                    wholesalerPrice = new Amount(Double.parseDouble(finalData[3]));
                    stock = Integer.parseInt(finalData[5]);
                    
                inventory.add(new Product(name, wholesalerPrice, stock));
                }
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
