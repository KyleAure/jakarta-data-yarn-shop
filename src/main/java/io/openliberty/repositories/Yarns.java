package io.openliberty.repositories;

import java.util.List;

import io.openliberty.entities.Yarn;
import jakarta.data.Limit;
import jakarta.data.Sort;
import jakarta.data.repository.BasicRepository;
import jakarta.data.repository.Query;
import jakarta.data.repository.Repository;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

@Repository
public interface Yarns extends BasicRepository<Yarn, Long> {

	@Size(max = 3)
	Yarn[] findFirst3ByStockGreaterThanEqualAndBrandStartsWith(
			@Positive int minStock, 
			@NotBlank String brandPrefix, 
			@NotNull Sort<Yarn> sort);
	
	@Query("where stock >= :minStock and brand like :brandPrefix")
	List<@Valid Yarn> findExcessiveStockByBrand(
			@Positive int minStock, 
			@NotBlank String brandPrefix, 
			@NotNull Limit limit);

}
