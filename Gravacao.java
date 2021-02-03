import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

 //Classe utilizada para gravar no arquivo de saida os dados que passarmos.
public class Gravacao {

  private BufferedWriter arqSaida;
  /**
   * Construtor da classe
   * @param caminho Endereço do arquivo que será gravado os dados
   * @throws IOException  Tratamento caso ocorra algum problema com o arquivo
   */
  Gravacao( String caminho) throws IOException {
    arqSaida = new BufferedWriter(new FileWriter(caminho));
  }

/**
 * Função criada para gravar no arquivo
 * @param transicao ArrayList passando as informações da transição que acabou de ser feita para ser salva.
 * @throws IOException Tratamento caso ocorra algum problema com o arquivo
 */
  public void gravar (ArrayList<String> transicao) throws IOException {
    for(int i = 0;i < transicao.size(); i++){
      arqSaida.write(transicao.get(i));
    }
    arqSaida.write("\n");
   }

   /**
    * Função criada com o unico objetivo de fechar o arquivo, ela foi criada para que possamos fechar o arquivo 
      em outras classes além da Gravacao.
    * @throws IOException Tratamento caso ocorra algum problema com o arquivo
    */
   public void fecharArquivo() throws IOException {
    arqSaida.close();
   }
   
}
