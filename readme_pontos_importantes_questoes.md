# Guia de Bolso – Algoritmos de Grafos, Heaps e Análise de Complexidade

*Baseado no estilo de questões da sua prova*

---

## 1. Notação de Complexidade

| Símbolo     | Significado                                                        | Como seu professor cobra                                    |
| ----------- | ------------------------------------------------------------------ | ----------------------------------------------------------- |
| **O(g(n))** | Limite **superior** assintótico ("*no pior caso não passa disso*") | “O programa é *O(n²)*? Mesmo que às vezes rode em *O(n)*?”  |
| **Ω(g(n))** | Limite **inferior** assintótico ("*sempre pelo menos isso*")       | “O gráfico mostra que  *f(n)=Ω(g(n))* mesmo para *n < n₀*?” |
| **Θ(g(n))** | Limite **apertado** (mesmo ordem sup. e inf.)                      | “Se *f∈Θ(g)* então *f∈Ω(g)*? (Sim!)”                        |

> **Dica‑prova:** quando ele coloca “mesmo quando 0 ≤ n < n₀”, a resposta é “não necessariamente” porque a definição de Ômega só exige a relação **a partir** de *n₀*.

---

## 2. Algoritmos & Pontos‑chave

### 2.1 DFS (Depth‑First Search)

* **Estrutura:** Pilha (recursão).
* **Complexidade:** `O(V+E)`   sempre.
* **Usos clássicos:** detectar ciclos, ordenação topológica (DAG), componentes conexas.
* **Pegadinha:** não garante caminho mais curto.

### 2.2 BFS (Breadth‑First Search)

* **Estrutura:** Fila.
* **Complexidade:** `O(V+E)`.
* **Propriedade:** primeiro a sair da fila tem a menor distância (nº de arestas).
* **Só vale para:** grafos **não ponderados** ou pesos iguais.

### 2.3 Dijkstra

* **Pré‑requisito:** pesos **≥ 0**.
* **Estrutura de apoio:** Fila de Prioridade (Heap).
* **Complexidade:**

  * `O((V+E) log V)` com heap
  * `O(V²)` versão simplificada (a do seu código‑prova).
* **Invariante:** cada vértice extraído da PQ tem distância final.

### 2.4 Bellman‑Ford

* **Aceita pesos negativos** e detecta ciclos negativos.
* **Relaxamento:** todas as arestas **|V|‑1** vezes.
* **Complexidade:** `O(V·E)`
* **Check final:** um relaxamento extra que ainda melhora ⇒ ciclo negativo.

### 2.5 Kruskal (MST)

* **Ideia:** escolher arestas de **menor peso** sem formar ciclo.
* **Estrutura:** **Union‑Find**.
* **Complexidade:** `O(E log E)` (ordenação).
* **Pegadinha:** funciona em grafo **não direcionado**.

### 2.6 Prim (MST)

* **Ideia:** crescer uma árvore a partir de um vértice.
* **Estrutura:** PQ que guarda aresta de menor `key`.
* **Complexidade:** `O(E log V)`.
* **Equivalente a:** Dijkstra com peso=chave e sem relaxamento de vértices já na árvore.

### 2.7 Max‑Heap & HeapSort

* **Max‑Heap:** pai ≥ filhos.
* **MaxHeapify:** `O(log n)`; move um item para baixo.
* **Build‑Max‑Heap:** `O(n)` (**pegadinha** do CLRS).
* **HeapSort:** troca raiz com último e aplica heapify – `O(n log n)` in‑place.

---

## 3. Cola rápida de complexidades

| Algoritmo        | Melhor       | Pior     | Observação crítica   |
| ---------------- | ------------ | -------- | -------------------- |
| **DFS/BFS**      | `Θ(V+E)`     | `Θ(V+E)` | peso irrelevante     |
| **Dijkstra**     | `Θ(E log V)` | idem     | só peso não‑negativo |
| **Bellman‑Ford** | `Θ(V·E)`     | idem     | detecta ciclo neg.   |
| **Kruskal**      | `Θ(E log E)` | idem     | usa Union‑Find       |
| **Prim (heap)**  | `Θ(E log V)` | idem     | —                    |
| **HeapSort**     | `Θ(n log n)` | idem     | estável? **Não**     |

---

