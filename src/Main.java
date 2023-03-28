import javax.swing.*;
import java.util.ArrayList;

public class Main {
    public static void testimine() {
        System.out.println("Loon 3 suvalist paletti:");
        Harmoonia h = new Harmoonia();
        ArrayList<Palett> paletid = new ArrayList<>();
        //3 suvalise algvärviga paletti
        for (int i = 0; i < 3; i++) {
            int randomR = (int) (Math.random() * 255);
            int randomG = (int) (Math.random() * 255);
            int randomB = (int) (Math.random() * 255);
            Varv randomVarv = new Varv(randomR, randomG, randomB);
            Palett p = new Palett(randomVarv);
            paletid.add(p);
        }

        int i = 1;
        for (Palett palett : paletid) {
            Varv varv = palett.getHarmoonia().get(0);
            h.genereeriKomplementaarneVärv(varv, palett);
            h.genereeriAnaloogsedVärvid(varv, palett);
            System.out.println(i++ + ". värvipalett");
            for (Varv v : palett.getHarmoonia()) {
                System.out.println(v);
            }
            System.out.println();
        }

    }

    public static void main(String[] args) throws Exception{
        //Eeldame, et sisend on korrektne.
        String punane = JOptionPane.showInputDialog(null, "Sisesta punase väärtus (0-255) ", "Andmete sisestamine",
                JOptionPane.QUESTION_MESSAGE);
        String roheline = JOptionPane.showInputDialog(null, "Sisesta rohelise väärtus (0-255) ", "Andmete sisestamine",
                JOptionPane.QUESTION_MESSAGE);
        String sinine = JOptionPane.showInputDialog(null, "Sisesta sinise väärtus (0-255) ", "Andmete sisestamine",
                JOptionPane.QUESTION_MESSAGE);

        int r = Integer.parseInt(punane);
        int g = Integer.parseInt(roheline);
        int b = Integer.parseInt(sinine);

        //Loome isendid
        Harmoonia h = new Harmoonia();
        Varv varv = new Varv(r, g, b);
        Palett palett = new Palett(varv);

        //Testime meetodite tööd
        //h.genereeriAnaloogsedVärvid(varv, palett);
        //h.genereeriKomplementaarneVärv(varv, palett);
        h.genereeriNelinurknePalett(varv, palett);
        System.out.println("Kasutaja sisestatud värvi põhjal:");
        for (Varv v : palett.getHarmoonia()) {
            System.out.println(v);
        }
        System.out.println();

        Muster.salvesta(Muster.looSuprematism(palett.getHarmoonia(), 500, 500), "pildike.png");

        //Teeme randomiga ka
        testimine();

    }
}