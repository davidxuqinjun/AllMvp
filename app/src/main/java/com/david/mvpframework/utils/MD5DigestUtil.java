package com.david.mvpframework.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MD5DigestUtil {

    /**
     * Default password digits
     */
    protected static char hexDigits[] = {'0', '1', '2', '3', '4', '5', '6',
            '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};

    protected static MessageDigest messagedigest = null;

    static {
        try {
            messagedigest = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException nsaex) {
            nsaex.printStackTrace();
        }
    }

    /**
     * 生成字符串的md5校验值
     *
     * @param s
     * @return
     */
    public static String getMD5String(String s) {
        return getMD5String(s.getBytes());
    }

    /**
     * Check MD5 matching
     *
     * @param password
     * @param md5PwdStr
     * @return
     */
    public static boolean checkMD5(String password, String md5PwdStr) {
        String s = getMD5String(password);
        return s.equals(md5PwdStr);
    }

    /**
     * Check MD5 matching
     *
     * @param sourceFile
     * @param md5Str
     * @return
     */
    public static boolean checkMD5(File sourceFile, String md5Str) {
        boolean f = false;
        String s;
        try {
            s = getFileMD5String(sourceFile);
            //System.out.println(new java.util.Date()+" md5 s:"+s);

            f = md5Str.equalsIgnoreCase(s);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return f;

    }

    /**
     * Check MD5 matching
     *
     * @param sourceFile
     * @param md5File
     * @return
     */
    public static boolean checkMD5(File sourceFile, File md5File) {
        //System.out.println(new java.util.Date()+" source File:"+sourceFile.getAbsolutePath());
        //System.out.println("md5File File:"+md5File.getAbsolutePath());
        boolean f = false;
        if (md5File.exists()) {
            String md5Str = "";
            java.io.BufferedReader reader = null;
            try {
                reader = new java.io.BufferedReader(new FileReader(md5File));
                String line = null;
                while ((line = reader.readLine()) != null) {
                    if (!line.trim().equals("")) {
                        md5Str = line.trim();
                        break;
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                if (reader != null) {
                    try {
                        reader.close();
                    } catch (Throwable e) {
                        e.printStackTrace();
                    }
                }
            }
            //System.out.println("md5 s:"+md5Str);
            return checkMD5(sourceFile, md5Str);
        } else {
            SmartSDNLoger.debug("File not exist:" + md5File.getAbsolutePath());
        }
        return f;


    }

    /**
     * generate the MD5 string by given file
     *
     * @param file
     * @return
     * @throws IOException
     */
    public static String getFileMD5String(File file) throws IOException {
        InputStream fis;
        fis = new FileInputStream(file);
        byte[] buffer = new byte[1024];
        int numRead = 0;
        while ((numRead = fis.read(buffer)) > 0) {
            messagedigest.update(buffer, 0, numRead);
        }
        try {
            fis.close();
        } catch (Throwable e) {
            e.printStackTrace();
        }
        return bufferToHex(messagedigest.digest());
    }

    public static String getMD5String(byte[] bytes) {
        messagedigest.update(bytes);
        return bufferToHex(messagedigest.digest());
    }

    private static String bufferToHex(byte bytes[]) {
        return bufferToHex(bytes, 0, bytes.length);
    }

    private static String bufferToHex(byte bytes[], int m, int n) {
        StringBuffer stringbuffer = new StringBuffer(2 * n);
        int k = m + n;
        for (int l = m; l < k; l++) {
            appendHexPair(bytes[l], stringbuffer);
        }
        return stringbuffer.toString();
    }

    private static void appendHexPair(byte bt, StringBuffer stringbuffer) {
        char c0 = hexDigits[(bt & 0xf0) >> 4];
        char c1 = hexDigits[bt & 0xf];
        stringbuffer.append(c0);
        stringbuffer.append(c1);
    }

    public static void main(String[] args) throws IOException {
        long begin = System.currentTimeMillis();

        File file = new File("D:/work/DCWS-6028_6.2.200.18_nos.img");
        String md5 = getFileMD5String(file);


        long end = System.currentTimeMillis();
        System.out.println("md5:" + md5 + " time:" + ((end - begin) / 1000) + "s");
    }
}

