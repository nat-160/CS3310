import java.util.*;
class Pathfinder{
  /**
  * Uses Floyd-Warshall to find shortest paths
  */
  public static int[][] findPathsFW(int[][] graph){
    int[][] dist = new int[graph.length][graph.length];
    for(int u=0;u<graph.length;u++){
      for(int v=0;v<graph.length;v++){
        if(u==v){
          dist[u][v] = 0;
        } else if(graph[u][v]==0) {
          dist[u][v] = 1000;
        } else {
          dist[u][v] = graph[u][v];
        }
      }
    }
    for(int k=0;k<graph.length;k++){
      for(int i=0;i<graph.length;i++){
        for(int j=0;j<graph.length;j++){
          if(dist[i][j]>dist[i][k]+dist[k][j]){
            dist[i][j] = dist[i][k] + dist[k][j];
          }
        }
      }
    }
    formatGraph(dist);
    return dist;
  }
  
  /**
  * Uses Dijkstra to find shortest paths
  */
  public static int[][] findPathsD(int[][] graph){
    int[][] dist = new int[graph.length][graph.length];
    for(int u=0;u<graph.length;u++){
      int[] d = findPathD(graph,u);
      for(int v=0;v<d.length;v++){
        dist[u][v] = d[v];
      }
    }
    formatGraph(dist);
    return dist;
    
  }

  /**
  * Helper method for Dijkstra that calculates 1 vertex
  */
  private static int[] findPathD(int[][] graph, int source){
    int[] dist = new int[graph.length];
    int[] prev = new int[graph.length];
    Set<Integer> Q = new HashSet<Integer>();
    Arrays.fill(dist, 1000);
    Arrays.fill(prev, -1);
    for(int i=0;i<graph.length;i++)
      Q.add(i);
    dist[source] = 0;

    while(Q.size()!=0){
      int u = -1;
      for(int vertex:Q)
        if(u==-1 || dist[u]>dist[vertex])
          u = vertex;
      Q.remove(u);
      for(int v:Q){
        if(graph[u][v]!=0){
          int alt = dist[u] + graph[u][v];
          if(alt < dist[v]){
            dist[v] = alt;
            prev[v] = u;
          }
        }
      }
    }
    return dist;
  }

  /**
  * Gets rid of placeholders and resets same node distance
  **/
  private static void formatGraph(int[][] graph){
    for(int i=0;i<graph.length;i++){
      for(int j=0;j<graph.length;j++){
        if(graph[i][j]>999 || graph[i][j]<0 || i==j){
          graph[i][j] = 0;
        }
      }
    }
  }
}
