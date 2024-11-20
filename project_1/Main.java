import java.util.*;
public class Main{
    public static void main(String[] args){
        int[][] a = {{2,0,-1,6},{3,7,8,0},{-5,1,6,-2},{8,0,1,7}};
        int[][] b = {{0,1,6,3},{-2,8,7,1},{2,0,-1,0},{9,1,6,-2}};
        int[][] c = {{52,8,49,-6},{2,59,59,16},{-8,1,-41,-10},{65,15,89,10}};

        int[][] bfResults = MultiplyMatrix.bfMultiply(a,b);
        int[][] dcResults = MultiplyMatrix.dcMultiply(a,b);
        int[][] strasResults = MultiplyMatrix.strasMultiply(a,b);

        System.out.println("Brute Force Result: ");
        printMatrix(bfResults);
        System.out.println("Divide Conquer Result: ");
        printMatrix(dcResults);
        System.out.println("Strassen Result: ");
        printMatrix(strasResults);
        
        int[][] x;
        long startTime, endTime;
        ArrayList<Integer> bfData = new ArrayList<Integer>();
        ArrayList<Integer> dcData = new ArrayList<Integer>();
        ArrayList<Integer> strasData = new ArrayList<Integer>();
        for(int i=1;i<=2048;i*=2){
            x = new int[i][i];
            for(int j=0;j<x.length;j++)
                Arrays.fill(x[j], 1);
            startTime = System.currentTimeMillis();
            MultiplyMatrix.bfMultiply(x,x);
            endTime = System.currentTimeMillis();
            bfData.add((int)(endTime-startTime));
            
            startTime = System.currentTimeMillis();
            MultiplyMatrix.bfMultiply(x,x);
            endTime = System.currentTimeMillis();
            dcData.add((int)(endTime-startTime));
            
            startTime = System.currentTimeMillis();
            MultiplyMatrix.bfMultiply(x,x);
            endTime = System.currentTimeMillis();
            strasData.add((int)(endTime-startTime));
            
            System.out.print(i+" ");
        }
        System.out.println();
        System.out.println("BF Data: "+bfData);
        System.out.println("DC Data: "+dcData);
        System.out.println("Stras Data: "+strasData);
    }

    private static void printMatrix (int[][] m){
        for(int i=0;i<m.length;i++){
            for(int j=0;j<m[0].length;j++){
                System.out.printf("%4d", m[i][j]);
            }
            System.out.println();
        }
    }
}