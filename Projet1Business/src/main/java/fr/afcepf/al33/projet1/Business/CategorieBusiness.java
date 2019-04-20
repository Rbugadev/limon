package fr.afcepf.al33.projet1.Business;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Remote;
import javax.ejb.Stateless;

import fr.afcepf.al33.projet1.IBusiness.CategorieIBusiness;
import fr.afcepf.al33.projet1.entity.Categorie;
import fr.afcepf.al33.projet1.idao.CategorieIdao;



@Remote(CategorieIBusiness.class)
@Stateless
public class CategorieBusiness implements CategorieIBusiness{

	
	@EJB
	private CategorieIdao proxyCategorie;

	@Override
	public List<Categorie> getAll() {
		List<Categorie> categories = new ArrayList<Categorie>();

		categories=proxyCategorie.getAll();

		return categories;
	}

	@Override
	public Categorie add(Categorie categorie) {
		proxyCategorie.ajouter(categorie);
		return categorie;
	}

	@Override
	public Boolean delete(Categorie categorie) {
		proxyCategorie.supprimer(categorie);
		return true;
	}

	@Override
	public Categorie update(Categorie categorie) {
		proxyCategorie.modifier(categorie);
		return categorie;
	}

	@Override
	public Categorie searchById(Integer id) {
		Categorie categorie = proxyCategorie.rechercherParId(id);

		return categorie;
	}
}
