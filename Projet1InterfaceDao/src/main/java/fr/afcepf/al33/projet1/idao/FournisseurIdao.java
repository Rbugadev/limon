package fr.afcepf.al33.projet1.idao;

import java.util.List;

import fr.afcepf.al33.projet1.entity.Fournisseur;

public interface FournisseurIdao extends GenericIdao<Fournisseur>{

	
	List<Fournisseur> getAll();
}
