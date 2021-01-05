package mx.com.nmp.mscustomerjourney.repository;

import mx.com.nmp.mscustomerjourney.model.catalogo.Errores;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ErroresRepository extends MongoRepository<Errores, String> {

}
