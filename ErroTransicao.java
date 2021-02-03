// Subclasse criada com o objetivo de tratar a excecao de qualquer erro possivel na transição.
public class ErroTransicao extends TratamentoExcecao{
  
  // UID do erro
  private static final long serialVersionUID = 1;

  /**
   * Construtor da classe, ele não recebe nada como parametro, apenas passa a mensagem que deverá ser
   * exibida quando o erro que ela trata acontecer .
   */
  public ErroTransicao() {
    super("A Maquina parou pois nao existe uma transicao valida!");
  }
}
