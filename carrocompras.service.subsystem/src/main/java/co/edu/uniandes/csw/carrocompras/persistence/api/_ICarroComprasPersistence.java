
package co.edu.uniandes.csw.carrocompras.persistence.api;

import java.util.List; 

import co.edu.uniandes.csw.carrocompras.logic.dto.CarroComprasDTO;

public interface _ICarroComprasPersistence {

	public CarroComprasDTO createCarroCompras(CarroComprasDTO detail);
	public List<CarroComprasDTO> getCarroComprass();
	public CarroComprasDTO getCarroCompras(Long id);
	public void deleteCarroCompras(Long id);
	public void updateCarroCompras(CarroComprasDTO detail);
	
}