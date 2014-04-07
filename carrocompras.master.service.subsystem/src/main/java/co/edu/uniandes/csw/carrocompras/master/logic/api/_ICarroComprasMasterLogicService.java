 
package co.edu.uniandes.csw.carrocompras.master.logic.api;

import co.edu.uniandes.csw.carrocompras.master.logic.dto.CarroComprasMasterDTO;

public interface _ICarroComprasMasterLogicService {

	public CarroComprasMasterDTO createMasterCarroCompras(CarroComprasMasterDTO detail);
    public void updateMasterCarroCompras(CarroComprasMasterDTO detail);
	public void deleteMasterCarroCompras(Long id); 
	public CarroComprasMasterDTO getMasterCarroCompras(Long id);
        
}