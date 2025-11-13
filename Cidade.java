public class Cidade {
    private int id;
    private double x;
    private double y;

    public Cidade(int id, double x, double y){
        this.id = id;
        this.x= x;
        this.y = y;
    }

    public int getId(){
        return id;
    }

    public double getx(){
        return x;
    }

    public double gety(){
        return y;
    }

    public void setId(int id){
        this.id=id;
    }

    public void setX(double x){
        this.x =x;
    }

    public void setY(double y){
        this.y =y;
    }

    //formula da distancia euclidiana : raiz de [( (x2-x1)^2 + (y2-y1)^2 )]

    public double calcularDistanciaEuclidiana(Cidade segundo){
        double segundox = segundo.getx();
        double segundoy = segundo.gety();
        double deltax = Math.pow(this.x - segundox, 2);
        double deltay = Math.pow(this.y - segundoy, 2);

        return Math.sqrt(deltax + deltay);

    }
    
}
