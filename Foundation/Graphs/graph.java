import java.util.*;

public class graph {
    public static class Edge {
        int src;
        int nbr;
        int wt;

        Edge(int src, int nbr, int wt) {
            this.src = src;
            this.nbr = nbr;
            this.wt = wt;
        }
    }

    public static void addEdge(ArrayList<Edge>[] graph, int src, int nbr, int wt) {
        graph[src].add(new Edge(src, nbr, wt));
        graph[nbr].add(new Edge(nbr, src, wt));
    }

    public static void display(ArrayList<Edge>[] graph) {
        for (int i = 0; i < graph.length; i++) {
            System.out.print(i+"->");
            for (int j = 0; j < graph[i].size(); i++) {
                System.out.print("(" + graph[i].get(j).src + graph[i].get(j).nbr + " " + graph[i].get(2).wt+") ");
            }
            System.out.println();
        }
    }

    public static int FindEdge(ArrayList<Edge>[] graph, int u, int v) {
        int edge = -1;
        for (int j = 0; j < graph[u].size(); j++) {
            if (v == graph[u].get(j).nbr) {
                edge = j;
            }
        }
        return edge;
    }

    public static void RemoveEdge(ArrayList<Edge>[] graph, int u, int v) {
        int e1 = FindEdge(graph, u, v);
        int e2 = FindEdge(graph, v, u);

        graph[u].remove(e1);
        graph[v].remove(e2);
    }

    public static void removeVtx(ArrayList<Edge>[] graph, int u) {
        ArrayList<Edge>list=graph[u];
        for(int i=list.size()-1;i>=0;i--){
            Edge e=list.get(i);
            RemoveEdge(graph, e.src,e.nbr );
        }
    }

    public static boolean HasPath(ArrayList<Edge>[] graph, int src, int dest,boolean[]visited){
        if(src==dest)return true;
        visited[src]=true;
        boolean res=false;
        for(Edge e:graph[src]){
            if(!visited[e.nbr])
            res=res||HasPath(graph, e.nbr, dest, visited);
        }
        return res;
    }

    public static int printAllPaths(ArrayList<Edge>[] graph, int src, int dest, boolean[] vis,String psf) {
        if(src==dest){
            psf+=src;
            System.out.println(psf);
            return 1;
        }
        int count=0;
        vis[src]=true;
        for(Edge e:graph[src]){
            if(!vis[e.nbr]){
            count+=printAllPaths(graph, e.nbr, dest, vis, psf+src);
            }   
        }
        vis[src]=false;
        return count;
    }   

    public static void preOrder(ArrayList<Edge>[] graph, int src, boolean[] vis,int wsf,String psf){
        System.out.println(src+" -> "+psf+src+" @ "+ wsf);
        vis[src]=true;
        for(Edge e : graph[src]){
            if(!vis[e.nbr])preOrder(graph, e.nbr, vis, wsf+e.wt, psf+src);
        }
        vis[src]=false;
    }

    public static void postOrder(ArrayList<Edge>[] graph, int src, boolean[] vis,int wsf,String psf){
        vis[src]=true;
        for(Edge e : graph[src]){
            if(!vis[e.nbr])preOrder(graph, e.nbr, vis, wsf+e.wt, psf+src);
        }
        System.out.println(src+" -> "+psf+" @ "+ wsf);
        vis[src]=false;
    }

    public static class pathPairLightest{
        String psf = "";
        int wsf = (int)1e9;
    }

    public static pathPairLightest lightestPath(ArrayList<Edge>[] graph, int src, int dest, boolean[] vis) {
        if(src==dest){
            pathPairLightest base=new pathPair();
            base.wsf=0;
            base.psf=src+base.psf;
            return base;
        }
        vis[src]=false;
        pathPairLightest myAns=new pathPair();
        for(Edge e : graph[src]){
            if(!vis[e.nbr]){
                pathPairLightest recAns=heaviestPath(graph, e.nbr, dest, vis);
                if(recAns.wsf!=(int)1e9 && e.wt +recAns.wsf<myAns.wsf){
                    myAns.psf=src+recAns.psf;
                    myAns.wsf=e.wt+recAns.wsf;
                }
            }
        }
        vis[src]=false;
        return myAns;
    }

    public static class pathPairHeaviest {
        String psf = "";
        int wsf = -1;
    }
    
