package fr.afcepf.al33.projet1.controller.admin;

import java.io.Serializable;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import fr.afcepf.al33.projet1.IBusiness.ClientIBusiness;
import fr.afcepf.al33.projet1.IBusiness.VilleIBusiness;
import fr.afcepf.al33.projet1.entity.Client;
import fr.afcepf.al33.projet1.entity.Ville;

@ManagedBean(name="NouveauClientManagedBean")
@SessionScoped
public class NouveauClientManagedBean implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Client client = new Client();
	private Ville ville = new Ville();
	private String nomVille;
	private String cpVille;
	
	@EJB
	private ClientIBusiness proxyClient;
	
	@EJB
	private VilleIBusiness proxyVille;
	
	
	public void creerClient()
	{
		ville = proxyVille.rechercherParVilleEtCodePostal(nomVille, cpVille);
		client.setVille(ville);
		proxyClient.add(client);
		
	}
	
	

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	public Ville getVille() {
		return ville;
	}

	public void setVille(Ville ville) {
		this.ville = ville;
	}

	public String getNomVille() {
		return nomVille;
	}



	public void setNomVille(String nomVille) {
		this.nomVille = nomVille;
	}



	public String getCpVille() {
		return cpVille;
	}



	public void setCpVille(String cpVille) {
		this.cpVille = cpVille;
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
	

}
