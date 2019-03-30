
/**
 * Representa uma Pe�a do jogo.
 * Possui uma casa e um tipo associado.
 * 
 * @author Alan Moraes &lt;alan@ci.ufpb.br&gt;
 * @author Leonardo Villeth &lt;lvilleth@cc.ci.ufpb.br&gt;
 */
public class Peca{

    public static final int PEDRA_BRANCA = 0;
    public static final int DAMA_BRANCA = 1;
    public static final int PEDRA_VERMELHA = 2;
    public static final int DAMA_VERMELHA = 3;
    
    boolean jaVerificado;

    private Casa casa;
    private int tipo;

    public Peca(Casa casa, int tipo) {
        this.casa = casa;
        this.tipo = tipo;
        casa.colocarPeca(this);
        jaVerificado = false;
    }
    
    /**
     * Movimenta a peca para uma nova casa.
     * para destino nova casa que ira conter esta peca.
     */
    public void mover(Casa destino) {
        casa.removerPeca();
        destino.colocarPeca(this);
        casa = destino;
    }
    
    /**
     * Realiza o movimento simples das Peças
     */
    public void movimentoSimplesPeca(Casa origem, Casa destino, Peca peca, Jogo jogo){
        //Movimento da Pedra Branca Simples
        if(((peca.getTipo() == PEDRA_BRANCA) && (!destino.possuiPeca()) && jogo.vezJogador())&& 
           ((destino.getCasaY() == origem.getCasaY()+1) &&
            ((destino.getCasaX() == origem.getCasaX()-1) || (destino.getCasaX() == origem.getCasaX()+1)))){
                mover(destino);
                jogo.passarVez();
        }
        //Movimento da Pedra Vermelha Simples
        else if(((peca.getTipo() == PEDRA_VERMELHA) && (!destino.possuiPeca()) && !jogo.vezJogador()) && 
           ((destino.getCasaY() == origem.getCasaY()-1) &&
            ((destino.getCasaX() == origem.getCasaX()-1) || (destino.getCasaX() == origem.getCasaX()+1)))){
                mover(destino);
                jogo.passarVez();
        }
    }
    /**
     * Realiza o movimento Simples das Damas
     */
    public void movimentoSimplesDama(Casa origem, Casa destino, Peca peca, Jogo jogo){
        //Movimento da Pedra Branca Simples
        if(((peca.getTipo() == DAMA_BRANCA) && (!destino.possuiPeca()) && jogo.vezJogador())&& 
           ((destino.getCasaY() == origem.getCasaY()-1) &&
            ((destino.getCasaX() == origem.getCasaX()-1) || (destino.getCasaX() == origem.getCasaX()+1)))){
                mover(destino);
                virarDama(peca, destino);
                virarPeca(peca, destino);
                jogo.passarVez();
        }
        //Movimento da Pedra Vermelha Simples
        else if(((peca.getTipo() == DAMA_VERMELHA) && (!destino.possuiPeca()) && !jogo.vezJogador()) && 
           ((destino.getCasaY() == origem.getCasaY()+1) &&
            ((destino.getCasaX() == origem.getCasaX()-1) || (destino.getCasaX() == origem.getCasaX()+1)))){
                mover(destino);
                virarDama(peca, destino);
                virarPeca(peca, destino);
                jogo.passarVez();
        }
    }
    
