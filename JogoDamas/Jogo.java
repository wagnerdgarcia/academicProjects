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
    private void criarPecas() {
        for (int y = 0; y<8; y++){
            for(int x = 0; x<8; x++){
                if((y < 3) && (((y%2!=0) && (x%2 != 0)) || ((y%2==0) && (x%2 == 0)))){
                    Casa casa = tabuleiro.getCasa(x, y);
                    Peca peca = new Peca(casa, Peca.PEDRA_BRANCA);
                    nPecaBrancas++;
                }
                if((y > 4) && (((y%2!=0) && (x%2 != 0)) || ((y%2==0) && (x%2 == 0)))){
                    Casa casa = tabuleiro.getCasa(x, y);
                    Peca peca = new Peca(casa, Peca.PEDRA_VERMELHA);
                    nPecaBrancas++;
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
    public void Jogar(int origemX, int origemY, int destinoX, int destinoY) {
        Casa origem = tabuleiro.getCasa(origemX, origemY);
        Casa destino = tabuleiro.getCasa(destinoX, destinoY);
        Peca peca = origem.getPeca();
        boolean podeIR = destino.possuiPeca();
        int tipoDePeca = peca.getTipo();
        if ((nPecaBrancas != 0) || (nPecaVermelhas != 0)){
            //Verifica o numero de peças, quando não tiver mais peças diz quem ganhou
            if ((tipoDePeca == peca.PEDRA_BRANCA) && (vezJogar)){
                //Verifica se o tipo de peça é branca
                    if (((destinoX%2 == 0) && destinoY % 2 == 0) ||((destinoX%2 != 0) && destinoY % 2 != 0)){ 
                    // Permite o Movimento em Diiagonal
                    if(podeIR == false){ 
                        // Só permite ir se tiver livre a casa
                        MovimentoSimples(origem, destino, peca);
                        Captura(origem, destino, peca);
                    }
                }
            }
            else if ((tipoDePeca == peca.PEDRA_VERMELHA) && (!vezJogar)){
                //Verifica se o tipo de peça é branca
                    if (((destinoX%2 == 0) && destinoY % 2 == 0) ||((destinoX%2 != 0) && destinoY % 2 != 0)){ 
                    // Permite o Movimento em Diagonal
                    if(!podeIR){ 
                        // Só permite ir se tiver livre a casa
                        MovimentoSimples(origem, destino, peca);
                        Captura(origem, destino, peca);
                    }
                }
            }
        }
        else if(nPecaBrancas == 0){
            JOptionPane.showMessageDialog(null, "O jogo Acabou\nAs Brancas Vencem!");
        }
        else if(nPecaVermelhas == 0){
            JOptionPane.showMessageDialog(null, "O jogo Acabou\nAs Vermelhas Vencem!");
        }
    }
    /**
     * Faz os movimentos do jogo
     */
    public void MovimentoSimples(Casa origem, Casa destino, Peca peca) {
        if(((peca.getTipo() == (peca.PEDRA_BRANCA)) && 
            ((destino.getCasaY() == origem.getCasaY()+1) && 
             ((destino.getCasaX() == origem.getCasaX()+1) || (destino.getCasaX() == origem.getCasaX()-1)))) ||
          ((peca.getTipo() == (peca.PEDRA_VERMELHA) && 
           ((destino.getCasaY() == origem.getCasaY()-1) && 
            ((destino.getCasaX() == origem.getCasaX()+1) || (destino.getCasaX() == origem.getCasaX()-1)))))){
            // Movimento Simples de Peça
            peca.mover(destino);
            if(peca.getTipo() == peca.PEDRA_BRANCA){
                vezJogar = false;
            } 
            else{
                vezJogar = true;
            }
        }
    }
    
    /**
     * Faz as capturas do jogo
     */
    public void Captura(Casa origem, Casa destino, Peca peca) {
        boolean tipoPB = (peca.getTipo() == peca.PEDRA_BRANCA);
        boolean tipoPV = (peca.getTipo() == peca.PEDRA_VERMELHA);
        boolean tipoDB = (peca.getTipo() == peca.DAMA_BRANCA);
        boolean tipoDV = (peca.getTipo() == peca.DAMA_VERMELHA);
        boolean destino1 = ((destino.getCasaY() == origem.getCasaY()+2) && ((destino.getCasaX() == origem.getCasaX()+2)));
        boolean destino2 = ((destino.getCasaY() == origem.getCasaY()+2) && ((destino.getCasaX() == origem.getCasaX()-2)));
        boolean destino3 = ((destino.getCasaY() == origem.getCasaY()-2) && ((destino.getCasaX() == origem.getCasaX()-2)));
        boolean destino4 = ((destino.getCasaY() == origem.getCasaY()-2) && ((destino.getCasaX() == origem.getCasaX()+2)));
        if((tipoPB || tipoPV ) && destino1){
            Casa verificar = tabuleiro.getCasa(origem.getCasaX()+1, origem.getCasaY()+1);
            boolean verificaCasa = verificar.possuiPeca();
            Peca objetivo = verificar.getPeca();
            int tipoObjetivo = objetivo.getTipo();
            boolean objetivoB = ((objetivo.getTipo() == objetivo.PEDRA_BRANCA) || 
                                 (objetivo.getTipo() == objetivo.DAMA_BRANCA));
            boolean objetivoV = ((objetivo.getTipo() == objetivo.PEDRA_VERMELHA) || 
                                 (objetivo.getTipo() == objetivo.DAMA_VERMELHA));
            if (tipoPB && objetivoV){
                verificar.removerPeca();
                peca.mover(destino);
                nPecaVermelhas--;
                vezJogar = !vezJogar;
            }
            else if (tipoPV && objetivoB){
                verificar.removerPeca();
                peca.mover(destino);
                nPecaBrancas--;
                vezJogar = !vezJogar;
            }
        }
        else if((tipoPB || tipoPV ) && destino2){
            Casa verificar = tabuleiro.getCasa(origem.getCasaX()-1, origem.getCasaY()+1);
            boolean verificaCasa = verificar.possuiPeca();
            Peca objetivo = verificar.getPeca();
            int tipoObjetivo = objetivo.getTipo();
            boolean objetivoB = ((objetivo.getTipo() == objetivo.PEDRA_BRANCA) || 
                                 (objetivo.getTipo() == objetivo.DAMA_BRANCA));
            boolean objetivoV = ((objetivo.getTipo() == objetivo.PEDRA_VERMELHA) || 
                                 (objetivo.getTipo() == objetivo.DAMA_VERMELHA));
            if (tipoPB && objetivoV){
                verificar.removerPeca();
                peca.mover(destino);
                nPecaVermelhas--;
                vezJogar = !vezJogar;
            }
            else if (tipoPV && objetivoB){
                verificar.removerPeca();
                peca.mover(destino);
                nPecaBrancas--;
                vezJogar = !vezJogar;
            }
        }
        if((tipoPB || tipoPV ) && destino3){
            Casa verificar = tabuleiro.getCasa(origem.getCasaX()-1, origem.getCasaY()-1);
            boolean verificaCasa = verificar.possuiPeca();
            Peca objetivo = verificar.getPeca();
            int tipoObjetivo = objetivo.getTipo();
            boolean objetivoB = ((objetivo.getTipo() == objetivo.PEDRA_BRANCA) || 
                                 (objetivo.getTipo() == objetivo.DAMA_BRANCA));
            boolean objetivoV = ((objetivo.getTipo() == objetivo.PEDRA_VERMELHA) || 
                                 (objetivo.getTipo() == objetivo.DAMA_VERMELHA));
            if (tipoPB && objetivoV){
                verificar.removerPeca();
                peca.mover(destino);
                nPecaVermelhas--;
                vezJogar = !vezJogar;
            }
            else if (tipoPV && objetivoB){
                verificar.removerPeca();
                peca.mover(destino);
                nPecaBrancas--;
                vezJogar = !vezJogar;
            }
        }
        if((tipoPB || tipoPV ) && destino4){
            Casa verificar = tabuleiro.getCasa(origem.getCasaX()+1, origem.getCasaY()-1);
            boolean verificaCasa = verificar.possuiPeca();
            Peca objetivo = verificar.getPeca();
            int tipoObjetivo = objetivo.getTipo();
            boolean objetivoB = ((objetivo.getTipo() == objetivo.PEDRA_BRANCA) || 
                                 (objetivo.getTipo() == objetivo.DAMA_BRANCA));
            boolean objetivoV = ((objetivo.getTipo() == objetivo.PEDRA_VERMELHA) || 
                                 (objetivo.getTipo() == objetivo.DAMA_VERMELHA));
            if (tipoPB && objetivoV){
                verificar.removerPeca();
                peca.mover(destino);
                nPecaVermelhas--;
                vezJogar = !vezJogar;
            }
            else if (tipoPV && objetivoB){
                verificar.removerPeca();
                peca.mover(destino);
                nPecaBrancas--;
                vezJogar = !vezJogar;
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
