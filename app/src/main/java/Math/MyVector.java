package Math;

public class MyVector {

    public static String lineWith2Point(int x1,int y1,int x2,int y2) {
        //(x-x1)*(y2-y1)/(x2-x1)+y1=y;
        double k=(double)(y2-y1)/(x2-x1);
        double b=(-x1)*k+y1;
        String text="";
        if(k==0) {
            text=""+b;
        }else{
            if(b==0) {
                text=k + "x";
            }else{
                if(b<0) {
                    text=k + "x" + b;
                }else {
                    text=k + "x+" + b;
                }
            }
        }
        return text;
    }
    public static double angleOfLine(int x1,int y1,int x2,int y2){
        return (Math.toDegrees(Math.atan((double)(y2-y1)/(x2-x1))));
    }
    public static String deretative(String function,char d){

        String str="";
        if(function.length()==1 && function=="x"){
            str="1";
        }else
        if(function.indexOf(d)==-1){
            str="0";
        }
        else {
            String text1 = "";
            char o;
            for (int i = 1; i <= function.indexOf(d); i++) {
                o = function.charAt(function.indexOf(d) - i);
                if (o == '+' || o == '-') break;
                text1 += o;
            }
            String text = "";
            if (text1.length() == 0)
                text1 = "1";
            for (int i = text1.length() - 1; i >= 0; i--) {
                text += text1.charAt(i);
            }
            //double k = Double.parseDouble(text);
            str=text;
        }
        return str;
    }
}
