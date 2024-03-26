package optica;

public class LungimeDeUnda {
     public static String getColor(float lambda) {
        if (lambda < 450) {
            return "VIOLET";
        } else if (lambda < 495) {
            return "ALBASTRU";
        } else if (lambda < 570) {
            return "VERDE";
        } else if (lambda < 590) {
            return "GALBEN";
        } else if (lambda < 620) {
            return "PORTOCALIU";
        } else if (lambda < 750) {
            return "ROSU";
        } else {
            return "NECUNOSCUT";
        }
    }
     
 public static float getEroareDifractie(float lambda){
         String color = getColor(lambda);
         if("VIOLET".equals(color))
             return (float) Math.abs(lambda - 435.8);
         if("VERDE".equals(color))
             return (float) Math.abs(lambda - 546.1);
         if("GALBEN".equals(color))
             return (float) Math.abs(lambda - 578.2);
     return 0;
 }    
 public static float getEroareInterferenta(float lambda){
     String color = getColor(lambda);
     if("VERDE".equals(color))
         return (float) Math.abs(lambda - 546);
     return 0;
 }
}
