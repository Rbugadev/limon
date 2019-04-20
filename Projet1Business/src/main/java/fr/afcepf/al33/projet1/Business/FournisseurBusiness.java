package fr.afcepf.al33.projet1.Business;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Remote;
import javax.ejb.Stateless;

import fr.afcepf.al33.projet1.IBusiness.FournisseurIBusiness;
import fr.afcepf.al33.projet1.entity.Fournisseur;
import fr.afcepf.al33.projet1.idao.FournisseurIdao;

@Remote(FournisseurIBusiness.class)
@Stateless
public class FournisseurBusiness implements FournisseurIBusiness{
	
	@EJB
	private FournisseurIdao proxyFournisseur;

	@Override
	public Fournisseur add(Fournisseur fournisseur) {
		proxyFournisseur.ajouter(fournisseur);
		return fournisseur;
	}

	@Override
	public Boolean delete(Fournisseur fournisseur) {
		proxyFournisseur.supprimer(fournisseur);
		return true;
	}

	@Override
	public Fournisseur update(Fournisseur fournisseur) {
		proxyFournisseur.modifier(fournisseur);
		return fournisseur;
	}

	@Override
	public Fournisseur searchById(Integer id) {
		
		Fournisseur fournisseur = proxyFournisseur.rechercherParId(id);
		
		return fournisseur;
	}

	@Override
	public List<Fournisseur> getAll() {
		
		List<Fournisseur> fournisseurs = new ArrayList<Fournisseur>();
		fournisseurs = proxyFournisseur.getAll();
		return fournisseurs;
	}

}
