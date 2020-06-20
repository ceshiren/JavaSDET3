package test_web.wework.page;

import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;

public class MainPage extends WebBasePage {

    public MainPage() {
        super();
//        System.setProperty("webdriver.gecko.driver", "/Users/seveniruby/ke/java_3/selenium/drivers/geckodriver");

        String url = "https://work.weixin.qq.com/wework_admin/frame";
//        FirefoxDriver driver=new FirefoxDriver();
        driver.get(url);
        driver.manage().deleteAllCookies();

        //todo: 改成从文件读取

        //todo: 使用自己的cookie，别使用老师的
        driver.manage().addCookie(new Cookie("pgv_pvid", "7369917120"));
        driver.manage().addCookie(new Cookie("pgv_pvi", "3082740736"));
        driver.manage().addCookie(new Cookie("RK", "LYi80hm44v"));
        driver.manage().addCookie(new Cookie("ptcz", "45aa41170bdc0dcb226e9cc4612a1441010facbecd8e643c59ae6435a78e6bd7"));
        driver.manage().addCookie(new Cookie("_ga", "GA1.2.1174079383.1569761110"));
        driver.manage().addCookie(new Cookie("tvfe_boss_uuid", "5ba628f509c99733"));
        driver.manage().addCookie(new Cookie("ied_qq", "o2158067250"));
        driver.manage().addCookie(new Cookie("worknote_upgradeFlag1688853941438590", ""));
        driver.manage().addCookie(new Cookie("wwrtx.i18n_lan", "zh"));
        driver.manage().addCookie(new Cookie("worknote_upgradeFlag1688851806304546", "40000001,40000002,40000003"));
        driver.manage().addCookie(new Cookie("o_cookie", "1516491904"));
        driver.manage().addCookie(new Cookie("pac_uid", "1_1516491904"));
        driver.manage().addCookie(new Cookie("__utma", "135912439.1174079383.1569761110.1581560653.1582467230.4"));
        driver.manage().addCookie(new Cookie("__utmz", "135912439.1582467230.4.3.utmcsr=exmail.qq.com|utmccn=(referral)|utmcmd=referral|utmcct=/"));
        driver.manage().addCookie(new Cookie("Hm_lvt_f2ba645ba13636ba52b0234381f51cbc", "1581469659,1581469679,1582467230"));
        driver.manage().addCookie(new Cookie("Hm_lvt_9364e629af24cb52acc78b43e8c9f77d", "1586439438,1587724679"));
        driver.manage().addCookie(new Cookie("pgv_info", "ssid=s9831227618"));
        driver.manage().addCookie(new Cookie("wwrtx.ref", "direct"));
        driver.manage().addCookie(new Cookie("wwrtx.refid", "2643122397429442"));
        driver.manage().addCookie(new Cookie("ww_rtkey", "2453097365"));
        driver.manage().addCookie(new Cookie("wxpay.vid", "1688853941438590"));
        driver.manage().addCookie(new Cookie("wxpay.corpid", "1970325013047104"));
        driver.manage().addCookie(new Cookie("wwrtx.ltype", "1"));
        driver.manage().addCookie(new Cookie("wwrtx.vid", "1688853941438590"));
        driver.manage().addCookie(new Cookie("wwrtx.logined", "true"));
        driver.manage().addCookie(new Cookie("pgv_si", "s1911890944"));
        driver.manage().addCookie(new Cookie("pt_sms_phone", "185******85"));
        driver.manage().addCookie(new Cookie("ptui_loginuin", "testerhomeee@qq.com"));
        driver.manage().addCookie(new Cookie("uin", "o2158067250"));
        driver.manage().addCookie(new Cookie("skey", "@il705ykGg"));
        driver.manage().addCookie(new Cookie("luin", "o2158067250"));
        driver.manage().addCookie(new Cookie("lskey", "00010000f1cb0b6c7d02095a5c0f68ed83b427a2150b1b3b9764116b397256d5e61de69906c143c741e3739a"));
        driver.manage().addCookie(new Cookie("_gid", "GA1.2.617894885.1590816063"));
        driver.manage().addCookie(new Cookie("wwrtx.vst", "GVuQkVZItNwv2MMhWJ3TNsiavHWer3keIcfbO7pw1PLIYR09jBlWvvhiBiJJUvwIFQgZhD4xI4PIAeZVV_oTM-tie_1vw-zbeNs7jTqKLJqiA_fKaVzUgJrfx2RG55Xt_G0HyqOO5BxDkKB9hqycQmrAXiv-RHd4rbcIuLdP7BGFWUrnwxLumBxEpny09fJ49W4eWqHhlUAGe9yHLR3kfViccbR8cHEZEPCO9lAvdpm2zwF094K_LZQ_rmjAcZjxYhnxwaLDn8lrBgqlTEvSNw"));
        driver.manage().addCookie(new Cookie("wwrtx.d2st", "a8820380"));
        driver.manage().addCookie(new Cookie("wwrtx.sid", "PvmFAAW3_ZQOnOfp5SzMi7NAEoq9nJ4t3FF6o5IRmzje7aOBiVQa5yhGdAtCKXmL"));

        System.out.println(driver.manage().getCookies());
        driver.get(url);

    }

    public ContactPage toContact() {
        //todo:
        click(By.cssSelector("#menu_contacts"));
//        driver.findElement(By.cssSelector("#menu_contacts")).click();
        return new ContactPage(driver);
    }

}
