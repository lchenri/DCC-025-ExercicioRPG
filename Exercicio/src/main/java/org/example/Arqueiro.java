/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.example;

/**
 *
 * @author lucas
 */
public class Arqueiro extends Personagem {

    public Arqueiro() {
        super.nome = "Arqueiro(A):";
        super.letra = 'A';
        super.forcaAtaque = 10;
        super.forcaDefesa = 5;
        super.range = 4;
    }

    @Override
    public void invocarPoder() {
        super.range = 5;
        super.poderDisponivel = false;
    }

}
