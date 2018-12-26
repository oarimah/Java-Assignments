public class Configuration {
    private String configuration;
    private int configScore;
    public Configuration(String config, int score) {
        configuration = config;
        configScore  = score;
    }

    public String getStringConfiguration() {
        return configuration;
    }

    public int getScore() {
        return configScore;
    }
}