    /**
     * Realiza a Captura Simples das Peças
     */
    public void capturaPeca(Casa origem, Casa destino, Peca peca, Jogo jogo){
        boolean tipoPB = (peca.getTipo() == peca.PEDRA_BRANCA);
        boolean tipoPV = (peca.getTipo() == peca.PEDRA_VERMELHA);
        boolean tipoDB = (peca.getTipo() == peca.DAMA_BRANCA);
        boolean tipoDV = (peca.getTipo() == peca.DAMA_VERMELHA);
        boolean destino1 = ((destino.getCasaY() == origem.getCasaY()+2) && ((destino.getCasaX() == origem.getCasaX()+2)));
        boolean destino2 = ((destino.getCasaY() == origem.getCasaY()+2) && ((destino.getCasaX() == origem.getCasaX()-2)));
        boolean destino3 = ((destino.getCasaY() == origem.getCasaY()-2) && ((destino.getCasaX() == origem.getCasaX()-2)));
        boolean destino4 = ((destino.getCasaY() == origem.getCasaY()-2) && ((destino.getCasaX() == origem.getCasaX()+2)));
        if(destino1){
            Casa verificar = jogo.getTabuleiro().getCasa(origem.getCasaX()+1, origem.getCasaY()+1);
            Peca objetivo = verificar.getPeca();
            if (objetivo != null){
                int tipoObjetivo = objetivo.getTipo();
                boolean objetivoB = ((objetivo.getTipo() == objetivo.PEDRA_BRANCA) || 
                                     (objetivo.getTipo() == objetivo.DAMA_BRANCA));
                boolean objetivoV = ((objetivo.getTipo() == objetivo.PEDRA_VERMELHA) || 
                                     (objetivo.getTipo() == objetivo.DAMA_VERMELHA));
                boolean podeIr = !destino.possuiPeca();
                if ((tipoPB || tipoDB) && (objetivoV) && (podeIr)){
                    verificar.removerPeca();
                    peca.mover(destino);
                    jogo.decrementaVermelhas();
                    virarDama(peca, destino);
                    virarPeca(peca, destino);
                    CapturaSeguida(peca, destino, jogo);
                    if (!jogo.podeComerSeguido()){
                        jogo.passarVez();
                    }
                }
                else if ((tipoPV || tipoDV) && (objetivoB) && (podeIr)){
                    verificar.removerPeca();
                    peca.mover(destino);
                    jogo.decrementaBrancas();
                    virarDama(peca, destino);
                    virarPeca(peca, destino);
                    CapturaSeguida(peca, destino, jogo);
                    if (!jogo.podeComerSeguido()){
                        jogo.passarVez();
                    }
                }
            }
        }
        else if(destino2){
            Casa verificar = jogo.getTabuleiro().getCasa(origem.getCasaX()-1, origem.getCasaY()+1);
            Peca objetivo = verificar.getPeca();
            if (objetivo != null){
                int tipoObjetivo = objetivo.getTipo();
                boolean objetivoB = ((objetivo.getTipo() == objetivo.PEDRA_BRANCA) || 
                                     (objetivo.getTipo() == objetivo.DAMA_BRANCA));
                boolean objetivoV = ((objetivo.getTipo() == objetivo.PEDRA_VERMELHA) || 
                                     (objetivo.getTipo() == objetivo.DAMA_VERMELHA));
                boolean podeIr = !destino.possuiPeca();
                if ((tipoPB || tipoDB) && (objetivoV) && (podeIr)){
                    verificar.removerPeca();
                    peca.mover(destino);
                    jogo.decrementaVermelhas();
                    virarDama(peca, destino);
                    virarPeca(peca, destino);
                    CapturaSeguida(peca, destino, jogo);
                    if (!jogo.podeComerSeguido()){
                        jogo.passarVez();
                    }
                }
                else if ((tipoPV || tipoDV) && (objetivoB) && (podeIr)){
                    verificar.removerPeca();
                    peca.mover(destino);
                    jogo.decrementaBrancas();
                    virarDama(peca, destino);
                    virarPeca(peca, destino);
                    CapturaSeguida(peca, destino, jogo);
                    if (!jogo.podeComerSeguido()){
                        jogo.passarVez();
                    }
                }
            }
        }
        else if(destino3){
            Casa verificar = jogo.getTabuleiro().getCasa(origem.getCasaX()-1, origem.getCasaY()-1);
            Peca objetivo = verificar.getPeca();
            if (objetivo != null){
                int tipoObjetivo = objetivo.getTipo();
                boolean objetivoB = ((objetivo.getTipo() == objetivo.PEDRA_BRANCA) || 
                                     (objetivo.getTipo() == objetivo.DAMA_BRANCA));
                boolean objetivoV = ((objetivo.getTipo() == objetivo.PEDRA_VERMELHA) || 
                                     (objetivo.getTipo() == objetivo.DAMA_VERMELHA));
                boolean podeIr = !destino.possuiPeca();
                if ((tipoPB || tipoDB) && (objetivoV) && (podeIr)){
                    verificar.removerPeca();
                    peca.mover(destino);
                    jogo.decrementaVermelhas();
                    virarDama(peca, destino);
                    virarPeca(peca, destino);
                    CapturaSeguida(peca, destino, jogo);
                    if (!jogo.podeComerSeguido()){
                        jogo.passarVez();
                    }
                }
                else if ((tipoPV || tipoDV) && (objetivoB)&& (podeIr)){
                    verificar.removerPeca();
                    peca.mover(destino);
                    jogo.decrementaBrancas();
                    virarDama(peca, destino);
                    virarPeca(peca, destino);
                    CapturaSeguida(peca, destino, jogo);
                    if (!jogo.podeComerSeguido()){
                        jogo.passarVez();
                    }
                }
            }
        }
        else if(destino4){
            Casa verificar = jogo.getTabuleiro().getCasa(origem.getCasaX()+1, origem.getCasaY()-1);
            Peca objetivo = verificar.getPeca();
            if (objetivo != null){
                int tipoObjetivo = objetivo.getTipo();
                boolean objetivoB = ((objetivo.getTipo() == objetivo.PEDRA_BRANCA) || 
                                     (objetivo.getTipo() == objetivo.DAMA_BRANCA));
                boolean objetivoV = ((objetivo.getTipo() == objetivo.PEDRA_VERMELHA) || 
                                     (objetivo.getTipo() == objetivo.DAMA_VERMELHA));
                boolean podeIr = !destino.possuiPeca();
                if ((tipoPB || tipoDB) && (objetivoV) && (podeIr)){
                    verificar.removerPeca();
                    peca.mover(destino);
                    jogo.decrementaVermelhas();
                    virarDama(peca, destino);
                    virarPeca(peca, destino);
                    CapturaSeguida(peca, destino, jogo);
                    if (!jogo.podeComerSeguido()){
                        jogo.passarVez();
                    }
                }
                else if ((tipoPV || tipoDV) && (objetivoB)&& (podeIr)){
                    verificar.removerPeca();
                    peca.mover(destino);
                    jogo.decrementaBrancas();
                    virarDama(peca, destino);
                    virarPeca(peca, destino);
                    CapturaSeguida(peca, destino, jogo);
                    if (!jogo.podeComerSeguido()){
                        jogo.passarVez();
                    }
                }
            }
        }
    }
    
