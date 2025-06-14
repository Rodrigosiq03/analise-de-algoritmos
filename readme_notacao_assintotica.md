# Cola Definitiva de **Notação Assintótica**

> **Objetivo** – Ser o guia rápido (e completo) para você bater o olho em uma expressão ou em um trecho de código e **cravar** as respostas de *Big‑O*, *Big‑Omega* e *Big‑Theta* na prova.

---

## Índice

1. [Por que precisamos de notação assintótica?](#por-que)
2. [Definições formais – O, Ω e Θ](#definicoes)
3. [Regra de ouro para funções matemáticas](#regra-funcoes)
4. [Passo‑a‑passo para códigos (loops, condicionais, recursão)](#passo-codigo)
5. [Diferença prática entre ](#o-vs-omega)[**O(n)**](#o-vs-omega)[ e ](#o-vs-omega)[**Ω(n)**](#o-vs-omega)
6. [Exemplos clássicos & pegadinhas da prova](#exemplos)
   - 6.1 5n² + 3n + 7 × n²
   - 6.2 Programa **ProcessaVetor** (Java)
   - 6.3 Gráfico com c₁·g(n) e c₂·g(n)
7. [Tabela de consulta rápida](#tabela)
8. [Checklist final antes de marcar a alternativa](#checklist)

---



## 1. Por que precisamos de notação assintótica?

Medir tempo real de execução é inviável para todas as entradas; queremos **comparar algoritmos independentemente de hardware**. Usamos funções *f(n)* (tempo, número de operações) em função do tamanho *n* da entrada e descrevemos o **comportamento quando n cresce sem limite**.



- **O (Big‑O)** ‑ limite **superior** (teto): *f(n)* cresce **no máximo** tão rápido quanto *g(n)*.
- **Ω (Big‑Omega)** ‑ limite **inferior** (piso): *f(n)* cresce **pelo menos** tão rápido quanto *g(n)*.
- **Θ (Big‑Theta)** ‑ quando teto = piso (corredor estreito): *f(n)* cresce **na mesma ordem** de *g(n)*.

---



## 2. Definições formais

| Símbolo     | Definição matemática                                                               |
| ----------- | ---------------------------------------------------------------------------------- |
| **O(g(n))** | ∃ *c*>0, *n₀* tal que 0 ≤ *f(n)* ≤ *c·g(n)* para todo *n ≥ n₀*                     |
| **Ω(g(n))** | ∃ *c*>0, *n₀* tal que 0 ≤ *c·g(n)* ≤ *f(n)* para todo *n ≥ n₀*                     |
| **Θ(g(n))** | ∃ *c₁*, *c₂*>0, *n₀* tal que 0 ≤ *c₁·g(n)* ≤ *f(n)* ≤ *c₂·g(n)* para todo *n ≥ n₀* |

🔑 **Tradução para humanos**

- *O* → “não passa de”
- *Ω* → “não cai abaixo de”
- *Θ* → “fica preso entre”

---



## 3. Regra de ouro para funções matemáticas

1. **Encontre o termo de maior crescimento** (maior expoente ou mais rápido – por ex., exponencial > polinomial > logarítmico > constante).
2. **Jogue fora constantes multiplicativas** (5n² ~~→ n²~~).
3. **Despreze termos menores** (*n²* domina *n* e *log n*).\
   `7n³ + 4n log n + 42  ⇒  Θ(n³)`
4. Se a função for polinomial  *aₖnᵏ + ... + a₀*, então:
   - O, Ω e Θ **coincidem** em *nᵏ*.

> **Atalho mental** ⚡ – *“Maior potência ganha. Multiplicadores não importam.”*

---



## 4. Passo‑a‑passo para códigos

### 4.1 Fluxo sequencial

Somamos custos: `O(f) + O(g) = O(max(f,g))`\
Ex.: ler vetor *(n)* + somar *(n)* ⇒ `O(n)`.

### 4.2 Loops simples

```python
for i in range(n):
    ...          # O(1) interno
```

Custo = n · O(1) ⇒ **O(n)**.

### 4.3 Loops aninhados

```python
for i in range(n):
    for j in range(n):
        ...        # O(1)
```

Produto dos limites ⇒ **O(n²)**.

### 4.4 Loop que reduz o problema

```python
while n > 1:
    n = n//2   # divide por 2
```

Número de iterações ≈ log₂ n ⇒ **O(log n)**.

### 4.5 Recursão típica

- Merge Sort: `T(n)=2T(n/2)+O(n)` → **O(n log n)**.
- Quick Sort pior caso: `T(n)=T(n−1)+O(n)` → **O(n²)**.

### 4.6 Pistas visuais (malícias)

| Padrão                                     | Complexidade   |
| ------------------------------------------ | -------------- |
| **Somar**/percorrer estrutura              | O(n)           |
| **Dois laços independentes** um após outro | O(n)+O(n)=O(n) |
| **Laços encaixados** com mesmo limite      | O(n²)          |
| **Laço que corta pela metade**             | O(log n)       |
| **Recursão tipo árvore completa**          | O(n log n)     |

---



## 5. Diferença prática entre `O(n)` e `Ω(n)`

- `O(n)` – **garante um teto**: o algoritmo nunca será pior que linear (após certo *n*).
- `Ω(n)` – **garante um piso**: sempre gastará pelo menos tempo linear.

Quando **ambos** valem, temos `Θ(n)` (bound apertado).\
**Exemplo:** varrer array para somar ➜ `O(n)` + `Ω(n)` = `Θ(n)`.

> 💡 **Dica de prova:** Se você só varre todos os elementos uma vez, sabe que não é melhor que linear (Ω(n)) nem pior (O(n)) ⇒ marque `Θ(n)` se perguntarem.

---



## 6. Exemplos (baseados na sua prova)

### 6.1 Funções `f(n)=5n²+3n+7` e `g(n)=n²`

- **Passo 1**: termo dominante → `n²`.
- **Comparação**:\
  `5n²+3n+7  ≤ 5n²+3n²+7n² = 15n²` (para n≥1).\
  `5n²+3n+7  ≥ 5n²`.
- Existirão c₁=5, c₂=15 e n₀=1 tais que\
  `c₁·g(n) ≤ f(n) ≤ c₂·g(n)`.
- **Conclusão**: `f ∈ Θ(g)`, logo também `O(g)` e `Ω(g)`.

### 6.2 Código **ProcessaVetor** (Java mostrado no enunciado)

```java
int[] vetor = new int[args.length];             // O(n)
for (int i=0; i < args.length; i++) { ... }      // O(n)
int soma = 0;
for (int i=0; i < vetor.length; i++) { soma += vetor[i]; } // O(n)
int maximo = vetor[0];
for (int i=1; i < vetor.length; i++) { ... }     // O(n)
```

- Quatro passos lineares ⇒ **O(4n) = O(n)**.
- Precisa olhar todos os elementos → **Ω(n)**.
- Não é `Θ(1)` de jeito nenhum (executa + cresce com n).

### 6.3 Gráfico com c₁·g(n) e c₂·g(n)

- Se *f(n)* oscila mas eventualmente fica entre as curvas verde (c₁·g) e amarela (c₂·g) depois de n₀, então podemos afirmar `f ∈ Θ(g)` **mesmo que antes de n₀ isso não aconteça**.
- O gráfico da questão confundia ao destacar a região `0 ≤ n < n₀`; lembre‑se de que as definições **começam em n₀** – antes disso vale qualquer coisa.

---



## 7. Tabela de consulta rápida

| Padrão de código / função                 | O(n)      | Ω(n)        | Θ(n)      |
| ----------------------------------------- | --------- | ----------- | --------- |
| Loop único até n                          | ✔         | ✔           | ✔         |
| Dois loops aninhados n×n                  | ✔ (n²)    | ✔ (n²)      | ✔ (n²)    |
| Busca binária                             | ✔ (log n) | ✔ (log n)   | ✔ (log n) |
| Soma constante de números                 | ✔ (1)     | ✔ (1)       | ✔ (1)     |
| Algoritmo que depende de pivô (QuickSort) | ✔ (n²)    | ✔ (n log n) | ✖         |

---



## 8. Checklist final ⚙️

1. **Identifique a maior operação que repete**.
2. **Procure loops aninhados** → multiplicação de limites.
3. **Reduções pela metade** indicam `log n`.
4. Verifique se todo elemento é visitado **obrigatoriamente** → isto dá Ω(n).
5. Se O = Ω → marque Θ.
6. Ignore **constantes e termos menores** – não perca tempo!

---

### Último conselho ⏰

Treine fazer 10 exercícios cronometrando 1 min por função/código. Seu olho vai ficar automático.

Boa prova! 💪