    public static pathPairHeaviest heaviestPath(ArrayList<Edge>[] graph, int src, int dest, boolean[] vis) {
        if(src==dest){
            pathPairHeaviest base=new pathPair();
            base.wsf=0;
            base.psf=src+base.psf;
            return base;
        }
        vis[src]=false;
        pathPairHeaviest myAns=new pathPair();
        for(Edge e : graph[src]){
            if(!vis[e.nbr]){
                pathPairHeaviest recAns=heaviestPath(graph, e.nbr, dest, vis);
                if(recAns.wsf!=-1 && e.wt +recAns.wsf>myAns.wsf){
                    myAns.psf=src+recAns.psf;
                    myAns.wsf=e.wt+recAns.wsf;
                }
            }
        }
        vis[src]=false;
        return myAns;
    }

    public static class ceilFLoorPair{
        int ceil=(int)1e9;
        int floor=-(int)1e9;
    }

    public static void ceilAndFloor(ArrayList<Edge>[]graph,int src,int data, ceilFLoorPair cfPair,boolean[] vis,int wsf){
        if(data>wsf){
            cfpair.ceil=Math.min(cfpair.ceil,wsf);
        }
        if(data<wsf){
            cfpair.ceil=Math.max(cfpair.floor,wsf+find);
        }
        vis[src]=true;
        for(Edge e : graph[src]){
            if(!vis[e.nbr])ceilAndFloor(graph, e.nbr, data,cfPair,vis,wsf+e.wt);;
        }
        vis[src]=false;
    }

    public static void DFS(ArrayList<Edge>[]graph,int src,boolean[]vis,ArrayList<Integer>temp){
        vis[src] = true;
        temp.add(src);
        for (Edge e : graph[src]) {
            if (!vis[e.nbr])
                DFS(graph, e.nbr, vis,temp);
        }
    }

    public static ArrayList<ArrayList<Integer>> GCC(ArrayList<Edge>[]graph){
        ArrayList<ArrayList<Integer>>ans=new ArrayList<ArrayList<Integer>>();
        boolean[] gccVis = new boolean[graph.length];
        for(int i=0;i<graph.length;i++){
            ArrayList<Integer>temp=new ArrayList<>();
            if(!gccVis[i]){
                DFS(graph,i,gccVis,temp);
            }
            if(temp.size()>0)ans.add(temp);
        }
        return ans;
    }

    public static boolean isGraphConnected(ArrayList<Edge>[]graph){
        int gcc=0;
        boolean[] gccVis=new boolean[graph.length];
        for(int i=0;i<graph.length;i++){
            if(!gccVis[i]){
                gccVis[i]=true;
                gcc++;
            }
            for(Edge e:graph[i]){
                gccVis[e.nbr]=true;
            }
        }
        return gcc>1?false:true;
    }

      public void dfs(char[][] grid, int[][] dir, int sr, int sc) {
        grid[sr][sc] = '0';
        for (int d = 0; d < 4; d++) {
            int r = sr + dir[d][0];
            int c = sc + dir[d][1];

            if (r >= 0 && c >= 0 && r < grid.length && c < grid[0].length && grid[r][c] == '1')
                dfs(grid, dir, r, c);
        }

    }

