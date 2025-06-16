
# Gabarito e Dicas – Prova de Análise de Algoritmos

---

## Questão 1
**Enunciado resumido:** Propriedades de Θ(g(n)); afirmações I, II e III.  
**Resposta:** I e II  
**Explicação detalhada:**  
- I: Se c1 = c2 e existem constantes adequadas, f(n) ∈ Θ(g(n)) por definição de Theta.  
- II: Qualquer função em Θ(g(n)) pertence também a Ω(g(n)), pois Θ implica both O e Ω.  
- III: Uma função constante, por definição, não cresce em proporção a g(n) (exceto se g(n) também for constante), mas não pertence ao conjunto para funções crescentes genéricas.  
**Pegadinhas:**  
- Confundir a relação direta de Θ com Ω e O.  
- Pensar que constantes sempre pertencem a Θ de qualquer g(n).  
**Dicas:**  
- Decore formalmente definições de O, Ω e Θ.  
- Sempre verifique as condições de existência de constantes c1, c2 e n0.

---

## Questão 2
**Enunciado resumido:** Verdadeiro ou falso: um algoritmo com tempo O(n²) pode em certos casos rodar em tempo linear, dependendo da instância.  
**Resposta:** Verdadeiro  
**Explicação detalhada:**  
- O(n²) define o limite superior no pior caso, não impede casos melhores.  
- Por exemplo, um algoritmo de ordenação que verifica se já está ordenado pode parar em O(n).  
**Pegadinhas:**  
- Associar O(n²) a tempo exato em todas instâncias.  
**Dicas:**  
- Diferencie sempre pior caso (O), melhor caso (Ω) e caso médio (Θ).

---

## Questão 3
**Enunciado resumido:** f(n)=5n²+3n+7, g(n)=n²; afirmações I: f∈O(g), II: f∈Ω(n log n), III: f∈Θ(n³).  
**Resposta:** I e II  
**Explicação detalhada:**  
- I: 5n²+3n+7 ∈ O(n²) pois coeficiente constante.  
- II: n² domina n log n para n grandes ⇒ f ∈ Ω(n log n).  
- III: f não cresce como n³, cresce menos; portanto não ∈ Θ(n³).  
**Pegadinhas:**  
- Confundir dominação de termo principal.  
**Dicas:**  
- Identifique o termo dominante da função.

---

## Questão 4
**Enunciado resumido:** Java – seleção de tarefas; afirmações sobre maximizar tempo, número de tarefas e seleção baseada em fim%2=0.  
**Resposta:** II  
**Explicação detalhada:**  
- O algoritmo ordena por fim crescente e seleciona tarefas cujo início ≥ fimÚltima, maximizando número de tarefas, não tempo.  
**Pegadinhas:**  
- Pensar que o algoritmo de scheduling por fim máximo otimiza soma de durações.  
**Dicas:**  
- Lembre-se do problema clássico de seleção de atividades: maximizar quantidade.

---

## Questão 5
**Enunciado resumido:** Duplo loop em Java que conta pares e vizinhos ímpares; afirmações sobre complexidade quadrática.  
**Resposta:** I  
**Explicação detalhada:**  
- O loop aninhado de i de 0 a n e j de i+1 a n resulta em O(n²).  
- Segundo loop (vizinhos) é O(n), mas domina o primeiro.  
- Nem todo loop aninhado é necessariamente quadrático se os limites dependem de fatores constantes ou reduziram; mas aqui sim.  
**Pegadinhas:**  
- Generalizar que qualquer loop aninhado é sempre quadrático.  
**Dicas:**  
- Calcule soma de iterações exatamente: Σ_{i=0..n-1}(n−i−1) ≈ n(n−1)/2.

---

## Questão 6
**Enunciado resumido:** Gráfico de funções sinuosas; afirmações sobre f(n)=Ω(g(n)), f∈Θ(g(n)), f∈O(g(n)).  
**Resposta:** II e III  
**Explicação detalhada:**  
- II: Após n0, c1·g(n) ≤ f(n) ≤ c2·g(n) ⇒ f∈Θ(g).  
- III: Θ(g) implica O(g).  
- I: f não está sempre acima de c1·g(n) para 0≤n<n0.  
**Pegadinhas:**  
- Interpretar valores antes de n0.  
**Dicas:**  
- Atenção ao comportamento antes e depois de n0.

---

