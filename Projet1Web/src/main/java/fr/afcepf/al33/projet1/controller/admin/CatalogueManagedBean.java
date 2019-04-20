package fr.afcepf.al33.projet1.controller.admin;

import java.io.Serializable;
import java.util.ArrayList;
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
import fr.afcepf.al33.projet1.entity.Categorie;



@ManagedBean(name="mbCatalogue")
@SessionScoped
public class CatalogueManagedBean implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	private List <Article> articles;
	private List <Categorie> categories;
	private Categorie selectedCategorie = null;

	private Article selectedArticleAdmin;


	@EJB
	private ArticleIBusiness proxyArticle;

	@EJB
	private CategorieIBusiness proxyCategorie;



	@PostConstruct
	public void init() {
		
		categories =  proxyCategorie.getAll();
	
		onCategorieChange();

		
	}

	public void afficherFicheProduit(Article article) {
		selectedArticleAdmin = article;

		System.out.println(selectedArticleAdmin.getId());

		FacesContext facesContext = FacesContext.getCurrentInstance();
		HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(false);
		session.setAttribute("selectedArticleAdmin", selectedArticleAdmin);
		facesContext.getApplication()
					.getNavigationHandler()
					.handleNavigation(facesContext,null,"/interfaceAdmin/ficheArticleAdmin.xhtml?faces-redirect=true");
	}


	public void onCategorieChange() {


		if (selectedCategorie !=null && !selectedCategorie.equals("") && !selectedCategorie.getNomCategorie().equals("Toutes les catégories")) {
			
			articles=proxyArticle.getByIdCategorie(selectedCategorie);
			
		} else if (selectedCategorie==null) {
			
			
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



	public Article getSelectedArticleAdmin() {
		return selectedArticleAdmin;
	}

	public void setSelectedArticleAdmin(Article selectedArticleAdmin) {
		this.selectedArticleAdmin = selectedArticleAdmin;
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

}
