import java.util.ArrayList;
/**
 * Classe utilizada para trabalhar com a String
 */
public class  manipulacaoString{

private String entrada;
private String []vetorAux;
private ArrayList<String> retorno;
/**
 * Passamos uma String e criamos um vetor que será responsavel por armazenar essa String quando 
 * a mesma for trabalhada
 * @param entrada string que será trabalhada 
 */
manipulacaoString(String entrada){ 
this.entrada = entrada;
retorno = new ArrayList<String>();
}
/**
 * Retira da String todos os dados que não queremos na mesma, ou seja, dados que 
 * apenas iriam dificultar nosso trabalho nela.
 * @return  ArrayList com a string ja trabalhada.
 */
public ArrayList<String> limparString() {

  // Retirando os dados que não são desejados
  entrada = entrada.replace("{", "");
  entrada = entrada.replace("}", "");
  entrada = entrada.replace(" ", "");
  entrada = entrada.replace("->", ",");
  entrada = entrada.replace("(", "");
  entrada = entrada.replace(")", "");
  // Passamos para o vetor os dados, de forma que a parada seja atravez de cada "," achada 
  // já retirando a mesma.
  vetorAux = entrada.split(",");

  // Adicionamos os dados a ArrayList para que possamos retornar ela.
  for (int i = 0; i < vetorAux.length; i++) {
    retorno.add(vetorAux[i]);
  }

  return retorno;
 }
}
