package fr.afcepf.al33.projet1.controller.utilisateur;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import fr.afcepf.al33.projet1.IBusiness.ArticleCommandeIBusiness;
import fr.afcepf.al33.projet1.entity.ArticleCommande;
import fr.afcepf.al33.projet1.entity.Commande;


@ManagedBean(name="mbAfficherMaCommande")
@SessionScoped
public class AfficherMaCommandeManagedBean implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@ManagedProperty(value="#{mbMonCompte.selectedCommandePerso}")
	private Commande selectedCommande;
	
	private List<ArticleCommande> articlesCommandes = new ArrayList<ArticleCommande>();
	
	@EJB
	private ArticleCommandeIBusiness proxyArticleCommande;
	

	@PostConstruct
	public void init() {
		FacesContext facesContext = FacesContext.getCurrentInstance();
		HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(false);
		selectedCommande=(Commande)session.getAttribute("selectedCommandePerso");
		articlesCommandes= proxyArticleCommande.getAllByCommande(selectedCommande);
		
	}
	


	public Commande getSelectedCommande() {
		return selectedCommande;
	}



	public void setSelectedCommande(Commande selectedCommande) {
		this.selectedCommande = selectedCommande;
	}




	public List<ArticleCommande> getArticlesCommandes() {
		return articlesCommandes;
	}



	public void setArticlesCommandes(List<ArticleCommande> articlesCommandes) {
		this.articlesCommandes = articlesCommandes;
	}


}
