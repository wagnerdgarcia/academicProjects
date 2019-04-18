/**
 * Escreva a descrição da classe Torre aqui.
 * 
 * @author (seu nome) 
 * @version (número de versão ou data)
 */
public class Torre extends Peca
{
    /**
     * Construtor para objetos da classe Torre
     */
    public Torre(Casa casa, boolean tipo)
    {
        super(casa, tipo);
    }
    
    /**
     * Movimenta a peca para uma nova casa.
     * @param destino nova casa que ira conter esta peca.
     */
    public void mover(Casa destino, Jogo jogo){
        movimentoSimples(destino, Jogo);
    }
    
    /**
     * Realiza o movimento Simples da Torre
     */
    private void movimentoSimples(Casa destino, Jogo jogo){
        Casa origem = getCasa();
        boolean podeMover = true;
        if ((origem.getX() != destino.getX()) && (origem.getY() == destino.getY())){
            if (origem.getX() < destino.getX()){
                for (int x = origem.getX(); x < destino.getX(); x++){
                    Casa casaVerificada = jogo.getTabuleiro().getCasa(x, origem.getY());
                    if(casaVerificada.possuiPeca()){
                        podeMover = false;
                    }
                }
            }
            else if (origem.getX() > destino.getX()){
                for (int x = destino.getX(); x < origem.getX(); x++){
                    Casa casaVerificada = jogo.getTabuleiro().getCasa(x, origem.getY());
                    if(casaVerificada.possuiPeca()){
                        podeMover = false;
                    }
                }
            }
        }
        
        if ((origem.getY() != destino.getY()) && (origem.getX() == destino.getX())){
            ;
        }
    }
}