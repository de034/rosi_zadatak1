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
public class Fajlovi {
    public Fajlovi() {
        
    }
 
    public void SadrzajDirektorijuma() {
        System.out.println("Unesi putanju do foldera: ");
        Scanner scan = new Scanner(System.in);
        if (scan.hasNextLine()) {
            String putanja = scan.nextLine();
            
            System.out.println("Sadrzaj trazenog foldera je: ");
            
            File path = new File(putanja);
            if (path.exists() && path.isDirectory()) {
                String[] strings = path.list();
                for(int i = 0; i < strings.length; i++) {
                    System.out.println(strings[i]); 
                }
            }
        }
        
        scan.close();
    }
    
    
    public void InfoDirektorijuma() {
        System.out.println("Unesi putanju do foldera: ");
        Scanner scan = new Scanner(System.in);
        if (scan.hasNextLine()) {
            String putanja = scan.nextLine();
            
            System.out.println("Detaljni prikaz trazenog foldera je: ");
            
            File path = new File(putanja);
            if (path.exists() && path.isDirectory()) {
                SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
                
                File[] files = path.listFiles();
                System.out.println("Ime\t\t\tPutanja\t\t\tVelicina\t\tDatum kreiranja\t\tDatum poslednje izmene");
                for(int i = 0; i < files.length; i++) {
                    String ime = files[i].getName();
                    String apsolutna = files[i].getAbsolutePath();
                    Long vel = files[i].length();
                    Double vel_kb = (double)vel / 1024;
                    
                    String datum_kreiranja = GetCreatedDate(files[i].getAbsolutePath());
                    String datum_izmene = sdf.format(files[i].lastModified());
                    
                    System.out.println(ime + "\t\t\t" + apsolutna + "\t\t\t" + vel_kb + "KB\t\t" + datum_kreiranja + "\t\t"+ datum_izmene + ""); 
                }
            }
        }
        
        scan.close();
    }
    
    public String GetCreatedDate(String fajl) {
        String datum = "";
        try {
            Path p = Paths.get(fajl);
            BasicFileAttributes view  = Files.getFileAttributeView(p, BasicFileAttributeView.class).readAttributes();
            FileTime fileTime = view.creationTime();
            
            SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
            datum = sdf.format(fileTime.toMillis());
        }
        catch(IOException e) {
            System.out.println(e);
        }
        
        return datum;
    }
    
    public void KreirajDirektorijum() {
        System.out.println("Unesi putanju do foldera: ");
        Scanner scan = new Scanner(System.in);
        if (scan.hasNextLine()) {

            String putanja = scan.nextLine();

            File path = new File(putanja);
            
            if (!path.exists() && !path.isDirectory()) {
                 path.mkdir();
            }
        }  
        
        scan.close();
    }
    
   public void PromenaImena() {
        System.out.println("Unesi putanju do fajla/foldera cije ime zelis da promenis: ");
        Scanner scan = new Scanner(System.in);
        if (scan.hasNextLine()) {
            String staro_ime = scan.nextLine();
            
            File stari_fajl = new File(staro_ime);
            
            if (!stari_fajl.exists()) {
                System.out.println("Ne postoji takav fajl ili folder");
                return;
            }
            
            System.out.println("Unesi putanju sa novim imenom fajla ilio foldera: ");
            
            String novo_ime = scan.nextLine();
            File novi_fajl = new File(novo_ime);
            
            if (novi_fajl.exists()) {
                System.out.println("Fajl ili folder sa tim imenom vec postoji!");
                return;
            }
            
            stari_fajl.renameTo(novi_fajl);
        }
        
        scan.close();
    }
   
    public void Copy() {
        System.out.println("Unesi putanju do fajla/foldera koga zelis da kopiras: ");
        Scanner scan = new Scanner(System.in);
        if (scan.hasNextLine()) {

            String original_ime = scan.nextLine();
            
            File original_file = new File(original_ime);
            
            if (!original_file.exists()) {
                System.out.println("Ne postoji takav fajl ili folder");
                return;
            }
            
            System.out.println("Unesi putanju na koju zelis da kopiras fajl ili folder: ");
            
            String kopija_ime = scan.nextLine();
            File kopija_file = new File(kopija_ime);
            
            if (kopija_file.exists()) {
                System.out.println("Fajl ili folder sa tim imenom vec postoji!");
                return;
            }
            
            copyFolder(original_file, kopija_file);
        }
        
        scan.close();
    }
    
