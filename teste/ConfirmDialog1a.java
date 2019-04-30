import javax.swing.*;

public class ConfirmDialog1a {
    public ConfirmDialog1a(){
        ;
    }
    public static void main() {
        String[] opcoes = new String[4];
        opcoes[0] = "Cavalo";
        opcoes[1] = "Bispo";
        opcoes[2] = "Torre";
        opcoes[3] = "Rainha";
        
        int x = JOptionPane.showOptionDialog(null, "Escolha a Peça que deseja Promover",
                "Peça a Promoção",
                JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, opcoes, opcoes[0]);
        System.out.println(x);

    }
}