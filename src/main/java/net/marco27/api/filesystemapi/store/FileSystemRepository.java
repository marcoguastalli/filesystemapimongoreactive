package net.marco27.api.filesystemapi.store;

import net.marco27.api.filesystemapi.domain.FileStructure;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
public interface FileSystemRepository extends ReactiveMongoRepository<FileStructure, String> {

    Mono<FileStructure> findByPath(final String path);

}
