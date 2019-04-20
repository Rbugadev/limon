package fr.afcepf.al33.projet1.Business;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Remote;
import javax.ejb.Stateless;

import org.apache.log4j.Logger;

import fr.afcepf.al33.projet1.IBusiness.ApprovisionnementIBusiness;
import fr.afcepf.al33.projet1.entity.Approvisionnement;
import fr.afcepf.al33.projet1.entity.Stock;
import fr.afcepf.al33.projet1.idao.ApprovisionnementIdao;

@Remote(ApprovisionnementIBusiness.class)
@Stateless
public class ApprovisionnementBusiness implements ApprovisionnementIBusiness{
	
	final Logger logger = Logger.getLogger(this.getClass());

	@EJB
	private ApprovisionnementIdao proxyApprovisionnement;
	
	@Override
	public Approvisionnement addApprovisionnement(Approvisionnement a) {		
		proxyApprovisionnement.ajouter(a);
		return a;
	}

	@Override
	public List<Approvisionnement> getAllApproByStock(Stock stock) {
		List<Approvisionnement> approvisionnements=null;
		approvisionnements= proxyApprovisionnement.getAllApproByStock(stock);
		return approvisionnements;
	}

	@Override
	public Boolean delete(Approvisionnement approvisionnement) {
		proxyApprovisionnement.supprimer(approvisionnement);
		return true;
	}

	@Override
	public Approvisionnement update(Approvisionnement approvisionnement) {
		proxyApprovisionnement.modifier(approvisionnement);
		return approvisionnement;
	}

	@Override
	public Approvisionnement searchById(Integer id) {
		Approvisionnement approvisionnement = proxyApprovisionnement.rechercherParId(id);
		return approvisionnement;
	}

	@Override
	public List<Approvisionnement> getOutOfDateAppro(int nbJours) {
		if(logger.isDebugEnabled()) {
			logger.debug("getOutOfDateAppro pour approvisionnements périmés dans " + nbJours + " jours ");
		}
		List<Approvisionnement> approvisionnements=null;
		approvisionnements= proxyApprovisionnement.getOutOfDateAppro(nbJours);
		if(logger.isDebugEnabled()) {
			logger.debug("getOutOfDateAppro pour approvisionnements périmés dans " + nbJours + " jours "
					+ approvisionnements.size() + " trouvés");
		}
		return approvisionnements;
	}

	@Override
	public List<Approvisionnement> getApproRupture(){
		List<Approvisionnement> approvisionnements=null;
		approvisionnements = proxyApprovisionnement.getApproRupture();
		return approvisionnements;
	}

	@Override
	public List<Approvisionnement> getAllApproPerimes() {
		List<Approvisionnement> approvisionnements=null;
		approvisionnements = proxyApprovisionnement.getAllApproPerimes();
		return approvisionnements;
	}

	

}
