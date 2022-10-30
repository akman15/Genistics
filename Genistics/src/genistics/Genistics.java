package genistics;
import java.io.File;
import javax.swing.*;  
/**
 * Main class used to initiate the program.
 * Used for initiating the gui window as well as managing the folders.
*/
public class Genistics {
    private static final File GFolder = new File(new JFileChooser().getFileSystemView().getDefaultDirectory().toString()+"\\Genistics");
    public static void main(String[] args) {
        /**
         * Creates a new MainMenu window and configures it to be maximized and visible.
         */
        JFrame m = new MainMenu();
        initFolders();
        m.setVisible(true);
        m.setExtendedState(m.getExtendedState() | JFrame.MAXIMIZED_BOTH);
    }
    private static void initFolders(){
        /**
         * Checks if a "Genistics" folder already exists in the Documents folder,if not it creates it.
         */
        if(!GFolder.exists()){
            boolean file = GFolder.mkdir();
            if(file){
                System.out.println("Folder created");
            }else{
                System.out.println("Unknown error");
            }
        }
    }
    public static File GetGFolder(){//Returns the folder's File Object.
        return GFolder;
    }
}
