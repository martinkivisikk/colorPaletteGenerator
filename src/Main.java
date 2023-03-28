import javax.swing.*;
import java.util.ArrayList;

public class Main {
    public static void testimine() {
        System.out.println("Loon 3 suvalist palletti:");
        Harmoonia h = new Harmoonia();
        ArrayList<Pallett> palletid = new ArrayList<>();
        //3 suvalise algvärviga palletti
        for (int i = 0; i < 3; i++) {
            int randomR = (int) (Math.random() * 255);
            int randomG = (int) (Math.random() * 255);
            int randomB = (int) (Math.random() * 255);
            Varv randomVarv = new Varv(randomR, randomG, randomB);
            Pallett p = new Pallett(randomVarv);
            palletid.add(p);
        }

        int i = 1;
        for (Pallett pallett : palletid) {
            Varv varv = pallett.getHarmoonia().get(0);
            h.genereeriKomplementaarneVärv(varv, pallett);
            h.genereeriAnaloogsedVärvid(varv, pallett);
            System.out.println(i++ + ". värvipallett");
            for (Varv v : pallett.getHarmoonia()) {
                System.out.println(v);
            }
            System.out.println();
        }

    }

    public static void main(String[] args) {
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
        Pallett pallett = new Pallett(varv);

        //Testime meetodite tööd
        h.genereeriAnaloogsedVärvid(varv, pallett);
        h.genereeriKomplementaarneVärv(varv, pallett);
        for (Varv v : pallett.getHarmoonia()) {
            System.out.println(v);
        }
        System.out.println();

        //Teeme randomiga ka
        testimine();

    }
}