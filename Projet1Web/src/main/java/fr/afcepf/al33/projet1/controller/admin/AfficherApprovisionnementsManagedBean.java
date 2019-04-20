package fr.afcepf.al33.projet1.controller.admin;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import fr.afcepf.al33.projet1.IBusiness.ApprovisionnementIBusiness;
import fr.afcepf.al33.projet1.entity.Approvisionnement;
import fr.afcepf.al33.projet1.entity.Stock;





@ManagedBean(name="mbListeApprovisionnements")
@SessionScoped
public class AfficherApprovisionnementsManagedBean implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	private List <Approvisionnement> approvisionnements;
	
	@ManagedProperty(value="#{mbStock.selectedStock}")
	private Stock selectedStock;

	
	@EJB
	private ApprovisionnementIBusiness proxyApprovisionnement;
		
	

	@PostConstruct
	public void init() {
		
		 FacesContext facesContext = FacesContext.getCurrentInstance();
		 HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(false);
		 selectedStock=(Stock)session.getAttribute("selectedStock");
		 approvisionnements=proxyApprovisionnement.getAllApproByStock(selectedStock);
		
	}



	public List<Approvisionnement> getApprovisionnements() {
		return approvisionnements;
	}



	public void setApprovisionnements(List<Approvisionnement> approvisionnements) {
		this.approvisionnements = approvisionnements;
	}



	public Stock getSelectedStock() {
		return selectedStock;
	}



	public void setSelectedStock(Stock selectedStock) {
		this.selectedStock = selectedStock;
	}



	public ApprovisionnementIBusiness getProxyApprovisionnement() {
		return proxyApprovisionnement;
	}



	public void setProxyApprovisionnement(ApprovisionnementIBusiness proxyApprovisionnement) {
		this.proxyApprovisionnement = proxyApprovisionnement;
	}
	

}
