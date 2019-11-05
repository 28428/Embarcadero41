package pe.edu.upn.demo.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "pedidos")
public class Pedidos {
	@Id
	@Column(name = "codigo", length = 4)
	private String codigo_pedido;
	
	@Column(name = "estado", length = 30, nullable = false )
	private String estado;
	
	@ManyToMany
	@JoinTable(name = "platos_pedidos", 
	joinColumns = @JoinColumn(columnDefinition = "codigo_pedido"),
	inverseJoinColumns = @JoinColumn(columnDefinition = "cod_platos"))
	private List<Platos> platos;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "cod_cliente")
	private Cliente cliente;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "cod_personal")
	private Personal personal;

	public Pedidos() 
	{
		
		platos=new ArrayList<>();
	}
	
	public String getCodigo_pedido() {
		return codigo_pedido;
	}

	public void setCodigo_pedido(String codigo_pedido) {
		this.codigo_pedido = codigo_pedido;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public List<Platos> getPlatos() {
		return platos;
	}

	public void setPlatos(List<Platos> platos) {
		this.platos = platos;
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
	
	
}