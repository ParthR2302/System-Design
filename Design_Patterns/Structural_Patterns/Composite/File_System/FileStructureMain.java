package Design_Patterns.Structural_Patterns.Composite.File_System;

import java.util.ArrayList;
import java.util.List;

interface FileSystemItem {
    public void ls(int indent);
    public int getSize();
    public void openAll(int indent);
    public String getName();
    public boolean isFolder();
    public FileSystemItem cd(String name);
}

class File implements FileSystemItem {

    private String name;
    private int size;

    public File(String name, int size) {
        this.name = name;
        this.size = size;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public int getSize() {
        return size;
    }

    @Override
    public void openAll(int indent) {
        String indentSpaces = " ".repeat(indent);
        System.out.println(indentSpaces + name);
    }

    @Override
    public void ls(int indent) {
        String indentSpaces = " ".repeat(indent);
        System.out.println(indentSpaces + name);
    }

    @Override
    public boolean isFolder() {
        return false;
    }

    @Override
    public FileSystemItem cd(String name) {
        return null;
    }
}

class Folder implements FileSystemItem {

    private String name;
    List<FileSystemItem> children;

    public Folder(String name) {
        this.name = name;
        children = new ArrayList<>();
    }

    public void add(FileSystemItem fileSystemItem) {
        children.add(fileSystemItem);
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public int getSize() {
        // Calculate size of the children files and add them
        int size = 0;
        for(FileSystemItem child : children) {
            size += child.getSize();
        }

        return size;
    }

    @Override
    public void openAll(int indent) {
        String indentSpace = " ".repeat(indent);
        System.out.println(indentSpace + "+ " + name);
        for(FileSystemItem child : children) {
            child.openAll(indent + 4);
        }
    }

    @Override
    public void ls(int indent) {
        String indentSpace = " ".repeat(indent);
        System.out.println(indentSpace + "+ " + name);
    }

    @Override
    public boolean isFolder() {
        return true;
    }

    @Override
    public FileSystemItem cd(String name) {
        for(FileSystemItem child : children) {
            if(child.isFolder() && child.getName().equals(name)) 
                return child;
        }

        return null;
    }

}

public class FileStructureMain {
    public static void main(String[] args) {
        Folder root = new Folder("root");
        root.add(new File("file1.txt", 1));
        root.add(new File("file2.txt", 1));

        Folder docs = new Folder("docs");
        docs.add(new File("resume.pdf", 4));
        docs.add(new File("notes.txt", 2));
        root.add(docs);

        Folder images = new Folder("images");
        images.add(new File("photo.jpg", 1));
        root.add(images);

        root.ls(0);

        docs.ls(0);

        root.openAll(0);

        FileSystemItem cwd = root.cd("docs");
        if (cwd != null) {
            cwd.ls(0);
        } else {
            System.out.println("\nCould not cd into docs\n");
        }

        System.out.println(root.getSize());
    }
}
