import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class WeatherDashboard {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        try {
            while (true) {
                try {
                    System.out.print("Enter city name: ");
                    String city = input.nextLine().trim();

                    if (city.isEmpty()) {
                        System.out.println("City name cannot be empty.");
                    } else {
                        // 1. Find city coordinates from Open-Meteo geocoding API.
                        String encodedCity = URLEncoder.encode(city, StandardCharsets.UTF_8);
                        String geoUrl = "https://geocoding-api.open-meteo.com/v1/search?name=" + encodedCity
                                + "&count=1&language=en&format=json";
                        String geoJson = fetchJson(geoUrl);

                        int resultsIndex = geoJson.indexOf("\"results\":[");
                        if (resultsIndex == -1) {
                            System.out.println("No matching city found.");
                        } else {
                            String latitude = extractValue(geoJson, "latitude", resultsIndex);
                            String longitude = extractValue(geoJson, "longitude", resultsIndex);
                            String cityName = extractStringValue(geoJson, "name", resultsIndex);

                            if (latitude == null || longitude == null || cityName == null) {
                                System.out.println("Could not parse city coordinates.");
                            } else {
                                // 2. Fetch current weather for that location.
                                String weatherUrl = "https://api.open-meteo.com/v1/forecast?latitude=" + latitude
                                        + "&longitude=" + longitude + "&current_weather=true";
                                String weatherJson = fetchJson(weatherUrl);

                                int currentWeatherIndex = weatherJson.indexOf("\"current_weather\":");
                                if (currentWeatherIndex == -1) {
                                    System.out.println("Could not find current weather in API response.");
                                } else {
                                    String temperature = extractValue(weatherJson, "temperature", currentWeatherIndex);
                                    String windspeed = extractValue(weatherJson, "windspeed", currentWeatherIndex);
                                    String weatherCode = extractValue(weatherJson, "weathercode", currentWeatherIndex);

                                    if (temperature == null || windspeed == null || weatherCode == null) {
                                        System.out.println("Could not parse weather details.");
                                    } else {
                                        // 3. Print dashboard.
                                        System.out.println("\n=== MY WEATHER DASHBOARD ===");
                                        System.out.println("Location: " + cityName + " (Lat " + latitude + ", Lon " + longitude + ")");
                                        System.out.println("Current Temperature: " + temperature + " C");
                                        System.out.println("Current Windspeed: " + windspeed + " km/h");
                                        System.out.println("Weather Code: " + weatherCode);
                                        System.out.println("============================");
                                    }
                                }
                            }
                        }
                    }
                } catch (Exception e) {
                    System.out.println("Oops! Something went wrong.");
                    e.printStackTrace();
                }

                System.out.print("\nAnother city? (y/n): ");
                String answer = input.nextLine().trim().toLowerCase();
                if (!answer.equals("y") && !answer.equals("yes")) {
                    System.out.println("Goodbye.");
                    break;
                }
                System.out.println();
            }
        } finally {
            input.close();
        }
    }

    private static String fetchJson(String urlString) throws Exception {
        URL url = new URL(urlString);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        connection.setConnectTimeout(10000);
        connection.setReadTimeout(10000);

        int responseCode = connection.getResponseCode();
        if (responseCode != 200) {
            connection.disconnect();
            throw new RuntimeException("HTTP request failed with code: " + responseCode);
        }

        Scanner scanner = new Scanner(connection.getInputStream());
        StringBuilder json = new StringBuilder();
        while (scanner.hasNextLine()) {
            json.append(scanner.nextLine());
        }
        scanner.close();
        connection.disconnect();

        return json.toString();
    }

    private static String extractValue(String json, String key, int startIndex) {
        String pattern = "\"" + key + "\":";
        int keyIndex = json.indexOf(pattern, startIndex);
        if (keyIndex == -1) {
            return null;
        }

        int valueStart = keyIndex + pattern.length();
        int valueEnd = valueStart;

        while (valueEnd < json.length()) {
            char c = json.charAt(valueEnd);
            if (c == ',' || c == '}') {
                break;
            }
            valueEnd++;
        }

        return json.substring(valueStart, valueEnd).trim();
    }

    private static String extractStringValue(String json, String key, int startIndex) {
        String pattern = "\"" + key + "\":\"";
        int keyIndex = json.indexOf(pattern, startIndex);
        if (keyIndex == -1) {
            return null;
        }

        int valueStart = keyIndex + pattern.length();
        int valueEnd = json.indexOf("\"", valueStart);
        if (valueEnd == -1) {
            return null;
        }

        return json.substring(valueStart, valueEnd);
    }
}
