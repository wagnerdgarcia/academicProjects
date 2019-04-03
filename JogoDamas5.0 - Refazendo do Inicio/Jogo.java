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
     * Posiciona pe�as no tabuleiro.
     * Utilizado na inicializa�ao do jogo.
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
     * Comanda uma Pe�a na posicao (origemX, origemY) fazer um movimento 
     * para (destinoX, destinoY).
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
        if(nPecasBrancas == 0){
            JOptionPane.showMessageDialog(null, "O jogo Acabou\nAs Vermelhas Vencem!");
        }
        else if(nPecasVermelhas == 0){
            JOptionPane.showMessageDialog(null, "O jogo Acabou\nAs Brancas Vencem!");
        }
        // Se não encerra ele verifica se é possivel fazer jogadas
        else if ((nPecasBrancas != 0) && (nPecasVermelhas != 0)){
            if ((pecaAJogar == null) ||(pecaAJogar != null && pecaAJogar == peca)){
                boolean jogou = peca.mover(destino, this);
                boolean  continuar = true;
                boolean entrou = false;
                if (peca.pecaCapturou()){
                    ArrayList <Casa> proximasCasas = tabuleiro.buscarProximasCasas(destino);
                    for (Casa proximaCasa : proximasCasas){
                        if(proximaCasa!= null && peca.CapturaSeguida(proximaCasa, this)){
                            pecaAJogar = peca;
                            continuar = false;
                            entrou = true;
                        }
                    }
                }
                if (jogou && continuar){
                    pecaAJogar = null;
                    continuar = true;
                    peca.setCaptura();
                    System.out.println("Entrou");
                    passarVez();
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
    
    /** 
     * Decrementa as peças Brancas
     */
    public void decrementaBrancas(){
        nPecasBrancas--;
    }
    
    /** 
     * Decrementa as peças vermelhas
     */
    public void decrementaVermelhas(){
        nPecasVermelhas--;
    }
    
    /**
     * Passa a Vez de Jogar
     */
    public void passarVez(){
        vezJogar = !vezJogar;
    }
    
    /**
     * Retorna de quem é a Vez de jogar
     */
    public boolean vezJogador(){
        return vezJogar;
    }
    
    /**
     * Retorna de se Pode Comer Seguido
     */
    public boolean podeComerSeguido(){
        return comerSeguido;
    }
    
    /**
     * Altera a variavel comer seguido e pecaAJogar
     */
    public void alteraComerSeguido(boolean comeu, Peca newPeca){
        comerSeguido = comeu;
        pecaAJogar = newPeca;
    }
}
