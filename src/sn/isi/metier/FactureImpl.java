package sn.isi.metier;

import sn.isi.entities.Facture;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class FactureImpl implements IFacture {
    private DB db = new DB();
    private ResultSet rs;
    private int ok;
    @Override
    public int add(Facture f) {
        String sql = "INSERT into facture VALUES (NULL,?,?,?,?)";

        try {
            db.initPrepar(sql);

            SimpleDateFormat sdf =new SimpleDateFormat("yyyy-MM-dd");
            db.getPstm().setString(1,sdf.format(f.getDate()));
            db.getPstm().setInt(2,f.getConsommation());
            db.getPstm().setInt(3,f.getPrix());
            db.getPstm().setBoolean(4,f.isPaiement());

            ok = db.executeMaj();
            db.closeConnection();

        }catch (Exception ex){
            ex.printStackTrace();
        }

        return ok;
    }

    @Override
    public List<Facture> liste() {
        List<Facture> factures = new ArrayList<Facture>();
        String sql = "SELECT * FROM facture";

        try {
            db.initPrepar(sql);

            rs = db.executeSelect();

            while (rs.next()){
                Facture facture = new Facture();
                facture.setIdF(rs.getInt(1));

                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                facture.setDate(sdf.parse(rs.getString(2)));

                facture.setConsommation(rs.getInt(3));
                facture.setPrix(rs.getInt(4));
                facture.setPaiement(rs.getBoolean(5));
            }

        }catch (Exception ex){
            ex.printStackTrace();
        }
        return factures;
    }

    @Override
    public int update(Facture f) {
        String sql = "UPDATE facture set paiement = ? WHERE idF = ?";

        try {
            db.initPrepar(sql);

            db.getPstm().setBoolean(1,f.isPaiement());
            db.getPstm().setInt(2,f.getIdF());

            ok = db.executeMaj();

        }catch (Exception ex){
            ex.printStackTrace();
        }

        return ok;
    }

}
