package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;



public class Tabuleiro {
    private static Personagem tab[][] = new Personagem[10][10];
    public Tabuleiro() {
        preencheTab();
    }

    private void preencheTab(){
        for(int i = 0; i < 10; i++){
            for(int j = 0; j < 10; j++){
                tab[i][j] = null;
            }
        }
    }

    private int auxGeraNumero(){
        Random rand = new Random();
        return rand.nextInt(10);
    }
    //adicionar no documento -- adicionado
    public void geraPosicaoInicial(Personagem jogador, List<Personagem> inimigos){
        Random rand = new Random();
        int i = auxGeraNumero();
        int j = auxGeraNumero();
        tab[i][j] = jogador;
        jogador.posicao = new Posicao(i,j);
        for(Personagem p : inimigos){
            i = auxGeraNumero();
            j = auxGeraNumero();
            if (tab[i][j] != null) {
                while (tab[i][j] != null) {
                    i = auxGeraNumero();
                    j = auxGeraNumero();
                }
            }
            tab[i][j] = p;
            p.posicao = new Posicao(i,j);
        }
    }

    public void imprimeTabuleiro(){
        Personagem jogador = Main.getJogador();
        List<Personagem> inimigos = Main.getInimigos();
        for(int i = 0; i < 10; i++){
            for(int j = 0; j < 10; j++){
                if(tab[i][j] == null){
                    System.out.print('.' + "   ");
                }else{
                    if(tab[i][j] == jogador)
                        System.out.print('P' + "   ");
                    else
                        System.out.print(tab[i][j].letra + "   ");
                }
            }
            System.out.println();
        }
        for(int i = 0; i < 10; i++){
            for(int j = 0; j < 10; j++){
                if(tab[i][j] == jogador){
                    System.out.println("Player(" + 'P' + "): " + "( " + i + ", " + j + " ) => Vida: " + jogador.getVida());
                }
            }
        }

        for(int i = 0; i < 10; i++){
            for(int j = 0; j < 10; j++){
                for(Personagem inimigo : inimigos){
                    if(tab[i][j] == inimigo){
                        System.out.println(inimigo.nome + "( " + i + ", " + j + " ) => Vida: " + inimigo.getVida());
                    }
                }
            }
        }
    }

    public static void mensagemErro(Personagem p){
        if(p == Main.getJogador()){
            System.out.println("Player(P):" +"(" + p.posicao.getX() + ", " + p.posicao.getY() + ")" + "tentou se mover para fora do tabuleiro");
        }else{
            System.out.println(p.nome + "(" + p.posicao.getX() + ", " + p.posicao.getY() + ")"+"tentou se mover para fora do tabuleiro");
        }
    }

    //-- Opcional
    public static void movimentaPersonagem(Personagem p, char direcao){
        int lin = 0,col = 0;
        for(int i = 0; i < 10; i++){
            for(int j = 0; j < 10; j++){
                if(tab[i][j] == p){
                    lin = i;
                    col = j;
                    break;
                }
            }
        }
        switch(direcao){
            case 'C':
                if(lin - 1 < 0){
                    mensagemErro(p);
                    break;
                }
                if(tab[lin - 1][col] == null){
                    p.posicao.setX(p.posicao.getX() - 1);
                    tab[lin - 1][col] = p;
                    tab[lin][col] = null;
                }
                break;
            case 'B':
                if(lin + 1 > 9){
                    mensagemErro(p);
                    break;
                }
                if(tab[lin + 1][col] == null){
                    p.posicao.setX(p.posicao.getX() + 1);
                    tab[lin + 1][col] = p;
                    tab[lin][col] = null;
                }
                break;
            case 'D':
                if(col + 1 > 9){
                    mensagemErro(p);
                    break;
                }
                if(tab[lin][col + 1] == null){
                    p.posicao.setY(p.posicao.getY() + 1);
                    tab[lin][col + 1] = p;
                    tab[lin][col] = null;
                }
                break;
            case 'E':
                if(col - 1 < 0){
                    mensagemErro(p);
                    break;
                }
                if(tab[lin][col - 1] == null){
                    p.posicao.setY(p.posicao.getY() - 1);
                    tab[lin][col - 1] = p;
                    tab[lin][col] = null;
                }
        }
    }

    public static List<Personagem> retornaPersonagens(Personagem p){
        List<Personagem> inimigosNoAlcance = new ArrayList<>();
        int range = p.range;
        //verifica inimigos na Linha da posição atual do personagem +/- alcance
        //para cima
        for(int i = p.posicao.getX(); i >= p.posicao.getX() - range; i--){
            if(i < 0)
                break;
            if(tab[i][p.posicao.getY()] != null && tab[i][p.posicao.getY()] != p)
                inimigosNoAlcance.add(tab[i][p.posicao.getY()]);
        }
        //para baixo
        for(int i = p.posicao.getX(); i <= p.posicao.getX() + range; i++){
            if(i > 9)
                break;
            if(tab[i][p.posicao.getY()] != null && tab[i][p.posicao.getY()] != p)
                inimigosNoAlcance.add(tab[i][p.posicao.getY()]);
        }

        //para esquerda
        for(int i = p.posicao.getY(); i >= p.posicao.getY() - range; i--){
            if(i < 0)
                break;
            if(tab[p.posicao.getX()][i] != null && tab[p.posicao.getX()][i] != p)
                inimigosNoAlcance.add(tab[p.posicao.getX()][i]);
        }

        //para direita
        for(int i = p.posicao.getY(); i <= p.posicao.getY() + range; i++){
            if(i > 9)
                break;
            if(tab[p.posicao.getX()][i] != null && tab[p.posicao.getX()][i] != p)
                inimigosNoAlcance.add(tab[p.posicao.getX()][i]);
        }

        return inimigosNoAlcance;
    }

    public void removeMorto(Personagem inimigo) {
        tab[inimigo.posicao.getX()][inimigo.posicao.getY()] = null;
    }
}
