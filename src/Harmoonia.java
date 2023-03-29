//aitab genereerida kokkusobivaid värve. hea oleks kasutada hsl-kuju. nt komplementaarse värvi võtmisel võtad
//teisel pool ringi asuva hue, sama saturationi ja sama lightnessi ning lisad igale väärtusele natuke müra
//ei tasu genereerida liiga sarnaseid värve. äkki võiks võtta mõnele funktsioonile kaks argumenti, üks põhivärv,
//millele harmoonilist värvi leida, ning teine värv, millest see võiks märgatavalt erinev olla
public class Harmoonia {
    /**
     * Meetod, mis lisab sisendvärvi vastandvärvi olemasolevasse värvipaletti.
     *
     * @param v Värv, mille vastandvärvi leiame
     * @param p Olemasolev värvipalett, kuhu vastandvärv lisatakse
     */
    public void genereeriKomplementaarneVärv(Varv v, Palett p) {
        Varv komplementaarneVärv = new Varv(255 - v.getR(), 255 - v.getG(), 255 - v.getB());
        p.lisaVarv(komplementaarneVärv);
    }

    /**
     * Meetod, mis lisab värvipaletti 2 analoogset värvi.
     *
     * @param v Värv, millele genereerime analoogseid värve
     * @param p Olemasolev värvipalett, kuhu analoogsed värvid lisatakse.
     */
    public void genereeriAnaloogsedVärvid(Varv v, Palett p) {
        double[] sisendHSL = v.rgbToHsl(v.getR(), v.getG(), v.getB());
        //System.out.println(sisendHSL[0]);
        for (int i = 0; i < 2; i++) {
            double suvalineToon;
            if (i == 0) {
                suvalineToon = sisendHSL[0] + Math.random() * 30;
                if (suvalineToon > 360) suvalineToon = suvalineToon % 360;
            } else {
                suvalineToon = sisendHSL[0] - Math.random() * 30;
            }
            int[] uusRGB = v.hslToRgb(suvalineToon, sisendHSL[1], sisendHSL[2]);
            Varv uusVärv = new Varv(uusRGB[0], uusRGB[1], uusRGB[2]);
            p.lisaVarv(uusVärv);
        }
    }

    public void genereeriKolmnurknePalett(Varv v, Palett p) {
        double[] sisendHSL = v.rgbToHsl(v.getR(), v.getG(), v.getB());
        double suvalineToon = sisendHSL[0];
        for (int i = 0; i < 2; i++) {
            //liidame 120 kraadi, et lõpuks 3 värvi moodustaksid värviringil omavahel kolmnurga
            suvalineToon += 120;
            //kui lähme "üle ääre", siis jätkame nullist
            if (suvalineToon > 360) suvalineToon = suvalineToon % 360;
            int[] uusRGB = v.hslToRgb(suvalineToon, sisendHSL[1], sisendHSL[2]);
            Varv uusVärv = new Varv(uusRGB[0], uusRGB[1], uusRGB[2]);
            p.lisaVarv(uusVärv);
        }
    }

    public void genereeriNelinurknePalett(Varv v, Palett p) {
        double[] sisendHSL = v.rgbToHsl(v.getR(), v.getG(), v.getB());
        double suvalineToon = sisendHSL[0];
        for (int i = 0; i < 3; i++) {
            //lõpuks moodustub nelinurk
            suvalineToon += 90;
            //kui lähme "üle ääre"
            if (suvalineToon > 360) suvalineToon = suvalineToon % 360;
            int[] uusRGB = v.hslToRgb(suvalineToon, sisendHSL[1], sisendHSL[2]);
            Varv uusVärv = new Varv(uusRGB[0], uusRGB[1], uusRGB[2]);
            p.lisaVarv(uusVärv);
        }
    }


}
