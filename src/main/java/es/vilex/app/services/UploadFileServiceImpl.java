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

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;
import org.slf4j.Logger;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.FileSystemUtils;
import org.springframework.web.multipart.MultipartFile;
import es.vilex.app.config.AppConstants;

@Service
public class UploadFileServiceImpl implements UploadFileService {

  private final Logger log = org.slf4j.LoggerFactory.getLogger(getClass());

  @Override
  public Resource load(String filename) throws MalformedURLException {
    Path pathFoto = getPath(filename);
    log.info("pathFoto: " + pathFoto);
    Resource recurso = new UrlResource(pathFoto.toUri());
    if (!recurso.exists() || !recurso.isReadable()) {
      throw new RuntimeException("Error: no puede cargar la imange " + pathFoto.toString());
    }
    return recurso;
  }

  @Override
  public String copy(MultipartFile file) throws IOException {
    String uniqueFileName = UUID.randomUUID().toString() + "_" + file.getOriginalFilename();
    Path rootPath = getPath(uniqueFileName);
    log.info(String.format("rootPath: %s", rootPath));
    Files.copy(file.getInputStream(), rootPath);
    return uniqueFileName;
  }

  @Override
  public boolean delete(String filename) {
    Path rootPath = getPath(filename);
    File archivo = rootPath.toFile();
    if (archivo.exists() && archivo.canRead()) {
      if (archivo.delete()) {
        return true;
      }
    }
    return false;
  }

  public Path getPath(String filename) {
    return Paths.get(AppConstants.UPLOADS_FOLDER).resolve(filename).toAbsolutePath();
  }

  @Override
  public void deleteAll() {
    FileSystemUtils.deleteRecursively(Paths.get(AppConstants.UPLOADS_FOLDER).toFile());
  }

  @Override
  public void init() throws IOException {
    Files.createDirectories(Paths.get(AppConstants.UPLOADS_FOLDER));
  }

}
