
package ru.blockmania.bmpwelcome.utils;

import me.clip.placeholderapi.PlaceholderAPI;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.Color;
import org.bukkit.entity.Player;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Formating {

    private static String color(String from) {
        Pattern pattern = Pattern.compile("#[a-fA-F0-9]{6}");
        for(Matcher matcher = pattern.matcher(from); matcher.find(); matcher = pattern.matcher(from)) {
            String hexCode = from.substring(matcher.start(), matcher.end());
            String replaceSharp = hexCode.replace('#', 'x');
            char[] ch = replaceSharp.toCharArray();
            StringBuilder builder = new StringBuilder();
            for (char c : ch) {
                builder.append("&").append(c);
            }
            from = from.replace(hexCode, builder.toString());
        }

        return ChatColor.translateAlternateColorCodes('&', from);
    }
    private static String papi (Player p, String s){
        s = PlaceholderAPI.setPlaceholders(p.getPlayer(), s);
        return s;
    }

    public static String Gradient(String input) {
        // Найдем индексы символов '<' и '>'
        int startIdx = input.indexOf('<');
        int endIdx = input.indexOf('>');

        if (startIdx == -1 || endIdx == -1 || endIdx <= startIdx) {
            // Если не удалось найти '<' и '>', вернем исходную строку
            return input;
        }

        // Извлекаем строку между '<' и '>'
        String colorString = input.substring(startIdx + 1, endIdx);

        // Разделяем строку на два шестнадцатеричных цветовых кода и текст
        String[] parts = colorString.split(":");

        if (parts.length != 2) {
            // Если не удалось разделить строку на два цвета, вернем исходную строку
            return input;
        }

        String hexColor1 = parts[0];
        String hexColor2 = parts[1];

        // Извлекаем текст после '>'
        String text = input.substring(endIdx + 1);

        int length = text.length();
        Color color1 = hexToColor(hexColor1);
        Color color2 = hexToColor(hexColor2);

        StringBuilder gradientText = new StringBuilder();

        for (int i = 0; i < length; i++) {
            float ratio = (float) i / (length - 1);
            Color interpolatedColor = interpolateColors(color1, color2, ratio);
            String hex = colorToHex(interpolatedColor);
            gradientText.append("#").append(hex).append(text.charAt(i));
        }

        return gradientText.toString();
    }


    public static String fullformat(Player player, String input) {
        String formatted = papi(player, input);
        formatted = Gradient(formatted);
        return color(formatted);

    }


    private static Color hexToColor(String hex) {
        int r = Integer.parseInt(hex.substring(1, 3), 16);
        int g = Integer.parseInt(hex.substring(3, 5), 16);
        int b = Integer.parseInt(hex.substring(5, 7), 16);
        return Color.fromRGB(r, g, b);
    }

    private static String colorToHex(Color color) {
        int r = color.getRed();
        int g = color.getGreen();
        int b = color.getBlue();
        return String.format("%02X%02X%02X", r, g, b);
    }

    private static Color interpolateColors(Color color1, Color color2, float ratio) {
        int r = (int) (color1.getRed() * (1 - ratio) + color2.getRed() * ratio);
        int g = (int) (color1.getGreen() * (1 - ratio) + color2.getGreen() * ratio);
        int b = (int) (color1.getBlue() * (1 - ratio) + color2.getBlue() * ratio);
        return Color.fromRGB(r, g, b);
    }

}