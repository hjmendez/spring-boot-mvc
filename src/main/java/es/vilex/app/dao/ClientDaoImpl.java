
package es.vilex.app.dao;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import es.vilex.app.entities.Client;

@Repository
public class ClientDaoImpl implements ClientDao {

  @PersistenceContext
  private EntityManager em;

  @SuppressWarnings("unchecked")
  @Transactional(readOnly = true)
  @Override
  public List<Client> findAll() {
    return em.createQuery("from Client").getResultList();
  }

  @Transactional(readOnly = true)
  public Client findById(Long id) {
    return em.find(Client.class, id);
  }


  @Override
  @Transactional
  public void save(Client client) {
    if (client.getId() == null) {
      em.persist(client);
    } else {
      em.merge(client);
    }

  }

  @Override
  @Transactional
  public void delete(Long id) {
    em.remove(findById(id));
  }

}
