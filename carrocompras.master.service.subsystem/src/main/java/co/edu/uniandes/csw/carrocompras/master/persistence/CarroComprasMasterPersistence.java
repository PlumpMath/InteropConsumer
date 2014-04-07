package co.edu.uniandes.csw.carrocompras.master.persistence;

import javax.ejb.Stateless;

import co.edu.uniandes.csw.carrocompras.master.persistence.api.ICarroComprasMasterPersistence;
import javax.ejb.LocalBean;
import javax.enterprise.inject.Default;

@Default
@Stateless 
@LocalBean
public class CarroComprasMasterPersistence extends _CarroComprasMasterPersistence  implements ICarroComprasMasterPersistence {

}