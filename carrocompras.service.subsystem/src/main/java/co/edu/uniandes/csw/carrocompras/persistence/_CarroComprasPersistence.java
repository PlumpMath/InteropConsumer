
package co.edu.uniandes.csw.carrocompras.persistence;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import co.edu.uniandes.csw.carrocompras.logic.dto.CarroComprasDTO;
import co.edu.uniandes.csw.carrocompras.persistence.api._ICarroComprasPersistence;
import co.edu.uniandes.csw.carrocompras.persistence.converter.CarroComprasConverter;
import co.edu.uniandes.csw.carrocompras.persistence.entity.CarroComprasEntity;

public abstract class _CarroComprasPersistence implements _ICarroComprasPersistence {

	@PersistenceContext(unitName="CarroComprasPU")
	protected EntityManager entityManager;
	
	public CarroComprasDTO createCarroCompras(CarroComprasDTO carroCompras) {
		CarroComprasEntity entity=CarroComprasConverter.persistenceDTO2Entity(carroCompras);
		entityManager.persist(entity);
		return CarroComprasConverter.entity2PersistenceDTO(entity);
	}

	@SuppressWarnings("unchecked")
	public List<CarroComprasDTO> getCarroComprass() {
		Query q = entityManager.createQuery("select u from CarroComprasEntity u");
		return CarroComprasConverter.entity2PersistenceDTOList(q.getResultList());
	}

	public CarroComprasDTO getCarroCompras(Long id) {
		return CarroComprasConverter.entity2PersistenceDTO(entityManager.find(CarroComprasEntity.class, id));
	}

	public void deleteCarroCompras(Long id) {
		CarroComprasEntity entity=entityManager.find(CarroComprasEntity.class, id);
		entityManager.remove(entity);
	}

	public void updateCarroCompras(CarroComprasDTO detail) {
		CarroComprasEntity entity=entityManager.merge(CarroComprasConverter.persistenceDTO2Entity(detail));
		CarroComprasConverter.entity2PersistenceDTO(entity);
	}

}