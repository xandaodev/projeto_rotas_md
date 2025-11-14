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

        List<Cidade> listaDeCidades = gerenciador.getCidades();

        System.out.println("primeira cidade: ID " + listaDeCidades.get(0).getId());
        System.out.println("ultima cidade: ID " + listaDeCidades.get(listaDeCidades.size()-1).getId());
    }
    
}
