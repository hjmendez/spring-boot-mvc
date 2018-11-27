
package es.vilex.app.dao;

import java.util.List;
import es.vilex.app.entities.Client;

public interface ClientDao {
  /**
   * list all clients of bbdd
   * 
   * @return
   */
  public List<Client> findAll();

  /**
   * Save or update a client
   * 
   * @param client
   */
  public void save(Client client);

}
