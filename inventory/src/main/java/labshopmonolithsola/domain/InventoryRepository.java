package labshopmonolithsola.domain;

import labshopmonolithsola.domain.*;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

// 여기에서 자동으로 inventories 라는 path 를 생성해서 restAPI 주소를 만들어준다.
@RepositoryRestResource(collectionResourceRel="inventories", path="inventories")
public interface InventoryRepository extends PagingAndSortingRepository<Inventory, Long>{

}
