
# 💣 README DE SOBREVIVÊNCIA – ANÁLISE DE ALGORITMOS (MAUÁ 2025)

Este arquivo é a sua **tábua de salvação** para a P2 de Análise de Algoritmos.  
Aqui você vai encontrar explicações simples, com exemplos e os códigos Java já comentados, para **entender sem esforço** conceitos como:

- Listas ligadas
- Heap e HeapSort
- Algoritmos Gulosos (Greedy)
- Grafos (BFS, DFS, Dijkstra, Bellman-Ford, Kruskal)
- Árvores geradoras mínimas
- Diferença entre grafo, árvore e ciclo
- E MUITO MAIS

---

## 🧩 ESTRUTURA DO CONTEÚDO

- [1. Listas em Java](#1-listas-em-java)
- [2. Heap e HeapSort](#2-heap-e-heapsort)
- [3. Algoritmos Gulosos](#3-algoritmos-gulosos)
- [4. Grafos e Buscas](#4-grafos-e-buscas)
- [5. Árvores Geradoras Mínimas](#5-árvores-geradoras-mínimas)
- [6. Menor Caminho: Dijkstra e Bellman-Ford](#6-menor-caminho-dijkstra-e-bellman-ford)
- [7. Cola de Prova: o que é o que](#7-cola-de-prova-o-que-é-o-que)

---

## 1. 📚 LISTAS EM JAVA

### Arquivos:
- `MeuArrayList.java` – uma implementação de lista usando array fixo
- `MinhaListaLigada.java` – lista ligada com nós encadeados
- `AnaliseMetodoAddList.java` – mede o tempo de execução dos métodos `add`

### Conceitos:
- ArrayList: acesso rápido (O(1)), inserção pode ser O(n)
- Lista ligada: inserção rápida (O(1) na cabeça), acesso é lento (O(n))

### Exemplo (trecho do código):
```java
public class MeuArrayList {
    private int[] dados = new int[100];
    private int tamanho = 0;

    public void add(int valor) {
        dados[tamanho] = valor;
        tamanho++;
    }

    public int get(int i) {
        return dados[i];
    }
}
```

### Saque o que acontece:
- Adiciona elementos sequencialmente
- Acesso direto a qualquer índice com `get(i)`
- Se passar do tamanho do array, dá ruim

---

## 2. 🔺 HEAP E HEAPSORT

### Arquivo:
- `HeapSort.java`

### Conceitos:
- Heap é uma **árvore binária** onde o pai é sempre **maior (max heap)** ou **menor (min heap)** que os filhos.
- Usado para ordenar eficientemente (HeapSort tem O(n log n)).

### Exemplo:
```java
public class HeapSort {
    public void sort(int arr[]) {
        int n = arr.length;

        // Constrói max heap
        for (int i = n / 2 - 1; i >= 0; i--)
            heapify(arr, n, i);

        // Extrai elementos da heap
        for (int i = n - 1; i >= 0; i--) {
            int temp = arr[0];
            arr[0] = arr[i];
            arr[i] = temp;

            heapify(arr, i, 0);
        }
    }

    void heapify(int arr[], int n, int i) {
        int maior = i;
        int esq = 2 * i + 1;
        int dir = 2 * i + 2;

        if (esq < n && arr[esq] > arr[maior])
            maior = esq;

        if (dir < n && arr[dir] > arr[maior])
            maior = dir;

        if (maior != i) {
            int troca = arr[i];
            arr[i] = arr[maior];
            arr[maior] = troca;

            heapify(arr, n, maior);
        }
    }
}
```

---

## 3. ⚡ ALGORITMOS GULOSOS (GREEDY)

### Arquivos:
- `MochilaFracionaria.java`
- `Item.java`
- `Tarefa.java`
- `Principal.java`

### Conceito:
Guloso = sempre escolhe **o melhor do momento**, sem olhar o todo.

### Problema clássico: Mochila Fracionária

```java
public class Item {
    int peso, valor;
    public Item(int peso, int valor) {
        this.peso = peso;
        this.valor = valor;
    }

    public double valorPorPeso() {
        return (double) valor / peso;
    }
}
```

O programa ordena os itens por valor/peso e adiciona à mochila enquanto couber.  
Se não couber inteiro, adiciona a fração restante.

---

## 4. 🌐 GRAFOS E BUSCAS

### Arquivo:
- `bfsdfs/Grafo.java`

### Conceitos:
- **BFS (Busca em Largura):** vai por camadas
- **DFS (Busca em Profundidade):** vai até o fundo primeiro
- Usado para percorrer, detectar ciclos, componentes conexas

```java
public void bfs(int inicio) {
    boolean[] visitado = new boolean[numVertices];
    Queue<Integer> fila = new LinkedList<>();

    visitado[inicio] = true;
    fila.add(inicio);

    while (!fila.isEmpty()) {
        int atual = fila.poll();
        for (int vizinho : adj[atual]) {
            if (!visitado[vizinho]) {
                visitado[vizinho] = true;
                fila.add(vizinho);
            }
        }
    }
}
```

---

## 5. 🌲 ÁRVORES GERADORAS MÍNIMAS

### Arquivo:
- `arvores_geradoras_minimas/KruskalSimples.java`

### Conceito:
Conecta todos os vértices com **menor custo** e **sem formar ciclos**.  
É uma árvore se tem **V – 1 arestas**.

### Algoritmo de Kruskal:
1. Ordena arestas por peso
2. Adiciona as menores que **não formam ciclo**
3. Usa estrutura de conjuntos para evitar ciclos

---

## 6. 🧭 MENOR CAMINHO – DIJKSTRA & BELLMAN-FORD

### Arquivos:
- `Dijkstra.java`
- `BellmanFord.java`

### 📌 Dijkstra:
- Para grafos com **pesos não negativos**
- Escolhe o menor caminho atual

### 📌 Bellman-Ford:
- Funciona com **pesos negativos**
- Pode detectar **ciclos negativos**

---

## 7. 📌 COLA DE PROVA: O QUE É O QUÊ

| Termo                 | Significado rápido                                         |
|----------------------|-------------------------------------------------------------|
| Grafo                | Conjunto de vértices + arestas                             |
| Árvore               | Grafo sem ciclo e conectado                                |
| Aresta               | Conexão entre dois vértices                                |
| Vértice              | Nó (ponto/círculo)                                          |
| BFS                  | Busca em largura (nível por nível)                         |
| DFS                  | Busca em profundidade (vai fundo primeiro)                |
| Ciclo                | Caminho que volta pro mesmo ponto                          |
| Heap                 | Árvore onde pais > ou < filhos                             |
| HeapSort             | Ordena usando Heap                                         |
| Algoritmo guloso     | Pega a melhor escolha imediata                             |
| Kruskal              | Árvore geradora mínima com ordenação por peso             |
| Dijkstra             | Menor caminho sem pesos negativos                          |
| Bellman-Ford         | Menor caminho com pesos negativos, detecta ciclos negativos |

---

## 🚀 COMO RODAR

1. Compile:
```bash
javac NomeDoArquivo.java
```

2. Rode:
```bash
java NomeDaClassePrincipal
```

Exemplo:
```bash
cd greedy
javac Principal.java
java Principal
```

---

## 💬 Último conselho

Estuda por esse readme, deixa aberto na prova e **confia**.

Se precisar de ajuda com questão específica ou entender uma parte do código, **manda aqui** que eu destrincho.

Boa sorte, você vai passar. 🚀
