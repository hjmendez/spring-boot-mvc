/**
 * Aviso legal
 * 
 * Toda la información contenida aquí es propiedad de Diners Club Spain, S.A. y está protegida por
 * la ley de propiedad intelectual. Cualquier difusión o reproducción total o parcial, incluso para
 * uso por personal interno (empleado) o externo (proveedor), por cualquier medio y bajo cualquier
 * forma, está prohibida, salvo autorización expresa por parte de Diners Club Spain, otorgada con
 * carácter previo y de forma escrita.
 * 
 * El uso o acceso permitido a dicha información no podrá entenderse como cesión de ninguna clase de
 * derecho de explotación sobre los citados derechos de propiedad intelectual.
 */


package es.vilex.app.services;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import es.vilex.app.dao.ClientDao;
import es.vilex.app.entities.Client;

@Service
public class ClientServiceImpl implements ClientService {

  @Autowired
  private ClientDao clientDao;

  @Override
  @Transactional(readOnly = true)
  public List<Client> findAll() {
    return (List<Client>) clientDao.findAll();
  }

  @Override
  @Transactional(readOnly = true)
  public Client findById(Long id) {
    Optional<Client> optionalClient = clientDao.findById(id);
    if (optionalClient.isPresent()) {
      return optionalClient.get();
    } else {
      return null;
    }

  }

  @Override
  @Transactional
  public void save(Client client) {
    clientDao.save(client);
  }

  @Override
  @Transactional
  public void delete(Long id) {
    clientDao.deleteById(id);
  }

  @Override
  @Transactional(readOnly = true)
  public Page<Client> findAll(Pageable pageable) {
    return clientDao.findAll(pageable);
  }

}
