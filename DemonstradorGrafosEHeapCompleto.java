// /**
//  * Demonstrador COMPLETO de Estruturas e Algoritmos de Grafos + Heap
//  * Baseado em "Algoritmos: Teoria e Prática" (Cormen – CLRS) – Caps. 21 a 25.
//  *
//  * Inclui:
//  *   • Representações de Grafo  – Lista de Adjacência / Função de Incidência
//  *   • Buscas                   – DFS & BFS
//  *   • Caminhos Mínimos         – Dijkstra (≥ 0) & Bellman‑Ford (∃ neg.)
//  *   • Árvores Geradoras Mínimas– Kruskal (Union‑Find) & Prim (PQ)
//  *   • Estruturas de Dados      – Max‑Heap + HeapSort
//  *
//  * Comentários didáticos em cada parte → referência rápida de prova.
//  * Compile:  javac DemonstradorGrafosEHeapCompleto.java
//  * Execute:  java  DemonstradorGrafosEHeapCompleto
//  */

//  import java.util.*;

//  public class DemonstradorGrafosEHeapCompleto {
 
//      /* ================================================================ */
//      /* 1.  ESTRUTURAS BÁSICAS                                           */
//      /* ================================================================ */
 
//      /**
//       * Aresta genérica.  Comparable Ѻ ordenação por peso (Kruskal / PQ).
//       *  ‑ origem / destino : vértices
//       *  ‑ peso             : custo da aresta (≥0 ou não)
//       *  ‑ id               : rótulo textual → utilizada só na Função de Incidência
//       */
//      static class Aresta implements Comparable<Aresta> {
//          int origem, destino, peso;
//          String id;
 
//          // Aresta ponderada
//          Aresta(int origem, int destino, int peso) {
//              this.origem  = origem;
//              this.destino = destino;
//              this.peso    = peso;
//          }
 
//          // Aresta identificada (não ponderada)
//          Aresta(String id, int origem, int destino) {
//              this.id      = id;
//              this.origem  = origem;
//              this.destino = destino;
//          }
 
//          @Override public int compareTo(Aresta o) {
//              /*  Ordenação crescente por peso – usada em Kruskal e PQ do Prim. */
//              return Integer.compare(this.peso, o.peso);
//          }
 
//          @Override public String toString() {
//              return (id != null)
//                     ? String.format("%s:(%d‑%d)", id, origem, destino)
//                     : String.format("(%d‑%d,w:%d)", origem, destino, peso);
//          }
//      }
 
//      /**
//       * Union‑Find (Disjoint‑Set) com Path‑Compression + Union‑by‑Rank.
//       *  CLRS Cap. 21 – Estrutura vital para Kruskal.
//       */
//      static class UnionFind {
//          int[] pai;
//          int[] rank;
 
//          UnionFind(int n) {
//              pai  = new int[n];
//              rank = new int[n];
//              for (int i = 0; i < n; i++) {
//                  pai[i]  = i;   // cada vértice é seu próprio conjunto
//                  rank[i] = 0;   // altura inicial 0
//              }
//          }
 
//          /* Find com compressão de caminho → quase O(1) amortizado. */
//          int find(int i) {
//              if (pai[i] != i) {
//                  pai[i] = find(pai[i]);
//              }
//              return pai[i];
//          }
 
//          /* Union by Rank → mantém árvores rasas. */
//          boolean union(int i, int j) {
//              int ri = find(i);
//              int rj = find(j);
//              if (ri == rj) return false;          // já no mesmo conjunto
 
//              if (rank[ri] < rank[rj])      pai[ri] = rj;
//              else if (rank[ri] > rank[rj]) pai[rj] = ri;
//              else { pai[rj] = ri; rank[ri]++; }
//              return true;
//          }
//      }
 
//      /* ================================================================ */
//      /* 2.  REPRESENTAÇÕES DE GRAFO                                       */
//      /* ================================================================ */
 
