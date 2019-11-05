package pe.edu.upn.demo.entity;

import java.util.ArrayList;
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
import javax.persistence.Table;

import pe.edu.upn.demo.entity.Authority;
@Entity
@Table(name = "users")
public class Usuario {
@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(length = 30, nullable = false)
    private String username;

    @Column(length = 60, nullable = false)
    private String password;

    private boolean enable;
   
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cod_cliente")
    private Cliente cliente;
   
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cod_personal")
    private Personal personal;
   
    @OneToMany(mappedBy = "usuario", fetch = FetchType.EAGER, cascade = CascadeType.ALL)    
    private List<Authority> authorities;

public Usuario() {
 this.enable = true;
 this.authorities = new ArrayList<>();
}
public Usuario( String username, String password ) {
 this.username = username;
 this.password = password;
 this.enable = true;
 this.authorities = new ArrayList<>();
}

public void addAuthority( String _authority ) {
 Authority authority = new Authority();
 authority.setAuthority( _authority );
 authority.setUsuario(this);
 this.authorities.add(authority);
}
public long getId() {
 return id;
}
public void setId(long id) {
 this.id = id;
}
public String getUsername() {
 return username;
}
public void setUsername(String username) {
 this.username = username;
}
public String getPassword() {
 return password;
}
public void setPassword(String password) {
 this.password = password;
}
public boolean isEnable() {
 return enable;
}
public void setEnable(boolean enable) {
 this.enable = enable;
}
public Cliente getCliente() {
 return cliente;
}
public void setCliente(Cliente cliente) {
 this.cliente = cliente;
}
public Personal getPersonal() {
 return personal;
}
public void setPersonal(Personal personal) {
 this.personal = personal;
}
public List<Authority> getAuthorities() {
 return authorities;
}
public void setAuthorities(List<Authority> authorities) {
 this.authorities = authorities;
}


}
