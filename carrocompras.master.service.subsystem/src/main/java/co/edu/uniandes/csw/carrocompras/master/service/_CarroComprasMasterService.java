package co.edu.uniandes.csw.carrocompras.master.service;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.inject.Inject;

import co.edu.uniandes.csw.carrocompras.master.logic.api.ICarroComprasMasterLogicService;
import co.edu.uniandes.csw.carrocompras.master.logic.dto.CarroComprasMasterDTO;

public abstract class _CarroComprasMasterService {

    @Inject
    protected ICarroComprasMasterLogicService carrocomprasLogicService;

    @POST
    public CarroComprasMasterDTO createCarroCompras(CarroComprasMasterDTO carrocompras) {
        return carrocomprasLogicService.createMasterCarroCompras(carrocompras);
    }

    @DELETE
    @Path("{id}")
    public void deleteCarroCompras(@PathParam("id") Long id) {
        carrocomprasLogicService.deleteMasterCarroCompras(id);
    }
    
    @GET
    @Path("{id}")
    public CarroComprasMasterDTO getCarroCompras(@PathParam("id") Long id) {
        return carrocomprasLogicService.getMasterCarroCompras(id);
    }

    @PUT
    @Path("{id}")
    public void updateCarroCompras(@PathParam("id") Long id, CarroComprasMasterDTO carrocompras) {
        carrocomprasLogicService.updateMasterCarroCompras(carrocompras);
    }

}
