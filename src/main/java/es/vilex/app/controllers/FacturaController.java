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

import java.util.List;
import javax.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import es.vilex.app.entities.Client;
import es.vilex.app.entities.Factura;
import es.vilex.app.entities.ItemFactura;
import es.vilex.app.entities.Producto;
import es.vilex.app.services.ClientService;
import es.vilex.app.services.FacturaService;
import es.vilex.app.services.ProductoService;

@Controller
@RequestMapping("/factura")
@SessionAttributes("factura")
public class FacturaController {

  private final Logger log = LoggerFactory.getLogger(getClass());

  @Autowired
  private ClientService clientService;

  @Autowired
  private ProductoService productoService;

  @Autowired
  private FacturaService facturaService;

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

  @PostMapping(value = "/save")
  public String save(@Valid Factura factura, BindingResult result, Model model,
      @RequestParam(name = "item_id[]", required = false) Long[] itemIdList,
      @RequestParam(name = "cantidad[]", required = false) Integer[] cantidadList,
      RedirectAttributes flash) {

    if (result.hasErrors()) {
      model.addAttribute("title", "Crear factura");
      return "/factura/form";
    }

    Integer i = 0;
    for (Long itemId : itemIdList) {
      Producto producto = productoService.findById(itemId);
      ItemFactura itemFactura = new ItemFactura();
      itemFactura.setCantidad(cantidadList[i]);
      itemFactura.setProducto(producto);
      factura.addItemFactura(itemFactura);
      log.info(String.format("ID {%s}, Cantidad {%s}", itemId, cantidadList[i]));
    }
    flash.addFlashAttribute("success", "Factura creada con éxito");
    facturaService.save(factura);

    return "redirect:/clients/detail/" + factura.getCliente().getId();
  }

  @GetMapping(value = "cargar-productos/{term}", produces = {"application/json"})
  public @ResponseBody List<Producto> cargarProductos(@PathVariable String term) {
    return productoService.findByNombreIgnoreCase("%" + term + "%");
  }

}