### 3.1 Detalhes sobre quando NAO se deve usar os algoritmos

| Algoritmo        | Quando NÃO usar?                                      |
| ---------------- | ----------------------------------------------------- |
| **DFS/BFS**      | grafos ponderados (não garante caminho mínimo)        |
| **Dijkstra**     | pesos negativos (não garante menor caminho)           |
| **Bellman-Ford** | se não há pesos negativos (Dijkstra é mais eficiente) |
| **Kruskal**      | se o grafo é direcionado (só funciona em não direcionados) |
| **Prim**         | se o grafo é desconexo (só funciona em conexos)       |
| **HeapSort**     | se precisa de estabilidade (troca não preserva ordem) |

---

## 4. Exercícios Estilo‑Prova

### 4.1 Complexidade de Código

```java
for (int i = 0; i < n; i++)
  for (int j = i+1; j < n; j++)
    if (A[i] < A[j]) cont++;
```

I O programa é `O(n²)`.
II A linha 2 tem complexidade `Θ(n)`.
III Há um laço aninhado ⇒ todo programa com laço aninhado é `Θ(n²)`.

**Alternativas**
a) I   b) I e II   c) II e III   d) III

> **Gabarito:** b) III é falsa (“não necessariamente”).

---

### 4.2 Gráfico de Θ, O, Ω

(O mesmo gráfico colorido da prova)
I Mostra `f ∈ Ω(g)`.
II Mostra `f ∈ Θ(g)`.
III Mostra `f ∈ O(g)`.

**Corretação esperada:** II e III.

*Comentário:* sempre que f está entre `c₁·g` e `c₂·g` para *n ≥ n₀* ⇒ f ∈ Θ(g) ⇒ também em O(g) e Ω(g).

---

### 4.3 Grafo: Matriz vs Lista vs Φ(e)

Dado `V(G)={v1..v5}` e φ(e₁)=…
I O grafo é conexo.
II É bipartido.
III Possui ciclo.

**Resposta correta:** III (motivo: existe caminho fechado `v1‑v2‑v3‑v1`).

---

### 4.4 Árvore ou Não?

Desenho com 3 vértices e 2 arestas: **árvore**.  Adicionou 3ª aresta e formou ciclo: deixa de ser árvore.
Pergunta típica: “Minimizar nº de arestas faz sentido? Não, pois árvore com V vértices **já** tem V‑1 arestas.”

---

### 4.5 Dijkstra vs Bellman

1. Em grafo com aresta de peso –2 o professor pergunta: “O algoritmo abaixo (Dijkstra) funciona?”  → **Não necessariamente**.
2. Coloca código Bellman e pede: “Após |V|‑1 iterações, o próximo relaxamento ainda melhora: o que conclui?”  → **Existe ciclo negativo acessível**.

---

### 4.6 Heap

Após executar `buildMaxHeap([4,1,3,2,16,9])`, qual a saída do primeiro `extractMax()`? Liste o estado do array.
*(use o demo do seu código para confirmar)*

---

## 5. Lista Relâmpago de Verdadeiro/Falso

| # | Afirmativa                                                     | V/F   | Justificativa curta           |   |                                |       |           |
| - | -------------------------------------------------------------- | ----- | ----------------------------- | - | ------------------------------ | ----- | --------- |
| 1 | Todo grafo com                                                 | E     |  =                            | V |  – 1 e **sem ciclo** é árvore. | **V** | definição |
| 2 | Se existe caminho s→v de custo negativo então Dijkstra falha.  | **V** |                               |   |                                |       |           |
| 3 | HeapSort é estável.                                            | **F** | troca não preserva ordem      |   |                                |       |           |
| 4 | Em BFS, primeira vez que destino sai da fila é caminho mínimo. | **V** |                               |   |                                |       |           |
| 5 | Kruskal precisa do grafo ser conexo.                           | **F** | devolve floresta se desconexo |   |                                |       |           |

---

## 6. Como usar este guia durante a prova

1. **Olhe a tabela**                               → lembra complexidades.
2. **Siga o fluxograma**:

   * Peso negativo? → Bellman
   * MST?           → Kruskal/Prim
   * Busca caminho não ponderado? → BFS
3. **Para V/F múltipla**: marque “não necessariamente” quando a afirmativa é muito absoluta.
