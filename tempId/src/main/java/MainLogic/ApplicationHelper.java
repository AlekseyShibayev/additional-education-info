package MainLogic;

import Services.*;

public class ApplicationHelper {

    private NotificationService notificationService;
    private DataExtractorService dataExtractorService;
    private HtmlParserService htmlParserService;
    private CaptchaFighterService captchaFighterService;

    public ApplicationHelper() {
        this.notificationService = new NotificationService();
        this.dataExtractorService = new DataExtractorService();
        this.htmlParserService = new HtmlParserService();
        this.captchaFighterService = new CaptchaFighterService();
    }

    public NotificationService getNotificationService() {
        return notificationService;
    }

    public void setNotificationService(NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    public DataExtractorService getDataExtractorService() {
        return dataExtractorService;
    }

    public void setDataExtractorService(DataExtractorService dataExtractorService) {
        this.dataExtractorService = dataExtractorService;
    }

    HtmlParserService getHtmlParserService() {
        return htmlParserService;
    }

    public void setHtmlParserService(HtmlParserService htmlParserService) {
        this.htmlParserService = htmlParserService;
    }

    CaptchaFighterService getCaptchaFighterService() {
        return captchaFighterService;
    }

    public void setCaptchaFighterService(CaptchaFighterService captchaFighterService) {
        this.captchaFighterService = captchaFighterService;
    }
}
