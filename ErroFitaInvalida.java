// Subclasse criada com o objetivo de tratar a excecao de caracteres indevidos na fita. 
public class ErroFitaInvalida extends TratamentoExcecao{
  
  private static final long serialVersionUID = 2;
 /**
   * Construtor da classe, ele não recebe nada como parametro, apenas passa a mensagem que deverá ser
   * exibida quando o erro que ela trata acontecer .
   */
  public ErroFitaInvalida() {
    super("A entrada tem caracteres que nao pertencem ao alfabeto da maquina!");
  }
}
