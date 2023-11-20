package ordenacao;
import java.util.Scanner;
import java.util.Random;

/* Ciencia da Computacao 4a 
Kauany Almeida Silva */

public class ShellSort // Shell Sort
{
    int[] vetorIncrementos;
    int tamanho;
 
    int contadorTrocas = 0;
    int contadorIteracoes = 0;   
    
    public ShellSort(int tamanho)
    {
        this.tamanho = tamanho;
        vetorIncrementos = new int[tamanho];
        Random random = new Random();           // preenchendo o vetor com numeros aleatorios, de 0 a 29
        for (int i = 0; i < tamanho ; i++){
            vetorIncrementos[i] = random.nextInt(30);
        }
    }    
    
    public static void main(String[] args) 
    {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Digite qual vai ser o tamanho do vetor: ");
        int respostaTamanho = scanner.nextInt();
        
        ShellSort shellsort = new ShellSort(respostaTamanho);
        System.out.print("\nVetor gerado aleatoriamente, sem o SHELL SORT: \n\t [ ");
        shellsort.printarVetor(shellsort.vetorIncrementos, respostaTamanho); System.out.print(" ]");

        // calculando tempo de inicio, antes da execucao do sort
        long tempoAntes = System.currentTimeMillis(); // tempo antes do shell sort
        System.out.println("\n\n>>> Tempo inicial de execução: " + tempoAntes + "ms.");
            
        shellsort.shellSort(shellsort.vetorIncrementos, respostaTamanho);
        System.out.print("\n\nVetor após o SHELL SORT: \n\t[ ");
        shellsort.printarVetor(shellsort.vetorIncrementos, respostaTamanho);  System.out.print(" ]");   
        
        // calculando tempo apos terminar a execucao do sort
        long tempoDepois = System.currentTimeMillis();
        System.out.println("\n\n>>> Tempo final da execução: " + tempoDepois + "ms.");

        System.out.println("\n\n>>> Média de tempo em milissegundos para executar a funcao: \n\t" + (tempoDepois - tempoAntes) + "ms");
        System.out.println("\nNúmero de trocas: " + shellsort.contadorTrocas); 
        System.out.println("Número de iteracoes: " + shellsort.contadorIteracoes + "\n\n");        
    }
    
    public void printarVetor(int[] vetor, int tamanhoVetor)
    {   for (int i = 0; i < tamanhoVetor ; i++) {
            System.out.print(vetor[i] + " ");
        }
    }

    public void shellSort(int[] gaps, int tamanhoVetor) 
    {
        int incr, j, k, span, y;
        for (incr = tamanhoVetor / 2; incr > 0; incr /= 2) { // dividindo pela metade a cada iteracao
            for (j = incr; j < tamanhoVetor; j++) // a diferenca esta aqui e na linha de cima tambem
            {
                y = gaps[j];

                for (k = j - incr; k >= 0 && y < gaps[k]; k -= incr) {
                    gaps[k + incr] = gaps[k];
                    contadorTrocas++;
                }
                gaps[k + incr] = y;

                contadorIteracoes++;
            }
        }
    }
}