import java.io.*;  

  
public class Principal {     
   public static void main(String[] args) throws IOException  { 
      /* Instancia uma maquina e passa o primeiro argumento passado na linha de comando em seu construtor
      * para que ela possa trabalhar com as informações do arquivo de entrada. 
      */
    Maquina maquina = new Maquina(args[0]);  
    maquina.executar();

   /**
    * Instancia um objeto da classe Transicao passando a maquina que foi criada(a que ja trabalhou com o arquivo de entrada) 
      e o segundo argumento passando pela linha de comando, ou seja, o endereço do arquivo final, após instanciar chama-se
      a função fazerTransicao(), ele será o responsavel por trabalhar com as transições que a maquina deve fazer. 
    */
    Transicao transicao = new Transicao(maquina,args[1]);

    transicao.fazerTransicao();
   }
}

