package pe.edu.upn.demo.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "clientes")
public class Cliente {

@Id
@Column(name = "codigo", length = 4, nullable = false)
private String id;

@Column(name = "nombre", length = 30, nullable = false)
private String nombre;

@Column(name = "ape_pat", length = 30, nullable = false)
private String apePat;

@Column(name = "ape_mat", length = 30, nullable = false)
private String apeMat;

@Column(name= "DNI", length = 8, nullable = false)
private String DNI;

@Column(name = "telefono", length = 9, nullable = false)
private String telefono;

@Column(name = "tipo", length = 30, nullable = false)
private String tipo;

@OneToMany(mappedBy = "cliente", fetch = FetchType.LAZY)
private List<Usuario> usuarios;

@OneToMany(mappedBy = "cliente", fetch = FetchType.LAZY)
private List<Pedidos> pedidos;

public Cliente() {
 pedidos = new ArrayList<>();
}

public String getId() {
 return id;
}

public void setId(String id) {
 this.id = id;
}

public String getNombre() {
 return nombre;
}

public void setNombre(String nombre) {
 this.nombre = nombre;
}

public String getApePat() {
 return apePat;
}

public void setApePat(String apePat) {
 this.apePat = apePat;
}

public String getApeMat() {
 return apeMat;
}

public void setApeMat(String apeMat) {
 this.apeMat = apeMat;
}

public String getDNI() {
 return DNI;
}

public void setDNI(String dNI) {
 DNI = dNI;
}

public String getTelefono() {
 return telefono;
}

public void setTelefono(String telefono) {
 this.telefono = telefono;
}

public String getTipo() {
 return tipo;
}

public void setTipo(String tipo) {
 this.tipo = tipo;
}

public List<Pedidos> getPedidos() {
 return pedidos;
}

public void setPedidos(List<Pedidos> pedidos) {
 this.pedidos = pedidos;
}

}