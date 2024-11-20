import java.util.*;
public class MultiplyMatrix{
    public static int[][] bfMultiply(int[][] x, int[][] y){
        int[][] r = new int[x.length][y[0].length];
        for(int i=0;i<r.length;i++)
            for(int j=0;j<r[0].length;j++)
                for(int k=0;k<x[0].length;k++)
                    r[i][j] += x[i][k] * y[k][j];
        return r;
    }

    public static int[][] dcMultiply(int[][] x, int[][] y){
        return dcMultiply(x, 0, x.length, 0, x[0].length, y, 0, y.length, 0, y[0].length);
    }
    
    private static int[][] dcMultiply(int[][] x, int xRowStart, int xRowEnd, int xColStart, int xColEnd, int[][] y, int yRowStart, int yRowEnd, int yColStart, int yColEnd){
        if(xRowEnd-xRowStart==2)
            return baseCase(x, xRowStart, xColStart, y, yRowStart, yColStart);
        int[][] ae = dcMultiply(x, xRowStart, xRowEnd/2, xColStart, xColEnd/2, y, yRowStart, yRowEnd/2, yColStart, yColEnd/2);
        int[][] bg = dcMultiply(x, xRowStart, xRowEnd/2, xColEnd/2, xColEnd,   y, yRowEnd/2, yRowEnd  , yColStart, yColEnd/2);
        int[][] af = dcMultiply(x, xRowStart, xRowEnd/2, xColStart, xColEnd/2, y, yRowStart, yRowEnd/2, yColEnd/2, yColEnd  );
        int[][] bh = dcMultiply(x, xRowStart, xRowEnd/2, xColEnd/2, xColEnd,   y, yRowEnd/2, yRowEnd  , yColEnd/2, yColEnd  );
        int[][] ce = dcMultiply(x, xRowEnd/2, xRowEnd,   xColStart, xColEnd/2, y, yRowStart, yRowEnd/2, yColStart, yColEnd/2);
        int[][] dg = dcMultiply(x, xRowEnd/2, xRowEnd,   xColEnd/2, xColEnd,   y, yRowEnd/2, yRowEnd  , yColStart, yColEnd/2);
        int[][] cf = dcMultiply(x, xRowEnd/2, xRowEnd,   xColStart, xColEnd/2, y, yRowStart, yRowEnd/2, yColEnd/2, yColEnd  );
        int[][] dh = dcMultiply(x, xRowEnd/2, xRowEnd,   xColEnd/2, xColEnd,   y, yRowEnd/2, yRowEnd  , yColEnd/2, yColEnd  );
        int[][] r = new int[xRowEnd-xRowStart][yColEnd-yColStart];
        for(int i=0;i<r.length;i++){
            for(int j=0;j<r.length;j++){
                if(i<xRowEnd/2)
                    if(j<xColEnd/2)
                        r[i][j] = ae[i][j] + bg[i][j];
                    else
                        r[i][j] = af[i][j-xColEnd/2] + bh[i][j-xColEnd/2];
                else
                    if(j<xColEnd/2)
                        r[i][j] = ce[i-xRowEnd/2][j] + dg[i-xRowEnd/2][j];
                    else
                        r[i][j] = cf[i-xRowEnd/2][j-xColEnd/2] + dh[i-xRowEnd/2][j-xColEnd/2];
            }
        }
        return r;
    }

    public static int[][] strasMultiply(int[][] x, int[][] y){
        return strasMultiply(x, 0, x.length, 0, x[0].length, y, 0, y.length, 0, y[0].length);
    }
    
    private static int[][]strasMultiply(int[][] x, int xRowStart, int xRowEnd, int xColStart, int xColEnd, int[][] y){
        return strasMultiply(x, xRowStart, xRowEnd, xColStart, xColEnd, y, 0, y.length, 0, y[0].length);
    }
    
    private static int[][]strasMultiply(int[][] x, int[][] y, int yRowStart, int yRowEnd, int yColStart, int yColEnd){
        return strasMultiply(x, 0, x.length, 0, x[0].length, y, yRowStart, yRowEnd, yColStart, yColEnd);
    }
    
