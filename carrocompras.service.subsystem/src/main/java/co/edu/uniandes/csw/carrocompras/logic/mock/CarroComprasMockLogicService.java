
package co.edu.uniandes.csw.carrocompras.logic.mock;
import javax.enterprise.inject.Alternative;
import javax.inject.Singleton;

import co.edu.uniandes.csw.carrocompras.logic.api.ICarroComprasLogicService;

@Alternative
@Singleton
public class CarroComprasMockLogicService extends _CarroComprasMockLogicService implements ICarroComprasLogicService {
	
}