//      /** Grafo direcionado e ponderado usando Lista de Adjacência. */
//      static class Grafo {
//          final int V;                                   // |V|
//          List<List<Aresta>> adj = new ArrayList<>();    // adj[u] → arestas de u
 
//          Grafo(int V) {
//              this.V = V;
//              for (int i = 0; i < V; i++) adj.add(new ArrayList<>());
//          }
 
//          void add(int o, int d, int w) { adj.get(o).add(new Aresta(o, d, w)); }
//          List<Aresta> vizinhos(int u)  { return adj.get(u); }
//      }
 
//      /** Função de Incidência  φ : id(aresta) ↦ {u,v}. */
//      static Map<String, Set<Integer>> phi(List<Aresta> ar) {
//          Map<String, Set<Integer>> res = new HashMap<>();
//          for (Aresta a : ar) res.put(a.id, Set.of(a.origem, a.destino));
//          return res;
//      }
 
//      /* ================================================================ */
//      /* 3.  BUSCAS – DFS & BFS                                            */
//      /* ================================================================ */
 
//      /** DFS iterativo (pilha explícita).  Retorna ordem de visita. */
//      static List<Integer> dfs(Grafo g, int s) {
//          boolean[] vis = new boolean[g.V];
//          List<Integer> ordem = new ArrayList<>();
//          Deque<Integer> st = new ArrayDeque<>();
//          st.push(s);
//          while (!st.isEmpty()) {
//              int u = st.pop();
//              if (!vis[u]) {
//                  vis[u] = true;
//                  ordem.add(u);
//                  /*  Empilha vizinhos em ordem reversa ⇒ saída mais legível. */
//                  List<Aresta> vs = new ArrayList<>(g.vizinhos(u));
//                  Collections.reverse(vs);
//                  vs.forEach(a -> { if (!vis[a.destino]) st.push(a.destino); });
//              }
//          }
//          return ordem;
//      }
 
//      /** BFS – distâncias em número de arestas + predecessores. */
//      static int[] bfs(Grafo g, int s, int[] pred) {
//          int[] dist = new int[g.V];
//          Arrays.fill(dist, -1);
//          Arrays.fill(pred, -1);
//          Queue<Integer> q = new ArrayDeque<>();
//          q.add(s); dist[s] = 0;
//          while (!q.isEmpty()) {
//              int u = q.poll();
//              for (Aresta a : g.vizinhos(u)) {
//                  int v = a.destino;
//                  if (dist[v] == -1) {
//                      dist[v] = dist[u] + 1;
//                      pred[v] = u;
//                      q.add(v);
//                  }
//              }
//          }
//          return dist;
//      }
 
//      /* ================================================================ */
//      /* 4.  CAMINHOS MÍNIMOS (Cap. 25)                                    */
//      /* ================================================================ */
 
//      static final int INF = Integer.MAX_VALUE / 4;      // ‘∞’ seguro contra overflow
 
//      /** Relaxamento – coração de Dijkstra & Bellman‑Ford. */
//      static boolean relax(int u, int v, int w, int[] d, int[] p) {
//          if (d[u] != INF && d[v] > d[u] + w) {
//              d[v] = d[u] + w;
//              p[v] = u;
//              return true;                               // houve melhoria
//          }
//          return false;
//      }
 
//      /** Dijkstra – versão simples O(V²). */
//      static void dijkstra(Grafo g, int s, int[] d, int[] p) {
//          Arrays.fill(d, INF);
//          Arrays.fill(p, -1);
//          boolean[] vis = new boolean[g.V];
//          d[s] = 0;
//          for (int i = 0; i < g.V; i++) {
//              /*   Extrai vértice não processado com menor distância   */
//              int u = -1, best = INF;
//              for (int v = 0; v < g.V; v++) if (!vis[v] && d[v] < best) { best = d[v]; u = v; }
//              if (u == -1) break;           // restante inacessível
//              vis[u] = true;
//              for (Aresta a : g.vizinhos(u)) relax(u, a.destino, a.peso, d, p);
//          }
//      }
 
