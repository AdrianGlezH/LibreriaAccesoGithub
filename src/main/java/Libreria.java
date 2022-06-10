import org.kohsuke.github.GHRepository;
import org.kohsuke.github.GitHub;
import org.kohsuke.github.GitHubBuilder;

import javax.swing.*;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class Libreria {
    static FileWriter fich;
    static PrintWriter esc;
    static GitHub github = null;
    static GHRepository repo=null;
    public static GitHub Token(String pathFich){
        File file=new File(pathFich);

        if(file.exists()){
            try {
                github = new GitHubBuilder()
                        .fromPropertyFile(pathFich)
                        .build();
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }

        }else{
            try {
                fich=new FileWriter(file , true);
                esc=new PrintWriter(fich);
                String tok=JOptionPane.showInputDialog("Introduce tu token");
                esc.print("oauth="+tok);
                github=new GitHubBuilder()
                        .withOAuthToken(tok)
                        .build();
            } catch (IOException e) {
                System.out.println(e.getMessage());
            } finally {
                esc.close();
            }
        }
        return github;
    }
        public static void crearRepo(){
        try {
            repo = github
                    .createRepository(JOptionPane.showInputDialog("Introduce el nombre de tu repositorio"))
                    .create();
        }catch (IOException e){
            System.out.println(e.getMessage());
        }
    }
}