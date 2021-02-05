// Classe utilizada para fazer os tratamentos de excecao
 
public class TratamentoExcecao extends Exception {
 
  private String msg;
  /**
   * Construtor da classe
   * @param msg Mensagem que será exibida, ela muda dependendo da subclasse que vier.
   */
  public TratamentoExcecao(String msg){
    super(msg);
    this.msg = msg;
  }

  public String getMessage(){
    return msg;
  }
}
