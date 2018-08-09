package org.xfs.scm.common.utils.encrypt;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * 密码加密类
 */
public class PasswordHelper {

    /**
     * 密码加密
     *
     * @param passwd
     * @param salt
     * @return
     */
    public static String getEncryptedPwd(String passwd, String salt) {

        String pwd = "";
        try {
            pwd = getEncryptedPwd(passwd.getBytes("UTF-8"), salt.getBytes("UTF-8"));
        } catch (UnsupportedEncodingException e) {
            //e.printStackTrace();
        }
        return pwd;
    }

    /**
     * 密码加密
     *
     * @param passwd
     * @param salt
     * @return
     */
    public static String getEncryptedPwd(byte[] passwd, byte[] salt) {
        //声明摘要对象，并生成
        MessageDigest md = null;
        try {
            md = MessageDigest.getInstance("MD5");
            md.update(salt);
            md.update(passwd);
        } catch (NoSuchAlgorithmException e) {
            //e.printStackTrace();
        }
        byte[] digest = md.digest();
        int i;
        StringBuffer buf = new StringBuffer();
        for (int offset = 0; offset < digest.length; offset++) {
            i = digest[offset];
            if (i < 0)
                i += 256;
            if (i < 16)
                buf.append("0");
            buf.append(Integer.toHexString(i));
        }
        String pwd = buf.toString();

        return pwd;
    }

    /**
     * 验证密码
     *
     * @param passwd 用户输入的密码
     * @param salt   盐
     * @param md5pwd 数据库中的密码
     * @return
     */
    public static boolean validPasswd(String passwd, String salt, String md5pwd) {

        String pwdStr = getEncryptedPwd(passwd, salt);
        if (pwdStr.equals(md5pwd)) {
            return true;
        }
        return false;
    }

    /**
     * 验证密码
     *
     * @param passwd 用户输入的密码
     * @param salt   盐
     * @param md5pwd 数据库中的密码
     * @return
     */
    public static boolean validPasswd(String passwd, byte[] salt, String md5pwd) {

        String pwdStr = null;
        try {
            pwdStr = getEncryptedPwd(passwd.getBytes("UTF-8"), salt);
        } catch (UnsupportedEncodingException e) {
            //e.printStackTrace();
        }

        if (pwdStr.equals(md5pwd)) {
            return true;
        }
        return false;
    }


    public static void main(String[] args) {
        String pwd = PasswordHelper.getEncryptedPwd("董小姐123456", "NCIter7gFN");
        System.out.println(pwd);//5ef5302354f69d459ee0314a2867b1e9
        String pwd1 = PasswordHelper.getEncryptedPwd("123456", "董小姐" + "NCIter7gFN");
        System.out.println(pwd1);//5ef5302354f69d459ee0314a2867b1e9

        //E10ADC3949BA59ABBE56E057F20F883E
       // String pwd1 = PasswordHelper.getEncryptedPwd("E10ADC3949BA59ABBE56E057F20F883E", "qht13696770150" + "iQIlJnhmut");
      //  boolean is =validPasswd("baadb48b98d8b8e68b4f146f36792fcc",("admin" + "admin").getBytes(),"ed24401d8c177bcb5bc52e42dd9fed8d");
        boolean is = PasswordHelper.validPasswd("董小姐123456", "NCIter7gFN", "7503375308556c4933f49638d4c9c225");
        System.out.println(is);
        
    }
}