    /**
     * Valor    Tipo
     *   0   Branca (Pedra)
     *   1   Branca (Dama)
     *   2   Vermelha (Pedra)
     *   3   Vermelha (Dama)
     * @return o tipo da peca.
     */
    public int getTipo() {
        return tipo;
    }
    
    /**
     * Transforma a Pedra em Dama
     */
    public void virarDama(Peca peca, Casa casa){
        boolean tipoPB = (peca.getTipo() == PEDRA_BRANCA);
        boolean tipoPV = (peca.getTipo() == PEDRA_VERMELHA);
        
        if((tipoPB) && (casa.getCasaY() == 7)){
            tipo = DAMA_BRANCA;
        }
        if((tipoPV) && (casa.getCasaY() == 0)){
            tipo = DAMA_VERMELHA;
        }
    }
    
    /**
     * Transforma a Dama em Pedra
     */
    public void virarPeca(Peca peca, Casa casa){
        boolean tipoDB = (peca.getTipo() == DAMA_BRANCA);
        boolean tipoDV = (peca.getTipo() == DAMA_VERMELHA);
        
        if((tipoDB) && (casa.getCasaY() == 0)){
            tipo = PEDRA_BRANCA;
        }
        if((tipoDV) && (casa.getCasaY() == 7)){
            tipo = PEDRA_VERMELHA;
        }
    }
    
