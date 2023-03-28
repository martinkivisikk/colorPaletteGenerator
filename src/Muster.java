import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.List;

import static java.lang.Math.cos;
import static java.lang.Math.sin;

//kasutab Java EPI klasse, mis lasevad luua maatriksitest pilte.
//maatriksid olgu kahemõõtmelised, igale valitud harmoonilisele värvile vastaku näiteks üks täisarv.
//vt. programmeerimine 2 pilditöötluse abiklassi.
//see, kuidas ja mis kujundeid maatriksisse luua, on veel toores idee. äkki fraktaalid? siis saaks rekursiooni kasutada.
//äkki hoopis mingid sujuvad leegilaadsed või n.ö. looduslikud kujundid ehk midagi art nouveau/juugendlikku? vajaks korralikku algoritmi.
public class Muster {
    public static BufferedImage looVeerud(List<Varv> varvid, int w, int h) {
        int varvideArv = varvid.size();
        BufferedImage bImg = new BufferedImage(w, h, BufferedImage.TYPE_3BYTE_BGR);
        Graphics2D g2d = bImg.createGraphics();
        int[] laiused = new int[varvideArv];
        int minVeeruLaius = w / varvideArv; //täisarvuline jagamine
        int ekstra = w;
        for (int i = 0; i < varvideArv; i++) {
            laiused[i] = minVeeruLaius;
            ekstra -= minVeeruLaius;
        }
        if (ekstra > 0) {
            if (varvideArv % 2 == 1) {
                laiused[varvideArv / 2] += ekstra;
            } else {
                laiused[0] += (int) Math.ceil(ekstra / 2.0);
                laiused[varvideArv - 1] = ekstra / 2;
            }
        }
        int xAlgus = 0;
        for (int v = 0; v < varvideArv; v++) {
            Varv varv = varvid.get(v);
            int r = varv.getR();
            int g = varv.getG();
            int b = varv.getB();
            g2d.setPaint(new Color(r, g, b));
            g2d.fillRect(xAlgus, 0, laiused[v], h);
            xAlgus += laiused[v];
        }
        return bImg;
    }
    public static BufferedImage looRead(List<Varv> varvid, int w, int h) {
        int varvideArv = varvid.size();
        BufferedImage bImg = new BufferedImage(w, h, BufferedImage.TYPE_3BYTE_BGR);
        Graphics2D g2d = bImg.createGraphics();
        int[] laiused = new int[varvideArv];
        int minReaLaius = h / varvideArv; //täisarvuline jagamine
        int ekstra = h;
        for (int i = 0; i < varvideArv; i++) {
            laiused[i] = minReaLaius;
            ekstra -= minReaLaius;
        }
        if (ekstra > 0) {
            if (varvideArv % 2 == 1) {
                laiused[varvideArv / 2] += ekstra;
            }
            else {
                laiused[0] += (int) Math.ceil(ekstra / 2.0);
                laiused[varvideArv - 1] = ekstra / 2;
            }
        }
        int yAlgus = 0;
        for (int v = 0; v < varvideArv; v++) {
            Varv varv = varvid.get(v);
            int r = varv.getR();
            int g = varv.getG();
            int b = varv.getB();
            g2d.setPaint(new Color(r, g, b));
            g2d.fillRect(0, yAlgus, w, laiused[v]);
            yAlgus += laiused[v];
        }
        return bImg;
    }

