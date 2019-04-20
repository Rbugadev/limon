package fr.afcepf.al33.projet1.controller.admin;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import fr.afcepf.al33.projet1.IBusiness.ApprovisionnementIBusiness;
import fr.afcepf.al33.projet1.IBusiness.CommandeIBusiness;
import fr.afcepf.al33.projet1.IBusiness.StockIBusiness;
import fr.afcepf.al33.projet1.entity.Approvisionnement;
import fr.afcepf.al33.projet1.entity.Commande;
import fr.afcepf.al33.projet1.entity.Stock;

@ManagedBean(name="mbAccueilAdmin")
@SessionScoped
public class AccueilAdminManagedBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private List <Stock> stockQuantity;
	private List<Stock> stockRupture;
	private List <Approvisionnement> approvisionnementsPeremption;
	private List <Approvisionnement> approvisionnementsRupture;
	private List <Approvisionnement> approvisionnementsPerimes;
	private List<Commande> commandesEnAttente;
	private Commande selectedCommandeReporting;
	
	private String stockTotal;
	private String stockVirtuel;

	@EJB
	private StockIBusiness proxyStock;
	
	@EJB
	private CommandeIBusiness proxyCommande;
	
	@EJB
	private ApprovisionnementIBusiness proxyApprovisionnement;
	
	@PostConstruct
	public void init() {
		setStockQuantity(proxyStock.getByQuantity());
		// recherche des approvisionnements qui seront périmés dans n jours
		setApprovisionnementsPeremption(proxyApprovisionnement.getOutOfDateAppro(5));
		setApprovisionnementsRupture(proxyApprovisionnement.getApproRupture());
		setStockRupture(proxyStock.getRupture());
		setStockTotal(proxyStock.getTotalQuantity());
		setStockVirtuel(proxyStock.getVirtualQuantity());
		setCommandesEnAttente(proxyCommande.getAllToProcess());
		approvisionnementsPerimes=proxyApprovisionnement.getAllApproPerimes();
		
	}
	
	public void jeterArticlePerime(Approvisionnement approvisionnement) {
		Date dateRebut = new Date();
		approvisionnement.setDateMiseAuRebut(dateRebut);

		
		Stock stock = new Stock();
		stock= approvisionnement.getStock();
		stock.setQuantiteDispoPhysique(stock.getQuantiteDispoPhysique() - approvisionnement.getQuantiteRestante());
		stock.setQuantiteDispoSiteInternet(stock.getQuantiteDispoSiteInternet() - approvisionnement.getQuantiteRestante());
		
		proxyApprovisionnement.update(approvisionnement);
		proxyStock.update(stock);
	}
	
	public void onSelect(Commande commande) {
		
		selectedCommandeReporting=commande;
	  
		System.out.println(commande.getId());
		
		
		 FacesContext facesContext = FacesContext.getCurrentInstance();
		 HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(false);
		 session.setAttribute("selectedCommandeReporting", selectedCommandeReporting);
		 facesContext.getApplication().getNavigationHandler().handleNavigation(facesContext,null,"/interfaceAdmin/afficherCommandeReporting.xhtml?faces-redirect=true");
	}

	public List <Stock> getStockQuantity() {
		return stockQuantity;
	}

	public void setStockQuantity(List <Stock> stockQuantity) {
		this.stockQuantity = stockQuantity;
	}

	public List<Approvisionnement> getApprovisionnementsPeremption() {
		return approvisionnementsPeremption;
	}

	public void setApprovisionnementsPeremption(List<Approvisionnement> approvisionnementsPeremption) {
		this.approvisionnementsPeremption = approvisionnementsPeremption;
	}

	public String getStockTotal() {
		return stockTotal;
	}

	public void setStockTotal(String stockTotal) {
		this.stockTotal = stockTotal;
	}

	public String getStockVirtuel() {
		return stockVirtuel;
	}

	public void setStockVirtuel(String stockVirtuel) {
		this.stockVirtuel = stockVirtuel;
	}

	public List<Commande> getCommandesEnAttente() {
		return commandesEnAttente;
	}

	public void setCommandesEnAttente(List<Commande> commandesEnAttente) {
		this.commandesEnAttente = commandesEnAttente;
	}

	public List <Approvisionnement> getApprovisionnementsRupture() {
		return approvisionnementsRupture;
	}

	public void setApprovisionnementsRupture(List <Approvisionnement> approvisionnementsRupture) {
		this.approvisionnementsRupture = approvisionnementsRupture;
	}

	public List<Stock> getStockRupture() {
		return stockRupture;
	}

	public void setStockRupture(List<Stock> stockRupture) {
		this.stockRupture = stockRupture;
	}

	public List<Approvisionnement> getApprovisionnementsPerimes() {
		return approvisionnementsPerimes;
	}

	public void setApprovisionnementsPerimes(List<Approvisionnement> approvisionnementsPerimes) {
		this.approvisionnementsPerimes = approvisionnementsPerimes;
	}

	public Commande getSelectedCommandeReporting() {
		return selectedCommandeReporting;
	}

	public void setSelectedCommandeReporting(Commande selectedCommandeReporting) {
		this.selectedCommandeReporting = selectedCommandeReporting;
	}


	
	
	
}
