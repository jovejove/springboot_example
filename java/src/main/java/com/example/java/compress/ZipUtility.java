package com.example.java.compress;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Objects;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class ZipUtility {


    public static void zipFile(File srcFile, ZipOutputStream zipOutputStream) {
        if (!srcFile.exists()) {
            return;
        }
        try {
            zipOutputStream.putNextEntry(new ZipEntry(srcFile.getName()));
            zipOutputStream.write(Files.readAllBytes(srcFile.toPath()));
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                zipOutputStream.closeEntry();
                zipOutputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 目录&文件皆可压缩
     *
     * @param file
     * @param zipOutputStream
     * @param baseDir
     */
    public static void zipFile(File file, ZipOutputStream zipOutputStream, String baseDir) {
        try {
            if (file.isDirectory()) {
                baseDir += file.getName() + "/";
                zipOutputStream.putNextEntry(new ZipEntry(baseDir));
                File[] files = file.listFiles();
                if (Objects.nonNull(files) && files.length > 0) {
                    for (File f : files) {
                        zipFile(f, zipOutputStream, baseDir);
                    }
                }
            } else {
                zipOutputStream.putNextEntry(new ZipEntry(baseDir + file.getName()));
                byte[] bytes = Files.readAllBytes(file.toPath());
                zipOutputStream.write(bytes);
                zipOutputStream.closeEntry();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static void main(String[] args) throws IOException {
        String path = "C:\\Users\\Administrator\\Pictures\\Saved Pictures\\";
        String filePath = "C:\\Users\\Administrator\\Pictures\\Saved Pictures\\ddd.jpg";
        String outPath = System.getProperty("java.io.tmpdir");
        String outputFile = outPath + "msedu-git-规范.png.zip";
        try (ZipOutputStream zipOutputStream = new ZipOutputStream(new FileOutputStream(outputFile));) {
            ZipUtility.zipFile(new File(path), zipOutputStream, "/");
        }

    }

}