package co.edu.uniandes.csw.carrocompras.master.persistence.entity;

import co.edu.uniandes.csw.item.persistence.entity.ItemEntity;
import co.edu.uniandes.csw.carrocompras.persistence.entity.CarroComprasEntity;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.PrimaryKeyJoinColumn; 
import org.eclipse.persistence.annotations.JoinFetch;

@Entity
@IdClass(CarroComprasItemEntityId.class)
@NamedQueries({
    @NamedQuery(name = "CarroComprasItemEntity.getItemListForCarroCompras", query = "SELECT  u FROM CarroComprasItemEntity u WHERE u.carrocomprasId=:carrocomprasId"),
    @NamedQuery(name = "CarroComprasItemEntity.deleteCarroComprasItem", query = "DELETE FROM CarroComprasItemEntity u WHERE u.itemId=:itemId and  u.carrocomprasId=:carrocomprasId")
})
public class CarroComprasItemEntity implements Serializable {

    @Id
    @Column(name = "carrocomprasId")
    private Long carrocomprasId;
    @Id
    @Column(name = "itemId")
    private Long itemId;
    @ManyToOne
    @PrimaryKeyJoinColumn(name = "itemId", referencedColumnName = "id")
    @JoinFetch
    private ItemEntity itemEntity;
    @ManyToOne
    @PrimaryKeyJoinColumn(name = "carrocomprasId", referencedColumnName = "id")
    @JoinFetch
    private CarroComprasEntity carrocomprasEntity;

    public CarroComprasItemEntity() {
    }

    public CarroComprasItemEntity(Long carrocomprasId, Long itemId) {
        this.carrocomprasId = carrocomprasId;
        this.itemId = itemId;
    }

    public Long getCarroComprasId() {
        return carrocomprasId;
    }

    public void setCarroComprasId(Long carrocomprasId) {
        this.carrocomprasId = carrocomprasId;
    }

    public Long getItemId() {
        return itemId;
    }

    public void setItemId(Long itemId) {
        this.itemId = itemId;
    }

    public ItemEntity getItemEntity() {
        return itemEntity;
    }

    public void setItemEntity(ItemEntity itemEntity) {
        this.itemEntity = itemEntity;
    }

    public CarroComprasEntity getCarroComprasEntity() {
        return carrocomprasEntity;
    }

    public void setCarroComprasEntity(CarroComprasEntity carrocomprasEntity) {
        this.carrocomprasEntity = carrocomprasEntity;
    }

}
