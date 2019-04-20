package fr.afcepf.al33.projet1.idao;

import java.util.List;

import fr.afcepf.al33.projet1.entity.Ville;



public interface VilleIdao extends GenericIdao<Ville> {
	

	List<Ville> getAll();
	Ville rechercherParVilleEtCodePostal(String nom, String cp);

}
