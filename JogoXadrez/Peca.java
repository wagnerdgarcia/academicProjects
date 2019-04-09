
/**
 * Representa uma Pe�a do jogo.
 * Possui uma casa e um tipo associado.
 * 
 * @author Alan Moraes &lt;alan@ci.ufpb.br&gt;
 * @author Leonardo Villeth &lt;lvilleth@cc.ci.ufpb.br&gt;
 */
public class Peca {

    public static final Peao PEAO_BRANCO;
    public static final Torre TORRE_BRANCO;
    public static final Cavalo CAVALO_BRANCO;
    public static final Bispo BISPO_BRANCO;
    public static final Rainha RAINHA_BRANCO;
    public static final Rei REI_BRANCO;
    
    public static final Peao PEAO_PRETO;
    public static final Torre TORRE_PRETO;
    public static final Cavalo CAVALO_PRETO;
    public static final Bispo BISPO_PRETO;
    public static final Rainha RAINHA_PRETO;
    public static final Rei REI_PRETO;
    
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
}
