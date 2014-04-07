package co.edu.uniandes.csw.carrocompras.master.logic.ejb;

import co.edu.uniandes.csw.item.logic.dto.ItemDTO;
import co.edu.uniandes.csw.item.persistence.api.IItemPersistence;
import co.edu.uniandes.csw.carrocompras.logic.dto.CarroComprasDTO;
import co.edu.uniandes.csw.carrocompras.master.logic.api._ICarroComprasMasterLogicService;
import co.edu.uniandes.csw.carrocompras.master.logic.dto.CarroComprasMasterDTO;
import co.edu.uniandes.csw.carrocompras.master.persistence.api.ICarroComprasMasterPersistence;
import co.edu.uniandes.csw.carrocompras.master.persistence.entity.CarroComprasItemEntity;
import co.edu.uniandes.csw.carrocompras.persistence.api.ICarroComprasPersistence;
import javax.inject.Inject;

public abstract class _CarroComprasMasterLogicService implements _ICarroComprasMasterLogicService {

    @Inject
    protected ICarroComprasPersistence carrocomprasPersistance;
    @Inject
    protected ICarroComprasMasterPersistence carrocomprasMasterPersistance;
    @Inject
    protected IItemPersistence itemPersistance;

    public CarroComprasMasterDTO createMasterCarroCompras(CarroComprasMasterDTO carrocompras) {
        CarroComprasDTO persistedCarroComprasDTO = carrocomprasPersistance.createCarroCompras(carrocompras.getCarroComprasEntity());
        if (carrocompras.getCreateItem() != null) {
            for (ItemDTO itemDTO : carrocompras.getCreateItem()) {
                ItemDTO persistedItemDTO = itemPersistance.createItem(itemDTO);
                CarroComprasItemEntity carrocomprasItemEntity = new CarroComprasItemEntity(persistedCarroComprasDTO.getId(), persistedItemDTO.getId());
                carrocomprasMasterPersistance.createCarroComprasItem(carrocomprasItemEntity);
            }
        }
        return carrocompras;
    }

    public CarroComprasMasterDTO getMasterCarroCompras(Long id) {
        return carrocomprasMasterPersistance.getCarroCompras(id);
    }

    public void deleteMasterCarroCompras(Long id) {
        carrocomprasPersistance.deleteCarroCompras(id);
    }

    public void updateMasterCarroCompras(CarroComprasMasterDTO carrocompras) {
        carrocomprasPersistance.updateCarroCompras(carrocompras.getCarroComprasEntity());

        //---- FOR RELATIONSHIP
        // persist new item
        if (carrocompras.getCreateItem() != null) {
            for (ItemDTO itemDTO : carrocompras.getCreateItem()) {
                ItemDTO persistedItemDTO = itemPersistance.createItem(itemDTO);
                CarroComprasItemEntity carrocomprasItemEntity = new CarroComprasItemEntity(carrocompras.getCarroComprasEntity().getId(), persistedItemDTO.getId());
                carrocomprasMasterPersistance.createCarroComprasItem(carrocomprasItemEntity);
            }
        }
        // update item
        if (carrocompras.getUpdateItem() != null) {
            for (ItemDTO itemDTO : carrocompras.getUpdateItem()) {
                itemPersistance.updateItem(itemDTO);
            }
        }
        // delete item
        if (carrocompras.getDeleteItem() != null) {
            for (ItemDTO itemDTO : carrocompras.getDeleteItem()) {
                carrocomprasMasterPersistance.deleteCarroComprasItem(carrocompras.getCarroComprasEntity().getId(), itemDTO.getId());
                itemPersistance.deleteItem(itemDTO.getId());
            }
        }
    }
}
