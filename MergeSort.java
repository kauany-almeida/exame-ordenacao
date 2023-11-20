package ordenacao;

import java.util.Scanner;
import java.util.Random;

public class MergeSort // Merge Sort
{
    int[] vetorA;
    int[] vetorB;
    int[] vetorC;

    int tamanhoAB;
    int contadorTrocas = 0;
    int contadorIteracoes = 0;
    public int tamanhoVetorC;

    //long seed = 123;

    public MergeSort(int tamanhoAB) {
        this.tamanhoAB = tamanhoAB;
        vetorA = new int[tamanhoAB];
        vetorB = new int[tamanhoAB];
        vetorC = new int[tamanhoAB * 2]; // o tamanho de vetor C vai ser a soma dos tamanhos do vetor A e vetor B

        //Random random = new Random(seed); 
        Random random = new Random();
        for (int i = 0; i < tamanhoAB; i++) {
            vetorA[i] = random.nextInt(50);
            vetorB[i] = random.nextInt(50); // cada vetor sera preenchido com valores aleatorios de ate 59
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print(">>> Digite qual o tamanho das duas listas, lista vetor_A  e  lista_vetorB: ");
        int tamanhoVetores = scanner.nextInt(); //int tamanhoDoVetorC = tamanhoVetores;
        int tamanhoVetorC = tamanhoVetores * 2;

        MergeSort recuperacao = new MergeSort(tamanhoVetores);
        System.out.print("\nvetor_A: [ ");
        recuperacao.printarVetor(recuperacao.vetorA, recuperacao.tamanhoAB);   // printando os vetores antes do merge
        System.out.print(" ]\nvetor_B: [ ");            
        recuperacao.printarVetor(recuperacao.vetorB, recuperacao.tamanhoAB);
        System.out.print(" ]");

        // calculando tempo de inicio, antes da execucao do sort
        long tempoAntes = System.currentTimeMillis(); // tempo antes do merge sort

        int i = 0;
        int j = 0;
        int percorrer = 0;

        while (i < tamanhoVetores && j < tamanhoVetores) {
            if (recuperacao.vetorA[i] < recuperacao.vetorB[j]) {
                recuperacao.vetorC[percorrer] = recuperacao.vetorA[i];  // o menor valor entre A e B sera armazenado em C
                i++;
            } else {
                recuperacao.vetorC[percorrer] = recuperacao.vetorB[j];
                j++;
            }
            percorrer++;
            recuperacao.contadorIteracoes++;
            //recuperacao.contadorTrocas++;
        }

        while (i < tamanhoVetores) {
            recuperacao.vetorC[percorrer] = recuperacao.vetorA[i]; // o menor valor entre A e B sera armazenado em C
            i++;
            percorrer++;
            recuperacao.contadorIteracoes++;
        }
        while (j < tamanhoVetores) {
            recuperacao.vetorC[percorrer] = recuperacao.vetorB[j];
            j++;
            percorrer++;
            recuperacao.contadorIteracoes++;
        }

        System.out.print("\n\nvetor sem ordnacao    ");
        recuperacao.printarVetor(recuperacao.vetorC, tamanhoVetorC);

        recuperacao.mergeSort(recuperacao.vetorC, 0, (tamanhoVetorC - 1));

        System.out.print("\n\nvetor_C: [ ");
        recuperacao.printarVetor(recuperacao.vetorC, tamanhoVetorC);
        System.out.print(" ]\n");

        // calculando tempo apos terminar a execucao do sort
        long tempoDepois = System.currentTimeMillis();

        System.out.println("\n>>> Média de tempo em milissegundos para executar a funcao: \n\t" + (tempoDepois - tempoAntes) + "ms");
        System.out.println("\nNúmero de trocas: " + recuperacao.contadorTrocas);
        System.out.println("Número de iteracoes: " + recuperacao.contadorIteracoes + "\n\n");
    }

    public void printarVetor(int[] vetor, int tamanhoVetor) { // metodo para printar o vetor quando chamado
        for (int i = 0; i < tamanhoVetor; i++) {
            System.out.print(vetor[i] + "  ");
        }
    }

    public void mergeSort(int[] vetor, int indiceEsquerda, int indiceDireita) {
        if (indiceEsquerda < indiceDireita) { // dividindo em sublistas recursivamente e ordenando
            int indiceMetadeVetor = ((indiceEsquerda + indiceDireita) / 2); // esquerda comeca em 0, direita termina no "i" final

            mergeSort(vetor, indiceEsquerda, indiceMetadeVetor); // separando a metade da esquerda
            mergeSort(vetor, (indiceMetadeVetor + 1), indiceDireita);
            ordenacao(vetor, indiceEsquerda, indiceMetadeVetor, indiceDireita); // juntando e ordenando todas as sublistas
        }
    }

    public void ordenacao(int[] vetor, int indiceEsquerda, int metadeMeio, int indiceDireita) {
        int temporarioesquerda = (metadeMeio - indiceEsquerda + 1); // +1 por que vai comecar em 0, parte da esquerda
        int temporariodireita = (indiceDireita - metadeMeio); // parte da direita

        int[] metadeEsquerda = new int[temporarioesquerda];
        int[] metadeDireita = new int[temporariodireita];

        for (int i = 0; i < temporarioesquerda; i++) {
            metadeEsquerda[i] = vetor[indiceEsquerda + i];
        } // preenchendo metade da esquerda no vertor

        for (int j = 0; j < temporariodireita; j++) {
            metadeDireita[j] = vetor[metadeMeio + 1 + j];
        } // preenchendo metade da direita no vetor

        int primeiroEsq = 0;
        int primeiroDir = 0; // primeiro+1
        int percorrer = indiceEsquerda; // para ir percorrendo os indices comecando pela esquerda(inicio do vetor)

        while (primeiroEsq < temporarioesquerda && primeiroDir < temporariodireita) 
        { // primeiro < esquerda      &&      ultimo < direita

            if (metadeEsquerda[primeiroEsq] <= metadeDireita[primeiroDir]) {
                vetor[percorrer] = metadeEsquerda[primeiroEsq];
                primeiroEsq++;
                contadorTrocas++;
                contadorIteracoes++;
            } else {
                vetor[percorrer] = metadeDireita[primeiroDir];
                primeiroDir++;
                contadorTrocas++;
                contadorIteracoes++;
            }
            percorrer++;
            
        }

        while (primeiroEsq < temporarioesquerda) {
            vetor[percorrer] = metadeEsquerda[primeiroEsq]; // recebe vetor da esquera
            primeiroEsq++;
            percorrer++;
            contadorIteracoes++;
        }

        while (primeiroDir < temporariodireita) {
            vetor[percorrer] = metadeDireita[primeiroDir]; // recebe o i+1
            primeiroDir++;
            percorrer++;
            contadorIteracoes++;
        }
    }
}
