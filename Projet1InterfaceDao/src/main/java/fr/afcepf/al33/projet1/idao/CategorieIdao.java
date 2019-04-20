package fr.afcepf.al33.projet1.idao;

import java.util.List;

import fr.afcepf.al33.projet1.entity.Categorie;



public interface CategorieIdao extends GenericIdao<Categorie> {


	List<Categorie> getAll();


	
}
