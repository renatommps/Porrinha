package logica;

import java.util.ArrayList;

public class EstadoPorrinha implements EstadoArvore {

    private ArrayList<ArrayList<Integer>> maoAposta;
    private int jogados;
    private int jogadorAtual;
    private int maxJogadores;

    public EstadoPorrinha(ArrayList<ArrayList<Integer>> maoAposta, int jogados, int jogadorAtual, int maxJogadores) {
        this.maoAposta = maoAposta;
        this.jogados = jogados;
        this.jogadorAtual = jogadorAtual;
        this.maxJogadores = maxJogadores;
        System.out.println(maoAposta);
    }

    @Override
    public ArrayList<EstadoArvore> transicoes() {
        //Novo estado calculado
        ArrayList<EstadoArvore> novosEstados = new ArrayList<EstadoArvore>();
        //Calcula proximo jogador
        int proxJogador = (jogadorAtual + 1) % maxJogadores;
        int pedrasNoJogo = 0;
        //Calculas quantas pedras há no jogo
        for (ArrayList<Integer> jogador : maoAposta) {
            pedrasNoJogo += jogador.get(0);
        }

        for (int i = 0; i <= pedrasNoJogo; ++i) {
            //Se ninguem apostou a mesma aposta
            if (apostaUnica(jogadorAtual, i)) {
                //Copia a mao de aposta atual
                ArrayList<ArrayList<Integer>> novaMaoAposta = new ArrayList<ArrayList<Integer>>(maoAposta);
                //Pega a aposta velha do jogador atual
                ArrayList<Integer> apostaJogadorAtual = novaMaoAposta.get(jogadorAtual);
                //Cria uma nova aposta, com i pedras
                apostaJogadorAtual.set(1, i);
                //Atualiza a aposta do jogadorAtual com a apostaJogadorAtual
                novaMaoAposta.set(jogadorAtual, apostaJogadorAtual);
                //Adiciona o novo estado ao novo nivel da arvore.
                novosEstados.add(new EstadoPorrinha(novaMaoAposta, jogados, proxJogador, maxJogadores));
            }
        }
        return novosEstados;
    }

    public int numJogadores() {
        return maxJogadores;
    }

    @Override
    public boolean isFinal() {
        for (ArrayList<Integer> jogador : maoAposta) {
            if (jogador.get(1) == -1) {
                return false;
            }
        }
        return true;
    }

    @Override
    public ArrayList<Double> visit(Euristica euristica) {
        return euristica.accept(this);
    }

    public ArrayList<ArrayList<Integer>> getMaoAposta() {
        return maoAposta;
    }

    public int jogadosPeloAtual() {
        return jogados;
    }

    public int getJogadorAtual() {
        return jogadorAtual;
    }

    //Verifica se algum jogador anterior ao atual já apostou a mesma aposta(Não permissivel)
    private boolean apostaUnica(int curIndex, int value) {
        for (int i = 0; i < curIndex; ++i) {
            if (maoAposta.get(i).get(1) == value) {
                return false;
            }
        }
        return true;
    }

}
