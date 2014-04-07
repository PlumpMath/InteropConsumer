
package co.edu.uniandes.csw.carrocompras.logic.ejb;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless; 
import javax.inject.Inject;

import javax.enterprise.inject.Default;

import co.edu.uniandes.csw.carrocompras.logic.api.ICarroComprasLogicService;

@Default
@Stateless
@LocalBean
public class CarroComprasLogicService extends _CarroComprasLogicService implements ICarroComprasLogicService {

}