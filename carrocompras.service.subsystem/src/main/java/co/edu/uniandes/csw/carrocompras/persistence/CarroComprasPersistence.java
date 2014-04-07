
package co.edu.uniandes.csw.carrocompras.persistence;

import javax.ejb.Stateless;
import javax.enterprise.inject.Default;

import co.edu.uniandes.csw.carrocompras.persistence.api.ICarroComprasPersistence;
import javax.ejb.LocalBean;

@Default
@Stateless 
@LocalBean
public class CarroComprasPersistence extends _CarroComprasPersistence  implements ICarroComprasPersistence {

}