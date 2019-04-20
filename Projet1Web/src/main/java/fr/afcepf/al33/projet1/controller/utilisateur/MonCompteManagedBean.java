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

import fr.afcepf.al33.projet1.IBusiness.ClientIBusiness;
import fr.afcepf.al33.projet1.IBusiness.CommandeIBusiness;
import fr.afcepf.al33.projet1.entity.Client;
import fr.afcepf.al33.projet1.entity.Commande;




@ManagedBean(name="mbMonCompte")
@SessionScoped
public class MonCompteManagedBean implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@EJB
	private ClientIBusiness proxyClient;
	
	@EJB
	private CommandeIBusiness proxyCommandes;
	
	
	@ManagedProperty(value="#{mbConnectionUtilisateur.clientConnecte}")
	private Client client;
	
	List<Commande> commandes= new ArrayList<Commande>();
	
	private Commande selectedCommandePerso;
	
	public void onSelect(Commande commande) {
		
		selectedCommandePerso=commande;
	  
		System.out.println(commande.getId());
		
		
		 FacesContext facesContext = FacesContext.getCurrentInstance();
		 HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(false);
		 session.setAttribute("selectedCommandePerso", selectedCommandePerso);
		 facesContext.getApplication().getNavigationHandler().handleNavigation(facesContext,null,"/interfaceClient/afficherMaCommande.xhtml?faces-redirect=true");
	}

	
	
	

	@PostConstruct
	public void init() {


		FacesContext fc = FacesContext.getCurrentInstance();
		HttpSession session = (HttpSession) fc.getExternalContext().getSession(false);
		client = (Client) session.getAttribute("clientConnecte");
		
		commandes= proxyCommandes.getAllByClient(client);
		
	
	}



	public ClientIBusiness getProxyClient() {
		return proxyClient;
	}



	public void setProxyClient(ClientIBusiness proxyClient) {
		this.proxyClient = proxyClient;
	}


	public Client getClient() {
		return client;
	}




	public void setClient(Client client) {
		this.client = client;
	}



	public List<Commande> getCommandes() {
		return commandes;
	}



	public void setCommandes(List<Commande> commandes) {
		this.commandes = commandes;
	}





	public Commande getSelectedCommandePerso() {
		return selectedCommandePerso;
	}





	public void setSelectedCommandePerso(Commande selectedCommandePerso) {
		this.selectedCommandePerso = selectedCommandePerso;
	}


	
}