    public int numIslands(char[][] grid) {
        int n = grid.length, m = grid[0].length, componentCount = 0;

        int[][] dir = { { 1, 0 }, { -1, 0 }, { 0, -1 }, { 0, 1 } };

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (grid[i][j] == '1') {
                    dfs(grid, dir, i, j);
                    componentCount++;
                }
            }
        }
        return componentCount;
    }

    public int dfs(int[][] grid, int[][] dir, int sr, int sc) {

        grid[sr][sc] = 0;
        int size = 0;
        for (int d = 0; d < 4; d++) {
            int r = sr + dir[d][0];
            int c = sc + dir[d][1];

            if (r >= 0 && c >= 0 && r < grid.length && c < grid[0].length && grid[r][c] == 1)
                size += dfs(grid, dir, r, c);
        }

        return size + 1;

    }

    public int maxAreaOfIsland(int[][] grid) {
        int n = grid.length, m = grid[0].length, maxSize = 0;

        int[][] dir = { { 1, 0 }, { -1, 0 }, { 0, -1 }, { 0, 1 } };

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (grid[i][j] == 1) {
                    int s = dfs(grid, dir, i, j);
                    maxSize = Math.max(maxSize, s);
                }
            }
        }
        return maxSize;
    }

    public static void HamiltonianCycle(ArrayList<Edge>[]graph,int osrc,int src,String ans,int EdgeCount,boolean[]vis){
        if(EdgeCount==graph.length-1){
            int temp=FindEdge(graph, osrc,src);
            if(temp!=-1){
                System.out.println(ans+src+"*");
            }
            else{
                System.out.println(ans+src+".");
            }
        }
        
        vis[src]=true;
        for(Edge e:graph[src]){
            if(!vis[e.nbr])
            HamiltonianCycle(graph,osrc,e.nbr,ans+src,EdgeCount+1,vis);
        }
        vis[src]=false;
    }

    public static void BFS(ArrayList<Edge>[]graph,int src,int dest){
        LinkedList<Integer> que = new LinkedList<>();
        int N = graph.length;
        boolean[] vis = new boolean[N];

        que.addLast(src);
        int level=0;

        boolean isCyclePresent = false;
        int shortestPath = -1;        

        while(que.size()!=0){
            int size=que.size();
            while(sizw-->0){
                int rvtx=que.removeFirst();

                if(vis[rvtx]){
                    isCyclePresent=true;
                }

                if(rvtx==dest){
                    shortestPath=level;
                }

                vis[rvtx]=true;

                for(Edge e:graph[rvtx]){
                    if(!vis[e.nbr])que.addLast(e.nbr);
                }
            }
            level++;
        }
    }

     public static boolean BFSHelper(ArrayList<Edge>[]graph,int src,boolean[]vis){
       LinkedList<Integer>que=new LinkedList<>();
       que.addLast(src);
       while(que.size()!=0){
           int size=que.size();
           while(size-->0){
               int rvtx=que.removeFirst();
               if(vis[rvtx]){
                   return true;
               }
               vis[rvtx]=true;
               for(Edge e :graph[rvtx]){
                   if(!vis[e.nbr]){
                       que.addLast(e.nbr);
                   }
               }
           }
       }
       return false;
   }

    public static class BFSPair{
        int vtx;
        String psf="";
        int wsf;

        BFSPair(int vtx,String psf,int wsf){
            this.vtx=vtx;
            this.psf=psf;
            this.wsf=wsf;
        }
    }

    public static void BFS(ArrayList<Edge>[]graph){
        boolean[]vis=new boolean[graph.length];
        boolean res=false;
        for(int i=0;i<graph.length;i++){
        if(!vis[i])    
        res=res||BFS(graph,0,vis);
        }
        System.out.println(res);
    }

    public static void BFSPath(ArrayList<Edge>[]graph){
        boolean []vis=new boolean[graph.length];
        for(int i=0;i<graph.length;i++){
            if(vis[i])continue;
            LinkedList<BFSPair>que=new LinkedList<>();
            que.addLast(new BFSPair(i,i+"",0));
            while(que.size()!=0){
                int size=que.size();
                while(size-->0){
                    BFSPair re=que.removeFirst();
                    if(vis[re.vtx])continue;
                    System.out.println(re.vtx+"->"+re.psf+"@"+re.wsf);
                    vis[re.vtx]=true;
                    for(Edge e:graph[re.vtx]){
                        if(!vis[e.nbr]){
                            que.addLast(new BFSPair(e.nbr,re.psf+e.nbr,re.wsf+e.wt));                      
                        }
                    }
                }
            }
        }
    }

     public static int Infection(ArrayList<Edge>[]graph, int src, boolean[]vis, int t) {
    LinkedList<Integer>que = new LinkedList<>();
    que.addLast(src);
    int inf = 0;
    int level = 1;
    while (que.size() != 0) {
      int size = que.size();
      if (level > t)return inf;
      while (size-- > 0) {
        int rvtx = que.removeFirst();
        if (vis[rvtx])continue;
        inf++;
        vis[rvtx] = true;
        for (Edge e : graph[rvtx]) {
          if (!vis[e.nbr]) {
            que.addLast(e.nbr);
          }
        }
      }
      level++;
    }
    return inf;
  }

  public static class BFSPair2{
    int vtx;
    String psf="";
    int level;

    BFSPair2(int vtx,String psf,int level){
        this.vtx=vtx;
        this.psf=psf;
        this.level=level;
    }
}

 static class Pair implements Comparable<Pair> {
      int wsf;
      String psf;

      Pair(int wsf, String psf){
         this.wsf = wsf;
         this.psf = psf;
      }

      public int compareTo(Pair o){
         return this.wsf - o.wsf;
      }
   }

   public static Integer spathwt = Integer.MAX_VALUE;
   public static String lpath;
   public static String spath;
   public static Integer lpathwt = Integer.MIN_VALUE;
   public static String cpath;
   public static Integer cpathwt = Integer.MAX_VALUE;
   public static String fpath;
   public static Integer fpathwt = Integer.MIN_VALUE;
   public static PriorityQueue<Pair> pq = new PriorityQueue<>();
   public static void multisolver(ArrayList<Edge>[] graph, int src, int dest, boolean[] visited, int criteria, int k, String psf, int wsf) {
      if (src == dest) {
         if(wsf < spathwt){
            spathwt = wsf;
            spath = psf;
         }

         if(wsf > lpathwt){
            lpathwt = wsf;
            lpath = psf;
         }

         if(wsf < criteria && wsf > fpathwt){
            fpathwt = wsf;
            fpath = psf;
         }

         if(wsf > criteria && wsf < cpathwt){
            cpathwt = wsf;
            cpath = psf;
         }

         if(pq.size() <  k){
            pq.add(new Pair(wsf, psf));
         } else if(wsf > pq.peek().wsf){
            pq.remove();
            pq.add(new Pair(wsf, psf));
         }
         return;
      }

      visited[src] = true;
      for (Edge e : graph[src]) {
         if (!visited[e.nbr]) {
            multisolver(graph, e.nbr, dest, visited, criteria, k, psf + e.nbr, wsf + e.wt);
         }
      }
      visited[src] = false;
   }
}

