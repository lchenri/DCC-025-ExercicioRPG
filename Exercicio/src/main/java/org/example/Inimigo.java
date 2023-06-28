package org.example;

import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Inimigo extends Personagem {
    Personagem a;
    private Random random;

    public Inimigo(Personagem a) {
        super();
        this.a = a;
        super.forcaAtaque = a.forcaAtaque;
        super.forcaDefesa = a.forcaDefesa;
        super.range = a.range;
        super.nome = a.nome;
        super.letra = a.letra;
        random = new Random();
    }

    @Override
    public void atacar() {
        this.defesaAtiva = false;
        List<Personagem> inimigosAlcance = Tabuleiro.retornaPersonagens(this);
        if (!inimigosAlcance.isEmpty()) {
            int alvo = random.nextInt(inimigosAlcance.size());
            auxiliaAtacar(alvo, inimigosAlcance);
        } else {
            System.out.println(this.nome + "(" + this.posicao.getX() + ", " + this.posicao.getY() + ")" + " tentou atacar mas não havia ninguém no seu alcance.");
        }
    }

    @Override
    public void movimentar() {
        this.defesaAtiva = false;
        int i = this.posicao.getX(),j = this.posicao.getY();
        char[] direcoes = {'C', 'B', 'E', 'D'};
        char direc = direcoes[random.nextInt(direcoes.length)];
        Tabuleiro.movimentaPersonagem(this, direc);
        System.out.println(this.nome + " escolheu se movimentar " + "(" + i + ", " + j + ") => (" + posicao.getX() + ", " + posicao.getY() + ")." );
    }

    @Override
    public void invocarPoder() {
        if(a instanceof Guerreiro){
            int forcaATK = this.forcaAtaque;
            super.forcaAtaque = super.forcaAtaque * 3;
            System.out.println(this.nome + this.letra +"(" + this.posicao.getX() + ", " + this.posicao.getY() + ")" + ": usou seu Poder!");
            atacar();
            super.forcaAtaque = forcaATK;
            super.poderDisponivel = false;
        }else if(a instanceof Mago){
            Scanner scan = new Scanner(System.in);
            int aux, alvo;
            List<Personagem> inimigosAlcance = Tabuleiro.retornaPersonagens(this);
            if(inimigosAlcance.isEmpty()){
                System.out.println(this.nome + "(" + this.posicao.getX() + ", " + this.posicao .getY() + ")" + " tentou usar seu poder mas não havia ninguém em seu alcance.");
                return;
            }
            Personagem vidaTrocadaAlvo;
            alvo = random.nextInt(inimigosAlcance.size());
            vidaTrocadaAlvo = inimigosAlcance.get(alvo);
            aux = this.vida;
            this.vida = vidaTrocadaAlvo.vida;
            vidaTrocadaAlvo.vida = aux;
            System.out.println(this.nome + "(" +this.posicao.getX() + ", " + this.posicao .getY() + ") trocou de vida com " + inimigosAlcance.get(alvo).nome + "(" + this.posicao.getX() + ", " + this.posicao .getY() + ")");
            super.poderDisponivel = false;
        }else{
            super.range = 5;
            super.poderDisponivel = false;
        }
    }
}