//      /** Bellman‑Ford – O(V·E) + detecção de ciclo negativo. */
//      static boolean bellmanFord(Grafo g, int s, int[] d, int[] p) {
//          Arrays.fill(d, INF);
//          Arrays.fill(p, -1);
//          d[s] = 0;
//          /* |V|‑1 fases de relaxamento */
//          for (int i = 1; i < g.V; i++)
//              for (int u = 0; u < g.V; u++)
//                  for (Aresta a : g.vizinhos(u))
//                      relax(u, a.destino, a.peso, d, p);
//          /* Passo extra → ciclo negativo? */
//          for (int u = 0; u < g.V; u++)
//              for (Aresta a : g.vizinhos(u))
//                  if (d[a.destino] > d[u] + a.peso) return false;  // ciclo
//          return true;
//      }
 
//      /* ================================================================ */
//      /* 5.  ÁRVORES GERADORAS MÍNIMAS                                     */
//      /* ================================================================ */
 
//      /** Kruskal – require lista de arestas não direcionadas. */
//      static List<Aresta> kruskal(int V, List<Aresta> ar) {
//          Collections.sort(ar);                      // peso crescente
//          UnionFind uf = new UnionFind(V);
//          List<Aresta> mst = new ArrayList<>();
//          for (Aresta a : ar)
//              if (uf.union(a.origem, a.destino)) {
//                  mst.add(a);
//                  if (mst.size() == V - 1) break;
//              }
//          return mst;
//      }
 
//      /** Prim – PQ com (v,custo). */
//      static List<Aresta> prim(int V, List<Aresta>[] adj, int s) {
//          boolean[] inMST = new boolean[V];
//          int[] key = new int[V]; Arrays.fill(key, INF); key[s] = 0;
//          int[] parent = new int[V]; Arrays.fill(parent, -1);
//          PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a[1]));
//          pq.add(new int[]{s, 0});
//          List<Aresta> mst = new ArrayList<>();
//          while (!pq.isEmpty()) {
//              int u = pq.poll()[0];
//              if (inMST[u]) continue;
//              inMST[u] = true;
//              if (parent[u] != -1) mst.add(new Aresta(parent[u], u, key[u]));
//              for (Aresta a : adj[u]) {
//                  int v = a.destino;
//                  if (!inMST[v] && a.peso < key[v]) {
//                      key[v] = a.peso;
//                      parent[v] = u;
//                      pq.add(new int[]{v, key[v]});
//                  }
//              }
//          }
//          return mst;
//      }
 
//      /* ================================================================ */
//      /* 6.  MAX‑HEAP & HEAPSORT                                           */
//      /* ================================================================ */
 
//      static class MaxHeap {
//          int[] A;
//          int   heapSize;
 
//          MaxHeap(int[] arr) { A = arr; build(); }
 
//          int parent(int i) { return (i - 1) / 2; }
//          int left  (int i) { return 2 * i + 1; }
//          int right (int i) { return 2 * i + 2; }
 
//          /** MAX‑HEAPIFY – versão verbosa. */
//          void maxHeapify(int i) {
//              int l = left(i), r = right(i), largest = i;
//              if (l < heapSize && A[l] > A[largest]) largest = l;
//              if (r < heapSize && A[r] > A[largest]) largest = r;
 
//              if (largest != i) {          // filho maior que pai ⇒ troca
//                  swap(i, largest);
//                  maxHeapify(largest);     // recursão na sub‑árvore
//              }
//          }
 
//          /** BUILD‑MAX‑HEAP – O(n). */
//          void build() {
//              heapSize = A.length;
//              for (int i = heapSize / 2 - 1; i >= 0; i--) {
//                  maxHeapify(i);
//              }
//          }
 
