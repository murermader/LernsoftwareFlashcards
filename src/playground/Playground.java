package playground;

public class Playground {
    public static void main(String[] args) {
        String os = System.getProperty("os.name").toLowerCase();
        if (os.contains("win")){
            System.out.println("Windows");
            //Betriebssystem ist Windows-basiert
        }
        else if (os.contains("osx")){
            System.out.println("Apple OSX");
            //Betriebssystem ist Apple OSX
        }
        else if (os.contains("nix") || os.contains("aix") || os.contains("nux")){
            System.out.println("Linux/Unix");
            //Betriebssystem ist Linux/Unix basiert
        }
    }
}
