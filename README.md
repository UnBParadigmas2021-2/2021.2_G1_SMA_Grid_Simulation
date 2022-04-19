# Conway's Game Of Life

**Disciplina**: FGA0210 - PARADIGMAS DE PROGRAMAÇÃO - T01 <br>
**Nro do Grupo**: 01<br>
**Paradigma**: Sistemas Multiagentes<br>

## Alunos

| Matrícula | Aluno |
| -- | -- |
| 18/0100840  | Gabriel Azevedo Batalha        |
| 18/0054082  | Marcelo Victor de Sousa Silva  |
| 17/0115208  | Victor Levi Peixoto            | 
| 17/0013812  | João Matheus de Sousa Rodrigues| 
| 16/0049733  | Felipe Borges de Souza Chaves  | 
| 15/0058462  | Davi Antônio da Silva Santos   | 

## Sobre 

Este projeto toma como base um "jogo" chamado Game of Life, que é um autômato celular desenvolvido pelo matemático John Horton Conway. Este jogo consiste em uma série de células que se reproduzem ou morrem de acordo com 4 regras.

1. Qualquer célula viva com menos de dois vizinhos vivos morre de solidão.
2. Qualquer célula viva com mais de três vizinhos vivos morre de superpopulação.
3. Qualquer célula morta com exatamente três vizinhos vivos se torna uma célula viva.
4. Qualquer célula viva com dois ou três vizinhos vivos continua no mesmo estado para a próxima geração.

## Screenshot

### Programa em Execução (25x25)

![photo_2022-04-18_14-46-30](https://user-images.githubusercontent.com/88738347/163894313-1c257813-e60f-43b9-8dbc-4cfa07942186.jpg)

### Programa em Execução (50x50)

![photo_2022-04-18_14-46-38](https://user-images.githubusercontent.com/88738347/163894010-df164bb6-386c-4228-b292-fa26b96b08cb.jpg)

## Instalação 
**Linguagens**: Java<br>
**Tecnologias**: Jade<br>

Para executar o programa é necessário um sistema JADE funcional na máquina e o JDK 15 ou superior.

Após isso utilize os seguintes argumentos:

```
  -gui -name main -agents MAIN:main("25-25")
```

sendo os parâmetros da main o x-y referente ao grid, de forma a selecionar o tamanho do grid.

Caso não queira inicializar com o GUI do JADE, é só remover o argumento
correspondente:
```
  -name main -agents MAIN:main("25-25")
```

### Observações
É necessário ter o *classpath* corretamente especificado para executar o
projeto, além da necessidade de escapar corretamente os caracteres especiais.

Em um sistema GNU/Linux, caso o JADE já esteja especificando uma variável de
ambiente, esta pode ser expandida temporariamente no terminal, por exemplo, caso
esteja no diretório do projeto:

```bash
JADE_CP="${JADE_CP}:$(pwd)"
```

Um exemplo de linha de comando escapada corretamente em Bash é

```bash
java -cp "${JADE_CP}" jade.Boot -name main -agents "MAIN:main(25-25)"
```

## Uso 

Escolha os parâmetros iniciais e visualize como as células interagem entre si, seguindo as regras já citadas do jogo.

## Vídeo

[Vídeo de apresentação](../assets/apresentacao_equipe.mp4)

[Demonstração do projeto](../assets/demonstracao_programa.mp4)

## Outros 
Caso execute o programa *sem* a GUI do JADE, será necessário encerrá-lo usando
o terminal, enviando um SIGKILL ao processo, por exemplo, usando o comando `kill`
ou SIGINT, enviado ao apertar Ctrl-C no terminal. Caso não realize essa ação,
o JADE continuará em execução e recusará iniciar o programa novamente.

## Fontes

MARTIN, Edwin. **Game of Life**. Disponível em: https://playgameoflife.com. Acesso em: 18 abr. 2022.

NUMBERPHILE. **Inventing Game of Life (John Conway)**. Disponível em: https://www.youtube.com/watch?v=R9Plq-D1gEk. Acesso em: 18 abr. 2022.

WIKIPEDIA. **Conway's Game of Life**. Disponível em: https://en.wikipedia.org/wiki/Conway's_Game_of_Life. Acesso em: 18 abr. 2022.

GEEKSFORGEEKS. **Conway’s Game Of Life (Python Implementation)**. Disponível em: https://www.geeksforgeeks.org/conways-game-life-python-implementation/. Acesso em: 18 abr. 2022.

JAVA Agent Development Framework. Disponível em: https://jade.tilab.com. Acesso em: 18 abr. 2022.