## Questão 7
**Enunciado resumido:** Grafo com V={v1..v5}, E com arestas e função φ; afirmações sobre conexidade, bipartido, ciclo.  
**Resposta:** III  
**Explicação detalhada:**  
- Possui ciclo (e3 conecta v3→v1 criando laço).  
- Não é conexo? Cheque se todos vértices são alcançáveis; se não, conexo é falso.  
- Bipartido: ciclo ímpar impede bipartição.  
**Pegadinhas:**  
- Não checar todas arestas para conexidade.  
**Dicas:**  
- Use DFS/BFS para verificar conexidade e ciclos.

---

## Questão 8
**Enunciado resumido:** Soma e máximo em vetor; afirmações O(n), Ω(n), Θ(1).  
**Resposta:** I e II  
**Explicação detalhada:**  
- Passa pelo vetor três vezes sequencialmente ⇒ O(n).  
- Em melhor caso ainda faz leituras de cada elemento ⇒ Ω(n).  
- Não é constante (Θ(1)) pois cresce com n.  
**Pegadinhas:**  
- Confundir número de loops com aninhamento.  
**Dicas:**  
- Contar número total de operações em função de n.

---

## Questão 9
**Enunciado resumido:** Grafo desenhado; afirmações sobre matrizes de incidência e adjacência, representação por lista, planicidade.  
**Resposta:** I e II  
**Explicação detalhada:**  
- Matrizes de incidência (|V|×|E|) e adjacência (|V|×|V|) diferem de tamanho, mas número de células varia. Cheque |V| e |E|.  
- Qualquer grafo pode ser representado por lista de adjacências.  
- Cruzamento de arestas não implica necessariamente não planicidade; planaridade é sobre representação sem cruzamentos possíveis, não sobre desenho específico.  
**Pegadinhas:**  
- Confundir desenho com planaridade.  
**Dicas:**  
- Saber definições formais de representações e planaridade.

---

## Questão 10
**Enunciado resumido:** Grafo bipartido X={x1,x2,x3}, Y={y1,y2,y3}; afirmações estrela, conexo, bipartido.  
**Resposta:** II e III  
**Explicação detalhada:**  
- Não é estrela (mais de um vértice central).  
- É conexo? Em desenho, há caminho entre todos vértices.  
- É bipartido por definição (dois conjuntos sem arestas internas).  
**Pegadinhas:**  
- Pensar que todo grafo completo bipartido K_{m,n} é estrela apenas se um conjunto tiver tamanho 1.  
**Dicas:**  
- Relembre K_{m,n} e definição de estrela.

---

## Questão 11
**Enunciado resumido:** Árvore geradora mínima em grafo quadrado com pesos {1,2}. Afirmações sobre unicidade e número de arestas.  
**Resposta:** III  
**Explicação detalhada:**  
- III: Toda árvore geradora de um grafo com 4 vértices terá exatamente 3 arestas.  
- I: Podem existir várias MST com mesmo peso total.  
- II: Árvore geradora não “possui dois vértices” – não faz sentido.  
**Pegadinhas:**  
- Concluir unicidade sem analisar pesos iguais.  
**Dicas:**  
- Conte arestas em árvore de n vértices: sempre n−1.

---

## Questão 12
**Enunciado resumido:** Construção de heap máximo (buildMaxHeap) ilustrado nas figuras (a)…(j) e array final. Afirmações.  
**Resposta:** I  
**Explicação detalhada:**  
- I: Vetor em (a) já é heap máximo.  
- II: buildMaxHeap chama maxHeapify em cada nó (não exatamente uma vez por nó).  
- III: Sequência (a→j) não é funcionamento completo de buildMaxHeap, mas sim etapas individuais de maxHeapify.  
**Pegadinhas:**  
- Confundir buildMaxHeap com HeapSort completo.  
**Dicas:**  
- Memorize fluxo: build chama maxHeapify de baixo para cima.

---

## Questão 13
**Enunciado resumido:** Kruskal e Prim podem operar em grafos com ciclos; afirmações sobre MST e ciclos.  
**Resposta:** II e III  
**Explicação detalhada:**  
- Ambos descartam arestas que formam ciclos e constroem MST mesmo com ciclos no grafo.  
- I: O caminho entre dois vértices em uma árvore MST é único, mas mesmo assim esta afirmação não está entre corretas solicitadas.  
**Pegadinhas:**  
- Distinguir “grafo com ciclos” vs “árvore resultante sem ciclos”.  
**Dicas:**  
- Saiba que MST é uma subárvore sem ciclos.

---

