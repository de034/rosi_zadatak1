/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapplication7;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributeView;
import java.nio.file.attribute.BasicFileAttributes;
import java.nio.file.attribute.FileTime;
import java.text.SimpleDateFormat;
import java.util.Scanner;

/**
 *
 * @author ADMIN
 */
public class JavaApplication7 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        System.out.println("**********************************");
        System.out.println("ZADATAK 4 - FAJL SISTEM");
        System.out.println();
        System.out.println("OPCIJE PROGRAMA:");
        System.out.println();
        
        System.out.println("LIST - listanje sadržaja foldera");
        System.out.println("INFO - prikaz informacija o fajlovima/folderima");
        System.out.println("CREATE_DIR - kreiranje foldera");
        System.out.println("RENAME - promena imena fajlova/foldera");
        System.out.println("COPY - kopiranje fajlova / kompletnih foldera");
        System.out.println("MOVE - premeštanje fajlova / kompletnih foldera");
        System.out.println("DELETE - brisanje fajlova/foldera");
        
        System.out.println();
        
        System.out.println("**********************************");
        
        System.out.println("UNESITE NEKU OD PONUDJENIH KOMANDI:");
         Scanner scan = new Scanner(System.in);
         Fajlovi pomocni = new Fajlovi();
         
         if (scan.hasNextLine()) {
             String komanda = scan.nextLine();
             switch(komanda.toUpperCase()) {
                 case "LIST":
                     System.out.println("Izabrana je komanda LIST");
                     System.out.println();
                     System.out.println();
                     pomocni.SadrzajDirektorijuma();
                     break;
                 case "INFO":
                     System.out.println("Izabrana je komanda INFO");
                     System.out.println();
                     pomocni.InfoDirektorijuma();
                     break;
                 case "CREATE_DIR":
                     System.out.println("Izabrana je komanda CREATE_DIR");
                     System.out.println();
                     pomocni.KreirajDirektorijum();
                     break;
                 case "RENAME":
                     System.out.println("Izabrana je komanda RENAME");
                     System.out.println();
                     pomocni.PromenaImena();
                     break;
                 case "COPY":
                     System.out.println("Izabrana je komanda COPY");
                     System.out.println();
                     pomocni.Copy();
                     break;
                 case "MOVE":
                     System.out.println("Izabrana je komanda MOVE");
                     System.out.println();
                     
                     pomocni.Move();
                     break;
                 case "DELETE":
                     System.out.println("Izabrana je komanda DELETE");
                     System.out.println();
                     pomocni.Delete();
                     break;
                 default:
                    System.out.println("Izabrali ste NEPOSTOJECU opciju!");
             }
         }
     
         scan.close();
    }
    
    
  
}
