/**
 * Importando Bibliotecas em Java
 */
import java.lang.Math;

/**
 * Representa uma Pe�a do jogo.
 * Possui uma casa e um tipo associado.
 * 
 * @author Alan Moraes &lt;alan@ci.ufpb.br&gt;
 * @author Leonardo Villeth &lt;lvilleth@cc.ci.ufpb.br&gt;
 */
public class Peca {

    public static final int PEDRA_BRANCA = 0;
    public static final int DAMA_BRANCA = 1;
    public static final int PEDRA_VERMELHA = 2;
    public static final int DAMA_VERMELHA = 3;
    
    //Serão iniciadas durante o Jogo
    public static boolean Branca;
    public static boolean Vermelha;

    private Casa casa;
    private int tipo;
    private int direcao;
    private boolean pecaRealizouCaptura;

    public Peca(Casa casa, int tipo) {
        this.casa = casa;
        this.tipo = tipo;
        casa.colocarPeca(this);
        definirDirecao();
        pecaRealizouCaptura = false;
    }
    
    /**
     * Define a Direçao da Peca
     */
    private void definirDirecao(){
        if ((tipo == PEDRA_BRANCA) || (tipo == DAMA_VERMELHA)){
            direcao = 1;
        }
        else  if ((tipo == PEDRA_VERMELHA) || (tipo == DAMA_BRANCA)){
            direcao = -1;
        }
    }
    
    /**
     * Transforma a Pedra em Dama
     */
    public void virarDama(Casa casa){
        boolean tipoPB = (tipo == PEDRA_BRANCA);      // Verifica se é Pedra Branca
        boolean tipoPV = (tipo == PEDRA_VERMELHA);    // Verifica se é Pedra Vermelha
        
        if((tipoPB) && (casa.getCasaY() == 7)){
            tipo = DAMA_BRANCA;                                 // Transforma em Dama Branca
        }
        else if((tipoPV) && (casa.getCasaY() == 0)){
            tipo = DAMA_VERMELHA;                               // Transforma em Dama Vermelha
        }
        definirDirecao();
    }
    
    /**
     * Transforma a Dama em Pedra
     */
    public void virarPeca(Casa casa){
        boolean tipoDB = (tipo == DAMA_BRANCA);       // Verifica se é Dama Branca
        boolean tipoDV = (tipo == DAMA_VERMELHA);     // Verifica se é Dama Vermelha
        
        if((tipoDB) && (casa.getCasaY() == 0)){
            tipo = PEDRA_BRANCA;                                // Transforma em Pedra Branca
        }
        else if((tipoDV) && (casa.getCasaY() == 7)){
            tipo = PEDRA_VERMELHA;                              // Transforma em Pedra Vermelha
        }
        definirDirecao();
    }
    
    /**
     * Movimenta a peca para uma nova casa.
     * @param destino nova casa que ira conter esta peca.
     */
    public boolean mover(Casa destino, Jogo jogo) {
        boolean moveu = false;
        if(!pecaRealizouCaptura){
            moveu = movimentoSimples(destino, jogo);
        }
        if(!moveu || pecaRealizouCaptura){    
            moveu = Captura(destino, jogo);
        }
        if(moveu){
            virarDama(destino);
            virarPeca(destino);
        }
        return moveu;
    }
    
