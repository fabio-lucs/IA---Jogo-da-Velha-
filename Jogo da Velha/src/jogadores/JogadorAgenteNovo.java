package jogadores;

import java.util.Random;

public class JogadorAgenteNovo extends Jogador {

    public JogadorAgenteNovo(String nome) {
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
        int cantoA[] = cantos[0];
        int cantoB[] = cantos[1];
        int cantoC[] = cantos[2];
        int cantoD[] = cantos[3];

        // se tabuleiro impar comparar:
        if (impar) {
            if (tabuleiro[cantoA[0]][cantoA[1]] != -1 && tabuleiro[cantoC[0]][cantoC[1]] != -1
                    && tabuleiro[centro_i][centro_j] != 1) {
                for (int i = 1; i < tabuleiro[0].length; i++) {
                    if (tabuleiro[i][i] == -1) {
                        jogada[0] = i;
                        jogada[1] = i;
                        return jogada;
                    }
                }
            }
            if (tabuleiro[cantoB[0]][cantoB[1]] != -1 && tabuleiro[cantoD[0]][cantoD[1]] != -1
                    && tabuleiro[centro_i][centro_j] != 1) {
                //lógica inversa
            }
        }

        // comparar
        // A-B | B-C | C-D | D-A
        if (tabuleiro[cantoA[0]][cantoA[1]] != -1 && tabuleiro[cantoB[0]][cantoB[1]] != -1) {
            for (int i = 1; i < tabuleiro[0].length; i++) {
                if (tabuleiro[0][i] == -1) {
                    jogada[0] = 0;
                    jogada[1] = i;
                    return jogada;
                }
            }
        }
        if (tabuleiro[cantoB[0]][cantoB[1]] != -1 && tabuleiro[cantoC[0]][cantoC[1]] != -1) {
            for (int i = 1; i < tabuleiro[0].length; i++) {
                if (tabuleiro[i][indexFim] == -1) {
                    jogada[0] = i;
                    jogada[1] = indexFim;
                    return jogada;
                }
            }
        }
        if (tabuleiro[cantoC[0]][cantoC[1]] != -1 && tabuleiro[cantoD[0]][cantoD[1]] != -1) {
            for (int i = 1; i < tabuleiro[0].length; i++) {
                if (tabuleiro[indexFim][i] == -1) {
                    jogada[0] = indexFim;
                    jogada[1] = i;
                    return jogada;
                }
            }
        }
        if (tabuleiro[cantoD[0]][cantoD[1]] != -1 && tabuleiro[cantoA[0]][cantoA[1]] != -1) {
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