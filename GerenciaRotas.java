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

    public void lerArquivoEntrada(String entrada){
        this.cidades.clear();   //esvazia a lista

        // aqui tem que ter o try ctach pra evitar bo
        try(Scanner scanner = new Scanner(new File(entrada))){

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
            System.err.println("erro: arquivo '" + entrada + "' nao encontrado.");
            e.printStackTrace();
        }
    }

    public void preencheMatrizDistancias(){
        this.matrizDistancias = new double[this.numeroCidades][this.numeroCidades];
        
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

        //falta chamar buscaTSP(...){...}
        //oq tava faltando:
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
                melhorCaminho.add(cidadeInicial);
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

                    //adicioanndo uma poda para otimizar mais o algoritmo
                    //tipo, se o caminho que tamo construindo agr épior que o melhor caminho completo que ja achamos
                    //ent nem vale a pena continuar, ent tem esse if aqui:
                    if(novaDist<menorDistancia){
                    visitado[i] = true;
                    caminhoParcial.add(i);  //add a cidade ao registro do caminho percorrido
                    
                    buscaTSP_recursiva(i, cidadeInicial, novaQtd, novaDist, visitado, caminhoParcial);
                    //backtracking
                    caminhoParcial.remove(caminhoParcial.size() - 1);
                    visitado[i] = false;
                    }
                }
            }
        }
    }

    //adicionando getter de menor distancia e melhor caminho:

    public double getMenorDistancia(){
        return this.menorDistancia;
    }
    public List<Integer> getMelhorCaminho(){
        return this.melhorCaminho;
    }
}
//te amo <3
//te amo fofa <3