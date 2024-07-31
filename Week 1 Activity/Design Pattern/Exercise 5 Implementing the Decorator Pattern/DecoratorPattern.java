public interface Notifier {
    void send(String message);
}
public class EmailNotifier implements Notifier {
    @Override
    public void send(String message) {
        // Implement email sending
    }
}
public abstract class NotifierDecorator implements Notifier {
    private Notifier notifier;

    public NotifierDecorator(Notifier notifier) {
        this.notifier = notifier;
    }

    @Override
    public void send(String message) {
        notifier.send(message);
    }
}

public class SMSNotifierDecorator extends NotifierDecorator {
    public SMSNotifierDecorator(Notifier notifier) {
        super(notifier);
    }

    public void sendSMS(String message) {
        // Implement SMS sending
    }

    @Override
    public void send(String message) {
        super.send(message);
        sendSMS(message);
    }
}

public class SlackNotifierDecorator extends NotifierDecorator {
    public SlackNotifierDecorator(Notifier notifier) {
        super(notifier);
    }

    public void sendSlackMessage(String message) {
        // Implement Slack message sending
    }

    @Override
    public void send(String message) {
        super.send(message);
        sendSlackMessage(message);
    }
}
public class DecoratorPattern {
    public static void main(String[] args) {
        Notifier emailNotifier = new EmailNotifier();
        Notifier smsNotifier = new SMSNotifierDecorator(emailNotifier);
        Notifier slackNotifier = new SlackNotifierDecorator(smsNotifier);

        slackNotifier.send("Hello, world!");
    }
}