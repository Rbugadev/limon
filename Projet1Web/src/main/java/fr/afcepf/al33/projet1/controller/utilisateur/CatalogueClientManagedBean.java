package fr.afcepf.al33.projet1.controller.utilisateur;

import java.io.Serializable;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import fr.afcepf.al33.projet1.IBusiness.ArticleIBusiness;
import fr.afcepf.al33.projet1.IBusiness.CategorieIBusiness;
import fr.afcepf.al33.projet1.entity.Article;
import fr.afcepf.al33.projet1.entity.ArticleCommande;
import fr.afcepf.al33.projet1.entity.Categorie;




@ManagedBean(name="mbCatalogueClient")
@SessionScoped
public class CatalogueClientManagedBean implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private int quantiteSaisie;
	private List <Article> articles;
	private List <Categorie> categories;
	private Categorie selectedCategorie = null;

	private Article selectedArticle;
	private ArticleCommande articleCommande = new ArticleCommande();
	private List<ArticleCommande> articlesCommandes = new ArrayList<ArticleCommande>();




	@EJB
	private ArticleIBusiness proxyArticle;

	@EJB
	private CategorieIBusiness proxyCategorie;



	@SuppressWarnings("unchecked")
	@PostConstruct
	public void init() {
		articles=proxyArticle.getAll();
		categories =  proxyCategorie.getAll();
		onCategorieChange();
		FacesContext fc = FacesContext.getCurrentInstance();
		HttpSession session = (HttpSession) fc.getExternalContext().getSession(false);
		if ((List<ArticleCommande>)session.getAttribute("listeArticlesCommandes")!=null) {
			articlesCommandes= (List<ArticleCommande>)session.getAttribute("listeArticlesCommandes");
		}
	}

	public void afficherFicheProduit(Article article) {
		selectedArticle = article;

		System.out.println(selectedArticle.getId());

		FacesContext facesContext = FacesContext.getCurrentInstance();
		HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(false);
		session.setAttribute("selectedArticle", selectedArticle);
		facesContext.getApplication().getNavigationHandler().handleNavigation(facesContext,null,"/interfaceClient/ficheArticleClient.xhtml?faces-redirect=true");
	}

	public void ajouterArticle(Article article) {

		articleCommande = new ArticleCommande();
		articleCommande.setArticle(article);
		articleCommande.setQuantite(article.getQuantiteSaisie());
		Double calculPrixLigneArticleCommande = article.getPrix()*article.getQuantiteSaisie();
		
		DecimalFormat twoDForm =new DecimalFormat("##.##");
		DecimalFormatSymbols dfs = new DecimalFormatSymbols();
		dfs.setDecimalSeparator('.');
		twoDForm.setDecimalFormatSymbols(dfs);
		calculPrixLigneArticleCommande=Double.parseDouble(twoDForm.format(calculPrixLigneArticleCommande));
		articleCommande.setPrixTotal(calculPrixLigneArticleCommande);
		
		boolean isPresent = false;


		if (articlesCommandes.isEmpty()){
			articlesCommandes.add(articleCommande);
			System.out.println("ajout premier article");
		} else {

			Iterator<ArticleCommande> ite = articlesCommandes.iterator();


			while(ite.hasNext()) {
				ArticleCommande ac = ite.next();
				if (ac.getArticle().getId()==articleCommande.getArticle().getId()) {
					ac.setQuantite(ac.getQuantite()+ article.getQuantiteSaisie());
					System.out.println("nombre ajouté à la ligne existante");
					isPresent = true;
				}
			} 
			
			if (isPresent == false) {
				articlesCommandes.add(articleCommande);
			}
		
		}
		
		article.setQuantiteSaisie(0);
		
		System.out.println("Art Quantite "+articleCommande.getQuantite());
		
	
		FacesContext facesContext = FacesContext.getCurrentInstance();
		HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(false);
		session.setAttribute("listeArticlesCommandes", articlesCommandes);
		facesContext.getApplication()
					.getNavigationHandler()
					.handleNavigation(facesContext,null,"/interfaceClient/catalogueClient.xhtml?faces-redirect=true");

}
	
	public void plusQuantiteSaisie(Article article){	
		article.setQuantiteSaisie(article.getQuantiteSaisie()+1);
}

	public void minusQuantiteSaisie(Article article){
		if (article.getQuantiteSaisie()>0) {
			article.setQuantiteSaisie(article.getQuantiteSaisie()-1);
		}		
}

public void onCategorieChange() {


	if (selectedCategorie !=null && !selectedCategorie.equals("") && !selectedCategorie.getNomCategorie().equals("Toutes les catégories")) {

		articles=proxyArticle.getByIdCategorie(selectedCategorie);
		
	} else if(selectedCategorie==null) {

		articles=proxyArticle.getAll();	

	} else if(selectedCategorie.getNomCategorie().equals("Toutes les catégories")) {

		articles=proxyArticle.getAll();

	} else {

		articles=new ArrayList<>();

	}
}


public List<Article> getArticles() {
	return articles;
}

public void setArticles(List<Article> articles) {
	this.articles = articles;
}

public List<Categorie> getCategories() {
	return categories;
}

public void setCategories(List<Categorie> categories) {
	this.categories = categories;
}

public Categorie getSelectedCategorie() {
	return selectedCategorie;
}

public void setSelectedCategorie(Categorie selectedCategorie) {
	this.selectedCategorie = selectedCategorie;
}

public Article getSelectedArticle() {
	return selectedArticle;
}

public void setSelectedArticle(Article selectedArticle) {
	this.selectedArticle = selectedArticle;
}

public ArticleIBusiness getProxyArticle() {
	return proxyArticle;
}

public void setProxyArticle(ArticleIBusiness proxyArticle) {
	this.proxyArticle = proxyArticle;
}

public CategorieIBusiness getProxyCategorie() {
	return proxyCategorie;
}

public void setProxyCategorie(CategorieIBusiness proxyCategorie) {
	this.proxyCategorie = proxyCategorie;
}

public ArticleCommande getArticleCommande() {
	return articleCommande;
}

public void setArticleCommande(ArticleCommande articleCommande) {
	this.articleCommande = articleCommande;
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


}
