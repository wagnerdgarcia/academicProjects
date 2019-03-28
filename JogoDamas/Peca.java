
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
    
    /* Numero de peças de cada jogador,Serão iniciadas durante o jogo */
    public int nPecaBrancas;
    public int nPecaVermelhas;

    private Casa casa;
    private int tipo;

    public Peca(Casa casa, int tipo) {
        this.casa = casa;
        this.tipo = tipo;
        casa.colocarPeca(this);
    }
    
    /**
     * Movimenta a peca para uma nova casa.
     * @param destino nova casa que ira conter esta peca.
     */
    public void mover(Casa destino) {
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
     * Transforma a Pedra em Dama
     */
    public void virarDama(Peca peca){
        boolean tipoPB = peca.getTipo() == PEDRA_BRANCA;
        boolean tipoPV = peca.getTipo() == PEDRA_VERMELHA;
        
        if(tipoPB){
            tipo = DAMA_BRANCA;
        }
        if(tipoPV){
            tipo = DAMA_VERMELHA;
        }
    }
    
    /**
     * Transforma a Dama em Pedra
     */
    public void virarPeca(Peca peca){
        boolean tipoPB = peca.getTipo() == DAMA_BRANCA;
        boolean tipoPV = peca.getTipo() == DAMA_VERMELHA;
        
        if(tipoPB){
            tipo = PEDRA_BRANCA;
        }
        if(tipoPV){
            tipo = PEDRA_VERMELHA;
        }
    }
}
