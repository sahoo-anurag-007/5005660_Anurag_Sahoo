public class Logger {
    private static Logger _instance;
    private LogLevel logLevel;
    private List<String> logs;
    private PrintWriter fileWriter;

    private Logger() {
        logLevel = LogLevel.INFO;
        logs = new ArrayList<>();
        try {
            fileWriter = new PrintWriter(new FileWriter("server.log"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Singleton instance retrieval
    public static Logger getInstance() {
        if (_instance == null) {
            synchronized (Logger.class) {
                if (_instance == null) {
                    _instance = new Logger();
                }
            }
        }
        return _instance;
    }

    // Set the log level
    public void setLogLevel(LogLevel level) {
        logLevel = level;
    }

    // Log a message with the specified level
    public void log(LogLevel level, String message) {
        if (level.ordinal() >= logLevel.ordinal()) {
            String log = String.format("[%s] [%s] %s", level, LocalDateTime.now(), message);
            System.out.println(log);
            fileWriter.println(log);
            fileWriter.flush();
        }
        logs.add(String.format("[%s] [%s] %s", level, LocalDateTime.now(), message));
    }

    // Display all logged messages
    public void displayLogs() {
        for (String log : logs) {
            System.out.println(log);
        }
    }
}
enum LogLevel {
    TRACE(1),
    DEBUG(2),
    INFO(3),
    WARN(4),
    ERROR(5);

    final int level;

    LogLevel(int level) {
        this.level = level;
    }
}
public class SingletonSerializedTest {
    public static void main(String[] args) throws FileNotFoundException, IOException, ClassNotFoundException {
        Logger instanceOne = Logger.getInstance();
        ObjectOutput out = new ObjectOutputStream(new FileOutputStream("filename.ser"));
        out.writeObject(instanceOne);
        out.close();

        ObjectInput in = new ObjectInputStream(new FileInputStream("filename.ser"));
        Logger instanceTwo = (Logger) in.readObject();
        in.close();

        System.out.println("Are both loggers of the same reference? " + (instanceOne == instanceTwo));
    }
}