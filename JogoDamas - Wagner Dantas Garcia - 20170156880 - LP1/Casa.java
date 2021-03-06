/**
 * Representa uma Casa do tabuleiro.
 * Possui uma posiçao (i,j) e pode conter uma Peça.
 * 
 * @author Alan Moraes &lt;alan@ci.ufpb.br&gt;
 * @author Leonardo Villeth &lt;lvilleth@cc.ci.ufpb.br&gt;
 * @author Wagner Garcia &lt;wagnergarcia@cc.ci.ufpb.br&gt;
 */
public class Casa {

    private int x;
    private int y;
    private Peca peca;

    public Casa(int x, int y) {
        this.x = x;
        this.y = y;
        this.peca = null;
    }
    
    /**
     * @param peca a Peça a ser posicionada nesta Casa.
     */
    public void colocarPeca(Peca peca) {
        this.peca = peca;
    }
    
    /**
     * Remove a peca posicionada nesta casa, se houver.
     */
    public void removerPeca() {
        peca = null;
    }
    
    /**
     * @return a Peca posicionada nesta Casa, ou Null se a casa estiver livre.
     */
    public Peca getPeca() {
        return peca;
    }
    
    /**
     * @return true se existe uma peça nesta casa, caso contrario false.
     */
    public boolean possuiPeca() {
        return peca != null;
    }
    
    /**
     * Retorna o valor da casa em X
     */
    public int getCasaX() {
        return x;
    }
    
    /**
     * Retorna o valor da casa em y
     */
    public int getCasaY() {
        return y;
    }
    
}
