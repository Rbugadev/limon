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

import fr.afcepf.al33.projet1.IBusiness.ClientIBusiness;
import fr.afcepf.al33.projet1.IBusiness.VilleIBusiness;
import fr.afcepf.al33.projet1.entity.Client;
import fr.afcepf.al33.projet1.entity.Ville;




@ManagedBean(name="mbUpdateClient")
@SessionScoped
public class UpdateClientManagedBean implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@EJB
	private ClientIBusiness proxyClient;
	
	@EJB
	private VilleIBusiness proxyVille;
	


	
	@ManagedProperty(value="#{mbFindClient.foundClient}")
	private Client client;
	
	private List<Ville> villes;
	


	@PostConstruct
	public void init() {


		FacesContext fc = FacesContext.getCurrentInstance();
		HttpSession session = (HttpSession) fc.getExternalContext().getSession(false);
		client = (Client) session.getAttribute("foundClient");
		villes = proxyVille.getAll();
	}
	
	public void update() {
		
		proxyClient.update(client);
		FacesContext facesContext = FacesContext.getCurrentInstance();
		facesContext.getApplication().getNavigationHandler().handleNavigation(facesContext,null,"/interfaceAdmin/ficheClient.xhtml?faces-redirect=true");
	}

	public ClientIBusiness getProxyClient() {
		return proxyClient;
	}

	public void setProxyClient(ClientIBusiness proxyClient) {
		this.proxyClient = proxyClient;
	}

	public VilleIBusiness getProxyVille() {
		return proxyVille;
	}

	public void setProxyVille(VilleIBusiness proxyVille) {
		this.proxyVille = proxyVille;
	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	public List<Ville> getVilles() {
		return villes;
	}

	public void setVilles(List<Ville> villes) {
		this.villes = villes;
	}


}
