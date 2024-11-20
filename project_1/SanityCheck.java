import junit.framework.*;
public class SanityCheck extends TestCase{
    protected int[][] a, b, c;
    
    protected void setUp(){
        int[][] aa = {{2,0,-1,6},{3,7,8,0},{-5,1,6,-2},{8,0,1,7}};
        int[][] bb = {{0,1,6,3},{-2,8,7,1},{2,0,-1,0},{9,1,6,-2}};
        int[][] cc = {{52,8,49,-6},{2,59,59,16},{-8,1,-41,-10},{65,15,89,10}};
        a = aa;
        b = bb;
        c = cc;
    }
    
    public void testBF(){
        int[][] cc = MultiplyMatrix.bfMultiply(a,b);
        assertTrue(c.length == cc.length);
        assertTrue(c[0].length == cc[0].length);
        for(int i=0;i<4;i++){
            for(int j=0;j<4;j++){
                assertTrue(c[i][j] == cc[i][j]);
            }
        }
    }
    
    public void testDC(){
        int[][] cc = MultiplyMatrix.dcMultiply(a,b);
        assertTrue(c.length == cc.length);
        assertTrue(c[0].length == cc[0].length);
        for(int i=0;i<4;i++){
            for(int j=0;j<4;j++){
                assertTrue(c[i][j] == cc[i][j]);
            }
        }
    }
    
    public void testStras(){
        int[][] cc = MultiplyMatrix.strasMultiply(a,b);
        assertTrue(c.length == cc.length);
        assertTrue(c[0].length == cc[0].length);
        for(int i=0;i<4;i++){
            for(int j=0;j<4;j++){
                assertTrue(c[i][j] == cc[i][j]);
            }
        }
    }
}
