package fr.afcepf.al33.projet1.Business;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Remote;
import javax.ejb.Stateless;

import fr.afcepf.al33.projet1.IBusiness.ArticleIBusiness;
import fr.afcepf.al33.projet1.entity.Article;
import fr.afcepf.al33.projet1.entity.Categorie;
import fr.afcepf.al33.projet1.idao.ArticleIdao;


@Remote(ArticleIBusiness.class)
@Stateless
public class ArticleBusiness implements ArticleIBusiness{
	
	@EJB
	private ArticleIdao proxyArticleIDao;

	@Override
	public List<Article> getAll() {
		List<Article> articles= proxyArticleIDao.getAll();
		return articles;
	}
	
	@Override
	public List<Article> getByIdCategorie(Categorie c) {
		List<Article> articles= proxyArticleIDao.getByIdCategorie(c);
		return articles;
	}

	@Override
	public Article update(Article article) {
		proxyArticleIDao.modifier(article);
		return article;
	}

	@Override
	public Article add(Article article) {
		return proxyArticleIDao.ajouter(article);
		
	}

}
