package presentation;

import sn.isi.entities.Facture;
import sn.isi.metier.FactureImpl;
import sn.isi.metier.IFacture;

import java.text.ParseException;
import java.text.SimpleDateFormat;

public class Main {
    public static void main(String[]args) throws ParseException {

        Facture facture = new Facture();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        facture.setDate(sdf.parse("2019-02-21"));
        facture.setConsommation(999);
        facture.setPrix(9999999);
        facture.setPaiement(true);

        IFacture iFacture = new FactureImpl();
        iFacture.add(facture);
    }
}