//          int extractMax() {
//              if (heapSize < 1) throw new RuntimeException("heap underflow");
//              int max = A[0];
//              A[0] = A[heapSize - 1];
//              heapSize--;
//              maxHeapify(0);
//              return max;
//          }
 
//          void increaseKey(int i, int key) {
//              if (key < A[i]) throw new IllegalArgumentException("novo < atual");
//              A[i] = key;
//              while (i > 0 && A[parent(i)] < A[i]) {
//                  swap(i, parent(i));
//                  i = parent(i);
//              }
//          }
 
//          void insert(int key) {
//              heapSize++;
//              if (heapSize > A.length) A = Arrays.copyOf(A, heapSize);
//              A[heapSize - 1] = Integer.MIN_VALUE;
//              increaseKey(heapSize - 1, key);
//          }
 
//          void swap(int i, int j) { int t = A[i]; A[i] = A[j]; A[j] = t; }
//      }
 
//      /** HeapSort clássico – usa Max‑Heap. */
//      static void heapSort(int[] A) {
//          MaxHeap h = new MaxHeap(A);
//          for (int i = h.A.length - 1; i > 0; i--) {
//              h.swap(0, i);
//              h.heapSize--;
//              h.maxHeapify(0);
//          }
//      }
 
//      /* ================================================================ */
//      /* 7.  MENU INTERATIVO                                               */
//      /* ================================================================ */
 
//      static final Scanner SC = new Scanner(System.in);
 
//      public static void main(String[] args) {
//          while (true) {
//              System.out.println("\n=== DEMONSTRADOR ===");
//              System.out.println("1‑DFS/BFS  2‑Dijkstra  3‑Bellman  4‑Kruskal  5‑Prim  6‑Heap  0‑Sair");
//              int op = SC.nextInt();
//              if (op == 0) break;
//              switch (op) {
//                  case 1 -> demoBusca();
//                  case 2 -> demoDijkstra();
//                  case 3 -> demoBellman();
//                  case 4 -> demoKruskal();
//                  case 5 -> demoPrim();
//                  case 6 -> demoHeap();
//                  default -> System.out.println("Opção inválida");
//              }
//          }
//          SC.close();
//      }
 
//      /* ------------------ Demos rápidas -------------------------------- */
 
//      static Grafo demoGraph() {
//          Grafo g = new Grafo(5);
//          int[][] e = {{0,1,10},{0,3,5},{1,2,1},{1,3,2},{2,4,4},{3,1,3},{3,2,9},{3,4,2},{4,2,6}};
//          for (int[] a : e) g.add(a[0], a[1], a[2]);
//          return g;
//      }
 
//      static void demoBusca() {
//          Grafo g = demoGraph();
//          System.out.println("DFS: " + dfs(g, 0));
//          int[] pred = new int[g.V];
//          System.out.println("BFS dist: " + Arrays.toString(bfs(g, 0, pred)) + " pred: " + Arrays.toString(pred));
//      }
 
//      static void demoDijkstra() {
//          Grafo g = demoGraph();
//          int[] d = new int[g.V], p = new int[g.V];
//          dijkstra(g, 0, d, p);
//          System.out.println("Dijkstra dist: " + Arrays.toString(d));
//      }
 
//      static void demoBellman() {
//          Grafo g = demoGraph();
//          int[] d = new int[g.V], p = new int[g.V];
//          System.out.println("Bellman sucesso?: " + bellmanFord(g, 0, d, p) + " dist: " + Arrays.toString(d));
//      }
 
//      static void demoKruskal() {
//          List<Aresta> ar = List.of(
//              new Aresta(0,1,10), new Aresta(0,3,5), new Aresta(0,2,6),
//              new Aresta(1,3,15), new Aresta(2,3,4)
//          );
//          System.out.println("MST Kruskal: " + kruskal(4, new ArrayList<>(ar)));
//      }
 
