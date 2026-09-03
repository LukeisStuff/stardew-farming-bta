package luke.stardew.misc;

import java.util.Random;

public class TweakMyMessage {
    private TweakMyMessage() {}

    public static String tweakMessage(String orginalMessage) {
        if (orginalMessage.startsWith("/")) {
            return orginalMessage;
        }

        final var rand = new Random();

        StringBuilder builder = new StringBuilder();

        boolean newWord = true;
        String vowels = "aeiou";

        for (char ch : orginalMessage.toCharArray()) {
            if (ch == ' ') {
                builder.append(ch);
                newWord = true;
                continue;
            }

            if (".,-".contains(Character.toString(ch))) {
                builder.append(ch);
                newWord = false;
                continue;
            }

            if (newWord) {
                builder.append(ch);
                newWord = false;

                if (rand.nextBoolean()) {
                    continue;
                }

                var upper = Character.toUpperCase(ch);

                builder.append(String.valueOf(upper).repeat(rand.nextInt(3)));
                builder.append("-").append(upper);

                continue;
            }

            if (vowels.contains(Character.toString(ch)) && rand.nextInt(3) == 0) {
                builder.append(String.valueOf(ch).repeat(rand.nextInt(3)));
            }

            if (rand.nextInt(6) == 0) {
                builder.append("-");
            }

            if (rand.nextInt(6) == 0) {
                builder.append(ch);
            }

            if (rand.nextInt(6) == 0) {
                builder.append(Character.toUpperCase(ch));
            }
            else {
                builder.append(ch);
            }
        }

        return builder.toString();
    }
}
