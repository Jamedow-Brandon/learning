package com.jamedow.laodoufang.utils.ftp;

import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Ftp 上传 下载工具类
 * <p>
 * Created by Administrator on 2017/7/18.
 */
@Component
public class FTPUtils {
    private final Logger logger = LoggerFactory.getLogger(FTPUtils.class);

    private final String EMPTY = "";

    //连接地址
    @Value("${ftp.host}")
    private String host;
    //FTP 端口号
    @Value("${ftp.port}")
    private String port;
    //用户名
    @Value("${ftp.username}")
    private String username;
    //密码
    @Value("${ftp.password}")
    private String password;
    //图片前缀
    @Value("${ftp.server}")
    private String ftpServer;

//    /**
//     * FTP上传单个文件测试
//     */
//    public  void testUpload() {
//        FTPClient ftpClient = new FTPClient();
//        FileInputStream fis = null;
//
//        try {
//            //连接ftp
//            ftpClient.connect("106.14.210.31", 21);
//            //登录ftp
//            ftpClient.login("vsftp", "Zhangliang@520");
//            //返回状态吗 200-300 登录成功
//            int reply = ftpClient.getReplyCode();
//            if (!FTPReply.isPositiveCompletion(reply)) {
//                //重新连接
//                ftpClient.disconnect();
//            }
//            File srcFile = new File("F:\\user\\Desktop\\large-8.jpg");
//            //读取文件
//            fis = new FileInputStream(srcFile);
//            //设置上传目录
//            if (!ftpClient.changeWorkingDirectory("media/user/photo")) {
//                //创建目录
//                CreateDirectory(ftpClient, "media/user/photo");
//            }
////            ftpClient.setBufferSize(1024);
////            ftpClient.setControlEncoding("UTF-8");
//            //设置文件类型（二进制）
//            ftpClient.setFileType(FTPClient.BINARY_FILE_TYPE);
//            //使用ftp被动模式
//            ftpClient.enterLocalPassiveMode();
//
//            if (ftpClient.isConnected()) {
//                ftpClient.storeFile("large-8.jpg", fis);
//            }
//            //断开连接
//            ftpClient.logout();
//        } catch (IOException e) {
//            e.printStackTrace();
//            throw new RuntimeException("FTP客户端出错！", e);
//        } finally {
//            try {
//                fis.close();
//                if (ftpClient.isConnected()) {
//                    ftpClient.disconnect();
//                }
//            } catch (IOException e) {
//                e.printStackTrace();
//                throw new RuntimeException("关闭FTP连接发生异常！", e);
//            }
//        }
//    }
//
//    public  void main(String[] args) {
//        testUpload();
//    }

    /**
     * 上传本地文件
     *
     * @param localFilename 本地文件绝对路径
     * @return 可访问的文件URL；<br>
     * return <b>""</b> if fail
     */
    public String uploadFile(String localFilename, String destDirName, String fileName) {
        FTPClient ftpClient = new FTPClient();
        FileInputStream fis = null;
        File file = new File(localFilename);
        if (!file.exists() || !file.isFile()) {
            logger.error("当前文件不存在或不是文件，上传失败");
            return null;
        }
        String fileUrl = EMPTY;
        try {
            ftpClient.connect(host, Integer.valueOf(port));//连接FTP服务器
            //如果采用默认端口，可以使用ftp.connect(url)的方式直接连接FTP服务器
            ftpClient.login(username, password);//登录

            fis = new FileInputStream(file);
            //设置上传目录
            if (!ftpClient.changeWorkingDirectory(destDirName)) {
                //创建目录
                CreateDirectory(ftpClient, destDirName);
            }
//            ftpClient.setBufferSize(1024);
//            ftpClient.setControlEncoding("UTF-8");
            //设置文件类型（二进制）
            ftpClient.setFileType(FTPClient.BINARY_FILE_TYPE);
            //使用ftp被动模式
            ftpClient.enterLocalPassiveMode();

            if (ftpClient.isConnected()) {
                ftpClient.storeFile(fileName, fis);
            }

            //图片在服务器上的访问地址
            fileUrl = ftpServer + destDirName + "/" + fileName;
            //断开连接
            ftpClient.logout();
        } catch (IOException e) {
            logger.error(e.getMessage(), e);
            throw new RuntimeException("FTP客户端出错！", e);
        } finally {
            try {
                fis.close();
                if (ftpClient.isConnected()) {
                    ftpClient.disconnect();
                }
            } catch (IOException e) {
                logger.error(e.getMessage(), e);
                throw new RuntimeException("关闭FTP连接发生异常！", e);
            }
        }
        return fileUrl;
    }

