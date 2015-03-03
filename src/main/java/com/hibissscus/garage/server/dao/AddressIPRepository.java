package com.hibissscus.garage.server.dao;

import com.hibissscus.garage.server.entity.AddressIP;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource(collectionResourceRel = "ips", path = "ips")
public interface AddressIPRepository extends PagingAndSortingRepository<AddressIP, Long> {
    List<AddressIP> findByAddress(String address);

}
