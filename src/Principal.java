import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class Principal {        
    public static String local [] = {"Orlando Scarpelli","Ressacada","Arena Joinville","Heriberto Hulse","Arena Conda","Dr Hercilio Luz","Domingos Silveira Gonzalez","Sesi","Augusto Bauer"};
    
    static int[] pi;         
    static int[] ranking;

    public static int findSet(int x) {          
        int p = pi[x];
        if (x != p) {            
            p = findSet(p);
        }
        return p;
    }    

    public static void link(int x, int y){        
        if (ranking[x] > ranking[y]) {            
            pi[y] = x;
        } else  {            
            pi[x] = y;
            if (ranking[y] == ranking[x]) {                
                ranking[y] = ranking[y] + 1;
            }
        }
    }
    
    public static void union(int x, int y) {        
        link(findSet(x),findSet(y));        
    }
              
    public static void makeSet(int x){
        pi[x] = x;
        ranking[x] = 0;
    }
    public static String troca(int i) {
        return local[i];
    }


    public static void Caminho(int[][] A, int n) {
        int custo = 0;
        for (int v = 0; v < n-1; v++) {           
           System.out.println(troca(A[v][0]) + " -> " + troca(A[v][1])  + ": " + A[v][2] + " KM de Distância: ");                              
           custo = custo + A[v][2];        }
        System.out.println("Custo Total:" + custo);
    }

    public static List getMatrizVertices(int[][] G){
        int n = G.length;
        List vertices = new LinkedList();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if ((i<j)&&(G[i][j]!=0)){
                    vertices.add(new int[]{i,j,G[i][j]});
                }            
            }            
        }
        return vertices;
    }    
    
    public static int[][] rodarKruskal(int[][] G) {
        int[][] A;
         
        List arestas = getMatrizVertices(G); 
        int n = arestas.size();
                                     
        A = new int[n][];
        pi = new int[n];
        ranking = new int[n];
        for (int v = 0; v < n; v++) {
            makeSet(v);                
        }
        Collections.sort(arestas, (int[] e1, int[] e2) -> {
            if (e1[2] < e2[2]) return -1;
            if (e1[2] > e2[2]) return 1;
            return 0;
        });                                                 
        
        int linha = 0;
        for(int i=0; i<arestas.size();i++){            
            int[] vertice = (int[])arestas.get(i);
            int u = findSet(vertice[0]);
            int v = findSet(vertice[1]);
            if (u != v) {                
                A[linha] = vertice;
                linha = linha + 1;                
                union(u,v);
            }
        }
        return A;        
     }    

    public static void main(String args[]) {
               
        int G[][] =
                
               //a  b   c    d    e    f   g    h    i 
               {{0, 14, 169, 196, 539, 85, 137, 138, 92}, //a (Orlando Scarpelli)
                {14, 0, 183, 210, 553, 99, 151, 150, 106}, //b (Ressacada)
                {169, 183, 0, 358, 518, 89, 299, 114, 115}, //c (Arena Joinville)
                {196, 210, 358, 0, 509, 280,64, 329, 247}, //d (Heriberto Hulse)
                {539, 553, 518, 509, 0, 535,545, 474, 520}, //e (Arena Conda)
                {85, 99, 89, 280,535,0, 221, 56, 37}, //f (Dr Hercilio Luz)
                {137, 151, 299, 64, 545, 221, 0, 269, 223}, //g (Domingos Silveira Gonzalez)
                {138, 150,114, 329, 474, 56, 269, 0, 38}, //h (SESI)
                {92, 106, 115, 247, 520, 37, 223, 38, 0}};//i (Augusto Bauer)

        int n = G.length;
        
        System.out.println(">>> Menor Distâncias entre os estádios Catarinenses <<<");

        int[][] g = rodarKruskal(G);

        Caminho(g, n);
    }
}