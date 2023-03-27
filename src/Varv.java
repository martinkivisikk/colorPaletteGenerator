public class Varv {
    private int r;
    private int g;
    private int b;

    public Varv(int r, int g, int b) {
        this.r = Math.min(Math.max(r, 0), 255);
        this.g = Math.min(Math.max(r, 0), 255);
        this.b = Math.min(Math.max(r, 0), 255);
        //this.hex = Integer.toHexString(r) + Integer.toHexString(g) + Integer.toHexString(b);
    }

    public String rgbToHex(int r, int g, int b) {
        return Integer.toHexString(r) + Integer.toHexString(g) + Integer.toHexString(b);
    }

    public int[] hexToRgb(String hex) {
        int[] rgb = new int[3];
        rgb[0] = Integer.parseInt(hex.substring(0,2), 16);
        rgb[1] = Integer.parseInt(hex.substring(2,4), 16);
        rgb[2] = Integer.parseInt(hex.substring(4,6), 16);
        return rgb;
    }

    public double[] rgbToHsl(int r, int g, int b) {
        double mm = Math.max(Math.max(r,g),b); //abinumber
        double m = Math.min(Math.min(r,g),b); //abinumber
        double d = (mm - m)/255; //abinumber

        double[] hsl = new double[3];

        //esmalt l ehk heledus ([0,1])
        hsl[2] = (mm + m)/510;

        //siis s ehk erksus või küllastus
        if (hsl[2] > 0) {
            hsl[1] = d/(1 - Math.abs(2*hsl[2] - 1));
        }
        else {
            hsl[1] = 0;
        }

        //viimaks h ehk toon (kraadides värviringil)
        if (g >= b) {
            hsl[0] = Math.acos((r - g/2 - b/2)/Math.sqrt(r*r + g*g + b*b + r*g + r*b + g*b));
        }
        else {
            hsl[0] = 360 - Math.acos((r - g/2 - b/2)/Math.sqrt(r*r + g*g + b*b + r*g + r*b + g*b));
        }

        return hsl;
    }//rgbToHsl

    public int[] hslToRgb(double h, double s, double l) {
        int[] rgb = new int[3];

        double d = s * (1-Math.abs(2*l-1));
        double m = 255 * (l - d/2);
        double x = d * (1 - Math.abs((h/60) % 2 - 1));

        if (h >= 0 && h < 60) {
            rgb[0] = (int) Math.round(255 * d + m);
            rgb[1] = (int) Math.round(255 * x + m);
            rgb[2] = (int) Math.round(m);
        }
        else if (h >= 60 && h < 120) {

        }
        else if (h >= 120 && h < 180) {

        }
        else if (h >= 180 && h < 240) {

        }
        else if (h >= 240 && h < 300) {

        }
        else {

        }
    }//hslToRgb

    public int getR() {
        return r;
    }

    public void setR(int r) {
        this.r = r;
    }

    public int getG() {
        return g;
    }

    public void setG(int g) {
        this.g = g;
    }

    public int getB() {
        return b;
    }

    public void setB(int b) {
        this.b = b;
    }
}