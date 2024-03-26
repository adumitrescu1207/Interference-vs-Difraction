package optica;

import static java.lang.Math.pow;
import java.util.ArrayList;

public class Point {
    float x; //n, ordinul franjei
    float y; //x(n), lungimea

public Point (){
}

public Point(float x, float y){
    this.x = x;
    this.y = y;
}

public static float getValoare( float x){
    return x;
}

public static float getPanta(ArrayList <Point> PointArray){
     float sumxy = 0 , sumx = 0, sumy = 0, sumxSquared = 0;
     int n = PointArray.size();
            for(int i=0; i<PointArray.size(); i++){
                sumxy += PointArray.get(i).x*PointArray.get(i).y;
                sumx+=PointArray.get(i).x;
                sumy += PointArray.get(i).y;
                sumxSquared += pow(PointArray.get(i).x, 2);
            } 
            float panta = (n*sumxy - sumx * sumy) / ( n*sumxSquared - sumx * sumx);
    return  panta;
}

}
