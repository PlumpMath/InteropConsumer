
package co.edu.uniandes.csw.carrocompras.logic.dto;

import java.util.Date;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement 
public abstract class _CarroComprasDTO {

	private Long id;
	private String name;
	private Long comprasId;

	public Long getId() {
		return id;
	}
 
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
 
	public void setName(String name) {
		this.name = name;
	}
	public Long getComprasId() {
		return comprasId;
	}
 
	public void setComprasId(Long comprasid) {
		this.comprasId = comprasid;
	}
	
}