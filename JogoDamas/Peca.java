
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
    
    private Casa casa;
    private int tipo;

    public boolean comerSeguido = false;
    public Peca pecaAJogar = null;

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
        }
    }
    
    /**
     * Faz as capturas do jogo
     */
    public void Captura(Casa origem, Casa destino, Peca peca, Tabuleiro tabuleiro) {
        System.out.println("Captura");
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
            Peca objetivo = verificar.getPeca();
            int tipoObjetivo = objetivo.getTipo();
            boolean objetivoB = ((objetivo.getTipo() == objetivo.PEDRA_BRANCA) || 
                                 (objetivo.getTipo() == objetivo.DAMA_BRANCA));
            boolean objetivoV = ((objetivo.getTipo() == objetivo.PEDRA_VERMELHA) || 
                                 (objetivo.getTipo() == objetivo.DAMA_VERMELHA));
            if (tipoPB && objetivoV){
                verificar.removerPeca();
                peca.mover(destino);
                CapturaSeguida(destino, tabuleiro);
            }
            else if (tipoPV && objetivoB){
                verificar.removerPeca();
                peca.mover(destino);
                CapturaSeguida(destino, tabuleiro);
            }
        }
        else if((tipoPB || tipoPV ) && destino2){
            Casa verificar = tabuleiro.getCasa(origem.getCasaX()-1, origem.getCasaY()+1);
            Peca objetivo = verificar.getPeca();
            int tipoObjetivo = objetivo.getTipo();
            boolean objetivoB = ((objetivo.getTipo() == objetivo.PEDRA_BRANCA) || 
                                 (objetivo.getTipo() == objetivo.DAMA_BRANCA));
            boolean objetivoV = ((objetivo.getTipo() == objetivo.PEDRA_VERMELHA) || 
                                 (objetivo.getTipo() == objetivo.DAMA_VERMELHA));
            if (tipoPB && objetivoV){
                verificar.removerPeca();
                peca.mover(destino);
                CapturaSeguida(destino, tabuleiro);
            }
            else if (tipoPV && objetivoB){
                verificar.removerPeca();
                peca.mover(destino);
                CapturaSeguida(destino, tabuleiro);
            }
        }
        if((tipoPB || tipoPV ) && destino3){
            Casa verificar = tabuleiro.getCasa(origem.getCasaX()-1, origem.getCasaY()-1);
            Peca objetivo = verificar.getPeca();
            int tipoObjetivo = objetivo.getTipo();
            boolean objetivoB = ((objetivo.getTipo() == objetivo.PEDRA_BRANCA) || 
                                 (objetivo.getTipo() == objetivo.DAMA_BRANCA));
            boolean objetivoV = ((objetivo.getTipo() == objetivo.PEDRA_VERMELHA) || 
                                 (objetivo.getTipo() == objetivo.DAMA_VERMELHA));
            if (tipoPB && objetivoV){
                verificar.removerPeca();
                peca.mover(destino);
                CapturaSeguida(destino, tabuleiro);
            }
            else if (tipoPV && objetivoB){
                verificar.removerPeca();
                peca.mover(destino);
                CapturaSeguida(destino, tabuleiro);
            }
        }
        if((tipoPB || tipoPV ) && destino4){
            Casa verificar = tabuleiro.getCasa(origem.getCasaX()+1, origem.getCasaY()-1);
            Peca objetivo = verificar.getPeca();
            int tipoObjetivo = objetivo.getTipo();
            boolean objetivoB = ((objetivo.getTipo() == objetivo.PEDRA_BRANCA) || 
                                 (objetivo.getTipo() == objetivo.DAMA_BRANCA));
            boolean objetivoV = ((objetivo.getTipo() == objetivo.PEDRA_VERMELHA) || 
                                 (objetivo.getTipo() == objetivo.DAMA_VERMELHA));
            if (tipoPB && objetivoV){
                verificar.removerPeca();
                peca.mover(destino);
                CapturaSeguida(destino, tabuleiro);
            }
            else if (tipoPV && objetivoB){
                verificar.removerPeca();
                peca.mover(destino);
                CapturaSeguida(destino, tabuleiro);
            }
        }
    }
    
    /**
     * Verifica se tem a possibilidade de comer seguido
     */
    public void CapturaSeguida(Casa atual,Tabuleiro tabuleiro){
        System.out.println("Captura Seguida");
        Peca peca = atual.getPeca();
        boolean tipoPB = (peca.getTipo() == peca.PEDRA_BRANCA);
        boolean tipoPV = (peca.getTipo() == peca.PEDRA_VERMELHA);
        boolean tipoDB = (peca.getTipo() == peca.DAMA_BRANCA);
        boolean tipoDV = (peca.getTipo() == peca.DAMA_VERMELHA);
        
        Casa verificar = tabuleiro.getCasa(atual.getCasaX()+1, atual.getCasaY()+1);
        boolean possuiPeca = verificar.possuiPeca();
        
        Peca objetivo1 = verificar.getPeca();
        int tipoObjetivo1 = objetivo1.getTipo();
        boolean objetivo1B = ((objetivo1.getTipo() == objetivo1.PEDRA_BRANCA) || 
                             (objetivo1.getTipo() == objetivo1.DAMA_BRANCA));
        boolean objetivo1V = ((objetivo1.getTipo() == objetivo1.PEDRA_VERMELHA) || 
                             (objetivo1.getTipo() == objetivo1.DAMA_VERMELHA));

    
        if ((possuiPeca) && 
            ((1 < atual.getCasaX() || atual.getCasaX() < 6) && 
             (1 < atual.getCasaY() || atual.getCasaY()< 6))){
            Casa destino1 = tabuleiro.getCasa(atual.getCasaX()+2, atual.getCasaY()+2);
            boolean fim1 = destino1.possuiPeca();
            if (!fim1){
                if(objetivo1V && tipoPB){
                    comerSeguido = true;
                    pecaAJogar = peca;
                }
                else if (objetivo1B && tipoPV){
                    comerSeguido = true;
                    pecaAJogar = peca;
                }
            }
        }
        else{
            comerSeguido = false;
            pecaAJogar = null;
        }
    }
}