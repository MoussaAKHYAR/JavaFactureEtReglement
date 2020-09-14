package sn.isi.metier;

import sn.isi.entities.Facture;

import java.util.List;

public interface IFacture {
    public int add(Facture f);
    public List<Facture> liste();
    public int update(Facture f);
}
