
package co.edu.uniandes.csw.carrocompras.logic.mock;
import java.util.ArrayList;
import java.util.List;

import co.edu.uniandes.csw.carrocompras.logic.dto.CarroComprasDTO;
import co.edu.uniandes.csw.carrocompras.logic.api._ICarroComprasLogicService;

public abstract class _CarroComprasMockLogicService implements _ICarroComprasLogicService {

	private Long id= new Long(1);
	protected List<CarroComprasDTO> data=new ArrayList<CarroComprasDTO>();

	public CarroComprasDTO createCarroCompras(CarroComprasDTO carroCompras){
		id++;
		carroCompras.setId(id);
		return carroCompras;
    }

	public List<CarroComprasDTO> getCarroComprass(){
		return data; 
	}

	public CarroComprasDTO getCarroCompras(Long id){
		for(CarroComprasDTO data1:data){
			if(data1.getId().equals(id)){
				return data1;
			}
		}
		return null;
	}

	public void deleteCarroCompras(Long id){
	    CarroComprasDTO delete=null;
		for(CarroComprasDTO data1:data){
			if(data1.getId().equals(id)){
				delete=data1;
			}
		}
		if(delete!=null){
			data.remove(delete);
		} 
	}

	public void updateCarroCompras(CarroComprasDTO carroCompras){
	    CarroComprasDTO delete=null;
		for(CarroComprasDTO data1:data){
			if(data1.getId().equals(id)){
				delete=data1;
			}
		}
		if(delete!=null){
			data.remove(delete);
			data.add(carroCompras);
		} 
	}	
}