public static boolean isBipartiteHelper(ArrayList<Edge>[]graph, int src, int[]vis) {
    LinkedList<BFSPair2> que = new LinkedList<>();
    que.addLast(new BFSPair2(src, src + "", 0));
    while (que.size() != 0) {
      BFSPair2 remv = que.removeFirst();
      if (vis[remv.vtx] != -1) {
        if (vis[remv.vtx] != remv.level)return false;
      }
      else {
        vis[remv.vtx] = remv.level;
      }
      for (Edge e : graph[remv.vtx]) {
        if (vis[e.nbr] == -1)que.addLast(new BFSPair2(e.nbr, remv.psf + e.nbr, remv.level + 1));
      }
    }
    return true;
  }
  
    public static boolean isBipartite(ArrayList<Edge>[]graph){
        int[] vis=new int[graph.length];
        Arrays.fill(vis,-1);
        for(int i=0;i<graph.length;i++){
            if(vis[i]==-1){
                boolean ans=isBipartiteHelper(graph,i,vis);
                if(!ans)return false;
            }
        }
        return true;
    }
//    public static boolean bipartite(ArrayList<Edge>[] graph, int src, int[] vis) {
//         LinkedList<Integer> que = new LinkedList<>();
//         que.addLast(src);
//         int color = 0; // 0 : red, 1 : green
//         boolean cycle = false, isBipartite = true;

//         while (que.size() != 0) {
//             int size = que.size();
//             while (size-- > 0) {
//                 int rvtx = que.removeFirst();
//                 if (vis[rvtx] != -1) { // cycle
//                     cycle = true;
//                     if (vis[rvtx] != color) { // conflict
//                         isBipartite = false;
//                         break;
//                     }

//                     continue; // not any kind oo conflict
//                 }

//                 vis[rvtx] = color;

//                 for (Edge e : graph[rvtx]) {
//                     if (vis[e.nbr] == -1) {
//                         que.addLast(e.nbr);
//                     }
//                 }
//             }

//             color = (color + 1) % 2;
//             if (!isBipartite)
//                 break;
//         }

//         if (cycle) {
//             if (isBipartite)
//                 System.out.println("Even Length Cycle");
//             else
//                 System.out.println("Odd Length Cycle");
//         } else if (isBipartite && !cycle) {
//             System.out.println("A-Cycle and Bipartite graph");
//         }

//         return isBipartite;
//     }

//     public static void bipartite(ArrayList<Edge>[] graph) {

//         int N = graph.length;
//         int[] vis = new int[N];
//         Arrays.fill(vis, -1);

//         boolean isBipartite = true;
//         for (int i = 0; i < N; i++) {
//             if (vis[i] == -1) {
//                 isBipartite = isBipartite && bipartite(graph, i, vis);
//             }
//         }

//         System.out.println("Overall Graph is Bipartite: " + isBipartite);
//     }

    public static void construction() {
        int N = 7;
        ArrayList<Edge>[] graph = new ArrayList[N];
        for (int i = 0; i < N; i++)
            graph[i] = new ArrayList<>();

        addEdge(graph, 0, 1, 10);
        addEdge(graph, 0, 3, 10);
        addEdge(graph, 1, 2, 10);
        addEdge(graph, 2, 3, 40);
        addEdge(graph, 3, 4, 2);
        addEdge(graph, 4, 5, 2);
        addEdge(graph, 4, 6, 8);
        addEdge(graph, 5, 6, 3);
    }
    
}