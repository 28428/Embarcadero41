package pe.edu.upn.demo.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "personal")
public class Personal {

@Id
@Column(name = "codigo", length = 4)
private String codigo;

@Column(name = "nombre", length = 25)
private String nombre;

@Column(name = "ape_pat", length = 25)
private String apePat;

@Column(name = "ape_mat", length = 25)
private String apeMat;

@Column(name = "dni", length = 8)
private String dni;

@Column(name = "telefono", length =7)
private String telefono;

@Column(name = "tipo", length = 60)
private String tipo;

@OneToMany(mappedBy = "personal", fetch = FetchType.LAZY)
private List<Usuario> usuarios;

@OneToMany(mappedBy = "personal" , fetch = FetchType.LAZY)
private List<Pedidos> pedidos;

public Personal() {
 pedidos= new ArrayList<>();
 
}

public void addPersonal(Personal personal) {
 
}

public String getCodigo() {
 return codigo;
}

public void setCodigo(String codigo) {
 this.codigo = codigo;
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

public String getDni() {
 return dni;
}

public void setDni(String dni) {
 this.dni = dni;
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