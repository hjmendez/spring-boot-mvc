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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import es.vilex.app.entities.Client;
import es.vilex.app.entities.Factura;
import es.vilex.app.services.ClientService;

@Controller
@RequestMapping("/factura")
@SessionAttributes("factura")
public class FacturaController {

  @Autowired
  private ClientService clientService;

  @GetMapping("/form/{clienteId}")
  public String create(@PathVariable Long clienteId, Model model, RedirectAttributes flash) {

    Client client = clientService.findById(clienteId);
    if (client == null) {
      flash.addFlashAttribute("error", "El cliente no existe");
      return "redirect:/clients/list";
    }

    Factura factura = new Factura();
    factura.setCliente(client);

    model.addAttribute("factura", factura);
    model.addAttribute("title", "Crear factura");

    return "/factura/form";
  }

}
