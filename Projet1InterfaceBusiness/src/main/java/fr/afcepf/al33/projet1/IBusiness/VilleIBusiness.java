package fr.afcepf.al33.projet1.IBusiness;

import java.util.List;


import fr.afcepf.al33.projet1.entity.Ville;

public interface VilleIBusiness {
	
	public List<Ville> getAll();
	public Ville searchById(Integer id);
	public Ville rechercherParVilleEtCodePostal(String nom, String cp);

}
