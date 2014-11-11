package main.helper;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;

import android.content.Context;
import android.os.Environment;
import android.widget.Toast;


/**
 * Ejemplo de uso:
 * 
 * IOHelper ioh = new IOHelper(this);
 * File file = ioh.createLogFile("logs", "log");
 * ioh.saveToFile(file, "linea 1", true);
 * ioh.saveToFile(file, "linea 2", true);
 * ioh.saveToFile(file, "linea 3", true);
 * String texto = ioh.readFile(file);
 * Toast.makeText(getBaseContext(), texto, Toast.LENGTH_LONG).show();
 */


public class IOHelper
{
    private Context _context;

    public IOHelper(Context context)
    {
        this._context = context;
    }

    public File createLogFile(String folderName, String fileName)
    {
        File file = null;
        try
        {
            String externalStoragePath = Environment.getExternalStorageDirectory().toString();
            File mFolder = new File(externalStoragePath + "/" + folderName);
            if (!mFolder.exists())
            {
                mFolder.mkdir();
            }
            file = new File(mFolder.getAbsolutePath(), fileName);
            saveToFile(file, "", false);

        } catch (Exception e)
        {
            Toast.makeText(getBaseContext(), e.getMessage(), Toast.LENGTH_LONG).show();
        }
        return file;
    }

    public void saveToFile(File file, CharSequence texto, boolean append)
    {
        try
        {
            FileOutputStream fos = new FileOutputStream(file, append);
            OutputStreamWriter myOutWriter = new OutputStreamWriter(fos);
            myOutWriter.append(texto);
            myOutWriter.append("\n");
            myOutWriter.close();
            fos.close();
        } catch (Exception e)
        {
            Toast.makeText(getBaseContext(), e.getMessage(), Toast.LENGTH_LONG).show();
        }
    }

    public String readFile(File file)
    {
        StringBuilder text = new StringBuilder();
        try
        {
            String line = "";
            BufferedReader br = new BufferedReader(new FileReader(file));

            while ((line = br.readLine()) != null)
            {
                text.append(line);
                text.append('\n');
            }
            br.close();
        } catch (IOException e)
        {
            Toast.makeText(getBaseContext(), e.getMessage(), Toast.LENGTH_LONG).show();
        }
        return text.toString();
    }

    private Context getBaseContext()
    {
        return this._context;
    }
}
