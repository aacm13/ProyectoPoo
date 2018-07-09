/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package usuario;

import java.util.*;
import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author aacm12
 */
public class usuario {

    public void nuevo(String user, String password, String confirmar){
        Scanner input = new Scanner(System.in);
        //String user, password, confirmar, 
        String dato,highscore = "0", dinero = "0";

        dato = user + "," + password + "," +highscore +","+ dinero; 
        try{
            FileWriter fw = new FileWriter("Datos.txt", true);
            BufferedWriter bw = new BufferedWriter(fw);
            
            bw.write(dato);
            bw.newLine();
            
            bw.close();
            
        }catch(IOException e){
            System.out.println("ERROR, no se pudo agregar a file");
            e.printStackTrace();
        }

    }
    
    public boolean login(String user, String password) throws IOException{
        ArrayList<Dato> datos = Read();

        boolean bandera = false;
        if (!bandera){
           
            int i =0;
            while(datos.size()>i){
                
                if((user.equals(datos.get(i).getUser())) && (password.equals(datos.get(i).getPass()))){
                    //System.out.println("Ingresando...");
                    bandera = true;
                    break;
                }
                i++;
            }
        }
        return bandera;
        
    }
    
    public String valores(String user) throws IOException{
        //String user = "AACM12";
        //System.out.println("usuario: "+user);
        String dato;
        ArrayList<Dato> datos = Read();
        int i =0;
        int index = 0;
        while(datos.size()>i){
            if((user.equals(datos.get(i).getUser()))){
                index = i;
                break;
                    
            }
            i++;
        }
        
        dato = datos.get(index).toString();
        //System.out.println(dato);
        return dato;
        
    }   
    public boolean checkExiste(String user) throws IOException{
        boolean check = false;
        
        int i = 0;
        ArrayList<Dato> datos = Read();
        while(datos.size()>i){
            if (user.equals(datos.get(i).getUser())){
                check = true;
            }
            i++;
        }

        return check;
    }
    
    public ArrayList<Dato> Read() throws IOException{
        Dato dato = new Dato();
        Scanner input = new Scanner(System.in);
        BufferedReader br = null;
        ArrayList<Dato> datos = new ArrayList<>();
        try{
            br = new BufferedReader(new FileReader("Datos.txt"));
            String d;
            while ((d= br.readLine())!= null){
                
                String [] splitting = d.split(",");
                String tUser = splitting[0];
                String tPass = splitting[1];
                int tHigh = Integer.parseInt(splitting[2]);
                int tMoney = Integer.parseInt(splitting[3]); 
                
                datos.add(new Dato(tUser,tPass, tHigh, tMoney));
                
            }
        }finally{
            if(br!=null){
                br.close();
            }
        }
        
        return datos;
    }
    
    
}
