## Algoritmo de Bellman-Ford


 O algoritmo de **Bellman-Ford** serve para encontrar o **menor caminho a partir de um único vértice origem** para todos os outros vértices de um grafo **mesmo quando há arestas com pesos negativos**.


![](https://i.imgur.com/oddVI1S.png)

##### Lógica do algoritmo Bellman-Ford:

 - Inicialize todas as distâncias com infinito, exceto a origem (distância 0).
 - Repita **V-1 vezes**, sendo V o número de vértices do grafo:
    - Para cada aresta (u → v) com peso w, faça o **relaxamento**:
       - Se dist[u] + w < dist[v], então atualize:
      dist[v] = dist[u] + w

  - Passo adicional (opcional): Verificar se existe ciclo negativo no grafo.
    - Verifique se após isso ainda é possível fazer relaxamentos.
    - Se sim, **há um ciclo negativo** (sinal de alerta!).

##### Semelhanças com o Dijkstra:

 - Ambos encontram o menor caminho de um vértice origem para todos os outros.
 - Ambos utilizam o conceito de relaxamento de arestas.
 - Ambos iniciam com uma distância infinita para todos os vértices, exceto a origem.

##### Diferenças com o Dijkstra:

 - Dijkstra não funciona com pesos negativos, enquanto Bellman-Ford funciona.
 - Bellman-Ford consegue detectar ciclos negativos; Dijkstra não.


###### Exemplo de ciclo negativo:

![](https://i.imgur.com/WTVOec3.png)


#### Por que ciclos negativos são um problema?

 -  Não existe menor caminho bem definido:
    - Se há um **ciclo negativo**, é possível continuar passando por ele indefinidamente e reduzir o custo total do caminho a cada volta.
    - Em outras palavras: o **menor caminho** até certo vértice pode ser indefinido (e inconsistente) ou tendendo a -∞.

 - Algoritmos como **Dijkstra** e **Bellman-Ford** falham:
   - Os algoritmos de Dijkstra e Bellman-Ford assumem que, uma vez que o menor caminho para um vértice foi encontrado, ele não precisa mais ser atualizado.
   - Com ciclos negativos, **essa suposição é quebrada**, e o algoritmo pode retornar valores incorretos ou entrar em loop.


**Exemplo**

- Se um ciclo tem peso total -3, você pode dar 1 volta e reduzir o custo em -3. Se der 100 voltas, reduz -300, o que é irrealista no ponto de vista de uma aplicação prática.

  




