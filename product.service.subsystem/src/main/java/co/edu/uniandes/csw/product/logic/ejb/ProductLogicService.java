package co.edu.uniandes.csw.product.logic.ejb;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.inject.Inject;

import javax.enterprise.inject.Default;

import co.edu.uniandes.csw.product.logic.api.IProductLogicService;
import co.edu.uniandes.csw.product.logic.dto.ProductDTO;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.core.MediaType;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.type.TypeFactory;

@Default
@Stateless
@LocalBean
public class ProductLogicService extends _ProductLogicService implements IProductLogicService {
//Se reimplementa el método 'getProduct' para que consulte en el servicio remoto en vez de la persistencia.
    public ProductDTO getProduct(Long id) {
        try {
            //Se crea un cliente apache
            Client client = Client.create();
            
            //SE CONSUME EL SERVICIO REMOTO DE PRODUCTOS getProducto
            WebResource webResource = client.resource("http://localhost:8080/producto.service.subsystem.web/webresources/Producto/" + id);
            // Se instancia un nuevo object mapper para convertir el string JSON a un DTO
            ObjectMapper map = new ObjectMapper();
            //Se obtiene el string JSON por medio de get
            String resp = webResource.type(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON).get(String.class);
            //Se convierte el String a un DTO con ayuda de jackson y luego se retorna
            ProductDTO dto = map.readValue(resp, ProductDTO.class);
            return dto;
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return null;
    }
//Se reimplementa el método 'getProducts' de _productLogicService para que consulte la lista de productos en el servicio de la otra aplicación.
    public List<ProductDTO> getProducts() {
        try {
            Client client = Client.create();
            //SE CONSUME EL SERVICIO REMOTO DE PRODUCTOS getProductos
            WebResource webResource = client.resource("http://localhost:8080/producto.service.subsystem.web/webresources/Producto");
            // Se instancia un nuevo object mapper para convertir el string JSON a un DTO
            ObjectMapper map = new ObjectMapper();
            //Se convierte el String a un DTO y luego se retorna
            String resp = webResource.type(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON).get(String.class);
            //Se convierte a una lista de DTO con ayuda de jackson y se retrona.
            List<ProductDTO> dto = map.readValue(resp, TypeFactory.defaultInstance().constructCollectionType(List.class, ProductDTO.class));
            return dto;
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return null;
    }
}
