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


package es.vilex.app.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MvcConfig implements WebMvcConfigurer {

  private final Logger log = LoggerFactory.getLogger(getClass());

  // @Override
  // public void addResourceHandlers(ResourceHandlerRegistry registry) {
  // // TODO Auto-generated method stub
  // WebMvcConfigurer.super.addResourceHandlers(registry);
  // String resourcePath = Paths.get("uploads").toAbsolutePath().toUri().toString();
  // log.info(resourcePath);
  // registry.addResourceHandler("/uploads/**").addResourceLocations(resourcePath);
  // }



}
