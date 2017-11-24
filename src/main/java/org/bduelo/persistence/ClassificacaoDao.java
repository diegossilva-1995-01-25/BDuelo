/**
 * 
 */
package org.bduelo.persistence;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.bduelo.model.Classificacao;
import org.bduelo.util.TarefasComuns;

/**
 * @author Caio
 *
 */
public class ClassificacaoDao implements DaoGenerica<Classificacao, Integer> {

	private EntityManager entityManager;
	
	
	/* (non-Javadoc)
	 * @see org.bduelo.persistence.DaoGenerica#insert(java.lang.Classificacao)
	 */
	@Override
	public void insert(Classificacao classificacao) {
		
		entityManager = TarefasComuns.getInstanciaEntityManager();
		entityManager.persist(classificacao);
	}

	
	/* (non-Javadoc)
	 * @see org.bduelo.persistence.DaoGenerica#update(java.lang.Classificacao)
	 */
	@Override
	public void update(Classificacao classificacao) {
		
		entityManager = TarefasComuns.getInstanciaEntityManager();
		entityManager.merge(classificacao);
	}

	
	/* (non-Javadoc)
	 * @see org.bduelo.persistence.DaoGenerica#delete(java.io.Serializable)
	 */
	@Override
	public void delete(Integer chave) {
		
		entityManager = TarefasComuns.getInstanciaEntityManager();
		
		Classificacao classificacao = select(chave);
		entityManager.remove(classificacao);
	}

	
	/* (non-Javadoc)
	 * @see org.bduelo.persistence.DaoGenerica#selectListaLimitada()
	 */
	@Override
	@SuppressWarnings("unchecked")
	public List<Classificacao> selectListaLimitada() {
		
		entityManager = TarefasComuns.getInstanciaEntityManager();
		Query query = entityManager.createNamedQuery("Classificacao.selectLista");
		
		return query.setMaxResults(100).getResultList();
	}

	
	/* (non-Javadoc)
	 * @see org.bduelo.persistence.DaoGenerica#selectMultiplos()
	 */
	@Override
	@SuppressWarnings("unchecked")
	public List<Classificacao> selectMultiplos(Classificacao classificacao) {
		
		entityManager = TarefasComuns.getInstanciaEntityManager();
		Query query = entityManager.createNamedQuery("Classificacao.selectMulti");
		
		query.setParameter("posicao", "%" + classificacao.getPosicao() + "%" );
		
		return query.setMaxResults(100).getResultList();
	}

	
	/* (non-Javadoc)
	 * @see org.bduelo.persistence.DaoGenerica#select(java.io.Serializable)
	 */
	@Override
	public Classificacao select(Integer chave) {
		
		entityManager = TarefasComuns.getInstanciaEntityManager();
		return entityManager.find(Classificacao.class, chave);
	}

}
