package pe.edu.upn.demo.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "platos")
public class Platos {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "codigo_plato", length = 4)
	private Integer codigo_plato;
	
	@Column(name = "descripcion", length = 30, nullable = false )
	private String descripcion;
	
	@Column(name = "precio", nullable = false )
	private double precio;
	
	@ManyToMany(mappedBy = "platos")
	private List<Pedidos> pedidos;
	
	
	public Platos() 
	{
		
		pedidos=new ArrayList<>();
	}

	public Integer getCodigo_plato() {
		return codigo_plato;
	}

	public void setCodigo_plato(Integer codigo_plato) {
		this.codigo_plato = codigo_plato;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public double getPrecio() {
		return precio;
	}

	public void setPrecio(double precio) {
		this.precio = precio;
	}

	public List<Pedidos> getPedidos() {
		return pedidos;
	}

	public void setPedidos(List<Pedidos> pedidos) {
		this.pedidos = pedidos;
	}
	
	
}
