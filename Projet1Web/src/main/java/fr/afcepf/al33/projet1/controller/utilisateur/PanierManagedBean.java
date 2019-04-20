package fr.afcepf.al33.projet1.controller.utilisateur;


import java.io.Serializable;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import org.primefaces.PrimeFaces;

import fr.afcepf.al33.projet1.IBusiness.ArticleCommandeIBusiness;
import fr.afcepf.al33.projet1.IBusiness.CommandeIBusiness;
import fr.afcepf.al33.projet1.IBusiness.StockIBusiness;
import fr.afcepf.al33.projet1.entity.Article;
import fr.afcepf.al33.projet1.entity.ArticleCommande;
import fr.afcepf.al33.projet1.entity.Client;
import fr.afcepf.al33.projet1.entity.Commande;
import fr.afcepf.al33.projet1.entity.Stock;



@ManagedBean(name="mbPanier")
@SessionScoped
public class PanierManagedBean implements Serializable{

  /**
   * 
   */
  private static final long serialVersionUID = 1L;


  @ManagedProperty(value="#{mbCatalogueClient.articlesCommandes}")
  private List<ArticleCommande> articlesCommandes;
  
  @ManagedProperty(value="#{mbCatalogueClient.quantiteSaisie}")
  private int quantiteSaisie;
  
  @ManagedProperty(value="#{mbConnectionUtilisateur.clientConnecte}")
  private Client client;
  
  private double prixTotal;
  

  private Article selectedArticle;
  
  
  private Commande commande;
  
  private Stock stock = new Stock();
  
  private String articleEnRupture;
  
  @EJB
  private CommandeIBusiness proxyCommande;
  
  @EJB
  private ArticleCommandeIBusiness proxyArticleCommande;
  
  @EJB
  private StockIBusiness proxyStock;
  

	@SuppressWarnings("unchecked")
	@PostConstruct
	public void init() {


		FacesContext fc = FacesContext.getCurrentInstance();
		HttpSession session = (HttpSession) fc.getExternalContext().getSession(false);
		client = (Client) session.getAttribute("clientConnecte");
		if ((List<ArticleCommande>) session.getAttribute("listeArticlesCommandes") != null)
		{
			
			articlesCommandes = (List<ArticleCommande>) session.getAttribute("listeArticlesCommandes");
		}
			
	}
    
  public void payer() {
	  
	  if (client != null) {
		  Commande cde = new Commande();
		  prixTotal = 0;
		  
		  Iterator<ArticleCommande> ite = articlesCommandes.iterator();
			while(ite.hasNext()) {
				ArticleCommande ac = ite.next();
				prixTotal += ac.getQuantite()*ac.getArticle().getPrix();
			}
		  
		  
		  DecimalFormat twoDForm =new DecimalFormat("##.##");
		  DecimalFormatSymbols dfs = new DecimalFormatSymbols();
		  dfs.setDecimalSeparator('.');
		  twoDForm.setDecimalFormatSymbols(dfs);
		  
		  prixTotal=Double.parseDouble(twoDForm.format(prixTotal));
		  
		  cde.setPrixTotal(prixTotal);
		  cde.setDateCreation(new Date());
		  cde.setClient(client);
			
		  boolean toutEstDispo = true;
		  
		  for (ArticleCommande articleCommande : articlesCommandes) {
			  double calculPrixLigneArticleCommande = articleCommande.getQuantite()*articleCommande.getArticle().getPrix();
			  
			  calculPrixLigneArticleCommande=Double.parseDouble(twoDForm.format(calculPrixLigneArticleCommande));
			  articleCommande.setPrixTotal(calculPrixLigneArticleCommande);
			  stock= proxyStock.searchById(articleCommande.getArticle().getStock().getId());
			  
			  if (stock.getQuantiteDispoSiteInternet() - articleCommande.getQuantite()<0) {
				  articleEnRupture=articleCommande.getArticle().getNom();
				  PrimeFaces current = PrimeFaces.current();
				  current.executeScript("PF('quantiteInsuffisante').show();");
				  toutEstDispo=false;
			  }
				
		  }
		  
		  if (toutEstDispo== true) {
		  
			  proxyArticleCommande.add(cde, articlesCommandes);
			  
			  for (ArticleCommande articleCommande : articlesCommandes) {
				
				stock= proxyStock.searchById(articleCommande.getArticle().getStock().getId());
				stock.setQuantiteDispoSiteInternet(stock.getQuantiteDispoSiteInternet()-articleCommande.getQuantite());
				
				proxyStock.update(stock);
				
			  }
			  
		
				 FacesContext facesContext = FacesContext.getCurrentInstance();
				 HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(false);
				 facesContext.getApplication().getNavigationHandler().handleNavigation(facesContext,null,"/interfaceClient/monCompte.xhtml#test?faces-redirect=true");
			  
				 articlesCommandes= new ArrayList<ArticleCommande>();
				 session.setAttribute("listeArticlesCommandes", articlesCommandes);
		  }
	  } else {
		  
		  PrimeFaces current = PrimeFaces.current();
		  current.executeScript("PF('notConnected').show();");
		 
			
	  }
	  
	  
  }
  
