package co.edu.uniandes.csw.carrocompras.service;

import java.util.List;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.inject.Inject;

import co.edu.uniandes.csw.carrocompras.logic.api.ICarroComprasLogicService;
import co.edu.uniandes.csw.carrocompras.logic.dto.CarroComprasDTO;


public abstract class _CarroComprasService {

	@Inject
	protected ICarroComprasLogicService carroComprasLogicService;
	
	@POST
	public CarroComprasDTO createCarroCompras(CarroComprasDTO carroCompras){
		return carroComprasLogicService.createCarroCompras(carroCompras);
	}
	
	@DELETE
	@Path("{id}")
	public void deleteCarroCompras(@PathParam("id") Long id){
		carroComprasLogicService.deleteCarroCompras(id);
	}
	
	@GET
	public List<CarroComprasDTO> getCarroComprass(){
		return carroComprasLogicService.getCarroComprass();
	}
	
	@GET
	@Path("{id}")
	public CarroComprasDTO getCarroCompras(@PathParam("id") Long id){
		return carroComprasLogicService.getCarroCompras(id);
	}
	
	@PUT
    @Path("{id}")
	public void updateCarroCompras(@PathParam("id") Long id, CarroComprasDTO carroCompras){
		carroComprasLogicService.updateCarroCompras(carroCompras);
	}
	
}