    public void copyFolder(File original_file, File kopija_file) {
        if (original_file.isDirectory()) {
            if (!kopija_file.exists()) {
                kopija_file.mkdirs();
            }

            String files[] = original_file.list();

            for (String file : files)  {
                File srcFile = new File(original_file, file);
                File destFile = new File(kopija_file, file);

                copyFolder(srcFile, destFile);
            }
        }
        else {
            try {
                FileInputStream in = new FileInputStream(original_file);
                FileOutputStream out = new FileOutputStream(kopija_file);

                byte[] buffer = new byte[1024];

                int length;
                while ((length = in.read(buffer)) > 0)
                    out.write(buffer, 0, length);
                
                in.close();
                out.close();
            }
            catch (IOException e) {
                System.out.println("Greska: " + e.toString());
            }
        }
    }
    
    public void moveFolder(File original_file, File kopija_file) {
        if (original_file.isDirectory()) {
            if (!kopija_file.exists()) {
                kopija_file.mkdirs();
            }

            String files[] = original_file.list();

            for (String file : files)  {
                File srcFile = new File(original_file, file);
                File destFile = new File(kopija_file, file);

                moveFolder(srcFile, destFile);
            }
            String naziv = original_file.getName();
            original_file.delete();
            System.out.println("*** BRISANJE FOLDERA '" + naziv + "' ZAVRSENO ***");
        }
        else {
            try {
                FileInputStream in = new FileInputStream(original_file);
                FileOutputStream out = new FileOutputStream(kopija_file);

                byte[] buffer = new byte[1024];

                int length;
                while ((length = in.read(buffer)) > 0)
                    out.write(buffer, 0, length);
                
                in.close();
                out.close();
                
                String naziv = original_file.getName();
                System.out.println();
                System.out.println("Kopiranje fajla '" + naziv + "' uspelo!");
                original_file.delete();
                
                System.out.println("Brisanje fajla '" + naziv + "' uspelo!");
            }
            catch (IOException e) {
                System.out.println("Greska: " + e.toString());
            }
        }
    }
    
    public void Move() {
        System.out.println("Unesi putanju do fajla/foldera koga zelis da premestis: ");
        Scanner scan = new Scanner(System.in);
        if (scan.hasNextLine()) {

            String original_ime = scan.nextLine();
            
            File original_file = new File(original_ime);
            
            if (!original_file.exists()) {
                System.out.println("Ne postoji takav fajl ili folder");
                return;
            }
            
            System.out.println("Unesi putanju na koju zelis da premestis fajl ili folder: ");
            
            String kopija_ime = scan.nextLine();
            File kopija_file = new File(kopija_ime);
            
            if (kopija_file.exists()) {
                System.out.println("Fajl ili folder sa tim imenom vec postoji!");
                return;
            }
            
            moveFolder(original_file, kopija_file);
        }
        
        scan.close();
    }
    
    public static void Delete() {
        System.out.println("Unesi putanju do fajla/foldera koga zelis da obrises: ");
        Scanner scan = new Scanner(System.in);
        if (scan.hasNextLine()) {

            String original_ime = scan.nextLine();
            
            File putanja = new File(original_ime);
            
            if (!putanja.exists()) {
                System.out.println("Ne postoji takav fajl ili folder");
                return;
            }  
            
            deleteComplete(putanja);
        }
        
    }
    
    public static void deleteComplete(File element) {
        if (element.isDirectory()) {
            for (File sub : element.listFiles()) {
                deleteComplete(sub);
            }
        }
        
        try {
            element.delete();
            System.out.println("Fajl obrisan");
        }
        catch(Exception e) {
            System.out.println("Greska: " + e.toString());
        }
        
    }
}
