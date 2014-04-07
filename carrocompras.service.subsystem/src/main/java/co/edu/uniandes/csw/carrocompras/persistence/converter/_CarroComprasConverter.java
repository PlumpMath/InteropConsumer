
package co.edu.uniandes.csw.carrocompras.persistence.converter;

import java.util.ArrayList;
import java.util.List;

import co.edu.uniandes.csw.carrocompras.logic.dto.CarroComprasDTO;
import co.edu.uniandes.csw.carrocompras.persistence.entity.CarroComprasEntity;

public abstract class _CarroComprasConverter {


	public static CarroComprasDTO entity2PersistenceDTO(CarroComprasEntity entity){
		if (entity != null) {
			CarroComprasDTO dto = new CarroComprasDTO();
				dto.setId(entity.getId());
				dto.setName(entity.getName());
				dto.setClienteId(entity.getClienteId());
			return dto;
		}else{
			return null;
		}
	}
	
	public static CarroComprasEntity persistenceDTO2Entity(CarroComprasDTO dto){
		if(dto!=null){
			CarroComprasEntity entity=new CarroComprasEntity();
			entity.setId(dto.getId());
			entity.setName(dto.getName());
			entity.setClienteId(dto.getClienteId());
			return entity;
		}else {
			return null;
		}
	}
	
	public static List<CarroComprasDTO> entity2PersistenceDTOList(List<CarroComprasEntity> entities){
		List<CarroComprasDTO> dtos=new ArrayList<CarroComprasDTO>();
		for(CarroComprasEntity entity:entities){
			dtos.add(entity2PersistenceDTO(entity));
		}
		return dtos;
	}
	
	public static List<CarroComprasEntity> persistenceDTO2EntityList(List<CarroComprasDTO> dtos){
		List<CarroComprasEntity> entities=new ArrayList<CarroComprasEntity>();
		for(CarroComprasDTO dto:dtos){
			entities.add(persistenceDTO2Entity(dto));
		}
		return entities;
	}
}