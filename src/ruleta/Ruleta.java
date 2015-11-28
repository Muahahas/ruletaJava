package ruleta;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Random;

import java.util.logging.Level;
import java.util.logging.Logger;

public class Ruleta {

    public static void main(String[] args) {
        Random rand = new Random();
        BufferedReader bufferRead = new BufferedReader(new InputStreamReader(System.in));
        int capital = 1000, aposta = 0, numero = 0, sorteig = 0, nguanyades = 0, nperdudes = 0;
        System.out.println("Benvingut a la ruleta de la sort");
        System.out.println("Tens un capital " + capital + " quan vols apostar?");
        try {
            aposta = Integer.parseInt(bufferRead.readLine());
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        while (aposta != 0 && capital != 0) {
            //Entrara en aquest bucle mentres s'aposti mes de 0 i es tingui capital.	
            if (aposta <= capital && aposta > 0) {
                //Si l'aposta es correcta, es a dir, menor o igual que el capital i positiva ens demanara el numero al que volem apostar.			
                System.out.println("Introdueix el numero al qual vols apostar:");
                try {
                    numero = Integer.parseInt(bufferRead.readLine());
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
                while ((numero <= 0) || (numero >= 37)) {
                    //Si apostem a un numero erroni que no esta entre 1 i 36 ambdos inclosos ens dira que es erroni i que n'introduim un altre.
                    System.out.println("No has introduit un numero valid torna-ho a intentar!");
                    System.out.println("Introdueix el numero al qual vols apostar: ");
                    try {
                        numero = Integer.parseInt(bufferRead.readLine());
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                }
                //Quan el numero sigui correcte sortira del bucle, fara el sorteig i ens dira si em guanyat o perdut.
                sorteig = rand.nextInt(37);
                System.out.println("Has apostat al numero " + numero + " i ha sortit el numero " + sorteig);
                if (sorteig == numero) {
                    aposta = aposta * 36;
                    System.out.println("Has guanyat " + aposta + "e.");
                    capital = capital + aposta;
                    nguanyades += 1;
                } else {
                    System.out.println("Has perdut " + aposta + "e.");
                    capital = capital - aposta;
                    nperdudes += 1;
                }
                if (capital != 0) {
                    //Ens donara l'opcio continuar jugant si el capital no es 0, perque en cas d'arruinar-nos no ens ho demani.
                    System.out.println("Tens un capital " + capital + "e, quan vols apostar?");
                    try {
                        aposta = Integer.parseInt(bufferRead.readLine());
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                }

            } else {
                //Si la cantitat apostada era negativa o superior al capital ens dira que ho tornem a provar.
                System.out.println("No has introduit una quantitat valida torna-ho a intentar!");
                try {
                    aposta = Integer.parseInt(bufferRead.readLine());
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        }
        //Si t'has arruinat et dona un misatge
        if (capital==0)
	System.out.println("Mala sort! Ho has perdut tot!");
        //Si ens queda ens en mostrara la quantitat
        else
	System.out.println("El teu capital final es: "+capital+".");
	//Finalment ens mostra les partides guanyades i perdudes.
	System.out.print("Has guanyat "+ nguanyades + " partides i n'has perdut "+ nperdudes+".");


    }
}
