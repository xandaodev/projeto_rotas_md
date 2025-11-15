import java.util.*;
public class Main {
    public static void main(String[] args){

        GerenciaRotas gerenciador = new GerenciaRotas();
        gerenciador.lerArquivoEntrada("instancia.txt");
        gerenciador.preencheMatrizDistancias();

        System.out.println("testando matriz:");
        System.out.println("distancia entre cidade 1 e cidade 3: " + gerenciador.getMatrizDistancias()[0][2]);

        //chamando metodo tsp
        int cidadeInicial =0;//começando da cidade 1(indice 0, como eu tinha te falado)

        gerenciador.TSP(cidadeInicial);//executando o algoritmo

        System.out.println("menor distancia encontrada: "+ gerenciador.getMenorDistancia());

        List<Integer> melhorCaminhoIndices = gerenciador.getMelhorCaminho();
        List<Integer> melhorCaminhoIDs = new ArrayList<>();
        
        //como os indices das cidades são o numero delas - 1, fiz esse for no final só pra formatar e imprimir certo
        for(Integer indice : melhorCaminhoIndices){
            melhorCaminhoIDs.add(indice + 1);
        }

        System.out.println("melhor caminho - rota: " +melhorCaminhoIDs);

    }
    
}
