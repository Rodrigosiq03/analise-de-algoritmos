<!-- # Algoritmos de Grafos & Estruturas — Guia Passo‑a‑Passo


---

## Índice

1. Estruturas Fundamentais
2. DFS
3. BFS
4. Dijkstra
5. Bellman‑Ford
6. Kruskal
7. Prim
8. Max‑Heap & Build‑Heap
9. HeapSort
10. Tabela Rápida de “Não Usar Quando…”.

---

## 1. Estruturas Fundamentais

### 1.1 Pilha (Stack)

* **LIFO** – `push`, `pop`, `peek`.
* Usada pela DFS para lembrar vértices “à espera” de exploração.
* **Pior caso** memória =`Θ(V)` se o grafo é caminho.

### 1.2 Fila (Queue)

* **FIFO** – `enqueue`, `dequeue`.
* Mantém camadas equidistantes na BFS.
* Pior caso memória=`Θ(V)`.

### 1.3 Fila de Prioridade (Min‑Heap)

* Opera em `extract‑min`, `insert`, `decrease‑key` (`log n`).
* Necessária em Dijkstra e Prim.

---

## 2. DFS — Depth‑First Search

| Aspecto                | Detalhe                                                                                       |
| ---------------------- | --------------------------------------------------------------------------------------------- |
| **Entrada**            | Grafo qualquer (dir./não‑dir.)                                                                |
| **Estrutura**          | *Stack* explícita ou recursão implícita                                                       |
| **Passos**             | 1. *push(s)*.  2. enquanto pilha≠∅ → *pop u*.  3. se `u` inédito ⇒ marca e empilha vizinhos ↓ |
| **Ordem dos vizinhos** | Inverter antes de empilhar ⇒ garante numérica/lexicográfica                                   |
| **Saídas úteis**       | ordem de visita, tempos descoberta/fechamento, árvore DFS                                     |
| **Complexidade**       | `Θ(V+E)`; pior caso caminho ⇒ profundidade = V                                                |
| **Falha típica**       | Não retorna menor caminho; não lida com pesos                                                 |

---

## 3. BFS — Breadth‑First Search

| **Estrutura** | Fila (ArrayDeque) | **Passo a passo** | |  1 | `enqueue(s)`; `dist[s]=0` | |  2 | *while* fila≠∅ → `u = dequeue()` | |  3 | Para cada `v` em `adj[u]` não visitado: ↴ | |    3‑a | `dist[v] = dist[u]+1` | |    3‑b | `pred[v] = u` | |    3‑c | `enqueue(v)` | | **Propriedade‑chave** | 1ª vez que `v` sai da fila é menor nº de arestas s→v | | **Complexidade** | `Θ(V+E)` | | **Quando não usar** | Grafo ponderado com pesos ≠ 1 |

---

## 4. Dijkstra — Caminhos Mínimos (Pesos ≥ 0)

**Estrutura:** Min‑Heap (ou array simples).
**Pseudo‑loop:**

```text
init d[]←∞; d[s]=0; PQ ← (s,0)
while PQ ≠ ∅:
  u = extract‑min
  for (u,v,w) in adj[u]:
      if d[v] > d[u]+w:  // relaxa
           d[v] = d[u]+w
           parent[v] = u
           decrease‑key(v)
```

* **Invariante:** após extração, `d[u]` é definitivo.
* **Pior caso:** grafo completo `E=Θ(V²)` ⇒ tempo `Θ(V²)` na versão de array.
* **Não usar se:** existe **qualquer** aresta negativa — distância final pode ser superestimada.

---

## 5. Bellman‑Ford — Caminhos com Pesos Negativos

**Passos**

1. `d[s]=0`, resto ∞.
2. *Repita* `|V|-1` vezes: percorre **todas** as arestas e relaxa.
3. Iteração extra: se algum `d[v]` ainda melhora ⇒ ciclo negativo.

* **Complexidade:** `Θ(V·E)`; pior caso V≈E≈10⁴ ⇒ 10⁸ relaxamentos.
* **Use quando:** pesos negativos, necessidade de detectar ciclos.

---

## 6. Kruskal — MST por Ordenação de Arestas

1. Ordena arestas crescente (sort ou heap).
2. Para cada aresta `(u,v,w)`:

   * se `find(u)≠find(v)` ⇒ `union` e põe em MST.
3. Para quando MST tem `|V|-1` arestas.