    private static int[][] strasMultiply(int[][] x, int xRowStart, int xRowEnd, int xColStart, int xColEnd, int[][] y, int yRowStart, int yRowEnd, int yColStart, int yColEnd){
        if(xRowEnd-xRowStart==2)
            return baseCase(x, xRowStart, xColStart, y, yRowStart, yColStart);
        int[][] p1 = strasMultiply(x, xRowStart, xRowEnd/2, xColStart, xColEnd/2, subMatrix(y, yRowStart, yRowEnd/2, yColEnd/2, yColEnd,   y, yRowEnd/2, yRowEnd,   yColEnd/2, yColEnd  ));
        int[][] p2 = strasMultiply(addMatrix(x, xRowStart, xRowEnd/2, xColStart, xColEnd/2, x, xRowStart, xRowEnd/2, xColEnd/2, xColEnd  ), y, yRowEnd/2, yRowEnd, yColEnd/2, yColEnd);
        int[][] p3 = strasMultiply(addMatrix(x, xRowEnd/2, xRowEnd,   xColStart, xColEnd/2, x, xRowEnd/2, xRowEnd,   xColEnd/2, xColEnd  ), y, yRowStart, yRowEnd/2, yColStart, yColEnd/2);
        int[][] p4 = strasMultiply(x, xRowEnd/2, xRowEnd, xColEnd/2, xColEnd, subMatrix(y, yRowEnd/2, yRowEnd,   yColStart, yColEnd/2, y, yRowStart, yRowEnd/2, yColStart, yColEnd/2));
        int[][] p5 = strasMultiply(addMatrix(x, xRowStart, xRowEnd/2, xColStart, xColEnd/2, x, xRowEnd/2, xRowEnd,   xColEnd/2, xColEnd  ), addMatrix(y, yRowStart, yRowEnd/2, yColStart, yColEnd/2, y, yRowEnd/2, yRowEnd,   yColEnd/2, yColEnd  ));
        int[][] p6 = strasMultiply(subMatrix(x, xRowStart, xRowEnd/2, xColEnd/2, xColEnd,   x, xRowEnd/2, xRowEnd,   xColEnd/2, xColEnd  ), addMatrix(y, yRowEnd/2, yRowEnd,   yColStart, yColEnd/2, y, yRowEnd/2, yRowEnd,   yColEnd/2, yColEnd  ));
        int[][] p7 = strasMultiply(subMatrix(x, xRowStart, xRowEnd/2, xColStart, xColEnd/2, x, xRowEnd/2, xRowEnd,   xColStart, xColEnd/2), addMatrix(y, yRowStart, yRowEnd/2, yColStart, yColEnd/2, y, yRowStart, yRowEnd/2, yColEnd/2, yColEnd  ));
        int[][] r = new int[xRowEnd-xRowStart][yColEnd-yColStart];
        for(int i=0;i<r.length;i++){
            for(int j=0;j<r.length;j++){
                if(i<xRowEnd/2)
                    if(j<xColEnd/2)
                        r[i][j] = p5[i][j] + p4[i][j] - p2[i][j] + p6[i][j];
                    else
                        r[i][j] = p1[i][j-xColEnd/2] + p2[i][j-xColEnd/2];
                else
                    if(j<xColEnd/2)
                        r[i][j] = p3[i-xRowEnd/2][j] + p4[i-xRowEnd/2][j];
                    else
                        r[i][j] = p1[i-xRowEnd/2][j-xColEnd/2] + p5[i-xRowEnd/2][j-xColEnd/2] - p3[i-xRowEnd/2][j-xColEnd/2] - p7[i-xRowEnd/2][j-xColEnd/2];
            }
        }
        return r;
    }    
    
    private static int[][] addMatrix(int[][] x, int[][] y){
        return addMatrix(x, 0, x.length, 0, x[0].length, y, 0, y.length, 0, y[0].length);
    }
    
    private static int[][] addMatrix(int[][] x, int xRowStart, int xRowEnd, int xColStart, int xColEnd, int[][] y, int yRowStart, int yRowEnd, int yColStart, int yColEnd){
        int[][] r = new int[xRowEnd-xRowStart][yColEnd-yColStart];
        for(int i=0;i<r.length;i++)
            for(int j=0;j<r[0].length;j++)
                r[i][j] = x[xRowStart+i][xColStart+j] + y[yRowStart+i][yColStart+j];
        return r;
    }
    
    private static int[][] subMatrix(int[][] x, int[][] y){
        return subMatrix(x, 0, x.length, 0, x[0].length, y, 0, y.length, 0, y[0].length);
    }
    
    private static int[][] subMatrix(int[][] x, int xRowStart, int xRowEnd, int xColStart, int xColEnd, int[][] y, int yRowStart, int yRowEnd, int yColStart, int yColEnd){
        int[][] r = new int[xRowEnd-xRowStart][yColEnd-yColStart];
        for(int i=0;i<r.length;i++)
            for(int j=0;j<r[0].length;j++)
                r[i][j] = x[xRowStart+i][xColStart+j] - y[yRowStart+i][yColStart+j];
        return r;
    }
    
    private static int[][] baseCase(int[][] x, int xRow, int xCol, int[][] y, int yRow, int yCol){
        int[][] r = new int[2][2];
        r[0][0] = x[xRow][xCol] * y[yRow][yCol] + x[xRow][xCol+1] * y[yRow+1][yCol];
        r[0][1] = x[xRow][xCol] * y[yRow][yCol+1] + x[xRow][xCol+1] * y[yRow+1][yCol+1];
        r[1][0] = x[xRow+1][xCol] * y[yRow][yCol] + x[xRow+1][xCol+1] * y[yRow+1][yCol];
        r[1][1] = x[xRow+1][xCol] * y[yRow][yCol+1] + x[xRow+1][xCol+1] * y[yRow+1][yCol+1];
        return r;
    }
}
