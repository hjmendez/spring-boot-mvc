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


package es.vilex.app.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "facturas")
public class Factura implements Serializable {
  private static final long serialVersionUID = -3910432721877372711L;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private String descripcion;
  private String observacion;
  @Temporal(TemporalType.DATE)
  @Column(name = "create_at")
  private Date createAt;

  @ManyToOne(fetch = FetchType.LAZY)
  private Client cliente;

  @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
  @JoinColumn(name = "factura_id")
  private List<ItemFactura> items = new ArrayList<ItemFactura>();

  @PrePersist
  public void prePersist() {
    createAt = new Date();
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getDescripcion() {
    return descripcion;
  }

  public void setDescripcion(String descripcion) {
    this.descripcion = descripcion;
  }

  public String getObservacion() {
    return observacion;
  }

  public void setObservacion(String observacion) {
    this.observacion = observacion;
  }

  public Date getCreateAt() {
    return createAt;
  }

  public void setCreateAt(Date createAt) {
    this.createAt = createAt;
  }

  public Client getCliente() {
    return cliente;
  }

  public void setCliente(Client cliente) {
    this.cliente = cliente;
  }

  public List<ItemFactura> getItems() {
    return items;
  }

  public void setItems(List<ItemFactura> items) {
    this.items = items;
  }

  public void addItemFactura(ItemFactura itemFactura) {
    this.items.add(itemFactura);
  }

  public Double getTotal() {
    Double total = 0.0;
    for (ItemFactura itemFactura : items) {
      total = total + itemFactura.calcularImporte();
    }
    return total;
  }



}
