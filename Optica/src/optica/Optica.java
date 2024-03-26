package optica;

import java.io.File;
import java.io.IOException;
import static java.lang.Math.pow;
import java.util.ArrayList;
import java.util.Scanner;
import static optica.LungimeDeUnda.getColor;

public class Optica {
    
    public static float getPanta(ArrayList <Point> PointArray){
     int sumxy = 0 , sumx = 0, sumy = 0, sumxSquared = 0;
            for(int i=0; i<PointArray.size(); i++){
                sumxy += PointArray.get(i).x*PointArray.get(i).y;
                sumx+=PointArray.get(i).x;
                sumy += PointArray.get(i).y;
                sumxSquared += pow(PointArray.get(i).x, 2);
            }         
    return  (PointArray.size()*sumxy - sumx * sumy) / ( PointArray.size()*sumxSquared - sumx * sumx);
}

    public static void main(String[] args) {
        String caz;
        Scanner sc = new Scanner(System.in); 
        while(true){
            System.out.println("");
            System.out.println("Introduceti tipul de experiment ( INTERFERENTA / DIFRACTIE ): ");
            System.out.println("Introduceti comanda EXIT pentru a inchide programul.");
            System.out.println("");
            caz = sc.next();
            switch(caz){
                case "INTERFERENTA" -> {
                try {
                    File myIFile = new File("./interferenta.txt");
                    if (myIFile.createNewFile()) {
                        System.out.println("File created: " + myIFile.getName());
                    } else {
                        ArrayList <Point> PointArray = new ArrayList<>();
                        System.out.println("File interferenta.txt exists.");
                        Scanner myReader = new Scanner(myIFile);
                        System.out.println("Distanta dintre fante este (mm): " );
                        float d = sc.nextFloat();
                        System.out.println("Distanta dintre dispozitivul cu fante si ecran (mm): ");
                        float l = sc.nextFloat();
                        int n = 0;
                        float panta = 0;
                        float counter = 0;
                        n = myReader.nextInt();
                        System.out.println("S-au efectuat " + n + " masuratori.");
                        System.out.println();
                        System.out.println("Cele " + n + " masuratori sunt :");
                       
                        for(int j=0; j< n; j++){
                            counter = counter + 1;
                            float x = counter;
                            float y = myReader.nextFloat();
                            PointArray.add(new Point(x,y));
                        }
                        myReader.close();
                        System.out.println();
                        for(int i=0; i<n; i++){
                            System.out.println("Masuratoarea " + (i+1) + ": ");
                            System.out.println("n : " + PointArray.get(i).x);
                            System.out.println("x(n) : " + PointArray.get(i).y);
                        }
                        System.out.println();
                        panta = Point.getPanta(PointArray);
                        System.out.println("Panta functiei este : " + panta + "(mm)");
                        System.out.println();
                        float lambda = panta*d/l*1000000;
                        System.out.println("Asadar, lungimea de unda lambda are valoarea : " + lambda + "(nm)");
                        System.out.println();
                        String color = getColor(lambda);
                        System.out.println("Culoarea corespunzatoare lungimii de unda lambda este: " + color);
                        System.out.println();
                        myReader.close();
                        float eroare = LungimeDeUnda.getEroareDifractie(lambda);
                        System.out.println("Datele experimentale au o eroare de aproximativ " + eroare + " (nm)"); 
                    }
                } catch (IOException e) {
                    System.out.println("An error occurred.");
                }
            }
            case "DIFRACTIE" -> {
                try {
                    File myDFile = new File("./difractie.txt");
                    if (myDFile.createNewFile()) {
                        System.out.println("File created: " + myDFile.getName());
                    } else {
                        ArrayList <Point> PointArray = new ArrayList<>();
                        System.out.println("File difractie.txt exists.");
                        Scanner myReader = new Scanner(myDFile);
                        int n = myReader.nextInt();
                        System.out.println("S-au efectuat " + n + " masuratori.");
                        float alphad, alphas, alpha, alpha_rad, sin_alpha;
                        for(int i=0; i<n; i++){
                            alphad = myReader.nextFloat();
                            alphas = myReader.nextFloat();
                            alpha = Math.abs(alphad-alphas)/2;
                            alpha_rad = ((float) Math.PI)*alpha/180;
                            sin_alpha = (float) Math.sin(alpha_rad);
                            float x = i + 1;
                            PointArray.add(new Point(x,sin_alpha));  
                        }
                        myReader.close();
                        System.out.println();
                        for(int i=0; i<n; i++){
                            System.out.println("Masuratoarea " + (i+1) + ": ");
                            System.out.println("n : " + PointArray.get(i).x);
                            System.out.println("sin_alpha(n) : " + PointArray.get(i).y);
                        }
                        System.out.println();
                        float panta = Point.getPanta(PointArray);
                        System.out.println("Panta functiei este : " + panta);
                        System.out.println();
                        float lambda = panta*20000;
                        System.out.println("Asadar, lungimea de unda lambda are valoarea : " + lambda + "(nm)");
                        System.out.println();
                        String color = getColor(lambda);
                        System.out.println("Culoarea corespunzatoare lungimii de unda lambda este: " + color);
                        System.out.println();
                        float eroare = LungimeDeUnda.getEroareDifractie(lambda);
                        System.out.println("Datele experimentale au o eroare de aproximativ " + eroare + " (nm)");    
                    }
                } catch (IOException e) {
                    System.out.println("An error occurred.");
                }       }
         default -> 
         {   if("EXIT".equals(caz)){
             break;
         }         
            else System.out.println("NU EXISTA ACEST CAZ!");  }
        }
        if("EXIT".equals(caz))
            break;
        }
       
    }
}