## Questão 14
**Enunciado resumido:** Implementação de Union-Find sem compressão de caminho. Afirmações sobre complexidade find, uso e alteração de representante.  
**Resposta:** II e III  
**Explicação detalhada:**  
- I: No melhor caso, find tem O(1) apenas se x for raiz imediatamente; mas em geral O(h) onde h é profundidade.  
- II: Estrutura UF modela coleções disjuntas.  
- III: find retorna representante, mas não altera caminho (sem path compression): o representante de x permanece o mesmo.  
**Pegadinhas:**  
- Pensar que find sempre percorre toda a altura da árvore.  
**Dicas:**  
- Diferencie UF com e sem otimizações (path compression).

---

## Questão 15
**Enunciado resumido:** Ilustração parcial (a),(b),(c) do HeapSort passo a passo; afirmações sobre execução.  
**Resposta:** III  
**Explicação detalhada:**  
- III: Em (c), vetor inteiro satisfaz heap (tudo maior que filhos).  
- I: HeapSort envolve build+extraction, não só parte mostrada.  
- II: Subárvores em (a) só são heaps se a partir de índices 4…10; não todas necessárias.  
**Pegadinhas:**  
- Confundir etapas de buildHeap e extração de elemento.  
**Dicas:**  
- Revise cada fase do HeapSort: buildMaxHeap e repetidas maxHeapify após swap.

---

## Questão 16
**Enunciado resumido:** BFS; afirmações sobre uso de pilha, fila e tipo de lista (LinkedList vs ArrayList).  
**Resposta:** II e III  
**Explicação detalhada:**  
- I: BFS usa fila (FIFO), não pilha.  
- II: Fila pode ser implementada sobre qualquer estrutura que suporte FIFO, incluindo LinkedList ou ArrayDeque.  
- III: Uso de LinkedList é só uma implementação; troca por ArrayList não mantém FIFO de forma adequada sem lógica adicional.  
**Pegadinhas:**  
- Trocar LinkedList por ArrayList não garante FIFO.  
**Dicas:**  
- Entenda invariantes de fila: enqueue e dequeue.

---

## Questão 17
**Enunciado resumido:** Bellman-Ford em Java; afirmações sobre retorno booleano, relax, predessor.  
**Resposta:** I e II  
**Explicação detalhada:**  
- I: Método retorna false se detecta ciclo negativo; true caso contrário.  
- II: Relax é O(1): poucas operações aritméticas e atribuições.  
- III: Relax só atualiza dist e pred se condição d(u)+w(u,v)<d(v) for satisfeita.  
**Pegadinhas:**  
- Pensar que relax sempre atualiza dist.  
**Dicas:**  
- Revise condição dentro de relax: atualização condicional.

---

## Questão 18
**Enunciado resumido:** Dijkstra vs Bellman-Ford e planejamento de aplicações.  
**Resposta:** III  
**Explicação detalhada:**  
- I: Dijkstra falha se existir aresta negativa.  
- II: Em grafos com ciclo negativo, não faz sentido definir caminho mínimo.  
- III: É possível reexecutar Dijkstra para diferentes fontes.  
**Pegadinhas:**  
- Confundir extensão de Dijkstra para múltiplas fontes sem repetição.  
**Dicas:**  
- Dijkstra é single-source, mas repetível para todos vértices.

---

## Questão 19
**Enunciado resumido:** maxHeapify; afirmações sobre transformar vetor em heap, ordenação e condição de chamada recursiva.  
**Resposta:** III  
**Explicação detalhada:**  
- III: Linha 13 (chamada recursiva) só executa se i tiver filho direito e troca seja necessária.  
- I e II estão incorretas pois: maxHeapify só ajusta subárvore local, não garante heap global nem ordenação.  
**Pegadinhas:**  
- Pensar que maxHeapify é suficiente para ordenar.  
**Dicas:**  
- Diferencie maxHeapify isolado e HeapSort completo.

---

## Questão 20
**Enunciado resumido:** Arestas de Kruskal, grafo induzido em Prim e uso de UF em grafos não conexos.  
**Resposta:** I e II  
**Explicação detalhada:**  
- I: Kruskal escolhe aresta de menor peso global (guloso).  
- II: Conjunto de arestas em Prim forma subgrafo sempre conexo (é árvore em expansão).  
- III: UF pode ser usado em grafos não conexos para rastrear componentes; não exige conexidade.  
**Pegadinhas:**  
- Acreditar que UF vale só para MST conexas.  
**Dicas:**  
- Revise propriedades de algorítmicos guloso.

---
