import java.util.ArrayList;
class Main {
  /**
  * Generates graphs and calculates shortest paths
  * Creates larger graphs each run, and checks for correctness
  */
  public static void main(String[] args) {
    //Sanity Check + output
    int[][] graph,fwGraph,dGraph;
    for(int i=1;i<=2;i++){
      boolean isSparse = i==1;
      for(int n=5;n<=20;n+=5){
        System.out.print("Graph size: "+n+"x"+n+", ");
        if(isSparse){
          System.out.println("sparse");
        } else {
          System.out.println("dense");
        }
        graph = newGraph(n, isSparse);
        printGraph(graph);
        dGraph = Pathfinder.findPathsD(graph);
        System.out.println("Distances using Dijkstra:");
        printGraph(dGraph);
        fwGraph = Pathfinder.findPathsFW(graph);
        System.out.println("Distances using Floyd-Warshall:");
        printGraph(fwGraph);

        //check if graphs are equivalent
        int errors = 0;
        for(int x=0;x<n;x++){
          for(int y=0;y<n;y++){
            if(fwGraph[x][y]!=dGraph[x][y]){
              errors++;
            }
          }
        }
        if(errors==0){
          System.out.println("Sanity Check: Passed!");
        } else {
          System.out.println("Sanity Check: Failed.");
        }
      }
    }
    //Runtime data
    ArrayList<Integer> nData = new ArrayList<Integer>();
    ArrayList<Integer> dData = new ArrayList<Integer>();
    ArrayList<Integer> fwData = new ArrayList<Integer>();
    long startTime, endTime;
    for(int n=10;n<=150;n+=10){
      long dTime = 0, fwTime = 0;
      for(int x=0;x<10;x++){
        graph = newGraph(n, x%2==0);
        startTime = System.currentTimeMillis();
        dGraph = Pathfinder.findPathsD(graph);
        endTime = System.currentTimeMillis();
        dTime += endTime-startTime;
        startTime = System.currentTimeMillis();
        fwGraph = Pathfinder.findPathsFW(graph);
        endTime = System.currentTimeMillis();
        fwTime += endTime-startTime;
      }
      nData.add(n);
      dData.add((int)(dTime/10));
      fwData.add((int)(fwTime/10));
    }
    System.out.println("Graph sizes: "+nData);
    System.out.println("Dijkstra Runtimes: "+dData);
    System.out.println("Floyd-Warshall Runtimes: "+fwData);
  }

  /**
  * Creates a graph size nxn as 2d int array
  */
  private static int[][] newGraph(int n, boolean sparse){
    int[][] graph = new int[n][n];
    for(int i=0;i<graph.length;i++){
      for(int j=i+1;j<graph[i].length;j++){
        int x = (int)(9*Math.random());
        if(sparse){
          if(Math.round(Math.random())==0){
            x = 0;
          }
        }
        graph[i][j] = x;
        graph[j][i] = x;
      }
    }
    return graph;
  }

  /**
  * Displays 2d array graph to STOUT
  */
  private static void printGraph(int[][] graph){
    for(int i=0;i<graph.length;i++){
      for(int j=0;j<graph[i].length;j++){
        if(graph[i][j]!=0 || i==j){
          System.out.printf("%3d",graph[i][j]);
        } else {
          System.out.printf("  x");
        }
      }
      System.out.println();
    }
  }
}
