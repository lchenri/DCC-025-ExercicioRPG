/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.example;

import java.util.List;
import java.util.Random;
import java.util.Scanner;

/**
 *
 * @author lucas
 */
public class Mago extends Personagem {

    public Mago() {
        super.nome = "Mago(M):";
        super.letra = 'M';
        super.forcaAtaque = 15;
        super.forcaDefesa = 10;
        super.range = 2;
    }

    @Override
    public void invocarPoder() {
        Scanner scan = new Scanner(System.in);
        Random rand = new Random();
        int aux, alvo;
        List<Personagem> inimigosAlcance = Tabuleiro.retornaPersonagens(this);
        if(inimigosAlcance.isEmpty()){
            System.out.println(this.nome + "(" + this.posicao.getX() + ", " + this.posicao .getY() + ")" + " tentou usar seu poder mas não havia ninguém em seu alcance.");
            return;
        }
        Personagem vidaTrocadaAlvo;

        System.out.println("Inimigos no alcance de ataque:");
        for(Personagem inimigo : inimigosAlcance){
            System.out.println(inimigosAlcance.indexOf(inimigo) + " - " + inimigo.nome + "=> (" + inimigo.posicao.getX() + ", " + inimigo.posicao.getY() + ")");
        }
        System.out.println("Digite o alvo: ");
        alvo = scan.nextInt();
        alvo = rand.nextInt(inimigosAlcance.size());
        vidaTrocadaAlvo = inimigosAlcance.get(alvo);
        aux = this.vida;
        this.vida = vidaTrocadaAlvo.vida;
        vidaTrocadaAlvo.vida = aux;
        //}
        System.out.println(this.nome + "(" +this.posicao.getX() + ", " + this.posicao .getY() + ") trocou de vida com " + inimigosAlcance.get(alvo).nome + "(" + inimigosAlcance.get(alvo).posicao.getX() + ", " + inimigosAlcance.get(alvo).posicao.getY() + ")");
        super.poderDisponivel = false;
    }

}
