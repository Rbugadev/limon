package fr.afcepf.al33.projet1.controller.admin;

import java.io.Serializable;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import fr.afcepf.al33.projet1.IBusiness.ArticleIBusiness;
import fr.afcepf.al33.projet1.IBusiness.CategorieIBusiness;
import fr.afcepf.al33.projet1.entity.Article;
import fr.afcepf.al33.projet1.entity.Categorie;


@ManagedBean(name="mbUpdateArticle")
@SessionScoped
public class UpdateArticleManagedBean implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@EJB
	private ArticleIBusiness proxyArticle;
	
	@EJB
	private CategorieIBusiness proxyCategorie;
	
	@ManagedProperty(value="#{mbCatalogue.selectedArticleAdmin}")
	private Article article;
	
	private List<Categorie> categories;
	
	public void init()
	{
		FacesContext facesContext = FacesContext.getCurrentInstance();
		HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(false);
		article = (Article) session.getAttribute("selectedArticleAdmin");
	}
	
	public void update()
	{
		proxyArticle.update(article);
		FacesContext facesContext = FacesContext.getCurrentInstance();
		facesContext.getApplication().getNavigationHandler().handleNavigation(facesContext,null,"/interfaceAdmin/ficheArticleAdmin.xhtml?faces-redirect=true");
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

	public Article getArticle() {
		return article;
	}

	public void setArticle(Article article) {
		this.article = article;
	}

	public List<Categorie> getCategories() {
		return categories;
	}

	public void setCategories(List<Categorie> categories) {
		this.categories = categories;
	}
}