//      static void demoPrim() {
//          int V = 4;
//          @SuppressWarnings("unchecked") List<Aresta>[] adj = new List[V];
//          for (int i = 0; i < V; i++) adj[i] = new ArrayList<>();
//          int[][] e = {{0,1,10},{0,2,6},{0,3,5},{1,3,15},{2,3,4}};
//          for (int[] a : e) {
//              adj[a[0]].add(new Aresta(a[0], a[1], a[2]));
//              adj[a[1]].add(new Aresta(a[1], a[0], a[2]));
//          }
//          System.out.println("MST Prim: " + prim(V, adj, 0));
//      }
 
//      static void demoHeap() {
//          int[] arr = {4,1,3,2,16,9,10,14,8,7};
//          MaxHeap h = new MaxHeap(arr);
//          System.out.println("Max extraído: " + h.extractMax());
//          h.insert(20);
//          System.out.println("Novo max: " + h.extractMax());
//          int[] arr2 = {5,3,17,10,84,19,6,22,9};
//          heapSort(arr2);
//          System.out.println("HeapSort: " + Arrays.toString(arr2));
//      }
//  }
 

/**
 * Demonstrador COMPLETO – Algoritmos de Grafos + Max‑Heap (versão VERBOSA)
 * Cada algoritmo imprime o passo‑a‑passo detalhado no console.
 *
 * ▸ Representações  : Lista de Adjacência / Função de Incidência
 * ▸ Buscas          : DFS / BFS (logs de pilha e fila)
 * ▸ Caminhos Mínimos: Dijkstra (logs de relaxamento) & Bellman‑Ford (logs por iteração)
 * ▸ MST             : Kruskal & Prim (decisões de aresta)
 * ▸ Estruturas      : Max‑Heap (build, heapify, insert, extract‑max, ↑key) & HeapSort
 *
 * Compile  ➜  javac DemonstradorGrafosEHeapCompleto.java
 * Execute  ➜  java  DemonstradorGrafosEHeapCompleto
 */

 import java.util.*;

 public class DemonstradorGrafosEHeapCompleto {
 
     /* ================================================================ */
     /* 1. Estruturas básicas                                             */
     /* ================================================================ */
 
     /** Aresta genérica (ponderada ou identificada). */
     static class Aresta implements Comparable<Aresta> {
         int origem, destino, peso;
         String id;                             // opcional – usado na função de incidência
 
         Aresta(int o, int d, int w) { origem = o; destino = d; peso = w; }
         Aresta(String id, int o, int d) { this.id = id; origem = o; destino = d; }
 
         @Override public int compareTo(Aresta a) { return Integer.compare(peso, a.peso); }
         @Override public String toString()       { return id != null ? id + ":(" + origem + "‑" + destino + ")"
                                                                     : "(" + origem + "‑" + destino + ",w:" + peso + ")"; }
     }
 
     /** Union‑Find (Disjoint‑Set) com Path‑Compression + Union‑by‑Rank. */
     static class UnionFind {
         int[] pai, rank;
         UnionFind(int n) {
             pai = new int[n];
             rank = new int[n];
             for (int i = 0; i < n; i++) pai[i] = i;
         }
         int find(int x) { return pai[x] == x ? x : (pai[x] = find(pai[x])); }
         boolean union(int a, int b) {
             int ra = find(a), rb = find(b);
             if (ra == rb) return false;
             if (rank[ra] < rank[rb])       pai[ra] = rb;
             else if (rank[ra] > rank[rb])  pai[rb] = ra;
             else { pai[rb] = ra; rank[ra]++; }
             return true;
         }
     }
 
     /* ================================================================ */
     /* 2. Representação de Grafo                                         */
     /* ================================================================ */
 
     /** Grafo direcionado e ponderado usando Lista de Adjacência. */
     static class Grafo {
         final int V;
         List<List<Aresta>> adj = new ArrayList<>();
         Grafo(int V) {
             this.V = V;
             for (int i = 0; i < V; i++) adj.add(new ArrayList<>());
         }
         void add(int o, int d, int w) { adj.get(o).add(new Aresta(o, d, w)); }
         List<Aresta> vizinhos(int u)  { return adj.get(u); }
     }
 
     /* ================================================================ */
     /* 3. Buscas: DFS & BFS                                               */
     /* ================================================================ */
 
     /** DFS iterativo (pilha explícita) – logs de pilha e visita. */
     static List<Integer> dfs(Grafo g, int origem) {
         System.out.println("→ DFS passo‑a‑passo");
         boolean[] visitado = new boolean[g.V];
         Deque<Integer> pilha = new ArrayDeque<>();
         List<Integer> ordem = new ArrayList<>();
         pilha.push(origem);
         while (!pilha.isEmpty()) {
             System.out.println("  Pilha: " + pilha);
             int u = pilha.pop();
             if (!visitado[u]) {
                 visitado[u] = true;
                 ordem.add(u);
                 System.out.println("  Visitando: " + u);
                 List<Aresta> vs = new ArrayList<>(g.vizinhos(u));
                 Collections.reverse(vs);          // empilha vizinhos ao contrário ⇒ ordem natural
                 vs.forEach(a -> { if (!visitado[a.destino]) pilha.push(a.destino); });
             }
         }
         return ordem;
     }
 
     /** BFS – distâncias (arestas) + predecessores – logs de fila. */
     static int[] bfs(Grafo g, int origem, int[] pred) {
         System.out.println("→ BFS passo‑a‑passo");
         int[] dist = new int[g.V];
         Arrays.fill(dist, -1);
         Arrays.fill(pred, -1);
         Queue<Integer> fila = new ArrayDeque<>();
         fila.add(origem);
         dist[origem] = 0;
         while (!fila.isEmpty()) {
             System.out.println("  Fila: " + fila);
             int u = fila.poll();
             for (Aresta a : g.vizinhos(u)) {
                 int v = a.destino;
                 if (dist[v] == -1) {
                     dist[v] = dist[u] + 1;
                     pred[v] = u;
                     System.out.printf("  Relaxa %d→%d  dist=%d%n", u, v, dist[v]);
                     fila.add(v);
                 }
             }
         }
         return dist;
     }
 
     /* ================================================================ */
     /* 4. Caminhos Mínimos                                                */
     /* ================================================================ */
 
     static final int INF = Integer.MAX_VALUE / 4;
 
     /** Relaxamento genérico – usado por Dijkstra e Bellman‑Ford. */
     static boolean relax(int u, int v, int w, int[] d, int[] p) {
         if (d[u] != INF && d[v] > d[u] + w) {
             System.out.printf("    Relaxa %d→%d   %d→%d%n", u, v, d[v] == INF ? 999 : d[v], d[u] + w);
             d[v] = d[u] + w;
             p[v] = u;
             return true;
         }
         return false;
     }
 
     /** Dijkstra – versão O(V²) com logs. */
     static void dijkstra(Grafo g, int s, int[] d, int[] p) {
         System.out.println("→ Dijkstra");
         Arrays.fill(d, INF);
         Arrays.fill(p, -1);
         boolean[] vis = new boolean[g.V];
         d[s] = 0;
         for (int i = 0; i < g.V; i++) {
             int u = -1, best = INF;
             for (int v = 0; v < g.V; v++) if (!vis[v] && d[v] < best) { best = d[v]; u = v; }
             if (u == -1) break;
             vis[u] = true;
             System.out.println("  Extrai: " + u + " d=" + d[u]);
             for (Aresta a : g.vizinhos(u)) relax(u, a.destino, a.peso, d, p);
         }
         System.out.println("  dist final: " + Arrays.toString(d));
     }
 
     /** Bellman‑Ford – O(V·E) + detecção de ciclo negativo (logs). */
     static boolean bellmanFord(Grafo g, int s, int[] d, int[] p) {
         System.out.println("→ Bellman‑Ford");
         Arrays.fill(d, INF);
         Arrays.fill(p, -1);
         d[s] = 0;
         for (int i = 1; i < g.V; i++) {
             System.out.println(" Iteração " + i);
             for (int u = 0; u < g.V; u++)
                 for (Aresta a : g.vizinhos(u))
                     relax(u, a.destino, a.peso, d, p);
         }
         for (int u = 0; u < g.V; u++)
             for (Aresta a : g.vizinhos(u))
                 if (d[a.destino] > d[u] + a.peso) {
                     System.out.println("  *** ciclo negativo detectado");
                     return false;
                 }
         System.out.println(" dist final: " + Arrays.toString(d));
         return true;
     }
 
     /* ================================================================ */
     /* 5. Árvores Geradoras Mínimas                                      */
     /* ================================================================ */
 
     /** Kruskal – logs de decisão de aresta. */
     static List<Aresta> kruskal(int V, List<Aresta> arestas) {
         System.out.println("→ Kruskal");
         Collections.sort(arestas);
         UnionFind uf = new UnionFind(V);
         List<Aresta> mst = new ArrayList<>();
         for (Aresta a : arestas) {
             System.out.print("  Considera " + a);
             if (uf.union(a.origem, a.destino)) {
                 mst.add(a);
                 System.out.println("  ✔ entra");
             } else {
                 System.out.println("  ✖ ciclo");
             }
             if (mst.size() == V - 1) break;
         }
         System.out.println(" MST = " + mst);
         return mst;
     }
 
     /** Prim – PQ logs de atualização de chave. */
     static List<Aresta> prim(int V, List<Aresta>[] adj, int s) {
         System.out.println("→ Prim");
         boolean[] inMST = new boolean[V];
         int[] key = new int[V];
         int[] parent = new int[V];
         Arrays.fill(key, INF);
         Arrays.fill(parent, -1);
         key[s] = 0;
         PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a[1]));
         pq.add(new int[]{s, 0});
         List<Aresta> mst = new ArrayList<>();
         while (!pq.isEmpty()) {
             int u = pq.poll()[0];
             if (inMST[u]) continue;
             inMST[u] = true;
             System.out.println("  Adiciona vértice " + u);
             if (parent[u] != -1) mst.add(new Aresta(parent[u], u, key[u]));
             for (Aresta a : adj[u]) {
                 int v = a.destino;
                 if (!inMST[v] && a.peso < key[v]) {
                     key[v] = a.peso;
                     parent[v] = u;
                     pq.add(new int[]{v, key[v]});
                     System.out.printf("    Aktualiza chave %d (pai=%d, w=%d)%n", v, u, key[v]);
                 }
             }
         }
         System.out.println(" MST = " + mst);
         return mst;
     }
 
     /* ================================================================ */
     /* 6. Max‑Heap & HeapSort                                            */
     /* ================================================================ */
 
     static class MaxHeap {
         int[] A;
         int   tamanho;
         MaxHeap(int[] arr) { A = arr; buildMaxHeap(); }
 
         int pai(int i)   { return (i - 1) / 2; }
         int esq(int i)   { return 2 * i + 1; }
         int dir(int i)   { return 2 * i + 2; }
 
         void swap(int i, int j) { int t = A[i]; A[i] = A[j]; A[j] = t; }
 
         void maxHeapify(int i) {
             int l = esq(i), r = dir(i), maior = i;
             if (l < tamanho && A[l] > A[maior]) maior = l;
             if (r < tamanho && A[r] > A[maior]) maior = r;
             if (maior != i) {
                 System.out.printf("  swap %d↔%d%n", A[i], A[maior]);
                 swap(i, maior);
                 maxHeapify(maior);
             }
         }
 
         void buildMaxHeap() {
             tamanho = A.length;
             for (int i = tamanho / 2 - 1; i >= 0; i--) maxHeapify(i);
         }
 
         int extractMax() {
             int max = A[0];
             A[0] = A[tamanho - 1];
             tamanho--;
             maxHeapify(0);
             System.out.println("  extract=" + max + "   heap=" + Arrays.toString(Arrays.copyOf(A, tamanho)));
             return max;
         }
 
         void increaseKey(int i, int novo) {
             A[i] = novo;
             while (i > 0 && A[pai(i)] < A[i]) {
                 swap(i, pai(i));
                 i = pai(i);
             }
         }
 
         void insert(int k) {
             tamanho++;
             if (tamanho > A.length) A = Arrays.copyOf(A, tamanho);
             A[tamanho - 1] = -INF;
             increaseKey(tamanho - 1, k);
         }
     }
 
     /** HeapSort in‑place (O(n log n)). */
     static void heapSort(int[] A) {
         MaxHeap h = new MaxHeap(A);
         for (int i = h.tamanho - 1; i > 0; i--) {
             h.swap(0, i);
             h.tamanho--;
             h.maxHeapify(0);
         }
     }
 
     /* ================================================================ */
     /* 7. Demos rápidas (menu)                                           */
     /* ================================================================ */
 
     static Grafo demoGraph() {
         Grafo g = new Grafo(5);
         int[][] e = { {0,1,10},{0,3,5},{1,2,1},{1,3,2},{2,4,4},{3,1,3},{3,2,9},{3,4,2},{4,2,6} };
         for (int[] a : e) g.add(a[0], a[1], a[2]);
         return g;
     }
 
     static void demoBusca() {
         int[] pred = new int[5];
         System.out.println("DFS Resultado=" + dfs(demoGraph(), 0));
         System.out.println("BFS dist=" + Arrays.toString(bfs(demoGraph(), 0, pred)) + " pred=" + Arrays.toString(pred));
     }
 
     static void demoDijkstra() {
         int[] d = new int[5], p = new int[5];
         dijkstra(demoGraph(), 0, d, p);
     }
 
     static void demoBellman() {
         int[] d = new int[5], p = new int[5];
         bellmanFord(demoGraph(), 0, d, p);
     }
 
     static void demoKruskal() {
         List<Aresta> ar = List.of(
             new Aresta(0,1,10), new Aresta(0,3,5), new Aresta(0,2,6),
             new Aresta(1,3,15), new Aresta(2,3,4)
         );
         kruskal(4, new ArrayList<>(ar));
     }
 
     static void demoPrim() {
         int V = 4;
         @SuppressWarnings("unchecked") List<Aresta>[] adj = new List[V];
         for (int i = 0; i < V; i++) adj[i] = new ArrayList<>();
         int[][] e = { {0,1,10},{0,2,6},{0,3,5},{1,3,15},{2,3,4} };
         for (int[] a : e) {
             adj[a[0]].add(new Aresta(a[0], a[1], a[2]));
             adj[a[1]].add(new Aresta(a[1], a[0], a[2]));
         }
         prim(V, adj, 0);
     }
 
     static void demoHeap() {
         int[] arr = {4,1,3,2,16,9,10,14,8,7};
         MaxHeap h = new MaxHeap(arr);
         h.extractMax();
         h.insert(20);
         h.extractMax();
         int[] arr2 = {5,3,17,10,84,19,6,22,9};
         heapSort(arr2);
         System.out.println("HeapSort=" + Arrays.toString(arr2));
     }
 
     public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
         while (true) {
             System.out.println("\n1 DFS/BFS  2 Dijkstra  3 Bellman  4 Kruskal  5 Prim  6 Heap  0 Sair");
             switch (sc.nextInt()) {
                 case 0 -> System.exit(0);
                 case 1 -> demoBusca();
                 case 2 -> demoDijkstra();
                 case 3 -> demoBellman();
                    case 4 -> demoKruskal();
                    case 5 -> demoPrim();
                    case 6 -> demoHeap();
                 default -> {
                    sc.close();
                    System.out.println("Opção inválida");
                    return;
                 }
                }
            }
        }
    }

 