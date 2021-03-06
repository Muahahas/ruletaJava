package ruleta;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Random;
import java.util.ResourceBundle;

import java.util.logging.Level;
import java.util.logging.Logger;

public class Ruleta {

    private static final ResourceBundle bundles = ResourceBundle.getBundle("Lang/bundles");

    public static void main(String[] args) {
        BufferedReader bufferRead = new BufferedReader(new InputStreamReader(System.in));
        
        Random rand = new Random();

        int capital = 1000, aposta = 0, numero = 0, sorteig = 0, nguanyades = 0, nperdudes = 0;
        System.out.println(bundles.getString("Welcome"));
        
        System.out.println(java.text.MessageFormat.format(bundles.getString("YouHave {0} HowMuchBet"), new Object[]{capital}));
        try {
            aposta = Integer.parseInt(bufferRead.readLine());
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        while (aposta != 0 && capital != 0) {
            //Entrara en aquest bucle mentres s'aposti mes de 0 i es tingui capital.	
            if (aposta <= capital && aposta > 0) {
                //Si l'aposta es correcta, es a dir, menor o igual que el capital i positiva ens demanara el numero al que volem apostar.			
                System.out.println(bundles.getString("NumberToBet"));
                try {
                    numero = Integer.parseInt(bufferRead.readLine());
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
                while ((numero <= 0) || (numero >= 37)) {
                    //Si apostem a un numero erroni que no esta entre 1 i 36 ambdos inclosos ens dira que es erroni i que n'introduim un altre.
                    System.out.println(bundles.getString("InvalidNumberRetry"));
                    System.out.println(bundles.getString("NumberToBet"));
                    try {
                        numero = Integer.parseInt(bufferRead.readLine());
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                }
                //Quan el numero sigui correcte sortira del bucle, fara el sorteig i ens dira si em guanyat o perdut.
                sorteig = rand.nextInt(37);
                System.out.println(java.text.MessageFormat.format(bundles.getString("YouBet {0}NumberIs {1}"), new Object[]{numero, sorteig}));
                if (sorteig == numero) {
                    aposta = aposta * 36;
                    System.out.println(java.text.MessageFormat.format(bundles.getString("YouWin{0}"), new Object[]{aposta}));
                    capital = capital + aposta;
                    nguanyades += 1;
                } else {
                    System.out.println(java.text.MessageFormat.format(bundles.getString("YouLose{0}"), new Object[]{aposta}));
                    capital = capital - aposta;
                    nperdudes += 1;
                }
                if (capital != 0) {
                    //Ens donara l'opcio continuar jugant si el capital no es 0, perque en cas d'arruinar-nos no ens ho demani.
                    System.out.println(java.text.MessageFormat.format(bundles.getString("YouHave {0} HowMuchBet"), new Object[]{capital}));
                    try {
                        aposta = Integer.parseInt(bufferRead.readLine());
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                }

            } else {
                //Si la cantitat apostada era negativa o superior al capital ens dira que ho tornem a provar.
                System.out.println(bundles.getString("InvalidBetRetry"));
                try {
                    aposta = Integer.parseInt(bufferRead.readLine());
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        }
        //Si t'has arruinat et dona un misatge
        if (capital == 0) {
            System.out.println(bundles.getString("BadLuck"));
        } //Si ens queda ens en mostrara la quantitat
        else {
            System.out.println(java.text.MessageFormat.format(bundles.getString("FinalMoney{0}."), new Object[]{capital}));
        }
        //Finalment ens mostra les partides guanyades i perdudes.

        System.out.print(java.text.MessageFormat.format(bundles.getString("Wined{0}Losed{1}."), new Object[]{nguanyades, nperdudes}));

    }
}
