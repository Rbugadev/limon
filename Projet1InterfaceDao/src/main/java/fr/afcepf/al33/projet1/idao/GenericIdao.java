package fr.afcepf.al33.projet1.idao;


public interface GenericIdao<T> {
	
	
	T ajouter(T t);
	boolean supprimer(T t);
	T modifier(T t);
	T rechercherParId(Object o);
	
}
