package co.edu.uniandes.csw.carrocompras.master.persistence.entity;

import java.io.Serializable;

/**
 *
 * @author cadmilo
 */
public class CarroComprasItemEntityId implements Serializable{

    private Long carrocomprasId;
    private Long itemId;

    @Override
    public int hashCode() {
        return (int) (carrocomprasId + itemId);
    }

    @Override
    public boolean equals(Object object) {
        if (object instanceof CarroComprasItemEntityId) {
            CarroComprasItemEntityId otherId = (CarroComprasItemEntityId) object;
            return (otherId.carrocomprasId == this.carrocomprasId) && (otherId.itemId == this.itemId);
        }
        return false;
    }

}
