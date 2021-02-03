import java.io.*;  
import java.util.ArrayList;
/**
 * Classe utilizada para construir a Máquina de Turing a partir de um arquivo
 */

public class Maquina{

 private File file;
 private BufferedReader arq;
 private String linha;
 private ArrayList<String> conjuntoEstados;
 private ArrayList<String> alfabetoEntrada;
 private ArrayList<String> alfabetoFita;
 private ArrayList<String> transicoes;
 private String estadoInicial;
 private ArrayList<String> palavraEntrada;

  /**
   * Construtor recebe uma String com o URL de acesso ao arquivo que será instanciado na variável file e
   * inicializa os ArrayList que receberam as informações sobre a Máquina de Turing contidas no arquivo de leitura
   * @param caminho URL do caminho para acesso ao arquivo de entrada
   * @throws FileNotFoundException Exceção para arquivo não encontrado
   */
  Maquina(String caminho) throws FileNotFoundException {
  file =  new File(caminho);
  conjuntoEstados = new ArrayList<String>();
  alfabetoEntrada = new ArrayList<String>();
  alfabetoFita = new ArrayList<String>();
  transicoes = new ArrayList<String>();
  palavraEntrada = new ArrayList<String>();
  arq = new BufferedReader( new FileReader(file)); // Cria um fluxo de entrada do leitor de buffer
} 

/**
 * Procedimento responsável por executar a manipulação a partir do arquivo de leitura 
 * @throws IOException Gera exceção em caso de erro de acesso a informações no arquivo
 */
public void executar() throws IOException {
int contador = 1; // Contador utilizado para manipular a entrada nas condicionais
linha = arq.readLine(); // Variável do tipo String onde é armazenada a linha lida do arquivo

while((linha = arq.readLine()) != null){ // Laço de repetição que garante a leitura das linhas do arquivo até seu fim

  manipulacaoString manipulador = new manipulacaoString(linha); // Instância de objeto da classe manipulacaoString para tratar as linhas lidas
    
    if ((linha.charAt(0) == '{') && (contador == 1)) {
      /* Condição para identificação da primeira linha com o conjuto de estados e  
       * setter para inserção das informações lidas e tratadas em ArrayList
       */
      setConjuntoEstados(manipulador.limparString());

    }else if ((linha.charAt(0) == '{') && (contador == 2)) {
      /* Condição para identificação da segunda linha com o alfabeto de entrada e  
       * setter para inserção das informações lidas e tratadas em ArrayList
       */
      setAlfabetoEntrada(manipulador.limparString());
      
    } else if((linha.charAt(0) == '{') && (contador == 3)){
      /* Condição para identificação da terceira linha com o alfabeto da fita e  
       * setter para inserção das informações lidas e tratadas em ArrayList
       */
      setAlfabetoFita(manipulador.limparString());
      
    }else if ((linha.charAt(0) == '{') && (contador == 4) ){
      /* Condição para identificação da quarta linha e do início da leitura das transições
       */ 
      linha = arq.readLine(); // Leitura da primera transição
      while (linha.charAt(0) != '}') {
        /* Laço de repetição com parada após leitura de todas as transições
         */ 
        manipulador = new manipulacaoString(linha); // Instância de objeto da classe manipulacaoString para tratar as linhas lidas
        setTransicoes(manipulador.limparString()); // Setter para inserção da transição lida e tratada em ArrayList
        linha = arq.readLine(); // Leitura da próxima linha
      }
    }else if ((linha.charAt(0) == '{') && (contador > 4)) {
      /* Condição para identificação de leitura do estado inicial e
       * setter para inserção das informações lidas e tratadas em ArrayList
       */
      setEstadoInicial(manipulador.limparString().get(0));
    } else if(linha.charAt(0) == 'B'){
      /* Condição para identificação de leitura da palavra de entrada e
       * setter para inserção das informações lidas e tratadas em ArrayList
       */
      setpalavraEntrada(linha);
    }
    contador++; // incremento do contador
  }         
} 
/**
 * Setters e getters das variáveis
 * @param vetor de ArrayList de String
 */
public void setConjuntoEstados(ArrayList<String> vetor) {
  this.conjuntoEstados = vetor;
}

public ArrayList<String> getConjuntoEstados() {
  return this.conjuntoEstados;
}

public void setAlfabetoEntrada(ArrayList<String> vetor) {
  this.alfabetoEntrada = vetor;
}

public ArrayList<String> getAlfabetoEntrada() {
  return this.alfabetoEntrada;
}

public void setAlfabetoFita(ArrayList<String> vetor) {
  this.alfabetoFita = vetor;
}

public ArrayList<String> getAlfabetoFita() {
  return this.alfabetoFita;
}

public void setEstadoInicial(String palavra) {
  this.estadoInicial = palavra;
}

public String getEstadoInicial() {
  return this.estadoInicial;
}

public ArrayList<String> getTransicoes() {
  return this.transicoes;
}
/**
 * Adiciona as transições lidas em sequência no ArrayList e, assim, temos uma transição a cada cinco posições
 * @param array de ArrayList de String com transição lida e formatada
 */
public void setTransicoes(ArrayList<String> array) {
  for (int i = 0; i < array.size(); i++) {
    this.transicoes.add(array.get(i));
  }
}
/**
 * Coleta cada carácter da linha lida adicionando-o ao vetor de String para então salvar no ArrayList
 * @param linha com palavra de entrada no formato String
 */
public void setpalavraEntrada(String linha) {
  String []aux = new String[linha.length()]; // Vetor de String
      for (int i = 0; i < linha.length(); i++) {
        aux[i] = Character.toString(linha.charAt(i)); // Armazena cada carácter em um vetor
        this.palavraEntrada.add(aux[i]); // Adiciona cada carácter para o ArrayList de palavra de entrada 
      }
}

public ArrayList<String> getpalavraEntrada() {
  return this.palavraEntrada;
}
}
