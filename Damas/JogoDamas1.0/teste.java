
/**
 * Escreva a descrição da classe teste aqui.
 * 
 * @author (seu nome) 
 * @version (número de versão ou data)
 */
public class teste
{
    // variáveis de instância - substitua o exemplo abaixo pelo seu próprio
    private int x;

    /**
     * COnstrutor para objetos da classe teste
     */
    public teste()
    {
        // inicializa variáveis de instância
        x = 0;
    
        if ((tipoDePeca == 1) && (vezJogar == true)){
            /**
             * Movimento de Jogo das peças Brancas
             */
            if (((destinoX%2 == 0) && destinoY % 2 == 0) ||((destinoX%2 != 0) && destinoY % 2 != 0)){ 
                // Permite o Movimento em Diiagonal
                if(podeIR == false){ 
                    // Só permite ir se tiver livre a casa 
                    if((destinoY - origemY > 0)&&((destinoX - origemX ==(destinoY - origemY))||(destinoX - origemX ==-(destinoY - origemY)))){
                        // Movimento Simples de Peça 
                        peca.mover(destino);
                        vezJogar = false;
                    }
                    else if((destinoX == origemX+2) && (destinoY == origemY+2)){
                        // Movimento de captura de Peça em 1º Quadrante 
                        Casa verificar = tabuleiro.getCasa(origemX+1, origemY+1);
                        boolean verificaCasa = verificar.possuiPeca();
                        if (verificaCasa){
                            Peca verificarPeca = verificar.getPeca();
                            int tipoDePecaVerificada = verificarPeca.getTipo();
                            if((tipoDePecaVerificada == 2) || (tipoDePecaVerificada == 3)){
                                peca.mover(destino);
                                verificar.removerPeca();
                                nPecaVermelhas--;
                                vezJogar = false;
                            }
                        }
                    }
                    
                    else if ((destinoX == origemX-2) && (destinoY == origemY+2)){
                        // Movimento de captura de Peça em 2º Quadrante
                        Casa verificar = tabuleiro.getCasa(origemX-1, origemY+1);
                        boolean verificaCasa = verificar.possuiPeca();
                        if (verificaCasa){
                            Peca verificarPeca = verificar.getPeca();
                            int tipoDePecaVerificada = verificarPeca.getTipo();
                            if((tipoDePecaVerificada == 2) || (tipoDePecaVerificada == 3)){
                                peca.mover(destino);
                                verificar.removerPeca();
                                nPecaVermelhas--;
                                vezJogar = false;
                            }
                        }
                    }
                    
                    else if ((destinoX == origemX-2) && (destinoY == origemY-2)){
                        // Movimento de captura de Peça em 3º Quadrante 
                        Casa verificar = tabuleiro.getCasa(origemX-1, origemY-1);
                        boolean verificaCasa = verificar.possuiPeca();
                        if (verificaCasa){
                            Peca verificarPeca = verificar.getPeca();
                            int tipoDePecaVerificada = verificarPeca.getTipo();
                            if((tipoDePecaVerificada == 2) || (tipoDePecaVerificada == 3)){
                                peca.mover(destino);
                                verificar.removerPeca();
                                nPecaVermelhas--;
                                vezJogar = false;
                            }
                        }
                    }
                    
                    else if((destinoX == origemX+2) && (destinoY == origemY-2)){
                        // Movimento de captura de Peça em 4º Quadrante 
                        Casa verificar = tabuleiro.getCasa(origemX+1, origemY-1);
                        boolean verificaCasa = verificar.possuiPeca();
                        if (verificaCasa){
                            Peca verificarPeca = verificar.getPeca();
                            int tipoDePecaVerificada = verificarPeca.getTipo();
                            if((tipoDePecaVerificada == 2) || (tipoDePecaVerificada == 3)){
                                peca.mover(destino);
                                verificar.removerPeca();
                                nPecaVermelhas--;
                                vezJogar = false;
                            }
                        }
                    }
                }
            }
        }
    /**
     * Exemplo de método - substitua este comentário pelo seu próprio
     * 
     * @param  y   exemplo de um parâmetro de método
     * @return     a soma de x com y 
     */
    public int sampleMethod(int y)
    {
        // ponha seu código aqui
        return x + y;
    }
}
