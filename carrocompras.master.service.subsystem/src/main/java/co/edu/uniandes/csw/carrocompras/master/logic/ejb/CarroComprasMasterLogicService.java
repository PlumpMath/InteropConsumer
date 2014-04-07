package co.edu.uniandes.csw.carrocompras.master.logic.ejb;

import co.edu.uniandes.csw.carrocompras.master.logic.api.ICarroComprasMasterLogicService;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.enterprise.inject.Default;

@Default
@Stateless
@LocalBean
public class CarroComprasMasterLogicService extends _CarroComprasMasterLogicService implements ICarroComprasMasterLogicService {

}