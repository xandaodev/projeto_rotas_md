import java.io.*;
import java.util.*;

public class GerenciaRotas {
    private List<Cidade> cidades;

    public GerenciaRotas(){
        this.cidades = new ArrayList<>();
    }

    public List<Cidade> getCidades(){
        return this.cidades;
    }

    public void lerArquivoEntrada(String entrada){
        this.cidades.clear();

        // aqui tem que ter o try ctach pra evitar bo
        try(Scanner scanner = new Scanner(new File(entrada))){

            // le a primeira linha (15)
            int numCidades =scanner.nextInt();
            for(int i=1;i <= numCidades;i++){
                double x = scanner.nextDouble();
                double y = scanner.nextDouble();
                Cidade novaCidade = new Cidade(i, x, y);
                this.cidades.add(novaCidade);
            }

            System.out.println("arquivo " + entrada + " lido com sucesso. " + this.cidades.size() + " cidades carregadas.");

        }catch(FileNotFoundException e){
            System.err.println("erro, arquivo '" + entrada + "' nao encontrado.");
            e.printStackTrace();
        }
        
    }
    
}
