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


package es.vilex.app.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import es.vilex.app.dao.ClientDao;
import es.vilex.app.entities.Client;

@Controller
@RequestMapping("/clients")
public class ClientController {

  @Autowired
  private ClientDao clientDao;

  @GetMapping("/list")
  public String list(Model model) {
    model.addAttribute("title", "Listado de clientes");
    model.addAttribute("clients", clientDao.findAll());
    return "list";
  }

  @RequestMapping(value = "/form")
  public String create(Model model) {
    model.addAttribute("title", "Formulario de cliente");
    model.addAttribute("client", new Client());
    return "form";
  }

  @RequestMapping(value = "/save", method = RequestMethod.POST)
  public String save(Client client) {
    clientDao.save(client);
    return "redirect:list";
  }

}
