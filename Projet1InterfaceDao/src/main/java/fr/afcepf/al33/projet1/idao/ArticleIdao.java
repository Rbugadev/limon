package fr.afcepf.al33.projet1.idao;

import java.util.List;

import fr.afcepf.al33.projet1.entity.Article;
import fr.afcepf.al33.projet1.entity.Categorie;

public interface ArticleIdao extends GenericIdao<Article>{

	
	List<Article> getAll();
	List<Article> getByIdCategorie(Categorie c);
	
}
