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
    private int PecasBrancas;
    private int PecasVermelhas;
    private NPecas nPecas;

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
                    PecasBrancas++;
                }
                if((y > 4) && (((y%2!=0) && (x%2 != 0)) || ((y%2==0) && (x%2 == 0)))){
                    Casa casa = tabuleiro.getCasa(x, y);
                    Peca peca = new Peca(casa, Peca.PEDRA_VERMELHA);
                    PecasVermelhas++;
                }
            }
        }
        nPecas = new NPecas(PecasBrancas,PecasVermelhas);
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
        
        if ((nPecas.nPecaBrancas != 0) && (nPecas.nPecaVermelhas != 0)){
            //Verifica o numero de peças, quando não tiver mais peças diz quem ganhou
            if (((peca.getTipo() == peca.PEDRA_BRANCA) && (vezJogar)) || ((peca.getTipo() == peca.PEDRA_BRANCA) && (vezJogar))){
                //Verifica se o tipo de peça é branca
                if (((destinoX%2 == 0) && destinoY % 2 == 0) ||((destinoX%2 != 0) && destinoY % 2 != 0)){ 
                    // Permite o Movimento em Diiagonal
                    if (!podeIR){
                        if (!peca.comerSeguido){
                            // Só permite ir se tiver livre a casa
                            peca.MovimentoSimples(origem, destino, peca);
                            peca.Captura(origem, destino, peca, tabuleiro);
                            if (!peca.comerSeguido){
                                vezJogar = !vezJogar;
                            }
                        }
                        else{
                            peca.Captura(origem, destino, peca.pecaAJogar, tabuleiro);
                        }
                    }
                }
            }
            else if ((peca.getTipo() == peca.PEDRA_VERMELHA) && (!vezJogar)){
                //Verifica se o tipo de peça é branca
                    if (((destinoX%2 == 0) && destinoY % 2 == 0) ||((destinoX%2 != 0) && destinoY % 2 != 0)){ 
                    // Permite o Movimento em Diagonal
                    if(!podeIR){ 
                        if (!peca.comerSeguido){
                            // Só permite ir se tiver livre a casa
                            peca.MovimentoSimples(origem, destino, peca);
                            peca.Captura(origem, destino, peca, tabuleiro);
                            if (!peca.comerSeguido){
                                vezJogar = !vezJogar;
                            }
                        }
                        else{
                            peca.Captura(origem, destino, peca.pecaAJogar, tabuleiro);
                        }
                    }
                }
            }
        }
        if(nPecas.nPecaBrancas == 0){
            JOptionPane.showMessageDialog(null, "O jogo Acabou\nAs Vermelhas Vencem!");
        }
        else if(nPecas.nPecaVermelhas == 0){
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
