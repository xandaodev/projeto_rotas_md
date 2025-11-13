public class Main {
    public static void main(String[] args){
        Cidade cidade1 = new Cidade(1, 1.0,5.0);
        Cidade cidade2 = new Cidade(3,7.0,5.0);

        double distancia  =cidade1.calcularDistanciaEuclidiana(cidade2);

        System.out.println("distancia entre a cidade 1 e a cidade 2 é " + distancia);
    }
    
}
