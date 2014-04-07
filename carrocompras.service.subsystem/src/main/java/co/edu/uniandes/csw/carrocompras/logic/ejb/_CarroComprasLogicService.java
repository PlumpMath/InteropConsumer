
package co.edu.uniandes.csw.carrocompras.logic.ejb;
import java.util.List;
import javax.inject.Inject;

import co.edu.uniandes.csw.carrocompras.logic.dto.CarroComprasDTO;
import co.edu.uniandes.csw.carrocompras.logic.api._ICarroComprasLogicService;
import co.edu.uniandes.csw.carrocompras.persistence.api.ICarroComprasPersistence;

public abstract class _CarroComprasLogicService implements _ICarroComprasLogicService {

	@Inject
	protected ICarroComprasPersistence persistance;

	public CarroComprasDTO createCarroCompras(CarroComprasDTO carroCompras){
		return persistance.createCarroCompras( carroCompras); 
    }

	public List<CarroComprasDTO> getCarroComprass(){
		return persistance.getCarroComprass(); 
	}

	public CarroComprasDTO getCarroCompras(Long id){
		return persistance.getCarroCompras(id); 
	}

	public void deleteCarroCompras(Long id){
	    persistance.deleteCarroCompras(id); 
	}

	public void updateCarroCompras(CarroComprasDTO carroCompras){
	    persistance.updateCarroCompras(carroCompras); 
	}	
}