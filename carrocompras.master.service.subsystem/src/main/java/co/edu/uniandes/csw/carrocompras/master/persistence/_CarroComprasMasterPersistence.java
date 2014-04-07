package co.edu.uniandes.csw.carrocompras.master.persistence;
import co.edu.uniandes.csw.item.logic.dto.ItemDTO;
import co.edu.uniandes.csw.carrocompras.master.persistence.entity.CarroComprasItemEntity;
import co.edu.uniandes.csw.item.persistence.converter.ItemConverter;
import co.edu.uniandes.csw.carrocompras.logic.dto.CarroComprasDTO;
import co.edu.uniandes.csw.carrocompras.master.logic.dto.CarroComprasMasterDTO;
import co.edu.uniandes.csw.carrocompras.master.persistence.api._ICarroComprasMasterPersistence;
import co.edu.uniandes.csw.carrocompras.persistence.api.ICarroComprasPersistence;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

public class _CarroComprasMasterPersistence implements _ICarroComprasMasterPersistence {

    @PersistenceContext(unitName = "CarroComprasMasterPU")
    protected EntityManager entityManager;
    
    @Inject
    protected ICarroComprasPersistence carrocomprasPersistence;  

    public CarroComprasMasterDTO getCarroCompras(Long carrocomprasId) {
        CarroComprasMasterDTO carrocomprasMasterDTO = new CarroComprasMasterDTO();
        CarroComprasDTO carrocompras = carrocomprasPersistence.getCarroCompras(carrocomprasId);
        carrocomprasMasterDTO.setCarroComprasEntity(carrocompras);
        carrocomprasMasterDTO.setListItem(getItemListForCarroCompras(carrocomprasId));
        return carrocomprasMasterDTO;
    }

    public CarroComprasItemEntity createCarroComprasItem(CarroComprasItemEntity entity) {
        entityManager.persist(entity);
        return entity;
    }

    public void deleteCarroComprasItem(Long carrocomprasId, Long itemId) {
        Query q = entityManager.createNamedQuery("CarroComprasItemEntity.deleteCarroComprasItem");
        q.setParameter("carrocomprasId", carrocomprasId);
        q.setParameter("itemId", itemId);
        q.executeUpdate();
    }

    public List<ItemDTO> getItemListForCarroCompras(Long carrocomprasId) {
        ArrayList<ItemDTO> resp = new ArrayList<ItemDTO>();
        Query q = entityManager.createNamedQuery("CarroComprasItemEntity.getItemListForCarroCompras");
        q.setParameter("carrocomprasId", carrocomprasId);
        List<CarroComprasItemEntity> qResult =  q.getResultList();
        for (CarroComprasItemEntity carrocomprasItemEntity : qResult) { 
            if(carrocomprasItemEntity.getItemEntity()==null){
                entityManager.refresh(carrocomprasItemEntity);
            }
            resp.add(ItemConverter.entity2PersistenceDTO(carrocomprasItemEntity.getItemEntity()));
        }
        return resp;
    }

}
