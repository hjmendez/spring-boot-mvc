package es.vilex.app.aspects;



import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;


@Aspect
@Component
public class DatabaseLockAspect {

  @Before("@annotation(DatabaseLock)")
  public void commentService() {
    System.out.println("Hola mundo");
  }

}
