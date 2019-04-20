package fr.afcepf.al33.projet1.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;


@Entity
@Table(name = "fournisseur")
public class Fournisseur implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id", nullable=false)
	private Integer id;
	
	@Column(name="nom")
	private String nom;
		
	@Column(name="adresse")
	private String adresse;
	
	@Column(name="complementAdresse")
	private String complementAdresse;
	
	@ManyToOne
	@JoinColumn(referencedColumnName="id")
	private Ville ville;
	
	@Column(name="numeroFixe")
	private String numeroFixe;
	
	@Column(name="numeroPort")
	private String numeroPort;
	
	@Column(name="email")
	private String email;

	@OneToMany(mappedBy="fournisseur", cascade=CascadeType.ALL, fetch=FetchType.EAGER)
	private List<Approvisionnement> approvisionnements;
	
	//Constructeur vide
	
	public Fournisseur() {
		super();
	}

	//Constructeur chargé
	
	public Fournisseur(Integer id, String nom, String adresse, String complementAdresse, Ville ville, String numeroFixe,
			String numeroPort, String email, List<Approvisionnement> approvisionnements) {
		super();
		this.id = id;
		this.nom = nom;
		this.adresse = adresse;
		this.complementAdresse = complementAdresse;
		this.ville = ville;
		this.numeroFixe = numeroFixe;
		this.numeroPort = numeroPort;
		this.email = email;
		this.approvisionnements = approvisionnements;
	}


	//GETTERS ET SETTERS

	public Integer getId() {
		return id;
	}



	public void setId(Integer id) {
		this.id = id;
	}


	public String getNom() {
		return nom;
	}


	public void setNom(String nom) {
		this.nom = nom;
	}


	public String getAdresse() {
		return adresse;
	}


	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}


	public String getComplementAdresse() {
		return complementAdresse;
	}


	public void setComplementAdresse(String complementAdresse) {
		this.complementAdresse = complementAdresse;
	}


	public Ville getVille() {
		return ville;
	}


	public void setVille(Ville ville) {
		this.ville = ville;
	}


	public String getNumeroFixe() {
		return numeroFixe;
	}


	public void setNumeroFixe(String numeroFixe) {
		this.numeroFixe = numeroFixe;
	}


	public String getNumeroPort() {
		return numeroPort;
	}


	public void setNumeroPort(String numeroPort) {
		this.numeroPort = numeroPort;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}
	
	
	public List<Approvisionnement> getApprovisionnements() {
		return approvisionnements;
	}

	public void setApprovisionnements(List<Approvisionnement> approvisionnements) {
		this.approvisionnements = approvisionnements;
	}


	//Hashcode and equals
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((adresse == null) ? 0 : adresse.hashCode());
		result = prime * result + ((approvisionnements == null) ? 0 : approvisionnements.hashCode());
		result = prime * result + ((complementAdresse == null) ? 0 : complementAdresse.hashCode());
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((nom == null) ? 0 : nom.hashCode());
		result = prime * result + ((numeroFixe == null) ? 0 : numeroFixe.hashCode());
		result = prime * result + ((numeroPort == null) ? 0 : numeroPort.hashCode());
		result = prime * result + ((ville == null) ? 0 : ville.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Fournisseur other = (Fournisseur) obj;
		if (adresse == null) {
			if (other.adresse != null)
				return false;
		} else if (!adresse.equals(other.adresse))
			return false;
		if (approvisionnements == null) {
			if (other.approvisionnements != null)
				return false;
		} else if (!approvisionnements.equals(other.approvisionnements))
			return false;
		if (complementAdresse == null) {
			if (other.complementAdresse != null)
				return false;
		} else if (!complementAdresse.equals(other.complementAdresse))
			return false;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (nom == null) {
			if (other.nom != null)
				return false;
		} else if (!nom.equals(other.nom))
			return false;
		if (numeroFixe == null) {
			if (other.numeroFixe != null)
				return false;
		} else if (!numeroFixe.equals(other.numeroFixe))
			return false;
		if (numeroPort == null) {
			if (other.numeroPort != null)
				return false;
		} else if (!numeroPort.equals(other.numeroPort))
			return false;
		if (ville == null) {
			if (other.ville != null)
				return false;
		} else if (!ville.equals(other.ville))
			return false;
		return true;
	}

	
	@Override
	public String toString() {
		return "Fournisseur [id=" + id + ", nom=" + nom + ", adresse=" + adresse + ", complementAdresse="
				+ complementAdresse + ", ville=" + ville + ", numeroFixe=" + numeroFixe + ", numeroPort=" + numeroPort
				+ ", email=" + email + "]";
	}
	
	
}
