package mx.com.nmp.mscustomerjourney.repository;

import mx.com.nmp.mscustomerjourney.model.catalogo.Application;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ApplicationRepository extends MongoRepository <Application,String> {
}
