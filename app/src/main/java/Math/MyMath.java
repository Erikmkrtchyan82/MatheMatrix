package Math;

public final class MyMath {

    public static String DeterminantOf(int[][] x){
        if(x.length!=x[0].length)
            return "Matrix is not square";
        else
            return Integer.toString(determinantOf(x));
    }

    private static int determinantOf(int[][] X){
        if (X.length == 2) {
            return (X[0][0] * X[1][1] - X[0][1] * X[1][0]);
        } else {
            int s=0;
            for (int j = 0; j < X.length; j++) {
                s += Math.pow(-1, j) * X[0][j] * det(X, 0, j);
            }
            return s;
        }
    }

    private static double det(int[][] x1,int i1, int j1){
        int[][] xx=new int[x1.length-1][x1.length-1];
        int ik = -1, jk = -1;
        for (int i = 1; i < x1.length; i++) {
            ik++;
            jk = 0;
            for (int j = 0; j < x1.length; j++) {
                if (j == j1) continue;
                xx[ik][jk] = x1[i][j];
                jk++;
            }
        }
        if (xx.length == 2) {
            return xx[0][0] * xx[1][1] - xx[0][1] * xx[1][0];
        } else {
            int s=0;
            for (int j = 0; j < xx.length; j++) {
                s += Math.pow(-1, j) * xx[0][j] * det(xx,0, j);
            }
            return s;
        }
    }

    public static String InverseOf(int[][] x1){
        if (x1.length != x1[0].length){
            return "Matrix is not square";
        }
        else{
            if (determinantOf(x1) == 0) {
                return "Inverse is impossible because matrix Determinant is 0";
            }
            else{
                float[][] x=inverseOf(x1);
                StringBuilder s= new StringBuilder();
                for (float[] floats : x) {
                    for (float aFloat : floats) {
                        s.append(aFloat+"  ");
                    }
                    s.append("\n");
                }
                return s.toString();
            }
        }
    }

    private static float[][] inverseOf(int [][] x) {

        float s = (1.0f / determinantOf(x));
        float[][] u = new float[x.length][x.length];
        if (x.length == 2) {
            u[0][0] = x[1][1] * s;
            u[0][1] = -x[0][1] * s;
            u[1][0] = -x[1][0] * s;
            u[1][1] = x[0][0] * s;
        } else {
            for (int i = 0; i < x.length; i++) {
                for (int j = 0; j < x.length; j++) {
                    u[j][i] = (float)(s * Math.pow(-1, i + j) * det(x, i, j));
                }
            }
        }
        return u;
    }

    public static String MultipleOf(int[][] ob1,int[][] ob2){
        int [][] x=multipleOf(ob1,ob2);
        String s="";
        for(int i=0;i<x.length;++i){
            for(int j=0;j<x[i].length;++j){
                s+=x[i][j]+" ";
            }
            s+="\n";
        }
        return s;
    }

    private static int[][] multipleOf(int[][] ob1,int[][] ob2) {
        if (ob1[0].length != ob2.length) {
            System.out.println("First matrix column is not equal to second matrix row");
            return new int[][]{{0}};
        }
        int[][] M = new int[ob1.length][ob2[0].length];
        int s;
        for (int l = 0; l < ob2[0].length; l++) {
            for (int i = 0; i < ob1.length; i++) {
                s = 0;
                for (int j = 0; j < ob1[0].length; j++) {
                    s += ob1[i][j] * ob2[j][l];
                }
                M[i][l] = s;
            }
        }
       return M;
    }
}
