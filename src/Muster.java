import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.List;

//kasutab Java EPI klasse, mis lasevad luua maatriksitest pilte.
//maatriksid olgu kahemõõtmelised, igale valitud harmoonilisele värvile vastaku näiteks üks täisarv.
//vt. programmeerimine 2 pilditöötluse abiklassi.
//see, kuidas ja mis kujundeid maatriksisse luua, on veel toores idee. äkki fraktaalid? siis saaks rekursiooni kasutada.
//äkki hoopis mingid sujuvad leegilaadsed või n.ö. looduslikud kujundid ehk midagi art nouveau/juugendlikku? vajaks korralikku algoritmi.
public class Muster {
    public static BufferedImage looBufferedImage(List<Varv> varvid, int w, int h, int kuju) {
        //olgu kuju == 1 tähendab veerud
        //olgu kuju == 2 tähendab read
        int varvideArv = varvid.size();
        BufferedImage bImg = new BufferedImage(w, h, BufferedImage.TYPE_3BYTE_BGR);
        int[] laiused = new int[varvideArv];
        if (kuju == 1) {
            int minVeeruLaius = w/varvideArv; //täisarvuline jagamine
            int ekstra = w;
            for (int i = 0; i < varvideArv; i++) {
                laiused[i] = minVeeruLaius;
                ekstra -= minVeeruLaius;
            }
            if (ekstra > 0) {
                if (varvideArv % 2 == 1) {
                    laiused[varvideArv/2] += ekstra;
                }
                else {
                    laiused[0] += (int) Math.ceil(ekstra/2.0);
                    laiused[varvideArv-1] = ekstra/2;
                }
            }
            int xAlgus = 0;
            for (int v = 0; v < varvideArv; v++) {
                bImg.setRGB(xAlgus, 0, laiused[v], h, varvid.get(v).getRGB(), 0, 0);
                xAlgus += laiused[v];
            }
        }
        else if (kuju == 2) {
            int minReaLaius = h/varvideArv; //täisarvuline jagamine
            int ekstra = h;
            for (int i = 0; i < varvideArv; i++) {
                laiused[i] = minReaLaius;
                ekstra -= minReaLaius;
            }
            if (ekstra > 0) {
                if (varvideArv % 2 == 1) {
                    laiused[varvideArv/2] += ekstra;
                }
                else {
                    laiused[0] += (int) Math.ceil(ekstra/2.0);
                    laiused[varvideArv-1] = ekstra/2;
                }
            }
            int yAlgus = 0;
            for (int v = 0; v < varvideArv; v++) {
                bImg.setRGB(0, yAlgus, w, laiused[v], varvid.get(v).getRGB(), 0, 0);
                yAlgus += laiused[v];
            }
        }
        return bImg;
    }
    public static void salvesta(BufferedImage bImg, String failiTee) throws Exception{
        ImageIO.write(bImg, "png", new File(failiTee));
    }
}
