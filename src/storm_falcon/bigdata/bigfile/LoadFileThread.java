package storm_falcon.bigdata.bigfile;

import storm_falcon.util.file.FileReader;

/**
 * Created by Storm_Falcon on 2016/4/27.
 *
 */
public class LoadFileThread extends Thread {

    private String file = null;

    private IProcess process = null;

    public LoadFileThread(String file, IProcess process) {
        this.file = file;
        this.process = process;
    }

    public void run() {
        FileReader reader = new FileReader();
        reader.open(file);

        while (reader.hasNext()) {
            String line = reader.getLine();
            process.doFun(line);
        }

        reader.close();
    }
}
