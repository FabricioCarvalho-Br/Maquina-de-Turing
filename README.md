# MaquinaTuring
Esse é um projeto onde o objetivo é criar um programa que simule a execução de uma máquina de turing. Esse projeto foi desenvolvido por mim e pelo usuário matheusrsp91.

**Para que esse programa funcione de maneira adequada precisamos seguir os seguintes cuidados**:

**1º:** O endereço do arquivo de entrada (o que contém toda a informação da máquina) e o endereço do arquivo de saída **devem ser informados na 
linha de comando na sequência descrita e o arquivo de entrada tem necessariamente que ser formato txt, além disso, a compilação deve ocorrer chamando a classe Principal.java**.

Os comando para que você execute o programa atravez do terminal são:

      javac Principal.java 
      java Principal arg1 arg2
      
Onde **arg1** é o endereço do arquivo de entrada e **arg2** o endereço de onde será gerado o arquivo de saida.

**2º:** O formato do arquivo deve seguir o exemplo disponibilizado no link a seguir: 

https://docs.google.com/document/d/e/2PACX-1vR6DJM7bt2cdytZSV_J7fsd4gIbfvAa2sLUHVH2WHD4gQHiVZAXP6knCqeZ014GxklvcKrmWPvWdlIm/pub

# Informações Gerais
Foram disponibilizados alguns arquivos no formato txt para teste, são eles:

**copiadora.txt**: Ela duplica a entrada, ou seja, se colocarmos como entrada B111 ela nos devolverá os passos até termos B111B111 ~~(essa exemplo é o usado no arquivo disponibilizado no link acima~~)

**predecessor.txt**: Essa máquina subtrai 1 em binário, ou seja, ao entregarmos B1111, ela nos devolverá B111

**soma.txt**: Essa maquina soma os dois valores binários inseridos, **lembre-se de separar os dois valores por um B(Branco)**

**somaErro1.txt**: Essa máquina está criada para testarmos se o código detecta erros na palavra de entrada

**somaErro2.txt**: Essa máquina está criada para testarmos se o código detecta erros nas transições

