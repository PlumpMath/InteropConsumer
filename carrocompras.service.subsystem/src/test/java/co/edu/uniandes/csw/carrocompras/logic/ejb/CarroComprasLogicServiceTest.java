
package co.edu.uniandes.csw.carrocompras.logic.ejb;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.UserTransaction;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import java.util.*;


import co.edu.uniandes.csw.carrocompras.logic.dto.CarroComprasDTO;
import co.edu.uniandes.csw.carrocompras.logic.api.ICarroComprasLogicService;
import co.edu.uniandes.csw.carrocompras.persistence.CarroComprasPersistence;
import co.edu.uniandes.csw.carrocompras.persistence.api.ICarroComprasPersistence;
import co.edu.uniandes.csw.carrocompras.persistence.entity.CarroComprasEntity;

@RunWith(Arquillian.class)
public class CarroComprasLogicServiceTest {

	public static final String DEPLOY = "Prueba";

	@Deployment
	public static WebArchive createDeployment() {
		return ShrinkWrap.create(WebArchive.class, DEPLOY + ".war")
				.addPackage(CarroComprasLogicService.class.getPackage())
				.addPackage(CarroComprasPersistence.class.getPackage())
				.addPackage(CarroComprasEntity.class.getPackage())
				.addAsResource("META-INF/persistence.xml", "META-INF/persistence.xml")
                .addAsWebInfResource("META-INF/beans.xml", "beans.xml");
	}

	@Inject
	private ICarroComprasLogicService carroComprasLogicService;
	
	@Inject
	private ICarroComprasPersistence carroComprasPersistence;	

	@Before
	public void configTest() {
		try {
			clearData();
			insertData();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void clearData() {
		List<CarroComprasDTO> dtos=carroComprasPersistence.getCarroComprass();
		for(CarroComprasDTO dto:dtos){
			carroComprasPersistence.deleteCarroCompras(dto.getId());
		}
	}

	private List<CarroComprasDTO> data=new ArrayList<CarroComprasDTO>();

	private void insertData() {
		for(int i=0;i<3;i++){
			CarroComprasDTO pdto=new CarroComprasDTO();
			pdto.setName(generateRandom(String.class));
			pdto.setComprasId(generateRandom(Long.class));
			pdto=carroComprasPersistence.createCarroCompras(pdto);
			data.add(pdto);
		}
	}
	
	@Test
	public void createCarroComprasTest(){
		CarroComprasDTO ldto=new CarroComprasDTO();
		ldto.setName(generateRandom(String.class));
		ldto.setComprasId(generateRandom(Long.class));
		
		
		CarroComprasDTO result=carroComprasLogicService.createCarroCompras(ldto);
		
		Assert.assertNotNull(result);
		
		CarroComprasDTO pdto=carroComprasPersistence.getCarroCompras(result.getId());
		
		Assert.assertEquals(ldto.getName(), pdto.getName());	
		Assert.assertEquals(ldto.getComprasId(), pdto.getComprasId());	
	}
	
	@Test
	public void getCarroComprassTest(){
		List<CarroComprasDTO> list=carroComprasLogicService.getCarroComprass();
		Assert.assertEquals(list.size(), data.size());
        for(CarroComprasDTO ldto:list){
        	boolean found=false;
            for(CarroComprasDTO pdto:data){
            	if(ldto.getId()==pdto.getId()){
                	found=true;
                }
            }
            Assert.assertTrue(found);
        }
	}
	
	@Test
	public void getCarroComprasTest(){
		CarroComprasDTO pdto=data.get(0);
		CarroComprasDTO ldto=carroComprasLogicService.getCarroCompras(pdto.getId());
        Assert.assertNotNull(ldto);
		Assert.assertEquals(pdto.getName(), ldto.getName());
		Assert.assertEquals(pdto.getComprasId(), ldto.getComprasId());
        
	}
	
	@Test
	public void deleteCarroComprasTest(){
		CarroComprasDTO pdto=data.get(0);
		carroComprasLogicService.deleteCarroCompras(pdto.getId());
        CarroComprasDTO deleted=carroComprasPersistence.getCarroCompras(pdto.getId());
        Assert.assertNull(deleted);
	}
	
	@Test
	public void updateCarroComprasTest(){
		CarroComprasDTO pdto=data.get(0);
		
		CarroComprasDTO ldto=new CarroComprasDTO();
		ldto.setId(pdto.getId());
		ldto.setName(generateRandom(String.class));
		ldto.setComprasId(generateRandom(Long.class));
		
		
		carroComprasLogicService.updateCarroCompras(ldto);
		
		
		CarroComprasDTO resp=carroComprasPersistence.getCarroCompras(pdto.getId());
		
		Assert.assertEquals(ldto.getName(), resp.getName());	
		Assert.assertEquals(ldto.getComprasId(), resp.getComprasId());	
	}
	
	public <T> T generateRandom(Class<T> objectClass){
		Random r=new Random();
		if(objectClass.isInstance(String.class)){
			String s="";
			for(int i=0;i<10;i++){
				char c=(char)(r.nextInt()/('Z'-'A')+'A');
				s=s+c;
			}
			return objectClass.cast(s);
		}else if(objectClass.isInstance(Integer.class)){
			Integer s=r.nextInt();
			return objectClass.cast(s);
		}else if(objectClass.isInstance(Long.class)){
			Long s=r.nextLong();
			return objectClass.cast(s);
		}else if(objectClass.isInstance(java.util.Date.class)){
			java.util.Calendar c=java.util.Calendar.getInstance();
			c.set(java.util.Calendar.MONTH, r.nextInt()/12);
			c.set(java.util.Calendar.DAY_OF_MONTH,r.nextInt()/30);
			c.setLenient(false);
			return objectClass.cast(c.getTime());
		} 
		return null;
	}
	
}