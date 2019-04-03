import java.util.ArrayList;
/**
 * O Tabuleiro do jogo. 
 * Respons�vel por armazenar as 64 casas.
 * 
 * @author Alan Moraes &lt;alan@ci.ufpb.br&gt;
 * @author Leonardo Villeth &lt;lvilleth@cc.ci.ufpb.br&gt;
 */
public class Tabuleiro {

    private Casa[][] casas;

    public Tabuleiro() {
        casas = new Casa[8][8];
        for (int x = 0; x < 8; x++) {
            for (int y = 0; y < 8; y++) {
                Casa casa = new Casa(x, y);
                casas[x][y] = casa;
            }
        }
    }
    /**
     * @param x linha
     * @param y coluna
     * @return Casa na posicao (x,y)
     */
    public Casa getCasa(int x, int y) {
        if ((x >= 0 ) && (x <= 7) && (y >= 0) && (y <= 7)){
            return casas[x][y];
        }
        else{
            return null;
        }
    }
    
    
    public ArrayList<Casa> buscarProximasCasas (Casa atual){
        ArrayList <Casa> casasVerificadas = new ArrayList <Casa>();
        
        /** Verifica a Casa do 1º Quadrante **/
        if (!this.getCasa(atual.getCasaX()+1, atual.getCasaY()+1).possuiPeca()){
            casasVerificadas.add(this.getCasa(atual.getCasaX()+1, atual.getCasaY()+1));
        }
        else{
            casasVerificadas.add(null);
        }
        
        /** Verifica a Casa do 2º Quadrante **/
        if(this.getCasa(atual.getCasaX()-1, atual.getCasaY()+1).possuiPeca()){ 
            casasVerificadas.add(this.getCasa(atual.getCasaX()-1, atual.getCasaY()+1));
        }
        else{
            casasVerificadas.add(null);
        }
        
        /** Verifica a Casa do 3º Quadrante **/
        casasVerificadas.add(this.getCasa(atual.getCasaX()-1, atual.getCasaY()-1));
        
        /** Verifica a Casa do 4º Quadrante **/
        casasVerificadas.add(this.getCasa(atual.getCasaX()+1, atual.getCasaY()-1));
        
        return casasVerificadas;
    }
}
