package fr.afcepf.al33.projet1.dao;

import java.util.List;

import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import fr.afcepf.al33.projet1.entity.Fournisseur;
import fr.afcepf.al33.projet1.idao.FournisseurIdao;

@Remote(FournisseurIdao.class)
@Stateless
public class FournisseurDao extends GenericDao<Fournisseur> implements FournisseurIdao{

	@PersistenceContext(unitName="Projet1DS")
	private EntityManager em;
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Fournisseur> getAll() {
		List<Fournisseur> fournisseurs = null;
		String REQ_GETALL = "SELECT fournisseur from Fournisseur fournisseur ORDER BY fournisseur.nom";
		Query queryJPQL = em.createQuery(REQ_GETALL);
		fournisseurs = queryJPQL.getResultList();
		return fournisseurs;
	}



	
	
}