* **Estrutura vital:** Union‑Find com *path‑compression* + *union‑by‑rank* (`α(n)` amortizado).
* **Pior caso:** sort `E log E`.
* **Não serve** em grafos **dirigidos** (MST definido só p/ não‑dir.).

---

## 7. Prim — MST Crescente

1. Chaves `key[]←∞`; `key[s]=0`. PQ por chave.
2. Extrai `u` mínimo; adiciona à árvore.
3. Relaxa arestas de `u`: se `w < key[v]` ⇒ `key[v]=w` e atualiza PQ.

* **Complexidade:** `Θ(E log V)` com heap.
* **Falha:** também supõe grafo não‑dir.

---

## 8. Max‑Heap & Build‑Heap

### 8.1 `maxHeapify(i)`

1. `l=2i+1`, `r=2i+2`, `largest=i`.
2. Troca com maior filho se houver.
3. Recursão na posição trocada.
   *Pior* altura=`⌊log₂(n)⌋` comparações.

### 8.2 `buildMaxHeap`

* Inicia `heapSize=n`.
* Chama heapify em todos os nós internos (índices ⌊n/2⌋−1…0).
* \*\*Tempo agregado \*\*\`\`.

---

## 9. HeapSort

1. `buildMaxHeap`.
2. Para `i=n−1…1`: troca raiz↔`A[i]`, reduz heap, `maxHeapify(0)`.

* **Pior caso:** `Θ(n log n)`.
* *Não* estável; bom espaço (in‑place).

---

## 10. "Não usar quando…" (coluna rápida)

| Algoritmo    | Não indicado quando…          |
| ------------ | ----------------------------- |
| DFS          | precisa do caminho mais curto |
| BFS          | pesos ≠ 1                     |
| Dijkstra     | qualquer peso negativo        |
| Bellman‑Ford | grafo denso sem negativos     |
| Kruskal/Prim | grafo dirigido                |
| HeapSort     | ordenação estável exigida     |

 -->
# Algoritmos de Grafos & Estruturas — Guia Passo‑a‑Passo


> **Como usar:** cada bloco agora traz **Objetivo** (por que aplicar), **Passo‑a‑Passo**, **Retorno** (o que o algoritmo entrega), **Complexidade**, **Pior caso** e **Quando NÃO usar**.

---

## Índice

1. Estruturas Fundamentais
2. DFS
3. BFS
4. Dijkstra
5. Bellman‑Ford
6. Kruskal
7. Prim
8. Max‑Heap & Build‑Heap
9. HeapSort
10. Tabela Rápida de "Não Usar Quando…"

---

## 1. Estruturas Fundamentais


| Estrutura    | Objetivo                        | Operações chave           | Complexidade |
| ------------ | ------------------------------- | ------------------------- | ------------ |
| **Pilha**    | LIFO para explorar profundidade | push / pop                | O(1)         |
| **Fila**     | FIFO para explorar largura      | enqueue / dequeue         | O(1)         |
| **Min‑Heap** | Extrair mínimo rápido           | extract‑min, decrease‑key | O(log n)     |

---

## 2. DFS — Depth‑First Search

**Objetivo:** percorrer todos os vértices, gerar árvore DFS, detectar ciclos, ordenar topologicamente DAG.

| Aspecto             | Detalhe                                                     |
| ------------------- | ----------------------------------------------------------- |
| **Entrada**         | Grafo (dir./não‑dir.) & vértice origem                      |
| **Passo‑a‑Passo**   | 1 push(s) → loop pop/visita & empilha vizinhos              |
| **Retorno**         | lista ordem visita, tempo descoberta/fechamento, árvore DFS |
| **Complexidade**    | Θ(V+E)                                                      |
| **Pior caso**       | caminho ⇒ profundidade = V                                  |
| **Quando não usar** | precisa do caminho mais curto                               |

---

## 3. BFS — Breadth‑First Search

**Objetivo:** encontrar menor nº de arestas s→v, níveis de camadas.

| **Passo‑a‑Passo**                                       |
| ------------------------------------------------------- |
| 1 `enqueue(s)` `dist[s]=0`                              |
| 2 enquanto fila≠∅ → `u=dequeue()`                       |
| 3 relaxa vizinhos não visitados, define `pred` e `dist` |

| Item                | Valor                                                |
| ------------------- | ---------------------------------------------------- |
| **Retorno**         | vetor `dist[]`, vetor `pred[]` (caminho), árvore BFS |
| **Complexidade**    | Θ(V+E)                                               |
| **Pior caso**       | grafo completo E≈V²                                  |
| **Quando não usar** | pesos ≠ 1                                            |

---

## 4. Dijkstra — Caminhos Mínimos (Pesos ≥ 0)

**Objetivo:** custo mínimo s→v em grafos sem peso negativo.

| Item              | Valor                                                                 |
| ----------------- | --------------------------------------------------------------------- |
| **Passo‑a‑Passo** | inicializa dist & PQ → extrai mínimo → relaxa vizinhos (decrease‑key) |
| **Retorno**       | vetor `dist[]`, árvore de predecessores (caminho mínimo)              |
| **Complexidade**  | (V+E) log V com heap / V² com array                                   |
| **Pior caso**     | grafo completo + heap ruim                                            |
| **Não usar se**   | existe peso negativo                                                  |

---

## 5. Bellman‑Ford — Pesos Negativos

**Objetivo:** caminhos mínimos s→v mesmo com pesos negativos e detectar ciclo negativo.

| Item              | Valor                                         |   |                                               |
| ----------------- | --------------------------------------------- | - | --------------------------------------------- |
| **Passo‑a‑Passo** |                                               | V | ‑1 relaxamentos completos → verificação extra |
| **Retorno**       | vetor `dist[]`, `pred[]`, flag ciclo‑negativo |   |                                               |
| **Complexidade**  | Θ(V·E)                                        |   |                                               |
| **Pior caso**     | grafo denso + muitos relaxamentos             |   |                                               |
| **Não usar**      | grafo grande sem negativos (use Dijkstra)     |   |                                               |

---

## 6. Kruskal — MST (não‑dir.)

**Objetivo:** achar árvore geradora mínima com arestas em ordem crescente.

| Item              | Valor                                                 |
| ----------------- | ----------------------------------------------------- |
| **Passo‑a‑Passo** | sort arestas → iterar/union‑find → aceitar sem ciclos |
| **Retorno**       | conjunto de arestas MST, custo total                  |
| **Complexidade**  | E log E                                               |
| **Pior caso**     | sort grande + quase todos unions                      |
| **Não usar**      | grafo dirigido                                        |

---

## 7. Prim — MST Crescente

**Objetivo:** crescer MST adicionando aresta mínima que conecta conjunto‑Árvore ao resto.

| Item              | Valor                                                       |
| ----------------- | ----------------------------------------------------------- |
| **Passo‑a‑Passo** | inicia chave ∞, PQ; extrai mínimo, atualiza chaves vizinhos |
| **Retorno**       | mesma MST (parent\[] ou lista)                              |
| **Complexidade**  | E log V                                                     |
| **Pior caso**     | grafo denso (ainda E≈V²)                                    |
| **Não usar**      | grafo dirigido                                              |

---

## 8. Max‑Heap & Build‑Heap

**Objetivo:** manter elemento máximo em O(1) e operações O(log n).

| Operação        | Passo‑a‑Passo                 | Retorno                   |
| --------------- | ----------------------------- | ------------------------- |
| `maxHeapify(i)` | compara pai/filhos, desce     | heap restaura propriedade |
| `buildMaxHeap`  | chama heapify em nós internos | heap válido               |
| `extractMax`    | remove raiz, heapify          | valor máximo              |
| `increaseKey`   | sobe valor                    | heap válido               |

---

## 9. HeapSort

**Objetivo:** ordenar in‑place em n log n usando Max‑Heap.

| Etapa            | Descrição                                     |
| ---------------- | --------------------------------------------- |
| 1                | buildMaxHeap                                  |
| 2                | loop i=n‑1 → 1: troca raiz com A\[i], heapify |
| **Retorno**      | vetor ordenado crescente                      |
| **Complexidade** | n log n                                       |
| **Pior caso**    | igual ao médio                                |
| **Não usar**     | precisa de ordenação estável                  |

---

## 10. "Não Usar Quando…"  (resumo)

| Algoritmo    | Evite se…                    | Alternativa                  |
| ------------ | ---------------------------- | ---------------------------- |
| DFS          | quer menor caminho           | BFS/Dijkstra                 |
| BFS          | pesos ≠1                     | Dijkstra/Bellman             |
| Dijkstra     | peso negativo                | Bellman‑Ford                 |
| Bellman‑Ford | grafo denso sem negativos    | Dijkstra                     |
| Kruskal/Prim | grafo dirigido               | Edmonds/arborescência mínima |
| HeapSort     | ordenação estável necessária | MergeSort/Timsort            |

