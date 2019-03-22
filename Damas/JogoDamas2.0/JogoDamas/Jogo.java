import javax.swing.JOptionPane;

/**
 * Armazena o tabuleiro e responsavel por posicionar as pecas.
 * 
 * @author Alan Moraes &lt;alan@ci.ufpb.br&gt;
 * @author Leonardo Villeth &lt;lvilleth@cc.ci.ufpb.br&gt;
 */
public class Jogo {

    private Tabuleiro tabuleiro;
    private boolean vezJogar;
    /* Numero de peças de cada jogador,Serão iniciadas durante o jogo */
    private int nPecaBrancas;
    private int nPecaVermelhas;
    public Jogo() {
        tabuleiro = new Tabuleiro();
        criarPecas();
        vezJogar = true;
    }
    
    /**
     * Posiciona peças no tabuleiro.
     * Utilizado na inicializaçao do jogo.
     */
    private void criarPecas()
    {
        for (int y = 0; y<3; y++){
            for(int x = 0; x<8; x++){
                if((y%2!=0) && (x%2 != 0)){
                    Casa casa1 = tabuleiro.getCasa(x, y);
                    Peca peca1 = new Peca(casa1, Peca.PEDRA_BRANCA);
                    nPecaBrancas++;
                }
                else if((y%2==0) && (x%2 == 0)){
                    Casa casa1 = tabuleiro.getCasa(x, y);
                    Peca peca1 = new Peca(casa1, Peca.PEDRA_BRANCA);
                    nPecaBrancas++;
                }
            }
        }
        for (int y = 5; y<8; y++){
            for(int x = 0; x<8; x++){
                if((y%2!=0) && (x%2 != 0)){
                    Casa casa1 = tabuleiro.getCasa(x, y);
                    Peca peca1 = new Peca(casa1, Peca.PEDRA_VERMELHA);
                    nPecaVermelhas++;
                }
                else if((y%2==0) && (x%2 == 0)){
                    Casa casa1 = tabuleiro.getCasa(x, y);
                    Peca peca1 = new Peca(casa1, Peca.PEDRA_VERMELHA);
                    nPecaVermelhas++;
                }
            }
        }
    }
    /**
     * Comanda uma Peça na posicao (origemX, origemY) 
     * Fazer um movimento para (destinoX, destinoY).
     * 
     * @param origemX linha da Casa de origem.
     * @param origemY coluna da Casa de origem.
     * @param destinoX linha da Casa de destino.
     * @param destinoY coluna da Casa de destino.
     */
    public void moverPeca(int origemX, int origemY, int destinoX, int destinoY) {
        Casa origem = tabuleiro.getCasa(origemX, origemY);
        Casa destino = tabuleiro.getCasa(destinoX, destinoY);
        Peca peca = origem.getPeca();
        boolean podeIR = destino.possuiPeca();
        int tipoDePeca = peca.getTipo();
        if ((tipoDePeca == 0) && (vezJogar == true)){
            /**
             * Movimento de Jogo das peças Brancas
             */
            if (((destinoX%2 == 0) && destinoY % 2 == 0) ||((destinoX%2 != 0) && destinoY % 2 != 0)){ 
                // Permite o Movimento em Diiagonal
                if(podeIR == false){ 
                    // Só permite ir se tiver livre a casa 
                    if((destinoY == origemY+1)&&((destinoX == origemX+1)||(destinoX == origemX-1))){
                        // Movimento Simples de Peça 
                        peca.mover(destino);
                        vezJogar = false;
                    }
                    else if((destinoX == origemX+2) && (destinoY == origemY+2)){
                        // Movimento de captura de Peça em 1º Quadrante 
                        Casa verificar = tabuleiro.getCasa(origemX+1, origemY+1);
                        boolean verificaCasa = verificar.possuiPeca();
                        if (verificaCasa){
                            Peca verificarPeca = verificar.getPeca();
                            int tipoDePecaVerificada = verificarPeca.getTipo();
                            if((tipoDePecaVerificada == 2) || (tipoDePecaVerificada == 3)){
                                peca.mover(destino);
                                verificar.removerPeca();
                                nPecaVermelhas--;
                                vezJogar = false;
                            }
                        }
                    }
                    
                    else if ((destinoX == origemX-2) && (destinoY == origemY+2)){
                        // Movimento de captura de Peça em 2º Quadrante
                        Casa verificar = tabuleiro.getCasa(origemX-1, origemY+1);
                        boolean verificaCasa = verificar.possuiPeca();
                        if (verificaCasa){
                            Peca verificarPeca = verificar.getPeca();
                            int tipoDePecaVerificada = verificarPeca.getTipo();
                            if((tipoDePecaVerificada == 2) || (tipoDePecaVerificada == 3)){
                                peca.mover(destino);
                                verificar.removerPeca();
                                nPecaVermelhas--;
                                vezJogar = false;
                            }
                        }
                    }
                    
                    else if ((destinoX == origemX-2) && (destinoY == origemY-2)){
                        // Movimento de captura de Peça em 3º Quadrante 
                        Casa verificar = tabuleiro.getCasa(origemX-1, origemY-1);
                        boolean verificaCasa = verificar.possuiPeca();
                        if (verificaCasa){
                            Peca verificarPeca = verificar.getPeca();
                            int tipoDePecaVerificada = verificarPeca.getTipo();
                            if((tipoDePecaVerificada == 2) || (tipoDePecaVerificada == 3)){
                                peca.mover(destino);
                                verificar.removerPeca();
                                nPecaVermelhas--;
                                vezJogar = false;
                            }
                        }
                    }
                    
                    else if((destinoX == origemX+2) && (destinoY == origemY-2)){
                        // Movimento de captura de Peça em 4º Quadrante 
                        Casa verificar = tabuleiro.getCasa(origemX+1, origemY-1);
                        boolean verificaCasa = verificar.possuiPeca();
                        if (verificaCasa){
                            Peca verificarPeca = verificar.getPeca();
                            int tipoDePecaVerificada = verificarPeca.getTipo();
                            if((tipoDePecaVerificada == 2) || (tipoDePecaVerificada == 3)){
                                peca.mover(destino);
                                verificar.removerPeca();
                                nPecaVermelhas--;
                                vezJogar = false;
                            }
                        }
                    }
                }
            }
        }
        if ((tipoDePeca == 2) && (vezJogar == false)){
            /**
             * Movimento de Jogo das peças Vermelhas
             */
            if (((destinoX%2 == 0) && destinoY%2 == 0) ||((destinoX%2 != 0) && destinoY%2 != 0)){ 
                // Permite o Movimento em Diiagonal
                if(podeIR == false){ 
                    // Só permite ir se tiver livre a casa 
                    if((destinoY == origemY-1)&&((destinoX == origemX+1)||(destinoX == origemX-1))){
                        // Movimento Simples de Peça 
                        peca.mover(destino);
                        vezJogar = true;
                    }
                    else if((destinoX == origemX+2) && (destinoY == origemY+2)){
                        // Movimento de captura de Peça em 1º Quadrante 
                        Casa verificar = tabuleiro.getCasa(origemX+1, origemY+1);
                        boolean verificaCasa = verificar.possuiPeca();
                        if (verificaCasa){
                            Peca verificarPeca = verificar.getPeca();
                            int tipoDePecaVerificada = verificarPeca.getTipo();
                            if((tipoDePecaVerificada == 0) || (tipoDePecaVerificada == 1)){
                                peca.mover(destino);
                                verificar.removerPeca();
                                nPecaBrancas--;
                                vezJogar = true;
                            }
                        }
                    }
                    
                    else if ((destinoX == origemX-2) && (destinoY == origemY+2)){
                        // Movimento de captura de Peça em 2º Quadrante
                        Casa verificar = tabuleiro.getCasa(origemX-1, origemY+1);
                        boolean verificaCasa = verificar.possuiPeca();
                        if (verificaCasa){
                            Peca verificarPeca = verificar.getPeca();
                            int tipoDePecaVerificada = verificarPeca.getTipo();
                            if((tipoDePecaVerificada == 0) || (tipoDePecaVerificada == 1)){
                                peca.mover(destino);
                                verificar.removerPeca();
                                nPecaBrancas--;
                                vezJogar = true;
                            }
                        }
                    }
                    
                    else if ((destinoX == origemX-2) && (destinoY == origemY-2)){
                        // Movimento de captura de Peça em 3º Quadrante 
                        Casa verificar = tabuleiro.getCasa(origemX-1, origemY-1);
                        boolean verificaCasa = verificar.possuiPeca();
                        if (verificaCasa){
                            Peca verificarPeca = verificar.getPeca();
                            int tipoDePecaVerificada = verificarPeca.getTipo();
                            if((tipoDePecaVerificada == 0) || (tipoDePecaVerificada == 1)){
                                peca.mover(destino);
                                verificar.removerPeca();
                                nPecaBrancas--;
                                vezJogar = true;
                            }
                        }
                    }
                    
                    else if((destinoX == origemX+2) && (destinoY == origemY-2)){
                        // Movimento de captura de Peça em 4º Quadrante 
                        Casa verificar = tabuleiro.getCasa(origemX+1, origemY-1);
                        boolean verificaCasa = verificar.possuiPeca();
                        if (verificaCasa){
                            Peca verificarPeca = verificar.getPeca();
                            int tipoDePecaVerificada = verificarPeca.getTipo();
                            if((tipoDePecaVerificada == 0) || (tipoDePecaVerificada == 1)){
                                peca.mover(destino);
                                verificar.removerPeca();
                                nPecaBrancas--;
                                vezJogar = true;
                            }
                        }
                    }
                }
            }
        }
    }    
    /**
      * @return o Tabuleiro em jogo.
      */
    public Tabuleiro getTabuleiro() {
        return tabuleiro;
    }
}