    /**
     * Movimenta a peca para uma nova casa.
     * @param destino nova casa que ira conter esta peca.
     */
    private void moveInterno (Casa destino) {
        casa.removerPeca();
        destino.colocarPeca(this);
        casa = destino;
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
     * Realiza o movimento simples das Peças
     */
    public boolean movimentoSimples(Casa destino, Jogo jogo){
        //Movimento da Pedra Branca Simples
        Branca = ((tipo == PEDRA_BRANCA) || (tipo == DAMA_BRANCA));
        Vermelha = ((tipo == PEDRA_VERMELHA) || (tipo == DAMA_VERMELHA));
        boolean mover = false;
        if ((destino.getCasaY() == casa.getCasaY() + direcao) && !destino.possuiPeca()){
            if(Math.abs(destino.getCasaX() - casa.getCasaX()) == 1){
                moveInterno(destino);
                mover = true;
            }
        }
        if (mover){
            return true;
        }
        else{
            return false;
        }
    }
    
    /**
     * Movimentação de Captura de Peça 
     */
    public boolean Captura(Casa destino, Jogo jogo){
        Branca = ((tipo == PEDRA_BRANCA) || (tipo == DAMA_BRANCA));
        Vermelha = ((tipo == PEDRA_VERMELHA) || (tipo == DAMA_VERMELHA));
        
        boolean mover = false;
        Casa casaAlvo = jogo.getTabuleiro().getCasa(((casa.getCasaX()+destino.getCasaX())/2), ((casa.getCasaY()+destino.getCasaY())/2));
        if ((!destino.possuiPeca() && casaAlvo.possuiPeca()) &&
            ((Math.abs(destino.getCasaX() - casa.getCasaX()) == 2) &&
             (Math.abs(destino.getCasaY() - casa.getCasaY()) == 2))){
            int tipoAlvo = casaAlvo.getPeca().getTipo();
            boolean alvoBranca = ((tipoAlvo == PEDRA_BRANCA) || (tipoAlvo == DAMA_BRANCA));
            boolean alvoVermelha = ((tipoAlvo == PEDRA_VERMELHA) || (tipoAlvo == DAMA_VERMELHA));
            if (Branca && alvoVermelha){
                moveInterno(destino);
                casaAlvo.removerPeca();
                jogo.decrementaVermelhas();
                mover = true;
                pecaRealizouCaptura = true;
            }
            else if (Vermelha && alvoBranca){
                moveInterno(destino);
                casaAlvo.removerPeca();
                jogo.decrementaBrancas();
                mover = true;
                pecaRealizouCaptura = true;
            }
        }
        
        if (mover){
            return true;
        }
        else{
            return false;
        }
    }
    
    /**
     * Movimentação de Captura de Peça 
     */
    public boolean CapturaSeguida(Casa destino, Jogo jogo){
        Branca = ((tipo == PEDRA_BRANCA) || (tipo == DAMA_BRANCA));
        Vermelha = ((tipo == PEDRA_VERMELHA) || (tipo == DAMA_VERMELHA));
        
        boolean mover = false;
        Casa casaAlvo = jogo.getTabuleiro().getCasa(((casa.getCasaX()+destino.getCasaX())/2), ((casa.getCasaY()+destino.getCasaY())/2));
        if (!destino.possuiPeca() && casaAlvo.possuiPeca()){
            int tipoAlvo = casaAlvo.getPeca().getTipo();
            boolean alvoBranca = ((tipoAlvo == PEDRA_BRANCA) || (tipoAlvo == DAMA_BRANCA));
            boolean alvoVermelha = ((tipoAlvo == PEDRA_VERMELHA) || (tipoAlvo == DAMA_VERMELHA));
            if (Branca && alvoVermelha){
                mover = true;
            }
            else if (Vermelha && alvoBranca){
                mover = true;
            }
        }
        
        if (mover){
            return true;
        }
        else{
            return false;
        }
    }
    
    /**
     * Informa se a Peca realizou Captura
     */
    public boolean pecaCapturou(){
        return pecaRealizouCaptura;
    }
    
    /**
     * Peca Realizou Captura Volta ao normal
     */
    
    public void setCaptura(){
        pecaRealizouCaptura = false;
    }
    /**
     * Verifica o tipo de Peça a se Jogar
     */
    public boolean tipoPeca (){
        Branca = ((tipo == PEDRA_BRANCA) || (tipo == DAMA_BRANCA));
        Vermelha = ((tipo == PEDRA_VERMELHA) || (tipo == DAMA_VERMELHA));
        if(Branca){
            return true;
        }
        else{
            return false;
        }
    }
}
