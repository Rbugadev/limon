package fr.afcepf.al33.projet1.Business;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Remote;
import javax.ejb.Stateless;


import fr.afcepf.al33.projet1.IBusiness.VilleIBusiness;
import fr.afcepf.al33.projet1.entity.Ville;
import fr.afcepf.al33.projet1.idao.VilleIdao;

@Remote(VilleIBusiness.class)
@Stateless
public class VilleBusiness implements VilleIBusiness{
	
	@EJB
	private VilleIdao proxyVille;

	@Override
	public List<Ville> getAll() {
		List<Ville> villes = null;
		villes=proxyVille.getAll();
		return villes;
	}

	@Override
	public Ville searchById(Integer id) {
		Ville ville= proxyVille.rechercherParId(id);

		return ville;
	}

	@Override
	public Ville rechercherParVilleEtCodePostal(String nom, String cp) {
		Ville ville = proxyVille.rechercherParVilleEtCodePostal(nom, cp);
		return ville;
	}

}
