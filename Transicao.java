
import java.io.IOException;
import java.util.ArrayList;
/**
 * Classe utilizada para executar as transições 
 */

public class Transicao {

  
  private ArrayList<String> palavraEntradaAux;
  private Gravacao gravacao;
  private Maquina maquinaAux;
  private String estadoAtual,simboloLido;
  private int posicaoAtual,cabecaFita;
  private boolean verificaTransicoes;
  /**
  * Construtor recebe a Máquina de Turing construída e   
  * o URL do local onde o arquivo de gravação será salvo,
  * também inicializando as variáveis
  */
 

  Transicao(Maquina maquina ,String caminho) throws IOException {
   gravacao = new Gravacao(caminho);
    maquinaAux = maquina; 
    posicaoAtual = 0; // Copia a máquina para um objeto auxiliar para manipulação
    cabecaFita = 0; // Cabeça da fita inicia na o posição 
    verificaTransicoes = false; // Verifica se transições válidas
    palavraEntradaAux = maquinaAux.getpalavraEntrada(); // Copia a palavra de entrada para um objeto auxiliar para manipulação
  }
  
  public void fazerTransicao() throws IOException {
   /**
    * Procedimento responsável por executar as transições e avaliar exceções
    * IOExceptio gera exceção em caso de erro de acesso a informações no arquivo 
    */
    try{
      if(! verificarEntrada()){
      	/**
         * Se a função verificarEntrada() retornar false quer dizer que algum simbolo da palavra de entrada 
    	   * não faz parte do alfabeto da fita. Caso ela retorne true podemos fazer as transições sem se preocupar com tal exceção.
    	   */
        throw new ErroFitaInvalida();
      }
        estadoAtual = retornarEstado(maquinaAux.getTransicoes(), posicaoAtual); // Coleta do estado do estado atual da Máquina de Turing construída
        
        palavraEntradaAux.add(cabecaFita, estadoAtual); // Adiciona o estado atual no início da fita
        
        simboloLido = palavraEntradaAux.get(cabecaFita + 1); // Armazena a primeiro símbolo a ser lida

        chamarGravacao(palavraEntradaAux); // Grava configuração inicial da fita no arquivo de saída 
        
        cabecaFita = 1; // Incrementa a cabeça da fita para entrada no laço de repetição
        boolean aux = true; // Variável auxiliar para reconfiguração da cabeça da fita para posição inicial
          while(cabecaFita != 0){ // Laço de repetição com parada quando a cabeça da fita voltar à posição inicial
            if (aux) {
              // Condicional para setar acabeça da fita para posição inicial
              cabecaFita = 0;
              aux = false;
            }

            /**
             * Condicional para verificar se o estadual atual e a palavra lida são iguais  
             * aos parâmetros de alguma transição presente na Máquina de Turing
             */
            if(estadoAtual.equals(retornarEstado(maquinaAux.getTransicoes(), posicaoAtual)) && simboloLido.equals(maquinaAux.getTransicoes().get(posicaoAtual + 1))){

              verificaTransicoes = true; // Transição válida identificada

              if(maquinaAux.getTransicoes().get(posicaoAtual + 4).equals("R")){ // Cabeça da fita move para direita
                palavraEntradaAux.set(cabecaFita, maquinaAux.getTransicoes().get(posicaoAtual + 3)); // Realiza a operação de troca do símbolo
                palavraEntradaAux.set(cabecaFita + 1, retornarEstado(maquinaAux.getTransicoes(), posicaoAtual + 2)); // Realiza a operação de troca do estado atual e avança para direita
                cabecaFita++; // Move a cabeça da fita para direita
              }else{ // Cabeça da fita move para esquerda
                String palavraanterior = palavraEntradaAux.get(cabecaFita - 1); // Guarda o símbolo na posição anterior à cabeça da fita
                palavraEntradaAux.set(cabecaFita + 1, maquinaAux.getTransicoes().get(posicaoAtual + 3)); // Realiza a operação de troca do símbolo
                palavraEntradaAux.set(cabecaFita - 1, retornarEstado(maquinaAux.getTransicoes(), posicaoAtual + 2)); // Realiza a operação de troca do estado atual e avança para esquerda
                palavraEntradaAux.set(cabecaFita, palavraanterior); // Copia o símbolo na posição anterior à cabeça da fita para sua posição atual
                cabecaFita--; // Move a cabeça da fita para esquerda
              }
              estadoAtual = palavraEntradaAux.get(cabecaFita); // Atualiza estado atual
              if(cabecaFita < palavraEntradaAux.size() -1){ // Verifica se chegou no fim da palavra de entrada

                simboloLido = palavraEntradaAux.get(cabecaFita + 1); // Armazena a próximo símbolo a ser lida
              }else{ // Chegou ao fim da palavra de entrada

                palavraEntradaAux.add("B"); // Adiciona o símbolo B ao fim da palavra de entrada, pois a fita é infinita à direita
                simboloLido = palavraEntradaAux.get(cabecaFita + 1); // Armazena a próximo símbolo a ser lida
              }
              chamarGravacao(palavraEntradaAux); // Grava o novo estado da palavra após transição no arquivo de saída
              
            }
            /**
             * Condicional para avançar no ArrayList de transições,
             * avançando sempre para posições múltiplas de 5
             * que representam o primeiro elemento de cada transição
             */
            if(posicaoAtual < maquinaAux.getTransicoes().size() - 4){
              posicaoAtual = posicaoAtual + 5;
            }
            /**
             * Condicional que verifica se todas as transições já foram visitadas
             * Em caso positivo, reinicializa o contador posicaoAtual
             */
            if (posicaoAtual == maquinaAux.getTransicoes().size()) {
              posicaoAtual = 0;
              if (verificaTransicoes) {
                /**
                 * Verifica se transiçoes válidas e, em caso positivo, define variável verificaTransicoes como falsa para testar validade das transições
                 */
                verificaTransicoes = false;
              }else{
                /**
                 * Transição inválida identificada
                 * Lançamento de exceção para erro na transição
                 */
                throw new ErroTransicao();
              }
            }
          }
          gravacao.fecharArquivo(); // Arquivo de saída fechado

    }catch(Exception ex) {
	    System.out.println(ex.getMessage()); // Imprime mensagem de exceção
    }finally{
      // Fechamos o aruivo independente se houve uma exceção.
      gravacao.fecharArquivo();   
    }
  }
   /**
    * Função responsável por recuperar o estado de uma posição e
    * configurá-lo no formato {estadoLido}
    * @param array ArrayList de Strings
    * @param posicao Posição desejada a ser recuperada
    * @return Retorna String formatada
    */
   public String retornarEstado(ArrayList<String> array, int posicao){
   	/* Função responsável por recuperar o estado de uma posição e
   	 * configurá-lo no formato {estadoLido}
   	 * @return ArrayList com a string configurada.
   	 */
     return "{" + array.get(posicao) + "}";
   	}
   
