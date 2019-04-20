package fr.afcepf.al33.projet1.dao;

import java.time.LocalDate;
import java.util.List;

import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.apache.log4j.Logger;

import fr.afcepf.al33.projet1.entity.Approvisionnement;
import fr.afcepf.al33.projet1.entity.Stock;
import fr.afcepf.al33.projet1.idao.ApprovisionnementIdao;


@Remote(ApprovisionnementIdao.class)
@Stateless
public class ApprovisionnementDao extends GenericDao<Approvisionnement> implements ApprovisionnementIdao {
	
	final Logger logger = Logger.getLogger(this.getClass());

	@PersistenceContext(unitName="Projet1DS")
	private EntityManager em;

	@SuppressWarnings("unchecked")
	@Override
	public List<Approvisionnement> getAllApproByStock(Stock stock) {
		List<Approvisionnement> approvisionnements = null;
		String REQ = "SELECT approvisionnement from Approvisionnement approvisionnement WHERE approvisionnement.stock.id = :id ORDER BY approvisionnement.datePeremption DESC";
		Query queryJPQL = em.createQuery(REQ);
		queryJPQL.setParameter("id", stock.getId());
		approvisionnements = queryJPQL.getResultList();
		return approvisionnements;
		
	}
	
	@SuppressWarnings("unchecked")
	public List<Approvisionnement> getOutOfDateAppro(int nbJours) {
		// retourne la liste des approvisionnements
		// avec date de péremption proche de la date du jour
				
		List<Approvisionnement> approvisionnements = null;
		java.sql.Date today = java.sql.Date.valueOf(LocalDate.now());
		java.sql.Date dateButoire;
		dateButoire = java.sql.Date.valueOf(LocalDate.now().plusDays(nbJours));
		String REQ = "SELECT appro from Approvisionnement appro WHERE appro.datePeremption <= :date AND appro.datePeremption >= :today";
		Query queryJPQL = em.createQuery(REQ);
		queryJPQL.setParameter("date", dateButoire);
		queryJPQL.setParameter("today", today);
		if(logger.isDebugEnabled()) {
			logger.debug("requête pour approvisionnements périmés dans " + nbJours + " jours (" + dateButoire + ") : " + queryJPQL.toString());
		}
		approvisionnements = queryJPQL.getResultList();
		if(logger.isDebugEnabled()) {
			logger.debug("requête pour approvisionnements périmés dans " + nbJours + " jours : " 
					+ approvisionnements.size() + " trouvés");
		}
		return approvisionnements;
		
	}
	
	@SuppressWarnings("unchecked")
	public List<Approvisionnement> getApproRupture() {
		List<Approvisionnement> approvisionnements = null;
		String REQ = "SELECT approvisionnement from Approvisionnement approvisionnement WHERE approvisionnement.quantiteRestante <= 50";
		Query queryJPQL = em.createQuery(REQ);
		approvisionnements = queryJPQL.getResultList();
		return approvisionnements;
		
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Approvisionnement> getAllApproPerimes() {
		List<Approvisionnement> approvisionnements = null;
		java.sql.Date today = java.sql.Date.valueOf(LocalDate.now());

		String REQ = "SELECT appro from Approvisionnement appro WHERE appro.datePeremption < :today AND appro.dateMiseAuRebut = null";
		Query queryJPQL = em.createQuery(REQ);
		queryJPQL.setParameter("today", today);

		approvisionnements = queryJPQL.getResultList();

		return approvisionnements;
	}
	
}
