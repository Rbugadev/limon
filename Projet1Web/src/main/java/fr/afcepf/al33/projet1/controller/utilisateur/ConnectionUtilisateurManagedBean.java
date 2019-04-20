package fr.afcepf.al33.projet1.controller.utilisateur;

import java.io.Serializable;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import fr.afcepf.al33.projet1.IBusiness.ClientIBusiness;
import fr.afcepf.al33.projet1.entity.Client;

@ManagedBean(name="mbConnectionUtilisateur")
@SessionScoped
public class ConnectionUtilisateurManagedBean implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Client clientConnecte;
	
	private String login;
	private String password;
	private String admin = "admin";
	
	@EJB
	private ClientIBusiness proxyClient;
	
	
	public Client getClientConnecte() {
		return clientConnecte;
	}

	public void setClientConnecte(Client clientConnecte) {
		this.clientConnecte = clientConnecte;
	}

	public ClientIBusiness getProxyClient() {
		return proxyClient;
	}

	public void setProxyClient(ClientIBusiness proxyClient) {
		this.proxyClient = proxyClient;
	}
	
	
	
	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}


	
	public void sIdentifier()
	{
		clientConnecte = proxyClient.seConnecter(login, password);
		if ( clientConnecte != null)
		{
			if ( clientConnecte.getLogin().equals(admin))
			{
				FacesContext facesContext = FacesContext.getCurrentInstance();
				HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(false);
				session.setAttribute("clientConnecte", clientConnecte);
				facesContext.getApplication().getNavigationHandler().handleNavigation(facesContext,null,"/interfaceAdmin/accueilAdmin.xhtml?faces-redirect=true");
				
			}
			else
			{
				 FacesContext facesContext = FacesContext.getCurrentInstance();
				 HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(false);
				 session.setAttribute("clientConnecte", clientConnecte);
				 
				 facesContext.getApplication().getNavigationHandler().handleNavigation(facesContext,null,"/interfaceClient/accueilClient.xhtml?faces-redirect=true");
			}
		
		}
		else
		{
			FacesContext facesContext = FacesContext.getCurrentInstance();
			 facesContext.getApplication().getNavigationHandler().handleNavigation(facesContext,null,"/interfaceAdmin/masterPageAdmin.xhtml?faces-redirect=true");
			 

		}		
		
	}
	
	public void seDeconnecter()
	{
		FacesContext facesContext = FacesContext.getCurrentInstance();
		HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(false);
		session.invalidate();
		//session.removeAttribute("clientConnecte");
		facesContext.getApplication().getNavigationHandler().handleNavigation(facesContext,null,"/interfaceClient/accueilClient.xhtml?faces-redirect=true");
	}

}
