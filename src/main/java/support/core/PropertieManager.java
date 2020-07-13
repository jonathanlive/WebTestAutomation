package support.core;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class PropertieManager {

    private static PropertieManager instance;

    private PropertieManager(){

    }

    public static PropertieManager getInstance(){
        if(instance == null)
            instance = new PropertieManager();

        return instance;
    }

    public Properties readProperties(){
        try(FileInputStream fs = new FileInputStream("automation.properties")){
            Properties prop = new Properties();
            prop.load(fs);
            return prop;
        }catch(FileNotFoundException e){
            System.out.println("Não foi possivel localizar o arquivo informado: "+ e.getMessage());
        }
        catch(IOException e){
            System.out.println("Erro ao ler as propriedades do arquivo " + e.getMessage());
        }

        return null;
    }
}
