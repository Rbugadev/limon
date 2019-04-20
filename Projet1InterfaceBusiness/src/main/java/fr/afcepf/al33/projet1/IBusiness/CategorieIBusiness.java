package fr.afcepf.al33.projet1.IBusiness;

import java.util.List;

import fr.afcepf.al33.projet1.entity.Categorie;


public interface CategorieIBusiness {

	public Categorie add(Categorie categorie);
	public Boolean delete(Categorie categorie);
	public Categorie update(Categorie categorie);
	public Categorie searchById(Integer id);
	public List<Categorie> getAll();
	
}
