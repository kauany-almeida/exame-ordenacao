package recuperacaoordenacao;
import java.util.Scanner;
import java.util.Random;

/* Ciencia da Computacao 4a 
Kauany Almeida Silva */

public class BubbleSort // BubbleSort
{
    int[] vetor;
    int tamanho;
    int contadorTrocas = 0;
    int contadorIteracoes = 0;
    
    public BubbleSort(int tamanho)
    {
        this.tamanho = tamanho;
        vetor = new int[tamanho];
        Random random = new Random();           // preenchendo o vetor com numeros aleatorios, de 0 a 29
        for (int i = 0; i < tamanho ; i++){
            vetor[i] = random.nextInt(30);
        }
    }
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Digite qual vai ser o tamanho do vetor: ");
        int respostaTamanho = scanner.nextInt();
        
        BubbleSort bubblesort = new BubbleSort(respostaTamanho);
        System.out.print("\nVetor gerado aleatoriamente (sem o BUBBLE SORT): \n\t[ ");        
        bubblesort.printarVetor(bubblesort.vetor, respostaTamanho); System.out.print(" ]");
                
        // calculando tempo de inicio, antes da execucao do sort
        long tempoAntes = System.currentTimeMillis(); // tempo antes do bubble sort
        System.out.println("\n\n>>> Tempo inicial de execução: " + tempoAntes + "ms.");
        
        bubblesort.bubbleSort(bubblesort.vetor, respostaTamanho);
        System.out.print("\n\nVetor após o BUBBLE SORT: \n\t[ ");
        bubblesort.printarVetor(bubblesort.vetor, respostaTamanho);  System.out.print(" ]");   
        
        // calculando tempo apos terminar a execucao do sort
        long tempoDepois = System.currentTimeMillis();
        System.out.println("\n\n>>> Tempo final da execução: " + tempoDepois + "ms.");

        System.out.println("\n\n>>> Média de tempo em milissegundos para executar a funcao: \n\t" + (tempoDepois - tempoAntes) + "ms");
        System.out.println("\nNúmero de trocas: " + bubblesort.contadorTrocas); 
        System.out.println("Número de iteracoes: " + bubblesort.contadorIteracoes + "\n\n");
    }
    
    public void printarVetor(int[] vetor, int tamanhoVetor){
        for ( int i = 0 ; i < tamanhoVetor ; i++){
            System.out.print(vetor[i] + " ");
        }
    }    
    
    public void bubbleSort(int[] vetor, int tamanhoVetor)
    {
        int n = tamanhoVetor;
        int temp = 0;
        for (int i = 0 ; i < n ; i++)
        {   
            for (int j = 1 ; j < (n-i) ; j++)
            {   contadorIteracoes++;
            
                if (vetor [j-1] > vetor[j])
                {
                    // trocar os elementos
                    temp = vetor[j-1];
                    vetor[j-1] = vetor[j];
                    vetor[j] = temp;
                    contadorTrocas++;
                }
            }
        }
    }
    
}
