
package org.meruvian.yama.core;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.PagingAndSortingRepository;


@NoRepositoryBean
public interface DefaultRepository<T extends DefaultPersistence> extends PagingAndSortingRepository<T, String> {
	T findById(String id);
	
	Page<T> findByLogInformationActiveFlag(int activeFlag, Pageable pageable);
}
