package fr.afcepf.al33.projet1.controller.admin;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import fr.afcepf.al33.projet1.IBusiness.ArticleIBusiness;
import fr.afcepf.al33.projet1.IBusiness.CategorieIBusiness;
import fr.afcepf.al33.projet1.IBusiness.StockIBusiness;
import fr.afcepf.al33.projet1.entity.Article;
import fr.afcepf.al33.projet1.entity.Categorie;
import fr.afcepf.al33.projet1.entity.Stock;


@ManagedBean(name="mbAjoutArticle")
@SessionScoped
public class AjoutArticleManagedBean implements Serializable{

	


		private static final long serialVersionUID = 1L;

		@EJB
		private ArticleIBusiness proxyArticle;
		
		@EJB
		private CategorieIBusiness proxyCategorie;
		
		@EJB
		private StockIBusiness proxyStock ;
		
		private Stock stock = new Stock();
		
		private Categorie categorie = new Categorie();
		
		private List<Categorie> categories = new ArrayList<>();
		
		private Article article = new Article();
		

		

		public Categorie getCategorie() {
			return categorie;
		}

		public void setCategorie(Categorie categorie) {
			this.categorie = categorie;
		}

		public List<Categorie> getCategories() {
			return categories;
		}

		public void setCategories(List<Categorie> categories) {
			this.categories = categories;
		}

		public void setProxyArticle(ArticleIBusiness proxyArticle) {
			this.proxyArticle = proxyArticle;
		}
		
		public ArticleIBusiness getProxyArticle() {
			return proxyArticle;
		}
		
		public void setArticle(Article article) {
			this.article = article;
		}

		public Article getArticle() {
			return article;
		}
		
		public CategorieIBusiness getProxyCategorie() {
			return proxyCategorie;
		}

		public void setProxyCategorie(CategorieIBusiness proxyCategorie) {
			this.proxyCategorie = proxyCategorie;
		}

		public Stock getStock() {
			return stock;
		}

		public void setStock(Stock stock) {
			this.stock = stock;
		}

		
		
		public void ajout() {
		
			article.setCategorie(categorie);
			article = proxyArticle.add(article);
			FacesContext facesContext = FacesContext.getCurrentInstance();
			facesContext.getApplication().getNavigationHandler().handleNavigation(facesContext,null,"/interfaceAdmin/moteurRechercheArticle.xhtml?faces-redirect=true");
			stock.setArticle(article);
			proxyStock.add(stock);
		}
		
		@PostConstruct
		public void init() {
			categories = proxyCategorie.getAll();
		}

		
		
}


