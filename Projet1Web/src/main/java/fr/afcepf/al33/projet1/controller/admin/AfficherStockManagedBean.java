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

import fr.afcepf.al33.projet1.IBusiness.CategorieIBusiness;
import fr.afcepf.al33.projet1.IBusiness.StockIBusiness;
import fr.afcepf.al33.projet1.entity.Categorie;
import fr.afcepf.al33.projet1.entity.Stock;





@ManagedBean(name="mbStock")
@SessionScoped
public class AfficherStockManagedBean implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	private List <Stock> stocks;
	
	private Stock selectedStock;
	
	private List <Categorie> categories;
	private Categorie selectedCategorie = null;

	
	@EJB
	private StockIBusiness proxyStock;
	

	@EJB
	private CategorieIBusiness proxyCategorie;
		
	

	@PostConstruct
	public void init() {
		
		categories =  proxyCategorie.getAll();
	
		onCategorieChange();

		
	}
	

	public void onCategorieChange() {


		if (selectedCategorie !=null && !selectedCategorie.equals("") && !selectedCategorie.getNomCategorie().equals("Toutes les catégories")) {
			
		
			stocks=proxyStock.getByIdCategorie(selectedCategorie);
			
		} else if (selectedCategorie==null) {
			
			
			stocks=proxyStock.getAll();
			 
		} else if(selectedCategorie.getNomCategorie().equals("Toutes les catégories")) {
			
		
			stocks=proxyStock.getAll();

		} else {
			
			stocks=new ArrayList<>();
			
		}
	}
	
	public void afficherApprovisionnements(Stock stock) {
		 selectedStock = stock;

		 System.out.println(selectedStock.getId());
		 
		 FacesContext facesContext = FacesContext.getCurrentInstance();
		 HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(false);
		 session.setAttribute("selectedStock", selectedStock);
		 facesContext.getApplication().getNavigationHandler().handleNavigation(facesContext,null,"/interfaceAdmin/afficherApprovisionnements.xhtml?faces-redirect=true");
	}
	
	


	public List<Stock> getStocks() {
		return stocks;
	}




	public void setStocks(List<Stock> stocks) {
		this.stocks = stocks;
	}



	public CategorieIBusiness getProxyCategorie() {
		return proxyCategorie;
	}

	public void setProxyCategorie(CategorieIBusiness proxyCategorie) {
		this.proxyCategorie = proxyCategorie;
		
	}


	public StockIBusiness getProxyStock() {
		return proxyStock;
	}



	public void setProxyStock(StockIBusiness proxyStock) {
		this.proxyStock = proxyStock;
	}

	public Stock getSelectedStock() {
		return selectedStock;
	}

	public void setSelectedStock(Stock selectedStock) {
		this.selectedStock = selectedStock;
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

	
	

}
