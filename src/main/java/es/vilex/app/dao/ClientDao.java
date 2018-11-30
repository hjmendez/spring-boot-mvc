
package es.vilex.app.dao;

import org.springframework.data.repository.PagingAndSortingRepository;
import es.vilex.app.entities.Client;

public interface ClientDao extends PagingAndSortingRepository<Client, Long> {

}
