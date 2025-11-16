import java.util.*;

public class Main {
    public static void main(String[] args){

        GerenciaRotas gerenciador = new GerenciaRotas();

        try{
            gerenciador.lerArquivoEntrada("instancia.txt");
            gerenciador.preencheMatrizDistancias();
        }catch(Excecoes e){
            System.out.println("Erro ao abrir o arquivo " + e.getMessage());
            e.printStackTrace();
            return;
        }
        
        Scanner scanner = new Scanner(System.in);
        boolean rodando =true;
        //System.out.print("\n");
        System.out.println("\n========================================================");
        System.out.println("--- Bem vindo ao Sistema de Gerenciamento de Rotas ---");
        System.out.println("========================================================");

        while(rodando){
            System.out.println("\n             MENU PRINCIPAL");
            System.out.println("----------------------------------------");
            System.out.println("1 - Resolver Caixeiro Viajante ");
            System.out.println("2 - Calcular Análise Combinatoria");
            System.out.println("0 - Sair");
            System.out.println("---------------------------------------");

            System.out.print("Escolha uma opção: > ");

            int opcao = scanner.nextInt();

            switch(opcao){
                case 1:
                    System.out.print("Digite a cidade de origem - 1 a " + gerenciador.getCidades().size() + "\n");
                    int idCidadeInicial =scanner.nextInt();
                    int indiceCidadeInicial = idCidadeInicial - 1;//ajustando indice
                    if(indiceCidadeInicial <0 || indiceCidadeInicial >= gerenciador.getCidades().size()) {
                            throw new IllegalArgumentException("\nCidade inválida, escolha um numero entre 1 e " + gerenciador.getCidades().size() + "\n");
                    }else{
                            gerenciador.TSP(indiceCidadeInicial);
                            System.out.print("\n");
                            System.out.println(".................................................................");
                            System.out.println("Menor distância encontrada: " + gerenciador.getMenorDistancia());
                            //formatando a lista de ids, como eu ja tinha feito :
                            List<Integer> melhorCaminhoIndices =gerenciador.getMelhorCaminho();
                            List<Integer> melhorCaminhoIDs = new ArrayList<>();
                            for(Integer indice : melhorCaminhoIndices){
                                melhorCaminhoIDs.add(indice+1);//ADICIONEI O +1 AQUI
                            }
                            System.out.print("\n");
                            System.out.println("Melhor caminho: " + melhorCaminhoIDs);
                            System.out.println(".................................................................");
                            System.out.print("\n");
                    }
                    break;
                case 2:
                    System.out.println("1 - Fatorial (n!)");
                    System.out.println("2 - Permutação");
                    System.out.println("3 - Combinção");
                    System.out.print("Escolha o cálculo: > ");
                    int tipoCalculo = scanner.nextInt();
                    if(tipoCalculo == 1){
                            System.out.print("\n");
                            System.out.print("Valor de n: > ");
                            int n=scanner.nextInt();
                            try{
                                long result = GerenciaRotas.fatorial(n);
                                System.out.print("\n");
                                System.out.println("..................................");
                                System.out.println("Resultado: " + n + "! = " + result);  
                                System.out.println("..................................");  
                            }catch(IllegalArgumentException e){
                                System.out.print("\n");
                                System.out.println("\nParâmetro inválido!\n");
                                e.printStackTrace();
                            }
                            

                        }else if(tipoCalculo ==2 || tipoCalculo == 3){
                            System.out.print("\n");
                            System.out.print("Valor de n: > ");
                            int n = scanner.nextInt();
                            System.out.print("\n");
                            System.out.print("Valor de k: > ");
                            int k =scanner.nextInt();

                            if(tipoCalculo == 2){
                                try{
                                    long result = GerenciaRotas.permutacao(n, k);
                                    System.out.print("\n");
                                    System.out.println("..................................");
                                    System.out.println("Permutação P(" + n + ", " +k + ") = " + result);  
                                    System.out.println("..................................");  
                                }catch(IllegalArgumentException e){
                                    System.out.print("\n");
                                    System.out.println("\nArgumentos invalidos!\n");
                                    e.printStackTrace();
                                }
                                
                            }else{
                                try{
                                long result =GerenciaRotas.combinacao(n, k);
                                System.out.print("\n");
                                System.out.println("..................................");  
                                System.out.println("Combinação C(" +n + "," +k+ ") = " + result); 
                                System.out.println("..................................");    
                                }catch(IllegalArgumentException e){
                                    System.out.print("\n");
                                    System.out.println("\nArgumentos invalidos!\n");
                                    e.printStackTrace();
                                }
                                
                            }
                        }else{
                            System.out.print("\n");
                            System.out.println("\nOpção inválida, escolha 1, 2 ou 3.\n");
                        }
                        break;
                case 0:
                        rodando = false;
                        System.out.println("Saindo do programa...");
                        break;
                default:
                        System.out.println("\nOpcão inválida, digite outro número\n");
                        break;
            }
        }
        scanner.close();
    }
}
