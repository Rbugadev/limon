package fr.afcepf.al33.projet1.dao;

import java.util.List;

import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import fr.afcepf.al33.projet1.entity.Categorie;
import fr.afcepf.al33.projet1.idao.CategorieIdao;


@Remote(CategorieIdao.class)
@Stateless
public class CategorieDao extends GenericDao<Categorie> implements CategorieIdao{
	
	@PersistenceContext(unitName="Projet1DS")
	private EntityManager em;

	@SuppressWarnings("unchecked")
	@Override
	public List<Categorie> getAll() {
		List<Categorie> categories = null;
		String REQ_GETALL = "SELECT categorie from Categorie categorie";
		Query queryJPQL = em.createQuery(REQ_GETALL);
		categories = queryJPQL.getResultList();
		return categories;
	}

}
