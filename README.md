# Conway's Game Of Life

**Disciplina**: FGA0210 - PARADIGMAS DE PROGRAMAÇÃO - T01 <br>
**Nro do Grupo**: 01<br>
**Paradigma**: Sistemas Multiagentes<br>

## Alunos
|Matrícula | Aluno |
| -- | -- |
| 18/0100840  | Gabriel Azevedo Batalha        |
| 18/0054082  | Marcelo Victor de Sousa Silva  |
| 17/0115208  | Victor Levi Peixoto            | 
| 17/0013812  | João Matheus de Sousa Rodrigues| 
| 16/0049733  | Felipe Borges de Souza Chaves  | 
| 15/0058462  | Davi Antônio da Silva Santos   | 

## Sobre 
Este projeto toma como base um jogo que tem se tornado muito popular na internet, o WORDLE que consiste em um jogo de palavras que tem como objetivo encontrar uma palavra a partir de chutes com palavras do mesmo tamanho e dicas que o próprio jogo oferece. 

Este projeto toma como base um "jogo" chamado game of life, que é um autómato celular desenvolvido pelo matemático John Horton Conway. Este jogo consiste em uma série de células que se reproduzem ou morrem de acordo com 4 regras.

1. Qualquer célula viva com menos de dois vizinhos vivos morre de solidão.
2. Qualquer célula viva com mais de três vizinhos vivos morre de superpopulação.
3. Qualquer célula morta com exatamente três vizinhos vivos se torna uma célula viva.
4. Qualquer célula viva com dois ou três vizinhos vivos continua no mesmo estado para a próxima geração.

## Screenshots



## Instalação 
**Linguagens**: Java<br>
**Tecnologias**: Jade<br>

Para executar o programa é necessário um sistema jade funcional na máquina e o sdk 15+ do java.

Após isso utilize em usa configuração os seguintes parâmetros:

```
-gui -name main -agents MAIN:main("25-25")
```

sendo os parâmetros da main o x-y referente ao grid, de forma a selecionar o tamanho do grid.

## Uso 

Escolha os parâmetros iniciais e visualize como as células interagem entre si, seguindo as regras já citadas do jogo.

## Vídeo

## Outros 

## Fontes

MARTIN, Edwin. **Game of Life**. Disponível em: https://playgameoflife.com. Acesso em: 18 abr. 2022.

NUMBERPHILE. **Inventing Game of Life (John Conway)**. Disponível em: https://www.youtube.com/watch?v=R9Plq-D1gEk. Acesso em: 18 abr. 2022.

WIKIPEDIA. **Conway's Game of Life**. Disponível em: https://en.wikipedia.org/wiki/Conway's_Game_of_Life. Acesso em: 18 abr. 2022.

GEEKSFORGEEKS. **Conway’s Game Of Life (Python Implementation)**. Disponível em: https://www.geeksforgeeks.org/conways-game-life-python-implementation/. Acesso em: 18 abr. 2022.

JAVA Agent Development Framework. Disponível em: https://jade.tilab.com. Acesso em: 18 abr. 2022.
