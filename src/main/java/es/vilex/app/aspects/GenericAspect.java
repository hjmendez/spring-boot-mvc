package es.vilex.app.aspects;



import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;


@Aspect
@Component
public class GenericAspect {

  @Pointcut("execution(* * (..))")
  public void allMethods() {
    System.out.println("consejo de todos los metodos y paquetes");
  }

  // @Before("allMethodsPackage()")
  // public void adviceA(JoinPoint jp) {
  // System.out.println(
  // "=== Consejo antes de ejecutar metodos de un paquete:" + jp.getSignature().getName());
  // }


  @Before("execution(* list*(..))")
  public void adviceA(JoinPoint jp) {
    System.out.println("=== Consejo antes de ejecutar metodos que empiezan por list:"
        + jp.getSignature().getName());
  }

  // @Before("args(arg)")
  // public void adviceB(JoinPoint jp, String arg) {
  // System.out.println("=== Consejo antes de ejecutar metodos con un argumento de tipo String:"
  // + jp.getSignature().getName() + "(arg):" + arg);
  // }

  // @AfterReturning(pointcut = "allMethods()", returning = "result")
  // public void adviceC(JoinPoint jp, int result) {
  // System.out.println("=== Consejo después de ejecutar métodos que devuelve un int:"
  // + jp.getSignature().getName() + "(result):" + result);
  // }

  // @AfterReturning(pointcut = "allMethods()", returning = "result")
  // public void adviceF(JoinPoint jp, int result) {
  // System.out.println("=== Consejo Despues de ejecutar metodos que devuelven un int: "
  // + jp.getSignature().getName() + " return:" + result);
  // }



}
