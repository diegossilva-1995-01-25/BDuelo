/**
 * 
 */
package org.bduelo.persistence;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.StoredProcedureQuery;

import org.bduelo.model.Time;
import org.bduelo.util.TarefasComuns;

/**
 * @author Caio
 *
 */
public class TimeDao implements DaoGenerica<Time, Integer> {

	private EntityManager entityManager;
	
	
	/* (non-Javadoc)
	 * @see org.bduelo.persistence.DaoGenerica#insert(java.lang.Object)
	 */
	@Override
	public void insert(Time time) {
		
		entityManager = TarefasComuns.getInstanciaEntityManager();
		entityManager.persist(time);
	}

	/* (non-Javadoc)
	 * @see org.bduelo.persistence.DaoGenerica#update(java.lang.Object)
	 */
	@Override
	public void update(Time time) {
		
		entityManager = TarefasComuns.getInstanciaEntityManager();
		/*entityManager.merge(time);*/
            
            StoredProcedureQuery query;
            query = entityManager.createNamedStoredProcedureQuery("Time.update");
            
            query.setParameter("id", time.getIdTime() );
            query.setParameter("nom", time.getNome() );
            query.setParameter("estado", time.getStatus() );
            
            query.execute();
	}

	/* (non-Javadoc)
	 * @see org.bduelo.persistence.DaoGenerica#delete(java.io.Serializable)
	 */
	@Override
	public void delete(Integer chave) {
		
		entityManager = TarefasComuns.getInstanciaEntityManager();
		
		Time time = select(chave);
		entityManager.remove(time);
	}

	/* (non-Javadoc)
	 * @see org.bduelo.persistence.DaoGenerica#selectListaLimitada()
	 */
	@Override
	@SuppressWarnings("unchecked")
	public List<Time> selectListaLimitada() {
		
		entityManager = TarefasComuns.getInstanciaEntityManager();
		Query query = entityManager.createNamedQuery("Time.selectLista");

		return (List<Time>) query.setMaxResults(100).getResultList();
	}

	/* (non-Javadoc)
	 * @see org.bduelo.persistence.DaoGenerica#selectMultiplos()
	 */
	@Override
	@SuppressWarnings("unchecked")
	public List<Time> selectMultiplos(Time time) {
		
		entityManager = TarefasComuns.getInstanciaEntityManager();
		Query query = entityManager.createNamedQuery("Time.selectMulti");
		
		query.setParameter("nome", "%" + time.getNome() + "%" );
		
		return query.setMaxResults(100).getResultList();
	}

	/* (non-Javadoc)
	 * @see org.bduelo.persistence.DaoGenerica#select(java.io.Serializable)
	 */
	@Override
	public Time select(Integer chave) {
		
		entityManager = TarefasComuns.getInstanciaEntityManager();
		return entityManager.find(Time.class, chave);
	}

}
