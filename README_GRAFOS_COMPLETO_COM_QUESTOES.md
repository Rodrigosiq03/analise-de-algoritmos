
# 🌐 Grafos – Guia Completo para Provas (MAUÁ 2025)

Este é um guia 100% focado em **prova de Análise de Algoritmos com consulta**.

Aqui você vai encontrar:

- Conceitos base de grafos
- Diferenças entre tipos de grafos e árvores
- Como funcionam os algoritmos BFS, DFS, Dijkstra, Bellman-Ford e Kruskal
- Códigos Java comentados
- **Simulado com questões estilo prova** com alternativas e gabarito explicado

---

## 📌 Conceitos Fundamentais

| Termo        | Significado                                                                 |
|--------------|-------------------------------------------------------------------------------|
| **Grafo**    | Estrutura com vértices (nós) e arestas (ligações entre os nós)               |
| **Vértice**  | Um ponto/nó em um grafo                                                       |
| **Aresta**   | Uma ligação entre dois vértices                                               |
| **Grafo direcionado** | Arestas têm direção (ex: A → B)                                     |
| **Grafo não direcionado** | Arestas ligam dois vértices em qualquer direção                 |
| **Ciclo**    | Caminho que começa e termina no mesmo vértice                                 |
| **Caminho**  | Sequência de vértices conectados por arestas                                  |
| **Árvore**   | Grafo sem ciclos, conectado, com (V − 1) arestas                              |
| **Árvore Geradora Mínima** | Conecta todos os vértices com menor custo total, sem ciclos   |

---

## 🔍 BFS – Busca em Largura

- Vai visitando **nível por nível**
- Usa **fila (Queue)**
- Ideal para descobrir **menor caminho em grafos não ponderados**

**Complexidade:** O(V + E)

---

## 🔍 DFS – Busca em Profundidade

- Vai fundo até não poder mais, depois volta
- Usa **pilha (Stack)** ou **recursão**
- Útil para detectar ciclos, componentes conexas, ordenação topológica

**Complexidade:** O(V + E)

---

## 🚀 Dijkstra

- Encontra menor caminho a partir de um vértice
- **Não funciona com pesos negativos**
- Escolhe sempre o próximo vértice com menor distância estimada
- Relaxa os vizinhos

**Complexidade:** O(V²) na versão simples, O((V + E) log V) com heap

---

## 🧪 Bellman-Ford

- Também encontra o menor caminho a partir de um vértice
- **Aceita pesos negativos**
- Pode detectar ciclos negativos
- Relaxa todas as arestas (V − 1) vezes

**Complexidade:** O(V × E)

---

## 🌲 Kruskal – Árvore Geradora Mínima

- Conecta todos os vértices com menor custo sem formar ciclos
- Ordena arestas por peso
- Adiciona a menor que **não forma ciclo**
- Usa **Union-Find**

**Complexidade:** O(E log E)

---

## 💻 Códigos Java: Ver exemplos no README anterior (Dijkstra e Bellman-Ford)

---

# 🧪 Simulado Estilo Prova

## ❓ Questão 1

Considere um grafo com 4 vértices e as seguintes arestas:

- A–B (peso 2)
- A–C (peso -3)
- B–C (peso 4)
- C–D (peso 1)

Assinale a alternativa correta:

a) Dijkstra pode ser usado nesse grafo  
b) Bellman-Ford detectaria ciclo negativo  
c) O grafo não possui ciclo negativo  
d) Dijkstra e Bellman-Ford retornam o mesmo resultado  

✅ **Gabarito: a) FALSO, b) FALSO, c) VERDADEIRO, d) FALSO**  
📘 Explicação: Dijkstra não funciona com peso negativo. Bellman-Ford pode ser usado e **detectaria** um ciclo se ele existisse, mas não há ciclo negativo aqui.

---

## ❓ Questão 2

O que define uma Árvore Geradora Mínima?

a) Conecta todos os vértices formando ciclos com menor custo  
b) Conecta todos os vértices com menor custo e sem ciclos  
c) Conecta apenas vértices adjacentes  
d) Conecta todos os vértices com menor número de arestas  

✅ **Gabarito: b)**  
📘 Explicação: A MST conecta todos os vértices com menor custo total **e sem formar ciclos**.

---

## ❓ Questão 3

Sobre o algoritmo de Dijkstra:

I. É usado para encontrar o menor caminho de uma origem a todos os outros vértices  
II. Funciona com pesos negativos  
III. Pode ser otimizado usando heap binária  

Assinale:

a) Apenas I  
b) I e III  
c) I, II e III  
d) II e III  

✅ **Gabarito: b)**  
📘 Explicação: Dijkstra **não funciona com pesos negativos**.

---

## ❓ Questão 4

Considere:

```java
f(n) = 5n² + 3n + 7  
g(n) = n²
```

Assinale a opção correta:

a) f(n) ∈ O(g(n))  
b) f(n) ∈ Ω(n log n)  
c) f(n) ∈ Θ(n³)  
d) Todas as anteriores

✅ **Gabarito: a) e b)**  
📘 Explicação: f(n) é da ordem de n² → O(n²) e Ω(n log n) ao mesmo tempo. Mas **não** é Θ(n³), pois cresce mais devagar.

---

## ❓ Questão 5

Um programa com laço duplo como este:
```java
for (int i = 0; i < n; i++)
  for (int j = 0; j < n; j++)
    print(i, j);
```

Qual sua complexidade?

a) O(n)  
b) O(n log n)  
c) O(n²)  
d) O(2n)

✅ **Gabarito: c)**  
📘 Explicação: Laço aninhado = O(n × n) = O(n²)

---

Se quiser, posso gerar isso em PDF, flashcards ou exportar para simulado interativo.

