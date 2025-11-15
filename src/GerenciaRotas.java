package projeto_rotas_md.src;
import java.io.*;
import java.util.*;

public class GerenciaRotas {
    protected List<Cidade> cidades;
    protected double[][] matrizDistancias;
    protected int numeroCidades;

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
            System.out.println("arquivo "+ entrada + " lido com sucesso. " + this.cidades.size()+ " cidades carregadas.");

        }catch(FileNotFoundException e){
            e.printStackTrace();
            throw new Excecoes("arquivo" + entrada + "nao encontrado", e);  //joga a exceção pro segundo construtor da classe Excecoes
        }
    }

    public void preencheMatrizDistancias(){
        this.matrizDistancias = new double[this.numeroCidades][this.numeroCidades]; //depende da leitura correta do arquivo
        
        for(int i=0; i<this.numeroCidades; i++){
            for(int j=0; j<this.numeroCidades; j++){
                Cidade cidadei = this.cidades.get(i);   //acessa a lista nas posições i e j
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

    public void TSP (int indiceCidadeInicial){      //setup pra recursão
        boolean[] visitado = new boolean[numeroCidades];    //armazena quais cidades já foram visitadas
        visitado[indiceCidadeInicial] = true;   //por padrão já inicializa tudo em false

        List<Integer> caminhoParcial = new ArrayList();     //armazena o caminho percorrido até então
        caminhoParcial.add(indiceCidadeInicial);    //começa sempre com a cidade inicial (ponto de partida)
        double distanciaParcial = 0.0;
        int n = this.numeroCidades;

        menorDistancia = Double.MAX_VALUE;     //vai sendo atualizado. Ao final de todas as iterações vai armazenar o menor valor de todos
        contadorCaminhos = 0;           
        melhorCaminho = new ArrayList<>();  //declara uma nova lista zerada para cada iteração

        buscaTSP_recursiva(indiceCidadeInicial, indiceCidadeInicial, 1, distanciaParcial, visitado, caminhoParcial);
    }

    public void copiaLista(List<Integer> origem, List<Integer> destino){
        destino.clear();
        destino.addAll(origem);
    }

    //onde estou, por onde já passei, quanto já andei
    public void buscaTSP_recursiva(int cidadeAtual, int cidadeInicial, int qtdVisitadas, double distanciaParcial, boolean[] visitado, List<Integer> caminhoParcial){

        //caso base
        if(qtdVisitadas == numeroCidades){     //caso base -> já percorreu todas as cidades
            double distanciaFinal = matrizDistancias[cidadeAtual][cidadeInicial] + distanciaParcial;
            contadorCaminhos++;     //+1 possível caminho registrado

            if(distanciaFinal < menorDistancia){
                menorDistancia = distanciaFinal;
                copiaLista(caminhoParcial, melhorCaminho);
                melhorCaminho.add(cidades.get(cidadeInicial).getId());
            }
            return;

        //recursão
        }else{     
            for(int i = 0; i < numeroCidades; i++){
//i é equivalente à 'próxima cidade'
                if(visitado[i] == true) continue;   //cidade ja foi visitada
                else if(visitado[i] == false){
                    int novaQtd = qtdVisitadas + 1;     //atualiza a quantidade de cidades visitadas
                    double novaDist = distanciaParcial + matrizDistancias[cidadeAtual][i];   //incrementa a distancia parcial

                    if(novaDist<menorDistancia){
                    visitado[i] = true;
                    caminhoParcial.add(cidades.get(i).getId());  //add a cidade ao registro do caminho percorrido
                    
                    buscaTSP_recursiva(i, cidadeInicial, novaQtd, novaDist, visitado, caminhoParcial);
                    //backtracking
                    caminhoParcial.remove(caminhoParcial.size() - 1);
                    visitado[i] = false;
                    }
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
        //aqui tem que ter a condicao tmb caso o n for 0, ai entra aquelas parada de tratamento de excessao e throw
        if(n==0 || n==1){
            return 1;
        }else if(n < 0)
            throw new IllegalArgumentException();   //parâmetro inválido
        long resultado =1;//long pra evitar qualquer problema
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