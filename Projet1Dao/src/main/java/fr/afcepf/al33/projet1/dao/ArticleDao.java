package fr.afcepf.al33.projet1.dao;

import java.util.List;

import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import fr.afcepf.al33.projet1.entity.Article;
import fr.afcepf.al33.projet1.entity.Categorie;
import fr.afcepf.al33.projet1.idao.ArticleIdao;

@Remote(ArticleIdao.class)
@Stateless
public class ArticleDao extends GenericDao<Article> implements ArticleIdao {


	
	@PersistenceContext(unitName="Projet1DS")
	private EntityManager em;

	@SuppressWarnings("unchecked")
	@Override
	public List<Article> getAll() {
		List<Article> articles=null;
		String REQ = "SELECT article from Article article ORDER BY article.nom";
		Query queryJPQL = em.createQuery(REQ);
		articles = queryJPQL.getResultList();
		return articles;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Article> getByIdCategorie(Categorie c) {
		List<Article> articlesParCategorie=null;
		String REQ= "SELECT article from Article article WHERE article.categorie= :idCat ";
		Query queryJPQL = em.createQuery(REQ);
		queryJPQL.setParameter("idCat", c);
		articlesParCategorie = queryJPQL.getResultList();
		return articlesParCategorie ;
		
	}

}
