package com.jamedow.laodoufang.utils.ftp;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPReply;
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

    /**
     * FTP上传单个文件测试
     */
    public static void testUpload() {
        FTPClient ftpClient = new FTPClient();
        FileInputStream fis = null;

        try {
            //连接ftp
            ftpClient.connect("106.14.210.31", 21);
            //登录ftp
            ftpClient.login("vsftp", "Zhangliang@520");
            //返回状态吗 200-300 登录成功
            int reply = ftpClient.getReplyCode();
            if (!FTPReply.isPositiveCompletion(reply)) {
                //重新连接
                ftpClient.disconnect();
            }
            File srcFile = new File("F:\\user\\Desktop\\large-8.jpg");
            //读取文件
            fis = new FileInputStream(srcFile);
            //设置上传目录
            ftpClient.changeWorkingDirectory("user/photo");
//            ftpClient.setBufferSize(1024);
//            ftpClient.setControlEncoding("UTF-8");
            //设置文件类型（二进制）
            ftpClient.setFileType(FTPClient.BINARY_FILE_TYPE);
            //使用ftp被动模式
            ftpClient.enterLocalPassiveMode();

            if (ftpClient.isConnected()) {
                ftpClient.storeFile("large-8.jpg", fis);
            }
            //断开连接
            ftpClient.logout();
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("FTP客户端出错！", e);
        } finally {
            try {
                fis.close();
                if (ftpClient.isConnected()) {
                    ftpClient.disconnect();
                }
            } catch (IOException e) {
                e.printStackTrace();
                throw new RuntimeException("关闭FTP连接发生异常！", e);
            }
        }
    }

    public static void main(String[] args) {
        testUpload();
    }

    /**
     * 上传本地文件
     *
     * @param localFilename 本地文件绝对路径
     * @return 可访问的文件URL；<br>
     * return <b>""</b> if fail
     */
    public String uploadFile(String localFilename, String dirName) {
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
            ftpClient.changeWorkingDirectory(dirName);
//            ftpClient.setBufferSize(1024);
//            ftpClient.setControlEncoding("UTF-8");
            //设置文件类型（二进制）
            ftpClient.setFileType(FTPClient.BINARY_FILE_TYPE);
            ftpClient.enterLocalPassiveMode();

            if (ftpClient.isConnected()) {
                ftpClient.storeFile("large-8.jpg", fis);
            }

            fileUrl = ftpServer + dirName + file.getName();
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
     * 获取文件后缀名，不含.
     *
     * @param fileName
     * @return 文件后缀
     */
    public String getFileSuffix(String fileName) {
        String suffix = EMPTY;
        if (!StringUtils.isBlank(fileName)) {
            suffix = fileName.substring(fileName.lastIndexOf('.') + 1);
        }
        return suffix;
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
}
