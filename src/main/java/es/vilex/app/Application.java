package es.vilex.app;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import es.vilex.app.services.UploadFileService;

@SpringBootApplication
public class Application implements CommandLineRunner {

  @Autowired
  UploadFileService uploadFileService;

  public static void main(String[] args) {
    SpringApplication.run(Application.class);
  }

  @Override
  public void run(String... args) throws Exception {
    uploadFileService.deleteAll();
    uploadFileService.init();
  }


}
