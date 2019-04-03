/**
 * Representa uma Pe�a do jogo.
 * Possui uma casa e um tipo associado.
 * 
 * @author Alan Moraes &lt;alan@ci.ufpb.br&gt;
 * @author Leonardo Villeth &lt;lvilleth@cc.ci.ufpb.br&gt;
 */

public class Peca{

    public static final int PEDRA_BRANCA = 0;
    public static final int DAMA_BRANCA = 1;
    public static final int PEDRA_VERMELHA = 2;
    public static final int DAMA_VERMELHA = 3;
    
    boolean jaVerificado;

    private Casa casa;
    private int tipo;

    public Peca(Casa casa, int tipo) {
        this.casa = casa;
        this.tipo = tipo;
        casa.colocarPeca(this);
        jaVerificado = false;
    }
    
    /**
     * Movimenta a peca para uma nova casa.
     * para destino nova casa que ira conter esta peca.
     */
    public void movimentacao(Casa destino) {
        casa.removerPeca();
        destino.colocarPeca(this);
        casa = destino;
    }
    
    /**
     * Movimenta a peca para uma nova casa.
     * param destino nova casa que ira conter esta peca.
     */
    public void mover(Casa destino, Jogo jogo) {
        Casa origem = this.casa;
        
        if ((this.getTipo() == PEDRA_BRANCA) || (jogo.vezJogador())){
                if ((destino.getCasaY() ==  origem.getCasaY() + 1) && (!jogo.vezJogador())){
                    // Realiza o movimento simples das peças
                    movimentoSimplesPeca(origem, destino, peca, jogo);
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
    
    /**
     * Realiza o movimento simples das Peças
     */
    public void movimentoSimplesPeca(Casa origem, Casa destino, Peca peca, Jogo jogo){
        //Movimento da Pedra Branca Simples
        if(((peca.getTipo() == PEDRA_BRANCA) && (!destino.possuiPeca()) && jogo.vezJogador())&& 
           ((destino.getCasaY() == origem.getCasaY()+1) &&
            ((destino.getCasaX() == origem.getCasaX()-1) || (destino.getCasaX() == origem.getCasaX()+1)))){
                movimentacao(destino);             // Move a peça para o Destino
                virarDama(destino);   // Verifica se ela pode virar uma Dama
                jogo.passarVez();           // Passa a Vez do jogo
        }
        //Movimento da Pedra Vermelha Simples
        else if(((peca.getTipo() == PEDRA_VERMELHA) && (!destino.possuiPeca()) && !jogo.vezJogador()) && 
           ((destino.getCasaY() == origem.getCasaY()-1) &&
            ((destino.getCasaX() == origem.getCasaX()-1) || (destino.getCasaX() == origem.getCasaX()+1)))){
                movimentacao(destino);             // Move a peça para o Destino
                virarDama(destino);   // Verifica se ela pode virar uma Dama
                jogo.passarVez();           // Passa a Vez do jogo
        }
    }
    /**
     * Realiza o movimento Simples das Damas
     */
    public void movimentoSimplesDama(Casa origem, Casa destino, Peca peca, Jogo jogo){
        //Movimento da Pedra Branca Simples
        if(((peca.getTipo() == DAMA_BRANCA) && (!destino.possuiPeca()) && jogo.vezJogador())&& 
           ((destino.getCasaY() == origem.getCasaY()-1) &&
            ((destino.getCasaX() == origem.getCasaX()-1) || (destino.getCasaX() == origem.getCasaX()+1)))){
                movimentacao(destino);             // Move a peça para o Destino
                virarPeca(destino);   // Verifica se ela pode virar uma Peça
                jogo.passarVez();           // Passa a Vez do jogo
        }
        //Movimento da Pedra Vermelha Simples
        else if(((peca.getTipo() == DAMA_VERMELHA) && (!destino.possuiPeca()) && !jogo.vezJogador()) && 
           ((destino.getCasaY() == origem.getCasaY()+1) &&
            ((destino.getCasaX() == origem.getCasaX()-1) || (destino.getCasaX() == origem.getCasaX()+1)))){
                movimentacao(destino);             // Move a peça para o Destino
                virarPeca(destino);   // Verifica se ela pode virar uma Peça
                jogo.passarVez();           // Passa a Vez do 
        }
    }
    
    /**
     * Realiza a Captura Simples das Peças
     */
    public void capturaPeca(Casa origem, Casa destino, Peca peca, Jogo jogo){
        boolean tipoPB = (peca.getTipo() == peca.PEDRA_BRANCA);     // Informa se a Peça é Pedra Branca
        boolean tipoPV = (peca.getTipo() == peca.PEDRA_VERMELHA);   // Informa se a Peça é Pedra Vermelha
        boolean tipoDB = (peca.getTipo() == peca.DAMA_BRANCA);      // Informa se a Peça é Dama Vermelha
        boolean tipoDV = (peca.getTipo() == peca.DAMA_VERMELHA);    // Informa se a Peça é Dama Branca
        
        /** Verifica se o destino desejado é no sentido do 1º Quadrante **/
        boolean destino1 = ((destino.getCasaY() == origem.getCasaY()+2) && ((destino.getCasaX() == origem.getCasaX()+2)));
        
        /** Verifica se o destino desejado é no sentido do 2º Quadrante **/
        boolean destino2 = ((destino.getCasaY() == origem.getCasaY()+2) && ((destino.getCasaX() == origem.getCasaX()-2)));
        
        /** Verifica se o destino desejado é no sentido do 3º Quadrante **/
        boolean destino3 = ((destino.getCasaY() == origem.getCasaY()-2) && ((destino.getCasaX() == origem.getCasaX()-2)));
        
        /** Verifica se o destino desejado é no sentido do 4º Quadrante **/
        boolean destino4 = ((destino.getCasaY() == origem.getCasaY()-2) && ((destino.getCasaX() == origem.getCasaX()+2)));
        
        if(destino1){
            /** Verifica se a casa entre a Origem e o Destino tem peça **/
            Casa verificar = jogo.getTabuleiro().getCasa(origem.getCasaX()+1, origem.getCasaY()+1);
            Peca objetivo = verificar.getPeca();
            if (objetivo != null){
                int tipoObjetivo = objetivo.getTipo();
                boolean objetivoB = ((objetivo.getTipo() == objetivo.PEDRA_BRANCA) || //Verifica o Tipo da peça que tá entre
                                     (objetivo.getTipo() == objetivo.DAMA_BRANCA));   //a Origem e o Destino é Branca
                boolean objetivoV = ((objetivo.getTipo() == objetivo.PEDRA_VERMELHA) || //Verifica o Tipo da peça que tá entre
                                     (objetivo.getTipo() == objetivo.DAMA_VERMELHA));   //a Origem e o Destino é Vermelha
                
                /** Verifica se o Destino tem peça **/
                boolean podeIr = !destino.possuiPeca();
                
                if ((tipoPB || tipoDB) && (objetivoV) && (podeIr) && (jogo.vezJogador())){
                    verificar.removerPeca();                // Remove a Peça Vermelha da casa Verificada
                    this.movimentacao(destino);                    // Move a Peça para o Destino
                    jogo.decrementaVermelhas();             // Decrementa as Peças Vermelhas
                    virarDama(destino);               // Verifica se pode Virar Dama
                    virarPeca(destino);               // Verifica se pode Virar peça normal
                    CapturaSeguida(destino, jogo);    // Verifica se tem mais peças pra capturar
                    if (!jogo.podeComerSeguido()){
                        jogo.passarVez();                   // Passa a Vez
                    }
                }
                else if ((tipoPV || tipoDV) && (objetivoB) && (podeIr)&& (!jogo.vezJogador())){
                    verificar.removerPeca();                // Remove a Peça Branca da casa Verificada
                    peca.movimentacao(destino);                    // Move a Peça para o Destino
                    jogo.decrementaBrancas();               
                    virarDama(destino);               // Verifica se pode Virar Dama
                    virarPeca(destino);               // Verifica se pode Virar peça normal
                    CapturaSeguida(destino, jogo);    // Verifica se tem mais peças pra capturar
                    if (!jogo.podeComerSeguido()){
                        jogo.passarVez();                   // Passa a Vez
                    }
                }
            }
        }
        
        if(destino2){
            /** Verifica se a casa entre a Origem e o Destino tem peça **/
            Casa verificar = jogo.getTabuleiro().getCasa(origem.getCasaX()-1, origem.getCasaY()+1);
            Peca objetivo = verificar.getPeca();
            if (objetivo != null){
                int tipoObjetivo = objetivo.getTipo();
                boolean objetivoB = ((objetivo.getTipo() == objetivo.PEDRA_BRANCA) || //Verifica o Tipo da peça que tá entre
                                     (objetivo.getTipo() == objetivo.DAMA_BRANCA));   //a Origem e o Destino é Branca
                boolean objetivoV = ((objetivo.getTipo() == objetivo.PEDRA_VERMELHA) || //Verifica o Tipo da peça que tá entre
                                     (objetivo.getTipo() == objetivo.DAMA_VERMELHA));   //a Origem e o Destino é Vermelha
                
                /** Verifica se o Destino tem peça **/
                boolean podeIr = !destino.possuiPeca();
                
                if ((tipoPB || tipoDB) && (objetivoV) && (podeIr) && (jogo.vezJogador())){
                    verificar.removerPeca();                // Remove a Peça Vermelha da casa Verificada
                    peca.movimentacao(destino);                    // Move a Peça para o Destino
                    jogo.decrementaVermelhas();             // Decrementa as Peças Vermelhas
                    virarDama(destino);               // Verifica se pode Virar Dama
                    virarPeca(destino);               // Verifica se pode Virar peça normal
                    CapturaSeguida(destino, jogo);    // Verifica se tem mais peças pra capturar
                    if (!jogo.podeComerSeguido()){
                        jogo.passarVez();                   // Passa a Vez
                    }
                }
                else if ((tipoPV || tipoDV) && (objetivoB) && (podeIr) && (!jogo.vezJogador())){
                    verificar.removerPeca();                // Remove a Peça Branca da casa Verificada
                    peca.movimentacao(destino);                    // Move a Peça para o Destino
                    jogo.decrementaBrancas();               // Decrementa as Peças Brancas
                    virarDama(destino);               // Verifica se pode Virar Dama
                    virarPeca(destino);               // Verifica se pode Virar peça normal
                    CapturaSeguida(destino, jogo);    // Verifica se tem mais peças pra capturar
                    if (!jogo.podeComerSeguido()){
                        jogo.passarVez();                   // Passa a Vez
                    }
                }
            }
        }
        
        if(destino3){
            /** Verifica se a casa entre a Origem e o Destino tem peça **/
            Casa verificar = jogo.getTabuleiro().getCasa(origem.getCasaX()-1, origem.getCasaY()-1);
            Peca objetivo = verificar.getPeca();
            if (objetivo != null){
                int tipoObjetivo = objetivo.getTipo();
                boolean objetivoB = ((objetivo.getTipo() == objetivo.PEDRA_BRANCA) || //Verifica o Tipo da peça que tá entre
                                     (objetivo.getTipo() == objetivo.DAMA_BRANCA));   //a Origem e o Destino é Branca
                boolean objetivoV = ((objetivo.getTipo() == objetivo.PEDRA_VERMELHA) || //Verifica o Tipo da peça que tá entre
                                     (objetivo.getTipo() == objetivo.DAMA_VERMELHA));   //a Origem e o Destino é Vermelha
                
                /** Verifica se o Destino tem peça **/
                boolean podeIr = !destino.possuiPeca();
                
                if ((tipoPB || tipoDB) && (objetivoV) && (podeIr) && (jogo.vezJogador())){
                    verificar.removerPeca();                // Remove a Peça Vermelha da casa Verificada
                    peca.movimentacao(destino);                    // Move a Peça para o Destino
                    jogo.decrementaVermelhas();             // Decrementa as Peças Vermelhas
                    virarDama(destino);               // Verifica se pode Virar Dama
                    virarPeca(destino);               // Verifica se pode Virar peça normal
                    CapturaSeguida(destino, jogo);    // Verifica se tem mais peças pra capturar
                    if (!jogo.podeComerSeguido()){
                        jogo.passarVez();                   // Passa a Vez
                    }
                }
                else if ((tipoPV || tipoDV) && (objetivoB)&& (podeIr) && (!jogo.vezJogador())){
                    verificar.removerPeca();                // Remove a Peça Branca da casa Verificada
                    peca.movimentacao(destino);                    // Move a Peça para o Destino
                    jogo.decrementaBrancas();               // Decrementa as Peças Brancas
                    virarDama(destino);               // Verifica se pode Virar Dama
                    virarPeca(destino);               // Verifica se pode Virar peça normal
                    CapturaSeguida(destino, jogo);    // Verifica se tem mais peças pra capturar
                    if (!jogo.podeComerSeguido()){
                        jogo.passarVez();                   // Passa a Vez
                    }
                }
            }
        }
        
        if(destino4){
            /** Verifica se a casa entre a Origem e o Destino tem peça **/
            Casa verificar = jogo.getTabuleiro().getCasa(origem.getCasaX()+1, origem.getCasaY()-1);
            Peca objetivo = verificar.getPeca();
            if (objetivo != null){
                int tipoObjetivo = objetivo.getTipo();
                boolean objetivoB = ((objetivo.getTipo() == objetivo.PEDRA_BRANCA) || //Verifica o Tipo da peça que tá entre
                                     (objetivo.getTipo() == objetivo.DAMA_BRANCA));   //a Origem e o Destino é Branca
                boolean objetivoV = ((objetivo.getTipo() == objetivo.PEDRA_VERMELHA) || //Verifica o Tipo da peça que tá entre 
                                     (objetivo.getTipo() == objetivo.DAMA_VERMELHA));   //a Origem e o Destino é Vermelha
                
                /** Verifica se o Destino tem peça **/
                boolean podeIr = !destino.possuiPeca();

                if ((tipoPB || tipoDB) && (objetivoV) && (podeIr) && (jogo.vezJogador())){
                    verificar.removerPeca();                // Remove a Peça Vermelha da casa Verificada
                    peca.movimentacao(destino);                    // Move a Peça para o Destino
                    jogo.decrementaVermelhas();             // Decrementa as Peças Vermelhas
                    virarDama(destino);               // Verifica se pode Virar Dama
                    virarPeca(destino);               // Verifica se pode Virar peça normal
                    CapturaSeguida(destino, jogo);    // Verifica se tem mais peças pra capturar
                    if (!jogo.podeComerSeguido()){
                        jogo.passarVez();                   // Passa a Vez
                    }
                }
                else if ((tipoPV || tipoDV) && (objetivoB)&& (podeIr) && (!jogo.vezJogador())){
                    verificar.removerPeca();                // Remove a Peça Branca da casa Verificada
                    peca.movimentacao(destino);                    // Move a Peça para o Destino
                    jogo.decrementaBrancas();               // Decrementa as Peças Brancas
                    virarDama(destino);               // Verifica se pode Virar Dama
                    virarPeca(destino);               // Verifica se pode Virar peça normal
                    CapturaSeguida(destino, jogo);    // Verifica se tem mais peças pra capturar
                    if (!jogo.podeComerSeguido()){
                        jogo.passarVez();                   // Passa a Vez
                    }
                }
            }
        }
    }

    /**
     * Valor    Tipo
     *   0   Branca (Pedra)
     *   1   Branca (Dama)
     *   2   Vermelha (Pedra)
     *   3   Vermelha (Dama)
     * retorna o tipo da peca.
     */
    public int getTipo() {
        return tipo;
    }
    
    /**
     * Transforma a Pedra em Dama
     */
    public void virarDama(Casa casa){
        boolean tipoPB = (this.getTipo() == PEDRA_BRANCA);      // Verifica se é Pedra Branca
        boolean tipoPV = (this.getTipo() == PEDRA_VERMELHA);    // Verifica se é Pedra Vermelha
        
        if((tipoPB) && (casa.getCasaY() == 7)){
            tipo = DAMA_BRANCA;                                 // Transforma em Dama Branca
        }
        else if((tipoPV) && (casa.getCasaY() == 0)){
            tipo = DAMA_VERMELHA;                               // Transforma em Dama Vermelha
        }
    }
    
    /**
     * Transforma a Dama em Pedra
     */
    public void virarPeca(Casa casa){
        boolean tipoDB = (this.getTipo() == DAMA_BRANCA);       // Verifica se é Dama Branca
        boolean tipoDV = (this.getTipo() == DAMA_VERMELHA);     // Verifica se é Dama Vermelha
        
        if((tipoDB) && (casa.getCasaY() == 0)){
            tipo = PEDRA_BRANCA;                                // Transforma em Pedra Branca
        }
        else if((tipoDV) && (casa.getCasaY() == 7)){
            tipo = PEDRA_VERMELHA;                              // Transforma em Pedra Vermelha
        }
    }

    /**
     * Realiza a Captura Seguida de Pecas
     */
    public void CapturaSeguida(Casa atual, Jogo jogo){
        /** Verifica se o Tipo da Peça é Branco **/
        boolean tipoB = ((this.getTipo() == PEDRA_BRANCA) || (this.getTipo() == DAMA_BRANCA));
        
        /** Verifica se o Tipo da Peça é Vermelho **/
        boolean tipoV = ((this.getTipo() == PEDRA_VERMELHA) || (this.getTipo() == DAMA_VERMELHA));
        
        /** Verifica a Casa do 1º Quadrante **/
        Casa verificar1 = jogo.getTabuleiro().getCasa(atual.getCasaX()+1, atual.getCasaY()+1);

        /** Verifica a Casa do 2º Quadrante **/        
        Casa verificar2 = jogo.getTabuleiro().getCasa(atual.getCasaX()-1, atual.getCasaY()+1);

        /** Verifica a Casa do 3º Quadrante **/        
        Casa verificar3 = jogo.getTabuleiro().getCasa(atual.getCasaX()-1, atual.getCasaY()-1);

        /** Verifica a Casa do 4º Quadrante **/
        Casa verificar4 = jogo.getTabuleiro().getCasa(atual.getCasaX()+1, atual.getCasaY()-1);
        
        if(verificar1 != null){
            /** Verifica se a Casa Alvo tem peça **/
            boolean possuiPeca = verificar1.possuiPeca();
            if (possuiPeca){
                Peca objetivo = verificar1.getPeca();
                int tipoObjetivo = objetivo.getTipo();
                
                boolean objetivoB = ((objetivo.getTipo() == objetivo.PEDRA_BRANCA) ||   // Verifica o Tipo da peça a capturar
                                     (objetivo.getTipo() == objetivo.DAMA_BRANCA));     // é Branca
                boolean objetivoV = ((objetivo.getTipo() == objetivo.PEDRA_VERMELHA) || // Verifica o Tipo da Peça a Capturar
                                     (objetivo.getTipo() == objetivo.DAMA_VERMELHA));   // é Vermelha
                
                /** Verifica se a casa destino existe **/
                Casa destino = jogo.getTabuleiro().getCasa(atual.getCasaX()+2, atual.getCasaY()+2);
                
                if(destino != null){    
                    /** Verifica se o Destino tem peça **/
                    boolean fim = destino.possuiPeca();
                    if (!fim){
                        if(objetivoV && tipoB){
                            jogo.alteraComerSeguido(true, this);    // Altera a Variavel comerSeguido e peçaAComer da classe jogo
                            jaVerificado = true;                    // Altera a variavel já verificado para que não entre novamente
                        }
                        else if (objetivoB && tipoV){
                            jogo.alteraComerSeguido(true, this);    // Altera a Variavel comerSeguido e peçaAComer da classe jogo
                            jaVerificado = true;                    // Altera a variavel já verificado para que não entre novamente
                        }
                    }
                }
                else if(!jaVerificado){
                    jogo.alteraComerSeguido(false, null);           // Altera a Variavel comerSeguido e peçaAComer da classe jogo
                }
            }
            else if(!jaVerificado){
                jogo.alteraComerSeguido(false, null);               // Altera a Variavel comerSeguido e peçaAComer da classe jogo
            }
        }
        
        if(verificar2 != null){
            /** Verifica se a Casa alvo tem Peça **/
            boolean possuiPeca = verificar2.possuiPeca();
            
            if (possuiPeca){
                Peca objetivo = verificar2.getPeca();
                int tipoObjetivo = objetivo.getTipo();
                
                boolean objetivoB = ((objetivo.getTipo() == objetivo.PEDRA_BRANCA) ||   // Verifica o Tipo da peça a capturar
                                     (objetivo.getTipo() == objetivo.DAMA_BRANCA));     // é Branca
                boolean objetivoV = ((objetivo.getTipo() == objetivo.PEDRA_VERMELHA) || // Verifica o Tipo da Peça a Capturar
                                     (objetivo.getTipo() == objetivo.DAMA_VERMELHA));   // é Vermelha
                
                /** Verifica se a casa destino existe **/
                Casa destino = jogo.getTabuleiro().getCasa(atual.getCasaX()-2, atual.getCasaY()+2);
                if(destino != null){    
                    boolean fim = destino.possuiPeca();
                    if (!fim){
                        if(objetivoV && tipoB){
                            jogo.alteraComerSeguido(true, this);    // Altera a Variavel comerSeguido e peçaAComer da classe jogo
                            jaVerificado = true;                    // Altera a variavel já verificado para que não entre novamente
                        }
                        else if (objetivoB && tipoV){
                            jogo.alteraComerSeguido(true, this);    // Altera a Variavel comerSeguido e peçaAComer da classe jogo
                            jaVerificado = true;                    // Altera a variavel já verificado para que não entre novamente
                        }
                    }
                }
                else if(!jaVerificado){
                    jogo.alteraComerSeguido(false, null);           // Altera a Variavel comerSeguido e peçaAComer da classe jogo
                }
            }
            else if(!jaVerificado){
                jogo.alteraComerSeguido(false, null);               // Altera a Variavel comerSeguido e peçaAComer da classe jogo
            }
        }
        
        if(verificar3 != null){
            /** Verifica se a Casa alvo tem Peça **/
            boolean possuiPeca = verificar3.possuiPeca();
            if (possuiPeca){
                Peca objetivo = verificar3.getPeca();
                int tipoObjetivo = objetivo.getTipo();
                
                boolean objetivoB = ((objetivo.getTipo() == objetivo.PEDRA_BRANCA) ||   // Verifica o Tipo da peça a capturar
                                     (objetivo.getTipo() == objetivo.DAMA_BRANCA));     // é Branca
                boolean objetivoV = ((objetivo.getTipo() == objetivo.PEDRA_VERMELHA) || // Verifica o Tipo da Peça a Capturar
                                 (objetivo.getTipo() == objetivo.DAMA_VERMELHA));       // é Vermelha
            
                /** Verifica se a casa destino existe **/
                Casa destino = jogo.getTabuleiro().getCasa(atual.getCasaX()-2, atual.getCasaY()-2);
                if(destino != null){    
                    boolean fim = destino.possuiPeca();
                    if (!fim){
                        if(objetivoV && tipoB){
                            jogo.alteraComerSeguido(true, this);     // Altera a Variavel comerSeguido e peçaAComer da classe jogo
                            jaVerificado = true;                     // Altera a variavel já verificado para que não entre novamente 
                        }
                        else if (objetivoB && tipoV){
                            jogo.alteraComerSeguido(true, this);     // Altera a variavel já verificado para que não entre novamente
                            jaVerificado = true;                     // Altera a variavel já verificado para que não entre novamente
                        }
                    }
                }
                else if(!jaVerificado){
                    jogo.alteraComerSeguido(false, null);            // Altera a variavel já verificado para que não entre novamente
                }
            }
            else if(!jaVerificado){
                jogo.alteraComerSeguido(false, null);                // Altera a variavel já verificado para que não entre novamente
            }
        }
        
        if(verificar4 != null){
            /** Verifica se a Casa alvo tem Peça **/
            boolean possuiPeca = verificar4.possuiPeca();
            if (possuiPeca){
                Peca objetivo = verificar4.getPeca();
                int tipoObjetivo = objetivo.getTipo();
                
                boolean objetivoB = ((objetivo.getTipo() == objetivo.PEDRA_BRANCA) ||   // Verifica o Tipo da peça a capturar
                                     (objetivo.getTipo() == objetivo.DAMA_BRANCA));     // é Branca
                boolean objetivoV = ((objetivo.getTipo() == objetivo.PEDRA_VERMELHA) || // Verifica o Tipo da Peça a Capturar
                                     (objetivo.getTipo() == objetivo.DAMA_VERMELHA));   // é Vermelha
                
                /** Verifica se a casa destino existe **/
                Casa destino = jogo.getTabuleiro().getCasa(atual.getCasaX()+2, atual.getCasaY()-2);
                if(destino != null){    
                    boolean fim = destino.possuiPeca();
                    if (!fim){
                        if(objetivoV && tipoB){
                            jogo.alteraComerSeguido(true, this);     // Altera a Variavel comerSeguido e peçaAComer da classe jogo
                            jaVerificado = true;                     // Altera a variavel já verificado para que não entre novamente
                        }
                        else if (objetivoB && tipoV){
                            jogo.alteraComerSeguido(true, this);    // Altera a Variavel comerSeguido e peçaAComer da classe jogo
                            jaVerificado = true;                    // Altera a variavel já verificado para que não entre novamente
                        }
                    }
                }
                else if(!jaVerificado){
                    jogo.alteraComerSeguido(false, null);           // Altera a Variavel comerSeguido e peçaAComer da classe jogo
                }
            }
        }
        if((verificar1 == null) && (verificar2 == null) && (verificar3 == null) && (verificar4 == null) && (!jaVerificado)){
            jogo.alteraComerSeguido(false, null);                   // Altera a Variavel comerSeguido e peçaAComer da classe jogo
        }
        /** Altera a Variavel para falso para que no proximo ciclo a verificação ocorra de forma correta **/
        jaVerificado = false;
        
    }
}

