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

import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import es.vilex.app.aspects.DatabaseLock;
import es.vilex.app.entities.Client;
import es.vilex.app.services.ClientService;

@Controller
@RequestMapping("/clients")
@SessionAttributes("client")
public class ClientController {

  @Autowired
  private ClientService clientService;

  @GetMapping("/list")
  @DatabaseLock
  public String list(Model model) {
    model.addAttribute("title", "Listado de clientes");
    model.addAttribute("clients", clientService.findAll());
    return "list";
  }

  @RequestMapping(value = "/form")
  public String create(Model model) {
    model.addAttribute("title", "Formulario de cliente");
    model.addAttribute("client", new Client());
    return "form";
  }

  @RequestMapping(value = "/form/{id}")
  public String edit(@PathVariable(value = "id") Long id, Model model) {
    Client client = null;
    if (id > 0) {
      client = clientService.findById(id);
    } else {
      return "redirect:/list";
    }
    model.addAttribute("title", "Formulario de cliente");
    model.addAttribute("client", client);
    return "form";
  }

  @RequestMapping(value = "/save", method = RequestMethod.POST)
  public String save(@Valid Client client, BindingResult result, Model model,
      SessionStatus status) {
    if (result.hasErrors()) {
      model.addAttribute("title", "Formulario de cliente");
      return "form";
    }
    clientService.save(client);
    status.setComplete();
    return "redirect:list";
  }

  @RequestMapping(value = "/delete/{id}")
  public String delete(@PathVariable Long id) {
    if (id > 0) {
      clientService.delete(id);
    }
    return "redirect:/clients/list";
  }

}
