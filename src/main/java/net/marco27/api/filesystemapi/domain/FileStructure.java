package net.marco27.api.filesystemapi.domain;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "FileStructure")
public final class FileStructure implements Serializable {

    @Id
    private String path;
    private String name;
    private String ext;
    private String timestamp;
    private boolean isDirectory;
    private List<FileStructure> children;

    public FileStructure(String path, String name, String ext, String timestamp, boolean isDirectory, List<FileStructure> children) {
        this.path = path;
        this.name = name;
        this.ext = ext;
        this.timestamp = timestamp;
        this.isDirectory = isDirectory;
        this.children = children;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getExt() {
        return ext;
    }

    public void setExt(String ext) {
        this.ext = ext;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public boolean isDirectory() {
        return isDirectory;
    }

    public void setDirectory(boolean directory) {
        isDirectory = directory;
    }

    public List<FileStructure> getChildren() {
        return children;
    }

    public void setChildren(List<FileStructure> children) {
        this.children = children;
    }
}
