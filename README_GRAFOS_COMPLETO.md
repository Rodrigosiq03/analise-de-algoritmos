
# Teoria dos Grafos — Guia Visual Essencial

> Cola rápida com definições, imagens e pegadinhas de prova.

## 1. Elementos Básicos
| Conceito | Símbolo | Definição |
|----------|---------|-----------|
| Vértice  | v       | nó |
| Aresta   | e=(u,v) | liga dois vértices |
| Grau     | deg(v)  | nº de arestas tocando v |
| Incidência | —     | aresta "incide" em vértice |

## 2. Tipos Fundamentais (visual)
(links de imagens externas para não inflar PDF)

- **Digrafo** – arestas orientadas  
  ![](https://upload.wikimedia.org/wikipedia/commons/thumb/a/a2/Directed.svg/1200px-Directed.svg.png)
- **Ponderado** – peso em cada aresta  
  ![](https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSkIfk6Lv6EXeyNhqWS5Lv8Ke_lWFpjDB6Xow&s)
- **Bipartido** – partição U / V  
  ![](https://upload.wikimedia.org/wikipedia/commons/thumb/e/e8/Simple-bipartite-graph.svg/1200px-Simple-bipartite-graph.svg.png)
- **Conexo x Desconexo**  
  ![](https://upload.wikimedia.org/wikipedia/commons/7/7c/GrafoConexo.jpg)

## 3. Conectividade
- Conexo  – caminho entre todos pares.  
- Desconexo – componentes separadas.  
- **Fortemente conexo** (digrafo) – u→v e v→u.  
- **Fracamente conexo** – vira conexo ignorando setas.

## 4. Estruturas especiais
| Nome | Condições |
|------|-----------|
| Árvore | conexo + acíclico + E=V-1 |
| Floresta | acíclico, poss. desconexo |
| Ciclo | caminho fechado |

## 5. Representações
Lista adj, Matriz adj, Matriz incidência, Função incidência φ(e).

## 6. Outras classes
Multigrafo, Pseudografo, Planar, Grafo completo, Grafo completo bipartido K_{m,n}.

## 7. Checklist pegadinhas
1. Minimizar arestas em árvore? já fixo.  
2. Bipartido ⇔ sem ciclo ímpar.  
3. Digrafo: forte ≠ fraco.
4. Conexo ⇔ caminho entre todos pares.
5. Ciclo: caminho fechado, não é árvore.

