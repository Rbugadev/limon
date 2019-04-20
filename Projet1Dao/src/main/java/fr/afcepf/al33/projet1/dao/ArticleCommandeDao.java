package fr.afcepf.al33.projet1.dao;

import java.util.List;

import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import fr.afcepf.al33.projet1.entity.ArticleCommande;
import fr.afcepf.al33.projet1.entity.Commande;
import fr.afcepf.al33.projet1.idao.ArticleCommandeIDao;


@Remote(ArticleCommandeIDao.class)
@Stateless
public class ArticleCommandeDao extends GenericDao<ArticleCommande> implements ArticleCommandeIDao{
	
	@PersistenceContext(unitName="Projet1DS")
	private EntityManager em;
	
	

	@SuppressWarnings("unchecked")
	@Override
	public List<ArticleCommande> getAllByCommande(Commande commande) {
		List<ArticleCommande> articlesCommandes=null;
		String REQ = "SELECT ac from ArticleCommande ac WHERE ac.commande = :commande";
		Query queryJPQL = em.createQuery(REQ);
		queryJPQL.setParameter("commande", commande);
		articlesCommandes = queryJPQL.getResultList();
		return articlesCommandes;
	}

	
}
