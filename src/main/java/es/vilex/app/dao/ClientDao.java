
package es.vilex.app.dao;

import org.springframework.data.repository.CrudRepository;
import es.vilex.app.entities.Client;

public interface ClientDao extends CrudRepository<Client, Long> {

}
