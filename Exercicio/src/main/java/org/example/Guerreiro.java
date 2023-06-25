/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.example;

/**
 *
 * @author lucas
 */
public class Guerreiro extends Personagem {

    public Guerreiro() {
        super.nome = "Guerreiro(G):";
        super.letra = 'G';
        super.forcaAtaque = 25;
        super.forcaDefesa = 20;
        super.range = 1;
    }

    @Override
    public void invocarPoder() {
        int forcaATK = this.forcaAtaque;
        super.forcaAtaque = super.forcaAtaque * 3;
        System.out.println(this.nome + " usou seu Poder!");
        atacar();
        super.forcaAtaque = forcaATK;
        super.poderDisponivel = false;
    }


}
