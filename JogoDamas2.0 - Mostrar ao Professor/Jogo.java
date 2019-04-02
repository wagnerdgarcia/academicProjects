/**
 * Importação das Bibliotecas para funcionamento do jogo
 */
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
        for (int y = 0; y<8; y++){
            for(int x = 0; x<8; x++){
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
        JOptionPane.showMessageDialog(null, "Bom Jogo,\nJogardor da Vez\nÉ as Brancas!");
    }
    
    /**
     * Comanda uma Peça na posicao (origemX, origemY) fazer um movimento 
     * para (destinoX, destinoY).
     * 
     * para origemX linha da Casa de origem.
     * para origemY coluna da Casa de origem.
     * para destinoX linha da Casa de destino.
     * para destinoY coluna da Casa de destino.
     */
    public void jogar(int origemX, int origemY, int destinoX, int destinoY) {
        Casa origem = tabuleiro.getCasa(origemX, origemY);
        Casa destino = tabuleiro.getCasa(destinoX, destinoY);
        Peca peca = origem.getPeca();
        //Verifica o numero de Pecas brancas e vermelhas e Encerra o Jogo
        if(nPecasBrancas == 0){
            JOptionPane.showMessageDialog(null, "O jogo Acabou\nAs Vermelhas Vencem!");
        }
        else if(nPecasVermelhas == 0){
            JOptionPane.showMessageDialog(null, "O jogo Acabou\nAs Brancas Vencem!");
        }
        // Se não encerra ele verifica se é possivel fazer jogadas
        else if ((nPecasBrancas != 0) && (nPecasVermelhas != 0)){
            if ((peca.getTipo() == peca.PEDRA_BRANCA) || (vezJogar)){
                if (((destinoX == origemX + 1) || (destinoX == origemX - 1)) && (!comerSeguido)){
                    // Realiza o movimento simples das peças
                    peca.movimentoSimplesPeca(origem, destino, peca, this);
                }
                else if (((destinoX == origemX + 2) || (destinoX == origemX - 2))){
                    if (!comerSeguido){
                        // Realiza o movimento de captura simples das peças
                        peca.capturaPeca(origem, destino, peca, this);
                    }
                    else{
                        // Realiza o movimento de captura seguida das peças
                        peca.capturaPeca(origem, destino, pecaAJogar, this);
                    }
                }    
            }
            if ((peca.getTipo() == peca.PEDRA_VERMELHA) && (!vezJogar)){
                if (((destinoX == origemX + 1) || (destinoX == origemX - 1)) && (!comerSeguido)){
                    // Realiza o movimento simples das peças
                    peca.movimentoSimplesPeca(origem, destino, peca, this);
                }
                else if (((destinoX == origemX + 2) || (destinoX == origemX - 2))){
                    if (!comerSeguido){
                        // Realiza o movimento de captura simples das peças
                        peca.capturaPeca(origem, destino, peca, this);
                    }
                    else{
                        // Realiza o movimento de captura seguida das peças
                        peca.capturaPeca(origem, destino, pecaAJogar, this);
                    }
                }    
            }
            else if ((peca.getTipo() == peca.DAMA_BRANCA) && (vezJogar)){
                if (((destinoX == origemX + 1) || (destinoX == origemX - 1)) && (!comerSeguido)){
                    // Realiza o movimento de captura seguida das damas
                    peca.movimentoSimplesDama(origem, destino, peca, this);
                }
                else if ((destinoX == origemX + 2) || (destinoX == origemX - 2)){
                    if (!comerSeguido){
                        // Realiza o movimento de captura simples das damas
                        peca.capturaPeca(origem, destino, peca, this);
                    }
                    else{
                        // Realiza o movimento de captura seguida das damas
                        peca.capturaPeca(origem, destino, pecaAJogar, this);
                    }
                }    
            }
            else if ((peca.getTipo() == peca.DAMA_VERMELHA) && (!vezJogar)){
                if (((destinoX == origemX + 1) || (destinoX == origemX - 1)) && (!comerSeguido)){
                    // Realiza o movimento simples das damas
                    peca.movimentoSimplesDama(origem, destino, peca, this);
                }
                else if ((destinoX == origemX + 2) || (destinoX == origemX - 2)){
                    if (!comerSeguido){
                        // Realiza o movimento de captura simples das damas
                        peca.capturaPeca(origem, destino, peca, this);
                    }
                    else{
                        // Realiza o movimento de captura seguida das damas
                        peca.capturaPeca(origem, destino, pecaAJogar, this);
                    }
                }    
            }
        }
    }
    
    /**
     * retorna o Tabuleiro em jogo.
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
        //Informa através da Janela o Jogador da Vez
        if (vezJogar){
            JOptionPane.showMessageDialog(null, "Jogardor da Vez\nÉ as Brancas!");
        }
        else if (!vezJogar){
            JOptionPane.showMessageDialog(null, "Jogardor da Vez\nÉ as Vermelhas!");
        }
        
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
        if (comerSeguido){
            JOptionPane.showMessageDialog(null, "Continue Jogando\nCom a Mesa Peça");
        }
    }
    
}