   /**
    * Procedimento que faz a chamada para gravação da nova configuração da palavra após transição ser realizada
    * @param transicao ArrayList com palavra de entrada modificada após trnasição
    * @throws IOException IOExceptio gera exceção em caso de erro de acesso a informações no arquivo
    */  
   public void chamarGravacao(ArrayList<String> transicao) throws IOException {
    gravacao.gravar(transicao);
   }
   /**
    * Verifica se existe algum símbolo na palavra de entrada que não pertence ao alfabeto da Máquina
    * @return
    */
   public boolean verificarEntrada() {
    boolean tudoOK; // Variável de verificação
    ArrayList<String> alfabetoFita = maquinaAux.getAlfabetoFita(); // Armazena o alfabeto da fita em ArrayList auxiliar
    /**
     * Faz a verificação percorrendo cada símbolo da palavra de entrada e comparando com cada símbolo do alfabeto da fita
     */ 
    for (int i = 0; i < palavraEntradaAux.size(); i++) {
       tudoOK = false;
       for (int j = 0; j < alfabetoFita.size(); j++) {
         if (palavraEntradaAux.get(i).equals(alfabetoFita.get(j))){
          tudoOK = true;
         }
       }
       if (!tudoOK) {
         return false;
       }
     }
     return true;
   }
}
