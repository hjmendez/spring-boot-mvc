
package es.vilex.app.dao;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import es.vilex.app.entities.Client;

@Repository
public class ClientDaoImpl implements ClientDao {

  @PersistenceContext
  private EntityManager em;

  @SuppressWarnings("unchecked")
  @Override
  public List<Client> findAll() {
    return em.createQuery("from Client").getResultList();
  }

  public Client findById(Long id) {
    return em.find(Client.class, id);
  }


  @Override
  public void save(Client client) {
    if (client.getId() == null) {
      em.persist(client);
    } else {
      em.merge(client);
    }

  }

  @Override
  public void delete(Long id) {
    em.remove(findById(id));
  }

}
