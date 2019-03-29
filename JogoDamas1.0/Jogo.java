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
    private boolean comerSeguido = false;
    private Peca pecaAJogar = null;
    private boolean jaVerificado = false;
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
                    nPecaVermelhas++;
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
        if ((nPecaBrancas != 0) && (nPecaVermelhas != 0)){
            //Verifica o numero de peças, quando não tiver mais peças diz quem ganhou
            if (((peca.getTipo() == peca.PEDRA_BRANCA) || (peca.getTipo() == peca.DAMA_BRANCA)) && (vezJogar)){
                //Verifica se o tipo de peça é branca
                if (((destinoX%2 == 0) && destinoY % 2 == 0) ||((destinoX%2 != 0) && destinoY % 2 != 0)){ 
                    // Permite o Movimento em Diiagonal
                    if (!podeIR){
                        if (!comerSeguido){
                            // Só permite ir se tiver livre a casa
                            MovimentoSimples(origem, destino, peca);
                            Captura(origem, destino, peca);
                            if (comerSeguido){
                                JOptionPane.showMessageDialog(null, "Continue Capturando\nCom a Mesma Peca");
                            }
                        }
                        else{
                            Captura(origem, destino, pecaAJogar);
                            if (comerSeguido){
                                JOptionPane.showMessageDialog(null, "Continue Capturando\nCom a Mesma Peca");
                            }
                        }
                    }
                }
            }
            else if (((peca.getTipo() == peca.PEDRA_VERMELHA) || (peca.getTipo() == peca.DAMA_VERMELHA)) && (!vezJogar)){
                //Verifica se o tipo de peça é branca
                if (((destinoX%2 == 0) && destinoY % 2 == 0) ||((destinoX%2 != 0) && destinoY % 2 != 0)){ 
                    // Permite o Movimento em Diagonal
                    if(!podeIR){ 
                        if (!comerSeguido){
                            // Só permite ir se tiver livre a casa
                            MovimentoSimples(origem, destino, peca);
                            Captura(origem, destino, peca);
                            if (comerSeguido){
                                JOptionPane.showMessageDialog(null, "Continue Capturando\nCom a Mesma Peca");
                            }
                        }
                        else{
                            Captura(origem, destino, pecaAJogar);
                            if (comerSeguido){
                                JOptionPane.showMessageDialog(null, "Continue Capturando\nCom a Mesma Peca");
                            }
                        }
                    }
                }
            }
        }
        if(nPecaBrancas == 0){
            JOptionPane.showMessageDialog(null, "O jogo Acabou\nAs Vermelhas Vencem!");
        }
        else if(nPecaVermelhas == 0){
            JOptionPane.showMessageDialog(null, "O jogo Acabou\nAs Brancas Vencem!");
        }
    }
    /**
     * Faz os movimentos do jogo
     */
    public void MovimentoSimples(Casa origem, Casa destino, Peca peca) {
        if((((peca.getTipo() == (peca.PEDRA_BRANCA)) || (peca.getTipo() == (peca.DAMA_VERMELHA))) && 
            ((destino.getCasaY() == origem.getCasaY()+1) && 
             ((destino.getCasaX() == origem.getCasaX()+1) || (destino.getCasaX() == origem.getCasaX()-1)))) ||
          (((peca.getTipo() == (peca.PEDRA_VERMELHA)) || (peca.getTipo() == (peca.DAMA_BRANCA))) && 
           ((destino.getCasaY() == origem.getCasaY()-1) && 
            ((destino.getCasaX() == origem.getCasaX()+1) || (destino.getCasaX() == origem.getCasaX()-1))))){
            // Movimento Simples de Peça
            peca.mover(destino);
            peca.virarDama(peca, destino);
            peca.virarPeca(peca, destino);
            vezJogar = !vezJogar;
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
        if(destino1){
            Casa verificar = tabuleiro.getCasa(origem.getCasaX()+1, origem.getCasaY()+1);
            Peca objetivo = verificar.getPeca();
            int tipoObjetivo = objetivo.getTipo();
            boolean objetivoB = ((objetivo.getTipo() == objetivo.PEDRA_BRANCA) || 
                                 (objetivo.getTipo() == objetivo.DAMA_BRANCA));
            boolean objetivoV = ((objetivo.getTipo() == objetivo.PEDRA_VERMELHA) || 
                                 (objetivo.getTipo() == objetivo.DAMA_VERMELHA));
            if ((tipoPB || tipoDB) && (objetivoV)){
                verificar.removerPeca();
                peca.mover(destino);
                nPecaVermelhas--;
                peca.virarDama(peca, destino);
                peca.virarPeca(peca, destino);
                CapturaSeguida(destino);
                if (!comerSeguido){
                    vezJogar = !vezJogar;
                }
            }
            else if ((tipoPV || tipoDV) && objetivoB){
                verificar.removerPeca();
                peca.mover(destino);
                nPecaBrancas--;
                peca.virarDama(peca, destino);
                peca.virarPeca(peca, destino);
                CapturaSeguida(destino);
                if (!comerSeguido){
                    vezJogar = !vezJogar;
                }
            }
        }
        else if(destino2){
            Casa verificar = tabuleiro.getCasa(origem.getCasaX()-1, origem.getCasaY()+1);
            Peca objetivo = verificar.getPeca();
            int tipoObjetivo = objetivo.getTipo();
            boolean objetivoB = ((objetivo.getTipo() == objetivo.PEDRA_BRANCA) || 
                                 (objetivo.getTipo() == objetivo.DAMA_BRANCA));
            boolean objetivoV = ((objetivo.getTipo() == objetivo.PEDRA_VERMELHA) || 
                                 (objetivo.getTipo() == objetivo.DAMA_VERMELHA));
            if ((tipoPB || tipoDB) && (objetivoV)){
                verificar.removerPeca();
                peca.mover(destino);
                nPecaVermelhas--;
                peca.virarDama(peca, destino);
                peca.virarPeca(peca, destino);
                CapturaSeguida(destino);
                if (!comerSeguido){
                    vezJogar = !vezJogar;
                }
            }
            else if ((tipoPV || tipoDV) && objetivoB){
                verificar.removerPeca();
                peca.mover(destino);
                nPecaBrancas--;
                peca.virarDama(peca, destino);
                peca.virarPeca(peca, destino);
                CapturaSeguida(destino);
                if (!comerSeguido){
                    vezJogar = !vezJogar;
                }
            }
        }
        if(destino3){
            Casa verificar = tabuleiro.getCasa(origem.getCasaX()-1, origem.getCasaY()-1);
            Peca objetivo = verificar.getPeca();
            int tipoObjetivo = objetivo.getTipo();
            boolean objetivoB = ((objetivo.getTipo() == objetivo.PEDRA_BRANCA) || 
                                 (objetivo.getTipo() == objetivo.DAMA_BRANCA));
            boolean objetivoV = ((objetivo.getTipo() == objetivo.PEDRA_VERMELHA) || 
                                 (objetivo.getTipo() == objetivo.DAMA_VERMELHA));
            if ((tipoPB || tipoDB) && (objetivoV)){
                verificar.removerPeca();
                peca.mover(destino);
                nPecaVermelhas--;
                peca.virarDama(peca, destino);
                peca.virarPeca(peca, destino);
                CapturaSeguida(destino);
                if (!comerSeguido){
                    vezJogar = !vezJogar;
                }
            }
            else if ((tipoPV || tipoDV) && objetivoB){
                verificar.removerPeca();
                peca.mover(destino);
                nPecaBrancas--;
                peca.virarDama(peca, destino);
                peca.virarPeca(peca, destino);
                CapturaSeguida(destino);
                if (!comerSeguido){
                    vezJogar = !vezJogar;
                }
            }
        }
        if(destino4){
            Casa verificar = tabuleiro.getCasa(origem.getCasaX()+1, origem.getCasaY()-1);
            Peca objetivo = verificar.getPeca();
            int tipoObjetivo = objetivo.getTipo();
            boolean objetivoB = ((objetivo.getTipo() == objetivo.PEDRA_BRANCA) || 
                                 (objetivo.getTipo() == objetivo.DAMA_BRANCA));
            boolean objetivoV = ((objetivo.getTipo() == objetivo.PEDRA_VERMELHA) || 
                                 (objetivo.getTipo() == objetivo.DAMA_VERMELHA));
            if ((tipoPB || tipoDB) && (objetivoV)){
                verificar.removerPeca();
                peca.mover(destino);
                nPecaVermelhas--;
                peca.virarDama(peca, destino);
                peca.virarPeca(peca, destino);
                CapturaSeguida(destino);
                if (!comerSeguido){
                    vezJogar = !vezJogar;
                }
            }
            else if ((tipoPV || tipoDV) && objetivoB){
                verificar.removerPeca();
                peca.mover(destino);
                nPecaBrancas--;
                peca.virarDama(peca, destino);
                peca.virarPeca(peca, destino);
                CapturaSeguida(destino);
                if (!comerSeguido){
                    vezJogar = !vezJogar;
                }
            }
        }
    }
    
    /**
     * Verifica se tem a possibilidade de comer seguido
     */
    public void CapturaSeguida(Casa atual){
        Peca peca = atual.getPeca();
        boolean tipoB = ((peca.getTipo() == peca.PEDRA_BRANCA) || (peca.getTipo() == peca.DAMA_BRANCA));
        boolean tipoV = ((peca.getTipo() == peca.PEDRA_VERMELHA) || (peca.getTipo() == peca.DAMA_VERMELHA));

        Casa verificar1 = tabuleiro.getCasa(atual.getCasaX()+1, atual.getCasaY()+1);
        Casa verificar2 = tabuleiro.getCasa(atual.getCasaX()-1, atual.getCasaY()+1);
        Casa verificar3 = tabuleiro.getCasa(atual.getCasaX()-1, atual.getCasaY()-1);
        Casa verificar4 = tabuleiro.getCasa(atual.getCasaX()+1, atual.getCasaY()-1);
        
        if(verificar1 != null){
            boolean possuiPeca = verificar1.possuiPeca();
            if (possuiPeca){
                Peca objetivo = verificar1.getPeca();
                int tipoObjetivo = objetivo.getTipo();
                boolean objetivoB = ((objetivo.getTipo() == objetivo.PEDRA_BRANCA) || 
                                     (objetivo.getTipo() == objetivo.DAMA_BRANCA));
                boolean objetivoV = ((objetivo.getTipo() == objetivo.PEDRA_VERMELHA) || 
                                     (objetivo.getTipo() == objetivo.DAMA_VERMELHA));
                
                Casa destino = tabuleiro.getCasa(atual.getCasaX()+2, atual.getCasaY()+2);
                if(destino != null){    
                    boolean fim = destino.possuiPeca();
                    if (!fim){
                        if(objetivoV && tipoB){
                            comerSeguido = true;
                            pecaAJogar = peca;
                            jaVerificado = true;
                        }
                        else if (objetivoB && tipoV){
                            comerSeguido = true;
                            pecaAJogar = peca;
                            jaVerificado = true;
                        }
                    }
                }
                else if(!jaVerificado){
                    comerSeguido = false;
                    pecaAJogar = null;
                }
            }
            else if(!jaVerificado){
                comerSeguido = false;
                pecaAJogar = null;
            }
        }
        if(verificar2 != null){
            boolean possuiPeca = verificar2.possuiPeca();
            if (possuiPeca){
                Peca objetivo = verificar2.getPeca();
                int tipoObjetivo = objetivo.getTipo();
                boolean objetivoB = ((objetivo.getTipo() == objetivo.PEDRA_BRANCA) || 
                                     (objetivo.getTipo() == objetivo.DAMA_BRANCA));
                boolean objetivoV = ((objetivo.getTipo() == objetivo.PEDRA_VERMELHA) || 
                                     (objetivo.getTipo() == objetivo.DAMA_VERMELHA));
                
                Casa destino = tabuleiro.getCasa(atual.getCasaX()-2, atual.getCasaY()+2);
                if(destino != null){    
                    boolean fim = destino.possuiPeca();
                    if (!fim){
                        if(objetivoV && tipoB){
                            comerSeguido = true;
                            pecaAJogar = peca;
                            jaVerificado = true;
                        }
                        else if (objetivoB && tipoV){
                            comerSeguido = true;
                            pecaAJogar = peca;
                            jaVerificado = true;
                        }
                    }
                }
                else if(!jaVerificado){
                    comerSeguido = false;
                    pecaAJogar = null;
                }
            }
            else if(!jaVerificado){
                comerSeguido = false;
                pecaAJogar = null;
            }
        }
        if(verificar3 != null){
            boolean possuiPeca = verificar3.possuiPeca();
            if (possuiPeca){
                Peca objetivo = verificar3.getPeca();
                int tipoObjetivo = objetivo.getTipo();
                boolean objetivoB = ((objetivo.getTipo() == objetivo.PEDRA_BRANCA) || 
                                     (objetivo.getTipo() == objetivo.DAMA_BRANCA));
                boolean objetivoV = ((objetivo.getTipo() == objetivo.PEDRA_VERMELHA) || 
                                     (objetivo.getTipo() == objetivo.DAMA_VERMELHA));
                
                Casa destino = tabuleiro.getCasa(atual.getCasaX()-2, atual.getCasaY()-2);
                if(destino != null){    
                    boolean fim = destino.possuiPeca();
                    if (!fim){
                        if(objetivoV && tipoB){
                            comerSeguido = true;
                            pecaAJogar = peca;
                            jaVerificado = true;
                        }
                        else if (objetivoB && tipoV){
                            comerSeguido = true;
                            pecaAJogar = peca;
                            jaVerificado = true;
                        }
                    }
                }
                else if(!jaVerificado){
                    comerSeguido = false;
                    pecaAJogar = null;
                }
            }
            else if(!jaVerificado){
                comerSeguido = false;
                pecaAJogar = null;
            }
        }
        if(verificar4 != null){
            boolean possuiPeca = verificar4.possuiPeca();
            if (possuiPeca){
                Peca objetivo = verificar4.getPeca();
                int tipoObjetivo = objetivo.getTipo();
                boolean objetivoB = ((objetivo.getTipo() == objetivo.PEDRA_BRANCA) || 
                                     (objetivo.getTipo() == objetivo.DAMA_BRANCA));
                boolean objetivoV = ((objetivo.getTipo() == objetivo.PEDRA_VERMELHA) || 
                                     (objetivo.getTipo() == objetivo.DAMA_VERMELHA));
                
                Casa destino = tabuleiro.getCasa(atual.getCasaX()+2, atual.getCasaY()-2);
                if(destino != null){    
                    boolean fim = destino.possuiPeca();
                    if (!fim){
                        if(objetivoV && tipoB){
                            comerSeguido = true;
                            pecaAJogar = peca;
                            jaVerificado = true;
                        }
                        else if (objetivoB && tipoV){
                            comerSeguido = true;
                            pecaAJogar = peca;
                            jaVerificado = true;
                        }
                    }
                }
                else if(!jaVerificado){
                    comerSeguido = false;
                    pecaAJogar = null;
                }
            }
        }
        if((verificar1 == null) && (verificar2 == null) && (verificar3 == null) && (verificar4 == null) && (!jaVerificado)){
            comerSeguido = false;
            pecaAJogar = null;
        }
        jaVerificado = false;
    }
    
    /**
     * @return o Tabuleiro em jogo.
     */
    public Tabuleiro getTabuleiro() {
        return tabuleiro;
    }
}
