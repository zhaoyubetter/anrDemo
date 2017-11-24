package anr.better.com.anrdemo.utils;

import android.content.Context;
import android.text.TextUtils;
import android.text.format.Formatter;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.ByteArrayOutputStream;
import java.io.Closeable;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.TreeSet;

/**
 * 文件工具类
 *
 * @author Administrator
 */
public class FileUtils {

    /**
     * 返回格式化的文件大小
     */
    public static String getFileSize(Context context, long fileSize) {
        return Formatter.formatFileSize(context, fileSize);
    }

    /**
     * 获得文件后缀
     */
    public static String getFileRegex(String absolutePath) {
        return absolutePath.substring(absolutePath.lastIndexOf(".") + 1)
                .toLowerCase();
    }

    /**
     * 遍历所有文件
     */
    public static List<File> getAllFile(File file) {
        List<File> books = new ArrayList<>();
        LinkedList<File> files = new LinkedList<>();
        files.add(file);
        while (files.size() > 0) {
            File temp = files.removeFirst();
            if (temp.isDirectory()) {
                Collections.addAll(books, temp.listFiles());
            } else {
                books.add(temp);
            }
        }
        return books;
    }

    /**
     * 返回文件简称
     */
    public static String getSimpleName(String fileName) {
        return fileName.substring(0, fileName.lastIndexOf("."));
    }

    /**
     * 将List集合转为TreeSet集合 将无序文件转为有序...
     */
    public static <T extends Comparable<T>> List<T> sortList(List<T> list) {
        TreeSet<T> set = new TreeSet<>();
        set.addAll(list);
        list.clear();
        list.addAll(set);
        return list;
    }

    /**
     * 文件是否存在
     * <p>
     * 文件路径, 传 null 返回 false
     *
     * @return false 文件不存在
     */
    public static boolean isFileExist(String path) {
        return !TextUtils.isEmpty(path) && new File(path).exists();
    }

    /**
     * 把字节数组保存为一个文件
     *
     * @param b
     * @param outputFile
     * @return
     */
    public static File getFileFromBytes(byte[] b, String outputFile) {
        BufferedOutputStream stream = null;
        File file = null;
        try {
            file = new File(outputFile);
            FileOutputStream fstream = new FileOutputStream(file);
            stream = new BufferedOutputStream(fstream);
            stream.write(b);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (stream != null) {
                try {
                    stream.close();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        }
        return file;
    }

    /**
     * 保存将字符串保存到指定文件中
     *
     * @param content  文件内容
     * @param file     文件的路径（不包含文件名）
     * @param filename 文件名
     */
    public static File saveFile(String content, File file, String filename,
                                boolean append) {
        try {
            if (file.exists()) {
                File saveFile = new File(file, filename);
                BufferedWriter writer = new BufferedWriter(new FileWriter(saveFile, append));
                writer.write(content);
                FileUtils.closeStream(writer);
                return saveFile;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 删除文件
     *
     * @param file 文件路径
     */
    public static void deleteFile(File file) {
        if (file.exists()) {
            file.delete();
        }
    }

    /**
     * 关闭IO流对象
     *
     * @param stream
     */
    private static void closeStream(Closeable stream) {
        if (stream != null) {
            try {
                stream.close();
            } catch (IOException e) {
            }
        }
    }

    /**
     * 获取文件的内容，以字符串的形式返回， 注：暂没有考虑编码问题
     *
     * @return 文件内容，如果没有文件，返回空
     */
    public static String getFileContent(File file) {
        if (file.exists()) {
            StringBuilder builder = new StringBuilder();
            BufferedReader bufferedReader = null;
            try {
                FileReader reader = new FileReader(file);
                bufferedReader = new BufferedReader(reader);
                String lineTxt;
                while ((lineTxt = bufferedReader.readLine()) != null) {
                    builder.append(lineTxt);
                }
                bufferedReader.close();
            } catch (Exception e) {
            } finally {
                if (bufferedReader != null) {
                }
            }
            return builder.toString();
        }
        return null;
    }

    /**
     * 获取文件内容的字符串形式
     *
     * @param fis
     * @return
     * @throws IOException
     */
    public static String getFileContent(FileInputStream fis) throws IOException {
        if (null != fis && fis.available() > 0) {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            byte[] buffer = new byte[1024];
            int len;
            while ((len = fis.read(buffer)) != -1) {
                baos.write(buffer, 0, len);
            }
            closeStream(baos);
            closeStream(fis);
            return baos.toString("UTF-8");
        }

        return null;
    }

    /**
     * 保存并替换文件，非追加
     *
     * @throws IOException
     * @throws UnsupportedEncodingException
     */
    public static void saveFile(FileOutputStream fos, String content) throws IOException {
        if (null != fos && null != content) {
            fos.write(content.getBytes("UTF-8"));
            fos.flush();
            closeStream(fos);
        }
    }
}
