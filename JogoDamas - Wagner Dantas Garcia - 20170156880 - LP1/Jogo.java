/**
 * Importação das Bibliotecas para funcionamento do jogo
 */
import javax.swing.JOptionPane;
import java.util.ArrayList;

/**
 * Armazena o tabuleiro e responsavel por posicionar as pecas.
 * 
 * @author Alan Moraes &lt;alan@ci.ufpb.br&gt;
 * @author Leonardo Villeth &lt;lvilleth@cc.ci.ufpb.br&gt;
 * @author Wagner Garcia &lt;wagnergarcia@cc.ci.ufpb.br&gt;
 */
public class Jogo {
    // Tabuleiro do Jogo
    private Tabuleiro tabuleiro;
    
    //Variavel Que controla o Turno do Jogo
    private boolean vezJogar;
    
    // Numero de Pecas do Jogo, serão iniciadas durante a classe
    private int nPecasBrancas;
    private int nPecasVermelhas;

    // Variaveis para o Comer Seguido
    private boolean comerSeguido;
    private Peca pecaAJogar;
    
    public Jogo() {
        tabuleiro = new Tabuleiro();
        criarPecas();
        vezJogar = true;
        comerSeguido = false;
        pecaAJogar = null;
    }
    
    /**
     * Posiciona peças no tabuleiro.
     * Utilizado na inicializaçao do jogo.
     */
    private void criarPecas() {
        for (int y = 0; y < 8; y++){
            for(int x = 0; x < 8; x++){
                if((y < 3) && (((y%2!=0) && (x%2 != 0)) || ((y%2==0) && (x%2 == 0)))){
                    Casa casa = tabuleiro.getCasa(x, y);
                    Peca peca = new Peca(casa, Peca.PEDRA_BRANCA);
                    nPecasBrancas++;
                }
                if((y > 4) && (((y%2!=0) && (x%2 != 0)) || ((y%2==0) && (x%2 == 0)))){
                    Casa casa = tabuleiro.getCasa(x, y);
                    Peca peca = new Peca(casa, Peca.PEDRA_VERMELHA);
                    nPecasVermelhas++;
                }
            }
        }
    }
    
    /**
     * Comanda uma Peça na posicao (origemX, origemY) fazer um movimento 
     * para (destinoX, destinoY).
     * 
     * @para origemX linha da Casa de origem.
     * @para origemY coluna da Casa de origem.
     * @para destinoX linha da Casa de destino.
     * @para destinoY coluna da Casa de destino.
     */
    public void moverPeca(int origemX, int origemY, int destinoX, int destinoY) {
        Casa origem = tabuleiro.getCasa(origemX, origemY);
        Casa destino = tabuleiro.getCasa(destinoX, destinoY);
        Peca peca = origem.getPeca();
               
        if((peca.tipoPeca() && vezJogar) ||(!peca.tipoPeca() && !vezJogar)){
            // Se não encerra ele verifica se é possivel fazer jogadas
            if ((nPecasBrancas != 0) && (nPecasVermelhas != 0)){
                if ((pecaAJogar == null) ||(pecaAJogar != null && pecaAJogar == peca)){
                    boolean jogou = peca.mover(destino, this);
                    boolean  continuar = true;
                    if (peca.pecaCapturou()){
                        if (peca.tipoPeca()){
                            nPecasVermelhas--;
                        }
                        else{
                            nPecasBrancas--;
                        }
                        ArrayList <Casa> proximasCasas = tabuleiro.buscarProximasCasas(destino);
                        for (Casa proximaCasa : proximasCasas){
                            if(proximaCasa!= null && peca.podeCapturar(proximaCasa, this)){
                                pecaAJogar = peca;
                                continuar = false;
                                JOptionPane.showMessageDialog(null, "Continue Capturando com\na Mesma Peça Jogada");
                            }
                        }
                    }
                    if (jogou && continuar){
                        pecaAJogar = null;
                        continuar = true;
                        peca.setCaptura();
                        vezJogar = !vezJogar;
                    }
                }
            }
        }
        
        if(nPecasBrancas == 0){
            JOptionPane.showMessageDialog(null, "O jogo Acabou\nAs Vermelhas Vencem!");
        }
        else if(nPecasVermelhas == 0){
            JOptionPane.showMessageDialog(null, "O jogo Acabou\nAs Brancas Vencem!");
        }
    }
    
    /**
     * @return o Tabuleiro em jogo.
     */
    public Tabuleiro getTabuleiro() {
        return tabuleiro;
    }
}
