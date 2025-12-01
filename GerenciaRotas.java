import java.io.*;
import java.util.*;

public class GerenciaRotas {
    protected List<Cidade> cidades;
    protected double[][] matrizDistancias;
    protected int numeroCidades;

// Decidimos implementar o Problema do Caixeiro Viajante.
// Objetivo: encontrar a menor rota possível que percorra todos os pontos e volte à cidade de origem
// Estratégia: cálculo das rotas com base em matriz de distâncias

// inicializa as cidades
    public GerenciaRotas(){
        this.cidades = new ArrayList<>();
    }

    public List<Cidade> getCidades(){
        return this.cidades;
    }

    public void lerArquivoEntrada(String entrada) throws Excecoes{
        this.cidades.clear();   //esvazia a lista

        try (Scanner scanner = new Scanner(new File(entrada))){
            
            // lê a primeira linha (informa o número de cidades)
            this.numeroCidades =scanner.nextInt();

            for(int i=1;i <= numeroCidades;i++){
                double x=scanner.nextDouble();
                double y =scanner.nextDouble();
                Cidade novaCidade = new Cidade(i, x, y);
                this.cidades.add(novaCidade);
            }

        }catch(FileNotFoundException e){
            e.printStackTrace();
            throw new Excecoes("arquivo" + entrada + "nao encontrado", e); 
        }
    }

    //funcao que prrenche a matriz de distancias
    public void preencheMatrizDistancias(){
        this.matrizDistancias = new double[this.numeroCidades][this.numeroCidades]; 
        
        for(int i=0; i<this.numeroCidades; i++){
            for(int j=0; j<this.numeroCidades; j++){
                Cidade cidadei = this.cidades.get(i);  
                Cidade cidadej = this.cidades.get(j);
                double distancia = cidadei.calcularDistanciaEuclidiana(cidadej);
                this.matrizDistancias[i][j]= distancia;
            }
        }   
    }

    public double[][] getMatrizDistancias(){
        return this.matrizDistancias;
    }
    
    protected double menorDistancia;
    protected int contadorCaminhos; //contabiliza a quantidade de caminhos (COMPLETOS) possíveis
    protected List<Integer> melhorCaminho;

    public void TSP(int indiceCidadeInicial, int indiceCidadeDestino) {
        boolean[] visitado = new boolean[numeroCidades];
        visitado[indiceCidadeInicial] = true;

        List<Integer> caminhoParcial = new ArrayList<>(); //armazena o caminho percorrido até então
        caminhoParcial.add(indiceCidadeInicial); //começa sempre com a cidade inicial 
        
        double distanciaParcial = 0.0;
        
        menorDistancia = Double.MAX_VALUE; //vai sendo atualizado no final de todas as iterações vai armazenar o menor valor de todos
        melhorCaminho = new ArrayList<>(); //declara uma nova lista zerada para cada iteração

        buscaTSP_recursiva(indiceCidadeInicial, indiceCidadeInicial, indiceCidadeDestino, 1, distanciaParcial, visitado, caminhoParcial);
    }

    public void copiaLista(List<Integer> origem, List<Integer> destino){
        destino.clear();
        destino.addAll(origem);
    }

    //onde estou, por onde já passei, quanto já andei
    public void buscaTSP_recursiva(int cidadeAtual, int cidadeInicial, int cidadeDestino, int qtdVisitadas, double distanciaParcial, boolean[] visitado, List<Integer> caminhoParcial){
        //caso base
        if (qtdVisitadas == numeroCidades) {
            if (cidadeAtual == cidadeDestino) {
                double distanciaFinal = distanciaParcial; 
                if (distanciaFinal < menorDistancia){
                    menorDistancia = distanciaFinal;
                    copiaLista(caminhoParcial, melhorCaminho);
                }
            }
        return;
        }
        for (int i = 0; i < numeroCidades; i++) {
            if (!visitado[i]) {
                // se a gente tentar visitar a cidade destino antes de todas as outras, o caminho fica invalido
                if (i == cidadeDestino && qtdVisitadas < numeroCidades - 1) {
                    continue; 
                }
            double novaDist = distanciaParcial + matrizDistancias[cidadeAtual][i];
                //aqui verifica que a nova distancia é menor que a menor distancia ja encontrada
                if (novaDist < menorDistancia){
                    visitado[i] = true;
                    caminhoParcial.add(i);
                    buscaTSP_recursiva(i, cidadeInicial, cidadeDestino, qtdVisitadas + 1, novaDist, visitado, caminhoParcial);
                    caminhoParcial.remove(caminhoParcial.size() - 1);
                    visitado[i] = false;
                }
            }
        }
    }

    public double getMenorDistancia(){
        return this.menorDistancia;
    }
    public List<Integer> getMelhorCaminho(){
        return this.melhorCaminho;
    }
    
    //funcoes dos dados e análises:
    public static long fatorial(int n){
        if(n==0 || n==1){
            return 1;
        }else if(n < 0)
            throw new IllegalArgumentException();   //parâmetro inválido
        long resultado =1;  //long pra evitar qualquer problema
        for(int i=2;i <=n;i++){
            resultado *=i;
        }
        return resultado;
    }

    public static long permutacao(int n,int k){
        if(n<0 || k<0 || k>n)
            throw new IllegalArgumentException();   //parâmetros inválidos 
        //P(n,k) = n! dividido por (n-k)!
        return fatorial(n) / fatorial(n-k);
    }

    public static long combinacao(int n,int k){
        if(n<0 || k<0 || k>n)
            throw new IllegalArgumentException();   //parâmetros inválidos 
        return fatorial(n)/(fatorial(k) * fatorial(n-k));
    }
}
