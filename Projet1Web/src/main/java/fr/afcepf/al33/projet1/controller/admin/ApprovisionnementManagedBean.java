package fr.afcepf.al33.projet1.controller.admin;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import fr.afcepf.al33.projet1.IBusiness.ApprovisionnementIBusiness;
import fr.afcepf.al33.projet1.IBusiness.ArticleIBusiness;
import fr.afcepf.al33.projet1.IBusiness.FournisseurIBusiness;
import fr.afcepf.al33.projet1.IBusiness.StockIBusiness;
import fr.afcepf.al33.projet1.entity.Approvisionnement;
import fr.afcepf.al33.projet1.entity.Article;
import fr.afcepf.al33.projet1.entity.Fournisseur;
import fr.afcepf.al33.projet1.entity.Stock;


@ManagedBean(name="mbApprovisionnement")
@SessionScoped
public class ApprovisionnementManagedBean implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	

	@EJB
	private StockIBusiness proxyStock;
	
	@EJB
	private ApprovisionnementIBusiness proxyApprovisionnement;
	
	@EJB
	private ArticleIBusiness proxyArticle;
	


	@EJB
	private FournisseurIBusiness proxyFournisseur;
	
	private List<Fournisseur> fournisseurs = new ArrayList<Fournisseur>();
	private List<Article> articles = new ArrayList<Article>();
	
	private Approvisionnement approvisionnement = new Approvisionnement();;
	private double prix;
	private int quantite;
	private Date dateApprovisionnement;
	private String lot;

	
	private Fournisseur fournisseur;
	
	private Stock stock;
	
	@SuppressWarnings("deprecation")
	public void addApprovisionnementToStock() {
	
	//astuce pour corriger le problème d'enregistrement de date dans la BDD. Il y'a un décallage d1 jour entre la date vraiment saisie par l'utilisateur et la date enregsitrée en BDD.
		
	approvisionnement.getDateApprovisionnement().setTime(approvisionnement.getDateApprovisionnement().getTime() -  approvisionnement.getDateApprovisionnement().getTimezoneOffset()*60*1000 );
	approvisionnement.getDatePeremption().setTime(approvisionnement.getDatePeremption().getTime() -  approvisionnement.getDatePeremption().getTimezoneOffset()*60*1000 );
	
	stock = proxyStock.searchById(approvisionnement.getArticle().getId());
	
	stock.setQuantiteDispoPhysique(stock.getQuantiteDispoPhysique()+approvisionnement.getQuantiteCommandee());
	stock.setQuantiteDispoSiteInternet(stock.getQuantiteDispoSiteInternet()+approvisionnement.getQuantiteCommandee());
	approvisionnement.setStock(stock);
	approvisionnement.setQuantiteRestante(approvisionnement.getQuantiteCommandee());
	
	proxyApprovisionnement.addApprovisionnement(approvisionnement);
	
	
	proxyStock.update(stock);
		
		
	 FacesContext facesContext = FacesContext.getCurrentInstance();
	 facesContext.getApplication().getNavigationHandler().handleNavigation(facesContext,null,"/interfaceAdmin/afficherStock.xhtml?faces-redirect=true");
		
	}
	
	
	@PostConstruct
	public void init() {
		articles = proxyArticle.getAll();
		fournisseurs=proxyFournisseur.getAll();

	}



	public ApprovisionnementIBusiness getProxyApprovisionnement() {
		return proxyApprovisionnement;
	}


	public void setProxyApprovisionnement(ApprovisionnementIBusiness proxyApprovisionnement) {
		this.proxyApprovisionnement = proxyApprovisionnement;
	}


	public ArticleIBusiness getProxyArticle() {
		return proxyArticle;
	}

	public void setProxyArticle(ArticleIBusiness proxyArticle) {
		this.proxyArticle = proxyArticle;
	}


	public List<Fournisseur> getFournisseurs() {
		return fournisseurs;
	}


	public void setFournisseurs(List<Fournisseur> fournisseurs) {
		this.fournisseurs = fournisseurs;
	}


	public List<Article> getArticles() {
		return articles;
	}


	public void setArticles(List<Article> articles) {
		this.articles = articles;
	}


	public Approvisionnement getApprovisionnement() {
		return approvisionnement;
	}

	public void setApprovisionnement(Approvisionnement approvisionnement) {
		this.approvisionnement = approvisionnement;
	}

	public double getPrix() {
		return prix;
	}

	public void setPrix(double prix) {
		this.prix = prix;
	}

	public int getQuantite() {
		return quantite;
	}

	public void setQuantite(int quantite) {
		this.quantite = quantite;
	}

	public Date getDateApprovisionnement() {
		return dateApprovisionnement;
	}

	public void setDateApprovisionnement(Date dateApprovisionnement) {
		this.dateApprovisionnement = dateApprovisionnement;
	}

	public String getLot() {
		return lot;
	}

	public void setLot(String lot) {
		this.lot = lot;
	}

	public Fournisseur getFournisseur() {
		return fournisseur;
	}

	public void setFournisseur(Fournisseur fournisseur) {
		this.fournisseur = fournisseur;
	}

	public Stock getStock() {
		return stock;
	}

	public void setStock(Stock stock) {
		this.stock = stock;
	}

	

}
