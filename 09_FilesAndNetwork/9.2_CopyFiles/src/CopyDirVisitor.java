import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;


public class CopyDirVisitor extends SimpleFileVisitor<Path> {
    private final Path fromPath;
    private final Path toPath;
    private final CopyOption copyOption;

    public CopyDirVisitor(Path fromPath, Path toPath, CopyOption copyOption) {
        this.fromPath = fromPath;
        this.toPath = toPath;
        this.copyOption = copyOption;
    }

    @Override
    public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
        Path newDir = toPath.resolve(fromPath.relativize(dir));
        if (!Files.exists(newDir)) {
            Files.createDirectory(newDir);
        }
        return FileVisitResult.CONTINUE;
    }

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
        Files.copy(file, toPath.resolve(fromPath.relativize(file)), copyOption);
        return FileVisitResult.CONTINUE;
    }

}
