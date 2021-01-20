package MainLogic;

import Services.*;

public class ApplicationHelper {

    private NotificationService notificationService;
    private FileDataExtractorService fileDataExtractorService;
    private HtmlParserService htmlParserService;
    private CaptchaFighterService captchaFighterService;
    private HtmlResponseExtractorService htmlResponseExtractorService;

    public ApplicationHelper() {
        this.notificationService = new NotificationService();
        this.fileDataExtractorService = new FileDataExtractorService();
        this.htmlParserService = new HtmlParserService();
        this.captchaFighterService = new CaptchaFighterService();
        this.htmlResponseExtractorService = new HtmlResponseExtractorService();
    }

    public HtmlResponseExtractorService getHtmlResponseExtractorService() {
        return htmlResponseExtractorService;
    }

    public void setHtmlResponseExtractorService(HtmlResponseExtractorService htmlResponseExtractorService) {
        this.htmlResponseExtractorService = htmlResponseExtractorService;
    }

    public NotificationService getNotificationService() {
        return notificationService;
    }

    public void setNotificationService(NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    public FileDataExtractorService getFileDataExtractorService() {
        return fileDataExtractorService;
    }

    public void setFileDataExtractorService(FileDataExtractorService fileDataExtractorService) {
        this.fileDataExtractorService = fileDataExtractorService;
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
