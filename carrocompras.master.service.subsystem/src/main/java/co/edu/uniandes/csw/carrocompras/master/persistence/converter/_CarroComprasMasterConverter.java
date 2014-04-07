package co.edu.uniandes.csw.carrocompras.master.persistence.converter;
import co.edu.uniandes.csw.carrocompras.master.persistence.entity.CarroComprasItemEntity;
import co.edu.uniandes.csw.item.logic.dto.ItemDTO;
import co.edu.uniandes.csw.item.persistence.converter.ItemConverter;
import co.edu.uniandes.csw.carrocompras.logic.dto.CarroComprasDTO;
import co.edu.uniandes.csw.carrocompras.master.logic.dto.CarroComprasMasterDTO;
import co.edu.uniandes.csw.carrocompras.persistence.converter.CarroComprasConverter;
import co.edu.uniandes.csw.carrocompras.persistence.entity.CarroComprasEntity;
import java.util.ArrayList;
import java.util.List;

public abstract class _CarroComprasMasterConverter {

    public static CarroComprasMasterDTO entity2PersistenceDTO(CarroComprasEntity carrocomprasEntity 
    ,List<CarroComprasItemEntity> carrocomprasItemEntity 
    ) {
        CarroComprasDTO carrocomprasDTO = CarroComprasConverter.entity2PersistenceDTO(carrocomprasEntity);
        ArrayList<ItemDTO> itemEntities = new ArrayList<ItemDTO>(carrocomprasItemEntity.size());
        for (CarroComprasItemEntity carrocomprasItem : carrocomprasItemEntity) {
            itemEntities.add(ItemConverter.entity2PersistenceDTO(carrocomprasItem.getItemEntity()));
        }
        CarroComprasMasterDTO respDTO  = new CarroComprasMasterDTO();
        respDTO.setCarroComprasEntity(carrocomprasDTO);
        respDTO.setListItem(itemEntities);
        return respDTO;
    }

}