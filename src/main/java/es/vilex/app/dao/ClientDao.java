
package es.vilex.app.dao;

import java.util.List;
import es.vilex.app.entities.Client;

public interface ClientDao {

  public List<Client> findAll();

  public Client findById(Long id);

  public void save(Client client);


  public void delete(Long id);

}
