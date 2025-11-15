package projeto_rotas_md.src;

import java.util.*;

public class Excecoes extends Exception{
    
    public Excecoes(String mensagem){   //apenas mensagem personalizada (p/o usuário)
        super(mensagem);
    }

    public Excecoes(String mensagem, Throwable causa){     //mensagem personalizada + causa original do problema
        super(mensagem, causa);
    }
}
