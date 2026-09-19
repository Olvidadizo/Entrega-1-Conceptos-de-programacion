/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package primeraentrega;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Random;
import java.util.Set;
import java.util.HashSet;
import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.FileReader;

public class GenerateInfoFiles{
    private static ArrayList<String> productsIds = new ArrayList<>();
    private static final Random RANDOM = new Random();
    
    public static void main(String[] args) throws IOException{
        int numberOfProducts = RANDOM.nextInt(100) + 3;
        int numberOfVendors = RANDOM.nextInt(50) + 3;
        createProductsFile(numberOfProducts);
        createSalesMenInfoFile(numberOfVendors);
        createFilesForAllSalesmen();
    }
    
    public static void createProductsFile(int productsCount) throws IOException{        
        productsIds.clear();
        PrintWriter writer = 
                new PrintWriter(
                        new FileWriter("productos.txt")
                );
        
        String[] productNames = {
            "computadora",
            "celular",
            "nevera",
            "teclado",
            "mouse",
            "monitor",
            "parlantes",
            "lavadora"
        };
                        
        for(int i = 0; i < productsCount; i++){
            String productId = String.format("P%03d", i);
            productsIds.add(productId);
            String productName = productNames[
                        RANDOM.nextInt(productNames.length)
                    ];            
            int price = RANDOM.nextInt(1990001) + 10000;
            
            writer.println(
                    productId + ";" 
                    + productName + ";"
                    + price
            );                    
        }
        
        writer.close();
    }
    
    public static void createSalesMenInfoFile(int salesmanCount) throws IOException{
        PrintWriter writer = 
                new PrintWriter(
                    new FileWriter("vendedores.txt")
        );
        
        String[] nombres = {
            "Juan",
            "Carlos",
            "Andrés",
            "David",
            "Daniel",
            "Santiago",
            "Sebastián",
            "Alejandro",
            "Mateo",
            "Nicolás",
            "Miguel",
            "Julián",
            "Felipe",
            "Gabriel",
            "Diego",
            "Samuel",
            "Tomás",
            "Luis",
            "Camilo",
            "Jorge",
            "María",
            "Laura",
            "Andrea",
            "Valentina",
            "Sofía",
            "Isabella",
            "Camila",
            "Natalia",
            "Daniela",
            "Paula"
        };
        
        String[] apellidos = {
            "Gonzalez",
            "Rodriguez",
            "Martinez",
            "Garcia",
            "Lopez",    
            "Hernandez",
            "Perez",
            "Sanchez",
            "Ramirez",
            "Torres",
            "Gomez",
            "Diaz",
            "Vargas",
            "Castro",
            "Morales",
            "Rojas",
            "Jimenez",
            "Ruiz",
            "Mendoza",
            "Moreno",
            "Muñoz",
            "Restrepo",
            "Quintero",
            "Cardona",
            "Cortes",
            "Valencia",
            "Ortiz",
            "Marin",
            "Suarez",
            "Rivera"
        };
        
        Set<Integer> documentNumbers = new HashSet<>();
        while(documentNumbers.size() < salesmanCount) {
            int number = 1000000 + RANDOM.nextInt(35001);            
            documentNumbers.add(number);
        }
        
        for(int number: documentNumbers){
            String nombre = nombres[RANDOM.nextInt(nombres.length)];
            String apellido = apellidos[RANDOM.nextInt(apellidos.length)];
            writer.println(
                "CC;" + number
                + ";" + nombre
                + ";" + apellido
            );
        }
        
        writer.close();
    }
    
    public static void createSalesManFile(
            int randomSalesCount,
            String name,
            long id) throws IOException{
        
        String fileName = "ventas_" + id + "_" + name + ".txt";
        
        PrintWriter writer = 
                new PrintWriter(
                    new FileWriter(fileName)
                );        
        writer.println("CC;" + id);
        
        for(int i = 0; i < randomSalesCount; i++){
            String productId = 
                    productsIds.get(
                        RANDOM.nextInt(productsIds.size())
                    );
            
            int quantity = RANDOM.nextInt(10) + 1;
            writer.println(productId + ";" + quantity);
        }
        
        writer.close();
    }
    
    public static void createFilesForAllSalesmen() throws IOException{
        BufferedReader reader = 
                new BufferedReader(
                        new FileReader("vendedores.txt")
                );
        
        String line;
        while( (line = reader.readLine()) != null){
            String[] data = line.split(";");
            long id = Long.parseLong(data[1]);
            String name = data[2];
            int randomSalesCount = RANDOM.nextInt(100) + 1;
            
            createSalesManFile(randomSalesCount, name, id);
        }
        
        reader.close();
    }
    
}