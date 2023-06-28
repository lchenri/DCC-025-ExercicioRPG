package org.example;

import java.util.*;

import static java.lang.System.exit;

public class Main {
    private static Tabuleiro tab = new Tabuleiro();
    private static String nome;
    private static Scanner scan = new Scanner(System.in);
    private static Personagem jogador;
    private static List<Personagem> inimigos;

    public static Personagem getJogador(){
        return jogador;
    }
    public static List<Personagem> getInimigos(){
        return inimigos;
    }
    private static void acao(int acaoEscolhida){
        switch(acaoEscolhida){
            case 1:
                jogador.atacar();
                break;
            case 2:
                jogador.defender();
                break;
            case 3:
                jogador.movimentar();
                break;
            case 4:
                jogador.invocarPoder();
                break;
            default:
                System.out.println("Acao inválida. Pulando vez.");

        }
    }

    private static void acaoAleatoriaInimigo(int acaoInimigo, Personagem bot){
        bot.poderDisponivel = true;
        if(acaoInimigo == 0){
            //bot.atacar();
            bot.invocarPoder();
        }else if(acaoInimigo == 1){
            //bot.defender();
            bot.invocarPoder();
        }else if(acaoInimigo == 2){
            //bot.movimentar();
            bot.invocarPoder();
        }else if(acaoInimigo == 3 && bot.poderDisponivel){
            bot.invocarPoder();
        }else{
            System.out.println(bot.nome +  "(" + bot.posicao.getX() + ", " + bot.posicao.getY() + ")" + " tentou usar o poder especial, mas não estava disponível");
            return;
        }
        /*switch (acaoInimigo){
            case 0:
                bot.atacar();
                break;
            case 1:
                bot.defender();
                break;
            case 2:
                bot.movimentar();
                break;
            case 3:
                bot.invocarPoder();
                break;
        }*/
    }
    public static void gameLoop(){
        int acaoEscolhida;
        Random rand = new Random();
        int acaoAleatoria;
        do{
            tab.imprimeTabuleiro();
            System.out.println("1 - Atacar\n" +
                    "2 - Defender\n" +
                    "3 - Movimentar");
            if(jogador.poderDisponivel)
                System.out.println("4 - Poder");
            System.out.println("Escolha sua ação:");
            acaoEscolhida = scan.nextInt();
            acao(acaoEscolhida);
            Iterator<Personagem> iterator = inimigos.iterator();
            while (iterator.hasNext()) {
                Personagem inimigo = iterator.next();
                if (inimigo.verificaMorte()) {
                    System.out.println(inimigo.nome + "(" + inimigo.posicao.getX() + ", " + inimigo.posicao.getY() + ")" + " morreu");
                    iterator.remove(); // Remoção segura usando o iterador
                    tab.removeMorto(inimigo);
                    continue;
                }
                acaoAleatoria = rand.nextInt(4);
                acaoAleatoriaInimigo(acaoAleatoria, inimigo);
            }

            /*for(Personagem inimigo : inimigos){
                if(inimigo.verificaMorte()){
                    System.out.println(inimigo.nome + "(" + inimigo.posicao.getX() + ", " + inimigo.posicao.getY() + ")" + " morreu");
                    inimigos.remove(inimigo);
                    tab.removeMorto(inimigo);
                    continue;
                }
                acaoAleatoria = rand.nextInt(4);
                acaoAleatoriaInimigo(acaoAleatoria, inimigo);
            }*/

        }while(!jogador.verificaMorte());

        tab.imprimeTabuleiro();
        if(!jogador.verificaMorte()){
            System.out.println(nome + " é o vencedor!");
        }else{
            System.out.println("Você morreu!");
        }
    }

    private static void determinaJogador(int escolha){
        switch (escolha){
            case 1:
                jogador = new Guerreiro();
                jogador.nome = "Player";
                jogador.letra = 'P';
                break;
            case 2:
                jogador = new Mago();
                jogador.nome = "Player";
                jogador.letra = 'P';
                break;
            case 3:
                jogador = new Arqueiro();
                jogador.nome = "Player";
                jogador.letra = 'P';
                break;
            default:
                System.out.println("Escolha inválida");
                exit(1);
        }
    }

    private static Inimigo auxGeraInimigos(){
        Random rand = new Random();
        int escolha = rand.nextInt(3) + 1;
        switch (escolha){
            case 1:
                return new Inimigo(new Guerreiro());
            case 2:
                return new Inimigo(new Mago());
            case 3:
                return new Inimigo(new Arqueiro());
            default:
                System.out.println("Erro");
                return null;
        }
    }
    private static void geraInimigos(int numeroInimigos){
        inimigos = new ArrayList<>();
        for(int i = 0; i < numeroInimigos; i++){
            inimigos.add(auxGeraInimigos());
        }
    }
    private static void inicio(){
        int numeroInimigos;
        int escolhaJogador;
        System.out.println("Bem-vindo ao RPG");
        System.out.println("Digite seu nome:");
        nome = scan.nextLine();

        System.out.println("Com qual personagem deseja jogar?\n" +
                "1 - Guerreiro\n" +
                "2 - Mago\n" +
                "3 - Arqueiro\n");
        escolhaJogador = scan.nextInt();
        determinaJogador(escolhaJogador);
        System.out.println("Digite a quantidade de inimigos:");
        numeroInimigos = scan.nextInt();
        geraInimigos(numeroInimigos);
        tab.geraPosicaoInicial(jogador, inimigos);
    }

    public static void main(String[] args) {
        inicio();
        gameLoop();
    }
}