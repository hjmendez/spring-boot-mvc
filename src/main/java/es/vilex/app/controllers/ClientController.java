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

import java.io.IOException;
import java.net.MalformedURLException;
import javax.validation.Valid;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import es.vilex.app.aspects.DatabaseLock;
import es.vilex.app.config.AppConstants;
import es.vilex.app.entities.Client;
import es.vilex.app.services.ClientService;
import es.vilex.app.services.UploadFileService;
import es.vilex.app.util.paginator.PageRender;

@Controller
@RequestMapping("/clients")
@SessionAttributes("client")
public class ClientController {

  private final Logger log = org.slf4j.LoggerFactory.getLogger(getClass());

  @Autowired
  private ClientService clientService;

  @Autowired
  private UploadFileService uploadFileService;

  @GetMapping(value = "/uploads/{filename:.+}")
  public ResponseEntity<Resource> showPhoto(@PathVariable String filename) {
    Resource recurso = null;
    try {
      recurso = uploadFileService.load(filename);
    } catch (MalformedURLException e) {
      e.printStackTrace();
    }
    return (ResponseEntity<Resource>) ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION,
        "attachment; filename=\"" + recurso.getFilename() + "\"").body(recurso);
  }

  @GetMapping("/list")
  @DatabaseLock
  public String list(Model model, @RequestParam(name = "page", defaultValue = "0") int page) {
    PageRequest pageRequest = PageRequest.of(page, AppConstants.PAGE_SIZE_DEFATULT);
    Page<Client> clients = clientService.findAll(pageRequest);
    PageRender<Client> pageRender = new PageRender<Client>("/clients/list", clients);
    model.addAttribute("title", "Listado de clientes");
    model.addAttribute("clients", clients);
    model.addAttribute("page", pageRender);
    return "list";
  }

  @RequestMapping(value = "/form")
  public String create(Model model) {
    model.addAttribute("title", "Formulario de cliente");
    model.addAttribute("client", new Client());
    return "form";
  }

  @RequestMapping(value = "/form/{id}")
  public String edit(@PathVariable(value = "id") Long id, Model model, RedirectAttributes flash) {
    Client client = null;
    if (id > 0) {
      client = clientService.findById(id);
      if (client == null) {
        flash.addFlashAttribute("error", "El cliente no existe");
        return "redirect:/clients/list";
      }
    } else {
      flash.addFlashAttribute("error", "El id no puede ser menor que cero");
      return "redirect:/clients/list";
    }
    model.addAttribute("title", "Formulario de cliente");
    model.addAttribute("client", client);
    return "form";
  }

  @RequestMapping(value = "/save", method = RequestMethod.POST)
  public String save(@Valid Client client, BindingResult result, Model model, SessionStatus status,
      RedirectAttributes flash, @RequestParam("file") MultipartFile photo) {
    if (result.hasErrors()) {
      model.addAttribute("title", "Formulario de cliente");
      return "form";
    }
    if (!photo.isEmpty()) {
      // usuario existe y tiene una foto (borramos la antigua)
      if (client.getId() != null && client.getPhoto() != null) {
        if (uploadFileService.delete(client.getPhoto())) {
          flash.addFlashAttribute("info", "imagen eliminada: " + client.getPhoto());
        }
      }
      String uniqueFileName;
      try {
        uniqueFileName = uploadFileService.copy(photo);
        client.setPhoto(uniqueFileName);
      } catch (IOException e1) {
        e1.printStackTrace();
      }
    }

    String msgFlash =
        client.getId() == null ? "Cliente creado con éxisto" : "Cliente editado con éxito";

    clientService.save(client);
    flash.addFlashAttribute("success", msgFlash);
    status.setComplete();
    return "redirect:list";
  }

  @RequestMapping(value = "/delete/{id}")
  public String delete(@PathVariable Long id, RedirectAttributes flash) {
    Client client = clientService.findById(id);
    if (client == null) {
      flash.addFlashAttribute("error", "Cliente no existe en la base de datos");
      return "redirect:/clients/list";
    } else {
      clientService.delete(id);
      flash.addFlashAttribute("success", "Cliente eliminado correctamente");
      if (client.getPhoto() != null) {
        if (uploadFileService.delete(client.getPhoto())) {
          flash.addFlashAttribute("info", "imagen eliminada: " + client.getPhoto());
        }
      }
      return "redirect:/clients/list";
    }

  }


  @GetMapping(value = "/detail/{id}")
  public String showPhoto(@PathVariable Long id, Model model, RedirectAttributes flash) {
    Client client = clientService.findById(id);
    if (client == null) {
      flash.addFlashAttribute("error", "Cliente no existe en la base de datos");
      return "redirect:/clients/list";
    }
    model.addAttribute("title", "Ver cliente");
    model.addAttribute("client", client);

    return "detail";
  }


}
