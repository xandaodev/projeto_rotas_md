import java.util.*;
public class Main {
    public static void main(String[] args){

        GerenciaRotas gerenciador = new GerenciaRotas();
        gerenciador.lerArquivoEntrada("instancia.txt");
        gerenciador.preencheMatrizDistancias();

        Scanner scanner = new Scanner(System.in);
        boolean rodando =true;

        //fazendo um menu interativo com while

        while(rodando){
            System.out.println("1 - resolver Caixeiro Viajante ");
            System.out.println("2 - calcular analise combinatoria");
            System.out.println("0 - sair");
            System.out.print("escolha uma opcao: ");

            int opcao = scanner.nextInt();

            //amor aqui tmb se vc quiser colocar um try catch, mas assim o matheus que cobra isso n precisa
            switch(opcao){
                case 1:
                    System.out.print("digite a cidade de origem (1 a 12): ");
                    int idCidadeInicial =scanner.nextInt();
                    int indiceCidadeInicial = idCidadeInicial - 1;//ajustando indice
                    if(indiceCidadeInicial <0 || indiceCidadeInicial >= 12) {
                            System.out.println("essa cidade nao existe, escolha um numero entre 1 e 12.");
                    }else{
                            gerenciador.TSP(indiceCidadeInicial);
                            System.out.println("menor distancia encontrada: " + gerenciador.getMenorDistancia());
                            //formatando a lista de ids, como eu ja tinha feito :
                            List<Integer> melhorCaminhoIndices =gerenciador.getMelhorCaminho();
                            List<Integer> melhorCaminhoIDs = new ArrayList<>();
                            for(Integer indice : melhorCaminhoIndices){
                                melhorCaminhoIDs.add(indice+1);
                            }
                            System.out.println("melhor caminho: " + melhorCaminhoIDs);
                    }
                    break;
                case 2:
                    System.out.println("1 - fatorial (n!)");
                    System.out.println("2 - permutação");
                    System.out.println("3 - combincao");
                    System.out.print("escolha o calculo: ");
                    int tipoCalculo = scanner.nextInt();
                    if(tipoCalculo == 1){
                            System.out.print("valor de n: ");
                            int n=scanner.nextInt();
                            long result = GerenciaRotas.fatorial(n);
                            System.out.println("resultado: " + n + "! = " + result);

                        }else if(tipoCalculo ==2 || tipoCalculo == 3){
                            System.out.print("valor de n: ");
                            int n = scanner.nextInt();
                            System.out.print("valor de k: ");
                            int k =scanner.nextInt();

                            if(tipoCalculo == 2){
                                long result = GerenciaRotas.permutacao(n, k);
                                System.out.println("Permutação P(" + n + ", " +k + ") = " + result);
                            }else{
                                long result =GerenciaRotas.combinacao(n, k);
                                System.out.println("Combinação C(" +n + "," +k+ ") = " + result);
                            }
                        }else{
                            System.out.println("opcao invalida, escolha 1,2 ou 3.");
                        }
                        break;
                case 0:
                        rodando = false;
                        System.out.println("saindo do programa");
                        break;
                default:
                        System.out.println("opcao invalida, digite outro numero");
                        break;
            }
        }

        /* 
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
        */
        scanner.close();
    }
    
}
