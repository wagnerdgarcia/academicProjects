
/**
 * Escreva a descrição da classe NPecas aqui.
 * 
 * @author (seu nome) 
 * @version (número de versão ou data)
 */
public class NPecas
{
    // variáveis de instância - substitua o exemplo abaixo pelo seu próprio
    public int nPecaBrancas;
    public int nPecaVermelhas;
    
    /**
     * Construtor para objetos da classe NPecas
     */
    public NPecas(int Branca, int Vermelhas)
    {
        // inicializa variáveis de instância
        nPecaBrancas = Branca;
        nPecaVermelhas = Vermelhas;
    }

    /**
     * Incrementa o numero de pecas Brancas 
     */
    public void IncrementaBrancas(){
        nPecaBrancas++;
    }
    
    /**
     * Incrementa o numero de pecas Vermelhas
     */
    public void IncrementaVermelhas(){
        nPecaVermelhas++;
    }
    
    /**
     * Incrementa o numero de pecas Brancas 
     */
    public void DecrementaBrancas(){
        nPecaBrancas--;
    }
    
    /**
     * Incrementa o numero de pecas Vermelhas
     */
    public void DecrementaVermelhas(){
        nPecaVermelhas--;
    }
}
