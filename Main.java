import java.util.*;
public class Main {
    public static void main(String[] args){
        /*Cidade cidade1 = new Cidade(1, 1.0,5.0);
        Cidade cidade2 = new Cidade(3,7.0,5.0);

        double distancia  =cidade1.calcularDistanciaEuclidiana(cidade2);

        System.out.println("distancia entre a cidade 1 e a cidade 2 é " + distancia);
        */

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






        //double[][] matriz = gerenciador.getMatrizDistancias();
        //List<Cidade> listaDeCidades = gerenciador.getCidades();
        //System.out.println("primeira cidade: ID " + listaDeCidades.get(0).getId());
        //System.out.println("ultima cidade: ID " + listaDeCidades.get(listaDeCidades.size()-1).getId());
        // OS INDICES DAS CIDADES É NUMERO DA CIDADE-1 => EXMPLO: CIDADE 1 TEM INDICE 0
        //System.out.println("distancia entre cidade 1 e cidade 3: " + matriz[0][2]);
        //System.out.println("distancia entre cidade 4 e cidade 8: " + matriz[3][7]);
        //System.out.println("distancia entre cidade 3 e cidade 6: " + matriz[2][5]);
    }
    
}
