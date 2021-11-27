package passwordGenerator;

import java.security.SecureRandom;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class passGen {

    Function<Integer[], Stream<Character>> getUnicodeElement = (e) -> new SecureRandom()
            .ints(e[0], e[1], e[2])
            .mapToObj(data -> (char) data);

    public String generateRandomSecurePassword() {
        Stream<Character> pwdStream = Stream.concat(getUnicodeElement.apply(new Integer[] { 2, 48, 57 }),
                Stream.concat(getUnicodeElement.apply(new Integer[] { 2, 35, 38 }),
                        Stream.concat(getUnicodeElement.apply(new Integer[] { 2, 65, 90 }),
                                (getUnicodeElement.apply(new Integer[] { 5, 97, 122 })))));

        List<Character> charlist = pwdStream.collect(Collectors.toList());
        String password = charlist.stream().collect(StringBuilder::new,
                StringBuilder::appendCodePoint,
                StringBuilder::append).toString();
        return password;
    }
}
