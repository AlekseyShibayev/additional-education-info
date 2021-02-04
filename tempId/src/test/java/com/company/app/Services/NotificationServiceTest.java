package com.company.app.Services;

import org.junit.Assert;
import org.junit.Test;

public class NotificationServiceTest {

    @Test
    public void isLastSeenGoodTest() throws Exception {
        NotificationService service = new NotificationService();
        String lastSeen = "13 Minute ago";
        String lastSeen2 = "61 Minute ago";
        Assert.assertTrue(service.isLastSeenGood(lastSeen));
        Assert.assertFalse(service.isLastSeenGood(lastSeen2));
    }
}