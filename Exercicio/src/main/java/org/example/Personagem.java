package org.example;

import java.util.List;
import java.util.Random;
import java.util.Scanner;

public abstract class Personagem {
    protected int vida = 100;

    protected String nome;

    protected char letra;
    protected int forcaAtaque;

    protected int forcaDefesa;
    protected int range;
    protected Posicao posicao;
    protected boolean defesaAtiva = false;
    protected boolean poderDisponivel = false;
    //adicionar o bot e a descrição do método no documento
    private Scanner scan = new Scanner(System.in);

    public Personagem() {
    }

    protected void auxiliaAtacar(int alvo, List<Personagem> inimigosAlcance) {
        Personagem atacado;
        atacado = inimigosAlcance.get(alvo);
        if(atacado.defesaAtiva){
            atacado.vida = atacado.vida - this.forcaAtaque + atacado.forcaDefesa;
            if(!atacado.poderDisponivel){
                atacado.poderDisponivel = true;
                System.out.println(atacado.nome +  "(" + atacado.posicao.getX() + ", " + atacado.posicao.getY() + ")" + " desbloqueou seu poder!");
            }
        }else{
            atacado.vida = atacado.vida - this.forcaAtaque;
        }
        System.out.println(this.nome + "(" + this.posicao.getX() + ", " + this.posicao.getY() + ")" + " atacou " + atacado.nome + "(" + atacado.posicao.getX() + ", " + atacado.posicao.getY() + ").");
    }

    public void atacar(){
        this.defesaAtiva = false;
        int alvo;
        Personagem atacado;
        List<Personagem> inimigosAlcance = Tabuleiro.retornaPersonagens(this);
        if(inimigosAlcance.isEmpty())
            System.out.println(this.nome +  "(" + this.posicao.getX() + ", " + this.posicao.getY() + ")" + " tentou atacar mas não havia ninguém no seu range.");
        else{
            System.out.println("Inimigos no alcance de ataque:");
            for(Personagem inimigo : inimigosAlcance){
                System.out.println(inimigosAlcance.indexOf(inimigo) + " - " + inimigo.nome + "=> (" + inimigo.posicao.getX() + ", " + inimigo.posicao.getY() + ")");
            }
            System.out.println("Digite o alvo: ");
            alvo = scan.nextInt();
            auxiliaAtacar(alvo, inimigosAlcance);
        }
    }

    public void defender(){
        this.defesaAtiva = true;
        System.out.println(this.nome + "(" + this.posicao.getX() + ", " + this.posicao.getY() + ")" + " escolheu se defender.");
    }

    public void movimentar(){
        this.defesaAtiva = false;
        String movimento;
        char direc;
        int i = this.posicao.getX(),j = this.posicao.getY();
        System.out.println("Qual direção (C - Cima, B - Baixo, E - Esquerda, D - Direita)? ");
        //probleminha
        try{
            movimento = scan.nextLine().toUpperCase();
            direc = movimento.charAt(0);
        } catch(StringIndexOutOfBoundsException e){
            movimento = scan.nextLine().toUpperCase();
            direc = movimento.charAt(0);
        }
        Tabuleiro.movimentaPersonagem(this, direc);
        System.out.println(this.nome + " escolheu se movimentar " + "(" + i + ", " + j + ") => (" + posicao.getX() + ", " + posicao.getY() + ")." );
    }

    public boolean verificaMorte(){
        return this.vida <= 0;
    }

    public int getVida(){
        return this.vida;
    }

    public void setX(int x){
        posicao.setX(x);
    }
    public void setY(int y){
        posicao.setY(y);
    }
    public abstract void invocarPoder();

    public int getAtaque() {
        return this.forcaAtaque;
    }
}
