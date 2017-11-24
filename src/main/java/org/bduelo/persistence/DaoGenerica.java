package org.bduelo.persistence;

import java.io.Serializable;
import java.util.List;

public interface DaoGenerica <T, K extends Serializable> {

	void insert(T objeto);
	void update(T objeto);
	void delete(K chave);
	
	List<T> selectListaLimitada();
	List<T> selectMultiplos(T objeto);
	T select(K chave);
}
