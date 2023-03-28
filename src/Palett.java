import java.util.ArrayList;
import java.util.List;

//klass, mis hoiab värviharmooniat, ehk mingit hulka värve, mis sobivad kokku
public class Palett {
    private List<Varv> harmoonia;

    //konstruktor, mis võtab argumendisks harmoonia põhivärvi ning lisab selle järjendisse
    public Palett(Varv a) {
        this.harmoonia = new ArrayList<>();
        harmoonia.add(a);
    }

    public List<Varv> getHarmoonia() {
        return harmoonia;
    }

    public void lisaVarv(Varv v) {
        harmoonia.add(v);
    }

    public void eemaldaVarv(int r, int g, int b) {
        for (int i = 0; i < harmoonia.size(); i++) {
            Varv v = harmoonia.get(i);
            if (v.getR() == r && v.getG() == g && v.getB() == b) {
                harmoonia.remove(i);
            }
        }
    }
}
