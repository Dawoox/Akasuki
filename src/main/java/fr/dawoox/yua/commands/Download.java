package fr.dawoox.yua.commands;

import discord4j.common.util.Snowflake;
import fr.dawoox.yua.utils.Command;
import org.apache.commons.io.FilenameUtils;

import java.io.*;
import java.net.URL;
import java.util.Map;

public class Download {

    public static void reg(Map<String, Command> commands){
        commands.put("download", event -> {
            String url = event.getMessage().getContent().substring(10);
            if (!event.getMessage().getAuthor().get().getId().equals(Snowflake.of("389512860235071500"))){
                event.getMessage().getChannel().block().createMessage("Uh, it's a dev command that :thinking: ").block();
                return;
            }
            try {
                saveImage(url, "F:\\gifs");
                event.getMessage().getChannel().block().createMessage("File download on your pc !").block();
            } catch (IOException e){
                e.printStackTrace();
            }
        });
    }

    public static void saveImage(String imageUrl, String destinationFolder) throws IOException {
        URL url = new URL(imageUrl);
        String destinationFile = destinationFolder + "\\" + FilenameUtils.getName(url.getPath());
        InputStream is = url.openStream();
        File file = new File(destinationFile);
        file.getParentFile().mkdirs();
        file.createNewFile();
        OutputStream os = new FileOutputStream(destinationFile);

        byte[] b = new byte[2048];
        int length;

        while ((length = is.read(b)) != -1) {
            os.write(b, 0, length);
        }

        is.close();
        os.close();
    }
}
