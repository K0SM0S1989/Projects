import java.nio.file.*;
public class Main {

    public static void main(String[] args) {
        try {
            Path catalogCopy = Paths.get(args[0]);
            Path catalogCopyPlace = Paths.get(args[1]);
            Files.walkFileTree(catalogCopy,
                    new CopyDirVisitor(catalogCopy, catalogCopyPlace, StandardCopyOption.REPLACE_EXISTING));
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
