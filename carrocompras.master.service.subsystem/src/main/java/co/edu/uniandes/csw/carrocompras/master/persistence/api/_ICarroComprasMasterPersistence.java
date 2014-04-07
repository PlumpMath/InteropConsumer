package co.edu.uniandes.csw.carrocompras.master.persistence.api;

import co.edu.uniandes.csw.carrocompras.master.persistence.entity.CarroComprasItemEntity;
import co.edu.uniandes.csw.item.logic.dto.ItemDTO;
import co.edu.uniandes.csw.carrocompras.master.logic.dto.CarroComprasMasterDTO;
import java.util.List;

public interface _ICarroComprasMasterPersistence {

    public CarroComprasItemEntity createCarroComprasItem(CarroComprasItemEntity entity);

    public void deleteCarroComprasItem(Long carrocomprasId, Long itemId);
    
    public List<ItemDTO> getItemListForCarroCompras(Long carrocomprasId);
    
    public CarroComprasMasterDTO getCarroCompras(Long carrocomprasId);

}