    /**
     * Realiza a Captura Seguida de Pecas
     */
    public void CapturaSeguida(Peca peca, Casa atual, Jogo jogo){
        boolean tipoB = ((peca.getTipo() == peca.PEDRA_BRANCA) || (peca.getTipo() == peca.DAMA_BRANCA));
        boolean tipoV = ((peca.getTipo() == peca.PEDRA_VERMELHA) || (peca.getTipo() == peca.DAMA_VERMELHA));

        Casa verificar1 = jogo.getTabuleiro().getCasa(atual.getCasaX()+1, atual.getCasaY()+1);
        Casa verificar2 = jogo.getTabuleiro().getCasa(atual.getCasaX()-1, atual.getCasaY()+1);
        Casa verificar3 = jogo.getTabuleiro().getCasa(atual.getCasaX()-1, atual.getCasaY()-1);
        Casa verificar4 = jogo.getTabuleiro().getCasa(atual.getCasaX()+1, atual.getCasaY()-1);
        
        if(verificar1 != null){
            boolean possuiPeca = verificar1.possuiPeca();
            if (possuiPeca){
                Peca objetivo = verificar1.getPeca();
                int tipoObjetivo = objetivo.getTipo();
                boolean objetivoB = ((objetivo.getTipo() == objetivo.PEDRA_BRANCA) || 
                                     (objetivo.getTipo() == objetivo.DAMA_BRANCA));
                boolean objetivoV = ((objetivo.getTipo() == objetivo.PEDRA_VERMELHA) || 
                                     (objetivo.getTipo() == objetivo.DAMA_VERMELHA));
                
                Casa destino = jogo.getTabuleiro().getCasa(atual.getCasaX()+2, atual.getCasaY()+2);
                if(destino != null){    
                    boolean fim = destino.possuiPeca();
                    if (!fim){
                        if(objetivoV && tipoB){
                            jogo.alteraComerSeguido(true, peca);
                            jaVerificado = true;
                        }
                        else if (objetivoB && tipoV){
                            jogo.alteraComerSeguido(true, peca);
                            jaVerificado = true;
                        }
                    }
                }
                else if(!jaVerificado){
                    jogo.alteraComerSeguido(false, null);
                }
            }
            else if(!jaVerificado){
                jogo.alteraComerSeguido(false, null);
            }
        }
        if(verificar2 != null){
            boolean possuiPeca = verificar2.possuiPeca();
            if (possuiPeca){
                Peca objetivo = verificar2.getPeca();
                int tipoObjetivo = objetivo.getTipo();
                boolean objetivoB = ((objetivo.getTipo() == objetivo.PEDRA_BRANCA) || 
                                     (objetivo.getTipo() == objetivo.DAMA_BRANCA));
                boolean objetivoV = ((objetivo.getTipo() == objetivo.PEDRA_VERMELHA) || 
                                     (objetivo.getTipo() == objetivo.DAMA_VERMELHA));
                
                Casa destino = jogo.getTabuleiro().getCasa(atual.getCasaX()-2, atual.getCasaY()+2);
                if(destino != null){    
                    boolean fim = destino.possuiPeca();
                    if (!fim){
                        if(objetivoV && tipoB){
                            jogo.alteraComerSeguido(true, peca);
                            jaVerificado = true;
                        }
                        else if (objetivoB && tipoV){
                            jogo.alteraComerSeguido(true, peca);
                            jaVerificado = true;
                        }
                    }
                }
                else if(!jaVerificado){
                    jogo.alteraComerSeguido(false, null);
                }
            }
            else if(!jaVerificado){
                jogo.alteraComerSeguido(false, null);
            }
        }
        if(verificar3 != null){
            boolean possuiPeca = verificar3.possuiPeca();
            if (possuiPeca){
                Peca objetivo = verificar3.getPeca();
                int tipoObjetivo = objetivo.getTipo();
                boolean objetivoB = ((objetivo.getTipo() == objetivo.PEDRA_BRANCA) || 
                                     (objetivo.getTipo() == objetivo.DAMA_BRANCA));
                boolean objetivoV = ((objetivo.getTipo() == objetivo.PEDRA_VERMELHA) || 
                                     (objetivo.getTipo() == objetivo.DAMA_VERMELHA));
                
                Casa destino = jogo.getTabuleiro().getCasa(atual.getCasaX()-2, atual.getCasaY()-2);
                if(destino != null){    
                    boolean fim = destino.possuiPeca();
                    if (!fim){
                        if(objetivoV && tipoB){
                            jogo.alteraComerSeguido(true, peca);
                            jaVerificado = true;
                        }
                        else if (objetivoB && tipoV){
                            jogo.alteraComerSeguido(true, peca);
                            jaVerificado = true;
                        }
                    }
                }
                else if(!jaVerificado){
                    jogo.alteraComerSeguido(false, null);
                }
            }
            else if(!jaVerificado){
                jogo.alteraComerSeguido(false, null);
            }
        }
        if(verificar4 != null){
            boolean possuiPeca = verificar4.possuiPeca();
            if (possuiPeca){
                Peca objetivo = verificar4.getPeca();
                int tipoObjetivo = objetivo.getTipo();
                boolean objetivoB = ((objetivo.getTipo() == objetivo.PEDRA_BRANCA) || 
                                     (objetivo.getTipo() == objetivo.DAMA_BRANCA));
                boolean objetivoV = ((objetivo.getTipo() == objetivo.PEDRA_VERMELHA) || 
                                     (objetivo.getTipo() == objetivo.DAMA_VERMELHA));
                
                Casa destino = jogo.getTabuleiro().getCasa(atual.getCasaX()+2, atual.getCasaY()-2);
                if(destino != null){    
                    boolean fim = destino.possuiPeca();
                    if (!fim){
                        if(objetivoV && tipoB){
                            jogo.alteraComerSeguido(true, peca);
                            jaVerificado = true;
                        }
                        else if (objetivoB && tipoV){
                            jogo.alteraComerSeguido(true, peca);
                            jaVerificado = true;
                        }
                    }
                }
                else if(!jaVerificado){
                    jogo.alteraComerSeguido(false, null);
                }
            }
        }
        if((verificar1 == null) && (verificar2 == null) && (verificar3 == null) && (verificar4 == null) && (!jaVerificado)){
            jogo.alteraComerSeguido(false, null);
        }
        jaVerificado = false;
    }
}
