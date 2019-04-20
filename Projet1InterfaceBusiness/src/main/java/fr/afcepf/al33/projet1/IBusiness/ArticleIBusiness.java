package fr.afcepf.al33.projet1.IBusiness;

import java.util.List;

import fr.afcepf.al33.projet1.entity.Article;
import fr.afcepf.al33.projet1.entity.Categorie;

public interface ArticleIBusiness {
	
	List<Article> getAll();
	List<Article> getByIdCategorie(Categorie c);
	public Article update(Article article);
	Article add(Article article);
}