  public double calculerPanier() {
		prixTotal = 0;
		Iterator<ArticleCommande> ite = articlesCommandes.iterator();
		while(ite.hasNext()) {
			ArticleCommande ac = ite.next();
			prixTotal += ac.getQuantite()*ac.getArticle().getPrix();
			}
		
		DecimalFormat twoDForm =new DecimalFormat("##.##");
		DecimalFormatSymbols dfs = new DecimalFormatSymbols();
		dfs.setDecimalSeparator('.');
		twoDForm.setDecimalFormatSymbols(dfs);
		prixTotal=Double.parseDouble(twoDForm.format(prixTotal));
		return prixTotal;
  		}
  
  public void supprimerArticle(ArticleCommande articleCommande) {
		Iterator<ArticleCommande> ite = articlesCommandes.iterator();
		while(ite.hasNext()) {
			ArticleCommande ac = ite.next();
				if (ac.getArticle().getId()== articleCommande.getArticle().getId()) {
					ite.remove();
					break;
				}
					
			}
		
		FacesContext facesContext = FacesContext.getCurrentInstance();
		HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(false);
		session.setAttribute("listeArticlesCommandes", articlesCommandes);
		facesContext.getApplication().getNavigationHandler().handleNavigation(facesContext,null,"/interfaceClient/affichagePanier.xhtml?faces-redirect=true");
  }
  
  public List<ArticleCommande> rafraichirPanier() {
	  
	  DecimalFormat twoDForm =new DecimalFormat("##.##");
	  DecimalFormatSymbols dfs = new DecimalFormatSymbols();
	  dfs.setDecimalSeparator('.');
	  twoDForm.setDecimalFormatSymbols(dfs);
		
	  
	  for (ArticleCommande articleCommande : articlesCommandes) {
		  double calculPrixLigneArticleCommande = articleCommande.getQuantite()*articleCommande.getArticle().getPrix();
		  
		  calculPrixLigneArticleCommande=Double.parseDouble(twoDForm.format(calculPrixLigneArticleCommande));
		  articleCommande.setPrixTotal(calculPrixLigneArticleCommande);
		
			
	  }
	  
	 FacesContext facesContext = FacesContext.getCurrentInstance();
	 HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(false);
	 session.setAttribute("listeArticlesCommandes", articlesCommandes);
	 facesContext.getApplication().getNavigationHandler().handleNavigation(facesContext,null,"/interfaceClient/affichagePanier.xhtml?faces-redirect=true");
	 return articlesCommandes;
  }
		
  
  
  public List<ArticleCommande> getArticlesCommandes() {
    return articlesCommandes;
  }



  public void setArticlesCommandes(List<ArticleCommande> articlesCommandes) {
    this.articlesCommandes = articlesCommandes;
  }



  public int getQuantiteSaisie() {
    return quantiteSaisie;
  }



  public void setQuantiteSaisie(int quantiteSaisie) {
    this.quantiteSaisie = quantiteSaisie;
  }


public Article getSelectedArticle() {
	return selectedArticle;
}

public void setSelectedArticle(Article selectedArticle) {
	this.selectedArticle = selectedArticle;
}

public double getPrixTotal() {
	return prixTotal;
}

public void setPrixTotal(double prixTotal) {
	this.prixTotal = prixTotal;
}

public Commande getCommande() {
	return commande;
}

public void setCommande(Commande commande) {
	this.commande = commande;
}



public Client getClient() {
	return client;
}

public void setClient(Client client) {
	this.client = client;
}

public CommandeIBusiness getProxyCommande() {
	return proxyCommande;
}

public void setProxyCommande(CommandeIBusiness proxyCommande) {
	this.proxyCommande = proxyCommande;
}

public Stock getStock() {
	return stock;
}

public void setStock(Stock stock) {
	this.stock = stock;
}

public String getArticleEnRupture() {
	return articleEnRupture;
}

public void setArticleEnRupture(String articleEnRupture) {
	this.articleEnRupture = articleEnRupture;
}




}