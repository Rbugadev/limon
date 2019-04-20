package fr.afcepf.al33.projet1.idao;

import java.util.List;


import fr.afcepf.al33.projet1.entity.ArticleCommande;
import fr.afcepf.al33.projet1.entity.Commande;

public interface ArticleCommandeIDao extends GenericIdao<ArticleCommande> {
	
	List<ArticleCommande> getAllByCommande(Commande commande);

}