    /**
     * FTP下载单个文件测试
     */
    public void testDownload() {
        FTPClient ftpClient = new FTPClient();
        FileOutputStream fos = null;

        try {
            ftpClient.connect("192.168.14.117");
            ftpClient.login("admin", "123");

            String remoteFileName = "/admin/pic/3.gif";
            fos = new FileOutputStream("c:/down.gif");

            ftpClient.setBufferSize(1024);
            //设置文件类型（二进制）
            ftpClient.setFileType(FTPClient.BINARY_FILE_TYPE);
            ftpClient.retrieveFile(remoteFileName, fos);

            ftpClient.logout();
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("FTP客户端出错！", e);
        } finally {
            try {
                fos.close();
                if (ftpClient.isConnected()) {
                    ftpClient.disconnect();
                }
            } catch (IOException e) {
                e.printStackTrace();
                throw new RuntimeException("关闭FTP连接发生异常！", e);
            }
        }
    }

    //创建多层目录文件，如果有ftp服务器已存在该文件，则不创建，如果无，则创建
    public boolean CreateDirectory(FTPClient ftpClient, String remote) throws IOException {
        boolean success = true;
        String directory = remote + "/";
//        String directory = remote.substring(0, remote.lastIndexOf("/") + 1);
        // 如果远程目录不存在，则递归创建远程服务器目录
        if (!directory.equalsIgnoreCase("/") && !changeWorkingDirectory(ftpClient, new String(directory))) {
            int start = 0;
            int end = 0;
            if (directory.startsWith("/")) {
                start = 1;
            } else {
                start = 0;
            }
            end = directory.indexOf("/", start);
            String path = "";
            String paths = "";
            while (true) {

                String subDirectory = new String(remote.substring(start, end).getBytes("GBK"), "iso-8859-1");
                path = path + "/" + subDirectory;
                if (!existFile(ftpClient, path)) {
                    if (makeDirectory(ftpClient, subDirectory)) {
                        changeWorkingDirectory(ftpClient, subDirectory);
                    } else {
                        logger.debug("创建目录[" + subDirectory + "]失败");
                        changeWorkingDirectory(ftpClient, subDirectory);
                    }
                } else {
                    changeWorkingDirectory(ftpClient, subDirectory);
                }

                paths = paths + "/" + subDirectory;
                start = end + 1;
                end = directory.indexOf("/", start);
                // 检查所有目录是否创建完毕
                if (end <= start) {
                    break;
                }
            }
        }
        return success;
    }

    //判断ftp服务器文件是否存在
    public boolean existFile(FTPClient ftpClient, String path) throws IOException {
        boolean flag = false;
        FTPFile[] ftpFileArr = ftpClient.listFiles(path);
        if (ftpFileArr.length > 0) {
            flag = true;
        }
        return flag;
    }


    //创建目录
    public boolean makeDirectory(FTPClient ftpClient, String dir) {
        boolean flag = true;
        try {
            flag = ftpClient.makeDirectory(dir);
            if (flag) {
                logger.debug("创建文件夹" + dir + " 成功！");

            } else {
                logger.debug("创建文件夹" + dir + " 失败！");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return flag;
    }

    //改变目录路径
    public boolean changeWorkingDirectory(FTPClient ftpClient, String directory) {
        boolean flag = true;
        try {
            flag = ftpClient.changeWorkingDirectory(directory);
            if (flag) {
                logger.debug("进入文件夹" + directory + " 成功！");

            } else {
                logger.debug("进入文件夹" + directory + " 失败！");
            }
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
        return flag;
    }
}
