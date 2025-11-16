import java.util.*;

public class Main {
    public static void main(String[] args){

        GerenciaRotas gerenciador = new GerenciaRotas();

        try{
            gerenciador.lerArquivoEntrada("instancia.txt");
            gerenciador.preencheMatrizDistancias();
        }catch(Excecoes e){
            System.out.println("erro ao abrir o arquivo " + e.getMessage());
            e.printStackTrace();
            return;
        }
        
        Scanner scanner = new Scanner(System.in);
        boolean rodando =true;

        while(rodando){
            System.out.println("1 - Resolver Caixeiro Viajante ");
            System.out.println("2 - Calcular Análise Combinatoria");
            System.out.println("0 - Sair");
            System.out.print("escolha uma opcao: ");

            int opcao = scanner.nextInt();

            switch(opcao){
                case 1:
                    System.out.print("digite a cidade de origem - 1 a " + gerenciador.getCidades().size() + "\n");
                    int idCidadeInicial =scanner.nextInt();
                    int indiceCidadeInicial = idCidadeInicial - 1;//ajustando indice
                    if(indiceCidadeInicial <0 || indiceCidadeInicial >= gerenciador.getCidades().size()) {
                            throw new IllegalArgumentException("\nessa cidade nao existe, escolha um numero entre 1 e " + gerenciador.getCidades().size() + "\n");
                    }else{
                            gerenciador.TSP(indiceCidadeInicial);
                            System.out.println("menor distancia encontrada: " + gerenciador.getMenorDistancia());
                            //formatando a lista de ids, como eu ja tinha feito :
                            List<Integer> melhorCaminhoIndices =gerenciador.getMelhorCaminho();
                            List<Integer> melhorCaminhoIDs = new ArrayList<>();
                            for(Integer indice : melhorCaminhoIndices){
                                melhorCaminhoIDs.add(indice);
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
                            try{
                                long result = GerenciaRotas.fatorial(n);
                                System.out.println("resultado: " + n + "! = " + result);    
                            }catch(IllegalArgumentException e){
                                System.out.println("\nparametro invalido!\n");
                                e.printStackTrace();
                            }
                            

                        }else if(tipoCalculo ==2 || tipoCalculo == 3){
                            System.out.print("valor de n: ");
                            int n = scanner.nextInt();
                            System.out.print("valor de k: ");
                            int k =scanner.nextInt();

                            if(tipoCalculo == 2){
                                try{
                                    long result = GerenciaRotas.permutacao(n, k);
                                    System.out.println("Permutação P(" + n + ", " +k + ") = " + result);    
                                }catch(IllegalArgumentException e){
                                    System.out.println("\nargumentos invalidos!\n");
                                    e.printStackTrace();
                                }
                                
                            }else{
                                try{
                                long result =GerenciaRotas.combinacao(n, k);
                                System.out.println("Combinação C(" +n + "," +k+ ") = " + result);   
                                }catch(IllegalArgumentException e){
                                    System.out.println("\nargumentos invalidos!\n");
                                    e.printStackTrace();
                                }
                                
                            }
                        }else{
                            System.out.println("\nopcao invalida, escolha 1,2 ou 3.\n");
                        }
                        break;
                case 0:
                        rodando = false;
                        System.out.println("saindo do programa");
                        break;
                default:
                        System.out.println("\nopcao invalida, digite outro numero\n");
                        break;
            }
        }
        scanner.close();
    }
}
