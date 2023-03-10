package jogadores;

import java.util.Random;

public class JogadorCavernaDoDragao extends Jogador {

    public JogadorCavernaDoDragao(String nome) {
        super(nome);
    }

    @Override
    public int[] jogar(int[][] tabuleiro) {

        int[] jogada = new int[2];
        boolean impar = tabuleiro.length % 2 != 0 ? true : false;
        int centro_i = tabuleiro.length / 2;
        int centro_j = tabuleiro[0].length / 2;
        int indexFim = tabuleiro[0].length - 1;
        int[][] cantos = { { 0, 0 }, { 0, indexFim }, { indexFim, 0 }, { indexFim, indexFim } };

        // verifica se centro está disponível para jogar
        if (tabuleiro[centro_i][centro_j] == -1) {
            jogada[0] = centro_i;
            jogada[1] = centro_j;
            return jogada;
        }

        // verifica se cantos estão disponíveis para jogar
        for (int i = 0; i < 4; i++) {

            int[] canto = cantos[i];
            if (tabuleiro[canto[0]][canto[1]] == -1) {
                jogada[0] = canto[0];
                jogada[1] = canto[1];
                return jogada;
            }
        }

        // verifica se há 2 cantos que combinam já marcados
        int cantoSupEsquerdo[] = cantos[0];
        int cantoSupDireito[] = cantos[1];
        int cantoInfEsquerdo[] = cantos[2];
        int cantoInfDireito[] = cantos[3];

        // se tabuleiro impar comparar:
        if (impar) {
            if (tabuleiro[cantoSupEsquerdo[0]][cantoSupEsquerdo[1]] != -1 && tabuleiro[cantoInfEsquerdo[0]][cantoInfEsquerdo[1]] != -1
                    && tabuleiro[centro_i][centro_j] != 1) {
                for (int i = 1; i < tabuleiro[0].length; i++) {
                    if (tabuleiro[i][i] == -1) {
                        jogada[0] = i;
                        jogada[1] = i;
                        return jogada;
                    }
                }
            }
            if (tabuleiro[cantoSupDireito[0]][cantoSupDireito[1]] != -1 && tabuleiro[cantoInfDireito[0]][cantoInfDireito[1]] != -1
                    && tabuleiro[centro_i][centro_j] != 1) {
                //lógica inversa
            }
        }

        // comparar
        // A-B | B-C | C-D | D-A
        if (tabuleiro[cantoSupEsquerdo[0]][cantoSupEsquerdo[1]] != -1 && tabuleiro[cantoSupDireito[0]][cantoSupDireito[1]] != -1) {
            for (int i = 1; i < tabuleiro[0].length; i++) {
                if (tabuleiro[0][i] == -1) {
                    jogada[0] = 0;
                    jogada[1] = i;
                    return jogada;
                }
            }
        }
        if (tabuleiro[cantoSupDireito[0]][cantoSupDireito[1]] != -1 && tabuleiro[cantoInfEsquerdo[0]][cantoInfEsquerdo[1]] != -1) {
            for (int i = 1; i < tabuleiro[0].length; i++) {
                if (tabuleiro[i][indexFim] == -1) {
                    jogada[0] = i;
                    jogada[1] = indexFim;
                    return jogada;
                }
            }
        }
        if (tabuleiro[cantoInfEsquerdo[0]][cantoInfEsquerdo[1]] != -1 && tabuleiro[cantoInfDireito[0]][cantoInfDireito[1]] != -1) {
            for (int i = 1; i < tabuleiro[0].length; i++) {
                if (tabuleiro[indexFim][i] == -1) {
                    jogada[0] = indexFim;
                    jogada[1] = i;
                    return jogada;
                }
            }
        }
        if (tabuleiro[cantoInfDireito[0]][cantoInfDireito[1]] != -1 && tabuleiro[cantoSupEsquerdo[0]][cantoSupEsquerdo[1]] != -1) {
            for (int i = 1; i < tabuleiro[0].length; i++) {
                if (tabuleiro[i][0] == -1) {
                    jogada[0] = i;
                    jogada[1] = 0;
                    return jogada;
                }
            }
        }

        // realiza jogadas aleatórias
        Random r = new Random();
        int i;
        int j;
        for (int k = 0; k < tabuleiro.length * tabuleiro.length * 10; k++) {// 1000 tentativas de jogadas válidas
            i = r.nextInt(tabuleiro.length);
            j = r.nextInt(tabuleiro.length);
            if (tabuleiro[i][j] == -1) {
                jogada[0] = i;
                jogada[1] = j;
                return jogada;
            }
        }
        return null;
    }

}
