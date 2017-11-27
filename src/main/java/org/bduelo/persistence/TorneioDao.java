/**
 * 
 */
package org.bduelo.persistence;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.StoredProcedureQuery;

import org.bduelo.model.Torneio;
import org.bduelo.util.TarefasComuns;

/**
 * @author Caio
 *
 */
public class TorneioDao implements DaoGenerica<Torneio, Integer> {

	private EntityManager entityManager;
	
	
	/* (non-Javadoc)
	 * @see org.bduelo.persistence.DaoGenerica#insert(java.lang.Object)
	 */
	@Override
	public void insert(Torneio torneio) {
		
		entityManager = TarefasComuns.getInstanciaEntityManager();
		entityManager.persist(torneio);
	}

	/* (non-Javadoc)
	 * @see org.bduelo.persistence.DaoGenerica#update(java.lang.Object)
	 */
	@Override
	public void update(Torneio torneio) {
		
		entityManager = TarefasComuns.getInstanciaEntityManager();
		/*entityManager.merge(torneio);*/
            
            StoredProcedureQuery query;
            query = entityManager.createNamedStoredProcedureQuery("Torneio.update");
            
            query.setParameter("id", torneio.getIdTorneio() );
            query.setParameter("dataTorn", torneio.getData() );
            query.setParameter("inicio", torneio.getHoraInicio() );
            query.setParameter("fim", torneio.getHoraFim() );
            query.setParameter("partida", torneio.getJogo() );
            
            query.execute();
	}

	/* (non-Javadoc)
	 * @see org.bduelo.persistence.DaoGenerica#delete(java.io.Serializable)
	 */
	@Override
	public void delete(Integer chave) {
		
		entityManager = TarefasComuns.getInstanciaEntityManager();
		
		Torneio torneio = select(chave);
		entityManager.remove(torneio);
	}

	/* (non-Javadoc)
	 * @see org.bduelo.persistence.DaoGenerica#selectListaLimitada()
	 */
	@Override
	@SuppressWarnings("unchecked")
	public List<Torneio> selectListaLimitada() {
		
		entityManager = TarefasComuns.getInstanciaEntityManager();
		Query query = entityManager.createNamedQuery("Torneio.selectLista");
		
		return query.setMaxResults(100).getResultList();
	}

	/* (non-Javadoc)
	 * @see org.bduelo.persistence.DaoGenerica#selectMultiplos()
	 */
	@Override
	@SuppressWarnings("unchecked")
	public List<Torneio> selectMultiplos(Torneio torneio) {
		
		entityManager = TarefasComuns.getInstanciaEntityManager();
		Query query = entityManager.createNamedQuery("Torneio.selectMulti");
		
		query.setParameter("data", torneio.getData() );
		
		return query.setMaxResults(100).getResultList();
	}

	/* (non-Javadoc)
	 * @see org.bduelo.persistence.DaoGenerica#select(java.io.Serializable)
	 */
	@Override
	public Torneio select(Integer chave) {
		
		entityManager = TarefasComuns.getInstanciaEntityManager();
		return entityManager.find(Torneio.class, chave);
	}

}
