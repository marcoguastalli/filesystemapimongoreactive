package net.marco27.api.filesystemapi.controller;

import net.marco27.api.base.domain.JsonError;
import net.marco27.api.base.domain.JsonSuccess;
import net.marco27.api.filesystemapi.domain.FileStructure;
import net.marco27.api.filesystemapi.domain.PathFileToPrint;
import net.marco27.api.filesystemapi.service.FileSystemApiService;
import net.marco27.api.filesystemapi.store.FileSystemRepository;
import net.marco27.api.filesystemapi.validation.model.ValidationResult;
import net.marco27.api.filesystemapi.validation.service.ValidationService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import javax.validation.Valid;

import static net.marco27.api.base.ApiConstants.SLASH;
import static org.springframework.http.HttpStatus.CREATED;

/**
 * The main use case for the API is to read the filesystem
 */
@RestController
@RequestMapping
public class FileSystemApiController {

    @Autowired
    private FileSystemApiService fileSystemApiService;
    @Autowired
    private ValidationService validationService;
    @Autowired
    private FileSystemRepository fileSystemRepository;

    @GetMapping("/printPathToFile/{pathToPrint}/{fileToPrint}")
    public ResponseEntity<PathFileToPrint> printPathToFile(@Valid @PathVariable final String pathToPrint,
                                                           @Valid @PathVariable final String fileToPrint) {
        final PathFileToPrint pathFileToPrint = new PathFileToPrint.Builder(pathToPrint, fileToPrint).build();
        final ValidationResult validationResult = validationService.validateInput(pathFileToPrint);
        if (validationResult.isValid()) {
            final PathFileToPrint result = fileSystemApiService.printPathToFile(pathFileToPrint.getPathToPrint(),
                    pathFileToPrint.getFileToPrint());
            return new ResponseEntity<>(result, CREATED);
        } else {
            return new ResponseEntity(new JsonError().addErrorMessage(validationResult.getErrorMessage()),
                    validationResult.getHttpStatus());
        }
    }

    /**
     * The input path parameter from a request cannot start with SLASH, but absolute paths are used
     *
     * @param path to validate
     * @return a valid path
     */
    @Valid
    private String validatePath(@PathVariable @Valid final String path) {
        return StringUtils.startsWith(path, SLASH) ? path : SLASH.concat(path);
    }

    @GetMapping("/findFileStructureMongoById/{path}")
    public Mono<FileStructure> findFileStructureMongoById(@Valid @PathVariable final String path) {
        return fileSystemRepository.findById(validatePath(path));
    }

    @GetMapping("/findFileStructureMongoByPath/{path}")
    public Mono<FileStructure> findFileStructureMongoByPath(@Valid @PathVariable final String path) {
        return fileSystemRepository.findByPath(validatePath(path));
    }

    @PostMapping("/saveFileStructureMongo/{path}")
    public Mono<FileStructure> saveFileStructureMongo(@Valid @PathVariable final String path) {
        final String validPath = validatePath(path);
        final FileStructure fileStructure = fileSystemApiService.createFileStructure(validPath);
        if (fileStructure != null) {
            return fileSystemRepository.save(fileStructure);
        }
        return null;
    }

    @DeleteMapping("/deleteFileStructureMongo/{path}")
    public ResponseEntity<JsonSuccess> deleteFileStructureMongo(@Valid @PathVariable final String path) {
        final String validatedPath = validatePath(path);
        Mono<FileStructure> fileStructureMono = fileSystemRepository.findById(validatedPath);
        fileStructureMono.then(fileSystemRepository.delete(fileStructureMono.block()).then(fileStructureMono));
        return ResponseEntity.ok(new JsonSuccess());
    }
}
