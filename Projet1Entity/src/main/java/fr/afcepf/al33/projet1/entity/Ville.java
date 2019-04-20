package fr.afcepf.al33.projet1.entity;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="ville")
public class Ville implements Serializable{

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

	@Column(name="codePostal")
	private String codePostal;

	//Constructeur vide

	public Ville() {
		super();

	}


	//Constructeur chargé


	public Ville(Integer id, String nom, String codePostal) {
		super();
		this.id = id;
		this.nom = nom;
		this.codePostal = codePostal;
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


public String getCodePostal() {
	return codePostal;
}


public void setCodePostal(String codePostal) {
	this.codePostal = codePostal;
}


@Override
public int hashCode() {
	final int prime = 31;
	int result = 1;
	result = prime * result + ((codePostal == null) ? 0 : codePostal.hashCode());
	result = prime * result + ((id == null) ? 0 : id.hashCode());
	result = prime * result + ((nom == null) ? 0 : nom.hashCode());
	return result;
}


//HashCode and equals

@Override
public boolean equals(Object obj) {
	if (this == obj)
		return true;
	if (obj == null)
		return false;
	if (getClass() != obj.getClass())
		return false;
	Ville other = (Ville) obj;
	if (codePostal == null) {
		if (other.codePostal != null)
			return false;
	} else if (!codePostal.equals(other.codePostal))
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
	return true;
}


@Override
public String toString() {
	return "Ville [id=" + id + ", nom=" + nom + ", codePostal=" + codePostal + "]";
}



}