    public static BufferedImage looSuprematism(List<Varv> varvid, int w, int h) {
        int varvideArv = varvid.size();
        int kujundeid = (int) (Math.random() * 51);

        BufferedImage bImg = new BufferedImage(w, h, BufferedImage.TYPE_3BYTE_BGR);
        Graphics2D g2d = bImg.createGraphics();

        g2d.setPaint(new Color (240, 240, 200));
        g2d.fillRect(0, 0, w, h);
        for (int i = 1; i < kujundeid; i++) {

            Varv varv = varvid.get((int)(Math.random()*varvid.size()));
            Color c = new Color(varv.getR(), varv.getG(), varv.getB());
            int kujund = (int) (3 - Math.random() * 7);


            if (kujund == 0) {  //joon
                int x1 = (int) (Math.random()*w);
                int y1 = (int) (Math.random()*h);
                int pikkus = (int) (kujundeid * (Math.random() * 10 + 100) / i);
                double nurk = Math.random()*360;
                int paksus = (int) (Math.random() * 4 + 3);
                int x2 = (int) (x1 + pikkus * cos(nurk));
                int y2 = (int) (y1 + pikkus * sin(nurk));
                g2d.setPaint(c);
                g2d.setStroke(new BasicStroke(paksus));
                g2d.draw(new Line2D.Float(x1, y1, x2, y2));
            }
            else if (kujund == 1) { //ringjoon
                int diameeter = (int) (kujundeid * (Math.random() * 8 + 50) / i);
                int x = (int) (Math.random() * w) - diameeter / 2;
                int y = (int) (Math.random() * h) - diameeter / 2;
                int paksus = (int) (Math.random() * 4 + 3);
                g2d.setPaint(c);
                g2d.setStroke(new BasicStroke(paksus));
                g2d.draw(new Ellipse2D.Float(x, y, diameeter, diameeter));
            }
            else if (kujund == 2) { // tühi kolmnurk
                int x1 = (int) (Math.random() * w);
                int y1 = (int) (Math.random() * h);
                int pikkus1 = (int) (kujundeid * (Math.random() * 10 + 50) / i);
                double nurk1 = Math.random() * 360;
                double nurk2 = Math.random() * 330 + 30;
                int paksus = (int) (Math.random() * 4 + 3);
                int x2 = (int) (x1 + pikkus1 * cos(nurk1));
                int y2 = (int) (y1 + pikkus1 * sin(nurk1));
                double pikkus2Kordaja = Math.random() + 0.5;
                int x3 = (int) (x1 + pikkus1 * cos(nurk2) * pikkus2Kordaja);
                int y3 = (int) (y1 + pikkus1 * sin(nurk2) * pikkus2Kordaja);

                g2d.setPaint(c);
                g2d.setStroke(new BasicStroke(paksus));
                g2d.drawPolygon(new int[] {x1, x2, x3}, new int[] {y1, y2, y3}, 3);
            }
            else if (kujund == 3) { //tühi ristkülik
                int pikkus = (int) (kujundeid * (Math.random() * 10 + 50) / i);
                int laius = (int) (kujundeid * (Math.random() * 10 + 50) / i);
                int x = (int) (Math.random() * w);
                int y = (int) (Math.random() * h);
                int paksus = (int) (Math.random() * 6 + 3);

                Rectangle ristkylik = new Rectangle(x, y, pikkus, laius);
                AffineTransform tranform = new AffineTransform();

                g2d.setPaint(c);
                g2d.setStroke(new BasicStroke(paksus));
                g2d.drawRect(x, y, laius, pikkus);
            }
            else if (kujund == -1) { //ring
                int x = (int) (Math.random() * w);
                int y = (int) (Math.random() * h);
                int diameeter = (int) (kujundeid * (Math.random() * 8 + 50) / i);

                g2d.setPaint(c);
                g2d.fill(new Ellipse2D.Float(x, y, diameeter, diameeter));
            }
            else if (kujund == -2) { // kolmnurk
                int x1 = (int) (Math.random() * w);
                int y1 = (int) (Math.random() * h);
                int pikkus1 = (int) (kujundeid * (Math.random() * 10 + 50) / i);
                double nurk1 = Math.random() * 360;
                double nurk2 = Math.random() * 90 + 30;
                int x2 = (int) (x1 + pikkus1 * sin(nurk1));
                int y2 = (int) (y1 + pikkus1 * sin(nurk1));
                double pikkus2Kordaja = Math.random() + 0.5;
                int x3 = (int) (x1 + pikkus1 * cos(nurk2) * pikkus2Kordaja);
                int y3 = (int) (y1 + pikkus1 * sin(nurk2) * pikkus2Kordaja);

                g2d.setPaint(c);
                g2d.fillPolygon(new int[] {x1, x2, x3}, new int[] {y1, y2, y3}, 3);
            }
            else if (kujund == -3) { //ristkülik
                int pikkus = (int) (kujundeid * (Math.random() * 10 + 50) / i);
                int laius = (int) (kujundeid * (Math.random() * 10 + 50) / i);
                int x = (int) (Math.random() * w);
                int y = (int) (Math.random() * h);
                g2d.setPaint(c);
                g2d.fillRect(x, y, laius, pikkus);
            }
            g2d.rotate(Math.random()*2);
        }
        return bImg;
    }
    public static void salvesta(BufferedImage bImg, String failiTee) throws Exception{
        ImageIO.write(bImg, "png", new File(failiTee));
    }
}